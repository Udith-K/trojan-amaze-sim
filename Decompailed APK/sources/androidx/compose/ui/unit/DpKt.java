package androidx.compose.ui.unit;

import androidx.compose.ui.util.MathHelpersKt;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Dp.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DpKt {
    /* JADX INFO: renamed from: DpOffset-YgX7TsA, reason: not valid java name */
    public static final long m2426DpOffsetYgX7TsA(float f, float f2) {
        return DpOffset.m2430constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }

    /* JADX INFO: renamed from: DpSize-YgX7TsA, reason: not valid java name */
    public static final long m2427DpSizeYgX7TsA(float f, float f2) {
        return DpSize.m2439constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }

    /* JADX INFO: renamed from: lerp-Md-fbLM, reason: not valid java name */
    public static final float m2428lerpMdfbLM(float f, float f2, float f3) {
        return Dp.m2416constructorimpl(MathHelpersKt.lerp(f, f2, f3));
    }
}
