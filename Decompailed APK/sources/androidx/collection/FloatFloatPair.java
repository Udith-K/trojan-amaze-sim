package androidx.collection;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: FloatFloatPair.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FloatFloatPair {
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m18constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m17constructorimpl(float f, float f2) {
        return m18constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }
}
