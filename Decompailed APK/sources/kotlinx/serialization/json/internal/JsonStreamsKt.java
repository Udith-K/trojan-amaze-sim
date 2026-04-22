package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonEncoder;

/* JADX INFO: compiled from: JsonStreams.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JsonStreamsKt {
    public static final void encodeByWriter(Json json, JsonWriter writer, SerializationStrategy serializer, Object obj) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        new StreamingJsonEncoder(writer, json, WriteMode.OBJ, new JsonEncoder[WriteMode.values().length]).encodeSerializableValue(serializer, obj);
    }

    public static final Object decodeByReader(Json json, DeserializationStrategy deserializer, SerialReader reader) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(reader, "reader");
        ReaderJsonLexer readerJsonLexer = new ReaderJsonLexer(reader, null, 2, null);
        Object objDecodeSerializableValue = new StreamingJsonDecoder(json, WriteMode.OBJ, readerJsonLexer, deserializer.getDescriptor(), null).decodeSerializableValue(deserializer);
        readerJsonLexer.expectEof();
        return objDecodeSerializableValue;
    }
}
