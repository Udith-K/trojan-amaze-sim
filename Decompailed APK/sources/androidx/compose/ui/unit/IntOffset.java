package androidx.compose.ui.unit;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: IntOffset.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IntOffset {
    public static final Companion Companion = new Companion(null);
    private static final long Zero = m2452constructorimpl(0);
    private final long packedValue;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ IntOffset m2451boximpl(long j) {
        return new IntOffset(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2452constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2455equalsimpl(long j, Object obj) {
        return (obj instanceof IntOffset) && j == ((IntOffset) obj).m2463unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2456equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getX-impl, reason: not valid java name */
    public static final int m2457getXimpl(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: renamed from: getY-impl, reason: not valid java name */
    public static final int m2458getYimpl(long j) {
        return (int) (j & BodyPartID.bodyIdMax);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2459hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m2455equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m2459hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2463unboximpl() {
        return this.packedValue;
    }

    private /* synthetic */ IntOffset(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: copy-iSbpLlY$default, reason: not valid java name */
    public static /* synthetic */ long m2454copyiSbpLlY$default(long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = (int) (j >> 32);
        }
        if ((i3 & 2) != 0) {
            i2 = (int) (BodyPartID.bodyIdMax & j);
        }
        return m2453copyiSbpLlY(j, i, i2);
    }

    /* JADX INFO: renamed from: copy-iSbpLlY, reason: not valid java name */
    public static final long m2453copyiSbpLlY(long j, int i, int i2) {
        return m2452constructorimpl((((long) i) << 32) | (((long) i2) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: minus-qkQi6aY, reason: not valid java name */
    public static final long m2460minusqkQi6aY(long j, long j2) {
        return m2452constructorimpl((((long) (((int) (j >> 32)) - ((int) (j2 >> 32)))) << 32) | (((long) (((int) (j & BodyPartID.bodyIdMax)) - ((int) (j2 & BodyPartID.bodyIdMax)))) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: plus-qkQi6aY, reason: not valid java name */
    public static final long m2461plusqkQi6aY(long j, long j2) {
        return m2452constructorimpl((((long) (((int) (j >> 32)) + ((int) (j2 >> 32)))) << 32) | (((long) (((int) (j & BodyPartID.bodyIdMax)) + ((int) (j2 & BodyPartID.bodyIdMax)))) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2462toStringimpl(long j) {
        return CoreConstants.LEFT_PARENTHESIS_CHAR + m2457getXimpl(j) + ", " + m2458getYimpl(j) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public String toString() {
        return m2462toStringimpl(this.packedValue);
    }

    /* JADX INFO: compiled from: IntOffset.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZero-nOcc-ac, reason: not valid java name */
        public final long m2464getZeronOccac() {
            return IntOffset.Zero;
        }
    }
}
