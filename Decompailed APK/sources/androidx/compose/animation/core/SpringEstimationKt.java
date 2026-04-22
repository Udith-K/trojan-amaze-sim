package androidx.compose.animation.core;

/* JADX INFO: compiled from: SpringEstimation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SpringEstimationKt {
    public static final long estimateAnimationDurationMillis(float f, float f2, float f3, float f4, float f5) {
        if (f2 == 0.0f) {
            return 9223372036854L;
        }
        return estimateAnimationDurationMillis(f, f2, f3, f4, f5);
    }

    public static final long estimateAnimationDurationMillis(double d, double d2, double d3, double d4, double d5) {
        double dSqrt = 2.0d * d2 * Math.sqrt(d);
        double d6 = (dSqrt * dSqrt) - (4.0d * d);
        double d7 = -dSqrt;
        ComplexDouble complexDoubleComplexSqrt = ComplexDoubleKt.complexSqrt(d6);
        complexDoubleComplexSqrt._real += d7;
        complexDoubleComplexSqrt._real *= 0.5d;
        complexDoubleComplexSqrt._imaginary *= 0.5d;
        ComplexDouble complexDoubleComplexSqrt2 = ComplexDoubleKt.complexSqrt(d6);
        double d8 = -1;
        complexDoubleComplexSqrt2._real *= d8;
        complexDoubleComplexSqrt2._imaginary *= d8;
        complexDoubleComplexSqrt2._real += d7;
        complexDoubleComplexSqrt2._real *= 0.5d;
        complexDoubleComplexSqrt2._imaginary *= 0.5d;
        return estimateDurationInternal(complexDoubleComplexSqrt, complexDoubleComplexSqrt2, d2, d3, d4, d5);
    }

    private static final double estimateUnderDamped(ComplexDouble complexDouble, double d, double d2, double d3) {
        double real = complexDouble.getReal();
        double imaginary = (d2 - (real * d)) / complexDouble.getImaginary();
        return Math.log(d3 / Math.sqrt((d * d) + (imaginary * imaginary))) / real;
    }

    private static final double estimateCriticallyDamped(ComplexDouble complexDouble, double d, double d2, double d3) {
        double d4 = d3;
        double real = complexDouble.getReal();
        double d5 = real * d;
        double d6 = d2 - d5;
        double dLog = Math.log(Math.abs(d4 / d)) / real;
        double dLog2 = Math.log(Math.abs(d4 / d6));
        double dLog3 = dLog2;
        for (int i = 0; i < 6; i++) {
            dLog3 = dLog2 - Math.log(Math.abs(dLog3 / real));
        }
        double d7 = dLog3 / real;
        if (!((Double.isInfinite(dLog) || Double.isNaN(dLog)) ? false : true)) {
            dLog = d7;
        } else if ((Double.isInfinite(d7) || Double.isNaN(d7)) ? false : true) {
            dLog = Math.max(dLog, d7);
        }
        double d8 = (-(d5 + d6)) / (real * d6);
        double d9 = real * d8;
        double dExp = (Math.exp(d9) * d) + (d6 * d8 * Math.exp(d9));
        if (Double.isNaN(d8) || d8 <= 0.0d) {
            d4 = -d4;
        } else if (d8 <= 0.0d || (-dExp) >= d4) {
            dLog = (-(2.0d / real)) - (d / d6);
        } else {
            if (d6 < 0.0d && d > 0.0d) {
                dLog = 0.0d;
            }
            d4 = -d4;
        }
        double dAbs = Double.MAX_VALUE;
        int i2 = 0;
        while (dAbs > 0.001d && i2 < 100) {
            i2++;
            double d10 = real * dLog;
            double d11 = d4;
            double dExp2 = dLog - ((((d + (d6 * dLog)) * Math.exp(d10)) + d4) / ((((((double) 1) + d10) * d6) + d5) * Math.exp(d10)));
            dAbs = Math.abs(dLog - dExp2);
            dLog = dExp2;
            d4 = d11;
        }
        return dLog;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00c6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final double estimateOverDamped(androidx.compose.animation.core.ComplexDouble r29, androidx.compose.animation.core.ComplexDouble r30, double r31, double r33, double r35) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.SpringEstimationKt.estimateOverDamped(androidx.compose.animation.core.ComplexDouble, androidx.compose.animation.core.ComplexDouble, double, double, double):double");
    }

    private static final double estimateOverDamped$xInflection(double d, double d2, double d3, double d4, double d5) {
        return (d * Math.exp(d2 * d3)) + (d4 * Math.exp(d5 * d3));
    }

    private static final long estimateDurationInternal(ComplexDouble complexDouble, ComplexDouble complexDouble2, double d, double d2, double d3, double d4) {
        double dEstimateCriticallyDamped;
        double d5 = d2;
        if (d3 == 0.0d && d5 == 0.0d) {
            return 0L;
        }
        if (d3 < 0.0d) {
            d5 = -d5;
        }
        double dAbs = Math.abs(d3);
        if (d > 1.0d) {
            dEstimateCriticallyDamped = estimateOverDamped(complexDouble, complexDouble2, dAbs, d5, d4);
        } else if (d < 1.0d) {
            dEstimateCriticallyDamped = estimateUnderDamped(complexDouble, dAbs, d5, d4);
        } else {
            dEstimateCriticallyDamped = estimateCriticallyDamped(complexDouble, dAbs, d5, d4);
        }
        return (long) (dEstimateCriticallyDamped * 1000.0d);
    }
}
