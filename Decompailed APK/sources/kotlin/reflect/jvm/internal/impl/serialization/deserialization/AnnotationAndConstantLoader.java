package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: AnnotationAndConstantLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface AnnotationAndConstantLoader extends AnnotationLoader {
    Object loadAnnotationDefaultValue(ProtoContainer protoContainer, ProtoBuf$Property protoBuf$Property, KotlinType kotlinType);

    Object loadPropertyConstant(ProtoContainer protoContainer, ProtoBuf$Property protoBuf$Property, KotlinType kotlinType);
}
