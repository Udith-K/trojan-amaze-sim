package androidx.compose.ui.text.platform;

import android.graphics.Paint;
import android.text.TextPaint;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.platform.extensions.TextPaintExtensions_androidKt;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidTextPaint.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidTextPaint extends TextPaint {
    private int backingBlendMode;
    private Paint backingComposePaint;
    private Brush brush;
    private Size brushSize;
    private DrawStyle drawStyle;
    private State shaderState;
    private Shadow shadow;
    private TextDecoration textDecoration;

    public AndroidTextPaint(int i, float f) {
        super(i);
        ((TextPaint) this).density = f;
        this.textDecoration = TextDecoration.Companion.getNone();
        this.backingBlendMode = DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU();
        this.shadow = Shadow.Companion.getNone();
    }

    private final Paint getComposePaint() {
        Paint paint = this.backingComposePaint;
        if (paint != null) {
            return paint;
        }
        Paint paintAsComposePaint = AndroidPaint_androidKt.asComposePaint(this);
        this.backingComposePaint = paintAsComposePaint;
        return paintAsComposePaint;
    }

    public final void setTextDecoration(TextDecoration textDecoration) {
        if (textDecoration == null || Intrinsics.areEqual(this.textDecoration, textDecoration)) {
            return;
        }
        this.textDecoration = textDecoration;
        TextDecoration.Companion companion = TextDecoration.Companion;
        setUnderlineText(textDecoration.contains(companion.getUnderline()));
        setStrikeThruText(this.textDecoration.contains(companion.getLineThrough()));
    }

    public final void setShadow(Shadow shadow) {
        if (shadow == null || Intrinsics.areEqual(this.shadow, shadow)) {
            return;
        }
        this.shadow = shadow;
        if (Intrinsics.areEqual(shadow, Shadow.Companion.getNone())) {
            clearShadowLayer();
        } else {
            setShadowLayer(TextPaintExtensions_androidKt.correctBlurRadius(this.shadow.getBlurRadius()), Offset.m1159getXimpl(this.shadow.m1401getOffsetF1C5BW0()), Offset.m1160getYimpl(this.shadow.m1401getOffsetF1C5BW0()), ColorKt.m1316toArgb8_81llA(this.shadow.m1400getColor0d7_KjU()));
        }
    }

    /* JADX INFO: renamed from: setColor-8_81llA, reason: not valid java name */
    public final void m2253setColor8_81llA(long j) {
        if (j != 16) {
            setColor(ColorKt.m1316toArgb8_81llA(j));
            clearShader();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0035  */
    /* JADX INFO: renamed from: setBrush-12SF9DM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m2252setBrush12SF9DM(final androidx.compose.ui.graphics.Brush r5, final long r6, float r8) {
        /*
            r4 = this;
            if (r5 != 0) goto L6
            r4.clearShader()
            goto L6a
        L6:
            boolean r0 = r5 instanceof androidx.compose.ui.graphics.SolidColor
            if (r0 == 0) goto L18
            androidx.compose.ui.graphics.SolidColor r5 = (androidx.compose.ui.graphics.SolidColor) r5
            long r5 = r5.m1410getValue0d7_KjU()
            long r5 = androidx.compose.ui.text.style.TextDrawStyleKt.m2360modulateDxMtmZc(r5, r8)
            r4.m2253setColor8_81llA(r5)
            goto L6a
        L18:
            boolean r0 = r5 instanceof androidx.compose.ui.graphics.ShaderBrush
            if (r0 == 0) goto L6a
            androidx.compose.ui.graphics.Brush r0 = r4.brush
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r5)
            r1 = 0
            if (r0 == 0) goto L35
            androidx.compose.ui.geometry.Size r0 = r4.brushSize
            if (r0 != 0) goto L2b
            r0 = r1
            goto L33
        L2b:
            long r2 = r0.m1200unboximpl()
            boolean r0 = androidx.compose.ui.geometry.Size.m1193equalsimpl0(r2, r6)
        L33:
            if (r0 != 0) goto L54
        L35:
            r2 = 9205357640488583168(0x7fc000007fc00000, double:2.247117487993712E307)
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 == 0) goto L3f
            r1 = 1
        L3f:
            if (r1 == 0) goto L54
            r4.brush = r5
            androidx.compose.ui.geometry.Size r0 = androidx.compose.ui.geometry.Size.m1190boximpl(r6)
            r4.brushSize = r0
            androidx.compose.ui.text.platform.AndroidTextPaint$setBrush$1 r0 = new androidx.compose.ui.text.platform.AndroidTextPaint$setBrush$1
            r0.<init>()
            androidx.compose.runtime.State r5 = androidx.compose.runtime.SnapshotStateKt.derivedStateOf(r0)
            r4.shaderState = r5
        L54:
            androidx.compose.ui.graphics.Paint r5 = r4.getComposePaint()
            androidx.compose.runtime.State r6 = r4.shaderState
            if (r6 == 0) goto L63
            java.lang.Object r6 = r6.getValue()
            android.graphics.Shader r6 = (android.graphics.Shader) r6
            goto L64
        L63:
            r6 = 0
        L64:
            r5.setShader(r6)
            androidx.compose.ui.text.platform.AndroidTextPaint_androidKt.setAlpha(r4, r8)
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.platform.AndroidTextPaint.m2252setBrush12SF9DM(androidx.compose.ui.graphics.Brush, long, float):void");
    }

    public final void setDrawStyle(DrawStyle drawStyle) {
        if (drawStyle == null || Intrinsics.areEqual(this.drawStyle, drawStyle)) {
            return;
        }
        this.drawStyle = drawStyle;
        if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
            setStyle(Paint.Style.FILL);
            return;
        }
        if (drawStyle instanceof Stroke) {
            getComposePaint().mo1232setStylek9PVt8s(PaintingStyle.Companion.m1382getStrokeTiuSbCo());
            Stroke stroke = (Stroke) drawStyle;
            getComposePaint().setStrokeWidth(stroke.getWidth());
            getComposePaint().setStrokeMiterLimit(stroke.getMiter());
            getComposePaint().mo1231setStrokeJoinWw9F2mQ(stroke.m1512getJoinLxFBmk8());
            getComposePaint().mo1230setStrokeCapBeK7IIE(stroke.m1511getCapKaPHkGw());
            androidx.compose.ui.graphics.Paint composePaint = getComposePaint();
            stroke.getPathEffect();
            composePaint.setPathEffect(null);
        }
    }

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: not valid java name */
    public final int m2250getBlendMode0nO6VwU() {
        return this.backingBlendMode;
    }

    /* JADX INFO: renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m2251setBlendModes9anfk8(int i) {
        if (BlendMode.m1246equalsimpl0(i, this.backingBlendMode)) {
            return;
        }
        getComposePaint().mo1227setBlendModes9anfk8(i);
        this.backingBlendMode = i;
    }

    private final void clearShader() {
        this.shaderState = null;
        this.brush = null;
        this.brushSize = null;
        setShader(null);
    }
}
