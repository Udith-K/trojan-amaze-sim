package androidx.compose.animation;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.compose.ui.unit.Density;
import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: FlingCalculator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlingCalculator {
    private final Density density;
    private final float friction;
    private final float magicPhysicalCoefficient;

    public FlingCalculator(float f, Density density) {
        this.friction = f;
        this.density = density;
        this.magicPhysicalCoefficient = computeDeceleration(density);
    }

    private final float computeDeceleration(Density density) {
        return FlingCalculatorKt.computeDeceleration(0.84f, density.getDensity());
    }

    private final double getSplineDeceleration(float f) {
        return AndroidFlingSpline.INSTANCE.deceleration(f, this.friction * this.magicPhysicalCoefficient);
    }

    public final long flingDuration(float f) {
        return (long) (Math.exp(getSplineDeceleration(f) / (((double) FlingCalculatorKt.DecelerationRate) - 1.0d)) * 1000.0d);
    }

    public final float flingDistance(float f) {
        return (float) (((double) (this.friction * this.magicPhysicalCoefficient)) * Math.exp((((double) FlingCalculatorKt.DecelerationRate) / (((double) FlingCalculatorKt.DecelerationRate) - 1.0d)) * getSplineDeceleration(f)));
    }

    public final FlingInfo flingInfo(float f) {
        double splineDeceleration = getSplineDeceleration(f);
        double d = ((double) FlingCalculatorKt.DecelerationRate) - 1.0d;
        return new FlingInfo(f, (float) (((double) (this.friction * this.magicPhysicalCoefficient)) * Math.exp((((double) FlingCalculatorKt.DecelerationRate) / d) * splineDeceleration)), (long) (Math.exp(splineDeceleration / d) * 1000.0d));
    }

    /* JADX INFO: compiled from: FlingCalculator.kt */
    public static final class FlingInfo {
        private final float distance;
        private final long duration;
        private final float initialVelocity;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FlingInfo)) {
                return false;
            }
            FlingInfo flingInfo = (FlingInfo) obj;
            return Float.compare(this.initialVelocity, flingInfo.initialVelocity) == 0 && Float.compare(this.distance, flingInfo.distance) == 0 && this.duration == flingInfo.duration;
        }

        public int hashCode() {
            return (((Float.floatToIntBits(this.initialVelocity) * 31) + Float.floatToIntBits(this.distance)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.duration);
        }

        public String toString() {
            return "FlingInfo(initialVelocity=" + this.initialVelocity + ", distance=" + this.distance + ", duration=" + this.duration + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public FlingInfo(float f, float f2, long j) {
            this.initialVelocity = f;
            this.distance = f2;
            this.duration = j;
        }

        public final float position(long j) {
            long j2 = this.duration;
            return this.distance * Math.signum(this.initialVelocity) * AndroidFlingSpline.INSTANCE.flingPosition(j2 > 0 ? j / j2 : 1.0f).getDistanceCoefficient();
        }

        public final float velocity(long j) {
            long j2 = this.duration;
            return (((AndroidFlingSpline.INSTANCE.flingPosition(j2 > 0 ? j / j2 : 1.0f).getVelocityCoefficient() * Math.signum(this.initialVelocity)) * this.distance) / this.duration) * 1000.0f;
        }
    }
}
