package androidx.compose.runtime.internal;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: Utils.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PlatformOptimizedCancellationException extends CancellationException {
    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public PlatformOptimizedCancellationException(String str) {
        super(str);
    }
}
