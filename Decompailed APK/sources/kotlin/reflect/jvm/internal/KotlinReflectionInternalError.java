package kotlin.reflect.jvm.internal;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinReflectionInternalError.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class KotlinReflectionInternalError extends Error {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinReflectionInternalError(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
