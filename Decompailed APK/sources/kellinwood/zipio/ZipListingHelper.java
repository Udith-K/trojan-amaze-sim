package kellinwood.zipio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kellinwood.logging.LoggerInterface;

/* JADX INFO: loaded from: classes.dex */
public abstract class ZipListingHelper {
    static DateFormat dateFormat = new SimpleDateFormat("MM-dd-yy HH:mm", Locale.ENGLISH);

    public static void listHeader(LoggerInterface loggerInterface) {
        loggerInterface.debug(" Length   Method    Size  Ratio   Date   Time   CRC-32    Name");
        loggerInterface.debug("--------  ------  ------- -----   ----   ----   ------    ----");
    }

    public static void listEntry(LoggerInterface loggerInterface, ZioEntry zioEntry) {
        loggerInterface.debug(String.format(Locale.ENGLISH, "%8d  %6s %8d %4d%% %s  %08x  %s", Integer.valueOf(zioEntry.getSize()), zioEntry.getCompression() == 0 ? "Stored" : "Defl:N", Integer.valueOf(zioEntry.getCompressedSize()), Integer.valueOf(zioEntry.getSize() > 0 ? ((zioEntry.getSize() - zioEntry.getCompressedSize()) * 100) / zioEntry.getSize() : 0), dateFormat.format(new Date(zioEntry.getTime())), Integer.valueOf(zioEntry.getCrc32()), zioEntry.getName()));
    }
}
