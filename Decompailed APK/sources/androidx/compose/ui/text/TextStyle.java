package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: TextStyle.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextStyle {
    public static final Companion Companion = new Companion(null);
    private static final TextStyle Default = new TextStyle(0, 0, null, null, null, null, null, 0, null, null, null, 0, null, null, null, 0, 0, 0, null, null, null, 0, 0, null, 16777215, null);
    private final ParagraphStyle paragraphStyle;
    private final PlatformTextStyle platformStyle;
    private final SpanStyle spanStyle;

    public /* synthetic */ TextStyle(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i, int i2, long j5, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i3, int i4, TextMotion textMotion, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j3, baselineShift, textGeometricTransform, localeList, j4, textDecoration, shadow, drawStyle, i, i2, j5, textIndent, platformTextStyle, lineHeightStyle, i3, i4, textMotion);
    }

    public TextStyle(SpanStyle spanStyle, ParagraphStyle paragraphStyle, PlatformTextStyle platformTextStyle) {
        this.spanStyle = spanStyle;
        this.paragraphStyle = paragraphStyle;
        this.platformStyle = platformTextStyle;
    }

    public final SpanStyle getSpanStyle$ui_text_release() {
        return this.spanStyle;
    }

    public final ParagraphStyle getParagraphStyle$ui_text_release() {
        return this.paragraphStyle;
    }

    public final PlatformTextStyle getPlatformStyle() {
        return this.platformStyle;
    }

    public TextStyle(SpanStyle spanStyle, ParagraphStyle paragraphStyle) {
        this(spanStyle, paragraphStyle, TextStyleKt.createPlatformTextStyleInternal(spanStyle.getPlatformStyle(), paragraphStyle.getPlatformStyle()));
    }

    public /* synthetic */ TextStyle(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i, int i2, long j5, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i3, int i4, TextMotion textMotion, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j, (i5 & 2) != 0 ? TextUnit.Companion.m2494getUnspecifiedXSAIIZE() : j2, (i5 & 4) != 0 ? null : fontWeight, (i5 & 8) != 0 ? null : fontStyle, (i5 & 16) != 0 ? null : fontSynthesis, (i5 & 32) != 0 ? null : fontFamily, (i5 & 64) != 0 ? null : str, (i5 & 128) != 0 ? TextUnit.Companion.m2494getUnspecifiedXSAIIZE() : j3, (i5 & 256) != 0 ? null : baselineShift, (i5 & 512) != 0 ? null : textGeometricTransform, (i5 & 1024) != 0 ? null : localeList, (i5 & 2048) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j4, (i5 & PKIFailureInfo.certConfirmed) != 0 ? null : textDecoration, (i5 & 8192) != 0 ? null : shadow, (i5 & 16384) != 0 ? null : drawStyle, (i5 & 32768) != 0 ? TextAlign.Companion.m2346getUnspecifiede0LSkKk() : i, (i5 & PKIFailureInfo.notAuthorized) != 0 ? TextDirection.Companion.m2359getUnspecifieds_7Xco() : i2, (i5 & PKIFailureInfo.unsupportedVersion) != 0 ? TextUnit.Companion.m2494getUnspecifiedXSAIIZE() : j5, (i5 & PKIFailureInfo.transactionIdInUse) != 0 ? null : textIndent, (i5 & PKIFailureInfo.signerNotTrusted) != 0 ? null : platformTextStyle, (i5 & PKIFailureInfo.badCertTemplate) != 0 ? null : lineHeightStyle, (i5 & PKIFailureInfo.badSenderNonce) != 0 ? LineBreak.Companion.m2298getUnspecifiedrAG3T2k() : i3, (i5 & 4194304) != 0 ? Hyphens.Companion.m2286getUnspecifiedvmbZdU8() : i4, (i5 & 8388608) != 0 ? null : textMotion, null);
    }

    private TextStyle(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i, int i2, long j5, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i3, int i4, TextMotion textMotion) {
        this(new SpanStyle(j, j2, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j3, baselineShift, textGeometricTransform, localeList, j4, textDecoration, shadow, platformTextStyle != null ? platformTextStyle.getSpanStyle() : null, drawStyle, (DefaultConstructorMarker) null), new ParagraphStyle(i, i2, j5, textIndent, platformTextStyle != null ? platformTextStyle.getParagraphStyle() : null, lineHeightStyle, i3, i4, textMotion, null), platformTextStyle);
    }

    public final SpanStyle toSpanStyle() {
        return this.spanStyle;
    }

    public final ParagraphStyle toParagraphStyle() {
        return this.paragraphStyle;
    }

    public final TextStyle merge(TextStyle textStyle) {
        return (textStyle == null || Intrinsics.areEqual(textStyle, Default)) ? this : new TextStyle(toSpanStyle().merge(textStyle.toSpanStyle()), toParagraphStyle().merge(textStyle.toParagraphStyle()));
    }

    /* JADX INFO: renamed from: merge-dA7vx0o, reason: not valid java name */
    public final TextStyle m2140mergedA7vx0o(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i, int i2, long j5, TextIndent textIndent, LineHeightStyle lineHeightStyle, int i3, int i4, PlatformTextStyle platformTextStyle, TextMotion textMotion) {
        SpanStyle spanStyleM2092fastMergedSHsh3o = SpanStyleKt.m2092fastMergedSHsh3o(this.spanStyle, j, null, Float.NaN, j2, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j3, baselineShift, textGeometricTransform, localeList, j4, textDecoration, shadow, platformTextStyle != null ? platformTextStyle.getSpanStyle() : null, drawStyle);
        ParagraphStyle paragraphStyleM2071fastMergej5T8yCg = ParagraphStyleKt.m2071fastMergej5T8yCg(this.paragraphStyle, i, i2, j5, textIndent, platformTextStyle != null ? platformTextStyle.getParagraphStyle() : null, lineHeightStyle, i3, i4, textMotion);
        return (this.spanStyle == spanStyleM2092fastMergedSHsh3o && this.paragraphStyle == paragraphStyleM2071fastMergej5T8yCg) ? this : new TextStyle(spanStyleM2092fastMergedSHsh3o, paragraphStyleM2071fastMergej5T8yCg);
    }

    public final TextStyle merge(ParagraphStyle paragraphStyle) {
        return new TextStyle(toSpanStyle(), toParagraphStyle().merge(paragraphStyle));
    }

    /* JADX INFO: renamed from: copy-p1EtxEg, reason: not valid java name */
    public final TextStyle m2127copyp1EtxEg(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle, int i, int i2, long j5, TextIndent textIndent, PlatformTextStyle platformTextStyle, LineHeightStyle lineHeightStyle, int i3, int i4, TextMotion textMotion) {
        TextForegroundStyle textForegroundStyleM2361from8_81llA;
        if (Color.m1296equalsimpl0(j, this.spanStyle.m2087getColor0d7_KjU())) {
            textForegroundStyleM2361from8_81llA = this.spanStyle.getTextForegroundStyle$ui_text_release();
        } else {
            textForegroundStyleM2361from8_81llA = TextForegroundStyle.Companion.m2361from8_81llA(j);
        }
        return new TextStyle(new SpanStyle(textForegroundStyleM2361from8_81llA, j2, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j3, baselineShift, textGeometricTransform, localeList, j4, textDecoration, shadow, platformTextStyle != null ? platformTextStyle.getSpanStyle() : null, drawStyle, (DefaultConstructorMarker) null), new ParagraphStyle(i, i2, j5, textIndent, platformTextStyle != null ? platformTextStyle.getParagraphStyle() : null, lineHeightStyle, i3, i4, textMotion, null), platformTextStyle);
    }

    public final Brush getBrush() {
        return this.spanStyle.getBrush();
    }

    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name */
    public final long m2130getColor0d7_KjU() {
        return this.spanStyle.m2087getColor0d7_KjU();
    }

    public final float getAlpha() {
        return this.spanStyle.getAlpha();
    }

    /* JADX INFO: renamed from: getFontSize-XSAIIZE, reason: not valid java name */
    public final long m2131getFontSizeXSAIIZE() {
        return this.spanStyle.m2088getFontSizeXSAIIZE();
    }

    public final FontWeight getFontWeight() {
        return this.spanStyle.getFontWeight();
    }

    /* JADX INFO: renamed from: getFontStyle-4Lr2A7w, reason: not valid java name */
    public final FontStyle m2132getFontStyle4Lr2A7w() {
        return this.spanStyle.m2089getFontStyle4Lr2A7w();
    }

    /* JADX INFO: renamed from: getFontSynthesis-ZQGJjVo, reason: not valid java name */
    public final FontSynthesis m2133getFontSynthesisZQGJjVo() {
        return this.spanStyle.m2090getFontSynthesisZQGJjVo();
    }

    public final FontFamily getFontFamily() {
        return this.spanStyle.getFontFamily();
    }

    public final String getFontFeatureSettings() {
        return this.spanStyle.getFontFeatureSettings();
    }

    /* JADX INFO: renamed from: getLetterSpacing-XSAIIZE, reason: not valid java name */
    public final long m2135getLetterSpacingXSAIIZE() {
        return this.spanStyle.m2091getLetterSpacingXSAIIZE();
    }

    /* JADX INFO: renamed from: getBaselineShift-5SSeXJ0, reason: not valid java name */
    public final BaselineShift m2129getBaselineShift5SSeXJ0() {
        return this.spanStyle.m2086getBaselineShift5SSeXJ0();
    }

    public final TextGeometricTransform getTextGeometricTransform() {
        return this.spanStyle.getTextGeometricTransform();
    }

    public final LocaleList getLocaleList() {
        return this.spanStyle.getLocaleList();
    }

    /* JADX INFO: renamed from: getBackground-0d7_KjU, reason: not valid java name */
    public final long m2128getBackground0d7_KjU() {
        return this.spanStyle.m2085getBackground0d7_KjU();
    }

    public final TextDecoration getTextDecoration() {
        return this.spanStyle.getTextDecoration();
    }

    public final Shadow getShadow() {
        return this.spanStyle.getShadow();
    }

    public final DrawStyle getDrawStyle() {
        return this.spanStyle.getDrawStyle();
    }

    /* JADX INFO: renamed from: getTextAlign-e0LSkKk, reason: not valid java name */
    public final int m2138getTextAligne0LSkKk() {
        return this.paragraphStyle.m2069getTextAligne0LSkKk();
    }

    /* JADX INFO: renamed from: getTextDirection-s_7X-co, reason: not valid java name */
    public final int m2139getTextDirections_7Xco() {
        return this.paragraphStyle.m2070getTextDirections_7Xco();
    }

    /* JADX INFO: renamed from: getLineHeight-XSAIIZE, reason: not valid java name */
    public final long m2137getLineHeightXSAIIZE() {
        return this.paragraphStyle.m2068getLineHeightXSAIIZE();
    }

    public final TextIndent getTextIndent() {
        return this.paragraphStyle.getTextIndent();
    }

    public final LineHeightStyle getLineHeightStyle() {
        return this.paragraphStyle.getLineHeightStyle();
    }

    /* JADX INFO: renamed from: getHyphens-vmbZdU8, reason: not valid java name */
    public final int m2134getHyphensvmbZdU8() {
        return this.paragraphStyle.m2066getHyphensvmbZdU8();
    }

    /* JADX INFO: renamed from: getLineBreak-rAG3T2k, reason: not valid java name */
    public final int m2136getLineBreakrAG3T2k() {
        return this.paragraphStyle.m2067getLineBreakrAG3T2k();
    }

    public final TextMotion getTextMotion() {
        return this.paragraphStyle.getTextMotion();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextStyle)) {
            return false;
        }
        TextStyle textStyle = (TextStyle) obj;
        return Intrinsics.areEqual(this.spanStyle, textStyle.spanStyle) && Intrinsics.areEqual(this.paragraphStyle, textStyle.paragraphStyle) && Intrinsics.areEqual(this.platformStyle, textStyle.platformStyle);
    }

    public final boolean hasSameLayoutAffectingAttributes(TextStyle textStyle) {
        return this == textStyle || (Intrinsics.areEqual(this.paragraphStyle, textStyle.paragraphStyle) && this.spanStyle.hasSameLayoutAffectingAttributes$ui_text_release(textStyle.spanStyle));
    }

    public final boolean hasSameDrawAffectingAttributes(TextStyle textStyle) {
        return this == textStyle || this.spanStyle.hasSameNonLayoutAttributes$ui_text_release(textStyle.spanStyle);
    }

    public int hashCode() {
        int iHashCode = ((this.spanStyle.hashCode() * 31) + this.paragraphStyle.hashCode()) * 31;
        PlatformTextStyle platformTextStyle = this.platformStyle;
        return iHashCode + (platformTextStyle != null ? platformTextStyle.hashCode() : 0);
    }

    public String toString() {
        return "TextStyle(color=" + ((Object) Color.m1303toStringimpl(m2130getColor0d7_KjU())) + ", brush=" + getBrush() + ", alpha=" + getAlpha() + ", fontSize=" + ((Object) TextUnit.m2492toStringimpl(m2131getFontSizeXSAIIZE())) + ", fontWeight=" + getFontWeight() + ", fontStyle=" + m2132getFontStyle4Lr2A7w() + ", fontSynthesis=" + m2133getFontSynthesisZQGJjVo() + ", fontFamily=" + getFontFamily() + ", fontFeatureSettings=" + getFontFeatureSettings() + ", letterSpacing=" + ((Object) TextUnit.m2492toStringimpl(m2135getLetterSpacingXSAIIZE())) + ", baselineShift=" + m2129getBaselineShift5SSeXJ0() + ", textGeometricTransform=" + getTextGeometricTransform() + ", localeList=" + getLocaleList() + ", background=" + ((Object) Color.m1303toStringimpl(m2128getBackground0d7_KjU())) + ", textDecoration=" + getTextDecoration() + ", shadow=" + getShadow() + ", drawStyle=" + getDrawStyle() + ", textAlign=" + ((Object) TextAlign.m2338toStringimpl(m2138getTextAligne0LSkKk())) + ", textDirection=" + ((Object) TextDirection.m2352toStringimpl(m2139getTextDirections_7Xco())) + ", lineHeight=" + ((Object) TextUnit.m2492toStringimpl(m2137getLineHeightXSAIIZE())) + ", textIndent=" + getTextIndent() + ", platformStyle=" + this.platformStyle + ", lineHeightStyle=" + getLineHeightStyle() + ", lineBreak=" + ((Object) LineBreak.m2295toStringimpl(m2136getLineBreakrAG3T2k())) + ", hyphens=" + ((Object) Hyphens.m2282toStringimpl(m2134getHyphensvmbZdU8())) + ", textMotion=" + getTextMotion() + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: TextStyle.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TextStyle getDefault() {
            return TextStyle.Default;
        }
    }
}
