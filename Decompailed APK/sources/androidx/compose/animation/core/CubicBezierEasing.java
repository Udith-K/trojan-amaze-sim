package androidx.compose.animation.core;

import androidx.compose.ui.graphics.BezierKt;
import ch.qos.logback.core.CoreConstants;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Easing.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CubicBezierEasing implements Easing {
    private final float a;
    private final float b;
    private final float c;
    private final float d;
    private final float max;
    private final float min;

    public CubicBezierEasing(float f, float f2, float f3, float f4) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        if (!((Float.isNaN(f) || Float.isNaN(f2) || Float.isNaN(f3) || Float.isNaN(f4)) ? false : true)) {
            PreconditionsKt.throwIllegalArgumentException("Parameters to CubicBezierEasing cannot be NaN. Actual parameters are: " + f + ", " + f2 + ", " + f3 + ", " + f4 + CoreConstants.DOT);
        }
        long jComputeCubicVerticalBounds = BezierKt.computeCubicVerticalBounds(0.0f, f2, f4, 1.0f, new float[5], 0);
        this.min = Float.intBitsToFloat((int) (jComputeCubicVerticalBounds >> 32));
        this.max = Float.intBitsToFloat((int) (jComputeCubicVerticalBounds & BodyPartID.bodyIdMax));
    }

    @Override // androidx.compose.animation.core.Easing
    public float transform(float f) {
        if (f <= 0.0f || f >= 1.0f) {
            return f;
        }
        float fFindFirstCubicRoot = BezierKt.findFirstCubicRoot(0.0f - f, this.a - f, this.c - f, 1.0f - f);
        if (Float.isNaN(fFindFirstCubicRoot)) {
            throwNoSolution(f);
        }
        float fEvaluateCubic = BezierKt.evaluateCubic(this.b, this.d, fFindFirstCubicRoot);
        float f2 = this.min;
        float f3 = this.max;
        if (fEvaluateCubic < f2) {
            fEvaluateCubic = f2;
        }
        return fEvaluateCubic > f3 ? f3 : fEvaluateCubic;
    }

    private final void throwNoSolution(float f) {
        throw new IllegalArgumentException("The cubic curve with parameters (" + this.a + ", " + this.b + ", " + this.c + ", " + this.d + ") has no solution at " + f);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CubicBezierEasing) {
            CubicBezierEasing cubicBezierEasing = (CubicBezierEasing) obj;
            if (this.a == cubicBezierEasing.a && this.b == cubicBezierEasing.b && this.c == cubicBezierEasing.c && this.d == cubicBezierEasing.d) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((Float.floatToIntBits(this.a) * 31) + Float.floatToIntBits(this.b)) * 31) + Float.floatToIntBits(this.c)) * 31) + Float.floatToIntBits(this.d);
    }

    public String toString() {
        return "CubicBezierEasing(a=" + this.a + ", b=" + this.b + ", c=" + this.c + ", d=" + this.d + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
