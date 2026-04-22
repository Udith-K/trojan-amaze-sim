package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.observers.ConsumerSingleObserver;
import io.reactivex.rxjava3.internal.operators.single.SingleDoOnError;
import io.reactivex.rxjava3.internal.operators.single.SingleFromCallable;
import io.reactivex.rxjava3.internal.operators.single.SingleObserveOn;
import io.reactivex.rxjava3.internal.operators.single.SingleOnErrorReturn;
import io.reactivex.rxjava3.internal.operators.single.SingleSubscribeOn;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public abstract class Single implements SingleSource {
    protected abstract void subscribeActual(SingleObserver singleObserver);

    public static Single fromCallable(Callable callable) {
        Objects.requireNonNull(callable, "callable is null");
        return RxJavaPlugins.onAssembly(new SingleFromCallable(callable));
    }

    public final Single doOnError(Consumer consumer) {
        Objects.requireNonNull(consumer, "onError is null");
        return RxJavaPlugins.onAssembly(new SingleDoOnError(this, consumer));
    }

    public final Single observeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleObserveOn(this, scheduler));
    }

    public final Single onErrorReturnItem(Object obj) {
        Objects.requireNonNull(obj, "item is null");
        return RxJavaPlugins.onAssembly(new SingleOnErrorReturn(this, null, obj));
    }

    public final Disposable subscribe() {
        return subscribe(Functions.emptyConsumer(), Functions.ON_ERROR_MISSING);
    }

    public final Disposable subscribe(Consumer consumer) {
        return subscribe(consumer, Functions.ON_ERROR_MISSING);
    }

    public final Disposable subscribe(Consumer consumer, Consumer consumer2) {
        Objects.requireNonNull(consumer, "onSuccess is null");
        Objects.requireNonNull(consumer2, "onError is null");
        ConsumerSingleObserver consumerSingleObserver = new ConsumerSingleObserver(consumer, consumer2);
        subscribe(consumerSingleObserver);
        return consumerSingleObserver;
    }

    @Override // io.reactivex.rxjava3.core.SingleSource
    public final void subscribe(SingleObserver singleObserver) {
        Objects.requireNonNull(singleObserver, "observer is null");
        SingleObserver singleObserverOnSubscribe = RxJavaPlugins.onSubscribe(this, singleObserver);
        Objects.requireNonNull(singleObserverOnSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            subscribeActual(singleObserverOnSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final Single subscribeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new SingleSubscribeOn(this, scheduler));
    }
}
