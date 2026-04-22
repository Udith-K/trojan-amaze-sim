package androidx.work.impl;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: WorkerWrapper.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WorkerStoppedException extends CancellationException {
    private final int reason;

    public WorkerStoppedException(int i) {
        this.reason = i;
    }

    public final int getReason() {
        return this.reason;
    }
}
