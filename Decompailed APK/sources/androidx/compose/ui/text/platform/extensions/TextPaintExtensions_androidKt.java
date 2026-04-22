package androidx.compose.ui.text.platform.extensions;

import android.graphics.Typeface;
import android.os.Build;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextPaintExtensions.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextPaintExtensions_androidKt {
    public static final float correctBlurRadius(float f) {
        if (f == 0.0f) {
            return Float.MIN_VALUE;
        }
        return f;
    }

    public static final SpanStyle applySpanStyle(AndroidTextPaint androidTextPaint, SpanStyle spanStyle, Function4 function4, Density density, boolean z) {
        Locale current;
        long jM2489getTypeUIouoOA = TextUnit.m2489getTypeUIouoOA(spanStyle.m2088getFontSizeXSAIIZE());
        TextUnitType.Companion companion = TextUnitType.Companion;
        if (TextUnitType.m2503equalsimpl0(jM2489getTypeUIouoOA, companion.m2508getSpUIouoOA())) {
            androidTextPaint.setTextSize(density.mo209toPxR2X_6o(spanStyle.m2088getFontSizeXSAIIZE()));
        } else if (TextUnitType.m2503equalsimpl0(jM2489getTypeUIouoOA, companion.m2507getEmUIouoOA())) {
            androidTextPaint.setTextSize(androidTextPaint.getTextSize() * TextUnit.m2490getValueimpl(spanStyle.m2088getFontSizeXSAIIZE()));
        }
        if (hasFontAttributes(spanStyle)) {
            FontFamily fontFamily = spanStyle.getFontFamily();
            FontWeight fontWeight = spanStyle.getFontWeight();
            if (fontWeight == null) {
                fontWeight = FontWeight.Companion.getNormal();
            }
            FontStyle fontStyleM2089getFontStyle4Lr2A7w = spanStyle.m2089getFontStyle4Lr2A7w();
            FontStyle fontStyleM2152boximpl = FontStyle.m2152boximpl(fontStyleM2089getFontStyle4Lr2A7w != null ? fontStyleM2089getFontStyle4Lr2A7w.m2158unboximpl() : FontStyle.Companion.m2160getNormal_LCdwA());
            FontSynthesis fontSynthesisM2090getFontSynthesisZQGJjVo = spanStyle.m2090getFontSynthesisZQGJjVo();
            androidTextPaint.setTypeface((Typeface) function4.invoke(fontFamily, fontWeight, fontStyleM2152boximpl, FontSynthesis.m2161boximpl(fontSynthesisM2090getFontSynthesisZQGJjVo != null ? fontSynthesisM2090getFontSynthesisZQGJjVo.m2167unboximpl() : FontSynthesis.Companion.m2168getAllGVVA2EU())));
        }
        if (spanStyle.getLocaleList() != null && !Intrinsics.areEqual(spanStyle.getLocaleList(), LocaleList.Companion.getCurrent())) {
            if (Build.VERSION.SDK_INT >= 24) {
                LocaleListHelperMethods.INSTANCE.setTextLocales(androidTextPaint, spanStyle.getLocaleList());
            } else {
                if (spanStyle.getLocaleList().isEmpty()) {
                    current = Locale.Companion.getCurrent();
                } else {
                    current = spanStyle.getLocaleList().get(0);
                }
                androidTextPaint.setTextLocale(current.getPlatformLocale());
            }
        }
        if (spanStyle.getFontFeatureSettings() != null && !Intrinsics.areEqual(spanStyle.getFontFeatureSettings(), "")) {
            androidTextPaint.setFontFeatureSettings(spanStyle.getFontFeatureSettings());
        }
        if (spanStyle.getTextGeometricTransform() != null && !Intrinsics.areEqual(spanStyle.getTextGeometricTransform(), TextGeometricTransform.Companion.getNone$ui_text_release())) {
            androidTextPaint.setTextScaleX(androidTextPaint.getTextScaleX() * spanStyle.getTextGeometricTransform().getScaleX());
            androidTextPaint.setTextSkewX(androidTextPaint.getTextSkewX() + spanStyle.getTextGeometricTransform().getSkewX());
        }
        androidTextPaint.m2253setColor8_81llA(spanStyle.m2087getColor0d7_KjU());
        androidTextPaint.m2252setBrush12SF9DM(spanStyle.getBrush(), Size.Companion.m1201getUnspecifiedNHjbRc(), spanStyle.getAlpha());
        androidTextPaint.setShadow(spanStyle.getShadow());
        androidTextPaint.setTextDecoration(spanStyle.getTextDecoration());
        androidTextPaint.setDrawStyle(spanStyle.getDrawStyle());
        if (TextUnitType.m2503equalsimpl0(TextUnit.m2489getTypeUIouoOA(spanStyle.m2091getLetterSpacingXSAIIZE()), companion.m2508getSpUIouoOA()) && TextUnit.m2490getValueimpl(spanStyle.m2091getLetterSpacingXSAIIZE()) != 0.0f) {
            float textSize = androidTextPaint.getTextSize() * androidTextPaint.getTextScaleX();
            float fMo209toPxR2X_6o = density.mo209toPxR2X_6o(spanStyle.m2091getLetterSpacingXSAIIZE());
            if (textSize != 0.0f) {
                androidTextPaint.setLetterSpacing(fMo209toPxR2X_6o / textSize);
            }
        } else if (TextUnitType.m2503equalsimpl0(TextUnit.m2489getTypeUIouoOA(spanStyle.m2091getLetterSpacingXSAIIZE()), companion.m2507getEmUIouoOA())) {
            androidTextPaint.setLetterSpacing(TextUnit.m2490getValueimpl(spanStyle.m2091getLetterSpacingXSAIIZE()));
        }
        return m2262generateFallbackSpanStyle62GTOB8(spanStyle.m2091getLetterSpacingXSAIIZE(), z, spanStyle.m2085getBackground0d7_KjU(), spanStyle.m2086getBaselineShift5SSeXJ0());
    }

    /* JADX INFO: renamed from: generateFallbackSpanStyle-62GTOB8, reason: not valid java name */
    private static final SpanStyle m2262generateFallbackSpanStyle62GTOB8(long j, boolean z, long j2, BaselineShift baselineShift) {
        long jM1309getUnspecified0d7_KjU = j2;
        boolean z2 = false;
        boolean z3 = z && TextUnitType.m2503equalsimpl0(TextUnit.m2489getTypeUIouoOA(j), TextUnitType.Companion.m2508getSpUIouoOA()) && TextUnit.m2490getValueimpl(j) != 0.0f;
        Color.Companion companion = Color.Companion;
        boolean z4 = (Color.m1296equalsimpl0(jM1309getUnspecified0d7_KjU, companion.m1309getUnspecified0d7_KjU()) || Color.m1296equalsimpl0(jM1309getUnspecified0d7_KjU, companion.m1308getTransparent0d7_KjU())) ? false : true;
        if (baselineShift != null) {
            if (!BaselineShift.m2270equalsimpl0(baselineShift.m2273unboximpl(), BaselineShift.Companion.m2274getNoney9eOQZs())) {
                z2 = true;
            }
        }
        if (!z3 && !z4 && !z2) {
            return null;
        }
        long jM2494getUnspecifiedXSAIIZE = z3 ? j : TextUnit.Companion.m2494getUnspecifiedXSAIIZE();
        if (!z4) {
            jM1309getUnspecified0d7_KjU = companion.m1309getUnspecified0d7_KjU();
        }
        return new SpanStyle(0L, 0L, null, null, null, null, null, jM2494getUnspecifiedXSAIIZE, z2 ? baselineShift : null, null, null, jM1309getUnspecified0d7_KjU, null, null, null, null, 63103, null);
    }

    public static final void setTextMotion(AndroidTextPaint androidTextPaint, TextMotion textMotion) {
        int flags;
        if (textMotion == null) {
            textMotion = TextMotion.Companion.getStatic();
        }
        if (textMotion.getSubpixelTextPositioning$ui_text_release()) {
            flags = androidTextPaint.getFlags() | 128;
        } else {
            flags = androidTextPaint.getFlags() & (-129);
        }
        androidTextPaint.setFlags(flags);
        int iM2364getLinearity4e0Vf04$ui_text_release = textMotion.m2364getLinearity4e0Vf04$ui_text_release();
        TextMotion.Linearity.Companion companion = TextMotion.Linearity.Companion;
        if (TextMotion.Linearity.m2366equalsimpl0(iM2364getLinearity4e0Vf04$ui_text_release, companion.m2369getLinear4e0Vf04())) {
            androidTextPaint.setFlags(androidTextPaint.getFlags() | 64);
            androidTextPaint.setHinting(0);
        } else if (TextMotion.Linearity.m2366equalsimpl0(iM2364getLinearity4e0Vf04$ui_text_release, companion.m2368getFontHinting4e0Vf04())) {
            androidTextPaint.getFlags();
            androidTextPaint.setHinting(1);
        } else if (TextMotion.Linearity.m2366equalsimpl0(iM2364getLinearity4e0Vf04$ui_text_release, companion.m2370getNone4e0Vf04())) {
            androidTextPaint.getFlags();
            androidTextPaint.setHinting(0);
        } else {
            androidTextPaint.getFlags();
        }
    }

    public static final boolean hasFontAttributes(SpanStyle spanStyle) {
        return (spanStyle.getFontFamily() == null && spanStyle.m2089getFontStyle4Lr2A7w() == null && spanStyle.getFontWeight() == null) ? false : true;
    }
}
