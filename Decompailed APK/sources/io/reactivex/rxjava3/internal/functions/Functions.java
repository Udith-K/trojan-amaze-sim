package io.reactivex.rxjava3.internal.functions;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.LongConsumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import org.fdroid.fdroid.Preferences;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: classes.dex */
public abstract class Functions {
    static final Function IDENTITY = new Identity();
    public static final Runnable EMPTY_RUNNABLE = new EmptyRunnable();
    public static final Action EMPTY_ACTION = new EmptyAction();
    static final Consumer EMPTY_CONSUMER = new EmptyConsumer();
    public static final Consumer ERROR_CONSUMER = new ErrorConsumer();
    public static final Consumer ON_ERROR_MISSING = new OnErrorMissingConsumer();
    public static final LongConsumer EMPTY_LONG_CONSUMER = new EmptyLongConsumer();
    static final Predicate ALWAYS_TRUE = new TruePredicate();
    static final Predicate ALWAYS_FALSE = new FalsePredicate();
    static final Supplier NULL_SUPPLIER = new NullProvider();
    public static final Consumer REQUEST_MAX = new MaxRequestSubscription();

    public static Consumer emptyConsumer() {
        return EMPTY_CONSUMER;
    }

    static final class Identity implements Function {
        @Override // io.reactivex.rxjava3.functions.Function
        public Object apply(Object obj) {
            return obj;
        }

        Identity() {
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    static final class EmptyRunnable implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        EmptyRunnable() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    static final class EmptyAction implements Action {
        @Override // io.reactivex.rxjava3.functions.Action
        public void run() {
        }

        EmptyAction() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    static final class EmptyConsumer implements Consumer {
        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Object obj) {
        }

        EmptyConsumer() {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    static final class ErrorConsumer implements Consumer {
        ErrorConsumer() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Throwable th) {
            RxJavaPlugins.onError(th);
        }
    }

    static final class OnErrorMissingConsumer implements Consumer {
        OnErrorMissingConsumer() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Throwable th) {
            RxJavaPlugins.onError(new OnErrorNotImplementedException(th));
        }
    }

    static final class EmptyLongConsumer implements LongConsumer {
        EmptyLongConsumer() {
        }
    }

    static final class TruePredicate implements Predicate {
        @Override // io.reactivex.rxjava3.functions.Predicate
        public boolean test(Object obj) {
            return true;
        }

        TruePredicate() {
        }
    }

    static final class FalsePredicate implements Predicate {
        @Override // io.reactivex.rxjava3.functions.Predicate
        public boolean test(Object obj) {
            return false;
        }

        FalsePredicate() {
        }
    }

    static final class NullProvider implements Supplier {
        @Override // io.reactivex.rxjava3.functions.Supplier
        public Object get() {
            return null;
        }

        NullProvider() {
        }
    }

    static final class MaxRequestSubscription implements Consumer {
        MaxRequestSubscription() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public /* bridge */ /* synthetic */ void accept(Object obj) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
            accept((Subscription) null);
        }

        public void accept(Subscription subscription) {
            subscription.request(Preferences.UPDATE_INTERVAL_DISABLED);
        }
    }
}
