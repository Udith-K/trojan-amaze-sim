package kotlinx.serialization.json.internal;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;

/* JADX INFO: compiled from: TreeJsonDecoder.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class TreeJsonDecoderKt {
    public static final Object readJson(Json json, JsonElement element, DeserializationStrategy deserializer) {
        Decoder jsonPrimitiveDecoder;
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        if (element instanceof JsonObject) {
            jsonPrimitiveDecoder = new JsonTreeDecoder(json, (JsonObject) element, null, null, 12, null);
        } else if (element instanceof JsonArray) {
            jsonPrimitiveDecoder = new JsonTreeListDecoder(json, (JsonArray) element);
        } else {
            if (!(element instanceof JsonLiteral ? true : Intrinsics.areEqual(element, JsonNull.INSTANCE))) {
                throw new NoWhenBranchMatchedException();
            }
            jsonPrimitiveDecoder = new JsonPrimitiveDecoder(json, (JsonPrimitive) element);
        }
        return jsonPrimitiveDecoder.decodeSerializableValue(deserializer);
    }

    public static final Object readPolymorphicJson(Json json, String discriminator, JsonObject element, DeserializationStrategy deserializer) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(discriminator, "discriminator");
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return new JsonTreeDecoder(json, element, discriminator, deserializer.getDescriptor()).decodeSerializableValue(deserializer);
    }
}
