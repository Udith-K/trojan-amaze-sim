package androidx.vectordrawable.graphics.drawable;

import android.animation.TypeEvaluator;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: loaded from: classes.dex */
public class ArgbEvaluator implements TypeEvaluator {
    private static final ArgbEvaluator sInstance = new ArgbEvaluator();

    public static ArgbEvaluator getInstance() {
        return sInstance;
    }

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f, Object obj, Object obj2) {
        int iIntValue = ((Integer) obj).intValue();
        float f2 = ((iIntValue >> 24) & GF2Field.MASK) / 255.0f;
        float f3 = ((iIntValue >> 16) & GF2Field.MASK) / 255.0f;
        float f4 = ((iIntValue >> 8) & GF2Field.MASK) / 255.0f;
        int iIntValue2 = ((Integer) obj2).intValue();
        float f5 = ((iIntValue2 >> 24) & GF2Field.MASK) / 255.0f;
        float f6 = ((iIntValue2 >> 16) & GF2Field.MASK) / 255.0f;
        float f7 = ((iIntValue2 >> 8) & GF2Field.MASK) / 255.0f;
        float fPow = (float) Math.pow(f3, 2.2d);
        float fPow2 = (float) Math.pow(f4, 2.2d);
        float fPow3 = (float) Math.pow((iIntValue & GF2Field.MASK) / 255.0f, 2.2d);
        float fPow4 = (float) Math.pow(f6, 2.2d);
        float f8 = f2 + ((f5 - f2) * f);
        float fPow5 = fPow2 + ((((float) Math.pow(f7, 2.2d)) - fPow2) * f);
        float fPow6 = fPow3 + (f * (((float) Math.pow((iIntValue2 & GF2Field.MASK) / 255.0f, 2.2d)) - fPow3));
        return Integer.valueOf((Math.round(((float) Math.pow(fPow + ((fPow4 - fPow) * f), 0.45454545454545453d)) * 255.0f) << 16) | (Math.round(f8 * 255.0f) << 24) | (Math.round(((float) Math.pow(fPow5, 0.45454545454545453d)) * 255.0f) << 8) | Math.round(((float) Math.pow(fPow6, 0.45454545454545453d)) * 255.0f));
    }
}
