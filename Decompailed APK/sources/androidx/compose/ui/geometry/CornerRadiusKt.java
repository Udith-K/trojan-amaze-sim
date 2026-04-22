package androidx.compose.ui.geometry;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: CornerRadius.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CornerRadiusKt {
    public static /* synthetic */ long CornerRadius$default(float f, float f2, int i, Object obj) {
        if ((i & 2) != 0) {
            f2 = f;
        }
        return CornerRadius(f, f2);
    }

    public static final long CornerRadius(float f, float f2) {
        return CornerRadius.m1143constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }
}
