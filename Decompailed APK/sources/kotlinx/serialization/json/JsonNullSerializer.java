package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.internal.JsonDecodingException;

/* JADX INFO: compiled from: JsonElementSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JsonNullSerializer implements KSerializer {
    public static final JsonNullSerializer INSTANCE = new JsonNullSerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.buildSerialDescriptor$default("kotlinx.serialization.json.JsonNull", SerialKind.ENUM.INSTANCE, new SerialDescriptor[0], null, 8, null);

    private JsonNullSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, JsonNull value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.verify(encoder);
        encoder.encodeNull();
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public JsonNull deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonElementSerializersKt.verify(decoder);
        if (decoder.decodeNotNullMark()) {
            throw new JsonDecodingException("Expected 'null' literal");
        }
        decoder.decodeNull();
        return JsonNull.INSTANCE;
    }
}
