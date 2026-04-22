package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class NewThreadWorker extends Scheduler.Worker {
    volatile boolean disposed;
    private final ScheduledExecutorService executor;

    public NewThreadWorker(ThreadFactory threadFactory) {
        this.executor = SchedulerPoolFactory.create(threadFactory);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        if (this.disposed) {
            return EmptyDisposable.INSTANCE;
        }
        return scheduleActual(runnable, j, timeUnit, null);
    }

    public Disposable scheduleDirect(Runnable runnable, long j, TimeUnit timeUnit) {
        Future futureSchedule;
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(RxJavaPlugins.onSchedule(runnable), true);
        try {
            if (j <= 0) {
                futureSchedule = this.executor.submit(scheduledDirectTask);
            } else {
                futureSchedule = this.executor.schedule(scheduledDirectTask, j, timeUnit);
            }
            scheduledDirectTask.setFuture(futureSchedule);
            return scheduledDirectTask;
        } catch (RejectedExecutionException e) {
            RxJavaPlugins.onError(e);
            return EmptyDisposable.INSTANCE;
        }
    }

    public ScheduledRunnable scheduleActual(Runnable runnable, long j, TimeUnit timeUnit, DisposableContainer disposableContainer) {
        Future futureSchedule;
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(RxJavaPlugins.onSchedule(runnable), disposableContainer);
        if (disposableContainer != null && !disposableContainer.add(scheduledRunnable)) {
            return scheduledRunnable;
        }
        try {
            if (j <= 0) {
                futureSchedule = this.executor.submit((Callable) scheduledRunnable);
            } else {
                futureSchedule = this.executor.schedule((Callable) scheduledRunnable, j, timeUnit);
            }
            scheduledRunnable.setFuture(futureSchedule);
        } catch (RejectedExecutionException e) {
            if (disposableContainer != null) {
                disposableContainer.remove(scheduledRunnable);
            }
            RxJavaPlugins.onError(e);
        }
        return scheduledRunnable;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        this.executor.shutdownNow();
    }

    public void shutdown() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        this.executor.shutdown();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }
}
