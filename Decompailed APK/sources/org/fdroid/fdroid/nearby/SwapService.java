package org.fdroid.fdroid.nearby;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.ServiceCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import cc.mvdan.accesspoint.WifiApControl;
import ch.qos.logback.core.CoreConstants;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.jvm.functions.Function1;
import org.fdroid.database.Repository;
import org.fdroid.download.NotFoundException;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.NotificationHelper;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.peers.Peer;
import org.fdroid.fdroid.net.DownloaderFactory;
import org.fdroid.fdroid.net.DownloaderService;
import org.fdroid.fdroid.views.main.MainActivity;
import org.fdroid.index.IndexParser;
import org.fdroid.index.IndexParserKt;
import org.fdroid.index.SigningException;
import org.fdroid.index.v1.IndexV1;
import org.fdroid.index.v1.IndexV1UpdaterKt;
import org.fdroid.index.v1.IndexV1Verifier;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: loaded from: classes2.dex */
public class SwapService extends Service {
    private static final String KEY_APPS_TO_SWAP = "appsToSwap";
    private static final String KEY_BLUETOOTH_ENABLED = "bluetoothEnabled";
    private static final String KEY_BLUETOOTH_ENABLED_BEFORE_SWAP = "bluetoothEnabledBeforeSwap";
    private static final String KEY_BLUETOOTH_NAME_BEFORE_SWAP = "bluetoothNameBeforeSwap";
    private static final String KEY_HOTSPOT_ACTIVATED = "hotspotEnabled";
    private static final String KEY_HOTSPOT_ACTIVATED_BEFORE_SWAP = "hotspotEnabledBeforeSwap";
    private static final String KEY_WIFI_ENABLED = "wifiEnabled";
    private static final String KEY_WIFI_ENABLED_BEFORE_SWAP = "wifiEnabledBeforeSwap";
    private static final int NOTIFICATION = 1;
    private static final String SHARED_PREFERENCES = "swap-state";
    private static final String TAG = "SwapService";
    private static final int TIMEOUT = 900000;
    private static BluetoothAdapter bluetoothAdapter;
    private static LocalBroadcastManager localBroadcastManager;
    private static Timer pollConnectedSwapRepoTimer;
    private static SharedPreferences swapPreferences;
    private static WifiManager wifiManager;
    private Peer peer;
    private Repository peerRepo;
    private Timer timer;
    private final Set<String> appsToSwap = new HashSet();
    private final Set<Peer> activePeers = new HashSet();
    private final MutableLiveData index = new MutableLiveData();
    private final MutableLiveData indexError = new MutableLiveData();
    private final Binder binder = new Binder();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final Preferences.ChangeListener httpsEnabledListener = new Preferences.ChangeListener() { // from class: org.fdroid.fdroid.nearby.SwapService$$ExternalSyntheticLambda1
        @Override // org.fdroid.fdroid.Preferences.ChangeListener
        public final void onPreferenceChange() {
            this.f$0.restartWiFiServices();
        }
    };
    private final BroadcastReceiver onWifiChange = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapService.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SwapService.this.restartWiFiServices();
        }
    };
    private final BroadcastReceiver bluetoothScanModeChanged = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapService.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.SCAN_MODE", -1);
            if (intExtra == 20) {
                BluetoothManager.stop(SwapService.this);
            } else if (intExtra == 21 || intExtra == 23) {
                BluetoothManager.start(SwapService.this);
            }
        }
    };
    private final BroadcastReceiver bluetoothPeerFound = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapService.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SwapService.this.activePeers.add((Peer) intent.getParcelableExtra(BluetoothManager.EXTRA_PEER));
        }
    };
    private final BroadcastReceiver bonjourPeerFound = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapService.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SwapService.this.activePeers.add((Peer) intent.getParcelableExtra(BonjourManager.EXTRA_BONJOUR_PEER));
        }
    };
    private final BroadcastReceiver bonjourPeerRemoved = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapService.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SwapService.this.activePeers.remove((Peer) intent.getParcelableExtra(BonjourManager.EXTRA_BONJOUR_PEER));
        }
    };

    public static void stop(Context context) {
        context.stopService(new Intent(context, (Class<?>) SwapService.class));
    }

    public Set<String> getAppsToSwap() {
        return this.appsToSwap;
    }

    public Set<Peer> getActivePeers() {
        return this.activePeers;
    }

    public void connectToPeer() {
        if (getPeer() == null) {
            throw new IllegalStateException("Cannot connect to peer, no peer has been selected.");
        }
        connectTo(getPeer());
        if (LocalHTTPDManager.isAlive() && getPeer().shouldPromptForSwapBack()) {
            askServerToSwapWithUs(this.peerRepo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectTo(Peer peer) {
        if (peer != this.peer) {
            Log.e(TAG, "Oops, got a different peer to swap with than initially planned.");
        }
        Repository repositoryCreateSwapRepo = FDroidApp.createSwapRepo(peer.getRepoAddress(), null);
        this.peerRepo = repositoryCreateSwapRepo;
        try {
            updateRepo(peer, repositoryCreateSwapRepo);
        } catch (Exception e) {
            Log.e(TAG, "Error updating repo.", e);
            this.indexError.postValue(e);
        }
    }

    public static IndexV1 getVerifiedRepoIndex(Repository repository, String str, File file) throws InterruptedException, IOException, NotFoundException, SigningException {
        DownloaderFactory.INSTANCE.createWithTryFirstMirror(repository, Uri.parse(repository.getAddress()).buildUpon().appendPath(IndexV1UpdaterKt.SIGNED_FILE_NAME).build(), FileV2.fromPath("/index-v1.jar"), file).download();
        return (IndexV1) new IndexV1Verifier(file, null, str).getStreamAndVerify(new Function1() { // from class: org.fdroid.fdroid.nearby.SwapService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SwapService.lambda$getVerifiedRepoIndex$0((InputStream) obj);
            }
        }).getSecond();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ IndexV1 lambda$getVerifiedRepoIndex$0(InputStream inputStream) {
        return IndexParserKt.parseV1(IndexParser.INSTANCE, inputStream);
    }

    private void updateRepo(Peer peer, Repository repository) throws InterruptedException, IOException, SigningException, NotFoundException {
        File fileCreateTempFile = File.createTempFile("swap", "", getApplicationContext().getCacheDir());
        try {
            try {
                this.index.postValue(getVerifiedRepoIndex(repository, peer.getFingerprint(), fileCreateTempFile));
                startPollingConnectedSwapRepo();
            } catch (NotFoundException unused) {
                DownloaderFactory.INSTANCE.createWithTryFirstMirror(repository, Uri.parse(repository.getAddress()).buildUpon().appendPath("index.jar").build(), FileV2.fromPath("/index.jar"), File.createTempFile("ignored-", "")).download();
                throw new FileNotFoundException(getApplicationContext().getString(R.string.swap_connection_indexv0_error));
            }
        } finally {
            fileCreateTempFile.delete();
        }
    }

    public Repository getPeerRepo() {
        return this.peerRepo;
    }

    public LiveData getIndex() {
        return this.index;
    }

    public LiveData getIndexError() {
        return this.indexError;
    }

    public void swapWith(Peer peer) {
        this.peer = peer;
    }

    public void addCurrentPeerToActive() {
        this.activePeers.add(this.peer);
    }

    public void removeCurrentPeerFromActive() {
        this.activePeers.remove(this.peer);
    }

    public boolean isConnectingWithPeer() {
        return this.peer != null;
    }

    public Peer getPeer() {
        return this.peer;
    }

    private void persistAppsToSwap() {
        swapPreferences.edit().putString(KEY_APPS_TO_SWAP, serializePackages(this.appsToSwap)).apply();
    }

    private static String serializePackages(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        for (String str : set) {
            if (sb.length() > 0) {
                sb.append(CoreConstants.COMMA_CHAR);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private static Set<String> deserializePackages(String str) {
        HashSet hashSet = new HashSet();
        if (!TextUtils.isEmpty(str)) {
            Collections.addAll(hashSet, str.split(","));
        }
        return hashSet;
    }

    public void ensureFDroidSelected() {
        String packageName = getPackageName();
        if (hasSelectedPackage(packageName)) {
            return;
        }
        selectPackage(packageName);
    }

    public boolean hasSelectedPackage(String str) {
        return this.appsToSwap.contains(str);
    }

    public void selectPackage(String str) {
        this.appsToSwap.add(str);
        persistAppsToSwap();
    }

    public void deselectPackage(String str) {
        if (this.appsToSwap.contains(str)) {
            this.appsToSwap.remove(str);
        }
        persistAppsToSwap();
    }

    public static boolean getBluetoothVisibleUserPreference() {
        return swapPreferences.getBoolean(KEY_BLUETOOTH_ENABLED, false);
    }

    public static void putBluetoothVisibleUserPreference(boolean z) {
        swapPreferences.edit().putBoolean(KEY_BLUETOOTH_ENABLED, z).apply();
    }

    public static boolean getWifiVisibleUserPreference() {
        return swapPreferences.getBoolean(KEY_WIFI_ENABLED, false);
    }

    public static void putWifiVisibleUserPreference(boolean z) {
        swapPreferences.edit().putBoolean(KEY_WIFI_ENABLED, z).apply();
    }

    public static boolean getHotspotActivatedUserPreference() {
        return swapPreferences.getBoolean(KEY_HOTSPOT_ACTIVATED, false);
    }

    public static void putHotspotActivatedUserPreference(boolean z) {
        swapPreferences.edit().putBoolean(KEY_HOTSPOT_ACTIVATED, z).apply();
    }

    public static boolean wasBluetoothEnabledBeforeSwap() {
        return swapPreferences.getBoolean(KEY_BLUETOOTH_ENABLED_BEFORE_SWAP, false);
    }

    public static void putBluetoothEnabledBeforeSwap(boolean z) {
        swapPreferences.edit().putBoolean(KEY_BLUETOOTH_ENABLED_BEFORE_SWAP, z).apply();
    }

    public static String getBluetoothNameBeforeSwap() {
        return swapPreferences.getString(KEY_BLUETOOTH_NAME_BEFORE_SWAP, null);
    }

    public static void putBluetoothNameBeforeSwap(String str) {
        swapPreferences.edit().putString(KEY_BLUETOOTH_NAME_BEFORE_SWAP, str).apply();
    }

    public static boolean wasWifiEnabledBeforeSwap() {
        return swapPreferences.getBoolean(KEY_WIFI_ENABLED_BEFORE_SWAP, false);
    }

    public static void putWifiEnabledBeforeSwap(boolean z) {
        swapPreferences.edit().putBoolean(KEY_WIFI_ENABLED_BEFORE_SWAP, z).apply();
    }

    public static boolean wasHotspotEnabledBeforeSwap() {
        return swapPreferences.getBoolean(KEY_HOTSPOT_ACTIVATED_BEFORE_SWAP, false);
    }

    public static void putHotspotEnabledBeforeSwap(boolean z) {
        swapPreferences.edit().putBoolean(KEY_HOTSPOT_ACTIVATED_BEFORE_SWAP, z).apply();
    }

    public class Binder extends android.os.Binder {
        public Binder() {
        }

        public SwapService getService() {
            return SwapService.this;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        WifiManager wifiManager2;
        super.onCreate();
        boolean z = true;
        startForeground(1, createNotification());
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        swapPreferences = getSharedPreferences(SHARED_PREFERENCES, 0);
        LocalHTTPDManager.start(this);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter = defaultAdapter;
        if (defaultAdapter != null) {
            putBluetoothEnabledBeforeSwap(defaultAdapter.isEnabled());
            if (bluetoothAdapter.isEnabled()) {
                BluetoothManager.start(this);
            }
            registerReceiver(this.bluetoothScanModeChanged, new IntentFilter("android.bluetooth.adapter.action.SCAN_MODE_CHANGED"));
        }
        WifiManager wifiManager3 = (WifiManager) ContextCompat.getSystemService(getApplicationContext(), WifiManager.class);
        wifiManager = wifiManager3;
        if (wifiManager3 != null) {
            putWifiEnabledBeforeSwap(wifiManager3.isWifiEnabled());
        }
        this.appsToSwap.addAll(deserializePackages(swapPreferences.getString(KEY_APPS_TO_SWAP, "")));
        Preferences.get().registerLocalRepoHttpsListeners(this.httpsEnabledListener);
        localBroadcastManager.registerReceiver(this.onWifiChange, new IntentFilter(WifiStateChangeService.BROADCAST));
        localBroadcastManager.registerReceiver(this.bluetoothPeerFound, new IntentFilter(BluetoothManager.ACTION_FOUND));
        localBroadcastManager.registerReceiver(this.bonjourPeerFound, new IntentFilter(BonjourManager.ACTION_FOUND));
        localBroadcastManager.registerReceiver(this.bonjourPeerRemoved, new IntentFilter(BonjourManager.ACTION_REMOVED));
        if (Build.VERSION.SDK_INT <= 28) {
            if (getHotspotActivatedUserPreference()) {
                WifiApControl wifiApControl = WifiApControl.getInstance(this);
                if (wifiApControl != null) {
                    wifiApControl.enable();
                }
            } else if (getWifiVisibleUserPreference() && (wifiManager2 = wifiManager) != null && !wifiManager2.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
        }
        BonjourManager.start(this);
        if (!getWifiVisibleUserPreference() && !getHotspotActivatedUserPreference()) {
            z = false;
        }
        BonjourManager.setVisible(this, z);
    }

    private void askServerToSwapWithUs(final Repository repository) {
        this.compositeDisposable.add(Completable.fromAction(new Action() { // from class: org.fdroid.fdroid.nearby.SwapService$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                SwapService.lambda$askServerToSwapWithUs$1(repository);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).onErrorComplete(new Predicate() { // from class: org.fdroid.fdroid.nearby.SwapService$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$askServerToSwapWithUs$2(repository, (Throwable) obj);
            }
        }).subscribe());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$askServerToSwapWithUs$1(Repository repository) throws Throwable {
        String string = Utils.getLocalRepoUri(FDroidApp.repo).toString();
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(repository.getAddress().replace("/fdroid/repo", "/request-swap")).openConnection();
            try {
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setDoOutput(true);
                OutputStream outputStream = httpURLConnection2.getOutputStream();
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    try {
                        outputStreamWriter.write("repo=" + string);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        Utils.debugLog(TAG, "Asking server at " + repository.getAddress() + " to swap with us in return (by POSTing to \"/request-swap\" with repo \"" + string + "\"): " + httpURLConnection2.getResponseCode());
                        httpURLConnection2.disconnect();
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                th = th;
                httpURLConnection = httpURLConnection2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$askServerToSwapWithUs$2(Repository repository, Throwable th) throws Throwable {
        Intent intent = new Intent(DownloaderService.ACTION_INTERRUPTED);
        intent.setData(Uri.parse(repository.getAddress()));
        intent.putExtra(DownloaderService.EXTRA_ERROR_MESSAGE, th.getLocalizedMessage());
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        return true;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Intent intent2 = new Intent(this, (Class<?>) SwapWorkflowActivity.class);
        if (intent.getData() != null) {
            intent2.setData(intent.getData());
            intent2.setAction(MainActivity.ACTION_REQUEST_SWAP);
        }
        intent2.setFlags(268435456);
        startActivity(intent2);
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        initTimer();
        return this.binder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.compositeDisposable.dispose();
        Utils.debugLog(TAG, "Destroying service, will disable swapping if required, and unregister listeners.");
        Preferences.get().unregisterLocalRepoHttpsListeners(this.httpsEnabledListener);
        localBroadcastManager.unregisterReceiver(this.onWifiChange);
        localBroadcastManager.unregisterReceiver(this.bluetoothPeerFound);
        localBroadcastManager.unregisterReceiver(this.bonjourPeerFound);
        localBroadcastManager.unregisterReceiver(this.bonjourPeerRemoved);
        if (bluetoothAdapter != null) {
            unregisterReceiver(this.bluetoothScanModeChanged);
        }
        BluetoothManager.stop(this);
        BonjourManager.stop(this);
        LocalHTTPDManager.stop(this);
        if (Build.VERSION.SDK_INT <= 28) {
            if (wifiManager != null && !wasWifiEnabledBeforeSwap()) {
                wifiManager.setWifiEnabled(false);
            }
            WifiApControl wifiApControl = WifiApControl.getInstance(this);
            if (wifiApControl != null) {
                try {
                    if (wasHotspotEnabledBeforeSwap()) {
                        wifiApControl.enable();
                    } else {
                        wifiApControl.disable();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "could not access/enable/disable WiFi AP", e);
                }
            }
        }
        stopPollingConnectedSwapRepo();
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
        ServiceCompat.stopForeground(this, 1);
        super.onDestroy();
    }

    private Notification createNotification() {
        Intent intent = new Intent(this, (Class<?>) SwapWorkflowActivity.class);
        intent.setFlags(603979776);
        return new NotificationCompat.Builder(this, NotificationHelper.CHANNEL_SWAPS).setContentTitle(getText(R.string.local_repo_running)).setContentText(getText(R.string.touch_to_configure_local_repo)).setSmallIcon(R.drawable.ic_nearby).setContentIntent(PendingIntent.getActivity(this, 0, intent, 335544320)).build();
    }

    private void startPollingConnectedSwapRepo() {
        stopPollingConnectedSwapRepo();
        pollConnectedSwapRepoTimer = new Timer("pollConnectedSwapRepoTimer", true);
        pollConnectedSwapRepoTimer.schedule(new TimerTask() { // from class: org.fdroid.fdroid.nearby.SwapService.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (SwapService.this.peer != null) {
                    SwapService swapService = SwapService.this;
                    swapService.connectTo(swapService.peer);
                }
            }
        }, 5000L);
    }

    public void stopPollingConnectedSwapRepo() {
        Timer timer = pollConnectedSwapRepoTimer;
        if (timer != null) {
            timer.cancel();
            pollConnectedSwapRepoTimer = null;
        }
    }

    public void initTimer() {
        if (this.timer != null) {
            Utils.debugLog(TAG, "Cancelling existing timeout timer so timeout can be reset.");
            this.timer.cancel();
        }
        Utils.debugLog(TAG, "Initializing swap timeout to 900000ms minutes");
        Timer timer = new Timer(TAG, true);
        this.timer = timer;
        timer.schedule(new TimerTask() { // from class: org.fdroid.fdroid.nearby.SwapService.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Utils.debugLog(SwapService.TAG, "Disabling swap because 900000ms passed.");
                Utils.showToastFromService(SwapService.this, SwapService.this.getString(R.string.swap_toast_closing_nearby_after_timeout), 1);
                SwapService.stop(SwapService.this);
            }
        }, 900000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartWiFiServices() {
        if (FDroidApp.ipAddressString != null) {
            LocalHTTPDManager.restart(this);
            BonjourManager.restart(this);
            BonjourManager.setVisible(this, getWifiVisibleUserPreference() || getHotspotActivatedUserPreference());
        } else {
            BonjourManager.stop(this);
            LocalHTTPDManager.stop(this);
        }
    }
}
