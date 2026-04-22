package kotlin.time;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: Duration.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class Duration implements Comparable {
    private final long rawValue;
    public static final Companion Companion = new Companion(null);
    private static final long ZERO = m2791constructorimpl(0);
    private static final long INFINITE = DurationKt.durationOfMillis(4611686018427387903L);
    private static final long NEG_INFINITE = DurationKt.durationOfMillis(-4611686018427387903L);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Duration m2789boximpl(long j) {
        return new Duration(j);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2792equalsimpl(long j, Object obj) {
        return (obj instanceof Duration) && j == ((Duration) obj).m2816unboximpl();
    }

    /* JADX INFO: renamed from: getValue-impl, reason: not valid java name */
    private static final long m2803getValueimpl(long j) {
        return j >> 1;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2804hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    /* JADX INFO: renamed from: isInMillis-impl, reason: not valid java name */
    private static final boolean m2806isInMillisimpl(long j) {
        return (((int) j) & 1) == 1;
    }

    /* JADX INFO: renamed from: isInNanos-impl, reason: not valid java name */
    private static final boolean m2807isInNanosimpl(long j) {
        return (((int) j) & 1) == 0;
    }

    /* JADX INFO: renamed from: isNegative-impl, reason: not valid java name */
    public static final boolean m2809isNegativeimpl(long j) {
        return j < 0;
    }

    public boolean equals(Object obj) {
        return m2792equalsimpl(this.rawValue, obj);
    }

    public int hashCode() {
        return m2804hashCodeimpl(this.rawValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2816unboximpl() {
        return this.rawValue;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m2815compareToLRDsOJo(((Duration) obj).m2816unboximpl());
    }

    private /* synthetic */ Duration(long j) {
        this.rawValue = j;
    }

    /* JADX INFO: renamed from: getStorageUnit-impl, reason: not valid java name */
    private static final DurationUnit m2802getStorageUnitimpl(long j) {
        return m2807isInNanosimpl(j) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2791constructorimpl(long j) {
        if (DurationJvmKt.getDurationAssertionsEnabled()) {
            if (m2807isInNanosimpl(j)) {
                long jM2803getValueimpl = m2803getValueimpl(j);
                if (-4611686018426999999L > jM2803getValueimpl || jM2803getValueimpl >= 4611686018427000000L) {
                    throw new AssertionError(m2803getValueimpl(j) + " ns is out of nanoseconds range");
                }
            } else {
                long jM2803getValueimpl2 = m2803getValueimpl(j);
                if (-4611686018427387903L > jM2803getValueimpl2 || jM2803getValueimpl2 >= 4611686018427387904L) {
                    throw new AssertionError(m2803getValueimpl(j) + " ms is out of milliseconds range");
                }
                long jM2803getValueimpl3 = m2803getValueimpl(j);
                if (-4611686018426L <= jM2803getValueimpl3 && jM2803getValueimpl3 < 4611686018427L) {
                    throw new AssertionError(m2803getValueimpl(j) + " ms is denormalized");
                }
            }
        }
        return j;
    }

    /* JADX INFO: compiled from: Duration.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZERO-UwyO8pc, reason: not valid java name */
        public final long m2818getZEROUwyO8pc() {
            return Duration.ZERO;
        }

        /* JADX INFO: renamed from: getINFINITE-UwyO8pc, reason: not valid java name */
        public final long m2817getINFINITEUwyO8pc() {
            return Duration.INFINITE;
        }

        /* JADX INFO: renamed from: parseIsoString-UwyO8pc, reason: not valid java name */
        public final long m2819parseIsoStringUwyO8pc(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return DurationKt.parseDuration(value, true);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid ISO duration string format: '" + value + "'.", e);
            }
        }
    }

    /* JADX INFO: renamed from: unaryMinus-UwyO8pc, reason: not valid java name */
    public static final long m2814unaryMinusUwyO8pc(long j) {
        return DurationKt.durationOf(-m2803getValueimpl(j), ((int) j) & 1);
    }

    /* JADX INFO: renamed from: plus-LRDsOJo, reason: not valid java name */
    public static final long m2810plusLRDsOJo(long j, long j2) {
        if (m2808isInfiniteimpl(j)) {
            if (m2805isFiniteimpl(j2) || (j2 ^ j) >= 0) {
                return j;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        }
        if (m2808isInfiniteimpl(j2)) {
            return j2;
        }
        if ((((int) j) & 1) == (((int) j2) & 1)) {
            long jM2803getValueimpl = m2803getValueimpl(j) + m2803getValueimpl(j2);
            return m2807isInNanosimpl(j) ? DurationKt.durationOfNanosNormalized(jM2803getValueimpl) : DurationKt.durationOfMillisNormalized(jM2803getValueimpl);
        }
        if (m2806isInMillisimpl(j)) {
            return m2787addValuesMixedRangesUwyO8pc(j, m2803getValueimpl(j), m2803getValueimpl(j2));
        }
        return m2787addValuesMixedRangesUwyO8pc(j, m2803getValueimpl(j2), m2803getValueimpl(j));
    }

    /* JADX INFO: renamed from: addValuesMixedRanges-UwyO8pc, reason: not valid java name */
    private static final long m2787addValuesMixedRangesUwyO8pc(long j, long j2, long j3) {
        long jNanosToMillis = DurationKt.nanosToMillis(j3);
        long j4 = j2 + jNanosToMillis;
        if (-4611686018426L > j4 || j4 >= 4611686018427L) {
            return DurationKt.durationOfMillis(RangesKt.coerceIn(j4, -4611686018427387903L, 4611686018427387903L));
        }
        return DurationKt.durationOfNanos(DurationKt.millisToNanos(j4) + (j3 - DurationKt.millisToNanos(jNanosToMillis)));
    }

    /* JADX INFO: renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m2808isInfiniteimpl(long j) {
        return j == INFINITE || j == NEG_INFINITE;
    }

    /* JADX INFO: renamed from: isFinite-impl, reason: not valid java name */
    public static final boolean m2805isFiniteimpl(long j) {
        return !m2808isInfiniteimpl(j);
    }

    /* JADX INFO: renamed from: getAbsoluteValue-UwyO8pc, reason: not valid java name */
    public static final long m2793getAbsoluteValueUwyO8pc(long j) {
        return m2809isNegativeimpl(j) ? m2814unaryMinusUwyO8pc(j) : j;
    }

    /* JADX INFO: renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public int m2815compareToLRDsOJo(long j) {
        return m2790compareToLRDsOJo(this.rawValue, j);
    }

    /* JADX INFO: renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public static int m2790compareToLRDsOJo(long j, long j2) {
        long j3 = j ^ j2;
        if (j3 < 0 || (((int) j3) & 1) == 0) {
            return Intrinsics.compare(j, j2);
        }
        int i = (((int) j) & 1) - (((int) j2) & 1);
        return m2809isNegativeimpl(j) ? -i : i;
    }

    /* JADX INFO: renamed from: getHoursComponent-impl, reason: not valid java name */
    public static final int m2794getHoursComponentimpl(long j) {
        if (m2808isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m2796getInWholeHoursimpl(j) % ((long) 24));
    }

    /* JADX INFO: renamed from: getMinutesComponent-impl, reason: not valid java name */
    public static final int m2799getMinutesComponentimpl(long j) {
        if (m2808isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m2797getInWholeMinutesimpl(j) % ((long) 60));
    }

    /* JADX INFO: renamed from: getSecondsComponent-impl, reason: not valid java name */
    public static final int m2801getSecondsComponentimpl(long j) {
        if (m2808isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m2798getInWholeSecondsimpl(j) % ((long) 60));
    }

    /* JADX INFO: renamed from: getNanosecondsComponent-impl, reason: not valid java name */
    public static final int m2800getNanosecondsComponentimpl(long j) {
        long jM2803getValueimpl;
        if (m2808isInfiniteimpl(j)) {
            return 0;
        }
        if (m2806isInMillisimpl(j)) {
            jM2803getValueimpl = DurationKt.millisToNanos(m2803getValueimpl(j) % ((long) 1000));
        } else {
            jM2803getValueimpl = m2803getValueimpl(j) % ((long) 1000000000);
        }
        return (int) jM2803getValueimpl;
    }

    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    public static final long m2812toLongimpl(long j, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j == INFINITE) {
            return Preferences.UPDATE_INTERVAL_DISABLED;
        }
        if (j == NEG_INFINITE) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(m2803getValueimpl(j), m2802getStorageUnitimpl(j), unit);
    }

    /* JADX INFO: renamed from: getInWholeDays-impl, reason: not valid java name */
    public static final long m2795getInWholeDaysimpl(long j) {
        return m2812toLongimpl(j, DurationUnit.DAYS);
    }

    /* JADX INFO: renamed from: getInWholeHours-impl, reason: not valid java name */
    public static final long m2796getInWholeHoursimpl(long j) {
        return m2812toLongimpl(j, DurationUnit.HOURS);
    }

    /* JADX INFO: renamed from: getInWholeMinutes-impl, reason: not valid java name */
    public static final long m2797getInWholeMinutesimpl(long j) {
        return m2812toLongimpl(j, DurationUnit.MINUTES);
    }

    /* JADX INFO: renamed from: getInWholeSeconds-impl, reason: not valid java name */
    public static final long m2798getInWholeSecondsimpl(long j) {
        return m2812toLongimpl(j, DurationUnit.SECONDS);
    }

    public String toString() {
        return m2813toStringimpl(this.rawValue);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2813toStringimpl(long j) {
        if (j == 0) {
            return "0s";
        }
        if (j == INFINITE) {
            return "Infinity";
        }
        if (j == NEG_INFINITE) {
            return "-Infinity";
        }
        boolean zM2809isNegativeimpl = m2809isNegativeimpl(j);
        StringBuilder sb = new StringBuilder();
        if (zM2809isNegativeimpl) {
            sb.append(CoreConstants.DASH_CHAR);
        }
        long jM2793getAbsoluteValueUwyO8pc = m2793getAbsoluteValueUwyO8pc(j);
        long jM2795getInWholeDaysimpl = m2795getInWholeDaysimpl(jM2793getAbsoluteValueUwyO8pc);
        int iM2794getHoursComponentimpl = m2794getHoursComponentimpl(jM2793getAbsoluteValueUwyO8pc);
        int iM2799getMinutesComponentimpl = m2799getMinutesComponentimpl(jM2793getAbsoluteValueUwyO8pc);
        int iM2801getSecondsComponentimpl = m2801getSecondsComponentimpl(jM2793getAbsoluteValueUwyO8pc);
        int iM2800getNanosecondsComponentimpl = m2800getNanosecondsComponentimpl(jM2793getAbsoluteValueUwyO8pc);
        int i = 0;
        boolean z = jM2795getInWholeDaysimpl != 0;
        boolean z2 = iM2794getHoursComponentimpl != 0;
        boolean z3 = iM2799getMinutesComponentimpl != 0;
        boolean z4 = (iM2801getSecondsComponentimpl == 0 && iM2800getNanosecondsComponentimpl == 0) ? false : true;
        if (z) {
            sb.append(jM2795getInWholeDaysimpl);
            sb.append('d');
            i = 1;
        }
        if (z2 || (z && (z3 || z4))) {
            int i2 = i + 1;
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(iM2794getHoursComponentimpl);
            sb.append('h');
            i = i2;
        }
        if (z3 || (z4 && (z2 || z))) {
            int i3 = i + 1;
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(iM2799getMinutesComponentimpl);
            sb.append('m');
            i = i3;
        }
        if (z4) {
            int i4 = i + 1;
            if (i > 0) {
                sb.append(' ');
            }
            if (iM2801getSecondsComponentimpl != 0 || z || z2 || z3) {
                m2788appendFractionalimpl(j, sb, iM2801getSecondsComponentimpl, iM2800getNanosecondsComponentimpl, 9, "s", false);
            } else if (iM2800getNanosecondsComponentimpl >= 1000000) {
                m2788appendFractionalimpl(j, sb, iM2800getNanosecondsComponentimpl / 1000000, iM2800getNanosecondsComponentimpl % 1000000, 6, "ms", false);
            } else if (iM2800getNanosecondsComponentimpl >= 1000) {
                m2788appendFractionalimpl(j, sb, iM2800getNanosecondsComponentimpl / 1000, iM2800getNanosecondsComponentimpl % 1000, 3, "us", false);
            } else {
                sb.append(iM2800getNanosecondsComponentimpl);
                sb.append("ns");
            }
            i = i4;
        }
        if (zM2809isNegativeimpl && i > 1) {
            sb.insert(1, CoreConstants.LEFT_PARENTHESIS_CHAR).append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: renamed from: appendFractional-impl, reason: not valid java name */
    private static final void m2788appendFractionalimpl(long j, StringBuilder sb, int i, int i2, int i3, String str, boolean z) {
        sb.append(i);
        if (i2 != 0) {
            sb.append(CoreConstants.DOT);
            String strPadStart = StringsKt.padStart(String.valueOf(i2), i3, '0');
            int i4 = -1;
            int length = strPadStart.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i5 = length - 1;
                    if (strPadStart.charAt(length) != '0') {
                        i4 = length;
                        break;
                    } else if (i5 < 0) {
                        break;
                    } else {
                        length = i5;
                    }
                }
            }
            int i6 = i4 + 1;
            if (!z && i6 < 3) {
                sb.append((CharSequence) strPadStart, 0, i6);
                Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            } else {
                sb.append((CharSequence) strPadStart, 0, ((i4 + 3) / 3) * 3);
                Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            }
        }
        sb.append(str);
    }

    /* JADX INFO: renamed from: toIsoString-impl, reason: not valid java name */
    public static final String m2811toIsoStringimpl(long j) {
        StringBuilder sb = new StringBuilder();
        if (m2809isNegativeimpl(j)) {
            sb.append(CoreConstants.DASH_CHAR);
        }
        sb.append("PT");
        long jM2793getAbsoluteValueUwyO8pc = m2793getAbsoluteValueUwyO8pc(j);
        long jM2796getInWholeHoursimpl = m2796getInWholeHoursimpl(jM2793getAbsoluteValueUwyO8pc);
        int iM2799getMinutesComponentimpl = m2799getMinutesComponentimpl(jM2793getAbsoluteValueUwyO8pc);
        int iM2801getSecondsComponentimpl = m2801getSecondsComponentimpl(jM2793getAbsoluteValueUwyO8pc);
        int iM2800getNanosecondsComponentimpl = m2800getNanosecondsComponentimpl(jM2793getAbsoluteValueUwyO8pc);
        if (m2808isInfiniteimpl(j)) {
            jM2796getInWholeHoursimpl = 9999999999999L;
        }
        boolean z = false;
        boolean z2 = jM2796getInWholeHoursimpl != 0;
        boolean z3 = (iM2801getSecondsComponentimpl == 0 && iM2800getNanosecondsComponentimpl == 0) ? false : true;
        if (iM2799getMinutesComponentimpl != 0 || (z3 && z2)) {
            z = true;
        }
        if (z2) {
            sb.append(jM2796getInWholeHoursimpl);
            sb.append('H');
        }
        if (z) {
            sb.append(iM2799getMinutesComponentimpl);
            sb.append('M');
        }
        if (z3 || (!z2 && !z)) {
            m2788appendFractionalimpl(j, sb, iM2801getSecondsComponentimpl, iM2800getNanosecondsComponentimpl, 9, "S", true);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
