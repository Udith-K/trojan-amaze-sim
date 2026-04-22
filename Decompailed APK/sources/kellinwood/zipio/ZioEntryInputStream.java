package kellinwood.zipio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Locale;
import kellinwood.logging.LoggerInterface;
import kellinwood.logging.LoggerManager;

/* JADX INFO: loaded from: classes.dex */
public class ZioEntryInputStream extends InputStream {
    boolean debug;
    LoggerInterface log;
    int offset;
    RandomAccessFile raf;
    int size;
    boolean returnDummyByte = false;
    OutputStream monitor = null;

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public ZioEntryInputStream(ZioEntry zioEntry) throws IOException {
        LoggerInterface logger = LoggerManager.getLogger(getClass().getName());
        this.log = logger;
        this.debug = logger.isDebugEnabled();
        this.offset = 0;
        this.size = zioEntry.getCompressedSize();
        this.raf = zioEntry.getZipInput().in;
        if (zioEntry.getDataPosition() >= 0) {
            if (this.debug) {
                this.log.debug(String.format(Locale.ENGLISH, "Seeking to %d", Long.valueOf(zioEntry.getDataPosition())));
            }
            this.raf.seek(zioEntry.getDataPosition());
            return;
        }
        zioEntry.readLocalHeader();
    }

    public void setReturnDummyByte(boolean z) {
        this.returnDummyByte = z;
    }

    public void setMonitorStream(OutputStream outputStream) {
        this.monitor = outputStream;
    }

    @Override // java.io.InputStream
    public int available() {
        int i = this.size - this.offset;
        if (this.debug) {
            this.log.debug(String.format(Locale.ENGLISH, "Available = %d", Integer.valueOf(i)));
        }
        if (i == 0 && this.returnDummyByte) {
            return 1;
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.size - this.offset == 0) {
            if (!this.returnDummyByte) {
                return -1;
            }
            this.returnDummyByte = false;
            return 0;
        }
        int i = this.raf.read();
        if (i >= 0) {
            OutputStream outputStream = this.monitor;
            if (outputStream != null) {
                outputStream.write(i);
            }
            if (this.debug) {
                this.log.debug("Read 1 byte");
            }
            this.offset++;
        } else if (this.debug) {
            this.log.debug("Read 0 bytes");
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        return readBytes(bArr, i, i2);
    }

    private int readBytes(byte[] bArr, int i, int i2) throws IOException {
        if (this.size - this.offset == 0) {
            if (!this.returnDummyByte) {
                return -1;
            }
            this.returnDummyByte = false;
            bArr[i] = 0;
            return 1;
        }
        int i3 = this.raf.read(bArr, i, Math.min(i2, available()));
        if (i3 > 0) {
            OutputStream outputStream = this.monitor;
            if (outputStream != null) {
                outputStream.write(bArr, i, i3);
            }
            this.offset += i3;
        }
        if (this.debug) {
            this.log.debug(String.format(Locale.ENGLISH, "Read %d bytes for read(b,%d,%d)", Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        return i3;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return readBytes(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long jMin = Math.min(j, available());
        RandomAccessFile randomAccessFile = this.raf;
        randomAccessFile.seek(randomAccessFile.getFilePointer() + jMin);
        if (this.debug) {
            this.log.debug(String.format(Locale.ENGLISH, "Skipped %d bytes", Long.valueOf(jMin)));
        }
        return jMin;
    }
}
