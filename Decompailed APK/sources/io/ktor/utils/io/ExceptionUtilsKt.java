package io.ktor.utils.io;

import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExceptionUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ExceptionUtilsKt {
    public static final Throwable unwrapCancellationException(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        Throwable th2 = th;
        while (th2 instanceof CancellationException) {
            if (Intrinsics.areEqual(th2, th2.getCause())) {
                return th;
            }
            Throwable cause = th2.getCause();
            if (cause == null) {
                return th2;
            }
            th2 = cause;
        }
        return th2;
    }
}
