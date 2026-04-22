package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import java.io.IOException;
import java.net.BindException;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: loaded from: classes2.dex */
public class LocalHTTPDManager {
    public static final String ACTION_ERROR = "LocalHTTPDError";
    public static final String ACTION_STARTED = "LocalHTTPDStarted";
    public static final String ACTION_STOPPED = "LocalHTTPDStopped";
    private static final int STOP = 5709;
    private static final String TAG = "LocalHTTPDManager";
    private static Handler handler;
    private static volatile HandlerThread handlerThread;
    private static LocalHTTPD localHttpd;

    public static void start(Context context) {
        start(context, Preferences.get().isLocalRepoHttpsEnabled());
    }

    static void start(final Context context, final boolean z) {
        if (handlerThread != null && handlerThread.isAlive()) {
            Log.w(TAG, "handlerThread is already running, doing nothing!");
            return;
        }
        handlerThread = new HandlerThread("LocalHTTPD", 1) { // from class: org.fdroid.fdroid.nearby.LocalHTTPDManager.1
            @Override // android.os.HandlerThread
            protected void onLooperPrepared() {
                LocalHTTPDManager.localHttpd = new LocalHTTPD(context, FDroidApp.ipAddressString, FDroidApp.port, context.getFilesDir(), z);
                try {
                    LocalHTTPDManager.localHttpd.start();
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LocalHTTPDManager.ACTION_STARTED));
                } catch (BindException e) {
                    FDroidApp.generateNewPort = true;
                    WifiStateChangeService.start(context, null);
                    Intent intent = new Intent(LocalHTTPDManager.ACTION_ERROR);
                    intent.putExtra("android.intent.extra.TEXT", "port " + FDroidApp.port + " occupied, trying new port: (" + e.getLocalizedMessage() + ")");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                } catch (IOException e2) {
                    e2.printStackTrace();
                    Intent intent2 = new Intent(LocalHTTPDManager.ACTION_ERROR);
                    intent2.putExtra("android.intent.extra.TEXT", e2.getLocalizedMessage());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);
                }
            }
        };
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) { // from class: org.fdroid.fdroid.nearby.LocalHTTPDManager.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                LocalHTTPDManager.localHttpd.stop();
                LocalHTTPDManager.handlerThread.quit();
                LocalHTTPDManager.handlerThread = null;
            }
        };
    }

    public static void stop(Context context) {
        if (handler == null || handlerThread == null || !handlerThread.isAlive()) {
            Log.w(TAG, "handlerThread is already stopped, doing nothing!");
            handlerThread = null;
        } else {
            handler.sendEmptyMessage(STOP);
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_STOPPED));
        }
    }

    public static void restart(Context context) {
        restart(context, Preferences.get().isLocalRepoHttpsEnabled());
    }

    static void restart(Context context, boolean z) {
        stop(context);
        try {
            handlerThread.join(AbstractComponentTracker.LINGERING_TIMEOUT);
        } catch (InterruptedException | NullPointerException unused) {
        }
        start(context, z);
    }

    public static boolean isAlive() {
        return handlerThread != null && handlerThread.isAlive();
    }
}
