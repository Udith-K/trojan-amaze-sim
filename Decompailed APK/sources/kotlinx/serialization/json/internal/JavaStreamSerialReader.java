package kotlinx.serialization.json.internal;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: JvmJsonStreams.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JavaStreamSerialReader implements SerialReader {
    private final InputStreamReader reader;

    public JavaStreamSerialReader(InputStream stream, Charset charset) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        Intrinsics.checkNotNullParameter(charset, "charset");
        this.reader = new InputStreamReader(stream, charset);
    }

    public /* synthetic */ JavaStreamSerialReader(InputStream inputStream, Charset charset, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(inputStream, (i & 2) != 0 ? Charsets.UTF_8 : charset);
    }

    @Override // kotlinx.serialization.json.internal.SerialReader
    public int read(char[] buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        return this.reader.read(buffer, i, i2);
    }
}
