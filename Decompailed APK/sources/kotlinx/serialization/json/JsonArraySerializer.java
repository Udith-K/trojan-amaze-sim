package kotlinx.serialization.json;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: JsonElementSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JsonArraySerializer implements KSerializer {
    public static final JsonArraySerializer INSTANCE = new JsonArraySerializer();
    private static final SerialDescriptor descriptor = JsonArrayDescriptor.INSTANCE;

    private JsonArraySerializer() {
    }

    /* JADX INFO: compiled from: JsonElementSerializers.kt */
    private static final class JsonArrayDescriptor implements SerialDescriptor {
        public static final JsonArrayDescriptor INSTANCE = new JsonArrayDescriptor();
        private static final String serialName = "kotlinx.serialization.json.JsonArray";
        private final /* synthetic */ SerialDescriptor $$delegate_0 = BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE).getDescriptor();

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public List getAnnotations() {
            return this.$$delegate_0.getAnnotations();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public List getElementAnnotations(int i) {
            return this.$$delegate_0.getElementAnnotations(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public SerialDescriptor getElementDescriptor(int i) {
            return this.$$delegate_0.getElementDescriptor(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public int getElementIndex(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return this.$$delegate_0.getElementIndex(name);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public String getElementName(int i) {
            return this.$$delegate_0.getElementName(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public int getElementsCount() {
            return this.$$delegate_0.getElementsCount();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public SerialKind getKind() {
            return this.$$delegate_0.getKind();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isElementOptional(int i) {
            return this.$$delegate_0.isElementOptional(i);
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isInline() {
            return this.$$delegate_0.isInline();
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public boolean isNullable() {
            return this.$$delegate_0.isNullable();
        }

        private JsonArrayDescriptor() {
        }

        @Override // kotlinx.serialization.descriptors.SerialDescriptor
        public String getSerialName() {
            return serialName;
        }
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, JsonArray value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.verify(encoder);
        BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE).serialize(encoder, value);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public JsonArray deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonElementSerializersKt.verify(decoder);
        return new JsonArray((List) BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE).deserialize(decoder));
    }
}
