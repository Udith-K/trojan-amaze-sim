package io.reactivex.rxjava3.internal.schedulers;

import ch.qos.logback.core.CoreConstants;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class RxThreadFactory extends AtomicLong implements ThreadFactory {
    final boolean nonBlocking;
    final String prefix;
    final int priority;

    public RxThreadFactory(String str) {
        this(str, 5, false);
    }

    public RxThreadFactory(String str, int i) {
        this(str, i, false);
    }

    public RxThreadFactory(String str, int i, boolean z) {
        this.prefix = str;
        this.priority = i;
        this.nonBlocking = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        String str = this.prefix + CoreConstants.DASH_CHAR + incrementAndGet();
        Thread rxCustomThread = this.nonBlocking ? new RxCustomThread(runnable, str) : new Thread(runnable, str);
        rxCustomThread.setPriority(this.priority);
        rxCustomThread.setDaemon(true);
        return rxCustomThread;
    }

    @Override // java.util.concurrent.atomic.AtomicLong
    public String toString() {
        return "RxThreadFactory[" + this.prefix + "]";
    }

    static final class RxCustomThread extends Thread {
        RxCustomThread(Runnable runnable, String str) {
            super(runnable, str);
        }
    }
}
