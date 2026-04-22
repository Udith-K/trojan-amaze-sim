package androidx.compose.ui.graphics;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: TransformOrigin.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TransformOriginKt {
    public static final long TransformOrigin(float f, float f2) {
        return TransformOrigin.m1426constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }
}
