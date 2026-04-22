package androidx.compose.animation;

import ch.qos.logback.core.CoreConstants;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SplineBasedDecay.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidFlingSpline {
    public static final int $stable;
    public static final AndroidFlingSpline INSTANCE = new AndroidFlingSpline();
    private static final float[] SplinePositions;
    private static final float[] SplineTimes;

    private AndroidFlingSpline() {
    }

    static {
        float[] fArr = new float[101];
        SplinePositions = fArr;
        float[] fArr2 = new float[101];
        SplineTimes = fArr2;
        SplineBasedDecayKt.computeSplineInfo(fArr, fArr2, 100);
        $stable = 8;
    }

    public final FlingResult flingPosition(float f) {
        float f2 = 0.0f;
        float f3 = 1.0f;
        float fCoerceIn = RangesKt.coerceIn(f, 0.0f, 1.0f);
        float f4 = 100;
        int i = (int) (f4 * fCoerceIn);
        if (i < 100) {
            float f5 = i / f4;
            int i2 = i + 1;
            float f6 = i2 / f4;
            float[] fArr = SplinePositions;
            float f7 = fArr[i];
            float f8 = (fArr[i2] - f7) / (f6 - f5);
            float f9 = ((fCoerceIn - f5) * f8) + f7;
            f2 = f8;
            f3 = f9;
        }
        return new FlingResult(f3, f2);
    }

    public final double deceleration(float f, float f2) {
        return Math.log(((double) (Math.abs(f) * 0.35f)) / ((double) f2));
    }

    /* JADX INFO: compiled from: SplineBasedDecay.kt */
    public static final class FlingResult {
        private final float distanceCoefficient;
        private final float velocityCoefficient;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FlingResult)) {
                return false;
            }
            FlingResult flingResult = (FlingResult) obj;
            return Float.compare(this.distanceCoefficient, flingResult.distanceCoefficient) == 0 && Float.compare(this.velocityCoefficient, flingResult.velocityCoefficient) == 0;
        }

        public int hashCode() {
            return (Float.floatToIntBits(this.distanceCoefficient) * 31) + Float.floatToIntBits(this.velocityCoefficient);
        }

        public String toString() {
            return "FlingResult(distanceCoefficient=" + this.distanceCoefficient + ", velocityCoefficient=" + this.velocityCoefficient + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public FlingResult(float f, float f2) {
            this.distanceCoefficient = f;
            this.velocityCoefficient = f2;
        }

        public final float getDistanceCoefficient() {
            return this.distanceCoefficient;
        }

        public final float getVelocityCoefficient() {
            return this.velocityCoefficient;
        }
    }
}
