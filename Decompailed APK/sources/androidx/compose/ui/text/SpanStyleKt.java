package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.ShadowKt;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.FontWeightKt;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.BaselineShiftKt;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDrawStyleKt;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextGeometricTransformKt;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SpanStyle.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SpanStyleKt {
    private static final long DefaultBackgroundColor;
    private static final long DefaultColor;
    private static final TextForegroundStyle DefaultColorForegroundStyle;
    private static final long DefaultFontSize = TextUnitKt.getSp(14);
    private static final long DefaultLetterSpacing = TextUnitKt.getSp(0);

    public static final Object lerpDiscrete(Object obj, Object obj2, float f) {
        return ((double) f) < 0.5d ? obj : obj2;
    }

    static {
        Color.Companion companion = Color.Companion;
        DefaultBackgroundColor = companion.m1308getTransparent0d7_KjU();
        long jM1305getBlack0d7_KjU = companion.m1305getBlack0d7_KjU();
        DefaultColor = jM1305getBlack0d7_KjU;
        DefaultColorForegroundStyle = TextForegroundStyle.Companion.m2361from8_81llA(jM1305getBlack0d7_KjU);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0033  */
    /* JADX INFO: renamed from: fastMerge-dSHsh3o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.text.SpanStyle m2092fastMergedSHsh3o(androidx.compose.ui.text.SpanStyle r21, long r22, androidx.compose.ui.graphics.Brush r24, float r25, long r26, androidx.compose.ui.text.font.FontWeight r28, androidx.compose.ui.text.font.FontStyle r29, androidx.compose.ui.text.font.FontSynthesis r30, androidx.compose.ui.text.font.FontFamily r31, java.lang.String r32, long r33, androidx.compose.ui.text.style.BaselineShift r35, androidx.compose.ui.text.style.TextGeometricTransform r36, androidx.compose.ui.text.intl.LocaleList r37, long r38, androidx.compose.ui.text.style.TextDecoration r40, androidx.compose.ui.graphics.Shadow r41, androidx.compose.ui.text.PlatformSpanStyle r42, androidx.compose.ui.graphics.drawscope.DrawStyle r43) {
        /*
            Method dump skipped, instruction units count: 506
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.SpanStyleKt.m2092fastMergedSHsh3o(androidx.compose.ui.text.SpanStyle, long, androidx.compose.ui.graphics.Brush, float, long, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontSynthesis, androidx.compose.ui.text.font.FontFamily, java.lang.String, long, androidx.compose.ui.text.style.BaselineShift, androidx.compose.ui.text.style.TextGeometricTransform, androidx.compose.ui.text.intl.LocaleList, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.graphics.Shadow, androidx.compose.ui.text.PlatformSpanStyle, androidx.compose.ui.graphics.drawscope.DrawStyle):androidx.compose.ui.text.SpanStyle");
    }

    /* JADX INFO: renamed from: lerpTextUnitInheritable-C3pnCVY, reason: not valid java name */
    public static final long m2093lerpTextUnitInheritableC3pnCVY(long j, long j2, float f) {
        if (TextUnitKt.m2498isUnspecifiedR2X_6o(j) || TextUnitKt.m2498isUnspecifiedR2X_6o(j2)) {
            return ((TextUnit) lerpDiscrete(TextUnit.m2484boximpl(j), TextUnit.m2484boximpl(j2), f)).m2493unboximpl();
        }
        return TextUnitKt.m2499lerpC3pnCVY(j, j2, f);
    }

    public static final SpanStyle lerp(SpanStyle spanStyle, SpanStyle spanStyle2, float f) {
        TextForegroundStyle textForegroundStyleLerp = TextDrawStyleKt.lerp(spanStyle.getTextForegroundStyle$ui_text_release(), spanStyle2.getTextForegroundStyle$ui_text_release(), f);
        FontFamily fontFamily = (FontFamily) lerpDiscrete(spanStyle.getFontFamily(), spanStyle2.getFontFamily(), f);
        long jM2093lerpTextUnitInheritableC3pnCVY = m2093lerpTextUnitInheritableC3pnCVY(spanStyle.m2088getFontSizeXSAIIZE(), spanStyle2.m2088getFontSizeXSAIIZE(), f);
        FontWeight fontWeight = spanStyle.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.Companion.getNormal();
        }
        FontWeight fontWeight2 = spanStyle2.getFontWeight();
        if (fontWeight2 == null) {
            fontWeight2 = FontWeight.Companion.getNormal();
        }
        FontWeight fontWeightLerp = FontWeightKt.lerp(fontWeight, fontWeight2, f);
        FontStyle fontStyle = (FontStyle) lerpDiscrete(spanStyle.m2089getFontStyle4Lr2A7w(), spanStyle2.m2089getFontStyle4Lr2A7w(), f);
        FontSynthesis fontSynthesis = (FontSynthesis) lerpDiscrete(spanStyle.m2090getFontSynthesisZQGJjVo(), spanStyle2.m2090getFontSynthesisZQGJjVo(), f);
        String str = (String) lerpDiscrete(spanStyle.getFontFeatureSettings(), spanStyle2.getFontFeatureSettings(), f);
        long jM2093lerpTextUnitInheritableC3pnCVY2 = m2093lerpTextUnitInheritableC3pnCVY(spanStyle.m2091getLetterSpacingXSAIIZE(), spanStyle2.m2091getLetterSpacingXSAIIZE(), f);
        BaselineShift baselineShiftM2086getBaselineShift5SSeXJ0 = spanStyle.m2086getBaselineShift5SSeXJ0();
        float fM2273unboximpl = baselineShiftM2086getBaselineShift5SSeXJ0 != null ? baselineShiftM2086getBaselineShift5SSeXJ0.m2273unboximpl() : BaselineShift.m2268constructorimpl(0.0f);
        BaselineShift baselineShiftM2086getBaselineShift5SSeXJ02 = spanStyle2.m2086getBaselineShift5SSeXJ0();
        float fM2275lerpjWV1Mfo = BaselineShiftKt.m2275lerpjWV1Mfo(fM2273unboximpl, baselineShiftM2086getBaselineShift5SSeXJ02 != null ? baselineShiftM2086getBaselineShift5SSeXJ02.m2273unboximpl() : BaselineShift.m2268constructorimpl(0.0f), f);
        TextGeometricTransform textGeometricTransform = spanStyle.getTextGeometricTransform();
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.Companion.getNone$ui_text_release();
        }
        TextGeometricTransform textGeometricTransform2 = spanStyle2.getTextGeometricTransform();
        if (textGeometricTransform2 == null) {
            textGeometricTransform2 = TextGeometricTransform.Companion.getNone$ui_text_release();
        }
        TextGeometricTransform textGeometricTransformLerp = TextGeometricTransformKt.lerp(textGeometricTransform, textGeometricTransform2, f);
        LocaleList localeList = (LocaleList) lerpDiscrete(spanStyle.getLocaleList(), spanStyle2.getLocaleList(), f);
        long jM1314lerpjxsXWHM = ColorKt.m1314lerpjxsXWHM(spanStyle.m2085getBackground0d7_KjU(), spanStyle2.m2085getBackground0d7_KjU(), f);
        TextDecoration textDecoration = (TextDecoration) lerpDiscrete(spanStyle.getTextDecoration(), spanStyle2.getTextDecoration(), f);
        Shadow shadow = spanStyle.getShadow();
        if (shadow == null) {
            shadow = new Shadow(0L, 0L, 0.0f, 7, null);
        }
        Shadow shadow2 = spanStyle2.getShadow();
        if (shadow2 == null) {
            shadow2 = new Shadow(0L, 0L, 0.0f, 7, null);
        }
        return new SpanStyle(textForegroundStyleLerp, jM2093lerpTextUnitInheritableC3pnCVY, fontWeightLerp, fontStyle, fontSynthesis, fontFamily, str, jM2093lerpTextUnitInheritableC3pnCVY2, BaselineShift.m2267boximpl(fM2275lerpjWV1Mfo), textGeometricTransformLerp, localeList, jM1314lerpjxsXWHM, textDecoration, ShadowKt.lerp(shadow, shadow2, f), lerpPlatformStyle(spanStyle.getPlatformStyle(), spanStyle2.getPlatformStyle(), f), (DrawStyle) lerpDiscrete(spanStyle.getDrawStyle(), spanStyle2.getDrawStyle(), f), (DefaultConstructorMarker) null);
    }

    private static final PlatformSpanStyle lerpPlatformStyle(PlatformSpanStyle platformSpanStyle, PlatformSpanStyle platformSpanStyle2, float f) {
        if (platformSpanStyle == null && platformSpanStyle2 == null) {
            return null;
        }
        if (platformSpanStyle == null) {
            platformSpanStyle = PlatformSpanStyle.Companion.getDefault();
        }
        if (platformSpanStyle2 == null) {
            platformSpanStyle2 = PlatformSpanStyle.Companion.getDefault();
        }
        return AndroidTextStyle_androidKt.lerp(platformSpanStyle, platformSpanStyle2, f);
    }

    public static final SpanStyle resolveSpanStyleDefaults(SpanStyle spanStyle) {
        long jM2091getLetterSpacingXSAIIZE;
        TextForegroundStyle textForegroundStyleTakeOrElse = spanStyle.getTextForegroundStyle$ui_text_release().takeOrElse(new Function0() { // from class: androidx.compose.ui.text.SpanStyleKt.resolveSpanStyleDefaults.1
            @Override // kotlin.jvm.functions.Function0
            public final TextForegroundStyle invoke() {
                return SpanStyleKt.DefaultColorForegroundStyle;
            }
        });
        long jM2088getFontSizeXSAIIZE = TextUnitKt.m2498isUnspecifiedR2X_6o(spanStyle.m2088getFontSizeXSAIIZE()) ? DefaultFontSize : spanStyle.m2088getFontSizeXSAIIZE();
        FontWeight fontWeight = spanStyle.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.Companion.getNormal();
        }
        FontWeight fontWeight2 = fontWeight;
        FontStyle fontStyleM2089getFontStyle4Lr2A7w = spanStyle.m2089getFontStyle4Lr2A7w();
        FontStyle fontStyleM2152boximpl = FontStyle.m2152boximpl(fontStyleM2089getFontStyle4Lr2A7w != null ? fontStyleM2089getFontStyle4Lr2A7w.m2158unboximpl() : FontStyle.Companion.m2160getNormal_LCdwA());
        FontSynthesis fontSynthesisM2090getFontSynthesisZQGJjVo = spanStyle.m2090getFontSynthesisZQGJjVo();
        FontSynthesis fontSynthesisM2161boximpl = FontSynthesis.m2161boximpl(fontSynthesisM2090getFontSynthesisZQGJjVo != null ? fontSynthesisM2090getFontSynthesisZQGJjVo.m2167unboximpl() : FontSynthesis.Companion.m2168getAllGVVA2EU());
        FontFamily fontFamily = spanStyle.getFontFamily();
        if (fontFamily == null) {
            fontFamily = FontFamily.Companion.getDefault();
        }
        FontFamily fontFamily2 = fontFamily;
        String fontFeatureSettings = spanStyle.getFontFeatureSettings();
        if (fontFeatureSettings == null) {
            fontFeatureSettings = "";
        }
        String str = fontFeatureSettings;
        if (TextUnitKt.m2498isUnspecifiedR2X_6o(spanStyle.m2091getLetterSpacingXSAIIZE())) {
            jM2091getLetterSpacingXSAIIZE = DefaultLetterSpacing;
        } else {
            jM2091getLetterSpacingXSAIIZE = spanStyle.m2091getLetterSpacingXSAIIZE();
        }
        long j = jM2091getLetterSpacingXSAIIZE;
        BaselineShift baselineShiftM2086getBaselineShift5SSeXJ0 = spanStyle.m2086getBaselineShift5SSeXJ0();
        BaselineShift baselineShiftM2267boximpl = BaselineShift.m2267boximpl(baselineShiftM2086getBaselineShift5SSeXJ0 != null ? baselineShiftM2086getBaselineShift5SSeXJ0.m2273unboximpl() : BaselineShift.Companion.m2274getNoney9eOQZs());
        TextGeometricTransform textGeometricTransform = spanStyle.getTextGeometricTransform();
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.Companion.getNone$ui_text_release();
        }
        TextGeometricTransform textGeometricTransform2 = textGeometricTransform;
        LocaleList localeList = spanStyle.getLocaleList();
        if (localeList == null) {
            localeList = LocaleList.Companion.getCurrent();
        }
        LocaleList localeList2 = localeList;
        long jM2085getBackground0d7_KjU = spanStyle.m2085getBackground0d7_KjU();
        if (jM2085getBackground0d7_KjU == 16) {
            jM2085getBackground0d7_KjU = DefaultBackgroundColor;
        }
        long j2 = jM2085getBackground0d7_KjU;
        TextDecoration textDecoration = spanStyle.getTextDecoration();
        if (textDecoration == null) {
            textDecoration = TextDecoration.Companion.getNone();
        }
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow = spanStyle.getShadow();
        if (shadow == null) {
            shadow = Shadow.Companion.getNone();
        }
        Shadow shadow2 = shadow;
        PlatformSpanStyle platformStyle = spanStyle.getPlatformStyle();
        DrawStyle drawStyle = spanStyle.getDrawStyle();
        if (drawStyle == null) {
            drawStyle = Fill.INSTANCE;
        }
        return new SpanStyle(textForegroundStyleTakeOrElse, jM2088getFontSizeXSAIIZE, fontWeight2, fontStyleM2152boximpl, fontSynthesisM2161boximpl, fontFamily2, str, j, baselineShiftM2267boximpl, textGeometricTransform2, localeList2, j2, textDecoration2, shadow2, platformStyle, drawStyle, (DefaultConstructorMarker) null);
    }

    private static final PlatformSpanStyle mergePlatformStyle(SpanStyle spanStyle, PlatformSpanStyle platformSpanStyle) {
        if (spanStyle.getPlatformStyle() == null) {
            return platformSpanStyle;
        }
        if (platformSpanStyle == null) {
            return spanStyle.getPlatformStyle();
        }
        return spanStyle.getPlatformStyle().merge(platformSpanStyle);
    }
}
