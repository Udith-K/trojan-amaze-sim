package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: Decoding.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Decoder {
    CompositeDecoder beginStructure(SerialDescriptor serialDescriptor);

    boolean decodeBoolean();

    byte decodeByte();

    char decodeChar();

    double decodeDouble();

    int decodeEnum(SerialDescriptor serialDescriptor);

    float decodeFloat();

    Decoder decodeInline(SerialDescriptor serialDescriptor);

    int decodeInt();

    long decodeLong();

    boolean decodeNotNullMark();

    Void decodeNull();

    Object decodeSerializableValue(DeserializationStrategy deserializationStrategy);

    short decodeShort();

    String decodeString();

    /* JADX INFO: compiled from: Decoding.kt */
    public static final class DefaultImpls {
        public static Object decodeSerializableValue(Decoder decoder, DeserializationStrategy deserializer) {
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            return deserializer.deserialize(decoder);
        }
    }
}
