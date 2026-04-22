package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.util.MathHelpersKt;

/* JADX INFO: compiled from: Shadow.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ShadowKt {
    public static final Shadow lerp(Shadow shadow, Shadow shadow2, float f) {
        return new Shadow(ColorKt.m1314lerpjxsXWHM(shadow.m1400getColor0d7_KjU(), shadow2.m1400getColor0d7_KjU(), f), OffsetKt.m1175lerpWko1d7g(shadow.m1401getOffsetF1C5BW0(), shadow2.m1401getOffsetF1C5BW0(), f), MathHelpersKt.lerp(shadow.getBlurRadius(), shadow2.getBlurRadius(), f), null);
    }
}
