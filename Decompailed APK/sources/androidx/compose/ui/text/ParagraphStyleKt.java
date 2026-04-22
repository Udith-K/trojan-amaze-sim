package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextIndentKt;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParagraphStyle.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ParagraphStyleKt {
    private static final long DefaultLineHeight = TextUnit.Companion.m2494getUnspecifiedXSAIIZE();

    public static final ParagraphStyle lerp(ParagraphStyle paragraphStyle, ParagraphStyle paragraphStyle2, float f) {
        int iM2339unboximpl = ((TextAlign) SpanStyleKt.lerpDiscrete(TextAlign.m2333boximpl(paragraphStyle.m2069getTextAligne0LSkKk()), TextAlign.m2333boximpl(paragraphStyle2.m2069getTextAligne0LSkKk()), f)).m2339unboximpl();
        int iM2353unboximpl = ((TextDirection) SpanStyleKt.lerpDiscrete(TextDirection.m2347boximpl(paragraphStyle.m2070getTextDirections_7Xco()), TextDirection.m2347boximpl(paragraphStyle2.m2070getTextDirections_7Xco()), f)).m2353unboximpl();
        long jM2093lerpTextUnitInheritableC3pnCVY = SpanStyleKt.m2093lerpTextUnitInheritableC3pnCVY(paragraphStyle.m2068getLineHeightXSAIIZE(), paragraphStyle2.m2068getLineHeightXSAIIZE(), f);
        TextIndent textIndent = paragraphStyle.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.Companion.getNone();
        }
        TextIndent textIndent2 = paragraphStyle2.getTextIndent();
        if (textIndent2 == null) {
            textIndent2 = TextIndent.Companion.getNone();
        }
        return new ParagraphStyle(iM2339unboximpl, iM2353unboximpl, jM2093lerpTextUnitInheritableC3pnCVY, TextIndentKt.lerp(textIndent, textIndent2, f), lerpPlatformStyle(paragraphStyle.getPlatformStyle(), paragraphStyle2.getPlatformStyle(), f), (LineHeightStyle) SpanStyleKt.lerpDiscrete(paragraphStyle.getLineHeightStyle(), paragraphStyle2.getLineHeightStyle(), f), ((LineBreak) SpanStyleKt.lerpDiscrete(LineBreak.m2287boximpl(paragraphStyle.m2067getLineBreakrAG3T2k()), LineBreak.m2287boximpl(paragraphStyle2.m2067getLineBreakrAG3T2k()), f)).m2296unboximpl(), ((Hyphens) SpanStyleKt.lerpDiscrete(Hyphens.m2277boximpl(paragraphStyle.m2066getHyphensvmbZdU8()), Hyphens.m2277boximpl(paragraphStyle2.m2066getHyphensvmbZdU8()), f)).m2283unboximpl(), (TextMotion) SpanStyleKt.lerpDiscrete(paragraphStyle.getTextMotion(), paragraphStyle2.getTextMotion(), f), null);
    }

    private static final PlatformParagraphStyle lerpPlatformStyle(PlatformParagraphStyle platformParagraphStyle, PlatformParagraphStyle platformParagraphStyle2, float f) {
        if (platformParagraphStyle == null && platformParagraphStyle2 == null) {
            return null;
        }
        if (platformParagraphStyle == null) {
            platformParagraphStyle = PlatformParagraphStyle.Companion.getDefault();
        }
        if (platformParagraphStyle2 == null) {
            platformParagraphStyle2 = PlatformParagraphStyle.Companion.getDefault();
        }
        return AndroidTextStyle_androidKt.lerp(platformParagraphStyle, platformParagraphStyle2, f);
    }

    public static final ParagraphStyle resolveParagraphStyleDefaults(ParagraphStyle paragraphStyle, LayoutDirection layoutDirection) {
        int iM2069getTextAligne0LSkKk = paragraphStyle.m2069getTextAligne0LSkKk();
        TextAlign.Companion companion = TextAlign.Companion;
        int iM2345getStarte0LSkKk = TextAlign.m2336equalsimpl0(iM2069getTextAligne0LSkKk, companion.m2346getUnspecifiede0LSkKk()) ? companion.m2345getStarte0LSkKk() : paragraphStyle.m2069getTextAligne0LSkKk();
        int iM2141resolveTextDirectionIhaHGbI = TextStyleKt.m2141resolveTextDirectionIhaHGbI(layoutDirection, paragraphStyle.m2070getTextDirections_7Xco());
        long jM2068getLineHeightXSAIIZE = TextUnitKt.m2498isUnspecifiedR2X_6o(paragraphStyle.m2068getLineHeightXSAIIZE()) ? DefaultLineHeight : paragraphStyle.m2068getLineHeightXSAIIZE();
        TextIndent textIndent = paragraphStyle.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.Companion.getNone();
        }
        TextIndent textIndent2 = textIndent;
        PlatformParagraphStyle platformStyle = paragraphStyle.getPlatformStyle();
        LineHeightStyle lineHeightStyle = paragraphStyle.getLineHeightStyle();
        int iM2067getLineBreakrAG3T2k = paragraphStyle.m2067getLineBreakrAG3T2k();
        LineBreak.Companion companion2 = LineBreak.Companion;
        int iM2297getSimplerAG3T2k = LineBreak.m2290equalsimpl0(iM2067getLineBreakrAG3T2k, companion2.m2298getUnspecifiedrAG3T2k()) ? companion2.m2297getSimplerAG3T2k() : paragraphStyle.m2067getLineBreakrAG3T2k();
        int iM2066getHyphensvmbZdU8 = paragraphStyle.m2066getHyphensvmbZdU8();
        Hyphens.Companion companion3 = Hyphens.Companion;
        int iM2285getNonevmbZdU8 = Hyphens.m2280equalsimpl0(iM2066getHyphensvmbZdU8, companion3.m2286getUnspecifiedvmbZdU8()) ? companion3.m2285getNonevmbZdU8() : paragraphStyle.m2066getHyphensvmbZdU8();
        TextMotion textMotion = paragraphStyle.getTextMotion();
        if (textMotion == null) {
            textMotion = TextMotion.Companion.getStatic();
        }
        return new ParagraphStyle(iM2345getStarte0LSkKk, iM2141resolveTextDirectionIhaHGbI, jM2068getLineHeightXSAIIZE, textIndent2, platformStyle, lineHeightStyle, iM2297getSimplerAG3T2k, iM2285getNonevmbZdU8, textMotion, null);
    }

    /* JADX INFO: renamed from: fastMerge-j5T8yCg, reason: not valid java name */
    public static final ParagraphStyle m2071fastMergej5T8yCg(ParagraphStyle paragraphStyle, int i, int i2, long j, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, int i3, int i4, TextMotion textMotion) {
        long j2;
        int iM2069getTextAligne0LSkKk = i;
        TextIndent textIndent2 = textIndent;
        TextAlign.Companion companion = TextAlign.Companion;
        if (TextAlign.m2336equalsimpl0(iM2069getTextAligne0LSkKk, companion.m2346getUnspecifiede0LSkKk()) || TextAlign.m2336equalsimpl0(iM2069getTextAligne0LSkKk, paragraphStyle.m2069getTextAligne0LSkKk())) {
            if (TextUnitKt.m2498isUnspecifiedR2X_6o(j)) {
                j2 = j;
            } else {
                j2 = j;
                if (TextUnit.m2487equalsimpl0(j2, paragraphStyle.m2068getLineHeightXSAIIZE())) {
                }
            }
            if ((textIndent2 == null || Intrinsics.areEqual(textIndent2, paragraphStyle.getTextIndent())) && ((TextDirection.m2350equalsimpl0(i2, TextDirection.Companion.m2359getUnspecifieds_7Xco()) || TextDirection.m2350equalsimpl0(i2, paragraphStyle.m2070getTextDirections_7Xco())) && ((platformParagraphStyle == null || Intrinsics.areEqual(platformParagraphStyle, paragraphStyle.getPlatformStyle())) && ((lineHeightStyle == null || Intrinsics.areEqual(lineHeightStyle, paragraphStyle.getLineHeightStyle())) && ((LineBreak.m2290equalsimpl0(i3, LineBreak.Companion.m2298getUnspecifiedrAG3T2k()) || LineBreak.m2290equalsimpl0(i3, paragraphStyle.m2067getLineBreakrAG3T2k())) && ((Hyphens.m2280equalsimpl0(i4, Hyphens.Companion.m2286getUnspecifiedvmbZdU8()) || Hyphens.m2280equalsimpl0(i4, paragraphStyle.m2066getHyphensvmbZdU8())) && (textMotion == null || Intrinsics.areEqual(textMotion, paragraphStyle.getTextMotion())))))))) {
                return paragraphStyle;
            }
        } else {
            j2 = j;
        }
        long jM2068getLineHeightXSAIIZE = TextUnitKt.m2498isUnspecifiedR2X_6o(j) ? paragraphStyle.m2068getLineHeightXSAIIZE() : j2;
        if (textIndent2 == null) {
            textIndent2 = paragraphStyle.getTextIndent();
        }
        TextIndent textIndent3 = textIndent2;
        if (TextAlign.m2336equalsimpl0(iM2069getTextAligne0LSkKk, companion.m2346getUnspecifiede0LSkKk())) {
            iM2069getTextAligne0LSkKk = paragraphStyle.m2069getTextAligne0LSkKk();
        }
        return new ParagraphStyle(iM2069getTextAligne0LSkKk, !TextDirection.m2350equalsimpl0(i2, TextDirection.Companion.m2359getUnspecifieds_7Xco()) ? i2 : paragraphStyle.m2070getTextDirections_7Xco(), jM2068getLineHeightXSAIIZE, textIndent3, mergePlatformStyle(paragraphStyle, platformParagraphStyle), lineHeightStyle == null ? paragraphStyle.getLineHeightStyle() : lineHeightStyle, !LineBreak.m2290equalsimpl0(i3, LineBreak.Companion.m2298getUnspecifiedrAG3T2k()) ? i3 : paragraphStyle.m2067getLineBreakrAG3T2k(), !Hyphens.m2280equalsimpl0(i4, Hyphens.Companion.m2286getUnspecifiedvmbZdU8()) ? i4 : paragraphStyle.m2066getHyphensvmbZdU8(), textMotion == null ? paragraphStyle.getTextMotion() : textMotion, null);
    }

    private static final PlatformParagraphStyle mergePlatformStyle(ParagraphStyle paragraphStyle, PlatformParagraphStyle platformParagraphStyle) {
        if (paragraphStyle.getPlatformStyle() == null) {
            return platformParagraphStyle;
        }
        if (platformParagraphStyle == null) {
            return paragraphStyle.getPlatformStyle();
        }
        return paragraphStyle.getPlatformStyle().merge(platformParagraphStyle);
    }
}
