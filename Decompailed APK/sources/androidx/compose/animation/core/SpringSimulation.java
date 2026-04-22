package androidx.compose.animation.core;

/* JADX INFO: compiled from: SpringSimulation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SpringSimulation {
    private double dampedFreq;
    private float finalPosition;
    private double gammaMinus;
    private double gammaPlus;
    private boolean initialized;
    private double naturalFreq = Math.sqrt(50.0d);
    private float dampingRatio = 1.0f;

    public SpringSimulation(float f) {
        this.finalPosition = f;
    }

    public final void setFinalPosition(float f) {
        this.finalPosition = f;
    }

    public final void setStiffness(float f) {
        if (getStiffness() <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.naturalFreq = Math.sqrt(f);
        this.initialized = false;
    }

    public final float getStiffness() {
        double d = this.naturalFreq;
        return (float) (d * d);
    }

    public final float getDampingRatio() {
        return this.dampingRatio;
    }

    public final void setDampingRatio(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.dampingRatio = f;
        this.initialized = false;
    }

    private final void init() {
        if (this.initialized) {
            return;
        }
        if (this.finalPosition == SpringSimulationKt.getUNSET()) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
        float f = this.dampingRatio;
        double d = ((double) f) * ((double) f);
        if (f > 1.0f) {
            double d2 = this.naturalFreq;
            double d3 = d - ((double) 1);
            this.gammaPlus = (((double) (-f)) * d2) + (d2 * Math.sqrt(d3));
            double d4 = -this.dampingRatio;
            double d5 = this.naturalFreq;
            this.gammaMinus = (d4 * d5) - (d5 * Math.sqrt(d3));
        } else if (f >= 0.0f && f < 1.0f) {
            this.dampedFreq = this.naturalFreq * Math.sqrt(((double) 1) - d);
        }
        this.initialized = true;
    }

    /* JADX INFO: renamed from: updateValues-IJZedt4$animation_core_release, reason: not valid java name */
    public final long m59updateValuesIJZedt4$animation_core_release(float f, float f2, long j) {
        double dCos;
        double dExp;
        init();
        float f3 = f - this.finalPosition;
        double d = j / 1000.0d;
        float f4 = this.dampingRatio;
        if (f4 > 1.0f) {
            double d2 = f3;
            double d3 = this.gammaMinus;
            double d4 = f2;
            double d5 = this.gammaPlus;
            double d6 = d2 - (((d3 * d2) - d4) / (d3 - d5));
            double d7 = ((d2 * d3) - d4) / (d3 - d5);
            dExp = (Math.exp(d3 * d) * d6) + (Math.exp(this.gammaPlus * d) * d7);
            double d8 = this.gammaMinus;
            double dExp2 = d6 * d8 * Math.exp(d8 * d);
            double d9 = this.gammaPlus;
            dCos = dExp2 + (d7 * d9 * Math.exp(d9 * d));
        } else if (f4 == 1.0f) {
            double d10 = this.naturalFreq;
            double d11 = f3;
            double d12 = ((double) f2) + (d10 * d11);
            double d13 = d11 + (d12 * d);
            double dExp3 = Math.exp((-d10) * d) * d13;
            double dExp4 = d13 * Math.exp((-this.naturalFreq) * d);
            double d14 = this.naturalFreq;
            dCos = (dExp4 * (-d14)) + (d12 * Math.exp((-d14) * d));
            dExp = dExp3;
        } else {
            double d15 = ((double) 1) / this.dampedFreq;
            double d16 = this.naturalFreq;
            double d17 = f3;
            double d18 = d15 * ((((double) f4) * d16 * d17) + ((double) f2));
            double dExp5 = Math.exp(((double) (-f4)) * d16 * d) * ((Math.cos(this.dampedFreq * d) * d17) + (Math.sin(this.dampedFreq * d) * d18));
            double d19 = this.naturalFreq;
            float f5 = this.dampingRatio;
            double d20 = (-d19) * dExp5 * ((double) f5);
            double dExp6 = Math.exp(((double) (-f5)) * d19 * d);
            double d21 = this.dampedFreq;
            double dSin = (-d21) * d17 * Math.sin(d21 * d);
            double d22 = this.dampedFreq;
            dCos = d20 + (dExp6 * (dSin + (d18 * d22 * Math.cos(d22 * d))));
            dExp = dExp5;
        }
        return SpringSimulationKt.Motion((float) (dExp + ((double) this.finalPosition)), (float) dCos);
    }
}
