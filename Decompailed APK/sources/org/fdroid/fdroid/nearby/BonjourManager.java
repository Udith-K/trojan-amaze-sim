package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.util.HashMap;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: loaded from: classes2.dex */
public class BonjourManager {
    public static final String ACTION_FOUND = "BonjourNewPeer";
    public static final String ACTION_REMOVED = "BonjourPeerRemoved";
    public static final String ACTION_STATUS = "BonjourStatus";
    public static final String EXTRA_BONJOUR_PEER = "extraBonjourPeer";
    public static final String EXTRA_STATUS = "BonjourStatusExtra";
    public static final String HTTPS_SERVICE_TYPE = "_https._tcp.local.";
    public static final String HTTP_SERVICE_TYPE = "_http._tcp.local.";
    private static final int NOT_VISIBLE = 144151873;
    public static final int STATUS_ERROR = 65535;
    public static final int STATUS_NOT_VISIBLE = 5;
    public static final int STATUS_STARTED = 1;
    public static final int STATUS_STARTING = 0;
    public static final int STATUS_STOPPED = 3;
    public static final int STATUS_STOPPING = 2;
    public static final int STATUS_VISIBLE = 4;
    public static final int STATUS_VPN_CONFLICT = 6;
    private static final int STOP = 5709;
    private static final String TAG = "BonjourManager";
    private static final int VISIBLE = 4151873;
    private static final int VPN_CONFLICT = 72346752;
    private static WeakReference<Context> context;
    private static Handler handler;
    private static volatile HandlerThread handlerThread;
    private static final ServiceListener httpServiceListener;
    private static final ServiceListener httpsServiceListener;
    private static JmDNS jmdns;
    private static WifiManager.MulticastLock multicastLock;
    private static ServiceInfo pairService;

    public static boolean isAlive() {
        return handlerThread != null && handlerThread.isAlive();
    }

    public static void stop(Context context2) {
        context = new WeakReference<>(context2);
        if (handler == null || handlerThread == null || !handlerThread.isAlive()) {
            sendBroadcast(3, (String) null);
        } else {
            sendBroadcast(2, (String) null);
            handler.sendEmptyMessage(STOP);
        }
    }

    public static void setVisible(Context context2, boolean z) {
        context = new WeakReference<>(context2);
        if (handler == null || handlerThread == null || !handlerThread.isAlive()) {
            Log.e(TAG, "handlerThread is stopped, not changing visibility!");
            return;
        }
        if (isVpnActive(context2)) {
            handler.sendEmptyMessage(VPN_CONFLICT);
        } else if (z) {
            handler.sendEmptyMessage(VISIBLE);
        } else {
            handler.sendEmptyMessage(NOT_VISIBLE);
        }
    }

    public static void start(Context context2) {
        start(context2, Preferences.get().getLocalRepoName(), Preferences.get().isLocalRepoHttpsEnabled(), httpServiceListener, httpsServiceListener);
    }

