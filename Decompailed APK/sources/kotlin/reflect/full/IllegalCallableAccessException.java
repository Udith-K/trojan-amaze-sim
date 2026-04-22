package kotlin.reflect.full;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: exceptions.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class IllegalCallableAccessException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IllegalCallableAccessException(IllegalAccessException cause) {
        super(cause);
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}
