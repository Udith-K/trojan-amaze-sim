package androidx.compose.ui.unit;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Velocity.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class VelocityKt {
    public static final long Velocity(float f, float f2) {
        return Velocity.m2511constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }
}
