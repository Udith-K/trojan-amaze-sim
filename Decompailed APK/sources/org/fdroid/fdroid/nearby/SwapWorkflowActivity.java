package org.fdroid.fdroid.nearby;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$RequestPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import cc.mvdan.accesspoint.WifiApControl;
import ch.qos.logback.core.CoreConstants;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Locale;
import java.util.Stack;
import java.util.TimerTask;
import org.fdroid.fdroid.BuildConfig;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.SwapService;
import org.fdroid.fdroid.nearby.peers.BluetoothPeer;
import org.fdroid.fdroid.nearby.peers.Peer;
import org.fdroid.fdroid.nearby.peers.WifiPeer;
import org.fdroid.fdroid.net.BluetoothDownloader;
import org.fdroid.fdroid.qr.CameraCharacteristicsChecker;
import org.fdroid.fdroid.views.main.MainActivity;
import org.fdroid.index.v1.IndexV1;

/* JADX INFO: loaded from: classes2.dex */
public class SwapWorkflowActivity extends AppCompatActivity {
    public static final String EXTRA_PREVENT_FURTHER_SWAP_REQUESTS = "preventFurtherSwap";
    private static final int REQUEST_BLUETOOTH_DISCOVERABLE = 3;
    private static final int REQUEST_BLUETOOTH_ENABLE_FOR_SEND = 4;
    private static final int REQUEST_BLUETOOTH_ENABLE_FOR_SWAP = 2;
    private static final int REQUEST_WRITE_SETTINGS_PERMISSION = 5;
    private static final String TAG = "SwapWorkflowActivity";
    private BluetoothAdapter bluetoothAdapter;
    private NewRepoConfig confirmSwapConfig;
    private ViewGroup container;
    private SwapView currentView;
    private boolean hasPreparedLocalRepo;
    private LocalBroadcastManager localBroadcastManager;
    private boolean newIntent;
    private SwapService service;
    private MaterialToolbar toolbar;
    private WifiApControl wifiApControl;
    private WifiManager wifiManager;
    private int currentSwapViewLayoutRes = R.layout.swap_start_swap;
    private final Stack<Integer> backstack = new Stack<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final ActivityResultLauncher requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts$RequestPermission(), new ActivityResultCallback() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda10
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            this.f$0.lambda$new$0((Boolean) obj);
        }
    });
    private final ServiceConnection serviceConnection = new AnonymousClass1();
    private final BroadcastReceiver bluetoothScanModeChanged = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MaterialSwitch materialSwitch = (MaterialSwitch) SwapWorkflowActivity.this.container.findViewById(R.id.switch_bluetooth);
            TextView textView = (TextView) SwapWorkflowActivity.this.container.findViewById(R.id.bluetooth_visible);
            if (materialSwitch == null || textView == null || !BluetoothManager.ACTION_STATUS.equals(intent.getAction())) {
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.SCAN_MODE", -1);
            if (intExtra == 20) {
                textView.setText(R.string.disabled);
                materialSwitch.setEnabled(true);
            } else if (intExtra == 21) {
                textView.setText(R.string.swap_not_visible_bluetooth);
                materialSwitch.setEnabled(true);
            } else {
                if (intExtra != 23) {
                    return;
                }
                textView.setText(R.string.swap_visible_bluetooth);
                materialSwitch.setEnabled(true);
            }
        }
    };
    private final BroadcastReceiver onWifiStateChanged = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SwapWorkflowActivity.this.setUpFromWifi();
            TextView textView = (TextView) SwapWorkflowActivity.this.container.findViewById(R.id.wifi_visible);
            if (textView == null) {
                return;
            }
            int intExtra = intent.getIntExtra(WifiStateChangeService.EXTRA_STATUS, -1);
            if (intExtra == 0 || intExtra == 1) {
                textView.setText(R.string.swap_stopping_wifi);
            } else if (intExtra == 2) {
                textView.setText(R.string.swap_setting_up_wifi);
            } else if (intExtra == 3) {
                textView.setText(R.string.swap_not_visible_wifi);
            }
            SwapWorkflowActivity.this.updateWifiBannerVisibility();
        }
    };
    private final BroadcastReceiver bonjourStatusReceiver = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.5
        private volatile int bonjourStatus = 3;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (BonjourManager.ACTION_STATUS.equals(intent.getAction())) {
                this.bonjourStatus = intent.getIntExtra(BonjourManager.EXTRA_STATUS, this.bonjourStatus);
                TextView textView = (TextView) SwapWorkflowActivity.this.container.findViewById(R.id.wifi_visible);
                TextView textView2 = (TextView) SwapWorkflowActivity.this.container.findViewById(R.id.text_people_nearby);
                CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) SwapWorkflowActivity.this.container.findViewById(R.id.searching_people_nearby);
                if (textView == null || textView2 == null || circularProgressIndicator == null) {
                    return;
                }
                int i = this.bonjourStatus;
                if (i != 65535) {
                    switch (i) {
                        case 0:
                            textView.setText(R.string.swap_setting_up_wifi);
                            textView2.setText(R.string.swap_starting);
                            textView2.setVisibility(0);
                            circularProgressIndicator.setVisibility(0);
                            break;
                        case 1:
                            textView.setText(R.string.swap_not_visible_wifi);
                            textView2.setText(R.string.swap_scanning_for_peers);
                            textView2.setVisibility(0);
                            circularProgressIndicator.setVisibility(0);
                            break;
                        case 2:
                            textView.setText(R.string.swap_stopping_wifi);
                            if (!BluetoothManager.isAlive()) {
                                textView2.setText(R.string.swap_stopping);
                                textView2.setVisibility(0);
                                circularProgressIndicator.setVisibility(0);
                            }
                            break;
                        case 3:
                            textView.setText(R.string.swap_not_visible_wifi);
                            if (!BluetoothManager.isAlive()) {
                                textView2.setVisibility(8);
                                circularProgressIndicator.setVisibility(8);
                            }
                            break;
                        case 4:
                            if (SwapWorkflowActivity.this.wifiApControl != null && SwapWorkflowActivity.this.wifiApControl.isEnabled()) {
                                textView.setText(R.string.swap_visible_hotspot);
                            } else {
                                textView.setText(R.string.swap_visible_wifi);
                            }
                            textView2.setText(R.string.swap_scanning_for_peers);
                            textView2.setVisibility(0);
                            circularProgressIndicator.setVisibility(0);
                            break;
                        case 5:
                            textView.setText(R.string.swap_not_visible_wifi);
                            textView2.setText(R.string.swap_scanning_for_peers);
                            textView2.setVisibility(0);
                            circularProgressIndicator.setVisibility(0);
                            break;
                        case 6:
                            textView.setText(R.string.swap_wifi_vpn_conflict);
                            break;
                        default:
                            Log.i(SwapWorkflowActivity.TAG, "Bad intent: " + intent + " " + this.bonjourStatus);
                            break;
                    }
                }
                textView.setText(R.string.swap_not_visible_wifi);
                textView2.setText(intent.getStringExtra("android.intent.extra.TEXT"));
                textView2.setVisibility(0);
                circularProgressIndicator.setVisibility(8);
            }
        }
    };
    private final BroadcastReceiver bonjourFound = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ListView listView = (ListView) SwapWorkflowActivity.this.container.findViewById(R.id.list_people_nearby);
            if (listView != null) {
                ArrayAdapter arrayAdapter = (ArrayAdapter) listView.getAdapter();
                Peer peer = (Peer) intent.getParcelableExtra(BonjourManager.EXTRA_BONJOUR_PEER);
                if (arrayAdapter.getPosition(peer) == -1) {
                    arrayAdapter.add(peer);
                }
            }
        }
    };
    private final BroadcastReceiver bonjourRemoved = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ListView listView = (ListView) SwapWorkflowActivity.this.container.findViewById(R.id.list_people_nearby);
            if (listView != null) {
                ((ArrayAdapter) listView.getAdapter()).remove((Peer) intent.getParcelableExtra(BonjourManager.EXTRA_BONJOUR_PEER));
            }
        }
    };
    private final BroadcastReceiver bluetoothStatusReceiver = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.8
        private volatile int bluetoothStatus = 3;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (BluetoothManager.ACTION_STATUS.equals(intent.getAction())) {
                this.bluetoothStatus = intent.getIntExtra(BluetoothManager.EXTRA_STATUS, this.bluetoothStatus);
                MaterialSwitch materialSwitch = (MaterialSwitch) SwapWorkflowActivity.this.container.findViewById(R.id.switch_bluetooth);
                TextView textView = (TextView) SwapWorkflowActivity.this.container.findViewById(R.id.bluetooth_visible);
                TextView textView2 = (TextView) SwapWorkflowActivity.this.container.findViewById(R.id.device_id_bluetooth);
                TextView textView3 = (TextView) SwapWorkflowActivity.this.container.findViewById(R.id.text_people_nearby);
                CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) SwapWorkflowActivity.this.container.findViewById(R.id.searching_people_nearby);
                if (materialSwitch == null || textView == null || textView2 == null || textView3 == null || circularProgressIndicator == null) {
                    return;
                }
                int i = this.bluetoothStatus;
                if (i == 0) {
                    materialSwitch.setEnabled(false);
                    textView.setText(R.string.swap_setting_up_bluetooth);
                    textView2.setVisibility(0);
                    textView3.setText(R.string.swap_scanning_for_peers);
                    textView3.setVisibility(0);
                    circularProgressIndicator.setVisibility(0);
                    return;
                }
                if (i == 1) {
                    materialSwitch.setEnabled(true);
                    textView.setText(R.string.swap_visible_bluetooth);
                    textView2.setVisibility(0);
                    textView3.setText(R.string.swap_scanning_for_peers);
                    textView3.setVisibility(0);
                    circularProgressIndicator.setVisibility(0);
                    return;
                }
                if (i == 2) {
                    materialSwitch.setEnabled(false);
                    textView.setText(R.string.swap_stopping);
                    textView2.setVisibility(8);
                    if (BonjourManager.isAlive()) {
                        return;
                    }
                    textView3.setText(R.string.swap_stopping);
                    textView3.setVisibility(0);
                    circularProgressIndicator.setVisibility(0);
                    return;
                }
                if (i != 3) {
                    if (i == 65535) {
                        materialSwitch.setEnabled(true);
                        textView.setText(intent.getStringExtra("android.intent.extra.TEXT"));
                        textView2.setVisibility(0);
                        return;
                    }
                    throw new IllegalArgumentException("Bad intent: " + intent);
                }
                materialSwitch.setEnabled(true);
                textView.setText(R.string.swap_not_visible_bluetooth);
                textView2.setVisibility(8);
                if (!BonjourManager.isAlive()) {
                    textView3.setVisibility(8);
                    circularProgressIndicator.setVisibility(8);
                }
                ListView listView = (ListView) SwapWorkflowActivity.this.container.findViewById(R.id.list_people_nearby);
                if (listView == null) {
                    return;
                }
                ArrayAdapter arrayAdapter = (ArrayAdapter) listView.getAdapter();
                for (int i2 = 0; i2 < arrayAdapter.getCount(); i2++) {
                    Peer peer = (Peer) arrayAdapter.getItem(i2);
                    if (peer.getClass().equals(BluetoothPeer.class)) {
                        Utils.debugLog(SwapWorkflowActivity.TAG, "Removing bluetooth peer: " + peer.getName());
                        arrayAdapter.remove(peer);
                    }
                }
            }
        }
    };
    private final BroadcastReceiver bluetoothFound = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.9
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ListView listView = (ListView) SwapWorkflowActivity.this.container.findViewById(R.id.list_people_nearby);
            if (listView != null) {
                ArrayAdapter arrayAdapter = (ArrayAdapter) listView.getAdapter();
                Peer peer = (Peer) intent.getParcelableExtra(BluetoothManager.EXTRA_PEER);
                if (arrayAdapter.getPosition(peer) == -1) {
                    arrayAdapter.add(peer);
                }
            }
        }
    };
    private final BroadcastReceiver localRepoStatus = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SwapWorkflowActivity.this.setUpConnectingProgressText(intent.getStringExtra("android.intent.extra.TEXT"));
            CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) SwapWorkflowActivity.this.container.findViewById(R.id.progress_bar);
            Button button = (Button) SwapWorkflowActivity.this.container.findViewById(R.id.try_again);
            if (circularProgressIndicator == null || button == null) {
                return;
            }
            int intExtra = intent.getIntExtra(LocalRepoService.EXTRA_STATUS, -1);
            if (intExtra == 0) {
                circularProgressIndicator.show();
                button.setVisibility(8);
                SwapWorkflowActivity.this.onLocalRepoPrepared();
            } else if (intExtra == 1) {
                circularProgressIndicator.show();
                button.setVisibility(8);
            } else if (intExtra == 2) {
                circularProgressIndicator.hide();
                button.setVisibility(0);
            } else {
                throw new IllegalArgumentException("Bogus intent: " + intent);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$promptToSelectWifiNetwork$3(DialogInterface dialogInterface, int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Boolean bool) {
        if (bool.booleanValue()) {
            sendFDroidBluetooth();
        }
    }

    public static void requestSwap(Context context, String str) {
        requestSwap(context, Uri.parse(str));
    }

    public static void requestSwap(Context context, Uri uri) {
        Intent intent = new Intent(MainActivity.ACTION_REQUEST_SWAP, uri, context, SwapWorkflowActivity.class);
        intent.putExtra(EXTRA_PREVENT_FURTHER_SWAP_REQUESTS, true);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.nearby.SwapWorkflowActivity$1, reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {
        AnonymousClass1() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SwapWorkflowActivity.this.service = ((SwapService.Binder) iBinder).getService();
            SwapWorkflowActivity.this.service.getIndex().observe(SwapWorkflowActivity.this, new Observer() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$1$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$onServiceConnected$0((IndexV1) obj);
                }
            });
            SwapWorkflowActivity.this.service.getIndexError().observe(SwapWorkflowActivity.this, new Observer() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$1$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$onServiceConnected$1((Exception) obj);
                }
            });
            SwapWorkflowActivity.this.showRelevantView();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onServiceConnected$0(IndexV1 indexV1) {
            SwapWorkflowActivity.this.onRepoUpdateSuccess();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onServiceConnected$1(Exception exc) {
            SwapWorkflowActivity.this.onRepoUpdateError(exc);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            SwapWorkflowActivity.this.finish();
            SwapWorkflowActivity.this.service.getIndex().removeObservers(SwapWorkflowActivity.this);
            SwapWorkflowActivity.this.service.getIndexError().removeObservers(SwapWorkflowActivity.this);
            SwapWorkflowActivity.this.service = null;
        }
    }

    public SwapService getSwapService() {
        return this.service;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.backstack.isEmpty()) {
            super.onBackPressed();
        } else {
            inflateSwapView(this.backstack.pop().intValue(), true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058 A[PHI: r1
  0x0058: PHI (r1v17 int) = (r1v16 int), (r1v18 int) binds: [B:44:0x00cf, B:16:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onToolbarBackPressed() {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.nearby.SwapWorkflowActivity.onToolbarBackPressed():void");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        super.onCreate(bundle);
        this.currentView = new SwapView(this);
        if (!bindService(new Intent(this, (Class<?>) SwapService.class), this.serviceConnection, 72)) {
            Toast.makeText(this, "ERROR: cannot bind to SwapService!", 1).show();
            finish();
        }
        setContentView(R.layout.swap_activity);
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        this.toolbar = materialToolbar;
        setSupportActionBar(materialToolbar);
        this.container = (ViewGroup) findViewById(R.id.container);
        this.backstack.clear();
        this.localBroadcastManager = LocalBroadcastManager.getInstance(this);
        this.wifiManager = (WifiManager) ContextCompat.getSystemService(getApplicationContext(), WifiManager.class);
        this.wifiApControl = WifiApControl.getInstance(this);
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        new SwapDebug().logStatus();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.compositeDisposable.dispose();
        unbindService(this.serviceConnection);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater menuInflater = getMenuInflater();
        if (this.currentView.getLayoutResId() == R.layout.swap_select_apps) {
            menuInflater.inflate(R.menu.swap_next_search, menu);
            if (getSwapService().isConnectingWithPeer()) {
                setUpNextButton(menu, R.string.next, Integer.valueOf(R.drawable.ic_nearby));
            } else {
                setUpNextButton(menu, R.string.next, null);
            }
            setUpSearchView(menu);
            return true;
        }
        if (this.currentView.getLayoutResId() == R.layout.swap_success) {
            menuInflater.inflate(R.menu.swap_search, menu);
            setUpSearchView(menu);
            return true;
        }
        if (this.currentView.getLayoutResId() == R.layout.swap_join_wifi) {
            menuInflater.inflate(R.menu.swap_next, menu);
            setUpNextButton(menu, R.string.next, Integer.valueOf(R.drawable.ic_arrow_forward));
            return true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void setUpNextButton(Menu menu, int i, Integer num) {
        MenuItem menuItemFindItem = menu.findItem(R.id.action_next);
        String string = getString(i);
        menuItemFindItem.setTitle(string);
        menuItemFindItem.setTitleCondensed(string);
        if (num == null) {
            menuItemFindItem.setVisible(false);
        } else {
            menuItemFindItem.setVisible(true);
            menuItemFindItem.setIcon(num.intValue());
        }
        menuItemFindItem.setShowAsAction(6);
        menuItemFindItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda14
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return this.f$0.lambda$setUpNextButton$1(menuItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setUpNextButton$1(MenuItem menuItem) {
        sendNext();
        return true;
    }

    private void sendNext() {
        int layoutResId = this.currentView.getLayoutResId();
        int i = R.layout.swap_select_apps;
        if (layoutResId == i) {
            onAppsSelected();
        } else if (layoutResId == R.layout.swap_join_wifi) {
            inflateSwapView(i);
        }
    }

    private void setUpSearchView(Menu menu) {
        MenuItem menuItemFindItem = menu.findItem(R.id.action_apps);
        if (menuItemFindItem != null) {
            menuItemFindItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda13
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return this.f$0.lambda$setUpSearchView$2(menuItem);
                }
            });
        }
        SearchView searchView = new SearchView(this);
        MenuItem menuItemFindItem2 = menu.findItem(R.id.action_search);
        menuItemFindItem2.setActionView(searchView);
        menuItemFindItem2.setShowAsAction(1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.2
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                String currentFilterString = SwapWorkflowActivity.this.currentView.getCurrentFilterString();
                if (TextUtils.isEmpty(str)) {
                    str = null;
                }
                if (currentFilterString == null && str == null) {
                    return true;
                }
                if (currentFilterString != null && currentFilterString.equals(str)) {
                    return true;
                }
                SwapWorkflowActivity.this.currentView.setCurrentFilterString(str);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setUpSearchView$2(MenuItem menuItem) {
        inflateSwapView(R.layout.swap_select_apps);
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.localBroadcastManager.registerReceiver(this.onWifiStateChanged, new IntentFilter(WifiStateChangeService.BROADCAST));
        this.localBroadcastManager.registerReceiver(this.localRepoStatus, new IntentFilter(LocalRepoService.ACTION_STATUS));
        this.localBroadcastManager.registerReceiver(this.bonjourFound, new IntentFilter(BonjourManager.ACTION_FOUND));
        this.localBroadcastManager.registerReceiver(this.bonjourRemoved, new IntentFilter(BonjourManager.ACTION_REMOVED));
        this.localBroadcastManager.registerReceiver(this.bonjourStatusReceiver, new IntentFilter(BonjourManager.ACTION_STATUS));
        this.localBroadcastManager.registerReceiver(this.bluetoothFound, new IntentFilter(BluetoothManager.ACTION_FOUND));
        this.localBroadcastManager.registerReceiver(this.bluetoothStatusReceiver, new IntentFilter(BluetoothManager.ACTION_STATUS));
        registerReceiver(this.bluetoothScanModeChanged, new IntentFilter("android.bluetooth.adapter.action.SCAN_MODE_CHANGED"));
        checkIncomingIntent();
        if (this.service != null && this.newIntent) {
            showRelevantView();
            this.newIntent = false;
        }
        if (this.currentSwapViewLayoutRes == R.layout.swap_start_swap) {
            updateWifiBannerVisibility();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        unregisterReceiver(this.bluetoothScanModeChanged);
        this.localBroadcastManager.unregisterReceiver(this.onWifiStateChanged);
        this.localBroadcastManager.unregisterReceiver(this.localRepoStatus);
        this.localBroadcastManager.unregisterReceiver(this.bonjourFound);
        this.localBroadcastManager.unregisterReceiver(this.bonjourRemoved);
        this.localBroadcastManager.unregisterReceiver(this.bonjourStatusReceiver);
        this.localBroadcastManager.unregisterReceiver(this.bluetoothFound);
        this.localBroadcastManager.unregisterReceiver(this.bluetoothStatusReceiver);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.newIntent = true;
    }

    private void checkIncomingIntent() {
        Intent intent = getIntent();
        if (MainActivity.ACTION_REQUEST_SWAP.equals(intent.getAction())) {
            Uri data = intent.getData();
            if (data != null && !isSwapUrl(data) && !BluetoothDownloader.isBluetoothUri(data)) {
                Toast.makeText(this, getString(R.string.swap_toast_invalid_url, data), 1).show();
                return;
            }
            NewRepoConfig newRepoConfig = new NewRepoConfig(this, intent);
            this.confirmSwapConfig = newRepoConfig;
            checkIfNewRepoOnSameWifi(newRepoConfig);
        }
    }

    private static boolean isSwapUrl(Uri uri) {
        return isSwapUrl(uri.getHost(), uri.getPort());
    }

    private static boolean isSwapUrl(String str, int i) {
        return i > 1023 && str.matches("[0-9.]+") && FDroidApp.subnetInfo.isInRange(str);
    }

    private void promptToSelectWifiNetwork() {
        new MaterialAlertDialogBuilder(this).setTitle(R.string.swap_join_same_wifi).setMessage(R.string.swap_join_same_wifi_desc).setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SwapWorkflowActivity.lambda$promptToSelectWifiNetwork$3(dialogInterface, i);
            }
        }).setPositiveButton(R.string.wifi, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$promptToSelectWifiNetwork$4(dialogInterface, i);
            }
        }).setNegativeButton(R.string.wifi_ap, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$promptToSelectWifiNetwork$5(dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$promptToSelectWifiNetwork$4(DialogInterface dialogInterface, int i) {
        SwapService.putWifiEnabledBeforeSwap(this.wifiManager.isWifiEnabled());
        if (Build.VERSION.SDK_INT <= 28) {
            this.wifiManager.setWifiEnabled(true);
        }
        Intent intent = new Intent("android.net.wifi.PICK_WIFI_NETWORK");
        intent.setFlags(268435456);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$promptToSelectWifiNetwork$5(DialogInterface dialogInterface, int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            showTetheringSettings();
        } else if (!Settings.System.canWrite(getBaseContext())) {
            requestWriteSettingsPermission();
        } else {
            setupWifiAP();
        }
    }

    private void setupWifiAP() {
        boolean zEnable;
        WifiApControl wifiApControl = this.wifiApControl;
        if (wifiApControl == null) {
            Log.e(TAG, "WiFi AP is null");
            Toast.makeText(this, R.string.swap_toast_could_not_enable_hotspot, 1).show();
            return;
        }
        SwapService.putHotspotEnabledBeforeSwap(wifiApControl.isEnabled());
        if (Build.VERSION.SDK_INT <= 28) {
            this.wifiManager.setWifiEnabled(false);
        }
        try {
            zEnable = this.wifiApControl.enable();
        } catch (Exception e) {
            Log.e(TAG, "Error enabling WiFi: ", e);
            zEnable = false;
        }
        if (zEnable) {
            Toast.makeText(this, R.string.swap_toast_hotspot_enabled, 0).show();
            SwapService.putHotspotActivatedUserPreference(true);
        } else {
            Toast.makeText(this, R.string.swap_toast_could_not_enable_hotspot, 1).show();
            SwapService.putHotspotActivatedUserPreference(false);
            Log.e(TAG, "Could not enable WiFi AP.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRelevantView() {
        if (this.confirmSwapConfig != null) {
            inflateSwapView(R.layout.swap_confirm_receive);
            setUpConfirmReceive();
            this.confirmSwapConfig = null;
            return;
        }
        int i = this.currentSwapViewLayoutRes;
        int i2 = R.layout.swap_start_swap;
        if (i == i2) {
            showIntro();
        } else if (i == R.layout.swap_connecting) {
            inflateSwapView(i2);
        } else {
            inflateSwapView(i);
        }
    }

    public void inflateSwapView(int i) {
        inflateSwapView(i, false);
        if (i == R.layout.swap_start_swap) {
            updateWifiBannerVisibility();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWifiBannerVisibility() {
        WifiManager wifiManager;
        final View viewFindViewById = findViewById(R.id.wifi_banner);
        if (viewFindViewById != null) {
            if (Build.VERSION.SDK_INT >= 29 && (wifiManager = this.wifiManager) != null && !wifiManager.isWifiEnabled()) {
                Button button = (Button) findViewById(R.id.turn_on_wifi);
                if (button != null) {
                    button.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda7
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            this.f$0.lambda$updateWifiBannerVisibility$6(viewFindViewById, view);
                        }
                    });
                }
                viewFindViewById.setVisibility(0);
                return;
            }
            viewFindViewById.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateWifiBannerVisibility$6(View view, View view2) {
        view.setVisibility(8);
        startActivity(new Intent("android.settings.panel.action.WIFI"));
    }

    public void inflateSwapView(int i, boolean z) {
        int i2;
        getSwapService().initTimer();
        if (!z && (i2 = this.currentSwapViewLayoutRes) != R.layout.swap_connecting && i2 != R.layout.swap_confirm_receive) {
            if (this.backstack.isEmpty()) {
                if (i != R.layout.swap_start_swap) {
                    this.backstack.push(Integer.valueOf(this.currentSwapViewLayoutRes));
                }
            } else {
                int iIntValue = this.backstack.peek().intValue();
                int i3 = this.currentSwapViewLayoutRes;
                if (iIntValue != i3) {
                    this.backstack.push(Integer.valueOf(i3));
                }
            }
        }
        this.container.removeAllViews();
        View viewInflate = ((LayoutInflater) ContextCompat.getSystemService(this, LayoutInflater.class)).inflate(i, this.container, false);
        SwapView swapView = (SwapView) viewInflate;
        this.currentView = swapView;
        swapView.setLayoutResId(i);
        this.currentSwapViewLayoutRes = i;
        this.toolbar.setTitle(this.currentView.getToolbarTitle());
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$inflateSwapView$7(view);
            }
        });
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$inflateSwapView$8(view);
            }
        });
        if (i == R.layout.swap_start_swap) {
            this.toolbar.setNavigationIcon(R.drawable.ic_close);
        } else {
            this.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        }
        this.container.addView(viewInflate);
        supportInvalidateOptionsMenu();
        if (this.currentView.getLayoutResId() == R.layout.swap_send_fdroid) {
            setUpFromWifi();
            setUpUseBluetoothButton();
            return;
        }
        if (this.currentView.getLayoutResId() == R.layout.swap_wifi_qr) {
            setUpFromWifi();
            setUpQrScannerButton();
        } else if (this.currentView.getLayoutResId() == R.layout.swap_select_apps) {
            LocalRepoService.create(this, getSwapService().getAppsToSwap());
        } else if (this.currentView.getLayoutResId() == R.layout.swap_connecting) {
            setUpConnectingView();
        } else if (this.currentView.getLayoutResId() == R.layout.swap_start_swap) {
            setUpStartVisibility();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateSwapView$7(View view) {
        onToolbarBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$inflateSwapView$8(View view) {
        int layoutResId = this.currentView.getLayoutResId();
        int i = R.layout.swap_start_swap;
        if (layoutResId == i) {
            SwapService.stop(this);
            finish();
        } else {
            this.currentSwapViewLayoutRes = i;
            inflateSwapView(i);
        }
    }

    public void showIntro() {
        getSwapService().swapWith(null);
        LocalRepoService.create(this);
        inflateSwapView(R.layout.swap_start_swap);
    }

    public void showTetheringSettings() {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.TetherSettings"));
        intent.setFlags(268435456);
        startActivity(intent);
    }

    public void requestWriteSettingsPermission() {
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", Uri.parse("package:" + getPackageName()));
        intent.addFlags(268435456);
        startActivityForResult(intent, 5);
    }

    public void sendFDroid() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null || (!bluetoothAdapter.isEnabled() && LocalHTTPDManager.isAlive())) {
            inflateSwapView(R.layout.swap_send_fdroid);
        } else {
            sendFDroidBluetooth();
        }
    }

    public void sendFDroidBluetooth() {
        if (this.bluetoothAdapter.isEnabled()) {
            sendFDroidApk();
            return;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                this.requestPermissionLauncher.launch("android.permission.BLUETOOTH_CONNECT");
                return;
            }
            Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_DISCOVERABLE");
            intent.putExtra("android.bluetooth.adapter.extra.DISCOVERABLE_DURATION", 120);
            startActivityForResult(intent, 4);
        }
    }

    private void sendFDroidApk() {
        ((FDroidApp) getApplication()).sendViaBluetooth(this, -1, BuildConfig.APPLICATION_ID);
    }

    public void onAppsSelected() {
        if (this.hasPreparedLocalRepo) {
            onLocalRepoPrepared();
            return;
        }
        LocalRepoService.create(this, getSwapService().getAppsToSwap());
        int i = R.layout.swap_connecting;
        this.currentSwapViewLayoutRes = i;
        inflateSwapView(i);
    }

    public void onLocalRepoPrepared() {
        this.hasPreparedLocalRepo = true;
        if (getSwapService().isConnectingWithPeer()) {
            startSwappingWithPeer();
        } else {
            inflateSwapView(R.layout.swap_wifi_qr);
        }
    }

    private void startSwappingWithPeer() {
        getSwapService().connectToPeer();
        inflateSwapView(R.layout.swap_connecting);
    }

    public void swapWith(Peer peer) {
        getSwapService().swapWith(peer);
        inflateSwapView(R.layout.swap_select_apps);
    }

    public void swapWith(NewRepoConfig newRepoConfig) {
        WifiPeer peer = newRepoConfig.toPeer();
        int i = this.currentSwapViewLayoutRes;
        if (i == R.layout.swap_start_swap || i == R.layout.swap_confirm_receive) {
            swapWith(peer);
        } else {
            getSwapService().swapWith(peer);
            startSwappingWithPeer();
        }
    }

    public void denySwap() {
        showIntro();
    }

    public void initiateQrScan() {
        new IntentIntegrator(this).initiateScan();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        IntentResult activityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (activityResult != null) {
            if (activityResult.getContents() != null) {
                NewRepoConfig newRepoConfig = new NewRepoConfig(this, activityResult.getContents());
                if (newRepoConfig.isValidRepo()) {
                    checkIfNewRepoOnSameWifi(newRepoConfig);
                    this.confirmSwapConfig = newRepoConfig;
                    showRelevantView();
                    return;
                }
                Toast.makeText(this, R.string.swap_qr_isnt_for_swap, 0).show();
                return;
            }
            return;
        }
        if (i == 5) {
            if (Settings.System.canWrite(this)) {
                setupWifiAP();
                return;
            }
            return;
        }
        if (i == 2) {
            if (i2 == -1) {
                Utils.debugLog(TAG, "User enabled Bluetooth, will make sure we are discoverable.");
                ensureBluetoothDiscoverableThenStart();
                return;
            } else {
                Utils.debugLog(TAG, "User chose not to enable Bluetooth, so doing nothing");
                SwapService.putBluetoothVisibleUserPreference(false);
                return;
            }
        }
        if (i != 3) {
            if (i == 4) {
                sendFDroidApk();
            }
        } else if (i2 != 0) {
            Utils.debugLog(TAG, "User made Bluetooth discoverable, will proceed to start bluetooth server.");
            BluetoothManager.start(this);
        } else {
            Utils.debugLog(TAG, "User chose not to make Bluetooth discoverable, so doing nothing");
            SwapService.putBluetoothVisibleUserPreference(false);
        }
    }

    private void checkIfNewRepoOnSameWifi(NewRepoConfig newRepoConfig) {
        if (TextUtils.isEmpty(newRepoConfig.getBssid())) {
            return;
        }
        String bssid = ((WifiManager) ContextCompat.getSystemService(getApplicationContext(), WifiManager.class)).getConnectionInfo().getBSSID();
        if (TextUtils.isEmpty(bssid)) {
            return;
        }
        Locale locale = Locale.ENGLISH;
        if (bssid.toLowerCase(locale).equals(Uri.decode(newRepoConfig.getBssid()).toLowerCase(locale))) {
            return;
        }
        Toast.makeText(this, getString(R.string.not_on_same_wifi, newRepoConfig.getSsid()), 1).show();
    }

    public void startBluetoothSwap() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                Utils.debugLog(TAG, "Bluetooth enabled, will check if device is discoverable with device.");
                ensureBluetoothDiscoverableThenStart();
            } else {
                Utils.debugLog(TAG, "Bluetooth disabled, asking user to enable it.");
                startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 2);
            }
        }
    }

    private void ensureBluetoothDiscoverableThenStart() {
        Utils.debugLog(TAG, "Ensuring Bluetooth is in discoverable mode.");
        if (this.bluetoothAdapter.getScanMode() != 23) {
            Utils.debugLog(TAG, "Not currently in discoverable mode, so prompting user to enable.");
            Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_DISCOVERABLE");
            intent.putExtra("android.bluetooth.adapter.extra.DISCOVERABLE_DURATION", 3600);
            startActivityForResult(intent, 3);
        }
        BluetoothManager.start(this);
    }

    class SwapDebug {
        public void logStatus() {
        }

        SwapDebug() {
        }

        /* JADX INFO: renamed from: org.fdroid.fdroid.nearby.SwapWorkflowActivity$SwapDebug$1, reason: invalid class name */
        class AnonymousClass1 extends TimerTask {
            AnonymousClass1() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                SwapWorkflowActivity.this.new SwapDebug().logStatus();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpFromWifi() {
        String str = Preferences.get().isLocalRepoHttpsEnabled() ? "https://" : "http://";
        String string = str + FDroidApp.ipAddressString + ":" + FDroidApp.port;
        TextView textView = (TextView) this.container.findViewById(R.id.device_ip_address);
        if (textView != null) {
            textView.setText(string);
        }
        if (this.currentView.getLayoutResId() == R.layout.swap_join_wifi) {
            setUpJoinWifi();
            return;
        }
        if (this.currentView.getLayoutResId() != R.layout.swap_send_fdroid) {
            if (this.currentView.getLayoutResId() == R.layout.swap_wifi_qr) {
                Uri sharingUri = Utils.getSharingUri(FDroidApp.repo);
                StringBuilder sb = new StringBuilder(str);
                sb.append(sharingUri.getHost());
                if (sharingUri.getPort() != 80) {
                    sb.append(CoreConstants.COLON_CHAR);
                    sb.append(sharingUri.getPort());
                }
                sb.append(sharingUri.getPath());
                boolean z = true;
                for (String str2 : sharingUri.getQueryParameterNames()) {
                    if (!"ssid".equals(str2)) {
                        if (z) {
                            sb.append('?');
                            z = false;
                        } else {
                            sb.append('&');
                        }
                        sb.append(str2);
                        sb.append('=');
                        sb.append(sharingUri.getQueryParameter(str2));
                    }
                }
                string = sb.toString();
            } else {
                string = null;
            }
        }
        final ImageView imageView = (ImageView) this.container.findViewById(R.id.wifi_qr_code);
        if (string == null || imageView == null) {
            return;
        }
        Utils.debugLog(TAG, "Encoded swap URI in QR Code: " + string);
        this.compositeDisposable.add(Utils.generateQrBitmap(this, string).subscribe(new Consumer() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda15
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$setUpFromWifi$9(imageView, (Bitmap) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpFromWifi$9(ImageView imageView, Bitmap bitmap) throws Throwable {
        imageView.setImageBitmap(bitmap);
        imageView.setColorFilter(new LightingColorFilter(-1, ContextCompat.getColor(this, R.color.swap_blue)));
        View viewFindViewById = this.container.findViewById(R.id.warning_qr_scanner);
        if (viewFindViewById != null) {
            if (CameraCharacteristicsChecker.getInstance(this).hasAutofocus()) {
                viewFindViewById.setVisibility(8);
            } else {
                viewFindViewById.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpJoinWifi$10(View view) {
        startActivity(new Intent("android.net.wifi.PICK_WIFI_NETWORK"));
    }

    private void setUpJoinWifi() {
        this.currentView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setUpJoinWifi$10(view);
            }
        });
        TextView textView = (TextView) this.container.findViewById(R.id.text_description);
        ImageView imageView = (ImageView) this.container.findViewById(R.id.wifi_icon);
        TextView textView2 = (TextView) this.container.findViewById(R.id.wifi_ssid);
        TextView textView3 = (TextView) this.container.findViewById(R.id.wifi_available_networks_prompt);
        if (textView == null || imageView == null || textView2 == null || textView3 == null) {
            return;
        }
        if (TextUtils.isEmpty(FDroidApp.bssid) && !TextUtils.isEmpty(FDroidApp.ipAddressString)) {
            textView.setText(R.string.swap_join_this_hotspot);
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_wifi_tethering));
            textView2.setText(R.string.swap_active_hotspot);
            textView3.setText(R.string.swap_switch_to_wifi);
            return;
        }
        if (TextUtils.isEmpty(FDroidApp.ssid)) {
            textView.setText(R.string.swap_join_same_wifi);
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_wifi));
            textView2.setText(R.string.swap_no_wifi_network);
            textView3.setText(R.string.swap_view_available_networks);
            return;
        }
        textView.setText(R.string.swap_join_same_wifi);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_wifi));
        textView2.setText(FDroidApp.ssid);
        textView3.setText(R.string.swap_view_available_networks);
    }

    private void setUpStartVisibility() {
        this.bluetoothStatusReceiver.onReceive(this, new Intent(BluetoothManager.ACTION_STATUS));
        this.bonjourStatusReceiver.onReceive(this, new Intent(BonjourManager.ACTION_STATUS));
        TextView textView = (TextView) findViewById(R.id.wifi_network);
        MaterialSwitch materialSwitch = (MaterialSwitch) findViewById(R.id.switch_wifi);
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.btn_scan_qr);
        MaterialButton materialButton2 = (MaterialButton) findViewById(R.id.btn_apps);
        if (textView == null || materialSwitch == null || materialButton == null || materialButton2 == null) {
            return;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setUpStartVisibility$11(view);
            }
        });
        materialSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f$0.lambda$setUpStartVisibility$12(compoundButton, z);
            }
        });
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setUpStartVisibility$13(view);
            }
        });
        materialButton2.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setUpStartVisibility$14(view);
            }
        });
        materialButton2.setEllipsize(TextUtils.TruncateAt.END);
        if (SwapService.getWifiVisibleUserPreference()) {
            materialSwitch.setChecked(true);
        } else {
            materialSwitch.setChecked(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpStartVisibility$11(View view) {
        promptToSelectWifiNetwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpStartVisibility$12(CompoundButton compoundButton, boolean z) {
        Context applicationContext = getApplicationContext();
        if (z) {
            WifiApControl wifiApControl = this.wifiApControl;
            if (wifiApControl != null && wifiApControl.isEnabled()) {
                setupWifiAP();
            } else if (Build.VERSION.SDK_INT <= 28) {
                this.wifiManager.setWifiEnabled(true);
            }
            BonjourManager.start(applicationContext);
        }
        BonjourManager.setVisible(applicationContext, z);
        SwapService.putWifiVisibleUserPreference(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpStartVisibility$13(View view) {
        inflateSwapView(R.layout.swap_wifi_qr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpStartVisibility$14(View view) {
        inflateSwapView(R.layout.swap_select_apps);
    }

    private void setUpUseBluetoothButton() {
        Button button = (Button) findViewById(R.id.btn_use_bluetooth);
        if (button != null) {
            if (this.bluetoothAdapter == null) {
                button.setVisibility(8);
            } else {
                button.setVisibility(0);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setUpUseBluetoothButton$15(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpUseBluetoothButton$15(View view) {
        showIntro();
        sendFDroidBluetooth();
    }

    private void setUpQrScannerButton() {
        Button button = (Button) findViewById(R.id.btn_qr_scanner);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setUpQrScannerButton$16(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpQrScannerButton$16(View view) {
        initiateQrScan();
    }

    private void setUpConfirmReceive() {
        TextView textView = (TextView) findViewById(R.id.text_description);
        if (textView != null) {
            textView.setText(getString(R.string.swap_confirm_connect, this.confirmSwapConfig.getHost()));
        }
        Button button = (Button) this.container.findViewById(R.id.confirm_receive_yes);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda12
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setUpConfirmReceive$17(view);
                }
            });
        }
        Button button2 = (Button) this.container.findViewById(R.id.confirm_receive_no);
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity.10
                private final NewRepoConfig config;

                {
                    this.config = SwapWorkflowActivity.this.confirmSwapConfig;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SwapWorkflowActivity.this.swapWith(this.config);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpConfirmReceive$17(View view) {
        denySwap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpConnectingProgressText(String str) {
        TextView textView = (TextView) this.container.findViewById(R.id.progress_text);
        if (textView == null || str == null) {
            return;
        }
        textView.setVisibility(0);
        textView.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRepoUpdateSuccess() {
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) this.container.findViewById(R.id.progress_bar);
        Button button = (Button) this.container.findViewById(R.id.try_again);
        if (circularProgressIndicator != null && button != null) {
            circularProgressIndicator.show();
            button.setVisibility(8);
        }
        getSwapService().addCurrentPeerToActive();
        inflateSwapView(R.layout.swap_success);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRepoUpdateError(Exception exc) {
        String message;
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) this.container.findViewById(R.id.progress_bar);
        Button button = (Button) this.container.findViewById(R.id.try_again);
        if (circularProgressIndicator != null && button != null) {
            circularProgressIndicator.hide();
            button.setVisibility(0);
        }
        if (exc.getMessage() == null) {
            message = "Error updating repo " + exc;
        } else {
            message = exc.getMessage();
        }
        setUpConnectingProgressText(message);
        getSwapService().removeCurrentPeerFromActive();
    }

    private void setUpConnectingView() {
        ((TextView) this.container.findViewById(R.id.progress_text)).setText(R.string.swap_connecting);
        Button button = (Button) this.container.findViewById(R.id.try_again);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapWorkflowActivity$$ExternalSyntheticLambda16
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setUpConnectingView$18(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpConnectingView$18(View view) {
        onAppsSelected();
    }
}
