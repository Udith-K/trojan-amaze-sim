package androidx.compose.ui.geometry;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Offset.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Offset {
    private final long packedValue;
    public static final Companion Companion = new Companion(null);
    private static final long Zero = m1151constructorimpl(0);
    private static final long Infinite = m1151constructorimpl(9187343241974906880L);
    private static final long Unspecified = m1151constructorimpl(9205357640488583168L);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Offset m1150boximpl(long j) {
        return new Offset(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1151constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1155equalsimpl(long j, Object obj) {
        return (obj instanceof Offset) && j == ((Offset) obj).m1168unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1156equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1161hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    /* JADX INFO: renamed from: isValid-impl, reason: not valid java name */
    public static final boolean m1162isValidimpl(long j) {
        long j2 = j & 9223372034707292159L;
        return (((~j2) & (j2 - 9187343246269874177L)) & (-9223372034707292160L)) == -9223372034707292160L;
    }

    public boolean equals(Object obj) {
        return m1155equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m1161hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m1168unboximpl() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: copy-dBAh8RU$default, reason: not valid java name */
    public static /* synthetic */ long m1153copydBAh8RU$default(long j, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Float.intBitsToFloat((int) (j >> 32));
        }
        if ((i & 2) != 0) {
            f2 = Float.intBitsToFloat((int) (BodyPartID.bodyIdMax & j));
        }
        return m1152copydBAh8RU(j, f, f2);
    }

    /* JADX INFO: renamed from: div-tuRUvjQ, reason: not valid java name */
    public static final long m1154divtuRUvjQ(long j, float f) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) / f;
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)) / f;
        return m1151constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: getDistance-impl, reason: not valid java name */
    public static final float m1157getDistanceimpl(long j) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
        return (float) Math.sqrt((fIntBitsToFloat * fIntBitsToFloat) + (fIntBitsToFloat2 * fIntBitsToFloat2));
    }

    /* JADX INFO: renamed from: getDistanceSquared-impl, reason: not valid java name */
    public static final float m1158getDistanceSquaredimpl(long j) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
        return (fIntBitsToFloat * fIntBitsToFloat) + (fIntBitsToFloat2 * fIntBitsToFloat2);
    }

    /* JADX INFO: renamed from: getX-impl, reason: not valid java name */
    public static final float m1159getXimpl(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* JADX INFO: renamed from: getY-impl, reason: not valid java name */
    public static final float m1160getYimpl(long j) {
        return Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: minus-MK-Hz9U, reason: not valid java name */
    public static final long m1163minusMKHz9U(long j, long j2) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) - Float.intBitsToFloat((int) (j2 >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)) - Float.intBitsToFloat((int) (j2 & BodyPartID.bodyIdMax));
        return m1151constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: plus-MK-Hz9U, reason: not valid java name */
    public static final long m1164plusMKHz9U(long j, long j2) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) + Float.intBitsToFloat((int) (j2 >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)) + Float.intBitsToFloat((int) (j2 & BodyPartID.bodyIdMax));
        return m1151constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: times-tuRUvjQ, reason: not valid java name */
    public static final long m1165timestuRUvjQ(long j, float f) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) * f;
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)) * f;
        return m1151constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & BodyPartID.bodyIdMax));
    }

    private /* synthetic */ Offset(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: copy-dBAh8RU, reason: not valid java name */
    public static final long m1152copydBAh8RU(long j, float f, float f2) {
        return m1151constructorimpl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: compiled from: Offset.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZero-F1C5BW0, reason: not valid java name */
        public final long m1171getZeroF1C5BW0() {
            return Offset.Zero;
        }

        /* JADX INFO: renamed from: getInfinite-F1C5BW0, reason: not valid java name */
        public final long m1169getInfiniteF1C5BW0() {
            return Offset.Infinite;
        }

        /* JADX INFO: renamed from: getUnspecified-F1C5BW0, reason: not valid java name */
        public final long m1170getUnspecifiedF1C5BW0() {
            return Offset.Unspecified;
        }
    }

    /* JADX INFO: renamed from: unaryMinus-F1C5BW0, reason: not valid java name */
    public static final long m1167unaryMinusF1C5BW0(long j) {
        return m1151constructorimpl(j ^ (-9223372034707292160L));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1166toStringimpl(long j) {
        if (OffsetKt.m1173isSpecifiedk4lQ0M(j)) {
            return "Offset(" + GeometryUtilsKt.toStringAsFixed(m1159getXimpl(j), 1) + ", " + GeometryUtilsKt.toStringAsFixed(m1160getYimpl(j), 1) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
        return "Offset.Unspecified";
    }

    public String toString() {
        return m1166toStringimpl(this.packedValue);
    }
}
