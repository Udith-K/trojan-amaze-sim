package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final class SingleDoOnError extends Single {
    final Consumer onError;
    final SingleSource source;

    public SingleDoOnError(SingleSource singleSource, Consumer consumer) {
        this.source = singleSource;
        this.onError = consumer;
    }

    @Override // io.reactivex.rxjava3.core.Single
    protected void subscribeActual(SingleObserver singleObserver) {
        this.source.subscribe(new DoOnError(singleObserver));
    }

    final class DoOnError implements SingleObserver {
        private final SingleObserver downstream;

        DoOnError(SingleObserver singleObserver) {
            this.downstream = singleObserver;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSubscribe(Disposable disposable) {
            this.downstream.onSubscribe(disposable);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(Object obj) {
            this.downstream.onSuccess(obj);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onError(Throwable th) {
            try {
                SingleDoOnError.this.onError.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.downstream.onError(th);
        }
    }
}
