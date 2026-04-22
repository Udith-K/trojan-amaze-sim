package kotlinx.serialization;

/* JADX INFO: compiled from: SerializationExceptions.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class UnknownFieldException extends SerializationException {
    public UnknownFieldException(String str) {
        super(str);
    }

    public UnknownFieldException(int i) {
        this("An unknown field for index " + i);
    }
}
