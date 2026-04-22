package androidx.collection.internal;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RuntimeHelpers.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RuntimeHelpersKt {
    public static final void throwIllegalStateException(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw new IllegalStateException(message);
    }

    public static final void throwIllegalArgumentException(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw new IllegalArgumentException(message);
    }
}
