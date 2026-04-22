package androidx.compose.ui.text;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import androidx.compose.ui.text.TextGranularity;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.style.IndentationFixSpan;
import androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;

/* JADX INFO: compiled from: AndroidParagraph.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidParagraph_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutAlign-aXe7zB0, reason: not valid java name */
    public static final int m2030toLayoutAlignaXe7zB0(int i) {
        TextAlign.Companion companion = TextAlign.Companion;
        if (TextAlign.m2336equalsimpl0(i, companion.m2343getLefte0LSkKk())) {
            return 3;
        }
        if (TextAlign.m2336equalsimpl0(i, companion.m2344getRighte0LSkKk())) {
            return 4;
        }
        if (TextAlign.m2336equalsimpl0(i, companion.m2340getCentere0LSkKk())) {
            return 2;
        }
        return (!TextAlign.m2336equalsimpl0(i, companion.m2345getStarte0LSkKk()) && TextAlign.m2336equalsimpl0(i, companion.m2341getEnde0LSkKk())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutHyphenationFrequency--3fSNIE, reason: not valid java name */
    public static final int m2032toLayoutHyphenationFrequency3fSNIE(int i) {
        Hyphens.Companion companion = Hyphens.Companion;
        if (Hyphens.m2280equalsimpl0(i, companion.m2284getAutovmbZdU8())) {
            return Build.VERSION.SDK_INT <= 32 ? 2 : 4;
        }
        Hyphens.m2280equalsimpl0(i, companion.m2285getNonevmbZdU8());
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutBreakStrategy-xImikfE, reason: not valid java name */
    public static final int m2031toLayoutBreakStrategyxImikfE(int i) {
        LineBreak.Strategy.Companion companion = LineBreak.Strategy.Companion;
        if (LineBreak.Strategy.m2300equalsimpl0(i, companion.m2304getSimplefcGXIks())) {
            return 0;
        }
        if (LineBreak.Strategy.m2300equalsimpl0(i, companion.m2303getHighQualityfcGXIks())) {
            return 1;
        }
        return LineBreak.Strategy.m2300equalsimpl0(i, companion.m2302getBalancedfcGXIks()) ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutLineBreakStyle-hpcqdu8, reason: not valid java name */
    public static final int m2033toLayoutLineBreakStylehpcqdu8(int i) {
        LineBreak.Strictness.Companion companion = LineBreak.Strictness.Companion;
        if (LineBreak.Strictness.m2306equalsimpl0(i, companion.m2308getDefaultusljTpc())) {
            return 0;
        }
        if (LineBreak.Strictness.m2306equalsimpl0(i, companion.m2309getLooseusljTpc())) {
            return 1;
        }
        if (LineBreak.Strictness.m2306equalsimpl0(i, companion.m2310getNormalusljTpc())) {
            return 2;
        }
        return LineBreak.Strictness.m2306equalsimpl0(i, companion.m2311getStrictusljTpc()) ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutLineBreakWordStyle-wPN0Rpw, reason: not valid java name */
    public static final int m2034toLayoutLineBreakWordStylewPN0Rpw(int i) {
        LineBreak.WordBreak.Companion companion = LineBreak.WordBreak.Companion;
        return (!LineBreak.WordBreak.m2313equalsimpl0(i, companion.m2315getDefaultjp8hJ3c()) && LineBreak.WordBreak.m2313equalsimpl0(i, companion.m2316getPhrasejp8hJ3c())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int numberOfLinesThatFitMaxHeight(TextLayout textLayout, int i) {
        int lineCount = textLayout.getLineCount();
        for (int i2 = 0; i2 < lineCount; i2++) {
            if (textLayout.getLineBottom(i2) > i) {
                return i2;
            }
        }
        return textLayout.getLineCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldAttachIndentationFixSpan(TextStyle textStyle, boolean z) {
        if (!z || TextUnit.m2487equalsimpl0(textStyle.m2135getLetterSpacingXSAIIZE(), TextUnitKt.getSp(0)) || TextUnit.m2487equalsimpl0(textStyle.m2135getLetterSpacingXSAIIZE(), TextUnit.Companion.m2494getUnspecifiedXSAIIZE())) {
            return false;
        }
        int iM2138getTextAligne0LSkKk = textStyle.m2138getTextAligne0LSkKk();
        TextAlign.Companion companion = TextAlign.Companion;
        return (TextAlign.m2336equalsimpl0(iM2138getTextAligne0LSkKk, companion.m2346getUnspecifiede0LSkKk()) || TextAlign.m2336equalsimpl0(textStyle.m2138getTextAligne0LSkKk(), companion.m2345getStarte0LSkKk()) || TextAlign.m2336equalsimpl0(textStyle.m2138getTextAligne0LSkKk(), companion.m2342getJustifye0LSkKk())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence attachIndentationFixSpan(CharSequence charSequence) {
        if (charSequence.length() == 0) {
            return charSequence;
        }
        Spannable spannableString = charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence);
        SpannableExtensions_androidKt.setSpan(spannableString, new IndentationFixSpan(), spannableString.length() - 1, spannableString.length() - 1);
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutTextGranularity-duNsdkg, reason: not valid java name */
    public static final int m2035toLayoutTextGranularityduNsdkg(int i) {
        TextGranularity.Companion companion = TextGranularity.Companion;
        return (!TextGranularity.m2095equalsimpl0(i, companion.m2096getCharacterDRrd7Zo()) && TextGranularity.m2095equalsimpl0(i, companion.m2097getWordDRrd7Zo())) ? 1 : 0;
    }
}
