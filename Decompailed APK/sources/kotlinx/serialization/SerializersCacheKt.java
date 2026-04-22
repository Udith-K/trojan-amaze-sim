package kotlinx.serialization;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.CachingKt;
import kotlinx.serialization.internal.ParametrizedSerializerCache;
import kotlinx.serialization.internal.SerializerCache;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;

/* JADX INFO: compiled from: SerializersCache.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class SerializersCacheKt {
    private static final SerializerCache SERIALIZERS_CACHE = CachingKt.createCache(new Function1() { // from class: kotlinx.serialization.SerializersCacheKt$SERIALIZERS_CACHE$1
        @Override // kotlin.jvm.functions.Function1
        public final KSerializer invoke(KClass it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return SerializersKt.serializerOrNull(it);
        }
    });
    private static final SerializerCache SERIALIZERS_CACHE_NULLABLE = CachingKt.createCache(new Function1() { // from class: kotlinx.serialization.SerializersCacheKt$SERIALIZERS_CACHE_NULLABLE$1
        @Override // kotlin.jvm.functions.Function1
        public final KSerializer invoke(KClass it) {
            KSerializer nullable;
            Intrinsics.checkNotNullParameter(it, "it");
            KSerializer kSerializerSerializerOrNull = SerializersKt.serializerOrNull(it);
            if (kSerializerSerializerOrNull == null || (nullable = BuiltinSerializersKt.getNullable(kSerializerSerializerOrNull)) == null) {
                return null;
            }
            return nullable;
        }
    });
    private static final ParametrizedSerializerCache PARAMETRIZED_SERIALIZERS_CACHE = CachingKt.createParametrizedCache(new Function2() { // from class: kotlinx.serialization.SerializersCacheKt$PARAMETRIZED_SERIALIZERS_CACHE$1
        @Override // kotlin.jvm.functions.Function2
        public final KSerializer invoke(KClass clazz, List types) {
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            Intrinsics.checkNotNullParameter(types, "types");
            List listSerializersForParameters = SerializersKt.serializersForParameters(SerializersModuleBuildersKt.EmptySerializersModule(), types, true);
            Intrinsics.checkNotNull(listSerializersForParameters);
            return SerializersKt.parametrizedSerializerOrNull(clazz, types, listSerializersForParameters);
        }
    });
    private static final ParametrizedSerializerCache PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE = CachingKt.createParametrizedCache(new Function2() { // from class: kotlinx.serialization.SerializersCacheKt$PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE$1
        @Override // kotlin.jvm.functions.Function2
        public final KSerializer invoke(KClass clazz, List types) {
            KSerializer nullable;
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            Intrinsics.checkNotNullParameter(types, "types");
            List listSerializersForParameters = SerializersKt.serializersForParameters(SerializersModuleBuildersKt.EmptySerializersModule(), types, true);
            Intrinsics.checkNotNull(listSerializersForParameters);
            KSerializer kSerializerParametrizedSerializerOrNull = SerializersKt.parametrizedSerializerOrNull(clazz, types, listSerializersForParameters);
            if (kSerializerParametrizedSerializerOrNull == null || (nullable = BuiltinSerializersKt.getNullable(kSerializerParametrizedSerializerOrNull)) == null) {
                return null;
            }
            return nullable;
        }
    });

    public static final KSerializer findCachedSerializer(KClass clazz, boolean z) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        if (!z) {
            KSerializer kSerializer = SERIALIZERS_CACHE.get(clazz);
            if (kSerializer != null) {
                return kSerializer;
            }
            return null;
        }
        return SERIALIZERS_CACHE_NULLABLE.get(clazz);
    }

    public static final Object findParametrizedCachedSerializer(KClass clazz, List types, boolean z) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(types, "types");
        if (!z) {
            return PARAMETRIZED_SERIALIZERS_CACHE.mo2848getgIAlus(clazz, types);
        }
        return PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE.mo2848getgIAlus(clazz, types);
    }
}
