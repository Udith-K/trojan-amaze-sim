package io.ktor.util.date;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DateJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DateJvmKt {
    private static final TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT");

    public static /* synthetic */ GMTDate GMTDate$default(Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = null;
        }
        return GMTDate(l);
    }

    public static final GMTDate GMTDate(Long l) {
        Calendar calendar = Calendar.getInstance(GMT_TIMEZONE, Locale.ROOT);
        Intrinsics.checkNotNull(calendar);
        return toDate(calendar, l);
    }

    public static final GMTDate toDate(Calendar calendar, Long l) {
        Intrinsics.checkNotNullParameter(calendar, "<this>");
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        int i = calendar.get(15) + calendar.get(16);
        return new GMTDate(calendar.get(13), calendar.get(12), calendar.get(11), WeekDay.Companion.from((calendar.get(7) + 5) % 7), calendar.get(5), calendar.get(6), Month.Companion.from(calendar.get(2)), calendar.get(1), calendar.getTimeInMillis() + ((long) i));
    }
}
