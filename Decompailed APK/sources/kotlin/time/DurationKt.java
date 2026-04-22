package kotlin.time;

import ch.qos.logback.core.CoreConstants;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.time.Duration;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: Duration.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class DurationKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final long millisToNanos(long j) {
        return j * ((long) 1000000);
    }

    public static final long toDuration(long j, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        DurationUnit durationUnit = DurationUnit.NANOSECONDS;
        long jConvertDurationUnitOverflow = DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(4611686018426999999L, durationUnit, unit);
        if ((-jConvertDurationUnitOverflow) <= j && j <= jConvertDurationUnitOverflow) {
            return durationOfNanos(DurationUnitKt__DurationUnitJvmKt.convertDurationUnitOverflow(j, unit, durationUnit));
        }
        return durationOfMillis(RangesKt.coerceIn(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(j, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, 4611686018427387903L));
    }

    public static final long toDuration(double d, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        double dConvertDurationUnit = DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, unit, DurationUnit.NANOSECONDS);
        if (Double.isNaN(dConvertDurationUnit)) {
            throw new IllegalArgumentException("Duration value cannot be NaN.");
        }
        long jRoundToLong = MathKt.roundToLong(dConvertDurationUnit);
        if (-4611686018426999999L <= jRoundToLong && jRoundToLong < 4611686018427000000L) {
            return durationOfNanos(jRoundToLong);
        }
        return durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, unit, DurationUnit.MILLISECONDS)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long parseDuration(String str, boolean z) {
        long jM2810plusLRDsOJo;
        char cCharAt;
        char cCharAt2;
        int i;
        char cCharAt3;
        int length = str.length();
        if (length == 0) {
            throw new IllegalArgumentException("The string is empty");
        }
        Duration.Companion companion = Duration.Companion;
        long jM2818getZEROUwyO8pc = companion.m2818getZEROUwyO8pc();
        char cCharAt4 = str.charAt(0);
        boolean z2 = true;
        int length2 = (cCharAt4 == '+' || cCharAt4 == '-') ? 1 : 0;
        boolean z3 = length2 > 0;
        boolean z4 = z3 && StringsKt.startsWith$default((CharSequence) str, CoreConstants.DASH_CHAR, false, 2, (Object) null);
        if (length <= length2) {
            throw new IllegalArgumentException("No components");
        }
        char cCharAt5 = str.charAt(length2);
        char c = CoreConstants.COLON_CHAR;
        char c2 = '0';
        if (cCharAt5 == 'P') {
            int i2 = length2 + 1;
            if (i2 == length) {
                throw new IllegalArgumentException();
            }
            DurationUnit durationUnit = null;
            boolean z5 = false;
            while (i2 < length) {
                if (str.charAt(i2) != 'T') {
                    int i3 = i2;
                    while (i3 < str.length() && ((c2 <= (cCharAt3 = str.charAt(i3)) && cCharAt3 < c) || StringsKt.contains$default((CharSequence) "+-.", cCharAt3, false, 2, (Object) null))) {
                        i3++;
                        c = CoreConstants.COLON_CHAR;
                        c2 = '0';
                    }
                    Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
                    String strSubstring = str.substring(i2, i3);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    if (strSubstring.length() == 0) {
                        throw new IllegalArgumentException();
                    }
                    int length3 = i2 + strSubstring.length();
                    if (length3 < 0 || length3 >= str.length()) {
                        throw new IllegalArgumentException("Missing unit for value " + strSubstring);
                    }
                    char cCharAt6 = str.charAt(length3);
                    int i4 = length3 + 1;
                    DurationUnit durationUnitDurationUnitByIsoChar = DurationUnitKt__DurationUnitKt.durationUnitByIsoChar(cCharAt6, z5);
                    if (durationUnit != null && durationUnit.compareTo(durationUnitDurationUnitByIsoChar) <= 0) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                    }
                    int iIndexOf$default = StringsKt.indexOf$default((CharSequence) strSubstring, CoreConstants.DOT, 0, false, 6, (Object) null);
                    if (durationUnitDurationUnitByIsoChar != DurationUnit.SECONDS || iIndexOf$default <= 0) {
                        i = i4;
                        jM2818getZEROUwyO8pc = Duration.m2810plusLRDsOJo(jM2818getZEROUwyO8pc, toDuration(parseOverLongIsoComponent(strSubstring), durationUnitDurationUnitByIsoChar));
                    } else {
                        Intrinsics.checkNotNull(strSubstring, "null cannot be cast to non-null type java.lang.String");
                        String strSubstring2 = strSubstring.substring(0, iIndexOf$default);
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                        i = i4;
                        long jM2810plusLRDsOJo2 = Duration.m2810plusLRDsOJo(jM2818getZEROUwyO8pc, toDuration(parseOverLongIsoComponent(strSubstring2), durationUnitDurationUnitByIsoChar));
                        Intrinsics.checkNotNull(strSubstring, "null cannot be cast to non-null type java.lang.String");
                        String strSubstring3 = strSubstring.substring(iIndexOf$default);
                        Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                        jM2818getZEROUwyO8pc = Duration.m2810plusLRDsOJo(jM2810plusLRDsOJo2, toDuration(Double.parseDouble(strSubstring3), durationUnitDurationUnitByIsoChar));
                    }
                    i2 = i;
                    durationUnit = durationUnitDurationUnitByIsoChar;
                    c = CoreConstants.COLON_CHAR;
                    c2 = '0';
                    z2 = true;
                } else {
                    if (z5 || (i2 = i2 + 1) == length) {
                        throw new IllegalArgumentException();
                    }
                    z5 = z2;
                }
            }
        } else {
            if (z) {
                throw new IllegalArgumentException();
            }
            String str2 = "Unexpected order of duration components";
            if (StringsKt.regionMatches(str, length2, "Infinity", 0, Math.max(length - length2, 8), true)) {
                jM2818getZEROUwyO8pc = companion.m2817getINFINITEUwyO8pc();
            } else {
                boolean z6 = !z3;
                if (z3 && str.charAt(length2) == '(' && StringsKt.last(str) == ')') {
                    length2++;
                    length--;
                    if (length2 == length) {
                        throw new IllegalArgumentException("No components");
                    }
                    jM2810plusLRDsOJo = jM2818getZEROUwyO8pc;
                    z6 = true;
                } else {
                    jM2810plusLRDsOJo = jM2818getZEROUwyO8pc;
                }
                DurationUnit durationUnit2 = null;
                boolean z7 = false;
                while (length2 < length) {
                    if (z7 && z6) {
                        while (length2 < str.length() && str.charAt(length2) == ' ') {
                            length2++;
                        }
                    }
                    int i5 = length2;
                    while (i5 < str.length() && (('0' <= (cCharAt2 = str.charAt(i5)) && cCharAt2 < ':') || cCharAt2 == '.')) {
                        i5++;
                    }
                    Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
                    String strSubstring4 = str.substring(length2, i5);
                    Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
                    if (strSubstring4.length() == 0) {
                        throw new IllegalArgumentException();
                    }
                    int length4 = length2 + strSubstring4.length();
                    int i6 = length4;
                    while (i6 < str.length() && 'a' <= (cCharAt = str.charAt(i6)) && cCharAt < '{') {
                        i6++;
                    }
                    Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
                    String strSubstring5 = str.substring(length4, i6);
                    Intrinsics.checkNotNullExpressionValue(strSubstring5, "substring(...)");
                    length2 = length4 + strSubstring5.length();
                    DurationUnit durationUnitDurationUnitByShortName = DurationUnitKt__DurationUnitKt.durationUnitByShortName(strSubstring5);
                    if (durationUnit2 != null && durationUnit2.compareTo(durationUnitDurationUnitByShortName) <= 0) {
                        throw new IllegalArgumentException(str2);
                    }
                    String str3 = str2;
                    int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) strSubstring4, CoreConstants.DOT, 0, false, 6, (Object) null);
                    if (iIndexOf$default2 > 0) {
                        Intrinsics.checkNotNull(strSubstring4, "null cannot be cast to non-null type java.lang.String");
                        String strSubstring6 = strSubstring4.substring(0, iIndexOf$default2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring6, "substring(...)");
                        long jM2810plusLRDsOJo3 = Duration.m2810plusLRDsOJo(jM2810plusLRDsOJo, toDuration(Long.parseLong(strSubstring6), durationUnitDurationUnitByShortName));
                        Intrinsics.checkNotNull(strSubstring4, "null cannot be cast to non-null type java.lang.String");
                        String strSubstring7 = strSubstring4.substring(iIndexOf$default2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring7, "substring(...)");
                        jM2810plusLRDsOJo = Duration.m2810plusLRDsOJo(jM2810plusLRDsOJo3, toDuration(Double.parseDouble(strSubstring7), durationUnitDurationUnitByShortName));
                        length2 = length2;
                        if (length2 < length) {
                            throw new IllegalArgumentException("Fractional component must be last");
                        }
                    } else {
                        jM2810plusLRDsOJo = Duration.m2810plusLRDsOJo(jM2810plusLRDsOJo, toDuration(Long.parseLong(strSubstring4), durationUnitDurationUnitByShortName));
                    }
                    str2 = str3;
                    durationUnit2 = durationUnitDurationUnitByShortName;
                    z7 = true;
                }
                jM2818getZEROUwyO8pc = jM2810plusLRDsOJo;
            }
        }
        return z4 ? Duration.m2814unaryMinusUwyO8pc(jM2818getZEROUwyO8pc) : jM2818getZEROUwyO8pc;
    }

    private static final long parseOverLongIsoComponent(String str) {
        int length = str.length();
        int i = (length <= 0 || !StringsKt.contains$default((CharSequence) "+-", str.charAt(0), false, 2, (Object) null)) ? 0 : 1;
        if (length - i > 16) {
            Iterable intRange = new IntRange(i, StringsKt.getLastIndex(str));
            if (!(intRange instanceof Collection) || !((Collection) intRange).isEmpty()) {
                Iterator it = intRange.iterator();
                while (it.hasNext()) {
                    char cCharAt = str.charAt(((IntIterator) it).nextInt());
                    if ('0' > cCharAt || cCharAt >= ':') {
                    }
                }
            }
            if (str.charAt(0) == '-') {
                return Long.MIN_VALUE;
            }
            return Preferences.UPDATE_INTERVAL_DISABLED;
        }
        if (StringsKt.startsWith$default(str, "+", false, 2, (Object) null)) {
            str = StringsKt.drop(str, 1);
        }
        return Long.parseLong(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long nanosToMillis(long j) {
        return j / ((long) 1000000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanos(long j) {
        return Duration.m2791constructorimpl(j << 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillis(long j) {
        return Duration.m2791constructorimpl((j << 1) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOf(long j, int i) {
        return Duration.m2791constructorimpl((j << 1) + ((long) i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanosNormalized(long j) {
        if (-4611686018426999999L <= j && j < 4611686018427000000L) {
            return durationOfNanos(j);
        }
        return durationOfMillis(nanosToMillis(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillisNormalized(long j) {
        if (-4611686018426L <= j && j < 4611686018427L) {
            return durationOfNanos(millisToNanos(j));
        }
        return durationOfMillis(RangesKt.coerceIn(j, -4611686018427387903L, 4611686018427387903L));
    }
}
