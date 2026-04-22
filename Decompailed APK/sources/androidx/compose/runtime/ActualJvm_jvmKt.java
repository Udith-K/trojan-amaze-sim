package androidx.compose.runtime;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: ActualJvm.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ActualJvm_jvmKt {
    public static final int identityHashCode(Object obj) {
        return System.identityHashCode(obj);
    }

    public static final void invokeComposable(Composer composer, Function2 function2) {
        Intrinsics.checkNotNull(function2, "null cannot be cast to non-null type kotlin.Function2<androidx.compose.runtime.Composer, kotlin.Int, kotlin.Unit>");
        ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(composer, 1);
    }

    public static final long currentThreadId() {
        return Thread.currentThread().getId();
    }

    public static final String currentThreadName() {
        return Thread.currentThread().getName();
    }
}
