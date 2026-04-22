package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;

/* JADX INFO: compiled from: AnnotationLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface AnnotationLoader {
    Object loadAnnotation(ProtoBuf$Annotation protoBuf$Annotation, NameResolver nameResolver);

    List loadCallableAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind);

    List loadClassAnnotations(ProtoContainer.Class r1);

    List loadEnumEntryAnnotations(ProtoContainer protoContainer, ProtoBuf$EnumEntry protoBuf$EnumEntry);

    List loadExtensionReceiverParameterAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind);

    List loadPropertyBackingFieldAnnotations(ProtoContainer protoContainer, ProtoBuf$Property protoBuf$Property);

    List loadPropertyDelegateFieldAnnotations(ProtoContainer protoContainer, ProtoBuf$Property protoBuf$Property);

    List loadTypeAnnotations(ProtoBuf$Type protoBuf$Type, NameResolver nameResolver);

    List loadTypeParameterAnnotations(ProtoBuf$TypeParameter protoBuf$TypeParameter, NameResolver nameResolver);

    List loadValueParameterAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind, int i, ProtoBuf$ValueParameter protoBuf$ValueParameter);
}
