package org.fdroid.fdroid.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import androidx.core.app.JobIntentService;
import androidx.core.content.ContextCompat;
import androidx.core.net.ConnectivityManagerCompat;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import org.fdroid.fdroid.FDroidApp;

/* JADX INFO: loaded from: classes2.dex */
public class ConnectivityMonitorService extends JobIntentService {
    private static final String ACTION_START = "org.fdroid.fdroid.net.action.CONNECTIVITY_MONITOR";
    private static final BroadcastReceiver CONNECTIVITY_RECEIVER = new BroadcastReceiver() { // from class: org.fdroid.fdroid.net.ConnectivityMonitorService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityMonitorService.start(context);
        }
    };
    public static final int FLAG_NET_DEVICE_AP_WITHOUT_INTERNET = 3;
    public static final int FLAG_NET_METERED = 1;
    public static final int FLAG_NET_NO_LIMIT = 2;
    public static final int FLAG_NET_UNAVAILABLE = 0;
    public static final String TAG = "ConnectivityMonitorServ";

    public static void registerAndStart(Context context) {
        context.registerReceiver(CONNECTIVITY_RECEIVER, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, (Class<?>) ConnectivityMonitorService.class);
        intent.setAction(ACTION_START);
        JobIntentService.enqueueWork(context, (Class<?>) ConnectivityMonitorService.class, 159559291, intent);
    }

    public static int getNetworkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextCompat.getSystemService(context, ConnectivityManager.class);
        if (connectivityManager == null) {
            return 0;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null && connectivityManager.getAllNetworks().length == 0) {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                    if (networkInterfaceNextElement.getDisplayName().contains("wlan0") || networkInterfaceNextElement.getDisplayName().contains("eth0") || networkInterfaceNextElement.getDisplayName().contains("ap0")) {
                        Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddressNextElement = inetAddresses.nextElement();
                            if (!inetAddressNextElement.isLoopbackAddress() && !(inetAddressNextElement instanceof Inet6Address)) {
                                Log.i(TAG, "FLAG_NET_DEVICE_AP_WITHOUT_INTERNET: " + networkInterfaceNextElement.getDisplayName() + " " + inetAddressNextElement);
                                return 3;
                            }
                        }
                    }
                }
            } catch (NullPointerException | SocketException unused) {
            }
        }
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        return ((type == 1 || type == 9) && !ConnectivityManagerCompat.isActiveNetworkMetered(connectivityManager)) ? 2 : 1;
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        if (ACTION_START.equals(intent.getAction())) {
            FDroidApp.networkState = getNetworkState(this);
        }
    }
}
