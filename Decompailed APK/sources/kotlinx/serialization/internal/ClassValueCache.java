package kotlinx.serialization.internal;

import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: Caching.kt */
/* JADX INFO: loaded from: classes2.dex */
final class ClassValueCache implements SerializerCache {
    private final AnonymousClass1 classValue;
    private final Function1 compute;

    public ClassValueCache(Function1 compute) {
        Intrinsics.checkNotNullParameter(compute, "compute");
        this.compute = compute;
        this.classValue = initClassValue();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.serialization.internal.ClassValueCache$initClassValue$1] */
    private final AnonymousClass1 initClassValue() {
        return new ClassValue() { // from class: kotlinx.serialization.internal.ClassValueCache.initClassValue.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ClassValue
            public CacheEntry computeValue(Class type) {
                Intrinsics.checkNotNullParameter(type, "type");
                return new CacheEntry((KSerializer) ClassValueCache.this.compute.invoke(JvmClassMappingKt.getKotlinClass(type)));
            }
        };
    }

    @Override // kotlinx.serialization.internal.SerializerCache
    public KSerializer get(KClass key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return ((CacheEntry) get(JvmClassMappingKt.getJavaClass(key))).serializer;
    }
}
