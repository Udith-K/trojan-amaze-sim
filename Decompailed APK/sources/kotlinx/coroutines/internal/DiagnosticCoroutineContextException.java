package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;

/* JADX INFO: compiled from: CoroutineExceptionHandlerImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class DiagnosticCoroutineContextException extends RuntimeException {
    private final transient CoroutineContext context;

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public DiagnosticCoroutineContextException(CoroutineContext coroutineContext) {
        this.context = coroutineContext;
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.context.toString();
    }
}
