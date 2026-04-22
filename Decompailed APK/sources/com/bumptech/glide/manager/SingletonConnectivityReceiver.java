package com.bumptech.glide.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.util.GlideSuppliers;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class SingletonConnectivityReceiver {
    private static volatile SingletonConnectivityReceiver instance;
    private final FrameworkConnectivityMonitor frameworkConnectivityMonitor;
    private boolean isRegistered;
    final Set listeners = new HashSet();

    private interface FrameworkConnectivityMonitor {
        boolean register();

        void unregister();
    }

    static SingletonConnectivityReceiver get(Context context) {
        if (instance == null) {
            synchronized (SingletonConnectivityReceiver.class) {
                try {
                    if (instance == null) {
                        instance = new SingletonConnectivityReceiver(context.getApplicationContext());
                    }
                } finally {
                }
            }
        }
        return instance;
    }

    private SingletonConnectivityReceiver(final Context context) {
        FrameworkConnectivityMonitor frameworkConnectivityMonitorPreApi24;
        GlideSuppliers.GlideSupplier glideSupplierMemorize = GlideSuppliers.memorize(new GlideSuppliers.GlideSupplier() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.1
            @Override // com.bumptech.glide.util.GlideSuppliers.GlideSupplier
            public ConnectivityManager get() {
                return (ConnectivityManager) context.getSystemService("connectivity");
            }
        });
        ConnectivityMonitor.ConnectivityListener connectivityListener = new ConnectivityMonitor.ConnectivityListener() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.2
            @Override // com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener
            public void onConnectivityChanged(boolean z) {
                ArrayList arrayList;
                Util.assertMainThread();
                synchronized (SingletonConnectivityReceiver.this) {
                    arrayList = new ArrayList(SingletonConnectivityReceiver.this.listeners);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((ConnectivityMonitor.ConnectivityListener) it.next()).onConnectivityChanged(z);
                }
            }
        };
        if (Build.VERSION.SDK_INT >= 24) {
            frameworkConnectivityMonitorPreApi24 = new FrameworkConnectivityMonitorPostApi24(glideSupplierMemorize, connectivityListener);
        } else {
            frameworkConnectivityMonitorPreApi24 = new FrameworkConnectivityMonitorPreApi24(context, glideSupplierMemorize, connectivityListener);
        }
        this.frameworkConnectivityMonitor = frameworkConnectivityMonitorPreApi24;
    }

    synchronized void register(ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.listeners.add(connectivityListener);
        maybeRegisterReceiver();
    }

    synchronized void unregister(ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.listeners.remove(connectivityListener);
        maybeUnregisterReceiver();
    }

    private void maybeRegisterReceiver() {
        if (this.isRegistered || this.listeners.isEmpty()) {
            return;
        }
        this.isRegistered = this.frameworkConnectivityMonitor.register();
    }

    private void maybeUnregisterReceiver() {
        if (this.isRegistered && this.listeners.isEmpty()) {
            this.frameworkConnectivityMonitor.unregister();
            this.isRegistered = false;
        }
    }

    private static final class FrameworkConnectivityMonitorPostApi24 implements FrameworkConnectivityMonitor {
        private final GlideSuppliers.GlideSupplier connectivityManager;
        boolean isConnected;
        final ConnectivityMonitor.ConnectivityListener listener;
        private final ConnectivityManager.NetworkCallback networkCallback = new AnonymousClass1();

        /* JADX INFO: renamed from: com.bumptech.glide.manager.SingletonConnectivityReceiver$FrameworkConnectivityMonitorPostApi24$1, reason: invalid class name */
        class AnonymousClass1 extends ConnectivityManager.NetworkCallback {
            AnonymousClass1() {
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                postOnConnectivityChange(true);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                postOnConnectivityChange(false);
            }

            private void postOnConnectivityChange(final boolean z) {
                Util.postOnUiThread(new Runnable() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitorPostApi24.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1.this.onConnectivityChange(z);
                    }
                });
            }

            void onConnectivityChange(boolean z) {
                Util.assertMainThread();
                FrameworkConnectivityMonitorPostApi24 frameworkConnectivityMonitorPostApi24 = FrameworkConnectivityMonitorPostApi24.this;
                boolean z2 = frameworkConnectivityMonitorPostApi24.isConnected;
                frameworkConnectivityMonitorPostApi24.isConnected = z;
                if (z2 != z) {
                    frameworkConnectivityMonitorPostApi24.listener.onConnectivityChanged(z);
                }
            }
        }

        FrameworkConnectivityMonitorPostApi24(GlideSuppliers.GlideSupplier glideSupplier, ConnectivityMonitor.ConnectivityListener connectivityListener) {
            this.connectivityManager = glideSupplier;
            this.listener = connectivityListener;
        }

        @Override // com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitor
        public boolean register() {
            this.isConnected = ((ConnectivityManager) this.connectivityManager.get()).getActiveNetwork() != null;
            try {
                ((ConnectivityManager) this.connectivityManager.get()).registerDefaultNetworkCallback(this.networkCallback);
                return true;
            } catch (RuntimeException e) {
                if (Log.isLoggable("ConnectivityMonitor", 5)) {
                    Log.w("ConnectivityMonitor", "Failed to register callback", e);
                }
                return false;
            }
        }

        @Override // com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitor
        public void unregister() {
            ((ConnectivityManager) this.connectivityManager.get()).unregisterNetworkCallback(this.networkCallback);
        }
    }

    private static final class FrameworkConnectivityMonitorPreApi24 implements FrameworkConnectivityMonitor {
        static final Executor EXECUTOR = AsyncTask.SERIAL_EXECUTOR;
        private final GlideSuppliers.GlideSupplier connectivityManager;
        final BroadcastReceiver connectivityReceiver = new BroadcastReceiver() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitorPreApi24.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                FrameworkConnectivityMonitorPreApi24.this.onConnectivityChange();
            }
        };
        final Context context;
        volatile boolean isConnected;
        volatile boolean isRegistered;
        final ConnectivityMonitor.ConnectivityListener listener;

        FrameworkConnectivityMonitorPreApi24(Context context, GlideSuppliers.GlideSupplier glideSupplier, ConnectivityMonitor.ConnectivityListener connectivityListener) {
            this.context = context.getApplicationContext();
            this.connectivityManager = glideSupplier;
            this.listener = connectivityListener;
        }

        @Override // com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitor
        public boolean register() {
            EXECUTOR.execute(new Runnable() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitorPreApi24.2
                @Override // java.lang.Runnable
                public void run() {
                    FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi24 = FrameworkConnectivityMonitorPreApi24.this;
                    frameworkConnectivityMonitorPreApi24.isConnected = frameworkConnectivityMonitorPreApi24.isConnected();
                    try {
                        FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi242 = FrameworkConnectivityMonitorPreApi24.this;
                        frameworkConnectivityMonitorPreApi242.context.registerReceiver(frameworkConnectivityMonitorPreApi242.connectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                        FrameworkConnectivityMonitorPreApi24.this.isRegistered = true;
                    } catch (SecurityException e) {
                        if (Log.isLoggable("ConnectivityMonitor", 5)) {
                            Log.w("ConnectivityMonitor", "Failed to register", e);
                        }
                        FrameworkConnectivityMonitorPreApi24.this.isRegistered = false;
                    }
                }
            });
            return true;
        }

        @Override // com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitor
        public void unregister() {
            EXECUTOR.execute(new Runnable() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitorPreApi24.3
                @Override // java.lang.Runnable
                public void run() {
                    if (FrameworkConnectivityMonitorPreApi24.this.isRegistered) {
                        FrameworkConnectivityMonitorPreApi24.this.isRegistered = false;
                        FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi24 = FrameworkConnectivityMonitorPreApi24.this;
                        frameworkConnectivityMonitorPreApi24.context.unregisterReceiver(frameworkConnectivityMonitorPreApi24.connectivityReceiver);
                    }
                }
            });
        }

        void onConnectivityChange() {
            EXECUTOR.execute(new Runnable() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitorPreApi24.4
                @Override // java.lang.Runnable
                public void run() {
                    boolean z = FrameworkConnectivityMonitorPreApi24.this.isConnected;
                    FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi24 = FrameworkConnectivityMonitorPreApi24.this;
                    frameworkConnectivityMonitorPreApi24.isConnected = frameworkConnectivityMonitorPreApi24.isConnected();
                    if (z != FrameworkConnectivityMonitorPreApi24.this.isConnected) {
                        if (Log.isLoggable("ConnectivityMonitor", 3)) {
                            Log.d("ConnectivityMonitor", "connectivity changed, isConnected: " + FrameworkConnectivityMonitorPreApi24.this.isConnected);
                        }
                        FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi242 = FrameworkConnectivityMonitorPreApi24.this;
                        frameworkConnectivityMonitorPreApi242.notifyChangeOnUiThread(frameworkConnectivityMonitorPreApi242.isConnected);
                    }
                }
            });
        }

        void notifyChangeOnUiThread(final boolean z) {
            Util.postOnUiThread(new Runnable() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitorPreApi24.5
                @Override // java.lang.Runnable
                public void run() {
                    FrameworkConnectivityMonitorPreApi24.this.listener.onConnectivityChanged(z);
                }
            });
        }

        boolean isConnected() {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.connectivityManager.get()).getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            } catch (RuntimeException e) {
                if (Log.isLoggable("ConnectivityMonitor", 5)) {
                    Log.w("ConnectivityMonitor", "Failed to determine connectivity status when connectivity changed", e);
                }
                return true;
            }
        }
    }
}