    static void start(final Context context2, final String str, final boolean z, final ServiceListener serviceListener, final ServiceListener serviceListener2) {
        context = new WeakReference<>(context2);
        if (handlerThread != null && handlerThread.isAlive()) {
            sendBroadcast(1, (String) null);
            return;
        }
        sendBroadcast(0, (String) null);
        final WifiManager wifiManager = (WifiManager) ContextCompat.getSystemService(context2, WifiManager.class);
        handlerThread = new HandlerThread(TAG, 1) { // from class: org.fdroid.fdroid.nearby.BonjourManager.1
            @Override // android.os.HandlerThread
            protected void onLooperPrepared() {
                try {
                    BonjourManager.jmdns = JmDNS.create(InetAddress.getByName(FDroidApp.ipAddressString));
                    BonjourManager.jmdns.addServiceListener(BonjourManager.HTTP_SERVICE_TYPE, serviceListener);
                    BonjourManager.jmdns.addServiceListener(BonjourManager.HTTPS_SERVICE_TYPE, serviceListener2);
                    BonjourManager.multicastLock = wifiManager.createMulticastLock(context2.getPackageName());
                    BonjourManager.multicastLock.setReferenceCounted(false);
                    BonjourManager.multicastLock.acquire();
                    BonjourManager.sendBroadcast(1, (String) null);
                } catch (IOException e) {
                    if (BonjourManager.handler != null) {
                        BonjourManager.handler.removeMessages(BonjourManager.VISIBLE);
                        BonjourManager.handler.sendMessageAtFrontOfQueue(BonjourManager.handler.obtainMessage(BonjourManager.STOP));
                    }
                    Log.e(BonjourManager.TAG, "Error while registering jmdns service", e);
                    BonjourManager.sendBroadcast(65535, e.getLocalizedMessage());
                }
            }
        };
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) { // from class: org.fdroid.fdroid.nearby.BonjourManager.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == BonjourManager.STOP) {
                    handleStop();
                    return;
                }
                if (i == BonjourManager.VISIBLE) {
                    handleVisible(str, z);
                } else if (i == BonjourManager.VPN_CONFLICT) {
                    handleVpnConflict();
                } else {
                    if (i != BonjourManager.NOT_VISIBLE) {
                        return;
                    }
                    handleNotVisible();
                }
            }

            private void handleVisible(String str2, boolean z2) {
                String str3;
                if (FDroidApp.repo == null) {
                    BonjourManager.sendBroadcast(65535, context2.getString(R.string.swap_no_wifi_network));
                    return;
                }
                HashMap map = new HashMap();
                map.put(BonjourPeer.PATH, "/fdroid/repo");
                map.put("name", str2);
                map.put(BonjourPeer.FINGERPRINT, FDroidApp.repo.getFingerprint());
                if (z2) {
                    map.put(BonjourPeer.TYPE, "fdroidrepos");
                    str3 = BonjourManager.HTTPS_SERVICE_TYPE;
                } else {
                    map.put(BonjourPeer.TYPE, "fdroidrepo");
                    str3 = BonjourManager.HTTP_SERVICE_TYPE;
                }
                ServiceInfo serviceInfoCreate = ServiceInfo.create(str3, str2, FDroidApp.port, 0, 0, map);
                if (!serviceInfoCreate.equals(BonjourManager.pairService)) {
                    try {
                        if (BonjourManager.pairService != null) {
                            BonjourManager.jmdns.unregisterService(BonjourManager.pairService);
                        }
                        BonjourManager.jmdns.registerService(serviceInfoCreate);
                        BonjourManager.pairService = serviceInfoCreate;
                    } catch (IOException e) {
                        e.printStackTrace();
                        BonjourManager.sendBroadcast(65535, e.getLocalizedMessage());
                        return;
                    }
                }
                BonjourManager.sendBroadcast(4, (String) null);
            }

            private void handleNotVisible() {
                if (BonjourManager.pairService != null) {
                    BonjourManager.jmdns.unregisterService(BonjourManager.pairService);
                    BonjourManager.pairService = null;
                }
                BonjourManager.sendBroadcast(5, (String) null);
            }

            private void handleVpnConflict() {
                BonjourManager.sendBroadcast(6, (String) null);
            }

            private void handleStop() {
                if (BonjourManager.multicastLock != null) {
                    BonjourManager.multicastLock.release();
                }
                if (BonjourManager.jmdns != null) {
                    BonjourManager.jmdns.unregisterAllServices();
                    Utils.closeQuietly(BonjourManager.jmdns);
                    BonjourManager.pairService = null;
                    BonjourManager.jmdns = null;
                }
                BonjourManager.handlerThread.quit();
                BonjourManager.handlerThread = null;
                BonjourManager.sendBroadcast(3, (String) null);
            }
        };
    }

    public static void restart(Context context2) {
        restart(context2, Preferences.get().getLocalRepoName(), Preferences.get().isLocalRepoHttpsEnabled(), httpServiceListener, httpsServiceListener);
    }

    static void restart(Context context2, String str, boolean z, ServiceListener serviceListener, ServiceListener serviceListener2) {
        stop(context2);
        try {
            handlerThread.join(AbstractComponentTracker.LINGERING_TIMEOUT);
        } catch (InterruptedException | NullPointerException unused) {
        }
        start(context2, str, z, serviceListener, serviceListener2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendBroadcast(String str, ServiceInfo serviceInfo) {
        BonjourPeer bonjourPeer = BonjourPeer.getInstance(serviceInfo);
        if (bonjourPeer == null) {
            Utils.debugLog(TAG, "IGNORING: " + serviceInfo);
            return;
        }
        Intent intent = new Intent(str);
        intent.putExtra(EXTRA_BONJOUR_PEER, bonjourPeer);
        LocalBroadcastManager.getInstance(context.get()).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendBroadcast(int i, String str) {
        Intent intent = new Intent(ACTION_STATUS);
        intent.putExtra(EXTRA_STATUS, i);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("android.intent.extra.TEXT", str);
        }
        LocalBroadcastManager.getInstance(context.get()).sendBroadcast(intent);
    }

    static {
        httpServiceListener = new SwapServiceListener();
        httpsServiceListener = new SwapServiceListener();
    }

    private static class SwapServiceListener implements ServiceListener {
        @Override // javax.jmdns.ServiceListener
        public void serviceAdded(ServiceEvent serviceEvent) {
        }

        private SwapServiceListener() {
        }

        @Override // javax.jmdns.ServiceListener
        public void serviceRemoved(ServiceEvent serviceEvent) {
            BonjourManager.sendBroadcast(BonjourManager.ACTION_REMOVED, serviceEvent.getInfo());
        }

        @Override // javax.jmdns.ServiceListener
        public void serviceResolved(ServiceEvent serviceEvent) {
            BonjourManager.sendBroadcast(BonjourManager.ACTION_FOUND, serviceEvent.getInfo());
        }
    }

    public static boolean isVpnActive(Context context2) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null) {
            return false;
        }
        return networkCapabilities.hasTransport(4);
    }
}
