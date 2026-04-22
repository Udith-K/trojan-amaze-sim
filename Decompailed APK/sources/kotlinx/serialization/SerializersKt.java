package kotlinx.serialization;

import java.util.List;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SerializersKt {
    public static final KSerializer parametrizedSerializerOrNull(KClass kClass, List list, List list2) {
        return SerializersKt__SerializersKt.parametrizedSerializerOrNull(kClass, list, list2);
    }

    public static final KSerializer serializer(KType kType) {
        return SerializersKt__SerializersKt.serializer(kType);
    }

    public static final KSerializer serializer(SerializersModule serializersModule, KType kType) {
        return SerializersKt__SerializersKt.serializer(serializersModule, kType);
    }

    public static final KSerializer serializerOrNull(KClass kClass) {
        return SerializersKt__SerializersKt.serializerOrNull(kClass);
    }

    public static final KSerializer serializerOrNull(SerializersModule serializersModule, KType kType) {
        return SerializersKt__SerializersKt.serializerOrNull(serializersModule, kType);
    }

    public static final List serializersForParameters(SerializersModule serializersModule, List list, boolean z) {
        return SerializersKt__SerializersKt.serializersForParameters(serializersModule, list, z);
    }
}
