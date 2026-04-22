package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class TrampolineScheduler extends Scheduler {
    private static final TrampolineScheduler INSTANCE = new TrampolineScheduler();

    public static TrampolineScheduler instance() {
        return INSTANCE;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Scheduler.Worker createWorker() {
        return new TrampolineWorker();
    }

    TrampolineScheduler() {
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Disposable scheduleDirect(Runnable runnable) {
        RxJavaPlugins.onSchedule(runnable).run();
        return EmptyDisposable.INSTANCE;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Disposable scheduleDirect(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            RxJavaPlugins.onSchedule(runnable).run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            RxJavaPlugins.onError(e);
        }
        return EmptyDisposable.INSTANCE;
    }

    static final class TrampolineWorker extends Scheduler.Worker {
        volatile boolean disposed;
        final PriorityBlockingQueue queue = new PriorityBlockingQueue();
        private final AtomicInteger wip = new AtomicInteger();
        final AtomicInteger counter = new AtomicInteger();

        TrampolineWorker() {
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            long jNow = now(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return enqueue(new SleepingRunnable(runnable, this, jNow), jNow);
        }

        Disposable enqueue(Runnable runnable, long j) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            TimedRunnable timedRunnable = new TimedRunnable(runnable, Long.valueOf(j), this.counter.incrementAndGet());
            this.queue.add(timedRunnable);
            if (this.wip.getAndIncrement() == 0) {
                int iAddAndGet = 1;
                while (!this.disposed) {
                    TimedRunnable timedRunnable2 = (TimedRunnable) this.queue.poll();
                    if (timedRunnable2 != null) {
                        if (!timedRunnable2.disposed) {
                            timedRunnable2.run.run();
                        }
                    } else {
                        iAddAndGet = this.wip.addAndGet(-iAddAndGet);
                        if (iAddAndGet == 0) {
                            return EmptyDisposable.INSTANCE;
                        }
                    }
                }
                this.queue.clear();
                return EmptyDisposable.INSTANCE;
            }
            return Disposable.CC.fromRunnable(new AppendToQueueTask(timedRunnable));
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.disposed = true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        final class AppendToQueueTask implements Runnable {
            final TimedRunnable timedRunnable;

            AppendToQueueTask(TimedRunnable timedRunnable) {
                this.timedRunnable = timedRunnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.timedRunnable.disposed = true;
                TrampolineWorker.this.queue.remove(this.timedRunnable);
            }
        }
    }

    static final class TimedRunnable implements Comparable {
        final int count;
        volatile boolean disposed;
        final long execTime;
        final Runnable run;

        TimedRunnable(Runnable runnable, Long l, int i) {
            this.run = runnable;
            this.execTime = l.longValue();
            this.count = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(TimedRunnable timedRunnable) {
            int iCompare = Long.compare(this.execTime, timedRunnable.execTime);
            return iCompare == 0 ? Integer.compare(this.count, timedRunnable.count) : iCompare;
        }
    }

    static final class SleepingRunnable implements Runnable {
        private final long execTime;
        private final Runnable run;
        private final TrampolineWorker worker;

        SleepingRunnable(Runnable runnable, TrampolineWorker trampolineWorker, long j) {
            this.run = runnable;
            this.worker = trampolineWorker;
            this.execTime = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.worker.disposed) {
                return;
            }
            long jNow = this.worker.now(TimeUnit.MILLISECONDS);
            long j = this.execTime;
            if (j > jNow) {
                try {
                    Thread.sleep(j - jNow);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    RxJavaPlugins.onError(e);
                    return;
                }
            }
            if (this.worker.disposed) {
                return;
            }
            this.run.run();
        }
    }
}
