package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: Blocking.kt */
/* JADX INFO: loaded from: classes.dex */
final class InputAdapter extends InputStream {
    private final ByteReadChannel channel;
    private final CompletableJob context;
    private final InputAdapter$loop$1 loop;
    private byte[] single;

    public InputAdapter(Job job, ByteReadChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        this.channel = channel;
        this.context = JobKt.Job(job);
        this.loop = new InputAdapter$loop$1(job, this);
    }

    @Override // java.io.InputStream
    public int available() {
        return this.channel.getAvailableForRead();
    }

    @Override // java.io.InputStream
    public synchronized int read() {
        try {
            byte[] bArr = this.single;
            if (bArr == null) {
                bArr = new byte[1];
                this.single = bArr;
            }
            int iSubmitAndAwait = this.loop.submitAndAwait(bArr, 0, 1);
            if (iSubmitAndAwait == -1) {
                return -1;
            }
            if (iSubmitAndAwait != 1) {
                throw new IllegalStateException(("Expected a single byte or EOF. Got " + iSubmitAndAwait + " bytes.").toString());
            }
            return bArr[0] & 255;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) {
        InputAdapter$loop$1 inputAdapter$loop$1;
        inputAdapter$loop$1 = this.loop;
        Intrinsics.checkNotNull(bArr);
        return inputAdapter$loop$1.submitAndAwait(bArr, i, i2);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            super.close();
            ByteReadChannelKt.cancel(this.channel);
            if (!this.context.isCompleted()) {
                Job.DefaultImpls.cancel$default(this.context, null, 1, null);
            }
            this.loop.shutdown();
        } catch (Throwable th) {
            throw th;
        }
    }
}
