package androidx.compose.animation.core;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: SpringSimulation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Motion {
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m56constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: getValue-impl, reason: not valid java name */
    public static final float m57getValueimpl(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* JADX INFO: renamed from: getVelocity-impl, reason: not valid java name */
    public static final float m58getVelocityimpl(long j) {
        return Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
    }
}
