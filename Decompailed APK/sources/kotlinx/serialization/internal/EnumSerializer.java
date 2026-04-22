package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: Enums.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class EnumSerializer implements KSerializer {
    private final Lazy descriptor$delegate;
    private SerialDescriptor overriddenDescriptor;
    private final Enum[] values;

    public EnumSerializer(final String serialName, Enum[] values) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(values, "values");
        this.values = values;
        this.descriptor$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.serialization.internal.EnumSerializer$descriptor$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SerialDescriptor invoke() {
                SerialDescriptor serialDescriptor = this.this$0.overriddenDescriptor;
                return serialDescriptor == null ? this.this$0.createUnmarkedDescriptor(serialName) : serialDescriptor;
            }
        });
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return (SerialDescriptor) this.descriptor$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SerialDescriptor createUnmarkedDescriptor(String str) {
        EnumDescriptor enumDescriptor = new EnumDescriptor(str, this.values.length);
        for (Enum r0 : this.values) {
            PluginGeneratedSerialDescriptor.addElement$default(enumDescriptor, r0.name(), false, 2, null);
        }
        return enumDescriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Enum value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        int iIndexOf = ArraysKt.indexOf(this.values, value);
        if (iIndexOf == -1) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            sb.append(" is not a valid enum ");
            sb.append(getDescriptor().getSerialName());
            sb.append(", must be one of ");
            String string = Arrays.toString(this.values);
            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
            sb.append(string);
            throw new SerializationException(sb.toString());
        }
        encoder.encodeEnum(getDescriptor(), iIndexOf);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Enum deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        int iDecodeEnum = decoder.decodeEnum(getDescriptor());
        if (iDecodeEnum >= 0) {
            Enum[] enumArr = this.values;
            if (iDecodeEnum < enumArr.length) {
                return enumArr[iDecodeEnum];
            }
        }
        throw new SerializationException(iDecodeEnum + " is not among valid " + getDescriptor().getSerialName() + " enum values, values size is " + this.values.length);
    }

    public String toString() {
        return "kotlinx.serialization.internal.EnumSerializer<" + getDescriptor().getSerialName() + '>';
    }
}
