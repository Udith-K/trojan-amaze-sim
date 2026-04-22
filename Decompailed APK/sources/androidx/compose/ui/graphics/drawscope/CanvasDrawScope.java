package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.FontScaling;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CanvasDrawScope.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CanvasDrawScope implements DrawScope {
    private Paint fillPaint;
    private Paint strokePaint;
    private final DrawParams drawParams = new DrawParams(null, null, null, 0, 15, null);
    private final DrawContext drawContext = new DrawContext() { // from class: androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1
        private GraphicsLayer graphicsLayer;
        private final DrawTransform transform = CanvasDrawScopeKt.asDrawTransform(this);

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public Canvas getCanvas() {
            return this.this$0.getDrawParams().getCanvas();
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public void setCanvas(Canvas canvas) {
            this.this$0.getDrawParams().setCanvas(canvas);
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public long mo1487getSizeNHjbRc() {
            return this.this$0.getDrawParams().m1485getSizeNHjbRc();
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        /* JADX INFO: renamed from: setSize-uvyYCjk, reason: not valid java name */
        public void mo1488setSizeuvyYCjk(long j) {
            this.this$0.getDrawParams().m1486setSizeuvyYCjk(j);
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public DrawTransform getTransform() {
            return this.transform;
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public LayoutDirection getLayoutDirection() {
            return this.this$0.getDrawParams().getLayoutDirection();
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public void setLayoutDirection(LayoutDirection layoutDirection) {
            this.this$0.getDrawParams().setLayoutDirection(layoutDirection);
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public Density getDensity() {
            return this.this$0.getDrawParams().getDensity();
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public void setDensity(Density density) {
            this.this$0.getDrawParams().setDensity(density);
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public GraphicsLayer getGraphicsLayer() {
            return this.graphicsLayer;
        }

        @Override // androidx.compose.ui.graphics.drawscope.DrawContext
        public void setGraphicsLayer(GraphicsLayer graphicsLayer) {
            this.graphicsLayer = graphicsLayer;
        }
    };

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
    public /* synthetic */ long mo1482getCenterF1C5BW0() {
        return SizeKt.m1203getCenteruvyYCjk(getDrawContext().mo1487getSizeNHjbRc());
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    public /* synthetic */ long mo1483getSizeNHjbRc() {
        return getDrawContext().mo1487getSizeNHjbRc();
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public /* synthetic */ int mo204roundToPx0680j_4(float f) {
        return Density.CC.m2406$default$roundToPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public /* synthetic */ float mo205toDpGaN1DYA(long j) {
        return FontScaling.CC.m2449$default$toDpGaN1DYA(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo206toDpu2uoSUM(float f) {
        return Dp.m2416constructorimpl(f / getDensity());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo207toDpu2uoSUM(int i) {
        return Dp.m2416constructorimpl(i / getDensity());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public /* synthetic */ long mo208toDpSizekrfVVM(long j) {
        return Density.CC.m2409$default$toDpSizekrfVVM(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public /* synthetic */ float mo209toPxR2X_6o(long j) {
        return Density.CC.m2410$default$toPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public /* synthetic */ float mo210toPx0680j_4(float f) {
        return Density.CC.m2411$default$toPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public /* synthetic */ long mo211toSizeXkaWNTQ(long j) {
        return Density.CC.m2412$default$toSizeXkaWNTQ(this, j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public /* synthetic */ long mo212toSp0xMU5do(float f) {
        return FontScaling.CC.m2450$default$toSp0xMU5do(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public /* synthetic */ long mo213toSpkPz2Gy4(float f) {
        return mo212toSp0xMU5do(mo206toDpu2uoSUM(f));
    }

    public final DrawParams getDrawParams() {
        return this.drawParams;
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public LayoutDirection getLayoutDirection() {
        return this.drawParams.getLayoutDirection();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.drawParams.getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.drawParams.getDensity().getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public DrawContext getDrawContext() {
        return this.drawContext;
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-1RTmtNc, reason: not valid java name */
    public void mo1474drawLine1RTmtNc(Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2) {
        this.drawParams.getCanvas().mo1214drawLineWko1d7g(j, j2, m1468configureStrokePaintho4zsrM$default(this, brush, f, 4.0f, i, StrokeJoin.Companion.m1423getMiterLxFBmk8(), pathEffect, f2, colorFilter, i2, 0, 512, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-NGM6Ib0, reason: not valid java name */
    public void mo1475drawLineNGM6Ib0(long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2) {
        this.drawParams.getCanvas().mo1214drawLineWko1d7g(j2, j3, m1466configureStrokePaintQ_0CZUI$default(this, j, f, 4.0f, i, StrokeJoin.Companion.m1423getMiterLxFBmk8(), pathEffect, f2, colorFilter, i2, 0, 512, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-AsUm42w, reason: not valid java name */
    public void mo1478drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.drawParams.getCanvas().drawRect(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j), Offset.m1159getXimpl(j) + Size.m1196getWidthimpl(j2), Offset.m1160getYimpl(j) + Size.m1194getHeightimpl(j2), m1464configurePaintswdJneE$default(this, brush, drawStyle, f, colorFilter, i, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-n-J9OG0, reason: not valid java name */
    public void mo1479drawRectnJ9OG0(long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.drawParams.getCanvas().drawRect(Offset.m1159getXimpl(j2), Offset.m1160getYimpl(j2), Offset.m1159getXimpl(j2) + Size.m1196getWidthimpl(j3), Offset.m1160getYimpl(j2) + Size.m1194getHeightimpl(j3), m1462configurePaint2qPWKa0$default(this, j, drawStyle, f, colorFilter, i, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-gbVJVH8, reason: not valid java name */
    public void mo1473drawImagegbVJVH8(ImageBitmap imageBitmap, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.drawParams.getCanvas().mo1212drawImaged4ec7I(imageBitmap, j, m1464configurePaintswdJneE$default(this, null, drawStyle, f, colorFilter, i, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-AZ2fEMs, reason: not valid java name */
    public void mo1472drawImageAZ2fEMs(ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2) {
        this.drawParams.getCanvas().mo1213drawImageRectHPBpro0(imageBitmap, j, j2, j3, j4, m1463configurePaintswdJneE(null, drawStyle, f, colorFilter, i, i2));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ, reason: not valid java name */
    public void mo1480drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.drawParams.getCanvas().drawRoundRect(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j), Offset.m1159getXimpl(j) + Size.m1196getWidthimpl(j2), Offset.m1160getYimpl(j) + Size.m1194getHeightimpl(j2), CornerRadius.m1145getXimpl(j3), CornerRadius.m1146getYimpl(j3), m1464configurePaintswdJneE$default(this, brush, drawStyle, f, colorFilter, i, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA, reason: not valid java name */
    public void mo1481drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i) {
        this.drawParams.getCanvas().drawRoundRect(Offset.m1159getXimpl(j2), Offset.m1160getYimpl(j2), Offset.m1159getXimpl(j2) + Size.m1196getWidthimpl(j3), Offset.m1160getYimpl(j2) + Size.m1194getHeightimpl(j3), CornerRadius.m1145getXimpl(j4), CornerRadius.m1146getYimpl(j4), m1462configurePaint2qPWKa0$default(this, j, drawStyle, f, colorFilter, i, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawCircle-VaOC9Bg, reason: not valid java name */
    public void mo1471drawCircleVaOC9Bg(long j, float f, long j2, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.drawParams.getCanvas().mo1211drawCircle9KIMszo(j2, f, m1462configurePaint2qPWKa0$default(this, j, drawStyle, f2, colorFilter, i, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawArc-yD3GUKo, reason: not valid java name */
    public void mo1470drawArcyD3GUKo(long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.drawParams.getCanvas().drawArc(Offset.m1159getXimpl(j2), Offset.m1160getYimpl(j2), Offset.m1159getXimpl(j2) + Size.m1196getWidthimpl(j3), Offset.m1160getYimpl(j2) + Size.m1194getHeightimpl(j3), f, f2, z, m1462configurePaint2qPWKa0$default(this, j, drawStyle, f3, colorFilter, i, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-LG529CI, reason: not valid java name */
    public void mo1477drawPathLG529CI(Path path, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.drawParams.getCanvas().drawPath(path, m1462configurePaint2qPWKa0$default(this, j, drawStyle, f, colorFilter, i, 0, 32, null));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-GBMwjPU, reason: not valid java name */
    public void mo1476drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.drawParams.getCanvas().drawPath(path, m1464configurePaintswdJneE$default(this, brush, drawStyle, f, colorFilter, i, 0, 32, null));
    }

    private final Paint obtainFillPaint() {
        Paint paint = this.fillPaint;
        if (paint != null) {
            return paint;
        }
        Paint Paint = AndroidPaint_androidKt.Paint();
        Paint.mo1232setStylek9PVt8s(PaintingStyle.Companion.m1381getFillTiuSbCo());
        this.fillPaint = Paint;
        return Paint;
    }

    private final Paint obtainStrokePaint() {
        Paint paint = this.strokePaint;
        if (paint != null) {
            return paint;
        }
        Paint Paint = AndroidPaint_androidKt.Paint();
        Paint.mo1232setStylek9PVt8s(PaintingStyle.Companion.m1382getStrokeTiuSbCo());
        this.strokePaint = Paint;
        return Paint;
    }

    private final Paint selectPaint(DrawStyle drawStyle) {
        if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
            return obtainFillPaint();
        }
        if (drawStyle instanceof Stroke) {
            Paint paintObtainStrokePaint = obtainStrokePaint();
            Stroke stroke = (Stroke) drawStyle;
            if (paintObtainStrokePaint.getStrokeWidth() != stroke.getWidth()) {
                paintObtainStrokePaint.setStrokeWidth(stroke.getWidth());
            }
            if (!StrokeCap.m1412equalsimpl0(paintObtainStrokePaint.mo1225getStrokeCapKaPHkGw(), stroke.m1511getCapKaPHkGw())) {
                paintObtainStrokePaint.mo1230setStrokeCapBeK7IIE(stroke.m1511getCapKaPHkGw());
            }
            if (paintObtainStrokePaint.getStrokeMiterLimit() != stroke.getMiter()) {
                paintObtainStrokePaint.setStrokeMiterLimit(stroke.getMiter());
            }
            if (!StrokeJoin.m1419equalsimpl0(paintObtainStrokePaint.mo1226getStrokeJoinLxFBmk8(), stroke.m1512getJoinLxFBmk8())) {
                paintObtainStrokePaint.mo1231setStrokeJoinWw9F2mQ(stroke.m1512getJoinLxFBmk8());
            }
            paintObtainStrokePaint.getPathEffect();
            stroke.getPathEffect();
            if (!Intrinsics.areEqual((Object) null, (Object) null)) {
                stroke.getPathEffect();
                paintObtainStrokePaint.setPathEffect(null);
            }
            return paintObtainStrokePaint;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: configurePaint-swdJneE$default, reason: not valid java name */
    static /* synthetic */ Paint m1464configurePaintswdJneE$default(CanvasDrawScope canvasDrawScope, Brush brush, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i, int i2, int i3, Object obj) {
        if ((i3 & 32) != 0) {
            i2 = DrawScope.Companion.m1509getDefaultFilterQualityfv9h1I();
        }
        return canvasDrawScope.m1463configurePaintswdJneE(brush, drawStyle, f, colorFilter, i, i2);
    }

    /* JADX INFO: renamed from: configurePaint-swdJneE, reason: not valid java name */
    private final Paint m1463configurePaintswdJneE(Brush brush, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i, int i2) {
        Paint paintSelectPaint = selectPaint(drawStyle);
        if (brush != null) {
            brush.mo1280applyToPq9zytI(mo1483getSizeNHjbRc(), paintSelectPaint, f);
        } else {
            if (paintSelectPaint.getShader() != null) {
                paintSelectPaint.setShader(null);
            }
            long jMo1223getColor0d7_KjU = paintSelectPaint.mo1223getColor0d7_KjU();
            Color.Companion companion = Color.Companion;
            if (!Color.m1296equalsimpl0(jMo1223getColor0d7_KjU, companion.m1305getBlack0d7_KjU())) {
                paintSelectPaint.mo1228setColor8_81llA(companion.m1305getBlack0d7_KjU());
            }
            if (paintSelectPaint.getAlpha() != f) {
                paintSelectPaint.setAlpha(f);
            }
        }
        if (!Intrinsics.areEqual(paintSelectPaint.getColorFilter(), colorFilter)) {
            paintSelectPaint.setColorFilter(colorFilter);
        }
        if (!BlendMode.m1246equalsimpl0(paintSelectPaint.mo1222getBlendMode0nO6VwU(), i)) {
            paintSelectPaint.mo1227setBlendModes9anfk8(i);
        }
        if (!FilterQuality.m1326equalsimpl0(paintSelectPaint.mo1224getFilterQualityfv9h1I(), i2)) {
            paintSelectPaint.mo1229setFilterQualityvDHp3xo(i2);
        }
        return paintSelectPaint;
    }

    /* JADX INFO: renamed from: configurePaint-2qPWKa0$default, reason: not valid java name */
    static /* synthetic */ Paint m1462configurePaint2qPWKa0$default(CanvasDrawScope canvasDrawScope, long j, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i, int i2, int i3, Object obj) {
        return canvasDrawScope.m1461configurePaint2qPWKa0(j, drawStyle, f, colorFilter, i, (i3 & 32) != 0 ? DrawScope.Companion.m1509getDefaultFilterQualityfv9h1I() : i2);
    }

    /* JADX INFO: renamed from: configurePaint-2qPWKa0, reason: not valid java name */
    private final Paint m1461configurePaint2qPWKa0(long j, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i, int i2) {
        Paint paintSelectPaint = selectPaint(drawStyle);
        long jM1469modulate5vOe2sY = m1469modulate5vOe2sY(j, f);
        if (!Color.m1296equalsimpl0(paintSelectPaint.mo1223getColor0d7_KjU(), jM1469modulate5vOe2sY)) {
            paintSelectPaint.mo1228setColor8_81llA(jM1469modulate5vOe2sY);
        }
        if (paintSelectPaint.getShader() != null) {
            paintSelectPaint.setShader(null);
        }
        if (!Intrinsics.areEqual(paintSelectPaint.getColorFilter(), colorFilter)) {
            paintSelectPaint.setColorFilter(colorFilter);
        }
        if (!BlendMode.m1246equalsimpl0(paintSelectPaint.mo1222getBlendMode0nO6VwU(), i)) {
            paintSelectPaint.mo1227setBlendModes9anfk8(i);
        }
        if (!FilterQuality.m1326equalsimpl0(paintSelectPaint.mo1224getFilterQualityfv9h1I(), i2)) {
            paintSelectPaint.mo1229setFilterQualityvDHp3xo(i2);
        }
        return paintSelectPaint;
    }

    /* JADX INFO: renamed from: configureStrokePaint-Q_0CZUI$default, reason: not valid java name */
    static /* synthetic */ Paint m1466configureStrokePaintQ_0CZUI$default(CanvasDrawScope canvasDrawScope, long j, float f, float f2, int i, int i2, PathEffect pathEffect, float f3, ColorFilter colorFilter, int i3, int i4, int i5, Object obj) {
        return canvasDrawScope.m1465configureStrokePaintQ_0CZUI(j, f, f2, i, i2, pathEffect, f3, colorFilter, i3, (i5 & 512) != 0 ? DrawScope.Companion.m1509getDefaultFilterQualityfv9h1I() : i4);
    }

    /* JADX INFO: renamed from: configureStrokePaint-Q_0CZUI, reason: not valid java name */
    private final Paint m1465configureStrokePaintQ_0CZUI(long j, float f, float f2, int i, int i2, PathEffect pathEffect, float f3, ColorFilter colorFilter, int i3, int i4) {
        Paint paintObtainStrokePaint = obtainStrokePaint();
        long jM1469modulate5vOe2sY = m1469modulate5vOe2sY(j, f3);
        if (!Color.m1296equalsimpl0(paintObtainStrokePaint.mo1223getColor0d7_KjU(), jM1469modulate5vOe2sY)) {
            paintObtainStrokePaint.mo1228setColor8_81llA(jM1469modulate5vOe2sY);
        }
        if (paintObtainStrokePaint.getShader() != null) {
            paintObtainStrokePaint.setShader(null);
        }
        if (!Intrinsics.areEqual(paintObtainStrokePaint.getColorFilter(), colorFilter)) {
            paintObtainStrokePaint.setColorFilter(colorFilter);
        }
        if (!BlendMode.m1246equalsimpl0(paintObtainStrokePaint.mo1222getBlendMode0nO6VwU(), i3)) {
            paintObtainStrokePaint.mo1227setBlendModes9anfk8(i3);
        }
        if (paintObtainStrokePaint.getStrokeWidth() != f) {
            paintObtainStrokePaint.setStrokeWidth(f);
        }
        if (paintObtainStrokePaint.getStrokeMiterLimit() != f2) {
            paintObtainStrokePaint.setStrokeMiterLimit(f2);
        }
        if (!StrokeCap.m1412equalsimpl0(paintObtainStrokePaint.mo1225getStrokeCapKaPHkGw(), i)) {
            paintObtainStrokePaint.mo1230setStrokeCapBeK7IIE(i);
        }
        if (!StrokeJoin.m1419equalsimpl0(paintObtainStrokePaint.mo1226getStrokeJoinLxFBmk8(), i2)) {
            paintObtainStrokePaint.mo1231setStrokeJoinWw9F2mQ(i2);
        }
        paintObtainStrokePaint.getPathEffect();
        if (!Intrinsics.areEqual((Object) null, pathEffect)) {
            paintObtainStrokePaint.setPathEffect(pathEffect);
        }
        if (!FilterQuality.m1326equalsimpl0(paintObtainStrokePaint.mo1224getFilterQualityfv9h1I(), i4)) {
            paintObtainStrokePaint.mo1229setFilterQualityvDHp3xo(i4);
        }
        return paintObtainStrokePaint;
    }

    /* JADX INFO: renamed from: configureStrokePaint-ho4zsrM$default, reason: not valid java name */
    static /* synthetic */ Paint m1468configureStrokePaintho4zsrM$default(CanvasDrawScope canvasDrawScope, Brush brush, float f, float f2, int i, int i2, PathEffect pathEffect, float f3, ColorFilter colorFilter, int i3, int i4, int i5, Object obj) {
        return canvasDrawScope.m1467configureStrokePaintho4zsrM(brush, f, f2, i, i2, pathEffect, f3, colorFilter, i3, (i5 & 512) != 0 ? DrawScope.Companion.m1509getDefaultFilterQualityfv9h1I() : i4);
    }

    /* JADX INFO: renamed from: configureStrokePaint-ho4zsrM, reason: not valid java name */
    private final Paint m1467configureStrokePaintho4zsrM(Brush brush, float f, float f2, int i, int i2, PathEffect pathEffect, float f3, ColorFilter colorFilter, int i3, int i4) {
        Paint paintObtainStrokePaint = obtainStrokePaint();
        if (brush != null) {
            brush.mo1280applyToPq9zytI(mo1483getSizeNHjbRc(), paintObtainStrokePaint, f3);
        } else if (paintObtainStrokePaint.getAlpha() != f3) {
            paintObtainStrokePaint.setAlpha(f3);
        }
        if (!Intrinsics.areEqual(paintObtainStrokePaint.getColorFilter(), colorFilter)) {
            paintObtainStrokePaint.setColorFilter(colorFilter);
        }
        if (!BlendMode.m1246equalsimpl0(paintObtainStrokePaint.mo1222getBlendMode0nO6VwU(), i3)) {
            paintObtainStrokePaint.mo1227setBlendModes9anfk8(i3);
        }
        if (paintObtainStrokePaint.getStrokeWidth() != f) {
            paintObtainStrokePaint.setStrokeWidth(f);
        }
        if (paintObtainStrokePaint.getStrokeMiterLimit() != f2) {
            paintObtainStrokePaint.setStrokeMiterLimit(f2);
        }
        if (!StrokeCap.m1412equalsimpl0(paintObtainStrokePaint.mo1225getStrokeCapKaPHkGw(), i)) {
            paintObtainStrokePaint.mo1230setStrokeCapBeK7IIE(i);
        }
        if (!StrokeJoin.m1419equalsimpl0(paintObtainStrokePaint.mo1226getStrokeJoinLxFBmk8(), i2)) {
            paintObtainStrokePaint.mo1231setStrokeJoinWw9F2mQ(i2);
        }
        paintObtainStrokePaint.getPathEffect();
        if (!Intrinsics.areEqual((Object) null, pathEffect)) {
            paintObtainStrokePaint.setPathEffect(pathEffect);
        }
        if (!FilterQuality.m1326equalsimpl0(paintObtainStrokePaint.mo1224getFilterQualityfv9h1I(), i4)) {
            paintObtainStrokePaint.mo1229setFilterQualityvDHp3xo(i4);
        }
        return paintObtainStrokePaint;
    }

    /* JADX INFO: renamed from: modulate-5vOe2sY, reason: not valid java name */
    private final long m1469modulate5vOe2sY(long j, float f) {
        return f == 1.0f ? j : Color.m1294copywmQWz5c$default(j, Color.m1297getAlphaimpl(j) * f, 0.0f, 0.0f, 0.0f, 14, null);
    }

    /* JADX INFO: compiled from: CanvasDrawScope.kt */
    public static final class DrawParams {
        private Canvas canvas;
        private Density density;
        private LayoutDirection layoutDirection;
        private long size;

        public /* synthetic */ DrawParams(Density density, LayoutDirection layoutDirection, Canvas canvas, long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(density, layoutDirection, canvas, j);
        }

        public final Density component1() {
            return this.density;
        }

        public final LayoutDirection component2() {
            return this.layoutDirection;
        }

        public final Canvas component3() {
            return this.canvas;
        }

        /* JADX INFO: renamed from: component4-NH-jbRc, reason: not valid java name */
        public final long m1484component4NHjbRc() {
            return this.size;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrawParams)) {
                return false;
            }
            DrawParams drawParams = (DrawParams) obj;
            return Intrinsics.areEqual(this.density, drawParams.density) && this.layoutDirection == drawParams.layoutDirection && Intrinsics.areEqual(this.canvas, drawParams.canvas) && Size.m1193equalsimpl0(this.size, drawParams.size);
        }

        public int hashCode() {
            return (((((this.density.hashCode() * 31) + this.layoutDirection.hashCode()) * 31) + this.canvas.hashCode()) * 31) + Size.m1197hashCodeimpl(this.size);
        }

        public String toString() {
            return "DrawParams(density=" + this.density + ", layoutDirection=" + this.layoutDirection + ", canvas=" + this.canvas + ", size=" + ((Object) Size.m1199toStringimpl(this.size)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        private DrawParams(Density density, LayoutDirection layoutDirection, Canvas canvas, long j) {
            this.density = density;
            this.layoutDirection = layoutDirection;
            this.canvas = canvas;
            this.size = j;
        }

        public /* synthetic */ DrawParams(Density density, LayoutDirection layoutDirection, Canvas canvas, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? DrawContextKt.getDefaultDensity() : density, (i & 2) != 0 ? LayoutDirection.Ltr : layoutDirection, (i & 4) != 0 ? new EmptyCanvas() : canvas, (i & 8) != 0 ? Size.Companion.m1202getZeroNHjbRc() : j, null);
        }

        public final Density getDensity() {
            return this.density;
        }

        public final void setDensity(Density density) {
            this.density = density;
        }

        public final LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        public final void setLayoutDirection(LayoutDirection layoutDirection) {
            this.layoutDirection = layoutDirection;
        }

        public final Canvas getCanvas() {
            return this.canvas;
        }

        public final void setCanvas(Canvas canvas) {
            this.canvas = canvas;
        }

        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public final long m1485getSizeNHjbRc() {
            return this.size;
        }

        /* JADX INFO: renamed from: setSize-uvyYCjk, reason: not valid java name */
        public final void m1486setSizeuvyYCjk(long j) {
            this.size = j;
        }
    }
}
