package kotlinx.serialization.internal;

import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;

/* JADX INFO: compiled from: PrimitiveArraysSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class UIntArraySerializer extends PrimitiveArraySerializer implements KSerializer {
    public static final UIntArraySerializer INSTANCE = new UIntArraySerializer();

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ int collectionSize(Object obj) {
        return m2861collectionSizeajY9A(((UIntArray) obj).m2690unboximpl());
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public /* bridge */ /* synthetic */ Object empty() {
        return UIntArray.m2676boximpl(m2862emptyhP7Qyg());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public /* bridge */ /* synthetic */ Object toBuilder(Object obj) {
        return m2863toBuilderajY9A(((UIntArray) obj).m2690unboximpl());
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public /* bridge */ /* synthetic */ void writeContent(CompositeEncoder compositeEncoder, Object obj, int i) {
        m2864writeContentCPlH8fI(compositeEncoder, ((UIntArray) obj).m2690unboximpl(), i);
    }

    private UIntArraySerializer() {
        super(BuiltinSerializersKt.serializer(UInt.Companion));
    }

    /* JADX INFO: renamed from: collectionSize--ajY-9A, reason: not valid java name */
    protected int m2861collectionSizeajY9A(int[] collectionSize) {
        Intrinsics.checkNotNullParameter(collectionSize, "$this$collectionSize");
        return UIntArray.m2683getSizeimpl(collectionSize);
    }

    /* JADX INFO: renamed from: toBuilder--ajY-9A, reason: not valid java name */
    protected UIntArrayBuilder m2863toBuilderajY9A(int[] toBuilder) {
        Intrinsics.checkNotNullParameter(toBuilder, "$this$toBuilder");
        return new UIntArrayBuilder(toBuilder, null);
    }

    /* JADX INFO: renamed from: empty--hP7Qyg, reason: not valid java name */
    protected int[] m2862emptyhP7Qyg() {
        return UIntArray.m2677constructorimpl(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.CollectionLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder decoder, int i, UIntArrayBuilder builder, boolean z) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.m2859appendWZ4Q5Ns$kotlinx_serialization_core(UInt.m2671constructorimpl(decoder.decodeInlineElement(getDescriptor(), i).decodeInt()));
    }

    /* JADX INFO: renamed from: writeContent-CPlH8fI, reason: not valid java name */
    protected void m2864writeContentCPlH8fI(CompositeEncoder encoder, int[] content, int i) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(content, "content");
        for (int i2 = 0; i2 < i; i2++) {
            encoder.encodeInlineElement(getDescriptor(), i2).encodeInt(UIntArray.m2682getpVg5ArA(content, i2));
        }
    }
}
