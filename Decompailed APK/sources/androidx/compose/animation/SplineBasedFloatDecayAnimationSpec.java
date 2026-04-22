package androidx.compose.animation;

import androidx.compose.animation.core.FloatDecayAnimationSpec;
import androidx.compose.ui.unit.Density;

/* JADX INFO: compiled from: SplineBasedFloatDecayAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SplineBasedFloatDecayAnimationSpec implements FloatDecayAnimationSpec {
    private final FlingCalculator flingCalculator;

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public float getAbsVelocityThreshold() {
        return 0.0f;
    }

    public SplineBasedFloatDecayAnimationSpec(Density density) {
        this.flingCalculator = new FlingCalculator(SplineBasedFloatDecayAnimationSpec_androidKt.getPlatformFlingScrollFriction(), density);
    }

    private final float flingDistance(float f) {
        return this.flingCalculator.flingDistance(f) * Math.signum(f);
    }

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public float getTargetValue(float f, float f2) {
        return f + flingDistance(f2);
    }

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public float getValueFromNanos(long j, float f, float f2) {
        return f + this.flingCalculator.flingInfo(f2).position(j / 1000000);
    }

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public long getDurationNanos(float f, float f2) {
        return this.flingCalculator.flingDuration(f2) * 1000000;
    }

    @Override // androidx.compose.animation.core.FloatDecayAnimationSpec
    public float getVelocityFromNanos(long j, float f, float f2) {
        return this.flingCalculator.flingInfo(f2).velocity(j / 1000000);
    }
}
