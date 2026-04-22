package androidx.compose.foundation;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: MutatorMutex.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MutationInterruptedException extends CancellationException {
    public MutationInterruptedException() {
        super("Mutation interrupted");
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
