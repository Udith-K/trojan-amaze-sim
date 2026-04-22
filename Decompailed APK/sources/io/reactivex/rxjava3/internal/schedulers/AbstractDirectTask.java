package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.functions.Functions;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
abstract class AbstractDirectTask extends AtomicReference implements Disposable {
    protected static final FutureTask DISPOSED;
    protected static final FutureTask FINISHED;
    protected final boolean interruptOnCancel;
    protected final Runnable runnable;
    protected Thread runner;

    static {
        Runnable runnable = Functions.EMPTY_RUNNABLE;
        FINISHED = new FutureTask(runnable, null);
        DISPOSED = new FutureTask(runnable, null);
    }

    AbstractDirectTask(Runnable runnable, boolean z) {
        this.runnable = runnable;
        this.interruptOnCancel = z;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        FutureTask futureTask;
        Future future = (Future) get();
        if (future == FINISHED || future == (futureTask = DISPOSED) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        cancelFuture(future);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        Future future = (Future) get();
        return future == FINISHED || future == DISPOSED;
    }

    public final void setFuture(Future future) {
        Future future2;
        do {
            future2 = (Future) get();
            if (future2 == FINISHED) {
                return;
            }
            if (future2 == DISPOSED) {
                cancelFuture(future);
                return;
            }
        } while (!compareAndSet(future2, future));
    }

    private void cancelFuture(Future future) {
        if (this.runner == Thread.currentThread()) {
            future.cancel(false);
        } else {
            future.cancel(this.interruptOnCancel);
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        String str;
        Future future = (Future) get();
        if (future == FINISHED) {
            str = "Finished";
        } else if (future == DISPOSED) {
            str = "Disposed";
        } else if (this.runner != null) {
            str = "Running on " + this.runner;
        } else {
            str = "Waiting";
        }
        return getClass().getSimpleName() + "[" + str + "]";
    }
}
