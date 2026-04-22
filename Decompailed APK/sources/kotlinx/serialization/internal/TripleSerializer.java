package kotlinx.serialization.internal;

import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: Tuples.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class TripleSerializer implements KSerializer {
    private final KSerializer aSerializer;
    private final KSerializer bSerializer;
    private final KSerializer cSerializer;
    private final SerialDescriptor descriptor;

    public TripleSerializer(KSerializer aSerializer, KSerializer bSerializer, KSerializer cSerializer) {
        Intrinsics.checkNotNullParameter(aSerializer, "aSerializer");
        Intrinsics.checkNotNullParameter(bSerializer, "bSerializer");
        Intrinsics.checkNotNullParameter(cSerializer, "cSerializer");
        this.aSerializer = aSerializer;
        this.bSerializer = bSerializer;
        this.cSerializer = cSerializer;
        this.descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlin.Triple", new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.internal.TripleSerializer$descriptor$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ClassSerialDescriptorBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(ClassSerialDescriptorBuilder buildClassSerialDescriptor) {
                Intrinsics.checkNotNullParameter(buildClassSerialDescriptor, "$this$buildClassSerialDescriptor");
                ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "first", this.this$0.aSerializer.getDescriptor(), null, false, 12, null);
                ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "second", this.this$0.bSerializer.getDescriptor(), null, false, 12, null);
                ClassSerialDescriptorBuilder.element$default(buildClassSerialDescriptor, "third", this.this$0.cSerializer.getDescriptor(), null, false, 12, null);
            }
        });
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Triple value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(getDescriptor());
        compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 0, this.aSerializer, value.getFirst());
        compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 1, this.bSerializer, value.getSecond());
        compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 2, this.cSerializer, value.getThird());
        compositeEncoderBeginStructure.endStructure(getDescriptor());
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Triple deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(getDescriptor());
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            return decodeSequentially(compositeDecoderBeginStructure);
        }
        return decodeStructure(compositeDecoderBeginStructure);
    }

    private final Triple decodeSequentially(CompositeDecoder compositeDecoder) {
        Object objDecodeSerializableElement$default = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 0, this.aSerializer, null, 8, null);
        Object objDecodeSerializableElement$default2 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 1, this.bSerializer, null, 8, null);
        Object objDecodeSerializableElement$default3 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 2, this.cSerializer, null, 8, null);
        compositeDecoder.endStructure(getDescriptor());
        return new Triple(objDecodeSerializableElement$default, objDecodeSerializableElement$default2, objDecodeSerializableElement$default3);
    }

    private final Triple decodeStructure(CompositeDecoder compositeDecoder) {
        Object objDecodeSerializableElement$default = TuplesKt.NULL;
        Object objDecodeSerializableElement$default2 = TuplesKt.NULL;
        Object objDecodeSerializableElement$default3 = TuplesKt.NULL;
        while (true) {
            int iDecodeElementIndex = compositeDecoder.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex == -1) {
                compositeDecoder.endStructure(getDescriptor());
                if (objDecodeSerializableElement$default != TuplesKt.NULL) {
                    if (objDecodeSerializableElement$default2 != TuplesKt.NULL) {
                        if (objDecodeSerializableElement$default3 == TuplesKt.NULL) {
                            throw new SerializationException("Element 'third' is missing");
                        }
                        return new Triple(objDecodeSerializableElement$default, objDecodeSerializableElement$default2, objDecodeSerializableElement$default3);
                    }
                    throw new SerializationException("Element 'second' is missing");
                }
                throw new SerializationException("Element 'first' is missing");
            }
            if (iDecodeElementIndex == 0) {
                objDecodeSerializableElement$default = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 0, this.aSerializer, null, 8, null);
            } else if (iDecodeElementIndex == 1) {
                objDecodeSerializableElement$default2 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 1, this.bSerializer, null, 8, null);
            } else if (iDecodeElementIndex == 2) {
                objDecodeSerializableElement$default3 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 2, this.cSerializer, null, 8, null);
            } else {
                throw new SerializationException("Unexpected index " + iDecodeElementIndex);
            }
        }
    }
}
