package kotlinx.serialization.json;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: JsonElementSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JsonElementSerializersKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void verify(Encoder encoder) {
        asJsonEncoder(encoder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void verify(Decoder decoder) {
        asJsonDecoder(decoder);
    }

    public static final JsonDecoder asJsonDecoder(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "<this>");
        JsonDecoder jsonDecoder = decoder instanceof JsonDecoder ? (JsonDecoder) decoder : null;
        if (jsonDecoder != null) {
            return jsonDecoder;
        }
        throw new IllegalStateException("This serializer can be used only with Json format.Expected Decoder to be JsonDecoder, got " + Reflection.getOrCreateKotlinClass(decoder.getClass()));
    }

    public static final JsonEncoder asJsonEncoder(Encoder encoder) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        JsonEncoder jsonEncoder = encoder instanceof JsonEncoder ? (JsonEncoder) encoder : null;
        if (jsonEncoder != null) {
            return jsonEncoder;
        }
        throw new IllegalStateException("This serializer can be used only with Json format.Expected Encoder to be JsonEncoder, got " + Reflection.getOrCreateKotlinClass(encoder.getClass()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SerialDescriptor defer(Function0 function0) {
        return new SerialDescriptor(function0) { // from class: kotlinx.serialization.json.JsonElementSerializersKt.defer.1
            private final Lazy original$delegate;

            {
                this.original$delegate = LazyKt.lazy(function0);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public List getAnnotations() {
                return SerialDescriptor.DefaultImpls.getAnnotations(this);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isInline() {
                return SerialDescriptor.DefaultImpls.isInline(this);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isNullable() {
                return SerialDescriptor.DefaultImpls.isNullable(this);
            }

            private final SerialDescriptor getOriginal() {
                return (SerialDescriptor) this.original$delegate.getValue();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getSerialName() {
                return getOriginal().getSerialName();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialKind getKind() {
                return getOriginal().getKind();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementsCount() {
                return getOriginal().getElementsCount();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getElementName(int i) {
                return getOriginal().getElementName(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementIndex(String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                return getOriginal().getElementIndex(name);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public List getElementAnnotations(int i) {
                return getOriginal().getElementAnnotations(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialDescriptor getElementDescriptor(int i) {
                return getOriginal().getElementDescriptor(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isElementOptional(int i) {
                return getOriginal().isElementOptional(i);
            }
        };
    }
}
