package org.fdroid.index.v1;

import ch.qos.logback.core.joran.action.Action;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElementKt;

/* JADX INFO: compiled from: PackageV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lorg/fdroid/index/v1/PermissionV1Serializer;", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v1/PermissionV1;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", Action.VALUE_ATTRIBUTE, "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PermissionV1Serializer implements KSerializer {
    private final SerialDescriptor descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("PermissionV1", new SerialDescriptor[0], new Function1() { // from class: org.fdroid.index.v1.PermissionV1Serializer$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return PermissionV1Serializer.descriptor$lambda$0((ClassSerialDescriptorBuilder) obj);
        }
    });

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public PermissionV1 deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonDecoder jsonDecoder = decoder instanceof JsonDecoder ? (JsonDecoder) decoder : null;
        if (jsonDecoder == null) {
            throw new IllegalStateException("Can be deserialized only by JSON");
        }
        JsonArray jsonArray = JsonElementKt.getJsonArray(jsonDecoder.decodeJsonElement());
        if (jsonArray.size() < 2) {
            throw new IllegalArgumentException("Invalid array: " + jsonArray);
        }
        return new PermissionV1(JsonElementKt.getJsonPrimitive(jsonArray.get(0)).getContent(), JsonElementKt.getIntOrNull(JsonElementKt.getJsonPrimitive(jsonArray.get(1))));
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, PermissionV1 value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor = JsonArray.Companion.serializer().getDescriptor();
        CompositeEncoder compositeEncoderBeginCollection = encoder.beginCollection(descriptor, 2);
        compositeEncoderBeginCollection.encodeStringElement(getDescriptor(), 0, value.getName());
        compositeEncoderBeginCollection.encodeNullableSerializableElement(getDescriptor(), 1, BuiltinSerializersKt.serializer(IntCompanionObject.INSTANCE), value.getMaxSdk());
        compositeEncoderBeginCollection.endStructure(descriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit descriptor$lambda$0(ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
        List listEmptyList = CollectionsKt.emptyList();
        KSerializer kSerializerSerializer = SerializersKt.serializer(Reflection.typeOf(String.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        buildClassSerialDescriptor.element("name", kSerializerSerializer.getDescriptor(), listEmptyList, false);
        List listEmptyList2 = CollectionsKt.emptyList();
        KSerializer kSerializerSerializer2 = SerializersKt.serializer(Reflection.nullableTypeOf(Integer.class));
        Intrinsics.checkNotNull(kSerializerSerializer2, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        buildClassSerialDescriptor.element("maxSdk", kSerializerSerializer2.getDescriptor(), listEmptyList2, false);
        return Unit.INSTANCE;
    }
}
