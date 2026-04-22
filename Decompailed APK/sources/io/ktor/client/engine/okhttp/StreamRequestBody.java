package io.ktor.client.engine.okhttp;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.BlockingKt;
import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/* JADX INFO: compiled from: StreamRequestBody.kt */
/* JADX INFO: loaded from: classes.dex */
public final class StreamRequestBody extends RequestBody {
    private final Function0 block;
    private final Long contentLength;

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return null;
    }

    @Override // okhttp3.RequestBody
    public boolean isOneShot() {
        return true;
    }

    public StreamRequestBody(Long l, Function0 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.contentLength = l;
        this.block = block;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink sink) throws IOException {
        Long lValueOf;
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            Throwable th = null;
            Source source = Okio.source(BlockingKt.toInputStream$default((ByteReadChannel) this.block.invoke(), null, 1, null));
            try {
                lValueOf = Long.valueOf(sink.writeAll(source));
                if (source != null) {
                    try {
                        source.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Throwable th3) {
                if (source != null) {
                    try {
                        source.close();
                    } catch (Throwable th4) {
                        ExceptionsKt.addSuppressed(th3, th4);
                    }
                }
                th = th3;
                lValueOf = null;
            }
            if (th != null) {
                throw th;
            }
            Intrinsics.checkNotNull(lValueOf);
        } catch (IOException e) {
            throw e;
        } catch (Throwable th5) {
            throw new StreamAdapterIOException(th5);
        }
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        Long l = this.contentLength;
        if (l != null) {
            return l.longValue();
        }
        return -1L;
    }
}
