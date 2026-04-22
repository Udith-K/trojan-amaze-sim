package androidx.compose.ui.text.platform.style;

import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DrawStyleSpan.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DrawStyleSpan extends CharacterStyle implements UpdateAppearance {
    private final DrawStyle drawStyle;

    public DrawStyleSpan(DrawStyle drawStyle) {
        this.drawStyle = drawStyle;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        if (textPaint != null) {
            DrawStyle drawStyle = this.drawStyle;
            if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
                textPaint.setStyle(Paint.Style.FILL);
                return;
            }
            if (drawStyle instanceof Stroke) {
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setStrokeWidth(((Stroke) this.drawStyle).getWidth());
                textPaint.setStrokeMiter(((Stroke) this.drawStyle).getMiter());
                textPaint.setStrokeJoin(m2264toAndroidJoinWw9F2mQ(((Stroke) this.drawStyle).m1512getJoinLxFBmk8()));
                textPaint.setStrokeCap(m2263toAndroidCapBeK7IIE(((Stroke) this.drawStyle).m1511getCapKaPHkGw()));
                ((Stroke) this.drawStyle).getPathEffect();
                textPaint.setPathEffect(null);
            }
        }
    }

    /* JADX INFO: renamed from: toAndroidJoin-Ww9F2mQ, reason: not valid java name */
    private final Paint.Join m2264toAndroidJoinWw9F2mQ(int i) {
        StrokeJoin.Companion companion = StrokeJoin.Companion;
        return StrokeJoin.m1419equalsimpl0(i, companion.m1423getMiterLxFBmk8()) ? Paint.Join.MITER : StrokeJoin.m1419equalsimpl0(i, companion.m1424getRoundLxFBmk8()) ? Paint.Join.ROUND : StrokeJoin.m1419equalsimpl0(i, companion.m1422getBevelLxFBmk8()) ? Paint.Join.BEVEL : Paint.Join.MITER;
    }

    /* JADX INFO: renamed from: toAndroidCap-BeK7IIE, reason: not valid java name */
    private final Paint.Cap m2263toAndroidCapBeK7IIE(int i) {
        StrokeCap.Companion companion = StrokeCap.Companion;
        return StrokeCap.m1412equalsimpl0(i, companion.m1415getButtKaPHkGw()) ? Paint.Cap.BUTT : StrokeCap.m1412equalsimpl0(i, companion.m1416getRoundKaPHkGw()) ? Paint.Cap.ROUND : StrokeCap.m1412equalsimpl0(i, companion.m1417getSquareKaPHkGw()) ? Paint.Cap.SQUARE : Paint.Cap.BUTT;
    }
}
