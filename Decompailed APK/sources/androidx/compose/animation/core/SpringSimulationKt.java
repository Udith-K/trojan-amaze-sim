package androidx.compose.animation.core;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: SpringSimulation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SpringSimulationKt {
    private static final float UNSET = Float.MAX_VALUE;

    public static final long Motion(float f, float f2) {
        return Motion.m56constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }

    public static final float getUNSET() {
        return UNSET;
    }
}
