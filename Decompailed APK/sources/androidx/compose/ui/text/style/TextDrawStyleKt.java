package androidx.compose.ui.text.style;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.SpanStyleKt;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: TextForegroundStyle.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextDrawStyleKt {
    public static final TextForegroundStyle lerp(TextForegroundStyle textForegroundStyle, TextForegroundStyle textForegroundStyle2, float f) {
        boolean z = textForegroundStyle instanceof BrushStyle;
        if (!z && !(textForegroundStyle2 instanceof BrushStyle)) {
            return TextForegroundStyle.Companion.m2361from8_81llA(ColorKt.m1314lerpjxsXWHM(textForegroundStyle.mo2276getColor0d7_KjU(), textForegroundStyle2.mo2276getColor0d7_KjU(), f));
        }
        if (z && (textForegroundStyle2 instanceof BrushStyle)) {
            return TextForegroundStyle.Companion.from((Brush) SpanStyleKt.lerpDiscrete(((BrushStyle) textForegroundStyle).getBrush(), ((BrushStyle) textForegroundStyle2).getBrush(), f), MathHelpersKt.lerp(textForegroundStyle.getAlpha(), textForegroundStyle2.getAlpha(), f));
        }
        return (TextForegroundStyle) SpanStyleKt.lerpDiscrete(textForegroundStyle, textForegroundStyle2, f);
    }

    /* JADX INFO: renamed from: modulate-DxMtmZc, reason: not valid java name */
    public static final long m2360modulateDxMtmZc(long j, float f) {
        return (Float.isNaN(f) || f >= 1.0f) ? j : Color.m1294copywmQWz5c$default(j, Color.m1297getAlphaimpl(j) * f, 0.0f, 0.0f, 0.0f, 14, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float takeOrElse(float f, Function0 function0) {
        return Float.isNaN(f) ? ((Number) function0.invoke()).floatValue() : f;
    }
}
