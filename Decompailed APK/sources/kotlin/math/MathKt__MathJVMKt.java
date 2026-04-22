package kotlin.math;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: MathJVM.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class MathKt__MathJVMKt extends MathKt__MathHKt {
    public static long roundToLong(double d) {
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(d);
    }

    public static int roundToInt(float f) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(f);
    }

    public static long roundToLong(float f) {
        return MathKt.roundToLong(f);
    }

    public static int getSign(int i) {
        return Integer.signum(i);
    }

    public static int getSign(long j) {
        return Long.signum(j);
    }
}
