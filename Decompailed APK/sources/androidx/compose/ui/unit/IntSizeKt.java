package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: IntSize.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class IntSizeKt {
    public static final long IntSize(int i, int i2) {
        return IntSize.m2472constructorimpl((((long) i2) & BodyPartID.bodyIdMax) | (((long) i) << 32));
    }

    /* JADX INFO: renamed from: getCenter-ozmzZPI, reason: not valid java name */
    public static final long m2481getCenterozmzZPI(long j) {
        return IntOffset.m2452constructorimpl((((j << 32) >> 33) & BodyPartID.bodyIdMax) | ((j >> 33) << 32));
    }

    /* JADX INFO: renamed from: toSize-ozmzZPI, reason: not valid java name */
    public static final long m2483toSizeozmzZPI(long j) {
        return SizeKt.Size(IntSize.m2476getWidthimpl(j), IntSize.m2475getHeightimpl(j));
    }

    /* JADX INFO: renamed from: roundToIntSize-uvyYCjk, reason: not valid java name */
    public static final long m2482roundToIntSizeuvyYCjk(long j) {
        int iRound = Math.round(Size.m1196getWidthimpl(j));
        return IntSize.m2472constructorimpl((((long) Math.round(Size.m1194getHeightimpl(j))) & BodyPartID.bodyIdMax) | (((long) iRound) << 32));
    }
}
