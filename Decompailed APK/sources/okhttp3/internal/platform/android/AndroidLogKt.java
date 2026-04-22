package okhttp3.internal.platform.android;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/* JADX INFO: compiled from: AndroidLog.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AndroidLogKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int getAndroidLevel(LogRecord logRecord) {
        int iIntValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        if (iIntValue > level.intValue()) {
            return 5;
        }
        return logRecord.getLevel().intValue() == level.intValue() ? 4 : 3;
    }
}
