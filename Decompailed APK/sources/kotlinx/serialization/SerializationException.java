package kotlinx.serialization;

/* JADX INFO: compiled from: SerializationExceptions.kt */
/* JADX INFO: loaded from: classes2.dex */
public class SerializationException extends IllegalArgumentException {
    public SerializationException(String str) {
        super(str);
    }

    public SerializationException(String str, Throwable th) {
        super(str, th);
    }
}
