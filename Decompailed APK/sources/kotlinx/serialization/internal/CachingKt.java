package kotlinx.serialization.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Caching.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class CachingKt {
    private static final boolean useClassValue;

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
        useClassValue = ((Boolean) objM2639constructorimpl2).booleanValue();
    }

    public static final SerializerCache createCache(Function1 factory) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        return useClassValue ? new ClassValueCache(factory) : new ConcurrentHashMapCache(factory);
    }

    public static final ParametrizedSerializerCache createParametrizedCache(Function2 factory) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        return useClassValue ? new ClassValueParametrizedCache(factory) : new ConcurrentHashMapParametrizedCache(factory);
    }
}
