package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: Encoding.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface CompositeEncoder {

    /* JADX INFO: compiled from: Encoding.kt */
    public static final class DefaultImpls {
        public static boolean shouldEncodeElementDefault(CompositeEncoder compositeEncoder, SerialDescriptor descriptor, int i) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return true;
        }
    }

    void encodeBooleanElement(SerialDescriptor serialDescriptor, int i, boolean z);

    void encodeByteElement(SerialDescriptor serialDescriptor, int i, byte b);

    void encodeCharElement(SerialDescriptor serialDescriptor, int i, char c);

    void encodeDoubleElement(SerialDescriptor serialDescriptor, int i, double d);

    void encodeFloatElement(SerialDescriptor serialDescriptor, int i, float f);

    Encoder encodeInlineElement(SerialDescriptor serialDescriptor, int i);

    void encodeIntElement(SerialDescriptor serialDescriptor, int i, int i2);

    void encodeLongElement(SerialDescriptor serialDescriptor, int i, long j);

    void encodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, SerializationStrategy serializationStrategy, Object obj);

    void encodeSerializableElement(SerialDescriptor serialDescriptor, int i, SerializationStrategy serializationStrategy, Object obj);

    void encodeShortElement(SerialDescriptor serialDescriptor, int i, short s);

    void encodeStringElement(SerialDescriptor serialDescriptor, int i, String str);

    void endStructure(SerialDescriptor serialDescriptor);

    boolean shouldEncodeElementDefault(SerialDescriptor serialDescriptor, int i);
}
