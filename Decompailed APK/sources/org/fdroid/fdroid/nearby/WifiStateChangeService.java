package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import cc.mvdan.accesspoint.WifiApControl;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.net.util.SubnetUtils;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Hasher;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.LocalRepoKeyStore;

/* JADX INFO: loaded from: classes2.dex */
public class WifiStateChangeService extends Worker {
    public static final String BROADCAST = "org.fdroid.fdroid.action.WIFI_CHANGE";
    public static final String EXTRA_STATUS = "wifiStateChangeStatus";
    private static final int NETWORK_INFO_STATE_NOT_SET = -1;
    private static final String TAG = "WifiStateChangeService";
    private static int previousWifiState = Integer.MIN_VALUE;
    private static WifiInfoThread wifiInfoThread;
    private static volatile int wifiState;
    private WifiManager wifiManager;

    public WifiStateChangeService(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    public static void registerReceiver(Context context) {
        ContextCompat.registerReceiver(context, new WifiStateChangeReceiver(), new IntentFilter("android.net.wifi.STATE_CHANGE"), 4);
    }

    public static void start(Context context, Intent intent) {
        WorkManager.getInstance(context).enqueue(((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(WifiStateChangeService.class).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())).setInputData(new Data.Builder().putInt("networkInfo", intent != null ? ((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState().ordinal() : -1).build())).build());
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        Process.setThreadPriority(19);
        int i = getInputData().getInt("networkInfo", -1);
        NetworkInfo.State state = i != -1 ? NetworkInfo.State.values()[i] : null;
        Utils.debugLog(TAG, "WiFi change service started.");
        WifiManager wifiManager = (WifiManager) ContextCompat.getSystemService(getApplicationContext(), WifiManager.class);
        this.wifiManager = wifiManager;
        if (wifiManager == null) {
            return ListenableWorker.Result.failure();
        }
        wifiState = wifiManager.getWifiState();
        Utils.debugLog(TAG, "networkInfoStateInt == " + i + "  wifiState == " + printWifiState(wifiState));
        if ((state == null || state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.DISCONNECTED) && previousWifiState != wifiState && (wifiState == 3 || wifiState == 0 || wifiState == 1 || wifiState == 4)) {
            WifiInfoThread wifiInfoThread2 = wifiInfoThread;
            if (wifiInfoThread2 != null) {
                wifiInfoThread2.interrupt();
            }
            WifiInfoThread wifiInfoThread3 = new WifiInfoThread();
            wifiInfoThread = wifiInfoThread3;
            wifiInfoThread3.start();
        }
        return ListenableWorker.Result.success();
    }

    public class WifiInfoThread extends Thread {
        public WifiInfoThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            String lowerCase;
            WifiInfo connectionInfo;
            String str;
            Process.setThreadPriority(19);
            try {
                FDroidApp.initWifiSettings();
                Utils.debugLog(WifiStateChangeService.TAG, "Checking wifi state (in background thread).");
                int wifiState = WifiStateChangeService.this.wifiManager.getWifiState();
                lowerCase = null;
                int i = 0;
                connectionInfo = null;
                while (FDroidApp.ipAddressString == null) {
                    if (isInterrupted()) {
                        return;
                    }
                    if (wifiState == 3) {
                        connectionInfo = WifiStateChangeService.this.wifiManager.getConnectionInfo();
                        FDroidApp.ipAddressString = WifiStateChangeService.formatIpAddress(connectionInfo.getIpAddress());
                        WifiStateChangeService.this.setSsid(connectionInfo);
                        DhcpInfo dhcpInfo = WifiStateChangeService.this.wifiManager.getDhcpInfo();
                        if (dhcpInfo != null) {
                            String ipAddress = WifiStateChangeService.formatIpAddress(dhcpInfo.netmask);
                            if (!TextUtils.isEmpty(FDroidApp.ipAddressString) && ipAddress != null) {
                                try {
                                    FDroidApp.subnetInfo = new SubnetUtils(FDroidApp.ipAddressString, ipAddress).getInfo();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (FDroidApp.ipAddressString == null || FDroidApp.subnetInfo == FDroidApp.UNSET_SUBNET_INFO) {
                            WifiStateChangeService.this.setIpInfoFromNetworkInterface();
                        }
                    } else if (wifiState == 1 || wifiState == 0 || wifiState == 4) {
                        WifiStateChangeService.this.setIpInfoFromNetworkInterface();
                        if (FDroidApp.ipAddressString == null) {
                            return;
                        }
                    }
                    if (i > 120) {
                        return;
                    }
                    i++;
                    if (FDroidApp.ipAddressString == null) {
                        Thread.sleep(1000L);
                        Utils.debugLog(WifiStateChangeService.TAG, "waiting for an IP address...");
                    }
                }
            } catch (InterruptedException unused) {
                Utils.debugLog(WifiStateChangeService.TAG, "interrupted");
                return;
            } catch (LocalRepoKeyStore.InitException e2) {
                Log.e(WifiStateChangeService.TAG, "Unable to configure a fingerprint or HTTPS for the local repo", e2);
            }
            if (isInterrupted()) {
                return;
            }
            WifiStateChangeService.this.setSsid(connectionInfo);
            if (Preferences.get().isLocalRepoHttpsEnabled()) {
                str = "https";
            } else {
                str = "http";
            }
            Context applicationContext = WifiStateChangeService.this.getApplicationContext();
            String str2 = String.format(Locale.ENGLISH, "%s://%s:%d/fdroid/repo", str, FDroidApp.ipAddressString, Integer.valueOf(FDroidApp.port));
            LocalRepoKeyStore localRepoKeyStore = LocalRepoKeyStore.get(applicationContext);
            Certificate certificate = localRepoKeyStore.getCertificate();
            if (certificate != null) {
                lowerCase = Hasher.hex(certificate).toLowerCase(Locale.US);
            }
            Repository repositoryCreateSwapRepo = FDroidApp.createSwapRepo(str2, lowerCase);
            if (isInterrupted()) {
                return;
            }
            LocalRepoManager.get(applicationContext).writeIndexPage(Utils.getSharingUri(FDroidApp.repo).toString());
            if (isInterrupted()) {
                return;
            }
            FDroidApp.repo = repositoryCreateSwapRepo;
            if (Preferences.get().isLocalRepoHttpsEnabled()) {
                localRepoKeyStore.setupHTTPSCertificate();
            }
            Intent intent = new Intent(WifiStateChangeService.BROADCAST);
            intent.putExtra(WifiStateChangeService.EXTRA_STATUS, WifiStateChangeService.wifiState);
            LocalBroadcastManager.getInstance(WifiStateChangeService.this.getApplicationContext()).sendBroadcast(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSsid(WifiInfo wifiInfo) {
        Context applicationContext = getApplicationContext();
        if (wifiInfo != null && wifiInfo.getBSSID() != null) {
            String ssid = wifiInfo.getSSID();
            Utils.debugLog(TAG, "Have wifi info, connected to " + ssid);
            if (ssid == null) {
                FDroidApp.ssid = applicationContext.getString(R.string.swap_blank_wifi_ssid);
            } else {
                FDroidApp.ssid = ssid.replaceAll("^\"(.*)\"$", "$1");
            }
            FDroidApp.bssid = wifiInfo.getBSSID();
            return;
        }
        WifiApControl wifiApControl = WifiApControl.getInstance(applicationContext);
        Utils.debugLog(TAG, "WifiApControl: " + wifiApControl);
        if (wifiApControl == null && FDroidApp.ipAddressString != null) {
            WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
            if (connectionInfo == null || connectionInfo.getBSSID() == null) {
                FDroidApp.ssid = applicationContext.getString(R.string.swap_active_hotspot, "");
                return;
            } else {
                setSsid(connectionInfo);
                return;
            }
        }
        if (wifiApControl == null || !wifiApControl.isEnabled()) {
            return;
        }
        WifiConfiguration configuration = wifiApControl.getConfiguration();
        Utils.debugLog(TAG, "WifiConfiguration: " + configuration);
        if (configuration == null) {
            FDroidApp.ssid = applicationContext.getString(R.string.swap_active_hotspot, "");
            FDroidApp.bssid = "";
        } else {
            if (configuration.hiddenSSID) {
                FDroidApp.ssid = applicationContext.getString(R.string.swap_hidden_wifi_ssid);
            } else {
                FDroidApp.ssid = configuration.SSID;
            }
            FDroidApp.bssid = configuration.BSSID;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIpInfoFromNetworkInterface() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return;
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && !(inetAddressNextElement instanceof Inet6Address) && (networkInterfaceNextElement.getDisplayName().contains("wlan0") || networkInterfaceNextElement.getDisplayName().contains("eth0") || networkInterfaceNextElement.getDisplayName().contains("ap0"))) {
                        FDroidApp.ipAddressString = inetAddressNextElement.getHostAddress();
                        Iterator<InterfaceAddress> it = networkInterfaceNextElement.getInterfaceAddresses().iterator();
                        while (it.hasNext()) {
                            short networkPrefixLength = it.next().getNetworkPrefixLength();
                            if (networkPrefixLength <= 32) {
                                try {
                                    FDroidApp.subnetInfo = new SubnetUtils(String.format(Locale.ENGLISH, "%s/%d", FDroidApp.ipAddressString, Short.valueOf(networkPrefixLength))).getInfo();
                                    break;
                                } catch (IllegalArgumentException e) {
                                    Log.i(TAG, "Getting subnet failed: " + e.getLocalizedMessage());
                                }
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException e2) {
            e = e2;
            Log.e(TAG, "Could not get ip address", e);
        } catch (SocketException e3) {
            e = e3;
            Log.e(TAG, "Could not get ip address", e);
        }
    }

    static String formatIpAddress(int i) {
        if (i == 0) {
            return null;
        }
        return String.format(Locale.ENGLISH, "%d.%d.%d.%d", Integer.valueOf(i & GF2Field.MASK), Integer.valueOf((i >> 8) & GF2Field.MASK), Integer.valueOf((i >> 16) & GF2Field.MASK), Integer.valueOf((i >> 24) & GF2Field.MASK));
    }

    private String printWifiState(int i) {
        if (i == Integer.MIN_VALUE) {
            return "previous value unset";
        }
        if (i == 0) {
            return "WIFI_STATE_DISABLING";
        }
        if (i == 1) {
            return "WIFI_STATE_DISABLED";
        }
        if (i == 2) {
            return "WIFI_STATE_ENABLING";
        }
        if (i == 3) {
            return "WIFI_STATE_ENABLED";
        }
        if (i == 4) {
            return "WIFI_STATE_UNKNOWN";
        }
        return "~not mapped~";
    }
}
