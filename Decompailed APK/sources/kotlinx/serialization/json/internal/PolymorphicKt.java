package kotlinx.serialization.json.internal;

import ch.qos.logback.core.CoreConstants;
import java.lang.annotation.Annotation;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonClassDiscriminator;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;

/* JADX INFO: compiled from: Polymorphic.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class PolymorphicKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateIfSealed(SerializationStrategy serializationStrategy, SerializationStrategy serializationStrategy2, String str) {
    }

    public static final void checkKind(SerialKind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (kind instanceof SerialKind.ENUM) {
            throw new IllegalStateException("Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead");
        }
        if (kind instanceof PrimitiveKind) {
            throw new IllegalStateException("Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead");
        }
        if (kind instanceof PolymorphicKind) {
            throw new IllegalStateException("Actual serializer for polymorphic cannot be polymorphic itself");
        }
    }

    public static final Object decodeSerializableValuePolymorphic(JsonDecoder jsonDecoder, DeserializationStrategy deserializer) {
        JsonPrimitive jsonPrimitive;
        Intrinsics.checkNotNullParameter(jsonDecoder, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        if (!(deserializer instanceof AbstractPolymorphicSerializer) || jsonDecoder.getJson().getConfiguration().getUseArrayPolymorphism()) {
            return deserializer.deserialize(jsonDecoder);
        }
        String strClassDiscriminator = classDiscriminator(deserializer.getDescriptor(), jsonDecoder.getJson());
        JsonElement jsonElementDecodeJsonElement = jsonDecoder.decodeJsonElement();
        SerialDescriptor descriptor = deserializer.getDescriptor();
        if (jsonElementDecodeJsonElement instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) jsonElementDecodeJsonElement;
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) strClassDiscriminator);
            String content = (jsonElement == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement)) == null) ? null : jsonPrimitive.getContent();
            DeserializationStrategy deserializationStrategyFindPolymorphicSerializerOrNull = ((AbstractPolymorphicSerializer) deserializer).findPolymorphicSerializerOrNull(jsonDecoder, content);
            if (deserializationStrategyFindPolymorphicSerializerOrNull == null) {
                throwSerializerNotFound(content, jsonObject);
                throw new KotlinNothingValueException();
            }
            return TreeJsonDecoderKt.readPolymorphicJson(jsonDecoder.getJson(), strClassDiscriminator, jsonObject, deserializationStrategyFindPolymorphicSerializerOrNull);
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class) + " as the serialized body of " + descriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementDecodeJsonElement.getClass()));
    }

    public static final Void throwSerializerNotFound(String str, JsonObject jsonTree) {
        String str2;
        Intrinsics.checkNotNullParameter(jsonTree, "jsonTree");
        if (str == null) {
            str2 = "missing class discriminator ('null')";
        } else {
            str2 = "class discriminator '" + str + CoreConstants.SINGLE_QUOTE_CHAR;
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Polymorphic serializer was not found for " + str2, jsonTree.toString());
    }

    public static final String classDiscriminator(SerialDescriptor serialDescriptor, Json json) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        for (Annotation annotation : serialDescriptor.getAnnotations()) {
            if (annotation instanceof JsonClassDiscriminator) {
                return ((JsonClassDiscriminator) annotation).discriminator();
            }
        }
        return json.getConfiguration().getClassDiscriminator();
    }
}
