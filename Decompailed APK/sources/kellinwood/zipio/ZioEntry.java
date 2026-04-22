package kellinwood.zipio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import kellinwood.logging.LoggerInterface;
import kellinwood.logging.LoggerManager;

/* JADX INFO: loaded from: classes.dex */
public class ZioEntry implements Cloneable {
    private static byte[] alignBytes = new byte[4];
    private static LoggerInterface log;
    private int compressedSize;
    private short compression;
    private int crc32;
    private byte[] data;
    private long dataPosition;
    private short diskNumberStart;
    private ZioEntryOutputStream entryOut;
    private int externalAttributes;
    private byte[] extraData;
    private String fileComment;
    private String filename;
    private short generalPurposeBits;
    private short internalAttributes;
    private int localHeaderOffset;
    private short modificationDate;
    private short modificationTime;
    private short numAlignBytes;
    private int size;
    private short versionMadeBy;
    private short versionRequired;
    private ZipInput zipInput;

    public ZioEntry(ZipInput zipInput) {
        this.numAlignBytes = (short) 0;
        this.dataPosition = -1L;
        this.data = null;
        this.entryOut = null;
        this.zipInput = zipInput;
    }

    public static LoggerInterface getLogger() {
        if (log == null) {
            log = LoggerManager.getLogger(ZioEntry.class.getName());
        }
        return log;
    }

    public ZioEntry(String str) {
        this.numAlignBytes = (short) 0;
        this.dataPosition = -1L;
        this.data = null;
        this.entryOut = null;
        this.filename = str;
        this.fileComment = "";
        this.compression = (short) 8;
        this.extraData = new byte[0];
        setTime(System.currentTimeMillis());
    }

