package kotlinx.serialization.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;

/* JADX INFO: compiled from: Tagged.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class TaggedDecoder implements Decoder, CompositeDecoder {
    private boolean flag;
    private final ArrayList tagStack = new ArrayList();

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract boolean decodeNotNullMark();

    @Override // kotlinx.serialization.encoding.Decoder
    public final Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract Object decodeSerializableValue(DeserializationStrategy deserializationStrategy);

    protected abstract boolean decodeTaggedBoolean(Object obj);

    protected abstract byte decodeTaggedByte(Object obj);

    protected abstract char decodeTaggedChar(Object obj);

    protected abstract double decodeTaggedDouble(Object obj);

    protected abstract int decodeTaggedEnum(Object obj, SerialDescriptor serialDescriptor);

    protected abstract float decodeTaggedFloat(Object obj);

    protected abstract int decodeTaggedInt(Object obj);

    protected abstract long decodeTaggedLong(Object obj);

    protected abstract short decodeTaggedShort(Object obj);

    protected abstract String decodeTaggedString(Object obj);

    protected abstract Object getTag(SerialDescriptor serialDescriptor, int i);

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeCollectionSize(SerialDescriptor serialDescriptor) {
        return CompositeDecoder.DefaultImpls.decodeCollectionSize(this, serialDescriptor);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public boolean decodeSequentially() {
        return CompositeDecoder.DefaultImpls.decodeSequentially(this);
    }

    protected Decoder decodeTaggedInline(Object obj, SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        pushTag(obj);
        return this;
    }

    protected Object decodeSerializableValue(DeserializationStrategy deserializer, Object obj) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return decodeSerializableValue(deserializer);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final Decoder decodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedInline(popTag(), descriptor);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final boolean decodeBoolean() {
        return decodeTaggedBoolean(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final byte decodeByte() {
        return decodeTaggedByte(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final short decodeShort() {
        return decodeTaggedShort(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final int decodeInt() {
        return decodeTaggedInt(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final long decodeLong() {
        return decodeTaggedLong(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final float decodeFloat() {
        return decodeTaggedFloat(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final double decodeDouble() {
        return decodeTaggedDouble(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final char decodeChar() {
        return decodeTaggedChar(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final String decodeString() {
        return decodeTaggedString(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return decodeTaggedEnum(popTag(), enumDescriptor);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final boolean decodeBooleanElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedBoolean(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final byte decodeByteElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedByte(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final short decodeShortElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedShort(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final int decodeIntElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedInt(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final long decodeLongElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedLong(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final float decodeFloatElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedFloat(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final double decodeDoubleElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedDouble(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final char decodeCharElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedChar(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final String decodeStringElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedString(getTag(descriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final Decoder decodeInlineElement(SerialDescriptor descriptor, int i) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedInline(getTag(descriptor, i), descriptor.getElementDescriptor(i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final Object decodeSerializableElement(SerialDescriptor descriptor, int i, final DeserializationStrategy deserializer, final Object obj) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return tagBlock(getTag(descriptor, i), new Function0() { // from class: kotlinx.serialization.internal.TaggedDecoder.decodeSerializableElement.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TaggedDecoder.this.decodeSerializableValue(deserializer, obj);
            }
        });
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final Object decodeNullableSerializableElement(SerialDescriptor descriptor, int i, final DeserializationStrategy deserializer, final Object obj) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return tagBlock(getTag(descriptor, i), new Function0() { // from class: kotlinx.serialization.internal.TaggedDecoder.decodeNullableSerializableElement.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                if (TaggedDecoder.this.decodeNotNullMark()) {
                    return TaggedDecoder.this.decodeSerializableValue(deserializer, obj);
                }
                return TaggedDecoder.this.decodeNull();
            }
        });
    }

    private final Object tagBlock(Object obj, Function0 function0) {
        pushTag(obj);
        Object objInvoke = function0.invoke();
        if (!this.flag) {
            popTag();
        }
        this.flag = false;
        return objInvoke;
    }

    protected final Object getCurrentTagOrNull() {
        return CollectionsKt.lastOrNull((List) this.tagStack);
    }

    protected final void pushTag(Object obj) {
        this.tagStack.add(obj);
    }

    protected final Object popTag() {
        ArrayList arrayList = this.tagStack;
        Object objRemove = arrayList.remove(CollectionsKt.getLastIndex(arrayList));
        this.flag = true;
        return objRemove;
    }
}
