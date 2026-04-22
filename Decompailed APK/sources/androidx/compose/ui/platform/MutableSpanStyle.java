package androidx.compose.ui.platform;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: AndroidClipboardManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class MutableSpanStyle {
    private long background;
    private BaselineShift baselineShift;
    private long color;
    private FontFamily fontFamily;
    private String fontFeatureSettings;
    private long fontSize;
    private FontStyle fontStyle;
    private FontSynthesis fontSynthesis;
    private FontWeight fontWeight;
    private long letterSpacing;
    private LocaleList localeList;
    private Shadow shadow;
    private TextDecoration textDecoration;
    private TextGeometricTransform textGeometricTransform;

    public /* synthetic */ MutableSpanStyle(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j3, baselineShift, textGeometricTransform, localeList, j4, textDecoration, shadow);
    }

    private MutableSpanStyle(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow) {
        this.color = j;
        this.fontSize = j2;
        this.fontWeight = fontWeight;
        this.fontStyle = fontStyle;
        this.fontSynthesis = fontSynthesis;
        this.fontFamily = fontFamily;
        this.fontFeatureSettings = str;
        this.letterSpacing = j3;
        this.baselineShift = baselineShift;
        this.textGeometricTransform = textGeometricTransform;
        this.localeList = localeList;
        this.background = j4;
        this.textDecoration = textDecoration;
        this.shadow = shadow;
    }

    public /* synthetic */ MutableSpanStyle(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j, (i & 2) != 0 ? TextUnit.Companion.m2494getUnspecifiedXSAIIZE() : j2, (i & 4) != 0 ? null : fontWeight, (i & 8) != 0 ? null : fontStyle, (i & 16) != 0 ? null : fontSynthesis, (i & 32) != 0 ? null : fontFamily, (i & 64) != 0 ? null : str, (i & 128) != 0 ? TextUnit.Companion.m2494getUnspecifiedXSAIIZE() : j3, (i & 256) != 0 ? null : baselineShift, (i & 512) != 0 ? null : textGeometricTransform, (i & 1024) != 0 ? null : localeList, (i & 2048) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j4, (i & PKIFailureInfo.certConfirmed) != 0 ? null : textDecoration, (i & 8192) != 0 ? null : shadow, null);
    }

    /* JADX INFO: renamed from: setColor-8_81llA, reason: not valid java name */
    public final void m1977setColor8_81llA(long j) {
        this.color = j;
    }

    /* JADX INFO: renamed from: setFontSize--R2X_6o, reason: not valid java name */
    public final void m1978setFontSizeR2X_6o(long j) {
        this.fontSize = j;
    }

    public final void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }

    /* JADX INFO: renamed from: setFontStyle-mLjRB2g, reason: not valid java name */
    public final void m1979setFontStylemLjRB2g(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
    }

    /* JADX INFO: renamed from: setFontSynthesis-tDdu0R4, reason: not valid java name */
    public final void m1980setFontSynthesistDdu0R4(FontSynthesis fontSynthesis) {
        this.fontSynthesis = fontSynthesis;
    }

    public final void setFontFeatureSettings(String str) {
        this.fontFeatureSettings = str;
    }

    /* JADX INFO: renamed from: setLetterSpacing--R2X_6o, reason: not valid java name */
    public final void m1981setLetterSpacingR2X_6o(long j) {
        this.letterSpacing = j;
    }

    /* JADX INFO: renamed from: setBaselineShift-_isdbwI, reason: not valid java name */
    public final void m1976setBaselineShift_isdbwI(BaselineShift baselineShift) {
        this.baselineShift = baselineShift;
    }

    public final void setTextGeometricTransform(TextGeometricTransform textGeometricTransform) {
        this.textGeometricTransform = textGeometricTransform;
    }

    /* JADX INFO: renamed from: setBackground-8_81llA, reason: not valid java name */
    public final void m1975setBackground8_81llA(long j) {
        this.background = j;
    }

    public final void setTextDecoration(TextDecoration textDecoration) {
        this.textDecoration = textDecoration;
    }

    public final void setShadow(Shadow shadow) {
        this.shadow = shadow;
    }

    public final SpanStyle toSpanStyle() {
        return new SpanStyle(this.color, this.fontSize, this.fontWeight, this.fontStyle, this.fontSynthesis, this.fontFamily, this.fontFeatureSettings, this.letterSpacing, this.baselineShift, this.textGeometricTransform, this.localeList, this.background, this.textDecoration, this.shadow, null, null, 49152, null);
    }
}
