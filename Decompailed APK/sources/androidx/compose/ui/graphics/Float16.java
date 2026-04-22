package androidx.compose.ui.graphics;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: Float16.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Float16 implements Comparable {
    public static final Companion Companion = new Companion(null);
    private static final short Epsilon = m1332constructorimpl((short) 5120);
    private static final short LowestValue = m1332constructorimpl((short) -1025);
    private static final short MaxValue = m1332constructorimpl((short) 31743);
    private static final short MinNormal = m1332constructorimpl((short) 1024);
    private static final short MinValue = m1332constructorimpl((short) 1);
    private static final short NaN = m1332constructorimpl((short) 32256);
    private static final short NegativeInfinity = m1332constructorimpl((short) -1024);
    private static final short NegativeZero = m1332constructorimpl(Short.MIN_VALUE);
    private static final short PositiveInfinity = m1332constructorimpl((short) 31744);
    private static final short PositiveZero = m1332constructorimpl((short) 0);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m1332constructorimpl(short s) {
        return s;
    }

    /* JADX INFO: compiled from: Float16.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m1331constructorimpl(float f) {
        int i;
        int iFloatToRawIntBits = Float.floatToRawIntBits(f);
        int i2 = iFloatToRawIntBits >>> 31;
        int i3 = (iFloatToRawIntBits >>> 23) & GF2Field.MASK;
        int i4 = 8388607 & iFloatToRawIntBits;
        int i5 = 31;
        int i6 = 0;
        if (i3 != 255) {
            int i7 = i3 - 112;
            if (i7 >= 31) {
                i5 = 49;
            } else if (i7 > 0) {
                i6 = i4 >> 13;
                if ((iFloatToRawIntBits & PKIFailureInfo.certConfirmed) != 0) {
                    i = (((i7 << 10) | i6) + 1) | (i2 << 15);
                    return m1332constructorimpl((short) i);
                }
                i5 = i7;
            } else if (i7 >= -10) {
                int i8 = (8388608 | i4) >> (1 - i7);
                if ((i8 & PKIFailureInfo.certConfirmed) != 0) {
                    i8 += 8192;
                }
                i5 = 0;
                i6 = i8 >> 13;
            } else {
                i5 = 0;
            }
        } else if (i4 != 0) {
            i6 = 512;
        }
        i = (i2 << 15) | (i5 << 10) | i6;
        return m1332constructorimpl((short) i);
    }
}
