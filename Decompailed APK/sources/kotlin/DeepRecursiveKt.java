package kotlin;

import kotlin.Result;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeepRecursive.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DeepRecursiveKt {
    private static final Object UNDEFINED_RESULT;

    public static final Object invoke(DeepRecursiveFunction deepRecursiveFunction, Object obj) {
        Intrinsics.checkNotNullParameter(deepRecursiveFunction, "<this>");
        return new DeepRecursiveScopeImpl(deepRecursiveFunction.getBlock$kotlin_stdlib(), obj).runCallLoop();
    }

    static {
        Result.Companion companion = Result.Companion;
        UNDEFINED_RESULT = Result.m2639constructorimpl(IntrinsicsKt.getCOROUTINE_SUSPENDED());
    }
}
