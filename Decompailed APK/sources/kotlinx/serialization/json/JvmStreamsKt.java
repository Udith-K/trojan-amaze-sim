package kotlinx.serialization.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.internal.JavaStreamSerialReader;
import kotlinx.serialization.json.internal.JsonStreamsKt;
import kotlinx.serialization.json.internal.JsonToJavaStreamWriter;

/* JADX INFO: compiled from: JvmStreams.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JvmStreamsKt {
    public static final void encodeToStream(Json json, SerializationStrategy serializer, Object obj, OutputStream stream) throws IOException {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(stream, "stream");
        JsonToJavaStreamWriter jsonToJavaStreamWriter = new JsonToJavaStreamWriter(stream);
        try {
            JsonStreamsKt.encodeByWriter(json, jsonToJavaStreamWriter, serializer, obj);
        } finally {
            jsonToJavaStreamWriter.release();
        }
    }

    public static final Object decodeFromStream(Json json, DeserializationStrategy deserializer, InputStream stream) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(stream, "stream");
        return JsonStreamsKt.decodeByReader(json, deserializer, new JavaStreamSerialReader(stream, null, 2, null));
    }
}
