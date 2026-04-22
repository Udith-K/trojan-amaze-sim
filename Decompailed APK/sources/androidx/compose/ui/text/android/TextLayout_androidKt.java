package androidx.compose.ui.text.android;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: TextLayout.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextLayout_androidKt {
    private static final TextAndroidCanvas SharedTextAndroidCanvas = new TextAndroidCanvas();
    private static final long ZeroVerticalPadding = VerticalPaddings(0, 0);

    public static final TextDirectionHeuristic getTextDirectionHeuristic(int i) {
        if (i == 0) {
            return TextDirectionHeuristics.LTR;
        }
        if (i == 1) {
            return TextDirectionHeuristics.RTL;
        }
        if (i == 2) {
            return TextDirectionHeuristics.FIRSTSTRONG_LTR;
        }
        if (i == 3) {
            return TextDirectionHeuristics.FIRSTSTRONG_RTL;
        }
        if (i == 4) {
            return TextDirectionHeuristics.ANYRTL_LTR;
        }
        if (i == 5) {
            return TextDirectionHeuristics.LOCALE;
        }
        return TextDirectionHeuristics.FIRSTSTRONG_LTR;
    }

    public static final long VerticalPaddings(int i, int i2) {
        return VerticalPaddings.m2143constructorimpl((((long) i2) & BodyPartID.bodyIdMax) | (((long) i) << 32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getVerticalPaddings(TextLayout textLayout) {
        if (textLayout.getIncludePadding() || textLayout.isFallbackLinespacingApplied$ui_text_release()) {
            return ZeroVerticalPadding;
        }
        TextPaint paint = textLayout.getLayout().getPaint();
        CharSequence text = textLayout.getLayout().getText();
        Rect charSequenceBounds = PaintExtensions_androidKt.getCharSequenceBounds(paint, text, textLayout.getLayout().getLineStart(0), textLayout.getLayout().getLineEnd(0));
        int lineAscent = textLayout.getLayout().getLineAscent(0);
        int i = charSequenceBounds.top;
        int topPadding = i < lineAscent ? lineAscent - i : textLayout.getLayout().getTopPadding();
        if (textLayout.getLineCount() != 1) {
            int lineCount = textLayout.getLineCount() - 1;
            charSequenceBounds = PaintExtensions_androidKt.getCharSequenceBounds(paint, text, textLayout.getLayout().getLineStart(lineCount), textLayout.getLayout().getLineEnd(lineCount));
        }
        int lineDescent = textLayout.getLayout().getLineDescent(textLayout.getLineCount() - 1);
        int i2 = charSequenceBounds.bottom;
        int bottomPadding = i2 > lineDescent ? i2 - lineDescent : textLayout.getLayout().getBottomPadding();
        if (topPadding == 0 && bottomPadding == 0) {
            return ZeroVerticalPadding;
        }
        return VerticalPaddings(topPadding, bottomPadding);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getLineHeightPaddings(LineHeightStyleSpan[] lineHeightStyleSpanArr) {
        int iMax = 0;
        int iMax2 = 0;
        for (LineHeightStyleSpan lineHeightStyleSpan : lineHeightStyleSpanArr) {
            if (lineHeightStyleSpan.getFirstAscentDiff() < 0) {
                iMax = Math.max(iMax, Math.abs(lineHeightStyleSpan.getFirstAscentDiff()));
            }
            if (lineHeightStyleSpan.getLastDescentDiff() < 0) {
                iMax2 = Math.max(iMax, Math.abs(lineHeightStyleSpan.getLastDescentDiff()));
            }
        }
        if (iMax == 0 && iMax2 == 0) {
            return ZeroVerticalPadding;
        }
        return VerticalPaddings(iMax, iMax2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Paint.FontMetricsInt getLastLineMetrics(TextLayout textLayout, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, LineHeightStyleSpan[] lineHeightStyleSpanArr) {
        int lineCount = textLayout.getLineCount() - 1;
        if (textLayout.getLayout().getLineStart(lineCount) != textLayout.getLayout().getLineEnd(lineCount) || lineHeightStyleSpanArr == null || lineHeightStyleSpanArr.length == 0) {
            return null;
        }
        SpannableString spannableString = new SpannableString("\u200b");
        LineHeightStyleSpan lineHeightStyleSpan = (LineHeightStyleSpan) ArraysKt.first(lineHeightStyleSpanArr);
        spannableString.setSpan(lineHeightStyleSpan.copy$ui_text_release(0, spannableString.length(), (lineCount == 0 || !lineHeightStyleSpan.getTrimLastLineBottom()) ? lineHeightStyleSpan.getTrimLastLineBottom() : false), 0, spannableString.length(), 33);
        StaticLayout staticLayoutCreate = StaticLayoutFactory.INSTANCE.create(spannableString, textPaint, Integer.MAX_VALUE, (2072512 & 8) != 0 ? 0 : 0, (2072512 & 16) != 0 ? spannableString.length() : spannableString.length(), (2072512 & 32) != 0 ? LayoutCompat.INSTANCE.getDEFAULT_TEXT_DIRECTION_HEURISTIC$ui_text_release() : textDirectionHeuristic, (2072512 & 64) != 0 ? LayoutCompat.INSTANCE.getDEFAULT_LAYOUT_ALIGNMENT$ui_text_release() : null, (2072512 & 128) != 0 ? Integer.MAX_VALUE : 0, (2072512 & 256) != 0 ? null : null, (2072512 & 512) != 0 ? Integer.MAX_VALUE : 0, (2072512 & 1024) != 0 ? 1.0f : 0.0f, (2072512 & 2048) != 0 ? 0.0f : 0.0f, (2072512 & PKIFailureInfo.certConfirmed) != 0 ? 0 : 0, (2072512 & 8192) != 0 ? false : textLayout.getIncludePadding(), (2072512 & 16384) != 0 ? true : textLayout.getFallbackLineSpacing(), (32768 & 2072512) != 0 ? 0 : 0, (65536 & 2072512) != 0 ? 0 : 0, (131072 & 2072512) != 0 ? 0 : 0, (262144 & 2072512) != 0 ? 0 : 0, (524288 & 2072512) != 0 ? null : null, (2072512 & PKIFailureInfo.badCertTemplate) != 0 ? null : null);
        Paint.FontMetricsInt fontMetricsInt = new Paint.FontMetricsInt();
        fontMetricsInt.ascent = staticLayoutCreate.getLineAscent(0);
        fontMetricsInt.descent = staticLayoutCreate.getLineDescent(0);
        fontMetricsInt.top = staticLayoutCreate.getLineTop(0);
        fontMetricsInt.bottom = staticLayoutCreate.getLineBottom(0);
        return fontMetricsInt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LineHeightStyleSpan[] getLineHeightSpans(TextLayout textLayout) {
        if (!(textLayout.getText() instanceof Spanned)) {
            return null;
        }
        CharSequence text = textLayout.getText();
        Intrinsics.checkNotNull(text, "null cannot be cast to non-null type android.text.Spanned");
        if (!SpannedExtensions_androidKt.hasSpan((Spanned) text, LineHeightStyleSpan.class) && textLayout.getText().length() > 0) {
            return null;
        }
        CharSequence text2 = textLayout.getText();
        Intrinsics.checkNotNull(text2, "null cannot be cast to non-null type android.text.Spanned");
        return (LineHeightStyleSpan[]) ((Spanned) text2).getSpans(0, textLayout.getText().length(), LineHeightStyleSpan.class);
    }

    public static final boolean isLineEllipsized(Layout layout, int i) {
        return layout.getEllipsisCount(i) > 0;
    }
}
