package androidx.room.util;

import ch.qos.logback.classic.spi.CallerData;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StringUtil.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StringUtil {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static final StringBuilder newStringBuilder() {
        return new StringBuilder();
    }

    public static final void appendPlaceholders(StringBuilder builder, int i) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        for (int i2 = 0; i2 < i; i2++) {
            builder.append(CallerData.NA);
            if (i2 < i - 1) {
                builder.append(",");
            }
        }
    }
}
