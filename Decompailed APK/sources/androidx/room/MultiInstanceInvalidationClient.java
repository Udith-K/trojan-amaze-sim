package androidx.room;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.IMultiInstanceInvalidationService;
import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiInstanceInvalidationClient.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MultiInstanceInvalidationClient {
    private final Context appContext;
    private final IMultiInstanceInvalidationCallback callback;
    private int clientId;
    private final Executor executor;
    private final InvalidationTracker invalidationTracker;
    private final String name;
    public InvalidationTracker.Observer observer;
    private final Runnable removeObserverRunnable;
    private IMultiInstanceInvalidationService service;
    private final ServiceConnection serviceConnection;
    private final Runnable setUpRunnable;
    private final AtomicBoolean stopped;

    public MultiInstanceInvalidationClient(Context context, String name, Intent serviceIntent, InvalidationTracker invalidationTracker, Executor executor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(serviceIntent, "serviceIntent");
        Intrinsics.checkNotNullParameter(invalidationTracker, "invalidationTracker");
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.name = name;
        this.invalidationTracker = invalidationTracker;
        this.executor = executor;
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        this.callback = new MultiInstanceInvalidationClient$callback$1(this);
        this.stopped = new AtomicBoolean(false);
        ServiceConnection serviceConnection = new ServiceConnection() { // from class: androidx.room.MultiInstanceInvalidationClient$serviceConnection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name2, IBinder service) {
                Intrinsics.checkNotNullParameter(name2, "name");
                Intrinsics.checkNotNullParameter(service, "service");
                this.this$0.setService(IMultiInstanceInvalidationService.Stub.asInterface(service));
                this.this$0.getExecutor().execute(this.this$0.getSetUpRunnable());
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name2) {
                Intrinsics.checkNotNullParameter(name2, "name");
                this.this$0.getExecutor().execute(this.this$0.getRemoveObserverRunnable());
                this.this$0.setService(null);
            }
        };
        this.serviceConnection = serviceConnection;
        this.setUpRunnable = new Runnable() { // from class: androidx.room.MultiInstanceInvalidationClient$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MultiInstanceInvalidationClient.setUpRunnable$lambda$1(this.f$0);
            }
        };
        this.removeObserverRunnable = new Runnable() { // from class: androidx.room.MultiInstanceInvalidationClient$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MultiInstanceInvalidationClient.removeObserverRunnable$lambda$2(this.f$0);
            }
        };
        setObserver(new InvalidationTracker.Observer((String[]) invalidationTracker.getTableIdLookup$room_runtime_release().keySet().toArray(new String[0])) { // from class: androidx.room.MultiInstanceInvalidationClient.1
            @Override // androidx.room.InvalidationTracker.Observer
            public boolean isRemote$room_runtime_release() {
                return true;
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(Set tables) {
                Intrinsics.checkNotNullParameter(tables, "tables");
                if (MultiInstanceInvalidationClient.this.getStopped().get()) {
                    return;
                }
                try {
                    IMultiInstanceInvalidationService service = MultiInstanceInvalidationClient.this.getService();
                    if (service != null) {
                        service.broadcastInvalidation(MultiInstanceInvalidationClient.this.getClientId(), (String[]) tables.toArray(new String[0]));
                    }
                } catch (RemoteException e) {
                    Log.w("ROOM", "Cannot broadcast invalidation", e);
                }
            }
        });
        applicationContext.bindService(serviceIntent, serviceConnection, 1);
    }

    public final InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final int getClientId() {
        return this.clientId;
    }

    public final InvalidationTracker.Observer getObserver() {
        InvalidationTracker.Observer observer = this.observer;
        if (observer != null) {
            return observer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("observer");
        return null;
    }

    public final void setObserver(InvalidationTracker.Observer observer) {
        Intrinsics.checkNotNullParameter(observer, "<set-?>");
        this.observer = observer;
    }

    public final IMultiInstanceInvalidationService getService() {
        return this.service;
    }

    public final void setService(IMultiInstanceInvalidationService iMultiInstanceInvalidationService) {
        this.service = iMultiInstanceInvalidationService;
    }

    public final AtomicBoolean getStopped() {
        return this.stopped;
    }

    public final Runnable getSetUpRunnable() {
        return this.setUpRunnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUpRunnable$lambda$1(MultiInstanceInvalidationClient this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            IMultiInstanceInvalidationService iMultiInstanceInvalidationService = this$0.service;
            if (iMultiInstanceInvalidationService != null) {
                this$0.clientId = iMultiInstanceInvalidationService.registerCallback(this$0.callback, this$0.name);
                this$0.invalidationTracker.addObserver(this$0.getObserver());
            }
        } catch (RemoteException e) {
            Log.w("ROOM", "Cannot register multi-instance invalidation callback", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeObserverRunnable$lambda$2(MultiInstanceInvalidationClient this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.invalidationTracker.removeObserver(this$0.getObserver());
    }

    public final Runnable getRemoveObserverRunnable() {
        return this.removeObserverRunnable;
    }

    public final void stop() {
        if (this.stopped.compareAndSet(false, true)) {
            this.invalidationTracker.removeObserver(getObserver());
            try {
                IMultiInstanceInvalidationService iMultiInstanceInvalidationService = this.service;
                if (iMultiInstanceInvalidationService != null) {
                    iMultiInstanceInvalidationService.unregisterCallback(this.callback, this.clientId);
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot unregister multi-instance invalidation callback", e);
            }
            this.appContext.unbindService(this.serviceConnection);
        }
    }
}
