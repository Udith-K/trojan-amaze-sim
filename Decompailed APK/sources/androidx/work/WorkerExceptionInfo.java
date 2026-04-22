package androidx.work;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkerExceptionInfo.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WorkerExceptionInfo {
    private final Throwable throwable;
    private final String workerClassName;
    private final WorkerParameters workerParameters;

    public WorkerExceptionInfo(String workerClassName, WorkerParameters workerParameters, Throwable throwable) {
        Intrinsics.checkNotNullParameter(workerClassName, "workerClassName");
        Intrinsics.checkNotNullParameter(workerParameters, "workerParameters");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        this.workerClassName = workerClassName;
        this.workerParameters = workerParameters;
        this.throwable = throwable;
    }
}
