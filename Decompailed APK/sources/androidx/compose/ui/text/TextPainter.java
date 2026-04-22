package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.IntSize;

/* JADX INFO: compiled from: TextPainter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextPainter {
    public static final TextPainter INSTANCE = new TextPainter();

    private TextPainter() {
    }

    public final void paint(Canvas canvas, TextLayoutResult textLayoutResult) {
        long jM1305getBlack0d7_KjU;
        boolean z = textLayoutResult.getHasVisualOverflow() && !TextOverflow.m2372equalsimpl0(textLayoutResult.getLayoutInput().m2099getOverflowgIe3tQ8(), TextOverflow.Companion.m2377getVisiblegIe3tQ8());
        if (z) {
            Rect rectM1183Recttz77jQw = RectKt.m1183Recttz77jQw(Offset.Companion.m1171getZeroF1C5BW0(), SizeKt.Size(IntSize.m2476getWidthimpl(textLayoutResult.m2103getSizeYbymL2g()), IntSize.m2475getHeightimpl(textLayoutResult.m2103getSizeYbymL2g())));
            canvas.save();
            Canvas.CC.m1285clipRectmtrdDE$default(canvas, rectM1183Recttz77jQw, 0, 2, null);
        }
        SpanStyle spanStyle$ui_text_release = textLayoutResult.getLayoutInput().getStyle().getSpanStyle$ui_text_release();
        TextDecoration textDecoration = spanStyle$ui_text_release.getTextDecoration();
        if (textDecoration == null) {
            textDecoration = TextDecoration.Companion.getNone();
        }
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow = spanStyle$ui_text_release.getShadow();
        if (shadow == null) {
            shadow = Shadow.Companion.getNone();
        }
        Shadow shadow2 = shadow;
        DrawStyle drawStyle = spanStyle$ui_text_release.getDrawStyle();
        if (drawStyle == null) {
            drawStyle = Fill.INSTANCE;
        }
        DrawStyle drawStyle2 = drawStyle;
        try {
            Brush brush = spanStyle$ui_text_release.getBrush();
            if (brush == null) {
                if (spanStyle$ui_text_release.getTextForegroundStyle$ui_text_release() != TextForegroundStyle.Unspecified.INSTANCE) {
                    jM1305getBlack0d7_KjU = spanStyle$ui_text_release.getTextForegroundStyle$ui_text_release().mo2276getColor0d7_KjU();
                } else {
                    jM1305getBlack0d7_KjU = Color.Companion.m1305getBlack0d7_KjU();
                }
                textLayoutResult.getMultiParagraph().m2053paintLG529CI(canvas, (32 & 2) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : jM1305getBlack0d7_KjU, (32 & 4) != 0 ? null : shadow2, (32 & 8) != 0 ? null : textDecoration2, (32 & 16) == 0 ? drawStyle2 : null, (32 & 32) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : 0);
            } else {
                textLayoutResult.getMultiParagraph().m2054painthn5TExg(canvas, brush, (64 & 4) != 0 ? Float.NaN : spanStyle$ui_text_release.getTextForegroundStyle$ui_text_release() != TextForegroundStyle.Unspecified.INSTANCE ? spanStyle$ui_text_release.getTextForegroundStyle$ui_text_release().getAlpha() : 1.0f, (64 & 8) != 0 ? null : shadow2, (64 & 16) != 0 ? null : textDecoration2, (64 & 32) != 0 ? null : drawStyle2, (64 & 64) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : 0);
            }
            if (z) {
                canvas.restore();
            }
        } catch (Throwable th) {
            if (z) {
                canvas.restore();
            }
            throw th;
        }
    }
}
