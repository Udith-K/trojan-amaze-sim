package androidx.compose.ui.input.pointer;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: SuspendingPointerInputFilter.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CancelTimeoutCancellationException extends CancellationException {
    public static final CancelTimeoutCancellationException INSTANCE = new CancelTimeoutCancellationException();

    private CancelTimeoutCancellationException() {
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(SuspendingPointerInputFilter_jvmKt.EmptyStackTraceElements);
        return this;
    }
}
