package androidx.compose.foundation;

import android.content.Context;
import android.widget.EdgeEffect;
import androidx.compose.ui.unit.AndroidDensity_androidKt;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: EdgeEffectCompat.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class GlowEdgeEffectCompat extends EdgeEffect {
    private float oppositeReleaseDelta;
    private final float oppositeReleaseDeltaThreshold;

    public GlowEdgeEffectCompat(Context context) {
        super(context);
        this.oppositeReleaseDeltaThreshold = AndroidDensity_androidKt.Density(context).mo210toPx0680j_4(Dp.m2416constructorimpl(1));
    }

    @Override // android.widget.EdgeEffect
    public void onPull(float f, float f2) {
        this.oppositeReleaseDelta = 0.0f;
        super.onPull(f, f2);
    }

    @Override // android.widget.EdgeEffect
    public void onPull(float f) {
        this.oppositeReleaseDelta = 0.0f;
        super.onPull(f);
    }

    @Override // android.widget.EdgeEffect
    public void onRelease() {
        this.oppositeReleaseDelta = 0.0f;
        super.onRelease();
    }

    @Override // android.widget.EdgeEffect
    public void onAbsorb(int i) {
        this.oppositeReleaseDelta = 0.0f;
        super.onAbsorb(i);
    }

    public final void releaseWithOppositeDelta(float f) {
        float f2 = this.oppositeReleaseDelta + f;
        this.oppositeReleaseDelta = f2;
        if (Math.abs(f2) > this.oppositeReleaseDeltaThreshold) {
            onRelease();
        }
    }
}
