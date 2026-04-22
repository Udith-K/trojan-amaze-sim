package kotlinx.serialization.internal;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: CollectionSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class PrimitiveArraySerializer extends CollectionLikeSerializer {
    private final SerialDescriptor descriptor;

    protected abstract Object empty();

    protected abstract void writeContent(CompositeEncoder compositeEncoder, Object obj, int i);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrimitiveArraySerializer(KSerializer primitiveSerializer) {
        super(primitiveSerializer, null);
        Intrinsics.checkNotNullParameter(primitiveSerializer, "primitiveSerializer");
        this.descriptor = new PrimitiveArrayDescriptor(primitiveSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.CollectionLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final int builderSize(PrimitiveArrayBuilder primitiveArrayBuilder) {
        Intrinsics.checkNotNullParameter(primitiveArrayBuilder, "<this>");
        return primitiveArrayBuilder.getPosition$kotlinx_serialization_core();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final Object toResult(PrimitiveArrayBuilder primitiveArrayBuilder) {
        Intrinsics.checkNotNullParameter(primitiveArrayBuilder, "<this>");
        return primitiveArrayBuilder.build$kotlinx_serialization_core();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final void checkCapacity(PrimitiveArrayBuilder primitiveArrayBuilder, int i) {
        Intrinsics.checkNotNullParameter(primitiveArrayBuilder, "<this>");
        primitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core(i);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    protected final Iterator collectionIterator(Object obj) {
        throw new IllegalStateException("This method lead to boxing and must not be used, use writeContents instead");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.CollectionLikeSerializer
    public final void insert(PrimitiveArrayBuilder primitiveArrayBuilder, int i, Object obj) {
        Intrinsics.checkNotNullParameter(primitiveArrayBuilder, "<this>");
        throw new IllegalStateException("This method lead to boxing and must not be used, use Builder.append instead");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final PrimitiveArrayBuilder builder() {
        return (PrimitiveArrayBuilder) toBuilder(empty());
    }

    @Override // kotlinx.serialization.internal.CollectionLikeSerializer, kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, Object obj) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        int iCollectionSize = collectionSize(obj);
        SerialDescriptor serialDescriptor = this.descriptor;
        CompositeEncoder compositeEncoderBeginCollection = encoder.beginCollection(serialDescriptor, iCollectionSize);
        writeContent(compositeEncoderBeginCollection, obj, iCollectionSize);
        compositeEncoderBeginCollection.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer, kotlinx.serialization.DeserializationStrategy
    public final Object deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return merge(decoder, null);
    }
}
