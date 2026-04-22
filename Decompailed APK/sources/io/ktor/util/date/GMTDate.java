package io.ktor.util.date;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Date.kt */
/* JADX INFO: loaded from: classes.dex */
public final class GMTDate implements Comparable {
    public static final Companion Companion = new Companion(null);
    private static final GMTDate START = DateJvmKt.GMTDate(0L);
    private final int dayOfMonth;
    private final WeekDay dayOfWeek;
    private final int dayOfYear;
    private final int hours;
    private final int minutes;
    private final Month month;
    private final int seconds;
    private final long timestamp;
    private final int year;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GMTDate)) {
            return false;
        }
        GMTDate gMTDate = (GMTDate) obj;
        return this.seconds == gMTDate.seconds && this.minutes == gMTDate.minutes && this.hours == gMTDate.hours && this.dayOfWeek == gMTDate.dayOfWeek && this.dayOfMonth == gMTDate.dayOfMonth && this.dayOfYear == gMTDate.dayOfYear && this.month == gMTDate.month && this.year == gMTDate.year && this.timestamp == gMTDate.timestamp;
    }

    public int hashCode() {
        return (((((((((((((((this.seconds * 31) + this.minutes) * 31) + this.hours) * 31) + this.dayOfWeek.hashCode()) * 31) + this.dayOfMonth) * 31) + this.dayOfYear) * 31) + this.month.hashCode()) * 31) + this.year) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.timestamp);
    }

    public String toString() {
        return "GMTDate(seconds=" + this.seconds + ", minutes=" + this.minutes + ", hours=" + this.hours + ", dayOfWeek=" + this.dayOfWeek + ", dayOfMonth=" + this.dayOfMonth + ", dayOfYear=" + this.dayOfYear + ", month=" + this.month + ", year=" + this.year + ", timestamp=" + this.timestamp + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public GMTDate(int i, int i2, int i3, WeekDay dayOfWeek, int i4, int i5, Month month, int i6, long j) {
        Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
        Intrinsics.checkNotNullParameter(month, "month");
        this.seconds = i;
        this.minutes = i2;
        this.hours = i3;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = i4;
        this.dayOfYear = i5;
        this.month = month;
        this.year = i6;
        this.timestamp = j;
    }

    @Override // java.lang.Comparable
    public int compareTo(GMTDate other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return Intrinsics.compare(this.timestamp, other.timestamp);
    }

    /* JADX INFO: compiled from: Date.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
