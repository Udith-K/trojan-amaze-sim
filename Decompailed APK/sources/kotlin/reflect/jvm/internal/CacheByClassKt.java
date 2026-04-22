package kotlin.reflect.jvm.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CacheByClass.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class CacheByClassKt {
    static {
        Object objM2639constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            objM2639constructorimpl = Result.m2639constructorimpl(Class.forName("java.lang.ClassValue"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2645isSuccessimpl(objM2639constructorimpl)) {
            objM2639constructorimpl = Boolean.TRUE;
        }
        Object objM2639constructorimpl2 = Result.m2639constructorimpl(objM2639constructorimpl);
        Boolean bool = Boolean.FALSE;
        if (Result.m2644isFailureimpl(objM2639constructorimpl2)) {
            objM2639constructorimpl2 = bool;
        }
        ((Boolean) objM2639constructorimpl2).booleanValue();
    }

    public static final CacheByClass createCache(Function1 compute) {
        Intrinsics.checkNotNullParameter(compute, "compute");
        return new ConcurrentHashMapCache(compute);
    }
}
