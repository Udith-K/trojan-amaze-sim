package kellinwood.zipio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class ZioEntryOutputStream extends OutputStream {
    Deflater deflater;
    OutputStream downstream;
    OutputStream wrapped;
    int size = 0;
    CRC32 crc = new CRC32();
    int crcValue = 0;

    public ZioEntryOutputStream(int i, OutputStream outputStream) {
        this.wrapped = outputStream;
        if (i != 0) {
            this.deflater = new Deflater(9, true);
            this.downstream = new DeflaterOutputStream(outputStream, this.deflater);
        } else {
            this.downstream = outputStream;
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.downstream.flush();
        this.downstream.close();
        this.crcValue = (int) this.crc.getValue();
        Deflater deflater = this.deflater;
        if (deflater != null) {
            deflater.end();
        }
    }

    public int getCRC() {
        return this.crcValue;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.downstream.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.downstream.write(bArr);
        this.crc.update(bArr);
        this.size += bArr.length;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.downstream.write(bArr, i, i2);
        this.crc.update(bArr, i, i2);
        this.size += i2;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.downstream.write(i);
        this.crc.update(i);
        this.size++;
    }

    public int getSize() {
        return this.size;
    }

    public OutputStream getWrappedStream() {
        return this.wrapped;
    }
}
