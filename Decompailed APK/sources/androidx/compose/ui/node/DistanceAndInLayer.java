package androidx.compose.ui.node;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: HitTestResult.kt */
/* JADX INFO: loaded from: classes.dex */
abstract class DistanceAndInLayer {
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1797constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: isInLayer-impl, reason: not valid java name */
    public static final boolean m1799isInLayerimpl(long j) {
        return ((int) (j & BodyPartID.bodyIdMax)) != 0;
    }

    /* JADX INFO: renamed from: getDistance-impl, reason: not valid java name */
    public static final float m1798getDistanceimpl(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* JADX INFO: renamed from: compareTo-S_HNhKs, reason: not valid java name */
    public static final int m1796compareToS_HNhKs(long j, long j2) {
        boolean zM1799isInLayerimpl = m1799isInLayerimpl(j);
        if (zM1799isInLayerimpl != m1799isInLayerimpl(j2)) {
            return zM1799isInLayerimpl ? -1 : 1;
        }
        return (int) Math.signum(m1798getDistanceimpl(j) - m1798getDistanceimpl(j2));
    }
}
