package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;

/* JADX INFO: loaded from: classes.dex */
public final class SingleOnErrorReturn extends Single {
    final SingleSource source;
    final Object value;
    final Function valueSupplier;

    public SingleOnErrorReturn(SingleSource singleSource, Function function, Object obj) {
        this.source = singleSource;
        this.valueSupplier = function;
        this.value = obj;
    }

    @Override // io.reactivex.rxjava3.core.Single
    protected void subscribeActual(SingleObserver singleObserver) {
        this.source.subscribe(new OnErrorReturn(singleObserver));
    }

    final class OnErrorReturn implements SingleObserver {
        private final SingleObserver observer;

        OnErrorReturn(SingleObserver singleObserver) {
            this.observer = singleObserver;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onError(Throwable th) {
            Object objApply;
            SingleOnErrorReturn singleOnErrorReturn = SingleOnErrorReturn.this;
            Function function = singleOnErrorReturn.valueSupplier;
            if (function != null) {
                try {
                    objApply = function.apply(th);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    this.observer.onError(new CompositeException(th, th2));
                    return;
                }
            } else {
                objApply = singleOnErrorReturn.value;
            }
            if (objApply == null) {
                NullPointerException nullPointerException = new NullPointerException("Value supplied was null");
                nullPointerException.initCause(th);
                this.observer.onError(nullPointerException);
                return;
            }
            this.observer.onSuccess(objApply);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSubscribe(Disposable disposable) {
            this.observer.onSubscribe(disposable);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(Object obj) {
            this.observer.onSuccess(obj);
        }
    }
}
