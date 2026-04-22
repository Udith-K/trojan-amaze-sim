package androidx.compose.ui.geometry;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SizeKt {
    public static final long Size(float f, float f2) {
        return Size.m1191constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }

    /* JADX INFO: renamed from: toRect-uvyYCjk, reason: not valid java name */
    public static final Rect m1204toRectuvyYCjk(long j) {
        return RectKt.m1183Recttz77jQw(Offset.Companion.m1171getZeroF1C5BW0(), j);
    }

    /* JADX INFO: renamed from: getCenter-uvyYCjk, reason: not valid java name */
    public static final long m1203getCenteruvyYCjk(long j) {
        if (j == 9205357640488583168L) {
            InlineClassHelperKt.throwIllegalStateException("Size is unspecified");
        }
        return OffsetKt.Offset(Float.intBitsToFloat((int) (j >> 32)) / 2.0f, Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)) / 2.0f);
    }
}
