package okhttp3;

import java.io.Closeable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;

/* JADX INFO: compiled from: ResponseBody.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ResponseBody implements Closeable {
    public static final Companion Companion = new Companion(null);

    public abstract long contentLength();

    public abstract BufferedSource source();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Util.closeQuietly(source());
    }

    /* JADX INFO: compiled from: ResponseBody.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, byte[] bArr, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(bArr, mediaType);
        }

        public final ResponseBody create(byte[] bArr, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            return create(new Buffer().write(bArr), mediaType, bArr.length);
        }

        public final ResponseBody create(final BufferedSource bufferedSource, final MediaType mediaType, final long j) {
            Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
            return new ResponseBody() { // from class: okhttp3.ResponseBody$Companion$asResponseBody$1
                @Override // okhttp3.ResponseBody
                public long contentLength() {
                    return j;
                }

                @Override // okhttp3.ResponseBody
                public BufferedSource source() {
                    return bufferedSource;
                }
            };
        }
    }
}
