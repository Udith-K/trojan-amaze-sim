package kotlinx.serialization.json;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: JsonElementSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JsonElementSerializer implements KSerializer {
    public static final JsonElementSerializer INSTANCE = new JsonElementSerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.buildSerialDescriptor("kotlinx.serialization.json.JsonElement", PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.json.JsonElementSerializer$descriptor$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ClassSerialDescriptorBuilder) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ClassSerialDescriptorBuilder buildSerialDescriptor) {
            Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
            ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonPrimitive", JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$descriptor$1.1
                @Override // kotlin.jvm.functions.Function0
                public final SerialDescriptor invoke() {
                    return JsonPrimitiveSerializer.INSTANCE.getDescriptor();
                }
            }), null, false, 12, null);
            ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonNull", JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$descriptor$1.2
                @Override // kotlin.jvm.functions.Function0
                public final SerialDescriptor invoke() {
                    return JsonNullSerializer.INSTANCE.getDescriptor();
                }
            }), null, false, 12, null);
            ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonLiteral", JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$descriptor$1.3
                @Override // kotlin.jvm.functions.Function0
                public final SerialDescriptor invoke() {
                    return JsonLiteralSerializer.INSTANCE.getDescriptor();
                }
            }), null, false, 12, null);
            ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonObject", JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$descriptor$1.4
                @Override // kotlin.jvm.functions.Function0
                public final SerialDescriptor invoke() {
                    return JsonObjectSerializer.INSTANCE.getDescriptor();
                }
            }), null, false, 12, null);
            ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonArray", JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$descriptor$1.5
                @Override // kotlin.jvm.functions.Function0
                public final SerialDescriptor invoke() {
                    return JsonArraySerializer.INSTANCE.getDescriptor();
                }
            }), null, false, 12, null);
        }
    });

    private JsonElementSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, JsonElement value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.verify(encoder);
        if (value instanceof JsonPrimitive) {
            encoder.encodeSerializableValue(JsonPrimitiveSerializer.INSTANCE, value);
        } else if (value instanceof JsonObject) {
            encoder.encodeSerializableValue(JsonObjectSerializer.INSTANCE, value);
        } else if (value instanceof JsonArray) {
            encoder.encodeSerializableValue(JsonArraySerializer.INSTANCE, value);
        }
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public JsonElement deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return JsonElementSerializersKt.asJsonDecoder(decoder).decodeJsonElement();
    }
}