    public void readLocalHeader() {
        ZipInput zipInput = this.zipInput;
        boolean zIsDebugEnabled = getLogger().isDebugEnabled();
        zipInput.seek(this.localHeaderOffset);
        if (zIsDebugEnabled) {
            getLogger().debug(String.format("FILE POSITION: 0x%08x", Long.valueOf(zipInput.getFilePointer())));
        }
        if (zipInput.readInt() != 67324752) {
            throw new IllegalStateException(String.format("Local header not found at pos=0x%08x, file=%s", Long.valueOf(zipInput.getFilePointer()), this.filename));
        }
        short s = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("Version required: 0x%04x", Short.valueOf(s)));
        }
        short s2 = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("General purpose bits: 0x%04x", Short.valueOf(s2)));
        }
        short s3 = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("Compression: 0x%04x", Short.valueOf(s3)));
        }
        short s4 = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("Modification time: 0x%04x", Short.valueOf(s4)));
        }
        short s5 = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("Modification date: 0x%04x", Short.valueOf(s5)));
        }
        int i = zipInput.readInt();
        if (zIsDebugEnabled) {
            log.debug(String.format("CRC-32: 0x%04x", Integer.valueOf(i)));
        }
        int i2 = zipInput.readInt();
        if (zIsDebugEnabled) {
            log.debug(String.format("Compressed size: 0x%04x", Integer.valueOf(i2)));
        }
        int i3 = zipInput.readInt();
        if (zIsDebugEnabled) {
            log.debug(String.format("Size: 0x%04x", Integer.valueOf(i3)));
        }
        short s6 = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("File name length: 0x%04x", Short.valueOf(s6)));
        }
        short s7 = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("Extra length: 0x%04x", Short.valueOf(s7)));
        }
        String string = zipInput.readString(s6);
        if (zIsDebugEnabled) {
            log.debug("Filename: " + string);
        }
        zipInput.readBytes(s7);
        long filePointer = zipInput.getFilePointer();
        this.dataPosition = filePointer;
        if (zIsDebugEnabled) {
            log.debug(String.format("Data position: 0x%08x", Long.valueOf(filePointer)));
        }
    }

    public void writeLocalEntry(ZipOutput zipOutput) {
        short filePointer;
        long j = 0;
        if (this.data == null && this.dataPosition < 0 && this.zipInput != null) {
            readLocalHeader();
        }
        this.localHeaderOffset = zipOutput.getFilePointer();
        boolean zIsDebugEnabled = getLogger().isDebugEnabled();
        if (zIsDebugEnabled) {
            getLogger().debug(String.format("Writing local header at 0x%08x - %s", Integer.valueOf(this.localHeaderOffset), this.filename));
        }
        ZioEntryOutputStream zioEntryOutputStream = this.entryOut;
        if (zioEntryOutputStream != null) {
            zioEntryOutputStream.close();
            this.size = this.entryOut.getSize();
            byte[] byteArray = ((ByteArrayOutputStream) this.entryOut.getWrappedStream()).toByteArray();
            this.data = byteArray;
            this.compressedSize = byteArray.length;
            this.crc32 = this.entryOut.getCRC();
        }
        zipOutput.writeInt(67324752);
        zipOutput.writeShort(this.versionRequired);
        zipOutput.writeShort(this.generalPurposeBits);
        zipOutput.writeShort(this.compression);
        zipOutput.writeShort(this.modificationTime);
        zipOutput.writeShort(this.modificationDate);
        zipOutput.writeInt(this.crc32);
        zipOutput.writeInt(this.compressedSize);
        zipOutput.writeInt(this.size);
        zipOutput.writeShort((short) this.filename.length());
        this.numAlignBytes = (short) 0;
        if (this.compression == 0 && (filePointer = (short) (((long) (((zipOutput.getFilePointer() + 2) + this.filename.length()) + this.extraData.length)) % 4)) > 0) {
            this.numAlignBytes = (short) (4 - filePointer);
        }
        zipOutput.writeShort((short) (this.extraData.length + this.numAlignBytes));
        zipOutput.writeString(this.filename);
        zipOutput.writeBytes(this.extraData);
        short s = this.numAlignBytes;
        if (s > 0) {
            zipOutput.writeBytes(alignBytes, 0, s);
        }
        if (zIsDebugEnabled) {
            getLogger().debug(String.format(Locale.ENGLISH, "Data position 0x%08x", Integer.valueOf(zipOutput.getFilePointer())));
        }
        byte[] bArr = this.data;
        if (bArr != null) {
            zipOutput.writeBytes(bArr);
            if (zIsDebugEnabled) {
                getLogger().debug(String.format(Locale.ENGLISH, "Wrote %d bytes", Integer.valueOf(this.data.length)));
                return;
            }
            return;
        }
        if (zIsDebugEnabled) {
            getLogger().debug(String.format("Seeking to position 0x%08x", Long.valueOf(this.dataPosition)));
        }
        this.zipInput.seek(this.dataPosition);
        int iMin = Math.min(this.compressedSize, 8096);
        byte[] bArr2 = new byte[iMin];
        while (true) {
            int i = this.compressedSize;
            if (j == i) {
                return;
            }
            int i2 = this.zipInput.in.read(bArr2, 0, (int) Math.min(((long) i) - j, iMin));
            if (i2 > 0) {
                zipOutput.writeBytes(bArr2, 0, i2);
                if (zIsDebugEnabled) {
                    getLogger().debug(String.format(Locale.ENGLISH, "Wrote %d bytes", Integer.valueOf(i2)));
                }
                j += (long) i2;
            } else {
                throw new IllegalStateException(String.format(Locale.ENGLISH, "EOF reached while copying %s with %d bytes left to go", this.filename, Long.valueOf(((long) this.compressedSize) - j)));
            }
        }
    }

    public static ZioEntry read(ZipInput zipInput) throws IOException {
        if (zipInput.readInt() != 33639248) {
            zipInput.seek(zipInput.getFilePointer() - 4);
            return null;
        }
        ZioEntry zioEntry = new ZioEntry(zipInput);
        zioEntry.doRead(zipInput);
        return zioEntry;
    }

    private void doRead(ZipInput zipInput) {
        boolean zIsDebugEnabled = getLogger().isDebugEnabled();
        short s = zipInput.readShort();
        this.versionMadeBy = s;
        if (zIsDebugEnabled) {
            log.debug(String.format("Version made by: 0x%04x", Short.valueOf(s)));
        }
        short s2 = zipInput.readShort();
        this.versionRequired = s2;
        if (zIsDebugEnabled) {
            log.debug(String.format("Version required: 0x%04x", Short.valueOf(s2)));
        }
        short s3 = zipInput.readShort();
        this.generalPurposeBits = s3;
        if (zIsDebugEnabled) {
            log.debug(String.format("General purpose bits: 0x%04x", Short.valueOf(s3)));
        }
        if ((this.generalPurposeBits & 63473) != 0) {
            throw new IllegalStateException("Can't handle general purpose bits == " + String.format("0x%04x", Short.valueOf(this.generalPurposeBits)));
        }
        short s4 = zipInput.readShort();
        this.compression = s4;
        if (zIsDebugEnabled) {
            log.debug(String.format("Compression: 0x%04x", Short.valueOf(s4)));
        }
        short s5 = zipInput.readShort();
        this.modificationTime = s5;
        if (zIsDebugEnabled) {
            log.debug(String.format("Modification time: 0x%04x", Short.valueOf(s5)));
        }
        short s6 = zipInput.readShort();
        this.modificationDate = s6;
        if (zIsDebugEnabled) {
            log.debug(String.format("Modification date: 0x%04x", Short.valueOf(s6)));
        }
        int i = zipInput.readInt();
        this.crc32 = i;
        if (zIsDebugEnabled) {
            log.debug(String.format("CRC-32: 0x%04x", Integer.valueOf(i)));
        }
        int i2 = zipInput.readInt();
        this.compressedSize = i2;
        if (zIsDebugEnabled) {
            log.debug(String.format("Compressed size: 0x%04x", Integer.valueOf(i2)));
        }
        int i3 = zipInput.readInt();
        this.size = i3;
        if (zIsDebugEnabled) {
            log.debug(String.format("Size: 0x%04x", Integer.valueOf(i3)));
        }
        short s7 = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("File name length: 0x%04x", Short.valueOf(s7)));
        }
        short s8 = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("Extra length: 0x%04x", Short.valueOf(s8)));
        }
        short s9 = zipInput.readShort();
        if (zIsDebugEnabled) {
            log.debug(String.format("File comment length: 0x%04x", Short.valueOf(s9)));
        }
        short s10 = zipInput.readShort();
        this.diskNumberStart = s10;
        if (zIsDebugEnabled) {
            log.debug(String.format("Disk number start: 0x%04x", Short.valueOf(s10)));
        }
        short s11 = zipInput.readShort();
        this.internalAttributes = s11;
        if (zIsDebugEnabled) {
            log.debug(String.format("Internal attributes: 0x%04x", Short.valueOf(s11)));
        }
        int i4 = zipInput.readInt();
        this.externalAttributes = i4;
        if (zIsDebugEnabled) {
            log.debug(String.format("External attributes: 0x%08x", Integer.valueOf(i4)));
        }
        int i5 = zipInput.readInt();
        this.localHeaderOffset = i5;
        if (zIsDebugEnabled) {
            log.debug(String.format("Local header offset: 0x%08x", Integer.valueOf(i5)));
        }
        this.filename = zipInput.readString(s7);
        if (zIsDebugEnabled) {
            log.debug("Filename: " + this.filename);
        }
        this.extraData = zipInput.readBytes(s8);
        this.fileComment = zipInput.readString(s9);
        if (zIsDebugEnabled) {
            log.debug("File comment: " + this.fileComment);
        }
        this.generalPurposeBits = (short) (this.generalPurposeBits & 2048);
        if (this.size == 0) {
            this.compressedSize = 0;
            this.compression = (short) 0;
            this.crc32 = 0;
        }
    }

    public byte[] getData() throws IOException {
        byte[] bArr = this.data;
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[this.size];
        InputStream inputStream = getInputStream();
        int i = 0;
        while (true) {
            int i2 = this.size;
            if (i == i2) {
                return bArr2;
            }
            int i3 = inputStream.read(bArr2, i, i2 - i);
            if (i3 < 0) {
                throw new IllegalStateException(String.format(Locale.ENGLISH, "Read failed, expecting %d bytes, got %d instead", Integer.valueOf(this.size), Integer.valueOf(i)));
            }
            i += i3;
        }
    }

    public InputStream getInputStream() {
        return getInputStream(null);
    }

    public InputStream getInputStream(OutputStream outputStream) throws IOException {
        ZioEntryOutputStream zioEntryOutputStream = this.entryOut;
        if (zioEntryOutputStream != null) {
            zioEntryOutputStream.close();
            this.size = this.entryOut.getSize();
            byte[] byteArray = ((ByteArrayOutputStream) this.entryOut.getWrappedStream()).toByteArray();
            this.data = byteArray;
            this.compressedSize = byteArray.length;
            this.crc32 = this.entryOut.getCRC();
            this.entryOut = null;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.data);
            return this.compression == 0 ? byteArrayInputStream : new InflaterInputStream(new SequenceInputStream(byteArrayInputStream, new ByteArrayInputStream(new byte[1])), new Inflater(true));
        }
        ZioEntryInputStream zioEntryInputStream = new ZioEntryInputStream(this);
        if (outputStream != null) {
            zioEntryInputStream.setMonitorStream(outputStream);
        }
        if (this.compression == 0) {
            return zioEntryInputStream;
        }
        zioEntryInputStream.setReturnDummyByte(true);
        return new InflaterInputStream(zioEntryInputStream, new Inflater(true));
    }

    public OutputStream getOutputStream() {
        ZioEntryOutputStream zioEntryOutputStream = new ZioEntryOutputStream(this.compression, new ByteArrayOutputStream());
        this.entryOut = zioEntryOutputStream;
        return zioEntryOutputStream;
    }

    public void write(ZipOutput zipOutput) {
        getLogger().isDebugEnabled();
        zipOutput.writeInt(33639248);
        zipOutput.writeShort(this.versionMadeBy);
        zipOutput.writeShort(this.versionRequired);
        zipOutput.writeShort(this.generalPurposeBits);
        zipOutput.writeShort(this.compression);
        zipOutput.writeShort(this.modificationTime);
        zipOutput.writeShort(this.modificationDate);
        zipOutput.writeInt(this.crc32);
        zipOutput.writeInt(this.compressedSize);
        zipOutput.writeInt(this.size);
        zipOutput.writeShort((short) this.filename.length());
        zipOutput.writeShort((short) (this.extraData.length + this.numAlignBytes));
        zipOutput.writeShort((short) this.fileComment.length());
        zipOutput.writeShort(this.diskNumberStart);
        zipOutput.writeShort(this.internalAttributes);
        zipOutput.writeInt(this.externalAttributes);
        zipOutput.writeInt(this.localHeaderOffset);
        zipOutput.writeString(this.filename);
        zipOutput.writeBytes(this.extraData);
        short s = this.numAlignBytes;
        if (s > 0) {
            zipOutput.writeBytes(alignBytes, 0, s);
        }
        zipOutput.writeString(this.fileComment);
    }

    public long getTime() {
        short s = this.modificationDate;
        int i = ((s >> 9) & 127) + 80;
        int i2 = ((s >> 5) & 15) - 1;
        int i3 = s & 31;
        short s2 = this.modificationTime;
        return new Date(i, i2, i3, (s2 >> 11) & 31, (s2 >> 5) & 63, (s2 << 1) & 62).getTime();
    }

    public void setTime(long j) {
        long month = new Date(j).getYear() + 1900 < 1980 ? 2162688L : ((r4 - 80) << 25) | ((r0.getMonth() + 1) << 21) | (r0.getDate() << 16) | (r0.getHours() << 11) | (r0.getMinutes() << 5) | (r0.getSeconds() >> 1);
        this.modificationDate = (short) (month >> 16);
        this.modificationTime = (short) (month & 65535);
    }

    public boolean isDirectory() {
        return this.filename.endsWith("/");
    }

    public String getName() {
        return this.filename;
    }

    public short getCompression() {
        return this.compression;
    }

    public int getCrc32() {
        return this.crc32;
    }

    public int getCompressedSize() {
        return this.compressedSize;
    }

    public int getSize() {
        return this.size;
    }

    public long getDataPosition() {
        return this.dataPosition;
    }

    public ZipInput getZipInput() {
        return this.zipInput;
    }
}
