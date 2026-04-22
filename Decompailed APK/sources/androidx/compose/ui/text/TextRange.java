package androidx.compose.ui.text;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: TextRange.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextRange {
    public static final Companion Companion = new Companion(null);
    private static final long Zero = TextRangeKt.TextRange(0);
    private final long packedValue;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TextRange m2105boximpl(long j) {
        return new TextRange(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2106constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2109equalsimpl(long j, Object obj) {
        return (obj instanceof TextRange) && j == ((TextRange) obj).m2121unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2110equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getEnd-impl, reason: not valid java name */
    public static final int m2112getEndimpl(long j) {
        return (int) (j & BodyPartID.bodyIdMax);
    }

    /* JADX INFO: renamed from: getStart-impl, reason: not valid java name */
    public static final int m2117getStartimpl(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2118hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m2109equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m2118hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2121unboximpl() {
        return this.packedValue;
    }

    private /* synthetic */ TextRange(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: getMin-impl, reason: not valid java name */
    public static final int m2115getMinimpl(long j) {
        return m2117getStartimpl(j) > m2112getEndimpl(j) ? m2112getEndimpl(j) : m2117getStartimpl(j);
    }

    /* JADX INFO: renamed from: getMax-impl, reason: not valid java name */
    public static final int m2114getMaximpl(long j) {
        return m2117getStartimpl(j) > m2112getEndimpl(j) ? m2117getStartimpl(j) : m2112getEndimpl(j);
    }

    /* JADX INFO: renamed from: getCollapsed-impl, reason: not valid java name */
    public static final boolean m2111getCollapsedimpl(long j) {
        return m2117getStartimpl(j) == m2112getEndimpl(j);
    }

    /* JADX INFO: renamed from: getReversed-impl, reason: not valid java name */
    public static final boolean m2116getReversedimpl(long j) {
        return m2117getStartimpl(j) > m2112getEndimpl(j);
    }

    /* JADX INFO: renamed from: getLength-impl, reason: not valid java name */
    public static final int m2113getLengthimpl(long j) {
        return m2114getMaximpl(j) - m2115getMinimpl(j);
    }

    /* JADX INFO: renamed from: intersects-5zc-tL8, reason: not valid java name */
    public static final boolean m2119intersects5zctL8(long j, long j2) {
        return m2115getMinimpl(j) < m2114getMaximpl(j2) && m2115getMinimpl(j2) < m2114getMaximpl(j);
    }

    /* JADX INFO: renamed from: contains-5zc-tL8, reason: not valid java name */
    public static final boolean m2107contains5zctL8(long j, long j2) {
        return m2115getMinimpl(j) <= m2115getMinimpl(j2) && m2114getMaximpl(j2) <= m2114getMaximpl(j);
    }

    /* JADX INFO: renamed from: contains-impl, reason: not valid java name */
    public static final boolean m2108containsimpl(long j, int i) {
        return i < m2114getMaximpl(j) && m2115getMinimpl(j) <= i;
    }

    public String toString() {
        return m2120toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2120toStringimpl(long j) {
        return "TextRange(" + m2117getStartimpl(j) + ", " + m2112getEndimpl(j) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: TextRange.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZero-d9O1mEE, reason: not valid java name */
        public final long m2122getZerod9O1mEE() {
            return TextRange.Zero;
        }
    }
}
