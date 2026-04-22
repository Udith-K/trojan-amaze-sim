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
final class ConcurrentHashMapParametrizedCache implements ParametrizedSerializerCache {
    private final ConcurrentHashMap cache;
    private final Function2 compute;

    public ConcurrentHashMapParametrizedCache(Function2 compute) {
        Intrinsics.checkNotNullParameter(compute, "compute");
        this.compute = compute;
        this.cache = new ConcurrentHashMap();
    }

    @Override // kotlinx.serialization.internal.ParametrizedSerializerCache
    /* JADX INFO: renamed from: get-gIAlu-s */
    public Object mo2848getgIAlus(KClass key, List types) {
        Object objM2639constructorimpl;
        Object objPutIfAbsent;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(types, "types");
        ConcurrentHashMap concurrentHashMap = this.cache;
        Class javaClass = JvmClassMappingKt.getJavaClass(key);
        Object parametrizedCacheEntry = concurrentHashMap.get(javaClass);
        if (parametrizedCacheEntry == null && (objPutIfAbsent = concurrentHashMap.putIfAbsent(javaClass, (parametrizedCacheEntry = new ParametrizedCacheEntry()))) != null) {
            parametrizedCacheEntry = objPutIfAbsent;
        }
        ConcurrentHashMap concurrentHashMap2 = ((ParametrizedCacheEntry) parametrizedCacheEntry).serializers;
        Object obj = concurrentHashMap2.get(types);
        if (obj == null) {
            try {
                Result.Companion companion = Result.Companion;
                objM2639constructorimpl = Result.m2639constructorimpl((KSerializer) this.compute.invoke(key, types));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
            }
            Result resultM2638boximpl = Result.m2638boximpl(objM2639constructorimpl);
            Object objPutIfAbsent2 = concurrentHashMap2.putIfAbsent(types, resultM2638boximpl);
            obj = objPutIfAbsent2 == null ? resultM2638boximpl : objPutIfAbsent2;
        }
        Intrinsics.checkNotNullExpressionValue(obj, "serializers.getOrPut(typ… { producer() }\n        }");
        return ((Result) obj).m2647unboximpl();
    }
}
