package androidx.compose.ui.unit;

import androidx.compose.ui.util.MathHelpersKt;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: TextUnit.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextUnitKt {
    /* JADX INFO: renamed from: TextUnit-anM5pPY, reason: not valid java name */
    public static final long m2495TextUnitanM5pPY(float f, long j) {
        return pack(j, f);
    }

    /* JADX INFO: renamed from: isUnspecified--R2X_6o, reason: not valid java name */
    public static final boolean m2498isUnspecifiedR2X_6o(long j) {
        return TextUnit.m2488getRawTypeimpl(j) == 0;
    }

    public static final long getSp(float f) {
        return pack(4294967296L, f);
    }

    public static final long getSp(double d) {
        return pack(4294967296L, (float) d);
    }

    public static final long getSp(int i) {
        return pack(4294967296L, i);
    }

    public static final long pack(long j, float f) {
        return TextUnit.m2485constructorimpl(j | (((long) Float.floatToIntBits(f)) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: checkArithmetic--R2X_6o, reason: not valid java name */
    public static final void m2496checkArithmeticR2X_6o(long j) {
        if (m2498isUnspecifiedR2X_6o(j)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.");
        }
    }

    /* JADX INFO: renamed from: checkArithmetic-NB67dxo, reason: not valid java name */
    public static final void m2497checkArithmeticNB67dxo(long j, long j2) {
        if (m2498isUnspecifiedR2X_6o(j) || m2498isUnspecifiedR2X_6o(j2)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.");
        }
        if (TextUnitType.m2503equalsimpl0(TextUnit.m2489getTypeUIouoOA(j), TextUnit.m2489getTypeUIouoOA(j2))) {
            return;
        }
        throw new IllegalArgumentException(("Cannot perform operation for " + ((Object) TextUnitType.m2505toStringimpl(TextUnit.m2489getTypeUIouoOA(j))) + " and " + ((Object) TextUnitType.m2505toStringimpl(TextUnit.m2489getTypeUIouoOA(j2)))).toString());
    }

    /* JADX INFO: renamed from: lerp-C3pnCVY, reason: not valid java name */
    public static final long m2499lerpC3pnCVY(long j, long j2, float f) {
        m2497checkArithmeticNB67dxo(j, j2);
        return pack(TextUnit.m2488getRawTypeimpl(j), MathHelpersKt.lerp(TextUnit.m2490getValueimpl(j), TextUnit.m2490getValueimpl(j2), f));
    }
}
