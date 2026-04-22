package kotlinx.serialization.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: Primitives.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CharSerializer implements KSerializer {
    public static final CharSerializer INSTANCE = new CharSerializer();
    private static final SerialDescriptor descriptor = new PrimitiveSerialDescriptor("kotlin.Char", PrimitiveKind.CHAR.INSTANCE);

    private CharSerializer() {
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder encoder, Object obj) {
        serialize(encoder, ((Character) obj).charValue());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, char c) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        encoder.encodeChar(c);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Character deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return Character.valueOf(decoder.decodeChar());
    }
}
