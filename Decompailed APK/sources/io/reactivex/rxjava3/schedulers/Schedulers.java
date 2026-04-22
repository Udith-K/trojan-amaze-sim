package io.reactivex.rxjava3.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.schedulers.ComputationScheduler;
import io.reactivex.rxjava3.internal.schedulers.IoScheduler;
import io.reactivex.rxjava3.internal.schedulers.NewThreadScheduler;
import io.reactivex.rxjava3.internal.schedulers.SingleScheduler;
import io.reactivex.rxjava3.internal.schedulers.TrampolineScheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public abstract class Schedulers {
    static final Scheduler SINGLE = RxJavaPlugins.initSingleScheduler(new SingleTask());
    static final Scheduler COMPUTATION = RxJavaPlugins.initComputationScheduler(new ComputationTask());
    static final Scheduler IO = RxJavaPlugins.initIoScheduler(new IOTask());
    static final Scheduler TRAMPOLINE = TrampolineScheduler.instance();
    static final Scheduler NEW_THREAD = RxJavaPlugins.initNewThreadScheduler(new NewThreadTask());

    static final class ComputationHolder {
        static final Scheduler DEFAULT = new ComputationScheduler();
    }

    static final class IoHolder {
        static final Scheduler DEFAULT = new IoScheduler();
    }

    static final class NewThreadHolder {
        static final Scheduler DEFAULT = new NewThreadScheduler();
    }

    static final class SingleHolder {
        static final Scheduler DEFAULT = new SingleScheduler();
    }

    public static Scheduler computation() {
        return RxJavaPlugins.onComputationScheduler(COMPUTATION);
    }

    public static Scheduler io() {
        return RxJavaPlugins.onIoScheduler(IO);
    }

    static final class IOTask implements Supplier {
        IOTask() {
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public Scheduler get() {
            return IoHolder.DEFAULT;
        }
    }

    static final class NewThreadTask implements Supplier {
        NewThreadTask() {
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public Scheduler get() {
            return NewThreadHolder.DEFAULT;
        }
    }

    static final class SingleTask implements Supplier {
        SingleTask() {
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public Scheduler get() {
            return SingleHolder.DEFAULT;
        }
    }

    static final class ComputationTask implements Supplier {
        ComputationTask() {
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        public Scheduler get() {
            return ComputationHolder.DEFAULT;
        }
    }
}
