package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: Encoding.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Encoder {
    CompositeEncoder beginCollection(SerialDescriptor serialDescriptor, int i);

    CompositeEncoder beginStructure(SerialDescriptor serialDescriptor);

    void encodeBoolean(boolean z);

    void encodeByte(byte b);

    void encodeChar(char c);

    void encodeDouble(double d);

    void encodeEnum(SerialDescriptor serialDescriptor, int i);

    void encodeFloat(float f);

    Encoder encodeInline(SerialDescriptor serialDescriptor);

    void encodeInt(int i);

    void encodeLong(long j);

    void encodeNotNullMark();

    void encodeNull();

    void encodeSerializableValue(SerializationStrategy serializationStrategy, Object obj);

    void encodeShort(short s);

    void encodeString(String str);

    SerializersModule getSerializersModule();

    /* JADX INFO: compiled from: Encoding.kt */
    public static final class DefaultImpls {
        public static void encodeNotNullMark(Encoder encoder) {
        }

        public static CompositeEncoder beginCollection(Encoder encoder, SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return encoder.beginStructure(descriptor);
        }

        public static void encodeSerializableValue(Encoder encoder, SerializationStrategy serializer, Object obj) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            serializer.serialize(encoder, obj);
        }

        public static void encodeNullableSerializableValue(Encoder encoder, SerializationStrategy serializer, Object obj) {
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            if (serializer.getDescriptor().isNullable()) {
                encoder.encodeSerializableValue(serializer, obj);
            } else if (obj == null) {
                encoder.encodeNull();
            } else {
                encoder.encodeNotNullMark();
                encoder.encodeSerializableValue(serializer, obj);
            }
        }
    }
}
