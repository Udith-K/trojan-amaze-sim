package kotlinx.serialization.encoding;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;

/* JADX INFO: compiled from: AbstractDecoder.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractDecoder implements Decoder, CompositeDecoder {
    @Override // kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract byte decodeByte();

    @Override // kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract int decodeInt();

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract long decodeLong();

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return true;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract short decodeShort();

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeCollectionSize(SerialDescriptor serialDescriptor) {
        return CompositeDecoder.DefaultImpls.decodeCollectionSize(this, serialDescriptor);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public boolean decodeSequentially() {
        return CompositeDecoder.DefaultImpls.decodeSequentially(this);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public Object decodeSerializableValue(DeserializationStrategy deserializationStrategy) {
        return Decoder.DefaultImpls.decodeSerializableValue(this, deserializationStrategy);
    }

    public Object decodeValue() {
        throw new SerializationException(Reflection.getOrCreateKotlinClass(getClass()) + " can't retrieve untyped values");
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        Object objDecodeValue = decodeValue();
        Intrinsics.checkNotNull(objDecodeValue, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) objDecodeValue).booleanValue();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        Object objDecodeValue = decodeValue();
        Intrinsics.checkNotNull(objDecodeValue, "null cannot be cast to non-null type kotlin.Float");
        return ((Float) objDecodeValue).floatValue();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        Object objDecodeValue = decodeValue();
        Intrinsics.checkNotNull(objDecodeValue, "null cannot be cast to non-null type kotlin.Double");
        return ((Double) objDecodeValue).doubleValue();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        Object objDecodeValue = decodeValue();
        Intrinsics.checkNotNull(objDecodeValue, "null cannot be cast to non-null type kotlin.Char");
        return ((Character) objDecodeValue).charValue();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        Object objDecodeValue = decodeValue();
        Intrinsics.checkNotNull(objDecodeValue, "null cannot be cast to non-null type kotlin.String");
        return (String) objDecodeValue;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        Object objDecodeValue = decodeValue();
        Intrinsics.checkNotNull(objDecodeValue, "null cannot be cast to non-null type kotlin.Int");
        return ((Integer) objDecodeValue).intValue();
    }

    public Object decodeSerializableValue(DeserializationStrategy deserializer, Object obj) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return decodeSerializableValue(deserializer);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final boolean decodeBooleanElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeBoolean();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final byte decodeByteElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeByte();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final short decodeShortElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeShort();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final int decodeIntElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeInt();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final long decodeLongElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeLong();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final float decodeFloatElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeFloat();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final double decodeDoubleElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeDouble();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final char decodeCharElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeChar();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final String decodeStringElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeString();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public Decoder decodeInlineElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeInline(descriptor.getElementDescriptor(i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public Object decodeSerializableElement(SerialDescriptor descriptor, int i, DeserializationStrategy deserializer, Object obj) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return decodeSerializableValue(deserializer, obj);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final Object decodeNullableSerializableElement(SerialDescriptor descriptor, int i, DeserializationStrategy deserializer, Object obj) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (deserializer.getDescriptor().isNullable() || decodeNotNullMark()) ? decodeSerializableValue(deserializer, obj) : decodeNull();
    }
}
