package androidx.compose.material3.tokens;

import androidx.compose.material3.internal.DefaultPlatformTextStyle_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.LineHeightStyle;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: TypographyTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TypographyTokensKt {
    private static final LineHeightStyle DefaultLineHeightStyle;
    private static final TextStyle DefaultTextStyle;

    static {
        LineHeightStyle lineHeightStyle = new LineHeightStyle(LineHeightStyle.Alignment.Companion.m2323getCenterPIaL0Z0(), LineHeightStyle.Trim.Companion.m2332getNoneEVpEnUU(), null);
        DefaultLineHeightStyle = lineHeightStyle;
        TextStyle textStyle = TextStyle.Companion.getDefault();
        DefaultTextStyle = textStyle.m2127copyp1EtxEg((15204351 & 1) != 0 ? textStyle.spanStyle.m2087getColor0d7_KjU() : 0L, (15204351 & 2) != 0 ? textStyle.spanStyle.m2088getFontSizeXSAIIZE() : 0L, (15204351 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : null, (15204351 & 8) != 0 ? textStyle.spanStyle.m2089getFontStyle4Lr2A7w() : null, (15204351 & 16) != 0 ? textStyle.spanStyle.m2090getFontSynthesisZQGJjVo() : null, (15204351 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : null, (15204351 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : null, (15204351 & 128) != 0 ? textStyle.spanStyle.m2091getLetterSpacingXSAIIZE() : 0L, (15204351 & 256) != 0 ? textStyle.spanStyle.m2086getBaselineShift5SSeXJ0() : null, (15204351 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : null, (15204351 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : null, (15204351 & 2048) != 0 ? textStyle.spanStyle.m2085getBackground0d7_KjU() : 0L, (15204351 & PKIFailureInfo.certConfirmed) != 0 ? textStyle.spanStyle.getTextDecoration() : null, (15204351 & 8192) != 0 ? textStyle.spanStyle.getShadow() : null, (15204351 & 16384) != 0 ? textStyle.spanStyle.getDrawStyle() : null, (15204351 & 32768) != 0 ? textStyle.paragraphStyle.m2069getTextAligne0LSkKk() : 0, (15204351 & PKIFailureInfo.notAuthorized) != 0 ? textStyle.paragraphStyle.m2070getTextDirections_7Xco() : 0, (15204351 & PKIFailureInfo.unsupportedVersion) != 0 ? textStyle.paragraphStyle.m2068getLineHeightXSAIIZE() : 0L, (15204351 & PKIFailureInfo.transactionIdInUse) != 0 ? textStyle.paragraphStyle.getTextIndent() : null, (15204351 & PKIFailureInfo.signerNotTrusted) != 0 ? textStyle.platformStyle : DefaultPlatformTextStyle_androidKt.defaultPlatformTextStyle(), (15204351 & PKIFailureInfo.badCertTemplate) != 0 ? textStyle.paragraphStyle.getLineHeightStyle() : lineHeightStyle, (15204351 & PKIFailureInfo.badSenderNonce) != 0 ? textStyle.paragraphStyle.m2067getLineBreakrAG3T2k() : 0, (15204351 & 4194304) != 0 ? textStyle.paragraphStyle.m2066getHyphensvmbZdU8() : 0, (15204351 & 8388608) != 0 ? textStyle.paragraphStyle.getTextMotion() : null);
    }

    public static final TextStyle getDefaultTextStyle() {
        return DefaultTextStyle;
    }
}
