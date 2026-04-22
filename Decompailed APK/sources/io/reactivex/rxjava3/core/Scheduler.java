package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.schedulers.NewThreadWorker;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class Scheduler {
    static boolean IS_DRIFT_USE_NANOTIME = Boolean.getBoolean("rx3.scheduler.use-nanotime");
    static final long CLOCK_DRIFT_TOLERANCE_NANOSECONDS = computeClockDrift(Long.getLong("rx3.scheduler.drift-tolerance", 15).longValue(), System.getProperty("rx3.scheduler.drift-tolerance-unit", "minutes"));

    public abstract Worker createWorker();

    static long computeNow(TimeUnit timeUnit) {
        if (!IS_DRIFT_USE_NANOTIME) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
        return timeUnit.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    static long computeClockDrift(long j, String str) {
        if ("seconds".equalsIgnoreCase(str)) {
            return TimeUnit.SECONDS.toNanos(j);
        }
        if ("milliseconds".equalsIgnoreCase(str)) {
            return TimeUnit.MILLISECONDS.toNanos(j);
        }
        return TimeUnit.MINUTES.toNanos(j);
    }

    public Disposable scheduleDirect(Runnable runnable) {
        return scheduleDirect(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public Disposable scheduleDirect(Runnable runnable, long j, TimeUnit timeUnit) {
        Worker workerCreateWorker = createWorker();
        DisposeTask disposeTask = new DisposeTask(RxJavaPlugins.onSchedule(runnable), workerCreateWorker);
        workerCreateWorker.schedule(disposeTask, j, timeUnit);
        return disposeTask;
    }

    public static abstract class Worker implements Disposable {
        public abstract Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit);

        public long now(TimeUnit timeUnit) {
            return Scheduler.computeNow(timeUnit);
        }
    }

    static final class DisposeTask implements Disposable, Runnable {
        final Runnable decoratedRun;
        Thread runner;
        final Worker w;

        DisposeTask(Runnable runnable, Worker worker) {
            this.decoratedRun = runnable;
            this.w = worker;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.runner = Thread.currentThread();
            try {
                this.decoratedRun.run();
            } finally {
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.runner == Thread.currentThread()) {
                Worker worker = this.w;
                if (worker instanceof NewThreadWorker) {
                    ((NewThreadWorker) worker).shutdown();
                    return;
                }
            }
            this.w.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.w.isDisposed();
        }
    }
}
