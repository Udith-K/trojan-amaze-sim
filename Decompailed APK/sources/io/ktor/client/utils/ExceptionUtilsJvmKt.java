package io.ktor.client.utils;

import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExceptionUtilsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ExceptionUtilsJvmKt {
    public static final Throwable unwrapCancellationException(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        Throwable cause = th;
        while (cause instanceof CancellationException) {
            CancellationException cancellationException = (CancellationException) cause;
            if (Intrinsics.areEqual(cause, cancellationException.getCause())) {
                return th;
            }
            cause = cancellationException.getCause();
        }
        return cause == null ? th : cause;
    }
}
