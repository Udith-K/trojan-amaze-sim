package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JsonExceptions.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JsonEncodingException extends JsonException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonEncodingException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
