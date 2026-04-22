package androidx.compose.ui.text;

import kotlin.ranges.RangesKt;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: TextRange.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextRangeKt {
    /* JADX INFO: renamed from: substring-FDrldGo, reason: not valid java name */
    public static final String m2124substringFDrldGo(CharSequence charSequence, long j) {
        return charSequence.subSequence(TextRange.m2115getMinimpl(j), TextRange.m2114getMaximpl(j)).toString();
    }

    public static final long TextRange(int i, int i2) {
        return TextRange.m2106constructorimpl(packWithCheck(i, i2));
    }

    public static final long TextRange(int i) {
        return TextRange(i, i);
    }

    /* JADX INFO: renamed from: coerceIn-8ffj60Q, reason: not valid java name */
    public static final long m2123coerceIn8ffj60Q(long j, int i, int i2) {
        int iCoerceIn = RangesKt.coerceIn(TextRange.m2117getStartimpl(j), i, i2);
        int iCoerceIn2 = RangesKt.coerceIn(TextRange.m2112getEndimpl(j), i, i2);
        return (iCoerceIn == TextRange.m2117getStartimpl(j) && iCoerceIn2 == TextRange.m2112getEndimpl(j)) ? j : TextRange(iCoerceIn, iCoerceIn2);
    }

    private static final long packWithCheck(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException(("start cannot be negative. [start: " + i + ", end: " + i2 + ']').toString());
        }
        if (i2 >= 0) {
            return (((long) i2) & BodyPartID.bodyIdMax) | (((long) i) << 32);
        }
        throw new IllegalArgumentException(("end cannot be negative. [start: " + i + ", end: " + i2 + ']').toString());
    }
}
