package androidx.compose.ui.geometry;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Size {
    private final long packedValue;
    public static final Companion Companion = new Companion(null);
    private static final long Zero = m1191constructorimpl(0);
    private static final long Unspecified = m1191constructorimpl(9205357640488583168L);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Size m1190boximpl(long j) {
        return new Size(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1191constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1192equalsimpl(long j, Object obj) {
        return (obj instanceof Size) && j == ((Size) obj).m1200unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1193equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1197hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m1192equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m1197hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m1200unboximpl() {
        return this.packedValue;
    }

    private /* synthetic */ Size(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: getWidth-impl, reason: not valid java name */
    public static final float m1196getWidthimpl(long j) {
        if (j == 9205357640488583168L) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* JADX INFO: renamed from: getHeight-impl, reason: not valid java name */
    public static final float m1194getHeightimpl(long j) {
        if (j == 9205357640488583168L) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        return Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: compiled from: Size.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZero-NH-jbRc, reason: not valid java name */
        public final long m1202getZeroNHjbRc() {
            return Size.Zero;
        }

        /* JADX INFO: renamed from: getUnspecified-NH-jbRc, reason: not valid java name */
        public final long m1201getUnspecifiedNHjbRc() {
            return Size.Unspecified;
        }
    }

    /* JADX INFO: renamed from: isEmpty-impl, reason: not valid java name */
    public static final boolean m1198isEmptyimpl(long j) {
        if (j == 9205357640488583168L) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        long j2 = j & (~((((-9223372034707292160L) & j) >>> 31) * ((long) (-1))));
        return ((j2 & BodyPartID.bodyIdMax) & (j2 >>> 32)) == 0;
    }

    /* JADX INFO: renamed from: getMinDimension-impl, reason: not valid java name */
    public static final float m1195getMinDimensionimpl(long j) {
        if (j == 9205357640488583168L) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        return Math.min(Float.intBitsToFloat((int) ((j >> 32) & 2147483647L)), Float.intBitsToFloat((int) (j & 2147483647L)));
    }

    public String toString() {
        return m1199toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1199toStringimpl(long j) {
        if (j != 9205357640488583168L) {
            return "Size(" + GeometryUtilsKt.toStringAsFixed(m1196getWidthimpl(j), 1) + ", " + GeometryUtilsKt.toStringAsFixed(m1194getHeightimpl(j), 1) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
        return "Size.Unspecified";
    }
}
