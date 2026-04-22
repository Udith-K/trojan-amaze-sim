package kotlinx.serialization.internal;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: Caching.kt */
/* JADX INFO: loaded from: classes2.dex */
final class ClassValueParametrizedCache implements ParametrizedSerializerCache {
    private final AnonymousClass1 classValue;
    private final Function2 compute;

    public ClassValueParametrizedCache(Function2 compute) {
        Intrinsics.checkNotNullParameter(compute, "compute");
        this.compute = compute;
        this.classValue = initClassValue();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.serialization.internal.ClassValueParametrizedCache$initClassValue$1] */
    private final AnonymousClass1 initClassValue() {
        return new ClassValue() { // from class: kotlinx.serialization.internal.ClassValueParametrizedCache.initClassValue.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ClassValue
            public ParametrizedCacheEntry computeValue(Class type) {
                Intrinsics.checkNotNullParameter(type, "type");
                return new ParametrizedCacheEntry();
            }
        };
    }

    @Override // kotlinx.serialization.internal.ParametrizedSerializerCache
    /* JADX INFO: renamed from: get-gIAlu-s, reason: not valid java name */
    public Object mo2848getgIAlus(KClass key, List types) {
        Object objM2639constructorimpl;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(types, "types");
        ConcurrentHashMap concurrentHashMap = ((ParametrizedCacheEntry) get(JvmClassMappingKt.getJavaClass(key))).serializers;
        Object obj = concurrentHashMap.get(types);
        if (obj == null) {
            try {
                Result.Companion companion = Result.Companion;
                objM2639constructorimpl = Result.m2639constructorimpl((KSerializer) this.compute.invoke(key, types));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
            }
            Result resultM2638boximpl = Result.m2638boximpl(objM2639constructorimpl);
            Object objPutIfAbsent = concurrentHashMap.putIfAbsent(types, resultM2638boximpl);
            obj = objPutIfAbsent == null ? resultM2638boximpl : objPutIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(obj, "serializers.getOrPut(typ… { producer() }\n        }");
        return ((Result) obj).m2647unboximpl();
    }
}
