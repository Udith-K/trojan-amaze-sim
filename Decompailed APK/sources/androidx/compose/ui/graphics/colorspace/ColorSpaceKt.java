package androidx.compose.ui.graphics.colorspace;

import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.graphics.colorspace.ColorModel;
import androidx.compose.ui.graphics.colorspace.Connector;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ColorSpace.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ColorSpaceKt {
    /* JADX INFO: renamed from: createConnector-YBCOT_4, reason: not valid java name */
    private static final Connector m1448createConnectorYBCOT_4(ColorSpace colorSpace, ColorSpace colorSpace2, int i) {
        Connector connector;
        if (colorSpace == colorSpace2) {
            return Connector.Companion.identity$ui_graphics_release(colorSpace);
        }
        long jM1444getModelxdoWZVw = colorSpace.m1444getModelxdoWZVw();
        ColorModel.Companion companion = ColorModel.Companion;
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (ColorModel.m1437equalsimpl0(jM1444getModelxdoWZVw, companion.m1442getRgbxdoWZVw()) && ColorModel.m1437equalsimpl0(colorSpace2.m1444getModelxdoWZVw(), companion.m1442getRgbxdoWZVw())) {
            Intrinsics.checkNotNull(colorSpace, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
            Intrinsics.checkNotNull(colorSpace2, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
            connector = new Connector.RgbConnector((Rgb) colorSpace, (Rgb) colorSpace2, i, defaultConstructorMarker);
        } else {
            connector = new Connector(colorSpace, colorSpace2, i, defaultConstructorMarker);
        }
        return connector;
    }

    /* JADX INFO: renamed from: connect-YBCOT_4$default, reason: not valid java name */
    public static /* synthetic */ Connector m1447connectYBCOT_4$default(ColorSpace colorSpace, ColorSpace colorSpace2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            colorSpace2 = ColorSpaces.INSTANCE.getSrgb();
        }
        if ((i2 & 2) != 0) {
            i = RenderIntent.Companion.m1456getPerceptualuksYyKA();
        }
        return m1446connectYBCOT_4(colorSpace, colorSpace2, i);
    }

    /* JADX INFO: renamed from: connect-YBCOT_4, reason: not valid java name */
    public static final Connector m1446connectYBCOT_4(ColorSpace colorSpace, ColorSpace colorSpace2, int i) {
        int id$ui_graphics_release = colorSpace.getId$ui_graphics_release();
        int id$ui_graphics_release2 = colorSpace2.getId$ui_graphics_release();
        if ((id$ui_graphics_release | id$ui_graphics_release2) < 0) {
            return m1448createConnectorYBCOT_4(colorSpace, colorSpace2, i);
        }
        MutableIntObjectMap connectors = ConnectorKt.getConnectors();
        int i2 = id$ui_graphics_release | (id$ui_graphics_release2 << 6) | (i << 12);
        Object objM1448createConnectorYBCOT_4 = connectors.get(i2);
        if (objM1448createConnectorYBCOT_4 == null) {
            objM1448createConnectorYBCOT_4 = m1448createConnectorYBCOT_4(colorSpace, colorSpace2, i);
            connectors.set(i2, objM1448createConnectorYBCOT_4);
        }
        return (Connector) objM1448createConnectorYBCOT_4;
    }

    public static /* synthetic */ ColorSpace adapt$default(ColorSpace colorSpace, WhitePoint whitePoint, Adaptation adaptation, int i, Object obj) {
        if ((i & 2) != 0) {
            adaptation = Adaptation.Companion.getBradford();
        }
        return adapt(colorSpace, whitePoint, adaptation);
    }

    public static final ColorSpace adapt(ColorSpace colorSpace, WhitePoint whitePoint, Adaptation adaptation) {
        if (!ColorModel.m1437equalsimpl0(colorSpace.m1444getModelxdoWZVw(), ColorModel.Companion.m1442getRgbxdoWZVw())) {
            return colorSpace;
        }
        Intrinsics.checkNotNull(colorSpace, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
        Rgb rgb = (Rgb) colorSpace;
        if (compare(rgb.getWhitePoint(), whitePoint)) {
            return colorSpace;
        }
        return new Rgb(rgb, mul3x3(chromaticAdaptation(adaptation.getTransform$ui_graphics_release(), rgb.getWhitePoint().toXyz$ui_graphics_release(), whitePoint.toXyz$ui_graphics_release()), rgb.getTransform$ui_graphics_release()), whitePoint);
    }

    public static final double rcpResponse(double d, double d2, double d3, double d4, double d5, double d6) {
        return d >= d5 * d4 ? (Math.pow(d, 1.0d / d6) - d3) / d2 : d / d4;
    }

    public static final double response(double d, double d2, double d3, double d4, double d5, double d6) {
        return d >= d5 ? Math.pow((d2 * d) + d3, d6) : d * d4;
    }

    public static final double rcpResponse(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        return d >= d5 * d4 ? (Math.pow(d - d6, 1.0d / d8) - d3) / d2 : (d - d7) / d4;
    }

    public static final double response(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        return d >= d5 ? Math.pow((d2 * d) + d3, d8) + d6 : (d4 * d) + d7;
    }

    public static final double absRcpResponse(double d, double d2, double d3, double d4, double d5, double d6) {
        return Math.copySign(rcpResponse(d < 0.0d ? -d : d, d2, d3, d4, d5, d6), d);
    }

    public static final double absResponse(double d, double d2, double d3, double d4, double d5, double d6) {
        return Math.copySign(response(d < 0.0d ? -d : d, d2, d3, d4, d5, d6), d);
    }

    public static final boolean compare(WhitePoint whitePoint, WhitePoint whitePoint2) {
        if (whitePoint == whitePoint2) {
            return true;
        }
        return Math.abs(whitePoint.getX() - whitePoint2.getX()) < 0.001f && Math.abs(whitePoint.getY() - whitePoint2.getY()) < 0.001f;
    }

    public static final boolean compare(float[] fArr, float[] fArr2) {
        if (fArr == fArr2) {
            return true;
        }
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            if (Float.compare(fArr[i], fArr2[i]) != 0 && Math.abs(fArr[i] - fArr2[i]) > 0.001f) {
                return false;
            }
        }
        return true;
    }

    public static final float[] inverse3x3(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[3];
        float f3 = fArr[6];
        float f4 = fArr[1];
        float f5 = fArr[4];
        float f6 = fArr[7];
        float f7 = fArr[2];
        float f8 = fArr[5];
        float f9 = fArr[8];
        float f10 = (f5 * f9) - (f6 * f8);
        float f11 = (f6 * f7) - (f4 * f9);
        float f12 = (f4 * f8) - (f5 * f7);
        float f13 = (f * f10) + (f2 * f11) + (f3 * f12);
        float[] fArr2 = new float[fArr.length];
        fArr2[0] = f10 / f13;
        fArr2[1] = f11 / f13;
        fArr2[2] = f12 / f13;
        fArr2[3] = ((f3 * f8) - (f2 * f9)) / f13;
        fArr2[4] = ((f9 * f) - (f3 * f7)) / f13;
        fArr2[5] = ((f7 * f2) - (f8 * f)) / f13;
        fArr2[6] = ((f2 * f6) - (f3 * f5)) / f13;
        fArr2[7] = ((f3 * f4) - (f6 * f)) / f13;
        fArr2[8] = ((f * f5) - (f2 * f4)) / f13;
        return fArr2;
    }

    public static final float[] mul3x3(float[] fArr, float[] fArr2) {
        float f = fArr[0];
        float f2 = fArr2[0];
        float f3 = fArr[3];
        float f4 = fArr2[1];
        float f5 = fArr[6];
        float f6 = fArr2[2];
        float f7 = (f * f2) + (f3 * f4) + (f5 * f6);
        float f8 = fArr[1];
        float f9 = fArr[4];
        float f10 = fArr[7];
        float f11 = (f8 * f2) + (f9 * f4) + (f10 * f6);
        float f12 = fArr[2];
        float f13 = fArr[5];
        float f14 = fArr[8];
        float f15 = (f2 * f12) + (f4 * f13) + (f6 * f14);
        float f16 = fArr2[3];
        float f17 = fArr2[4];
        float f18 = fArr2[5];
        float f19 = (f * f16) + (f3 * f17) + (f5 * f18);
        float f20 = (f8 * f16) + (f9 * f17) + (f10 * f18);
        float f21 = (f16 * f12) + (f17 * f13) + (f18 * f14);
        float f22 = fArr2[6];
        float f23 = fArr2[7];
        float f24 = (f * f22) + (f3 * f23);
        float f25 = fArr2[8];
        return new float[]{f7, f11, f15, f19, f20, f21, f24 + (f5 * f25), (f8 * f22) + (f9 * f23) + (f10 * f25), (f12 * f22) + (f13 * f23) + (f14 * f25)};
    }

    public static final float[] mul3x3Float3(float[] fArr, float[] fArr2) {
        float f = fArr2[0];
        float f2 = fArr2[1];
        float f3 = fArr2[2];
        fArr2[0] = (fArr[0] * f) + (fArr[3] * f2) + (fArr[6] * f3);
        fArr2[1] = (fArr[1] * f) + (fArr[4] * f2) + (fArr[7] * f3);
        fArr2[2] = (fArr[2] * f) + (fArr[5] * f2) + (fArr[8] * f3);
        return fArr2;
    }

    public static final float[] mul3x3Diag(float[] fArr, float[] fArr2) {
        float f = fArr[0];
        float f2 = fArr2[0] * f;
        float f3 = fArr[1];
        float f4 = fArr2[1] * f3;
        float f5 = fArr[2];
        return new float[]{f2, f4, fArr2[2] * f5, fArr2[3] * f, fArr2[4] * f3, fArr2[5] * f5, f * fArr2[6], f3 * fArr2[7], f5 * fArr2[8]};
    }

    public static final float[] chromaticAdaptation(float[] fArr, float[] fArr2, float[] fArr3) {
        float[] fArrMul3x3Float3 = mul3x3Float3(fArr, fArr2);
        float[] fArrMul3x3Float32 = mul3x3Float3(fArr, fArr3);
        return mul3x3(inverse3x3(fArr), mul3x3Diag(new float[]{fArrMul3x3Float32[0] / fArrMul3x3Float3[0], fArrMul3x3Float32[1] / fArrMul3x3Float3[1], fArrMul3x3Float32[2] / fArrMul3x3Float3[2]}, fArr));
    }
}
