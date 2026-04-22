package androidx.compose.animation.core;

import androidx.compose.animation.core.FloatAnimationSpec;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: FloatAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FloatTweenSpec implements FloatAnimationSpec {
    private final int delay;
    private final long delayNanos;
    private final int duration;
    private final long durationNanos;
    private final Easing easing;

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public /* synthetic */ float getEndVelocity(float f, float f2, float f3) {
        return getVelocityFromNanos(getDurationNanos(f, f2, f3), f, f2, f3);
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public /* bridge */ /* synthetic */ VectorizedAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return vectorize(twoWayConverter);
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec, androidx.compose.animation.core.AnimationSpec
    public /* synthetic */ VectorizedFloatAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return FloatAnimationSpec.CC.m53$default$vectorize((FloatAnimationSpec) this, twoWayConverter);
    }

    public FloatTweenSpec(int i, int i2, Easing easing) {
        this.duration = i;
        this.delay = i2;
        this.easing = easing;
        this.durationNanos = ((long) i) * 1000000;
        this.delayNanos = ((long) i2) * 1000000;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public float getValueFromNanos(long j, float f, float f2, float f3) {
        float fClampPlayTimeNanos = this.duration == 0 ? 1.0f : clampPlayTimeNanos(j) / this.durationNanos;
        Easing easing = this.easing;
        if (fClampPlayTimeNanos < 0.0f) {
            fClampPlayTimeNanos = 0.0f;
        }
        return VectorConvertersKt.lerp(f, f2, easing.transform(fClampPlayTimeNanos <= 1.0f ? fClampPlayTimeNanos : 1.0f));
    }

    private final long clampPlayTimeNanos(long j) {
        return RangesKt.coerceIn(j - this.delayNanos, 0L, this.durationNanos);
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public long getDurationNanos(float f, float f2, float f3) {
        return ((long) (this.delay + this.duration)) * 1000000;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public float getVelocityFromNanos(long j, float f, float f2, float f3) {
        long jClampPlayTimeNanos = clampPlayTimeNanos(j);
        if (jClampPlayTimeNanos < 0) {
            return 0.0f;
        }
        if (jClampPlayTimeNanos == 0) {
            return f3;
        }
        return (getValueFromNanos(jClampPlayTimeNanos, f, f2, f3) - getValueFromNanos(jClampPlayTimeNanos - 1000000, f, f2, f3)) * 1000.0f;
    }
}
