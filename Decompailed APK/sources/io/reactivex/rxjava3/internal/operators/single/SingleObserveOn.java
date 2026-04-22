package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class SingleObserveOn extends Single {
    final Scheduler scheduler;
    final SingleSource source;

    public SingleObserveOn(SingleSource singleSource, Scheduler scheduler) {
        this.source = singleSource;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Single
    protected void subscribeActual(SingleObserver singleObserver) {
        this.source.subscribe(new ObserveOnSingleObserver(singleObserver, this.scheduler));
    }

    static final class ObserveOnSingleObserver extends AtomicReference implements SingleObserver, Disposable, Runnable {
        final SingleObserver downstream;
        Throwable error;
        final Scheduler scheduler;
        Object value;

        ObserveOnSingleObserver(SingleObserver singleObserver, Scheduler scheduler) {
            this.downstream = singleObserver;
            this.scheduler = scheduler;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(Object obj) {
            this.value = obj;
            DisposableHelper.replace(this, this.scheduler.scheduleDirect(this));
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.scheduleDirect(this));
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.downstream.onError(th);
            } else {
                this.downstream.onSuccess(this.value);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }
}
