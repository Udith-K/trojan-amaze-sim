package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class SingleFromCallable extends Single {
    final Callable callable;

    public SingleFromCallable(Callable callable) {
        this.callable = callable;
    }

    @Override // io.reactivex.rxjava3.core.Single
    protected void subscribeActual(SingleObserver singleObserver) {
        Disposable disposableEmpty = Disposable.CC.empty();
        singleObserver.onSubscribe(disposableEmpty);
        if (disposableEmpty.isDisposed()) {
            return;
        }
        try {
            Object objCall = this.callable.call();
            Objects.requireNonNull(objCall, "The callable returned a null value");
            if (disposableEmpty.isDisposed()) {
                return;
            }
            singleObserver.onSuccess(objCall);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (!disposableEmpty.isDisposed()) {
                singleObserver.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }
    }
}
