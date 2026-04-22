package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorModel;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.DoubleFunction;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: Color.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ColorKt {
    /* JADX WARN: Removed duplicated region for block: B:112:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0126  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long Color(float r20, float r21, float r22, float r23, androidx.compose.ui.graphics.colorspace.ColorSpace r24) {
        /*
            Method dump skipped, instruction units count: 507
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.ColorKt.Color(float, float, float, float, androidx.compose.ui.graphics.colorspace.ColorSpace):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ee  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long UncheckedColor(float r17, float r18, float r19, float r20, androidx.compose.ui.graphics.colorspace.ColorSpace r21) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.ColorKt.UncheckedColor(float, float, float, float, androidx.compose.ui.graphics.colorspace.ColorSpace):long");
    }

    public static final long Color(int i) {
        return Color.m1291constructorimpl(ULong.m2693constructorimpl(ULong.m2693constructorimpl(i) << 32));
    }

    public static final long Color(long j) {
        return Color.m1291constructorimpl(ULong.m2693constructorimpl(j << 32));
    }

    public static /* synthetic */ long Color$default(int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = GF2Field.MASK;
        }
        return Color(i, i2, i3, i4);
    }

    public static final long Color(int i, int i2, int i3, int i4) {
        return Color(((i & GF2Field.MASK) << 16) | ((i4 & GF2Field.MASK) << 24) | ((i2 & GF2Field.MASK) << 8) | (i3 & GF2Field.MASK));
    }

    /* JADX INFO: renamed from: lerp-jxsXWHM, reason: not valid java name */
    public static final long m1314lerpjxsXWHM(long j, long j2, float f) {
        ColorSpace oklab = ColorSpaces.INSTANCE.getOklab();
        long jM1292convertvNxB06k = Color.m1292convertvNxB06k(j, oklab);
        long jM1292convertvNxB06k2 = Color.m1292convertvNxB06k(j2, oklab);
        float fM1297getAlphaimpl = Color.m1297getAlphaimpl(jM1292convertvNxB06k);
        float fM1301getRedimpl = Color.m1301getRedimpl(jM1292convertvNxB06k);
        float fM1300getGreenimpl = Color.m1300getGreenimpl(jM1292convertvNxB06k);
        float fM1298getBlueimpl = Color.m1298getBlueimpl(jM1292convertvNxB06k);
        float fM1297getAlphaimpl2 = Color.m1297getAlphaimpl(jM1292convertvNxB06k2);
        float fM1301getRedimpl2 = Color.m1301getRedimpl(jM1292convertvNxB06k2);
        float fM1300getGreenimpl2 = Color.m1300getGreenimpl(jM1292convertvNxB06k2);
        float fM1298getBlueimpl2 = Color.m1298getBlueimpl(jM1292convertvNxB06k2);
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        return Color.m1292convertvNxB06k(UncheckedColor(MathHelpersKt.lerp(fM1301getRedimpl, fM1301getRedimpl2, f), MathHelpersKt.lerp(fM1300getGreenimpl, fM1300getGreenimpl2, f), MathHelpersKt.lerp(fM1298getBlueimpl, fM1298getBlueimpl2, f), MathHelpersKt.lerp(fM1297getAlphaimpl, fM1297getAlphaimpl2, f), oklab), Color.m1299getColorSpaceimpl(j2));
    }

    /* JADX INFO: renamed from: compositeOver--OWjLjI, reason: not valid java name */
    public static final long m1313compositeOverOWjLjI(long j, long j2) {
        long jM1292convertvNxB06k = Color.m1292convertvNxB06k(j, Color.m1299getColorSpaceimpl(j2));
        float fM1297getAlphaimpl = Color.m1297getAlphaimpl(j2);
        float fM1297getAlphaimpl2 = Color.m1297getAlphaimpl(jM1292convertvNxB06k);
        float f = 1.0f - fM1297getAlphaimpl2;
        float f2 = (fM1297getAlphaimpl * f) + fM1297getAlphaimpl2;
        return UncheckedColor(f2 == 0.0f ? 0.0f : ((Color.m1301getRedimpl(jM1292convertvNxB06k) * fM1297getAlphaimpl2) + ((Color.m1301getRedimpl(j2) * fM1297getAlphaimpl) * f)) / f2, f2 == 0.0f ? 0.0f : ((Color.m1300getGreenimpl(jM1292convertvNxB06k) * fM1297getAlphaimpl2) + ((Color.m1300getGreenimpl(j2) * fM1297getAlphaimpl) * f)) / f2, f2 != 0.0f ? ((Color.m1298getBlueimpl(jM1292convertvNxB06k) * fM1297getAlphaimpl2) + ((Color.m1298getBlueimpl(j2) * fM1297getAlphaimpl) * f)) / f2 : 0.0f, f2, Color.m1299getColorSpaceimpl(j2));
    }

    /* JADX INFO: renamed from: luminance-8_81llA, reason: not valid java name */
    public static final float m1315luminance8_81llA(long j) {
        ColorSpace colorSpaceM1299getColorSpaceimpl = Color.m1299getColorSpaceimpl(j);
        if (!ColorModel.m1437equalsimpl0(colorSpaceM1299getColorSpaceimpl.m1444getModelxdoWZVw(), ColorModel.Companion.m1442getRgbxdoWZVw())) {
            InlineClassHelperKt.throwIllegalArgumentException("The specified color must be encoded in an RGB color space. The supplied color space is " + ((Object) ColorModel.m1440toStringimpl(colorSpaceM1299getColorSpaceimpl.m1444getModelxdoWZVw())));
        }
        Intrinsics.checkNotNull(colorSpaceM1299getColorSpaceimpl, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
        DoubleFunction eotfFunc$ui_graphics_release = ((Rgb) colorSpaceM1299getColorSpaceimpl).getEotfFunc$ui_graphics_release();
        float fInvoke = (float) ((eotfFunc$ui_graphics_release.invoke(Color.m1301getRedimpl(j)) * 0.2126d) + (eotfFunc$ui_graphics_release.invoke(Color.m1300getGreenimpl(j)) * 0.7152d) + (eotfFunc$ui_graphics_release.invoke(Color.m1298getBlueimpl(j)) * 0.0722d));
        if (fInvoke < 0.0f) {
            fInvoke = 0.0f;
        }
        if (fInvoke > 1.0f) {
            return 1.0f;
        }
        return fInvoke;
    }

    /* JADX INFO: renamed from: toArgb-8_81llA, reason: not valid java name */
    public static final int m1316toArgb8_81llA(long j) {
        return (int) ULong.m2693constructorimpl(Color.m1292convertvNxB06k(j, ColorSpaces.INSTANCE.getSrgb()) >>> 32);
    }
}
