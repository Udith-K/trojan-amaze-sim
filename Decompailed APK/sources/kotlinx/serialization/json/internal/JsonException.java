package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;

/* JADX INFO: compiled from: JsonExceptions.kt */
/* JADX INFO: loaded from: classes2.dex */
public class JsonException extends SerializationException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
