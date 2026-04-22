package org.fdroid.download.glide;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.HashUtilsKt;

/* JADX INFO: compiled from: AutoVerifyingInputStream.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\f\u001a\u00020\u000bH\u0016J\"\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/fdroid/download/glide/AutoVerifyingInputStream;", "Ljava/security/DigestInputStream;", "inputStream", "Ljava/io/InputStream;", "expectedHash", "", "maxBytesToRead", "", "<init>", "(Ljava/io/InputStream;Ljava/lang/String;J)V", "bytesRead", "", "read", "b", "", "off", "len", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AutoVerifyingInputStream extends DigestInputStream {
    private int bytesRead;
    private final String expectedHash;
    private final long maxBytesToRead;

    public /* synthetic */ AutoVerifyingInputStream(InputStream inputStream, String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(inputStream, str, (i & 4) != 0 ? Runtime.getRuntime().maxMemory() / ((long) 2) : j);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoVerifyingInputStream(InputStream inputStream, String expectedHash, long j) {
        super(inputStream, MessageDigest.getInstance("SHA-256"));
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(expectedHash, "expectedHash");
        this.expectedHash = expectedHash;
        this.maxBytesToRead = j;
    }

    @Override // java.security.DigestInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i = super.read();
        if (i != -1) {
            int i2 = this.bytesRead + 1;
            this.bytesRead = i2;
            if (i2 > this.maxBytesToRead) {
                throw new IOException("Read " + this.bytesRead + " bytes, above maximum allowed.");
            }
        } else if (!HashUtilsKt.isMatching(((DigestInputStream) this).digest, this.expectedHash)) {
            throw new IOException("Hash not matching.");
        }
        return i;
    }

    @Override // java.security.DigestInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int i = super.read(b, off, len);
        if (i != -1) {
            int i2 = this.bytesRead + i;
            this.bytesRead = i2;
            if (i2 > this.maxBytesToRead) {
                throw new IOException("Read " + this.bytesRead + " bytes, above maximum allowed.");
            }
        } else if (!HashUtilsKt.isMatching(((DigestInputStream) this).digest, this.expectedHash)) {
            throw new IOException("Hash not matching.");
        }
        return i;
    }
}
