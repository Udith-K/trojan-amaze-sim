package io.reactivex.rxjava3.disposables;

import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.functions.Functions;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface Disposable {
    void dispose();

    boolean isDisposed();

    /* JADX INFO: renamed from: io.reactivex.rxjava3.disposables.Disposable$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static Disposable fromRunnable(Runnable runnable) {
            Objects.requireNonNull(runnable, "run is null");
            return new RunnableDisposable(runnable);
        }

        public static Disposable empty() {
            return fromRunnable(Functions.EMPTY_RUNNABLE);
        }

        public static Disposable disposed() {
            return EmptyDisposable.INSTANCE;
        }
    }
}
