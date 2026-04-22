package io.reactivex.rxjava3.plugins;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class RxJavaPlugins {
    static volatile Consumer errorHandler;
    static volatile boolean lockdown;
    static volatile Function onCompletableAssembly;
    static volatile Function onComputationHandler;
    static volatile Function onInitComputationHandler;
    static volatile Function onInitIoHandler;
    static volatile Function onInitNewThreadHandler;
    static volatile Function onInitSingleHandler;
    static volatile Function onIoHandler;
    static volatile Function onScheduleHandler;
    static volatile Function onSingleAssembly;

    public static CompletableObserver onSubscribe(Completable completable, CompletableObserver completableObserver) {
        return completableObserver;
    }

    public static SingleObserver onSubscribe(Single single, SingleObserver singleObserver) {
        return singleObserver;
    }

    public static Scheduler initComputationScheduler(Supplier supplier) {
        Objects.requireNonNull(supplier, "Scheduler Supplier can't be null");
        Function function = onInitComputationHandler;
        if (function == null) {
            return callRequireNonNull(supplier);
        }
        return applyRequireNonNull(function, supplier);
    }

    public static Scheduler initIoScheduler(Supplier supplier) {
        Objects.requireNonNull(supplier, "Scheduler Supplier can't be null");
        Function function = onInitIoHandler;
        if (function == null) {
            return callRequireNonNull(supplier);
        }
        return applyRequireNonNull(function, supplier);
    }

    public static Scheduler initNewThreadScheduler(Supplier supplier) {
        Objects.requireNonNull(supplier, "Scheduler Supplier can't be null");
        Function function = onInitNewThreadHandler;
        if (function == null) {
            return callRequireNonNull(supplier);
        }
        return applyRequireNonNull(function, supplier);
    }

    public static Scheduler initSingleScheduler(Supplier supplier) {
        Objects.requireNonNull(supplier, "Scheduler Supplier can't be null");
        Function function = onInitSingleHandler;
        if (function == null) {
            return callRequireNonNull(supplier);
        }
        return applyRequireNonNull(function, supplier);
    }

    public static Scheduler onComputationScheduler(Scheduler scheduler) {
        Function function = onComputationHandler;
        return function == null ? scheduler : (Scheduler) apply(function, scheduler);
    }

    public static void onError(Throwable th) {
        Consumer consumer = errorHandler;
        if (th == null) {
            th = ExceptionHelper.createNullPointerException("onError called with a null Throwable.");
        } else if (!isBug(th)) {
            th = new UndeliverableException(th);
        }
        if (consumer != null) {
            try {
                consumer.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                uncaught(th2);
            }
        }
        th.printStackTrace();
        uncaught(th);
    }

    static boolean isBug(Throwable th) {
        return (th instanceof OnErrorNotImplementedException) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof CompositeException);
    }

    static void uncaught(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }

    public static Scheduler onIoScheduler(Scheduler scheduler) {
        Function function = onIoHandler;
        return function == null ? scheduler : (Scheduler) apply(function, scheduler);
    }

    public static Runnable onSchedule(Runnable runnable) {
        Objects.requireNonNull(runnable, "run is null");
        Function function = onScheduleHandler;
        return function == null ? runnable : (Runnable) apply(function, runnable);
    }

    public static void setErrorHandler(Consumer consumer) {
        if (lockdown) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        errorHandler = consumer;
    }

    public static Single onAssembly(Single single) {
        Function function = onSingleAssembly;
        return function != null ? (Single) apply(function, single) : single;
    }

    public static Completable onAssembly(Completable completable) {
        Function function = onCompletableAssembly;
        return function != null ? (Completable) apply(function, completable) : completable;
    }

    static Object apply(Function function, Object obj) {
        try {
            return function.apply(obj);
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    static Scheduler callRequireNonNull(Supplier supplier) {
        try {
            Object obj = supplier.get();
            Objects.requireNonNull(obj, "Scheduler Supplier result can't be null");
            return (Scheduler) obj;
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    static Scheduler applyRequireNonNull(Function function, Supplier supplier) {
        Object objApply = apply(function, supplier);
        Objects.requireNonNull(objApply, "Scheduler Supplier result can't be null");
        return (Scheduler) objApply;
    }
}
