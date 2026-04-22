package kellinwood.zipio;

import java.io.IOException;
import kellinwood.logging.LoggerInterface;
import kellinwood.logging.LoggerManager;

/* JADX INFO: loaded from: classes.dex */
public class CentralEnd {
    private static LoggerInterface log;
    public int centralDirectorySize;
    public int centralStartOffset;
    public String fileComment;
    public short numCentralEntries;
    public short totalCentralEntries;
    public int signature = 101010256;
    public short numberThisDisk = 0;
    public short centralStartDisk = 0;

    public static CentralEnd read(ZipInput zipInput) throws IOException {
        if (zipInput.readInt() != 101010256) {
            zipInput.seek(zipInput.getFilePointer() - 4);
            return null;
        }
        CentralEnd centralEnd = new CentralEnd();
        centralEnd.doRead(zipInput);
        return centralEnd;
    }

    public static LoggerInterface getLogger() {
        if (log == null) {
            log = LoggerManager.getLogger(CentralEnd.class.getName());
        }
        return log;
    }

    private void doRead(ZipInput zipInput) {
        boolean zIsDebugEnabled = getLogger().isDebugEnabled();
        short s = zipInput.readShort();
        this.numberThisDisk = s;
        if (zIsDebugEnabled) {
            log.debug(String.format("This disk number: 0x%04x", Short.valueOf(s)));
        }
        short s2 = zipInput.readShort();
        this.centralStartDisk = s2;
        if (zIsDebugEnabled) {
            log.debug(String.format("Central dir start disk number: 0x%04x", Short.valueOf(s2)));
        }
        short s3 = zipInput.readShort();
        this.numCentralEntries = s3;
        if (zIsDebugEnabled) {
            log.debug(String.format("Central entries on this disk: 0x%04x", Short.valueOf(s3)));
        }
        short s4 = zipInput.readShort();
        this.totalCentralEntries = s4;
        if (zIsDebugEnabled) {
            log.debug(String.format("Total number of central entries: 0x%04x", Short.valueOf(s4)));
        }
        int i = zipInput.readInt();
        this.centralDirectorySize = i;
        if (zIsDebugEnabled) {
            log.debug(String.format("Central directory size: 0x%08x", Integer.valueOf(i)));
        }
        int i2 = zipInput.readInt();
        this.centralStartOffset = i2;
        if (zIsDebugEnabled) {
            log.debug(String.format("Central directory offset: 0x%08x", Integer.valueOf(i2)));
        }
        this.fileComment = zipInput.readString(zipInput.readShort());
        if (zIsDebugEnabled) {
            log.debug(".ZIP file comment: " + this.fileComment);
        }
    }

    public void write(ZipOutput zipOutput) {
        getLogger().isDebugEnabled();
        zipOutput.writeInt(this.signature);
        zipOutput.writeShort(this.numberThisDisk);
        zipOutput.writeShort(this.centralStartDisk);
        zipOutput.writeShort(this.numCentralEntries);
        zipOutput.writeShort(this.totalCentralEntries);
        zipOutput.writeInt(this.centralDirectorySize);
        zipOutput.writeInt(this.centralStartOffset);
        zipOutput.writeShort((short) this.fileComment.length());
        zipOutput.writeString(this.fileComment);
    }
}
