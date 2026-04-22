package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.colorspace.ColorModel;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Connector.kt */
/* JADX INFO: loaded from: classes.dex */
public class Connector {
    public static final Companion Companion = new Companion(null);
    private final ColorSpace destination;
    private final int renderIntent;
    private final ColorSpace source;
    private final float[] transform;
    private final ColorSpace transformDestination;
    private final ColorSpace transformSource;

    public /* synthetic */ Connector(ColorSpace colorSpace, ColorSpace colorSpace2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(colorSpace, colorSpace2, i);
    }

    public /* synthetic */ Connector(ColorSpace colorSpace, ColorSpace colorSpace2, ColorSpace colorSpace3, ColorSpace colorSpace4, int i, float[] fArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(colorSpace, colorSpace2, colorSpace3, colorSpace4, i, fArr);
    }

    private Connector(ColorSpace colorSpace, ColorSpace colorSpace2, ColorSpace colorSpace3, ColorSpace colorSpace4, int i, float[] fArr) {
        this.source = colorSpace;
        this.destination = colorSpace2;
        this.transformSource = colorSpace3;
        this.transformDestination = colorSpace4;
        this.renderIntent = i;
        this.transform = fArr;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private Connector(ColorSpace colorSpace, ColorSpace colorSpace2, int i) {
        long jM1444getModelxdoWZVw = colorSpace.m1444getModelxdoWZVw();
        ColorModel.Companion companion = ColorModel.Companion;
        this(colorSpace, colorSpace2, ColorModel.m1437equalsimpl0(jM1444getModelxdoWZVw, companion.m1442getRgbxdoWZVw()) ? ColorSpaceKt.adapt$default(colorSpace, Illuminant.INSTANCE.getD50(), null, 2, null) : colorSpace, ColorModel.m1437equalsimpl0(colorSpace2.m1444getModelxdoWZVw(), companion.m1442getRgbxdoWZVw()) ? ColorSpaceKt.adapt$default(colorSpace2, Illuminant.INSTANCE.getD50(), null, 2, null) : colorSpace2, i, Companion.m1451computeTransformYBCOT_4(colorSpace, colorSpace2, i), null);
    }

    /* JADX INFO: compiled from: Connector.kt */
    public static final class RgbConnector extends Connector {
        private final Rgb mDestination;
        private final Rgb mSource;
        private final float[] mTransform;

        public /* synthetic */ RgbConnector(Rgb rgb, Rgb rgb2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(rgb, rgb2, i);
        }

        private RgbConnector(Rgb rgb, Rgb rgb2, int i) {
            super(rgb, rgb2, rgb, rgb2, i, null, null);
            this.mSource = rgb;
            this.mDestination = rgb2;
            this.mTransform = m1452computeTransformYBCOT_4(rgb, rgb2, i);
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        /* JADX INFO: renamed from: transformToColor-l2rxGTc$ui_graphics_release */
        public long mo1449transformToColorl2rxGTc$ui_graphics_release(long j) {
            float fM1301getRedimpl = Color.m1301getRedimpl(j);
            float fM1300getGreenimpl = Color.m1300getGreenimpl(j);
            float fM1298getBlueimpl = Color.m1298getBlueimpl(j);
            float fM1297getAlphaimpl = Color.m1297getAlphaimpl(j);
            float fInvoke = (float) this.mSource.getEotfFunc$ui_graphics_release().invoke(fM1301getRedimpl);
            float fInvoke2 = (float) this.mSource.getEotfFunc$ui_graphics_release().invoke(fM1300getGreenimpl);
            float fInvoke3 = (float) this.mSource.getEotfFunc$ui_graphics_release().invoke(fM1298getBlueimpl);
            float[] fArr = this.mTransform;
            return ColorKt.Color((float) this.mDestination.getOetfFunc$ui_graphics_release().invoke((fArr[0] * fInvoke) + (fArr[3] * fInvoke2) + (fArr[6] * fInvoke3)), (float) this.mDestination.getOetfFunc$ui_graphics_release().invoke((fArr[1] * fInvoke) + (fArr[4] * fInvoke2) + (fArr[7] * fInvoke3)), (float) this.mDestination.getOetfFunc$ui_graphics_release().invoke((fArr[2] * fInvoke) + (fArr[5] * fInvoke2) + (fArr[8] * fInvoke3)), fM1297getAlphaimpl, this.mDestination);
        }

        /* JADX INFO: renamed from: computeTransform-YBCOT_4, reason: not valid java name */
        private final float[] m1452computeTransformYBCOT_4(Rgb rgb, Rgb rgb2, int i) {
            if (ColorSpaceKt.compare(rgb.getWhitePoint(), rgb2.getWhitePoint())) {
                return ColorSpaceKt.mul3x3(rgb2.getInverseTransform$ui_graphics_release(), rgb.getTransform$ui_graphics_release());
            }
            float[] transform$ui_graphics_release = rgb.getTransform$ui_graphics_release();
            float[] inverseTransform$ui_graphics_release = rgb2.getInverseTransform$ui_graphics_release();
            float[] xyz$ui_graphics_release = rgb.getWhitePoint().toXyz$ui_graphics_release();
            float[] xyz$ui_graphics_release2 = rgb2.getWhitePoint().toXyz$ui_graphics_release();
            WhitePoint whitePoint = rgb.getWhitePoint();
            Illuminant illuminant = Illuminant.INSTANCE;
            if (!ColorSpaceKt.compare(whitePoint, illuminant.getD50())) {
                float[] transform$ui_graphics_release2 = Adaptation.Companion.getBradford().getTransform$ui_graphics_release();
                float[] d50Xyz$ui_graphics_release = illuminant.getD50Xyz$ui_graphics_release();
                float[] fArrCopyOf = Arrays.copyOf(d50Xyz$ui_graphics_release, d50Xyz$ui_graphics_release.length);
                Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(this, size)");
                transform$ui_graphics_release = ColorSpaceKt.mul3x3(ColorSpaceKt.chromaticAdaptation(transform$ui_graphics_release2, xyz$ui_graphics_release, fArrCopyOf), rgb.getTransform$ui_graphics_release());
            }
            if (!ColorSpaceKt.compare(rgb2.getWhitePoint(), illuminant.getD50())) {
                float[] transform$ui_graphics_release3 = Adaptation.Companion.getBradford().getTransform$ui_graphics_release();
                float[] d50Xyz$ui_graphics_release2 = illuminant.getD50Xyz$ui_graphics_release();
                float[] fArrCopyOf2 = Arrays.copyOf(d50Xyz$ui_graphics_release2, d50Xyz$ui_graphics_release2.length);
                Intrinsics.checkNotNullExpressionValue(fArrCopyOf2, "copyOf(this, size)");
                inverseTransform$ui_graphics_release = ColorSpaceKt.inverse3x3(ColorSpaceKt.mul3x3(ColorSpaceKt.chromaticAdaptation(transform$ui_graphics_release3, xyz$ui_graphics_release2, fArrCopyOf2), rgb2.getTransform$ui_graphics_release()));
            }
            if (RenderIntent.m1454equalsimpl0(i, RenderIntent.Companion.m1455getAbsoluteuksYyKA())) {
                transform$ui_graphics_release = ColorSpaceKt.mul3x3Diag(new float[]{xyz$ui_graphics_release[0] / xyz$ui_graphics_release2[0], xyz$ui_graphics_release[1] / xyz$ui_graphics_release2[1], xyz$ui_graphics_release[2] / xyz$ui_graphics_release2[2]}, transform$ui_graphics_release);
            }
            return ColorSpaceKt.mul3x3(inverseTransform$ui_graphics_release, transform$ui_graphics_release);
        }
    }

    /* JADX INFO: renamed from: transformToColor-l2rxGTc$ui_graphics_release, reason: not valid java name */
    public long mo1449transformToColorl2rxGTc$ui_graphics_release(long j) {
        float fM1301getRedimpl = Color.m1301getRedimpl(j);
        float fM1300getGreenimpl = Color.m1300getGreenimpl(j);
        float fM1298getBlueimpl = Color.m1298getBlueimpl(j);
        float fM1297getAlphaimpl = Color.m1297getAlphaimpl(j);
        long xy$ui_graphics_release = this.transformSource.toXy$ui_graphics_release(fM1301getRedimpl, fM1300getGreenimpl, fM1298getBlueimpl);
        float fIntBitsToFloat = Float.intBitsToFloat((int) (xy$ui_graphics_release >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (xy$ui_graphics_release & BodyPartID.bodyIdMax));
        float z$ui_graphics_release = this.transformSource.toZ$ui_graphics_release(fM1301getRedimpl, fM1300getGreenimpl, fM1298getBlueimpl);
        float[] fArr = this.transform;
        if (fArr != null) {
            fIntBitsToFloat *= fArr[0];
            fIntBitsToFloat2 *= fArr[1];
            z$ui_graphics_release *= fArr[2];
        }
        float f = fIntBitsToFloat;
        return this.transformDestination.mo1445xyzaToColorJlNiLsg$ui_graphics_release(f, fIntBitsToFloat2, z$ui_graphics_release, fM1297getAlphaimpl, this.destination);
    }

    /* JADX INFO: compiled from: Connector.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: computeTransform-YBCOT_4, reason: not valid java name */
        public final float[] m1451computeTransformYBCOT_4(ColorSpace colorSpace, ColorSpace colorSpace2, int i) {
            if (!RenderIntent.m1454equalsimpl0(i, RenderIntent.Companion.m1455getAbsoluteuksYyKA())) {
                return null;
            }
            long jM1444getModelxdoWZVw = colorSpace.m1444getModelxdoWZVw();
            ColorModel.Companion companion = ColorModel.Companion;
            boolean zM1437equalsimpl0 = ColorModel.m1437equalsimpl0(jM1444getModelxdoWZVw, companion.m1442getRgbxdoWZVw());
            boolean zM1437equalsimpl02 = ColorModel.m1437equalsimpl0(colorSpace2.m1444getModelxdoWZVw(), companion.m1442getRgbxdoWZVw());
            if (zM1437equalsimpl0 && zM1437equalsimpl02) {
                return null;
            }
            if (!zM1437equalsimpl0 && !zM1437equalsimpl02) {
                return null;
            }
            if (!zM1437equalsimpl0) {
                colorSpace = colorSpace2;
            }
            Intrinsics.checkNotNull(colorSpace, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
            Rgb rgb = (Rgb) colorSpace;
            float[] xyz$ui_graphics_release = zM1437equalsimpl0 ? rgb.getWhitePoint().toXyz$ui_graphics_release() : Illuminant.INSTANCE.getD50Xyz$ui_graphics_release();
            float[] xyz$ui_graphics_release2 = zM1437equalsimpl02 ? rgb.getWhitePoint().toXyz$ui_graphics_release() : Illuminant.INSTANCE.getD50Xyz$ui_graphics_release();
            return new float[]{xyz$ui_graphics_release[0] / xyz$ui_graphics_release2[0], xyz$ui_graphics_release[1] / xyz$ui_graphics_release2[1], xyz$ui_graphics_release[2] / xyz$ui_graphics_release2[2]};
        }

        public final Connector identity$ui_graphics_release(final ColorSpace colorSpace) {
            final int iM1457getRelativeuksYyKA = RenderIntent.Companion.m1457getRelativeuksYyKA();
            return new Connector(colorSpace, iM1457getRelativeuksYyKA) { // from class: androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1
                @Override // androidx.compose.ui.graphics.colorspace.Connector
                /* JADX INFO: renamed from: transformToColor-l2rxGTc$ui_graphics_release */
                public long mo1449transformToColorl2rxGTc$ui_graphics_release(long j) {
                    return j;
                }

                {
                    super(colorSpace, colorSpace, iM1457getRelativeuksYyKA, null);
                }
            };
        }
    }
}
