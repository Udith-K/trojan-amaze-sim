package io.ktor.util.pipeline;

import io.ktor.utils.io.ExceptionUtilsJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StackTraceRecoverJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StackTraceRecoverJvmKt {
    public static final Throwable withCause(Throwable th, Throwable th2) {
        Throwable thTryCopyException;
        Intrinsics.checkNotNullParameter(th, "<this>");
        if (th2 == null || Intrinsics.areEqual(th.getCause(), th2) || (thTryCopyException = ExceptionUtilsJvmKt.tryCopyException(th, th2)) == null) {
            return th;
        }
        thTryCopyException.setStackTrace(th.getStackTrace());
        return thTryCopyException;
    }
}
