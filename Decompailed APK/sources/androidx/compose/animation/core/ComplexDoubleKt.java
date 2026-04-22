package androidx.compose.animation.core;

/* JADX INFO: compiled from: ComplexDouble.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ComplexDoubleKt {
    public static final ComplexDouble complexSqrt(double d) {
        if (d < 0.0d) {
            return new ComplexDouble(0.0d, Math.sqrt(Math.abs(d)));
        }
        return new ComplexDouble(Math.sqrt(d), 0.0d);
    }
}
