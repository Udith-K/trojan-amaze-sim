package okio;

import java.nio.channels.ReadableByteChannel;

/* JADX INFO: compiled from: BufferedSource.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    boolean exhausted();

    Buffer getBuffer();

    byte readByte();

    byte[] readByteArray(long j);

    ByteString readByteString(long j);

    void readFully(Buffer buffer, long j);

    void readFully(byte[] bArr);

    long readHexadecimalUnsignedLong();

    int readInt();

    long readLong();

    short readShort();

    String readUtf8LineStrict();

    String readUtf8LineStrict(long j);

    void require(long j);

    void skip(long j);
}
