package kotlin.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IOStreams.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ByteStreamsKt {
    public static final long copyTo(InputStream inputStream, OutputStream out, int i) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        byte[] bArr = new byte[i];
        int i2 = inputStream.read(bArr);
        long j = 0;
        while (i2 >= 0) {
            out.write(bArr, 0, i2);
            j += (long) i2;
            i2 = inputStream.read(bArr);
        }
        return j;
    }
}
