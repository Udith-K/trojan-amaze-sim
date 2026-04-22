package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: Decoding.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface CompositeDecoder {
    boolean decodeBooleanElement(SerialDescriptor serialDescriptor, int i);

    byte decodeByteElement(SerialDescriptor serialDescriptor, int i);

    char decodeCharElement(SerialDescriptor serialDescriptor, int i);

    int decodeCollectionSize(SerialDescriptor serialDescriptor);

    double decodeDoubleElement(SerialDescriptor serialDescriptor, int i);

    int decodeElementIndex(SerialDescriptor serialDescriptor);

    float decodeFloatElement(SerialDescriptor serialDescriptor, int i);

    Decoder decodeInlineElement(SerialDescriptor serialDescriptor, int i);

    int decodeIntElement(SerialDescriptor serialDescriptor, int i);

    long decodeLongElement(SerialDescriptor serialDescriptor, int i);

    Object decodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy deserializationStrategy, Object obj);

    boolean decodeSequentially();

    Object decodeSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy deserializationStrategy, Object obj);

    short decodeShortElement(SerialDescriptor serialDescriptor, int i);

    String decodeStringElement(SerialDescriptor serialDescriptor, int i);

    void endStructure(SerialDescriptor serialDescriptor);

    SerializersModule getSerializersModule();

    /* JADX INFO: compiled from: Decoding.kt */
    public static final class DefaultImpls {
        public static int decodeCollectionSize(CompositeDecoder compositeDecoder, SerialDescriptor descriptor) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return -1;
        }

        public static boolean decodeSequentially(CompositeDecoder compositeDecoder) {
            return false;
        }

        public static /* synthetic */ Object decodeSerializableElement$default(CompositeDecoder compositeDecoder, SerialDescriptor serialDescriptor, int i, DeserializationStrategy deserializationStrategy, Object obj, int i2, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeSerializableElement");
            }
            if ((i2 & 8) != 0) {
                obj = null;
            }
            return compositeDecoder.decodeSerializableElement(serialDescriptor, i, deserializationStrategy, obj);
        }
    }
}
