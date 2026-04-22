package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaceKt;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import ch.qos.logback.core.CoreConstants;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: Color.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Color {
    private final long value;
    public static final Companion Companion = new Companion(null);
    private static final long Black = ColorKt.Color(4278190080L);
    private static final long DarkGray = ColorKt.Color(4282664004L);
    private static final long Gray = ColorKt.Color(4287137928L);
    private static final long LightGray = ColorKt.Color(4291611852L);
    private static final long White = ColorKt.Color(BodyPartID.bodyIdMax);
    private static final long Red = ColorKt.Color(4294901760L);
    private static final long Green = ColorKt.Color(4278255360L);
    private static final long Blue = ColorKt.Color(4278190335L);
    private static final long Yellow = ColorKt.Color(4294967040L);
    private static final long Cyan = ColorKt.Color(4278255615L);
    private static final long Magenta = ColorKt.Color(4294902015L);
    private static final long Transparent = ColorKt.Color(0);
    private static final long Unspecified = ColorKt.Color(0.0f, 0.0f, 0.0f, 0.0f, ColorSpaces.INSTANCE.getUnspecified$ui_graphics_release());

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Color m1290boximpl(long j) {
        return new Color(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1291constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1295equalsimpl(long j, Object obj) {
        return (obj instanceof Color) && j == ((Color) obj).m1304unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1296equalsimpl0(long j, long j2) {
        return ULong.m2695equalsimpl0(j, j2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1302hashCodeimpl(long j) {
        return ULong.m2696hashCodeimpl(j);
    }

    public boolean equals(Object obj) {
        return m1295equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m1302hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m1304unboximpl() {
        return this.value;
    }

    private /* synthetic */ Color(long j) {
        this.value = j;
    }

    /* JADX INFO: renamed from: getColorSpace-impl, reason: not valid java name */
    public static final ColorSpace m1299getColorSpaceimpl(long j) {
        ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
        return colorSpaces.getColorSpacesArray$ui_graphics_release()[(int) ULong.m2693constructorimpl(j & 63)];
    }

    /* JADX INFO: renamed from: convert-vNxB06k, reason: not valid java name */
    public static final long m1292convertvNxB06k(long j, ColorSpace colorSpace) {
        return ColorSpaceKt.m1447connectYBCOT_4$default(m1299getColorSpaceimpl(j), colorSpace, 0, 2, null).mo1449transformToColorl2rxGTc$ui_graphics_release(j);
    }

    /* JADX INFO: renamed from: getRed-impl, reason: not valid java name */
    public static final float m1301getRedimpl(long j) {
        int i;
        int i2;
        int i3;
        if (ULong.m2693constructorimpl(63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m2693constructorimpl(ULong.m2693constructorimpl(j >>> 48) & 255))) / 255.0f;
        }
        short sM2693constructorimpl = (short) ULong.m2693constructorimpl(ULong.m2693constructorimpl(j >>> 48) & 65535);
        int i4 = Short.MIN_VALUE & sM2693constructorimpl;
        int i5 = ((65535 & sM2693constructorimpl) >>> 10) & 31;
        int i6 = sM2693constructorimpl & 1023;
        if (i5 != 0) {
            int i7 = i6 << 13;
            if (i5 == 31) {
                i = GF2Field.MASK;
                if (i7 != 0) {
                    i7 |= 4194304;
                }
            } else {
                i = i5 + 112;
            }
            int i8 = i;
            i2 = i7;
            i3 = i8;
        } else {
            if (i6 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i6 + 1056964608) - Float16Kt.Fp32DenormalFloat;
                return i4 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i3 = 0;
            i2 = 0;
        }
        return Float.intBitsToFloat((i3 << 23) | (i4 << 16) | i2);
    }

    /* JADX INFO: renamed from: getGreen-impl, reason: not valid java name */
    public static final float m1300getGreenimpl(long j) {
        int i;
        int i2;
        int i3;
        if (ULong.m2693constructorimpl(63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m2693constructorimpl(ULong.m2693constructorimpl(j >>> 40) & 255))) / 255.0f;
        }
        short sM2693constructorimpl = (short) ULong.m2693constructorimpl(ULong.m2693constructorimpl(j >>> 32) & 65535);
        int i4 = Short.MIN_VALUE & sM2693constructorimpl;
        int i5 = ((65535 & sM2693constructorimpl) >>> 10) & 31;
        int i6 = sM2693constructorimpl & 1023;
        if (i5 != 0) {
            int i7 = i6 << 13;
            if (i5 == 31) {
                i = GF2Field.MASK;
                if (i7 != 0) {
                    i7 |= 4194304;
                }
            } else {
                i = i5 + 112;
            }
            int i8 = i;
            i2 = i7;
            i3 = i8;
        } else {
            if (i6 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i6 + 1056964608) - Float16Kt.Fp32DenormalFloat;
                return i4 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i3 = 0;
            i2 = 0;
        }
        return Float.intBitsToFloat((i3 << 23) | (i4 << 16) | i2);
    }

    /* JADX INFO: renamed from: getBlue-impl, reason: not valid java name */
    public static final float m1298getBlueimpl(long j) {
        int i;
        int i2;
        int i3;
        if (ULong.m2693constructorimpl(63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m2693constructorimpl(ULong.m2693constructorimpl(j >>> 32) & 255))) / 255.0f;
        }
        short sM2693constructorimpl = (short) ULong.m2693constructorimpl(ULong.m2693constructorimpl(j >>> 16) & 65535);
        int i4 = Short.MIN_VALUE & sM2693constructorimpl;
        int i5 = ((65535 & sM2693constructorimpl) >>> 10) & 31;
        int i6 = sM2693constructorimpl & 1023;
        if (i5 != 0) {
            int i7 = i6 << 13;
            if (i5 == 31) {
                i = GF2Field.MASK;
                if (i7 != 0) {
                    i7 |= 4194304;
                }
            } else {
                i = i5 + 112;
            }
            int i8 = i;
            i2 = i7;
            i3 = i8;
        } else {
            if (i6 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i6 + 1056964608) - Float16Kt.Fp32DenormalFloat;
                return i4 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i3 = 0;
            i2 = 0;
        }
        return Float.intBitsToFloat((i3 << 23) | (i4 << 16) | i2);
    }

    /* JADX INFO: renamed from: getAlpha-impl, reason: not valid java name */
    public static final float m1297getAlphaimpl(long j) {
        float fUlongToDouble;
        float f;
        if (ULong.m2693constructorimpl(63 & j) == 0) {
            fUlongToDouble = (float) UnsignedKt.ulongToDouble(ULong.m2693constructorimpl(ULong.m2693constructorimpl(j >>> 56) & 255));
            f = 255.0f;
        } else {
            fUlongToDouble = (float) UnsignedKt.ulongToDouble(ULong.m2693constructorimpl(ULong.m2693constructorimpl(j >>> 6) & 1023));
            f = 1023.0f;
        }
        return fUlongToDouble / f;
    }

    /* JADX INFO: renamed from: copy-wmQWz5c$default, reason: not valid java name */
    public static /* synthetic */ long m1294copywmQWz5c$default(long j, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = m1297getAlphaimpl(j);
        }
        float f5 = f;
        if ((i & 2) != 0) {
            f2 = m1301getRedimpl(j);
        }
        float f6 = f2;
        if ((i & 4) != 0) {
            f3 = m1300getGreenimpl(j);
        }
        float f7 = f3;
        if ((i & 8) != 0) {
            f4 = m1298getBlueimpl(j);
        }
        return m1293copywmQWz5c(j, f5, f6, f7, f4);
    }

    /* JADX INFO: renamed from: copy-wmQWz5c, reason: not valid java name */
    public static final long m1293copywmQWz5c(long j, float f, float f2, float f3, float f4) {
        return ColorKt.Color(f2, f3, f4, f, m1299getColorSpaceimpl(j));
    }

    public String toString() {
        return m1303toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1303toStringimpl(long j) {
        return "Color(" + m1301getRedimpl(j) + ", " + m1300getGreenimpl(j) + ", " + m1298getBlueimpl(j) + ", " + m1297getAlphaimpl(j) + ", " + m1299getColorSpaceimpl(j).getName() + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: Color.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getBlack-0d7_KjU, reason: not valid java name */
        public final long m1305getBlack0d7_KjU() {
            return Color.Black;
        }

        /* JADX INFO: renamed from: getWhite-0d7_KjU, reason: not valid java name */
        public final long m1310getWhite0d7_KjU() {
            return Color.White;
        }

        /* JADX INFO: renamed from: getRed-0d7_KjU, reason: not valid java name */
        public final long m1307getRed0d7_KjU() {
            return Color.Red;
        }

        /* JADX INFO: renamed from: getBlue-0d7_KjU, reason: not valid java name */
        public final long m1306getBlue0d7_KjU() {
            return Color.Blue;
        }

        /* JADX INFO: renamed from: getTransparent-0d7_KjU, reason: not valid java name */
        public final long m1308getTransparent0d7_KjU() {
            return Color.Transparent;
        }

        /* JADX INFO: renamed from: getUnspecified-0d7_KjU, reason: not valid java name */
        public final long m1309getUnspecified0d7_KjU() {
            return Color.Unspecified;
        }
    }
}
