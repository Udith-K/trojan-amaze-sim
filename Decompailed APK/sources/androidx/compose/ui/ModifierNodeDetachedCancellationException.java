package androidx.compose.ui;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: Modifier.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ModifierNodeDetachedCancellationException extends CancellationException {
    public ModifierNodeDetachedCancellationException() {
        super("The Modifier.Node was detached");
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(Modifier_jvmKt.EmptyStackTraceElements);
        return this;
    }
}
