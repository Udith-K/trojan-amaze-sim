package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonPrimitive;

/* JADX INFO: compiled from: TreeJsonDecoder.kt */
/* JADX INFO: loaded from: classes2.dex */
final class JsonPrimitiveDecoder extends AbstractJsonTreeDecoder {
    private final JsonPrimitive value;

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonPrimitiveDecoder(Json json, JsonPrimitive value) {
        super(json, value, null);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        pushTag("primitive");
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonPrimitive getValue() {
        return this.value;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    protected JsonElement currentElement(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (tag != "primitive") {
            throw new IllegalArgumentException("This input can only handle primitives with 'primitive' tag");
        }
        return getValue();
    }
}
