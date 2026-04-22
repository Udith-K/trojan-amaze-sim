package androidx.compose.material.ripple;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: RippleAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RippleAnimationKt {
    private static final float BoundedRippleExtraRadius = Dp.m2416constructorimpl(10);

    /* JADX INFO: renamed from: getRippleStartRadius-uvyYCjk, reason: not valid java name */
    public static final float m582getRippleStartRadiusuvyYCjk(long j) {
        return Math.max(Size.m1196getWidthimpl(j), Size.m1194getHeightimpl(j)) * 0.3f;
    }

    /* JADX INFO: renamed from: getRippleEndRadius-cSwnlzA, reason: not valid java name */
    public static final float m581getRippleEndRadiuscSwnlzA(Density density, boolean z, long j) {
        float fM1157getDistanceimpl = Offset.m1157getDistanceimpl(OffsetKt.Offset(Size.m1196getWidthimpl(j), Size.m1194getHeightimpl(j))) / 2.0f;
        return z ? fM1157getDistanceimpl + density.mo210toPx0680j_4(BoundedRippleExtraRadius) : fM1157getDistanceimpl;
    }
}
