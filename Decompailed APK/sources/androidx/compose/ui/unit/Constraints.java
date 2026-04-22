package androidx.compose.ui.unit;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Constraints.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Constraints {
    public static final Companion Companion = new Companion(null);
    private final long value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Constraints m2378boximpl(long j) {
        return new Constraints(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2379constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2382equalsimpl(long j, Object obj) {
        return (obj instanceof Constraints) && j == ((Constraints) obj).m2395unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2383equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getHasBoundedHeight-impl, reason: not valid java name */
    public static final boolean m2384getHasBoundedHeightimpl(long j) {
        int i = (int) (3 & j);
        int i2 = ((i & 1) << 1) + (((i & 2) >> 1) * 3);
        return (((int) (j >> (i2 + 46))) & ((1 << (18 - i2)) - 1)) != 0;
    }

    /* JADX INFO: renamed from: getHasBoundedWidth-impl, reason: not valid java name */
    public static final boolean m2385getHasBoundedWidthimpl(long j) {
        int i = (int) (3 & j);
        return (((int) (j >> 33)) & ((1 << ((((i & 1) << 1) + (((i & 2) >> 1) * 3)) + 13)) - 1)) != 0;
    }

    /* JADX INFO: renamed from: getHasFixedHeight-impl, reason: not valid java name */
    public static final boolean m2386getHasFixedHeightimpl(long j) {
        int i = (int) (3 & j);
        int i2 = ((i & 1) << 1) + (((i & 2) >> 1) * 3);
        int i3 = (1 << (18 - i2)) - 1;
        int i4 = ((int) (j >> (i2 + 15))) & i3;
        int i5 = ((int) (j >> (i2 + 46))) & i3;
        return i4 == (i5 == 0 ? Integer.MAX_VALUE : i5 - 1);
    }

    /* JADX INFO: renamed from: getHasFixedWidth-impl, reason: not valid java name */
    public static final boolean m2387getHasFixedWidthimpl(long j) {
        int i = (int) (3 & j);
        int i2 = (1 << ((((i & 1) << 1) + (((i & 2) >> 1) * 3)) + 13)) - 1;
        int i3 = ((int) (j >> 2)) & i2;
        int i4 = ((int) (j >> 33)) & i2;
        return i3 == (i4 == 0 ? Integer.MAX_VALUE : i4 - 1);
    }

    /* JADX INFO: renamed from: getMaxHeight-impl, reason: not valid java name */
    public static final int m2388getMaxHeightimpl(long j) {
        int i = (int) (3 & j);
        int i2 = ((i & 1) << 1) + (((i & 2) >> 1) * 3);
        int i3 = ((int) (j >> (i2 + 46))) & ((1 << (18 - i2)) - 1);
        if (i3 == 0) {
            return Integer.MAX_VALUE;
        }
        return i3 - 1;
    }

    /* JADX INFO: renamed from: getMaxWidth-impl, reason: not valid java name */
    public static final int m2389getMaxWidthimpl(long j) {
        int i = (int) (3 & j);
        int i2 = ((int) (j >> 33)) & ((1 << ((((i & 1) << 1) + (((i & 2) >> 1) * 3)) + 13)) - 1);
        if (i2 == 0) {
            return Integer.MAX_VALUE;
        }
        return i2 - 1;
    }

    /* JADX INFO: renamed from: getMinHeight-impl, reason: not valid java name */
    public static final int m2390getMinHeightimpl(long j) {
        int i = (int) (3 & j);
        int i2 = ((i & 1) << 1) + (((i & 2) >> 1) * 3);
        return ((int) (j >> (i2 + 15))) & ((1 << (18 - i2)) - 1);
    }

    /* JADX INFO: renamed from: getMinWidth-impl, reason: not valid java name */
    public static final int m2391getMinWidthimpl(long j) {
        int i = (int) (3 & j);
        return ((int) (j >> 2)) & ((1 << ((((i & 1) << 1) + (((i & 2) >> 1) * 3)) + 13)) - 1);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2392hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    /* JADX INFO: renamed from: isZero-impl, reason: not valid java name */
    public static final boolean m2393isZeroimpl(long j) {
        int i = (int) (3 & j);
        int i2 = ((i & 1) << 1) + (((i & 2) >> 1) * 3);
        return (((int) (j >> 33)) & ((1 << (i2 + 13)) - 1)) - 1 == 0 || (((int) (j >> (i2 + 46))) & ((1 << (18 - i2)) - 1)) - 1 == 0;
    }

    public boolean equals(Object obj) {
        return m2382equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2392hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2395unboximpl() {
        return this.value;
    }

    private /* synthetic */ Constraints(long j) {
        this.value = j;
    }

    /* JADX INFO: renamed from: copy-Zbe2FdA$default, reason: not valid java name */
    public static /* synthetic */ long m2381copyZbe2FdA$default(long j, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = m2391getMinWidthimpl(j);
        }
        int i6 = i;
        if ((i5 & 2) != 0) {
            i2 = m2389getMaxWidthimpl(j);
        }
        int i7 = i2;
        if ((i5 & 4) != 0) {
            i3 = m2390getMinHeightimpl(j);
        }
        int i8 = i3;
        if ((i5 & 8) != 0) {
            i4 = m2388getMaxHeightimpl(j);
        }
        return m2380copyZbe2FdA(j, i6, i7, i8, i4);
    }

    /* JADX INFO: renamed from: copy-Zbe2FdA, reason: not valid java name */
    public static final long m2380copyZbe2FdA(long j, int i, int i2, int i3, int i4) {
        if (!(i3 >= 0 && i >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("minHeight(" + i3 + ") and minWidth(" + i + ") must be >= 0");
        }
        if (!(i2 >= i)) {
            InlineClassHelperKt.throwIllegalArgumentException("maxWidth(" + i2 + ") must be >= minWidth(" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
        if (!(i4 >= i3)) {
            InlineClassHelperKt.throwIllegalArgumentException("maxHeight(" + i4 + ") must be >= minHeight(" + i3 + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
        return ConstraintsKt.createConstraints(i, i2, i3, i4);
    }

    public String toString() {
        return m2394toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2394toStringimpl(long j) {
        int iM2389getMaxWidthimpl = m2389getMaxWidthimpl(j);
        String strValueOf = "Infinity";
        String strValueOf2 = iM2389getMaxWidthimpl == Integer.MAX_VALUE ? "Infinity" : String.valueOf(iM2389getMaxWidthimpl);
        int iM2388getMaxHeightimpl = m2388getMaxHeightimpl(j);
        if (iM2388getMaxHeightimpl != Integer.MAX_VALUE) {
            strValueOf = String.valueOf(iM2388getMaxHeightimpl);
        }
        return "Constraints(minWidth = " + m2391getMinWidthimpl(j) + ", maxWidth = " + strValueOf2 + ", minHeight = " + m2390getMinHeightimpl(j) + ", maxHeight = " + strValueOf + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: Constraints.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: fixed-JhjzzOo, reason: not valid java name */
        public final long m2398fixedJhjzzOo(int i, int i2) {
            if (!(i >= 0 && i2 >= 0)) {
                InlineClassHelperKt.throwIllegalArgumentException("width(" + i + ") and height(" + i2 + ") must be >= 0");
            }
            return ConstraintsKt.createConstraints(i, i, i2, i2);
        }

        /* JADX INFO: renamed from: fixedWidth-OenEA2s, reason: not valid java name */
        public final long m2399fixedWidthOenEA2s(int i) {
            if (!(i >= 0)) {
                InlineClassHelperKt.throwIllegalArgumentException("width(" + i + ") must be >= 0");
            }
            return ConstraintsKt.createConstraints(i, i, 0, Integer.MAX_VALUE);
        }

        /* JADX INFO: renamed from: fitPrioritizingWidth-Zbe2FdA, reason: not valid java name */
        public final long m2397fitPrioritizingWidthZbe2FdA(int i, int i2, int i3, int i4) {
            int iMin = Math.min(i, 262142);
            int iMin2 = i2 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i2, 262142);
            int iMaxAllowedForSize = ConstraintsKt.maxAllowedForSize(iMin2 == Integer.MAX_VALUE ? iMin : iMin2);
            return ConstraintsKt.Constraints(iMin, iMin2, Math.min(iMaxAllowedForSize, i3), i4 != Integer.MAX_VALUE ? Math.min(iMaxAllowedForSize, i4) : Integer.MAX_VALUE);
        }

        /* JADX INFO: renamed from: fitPrioritizingHeight-Zbe2FdA, reason: not valid java name */
        public final long m2396fitPrioritizingHeightZbe2FdA(int i, int i2, int i3, int i4) {
            int iMin = Math.min(i3, 262142);
            int iMin2 = i4 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i4, 262142);
            int iMaxAllowedForSize = ConstraintsKt.maxAllowedForSize(iMin2 == Integer.MAX_VALUE ? iMin : iMin2);
            return ConstraintsKt.Constraints(Math.min(iMaxAllowedForSize, i), i2 != Integer.MAX_VALUE ? Math.min(iMaxAllowedForSize, i2) : Integer.MAX_VALUE, iMin, iMin2);
        }
    }
}
