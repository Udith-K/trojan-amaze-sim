package io.reactivex.rxjava3.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidSchedulers {
    private static final Scheduler MAIN_THREAD = RxAndroidPlugins.initMainThreadScheduler(new Callable() { // from class: io.reactivex.rxjava3.android.schedulers.AndroidSchedulers$$ExternalSyntheticLambda0
        @Override // java.util.concurrent.Callable
        public final Object call() {
            return AndroidSchedulers.MainHolder.DEFAULT;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    static final class MainHolder {
        static final Scheduler DEFAULT = AndroidSchedulers.internalFrom(Looper.getMainLooper(), true);
    }

    public static Scheduler mainThread() {
        return RxAndroidPlugins.onMainThreadScheduler(MAIN_THREAD);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Scheduler internalFrom(Looper looper, boolean z) {
        return new HandlerScheduler(new Handler(looper), z);
    }
}
