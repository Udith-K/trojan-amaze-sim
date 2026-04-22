package kotlinx.serialization.json.internal;

/* JADX INFO: compiled from: JsonStreams.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface JsonWriter {
    void write(String str);

    void writeChar(char c);

    void writeLong(long j);

    void writeQuoted(String str);
}
