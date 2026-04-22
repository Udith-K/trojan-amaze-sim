package kotlinx.serialization.internal;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: ObjectSerializer.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ObjectSerializer implements KSerializer {
    private List _annotations;
    private final Lazy descriptor$delegate;
    private final Object objectInstance;

    public ObjectSerializer(final String serialName, Object objectInstance) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(objectInstance, "objectInstance");
        this.objectInstance = objectInstance;
        this._annotations = CollectionsKt.emptyList();
        this.descriptor$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: kotlinx.serialization.internal.ObjectSerializer$descriptor$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SerialDescriptor invoke() {
                final ObjectSerializer objectSerializer = this;
                return SerialDescriptorsKt.buildSerialDescriptor(serialName, StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.internal.ObjectSerializer$descriptor$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((ClassSerialDescriptorBuilder) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(ClassSerialDescriptorBuilder buildSerialDescriptor) {
                        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
                        buildSerialDescriptor.setAnnotations(objectSerializer._annotations);
                    }
                });
            }
        });
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return (SerialDescriptor) this.descriptor$delegate.getValue();
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        encoder.beginStructure(getDescriptor()).endStructure(getDescriptor());
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor);
        int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
        if (iDecodeElementIndex == -1) {
            Unit unit = Unit.INSTANCE;
            compositeDecoderBeginStructure.endStructure(descriptor);
            return this.objectInstance;
        }
        throw new SerializationException("Unexpected index " + iDecodeElementIndex);
    }
}
