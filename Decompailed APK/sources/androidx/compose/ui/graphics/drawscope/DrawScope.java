package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: compiled from: DrawScope.kt */
/* JADX INFO: loaded from: classes.dex */
public interface DrawScope extends Density {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: drawArc-yD3GUKo */
    void mo1470drawArcyD3GUKo(long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* JADX INFO: renamed from: drawCircle-VaOC9Bg */
    void mo1471drawCircleVaOC9Bg(long j, float f, long j2, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* JADX INFO: renamed from: drawImage-AZ2fEMs */
    void mo1472drawImageAZ2fEMs(ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2);

    /* JADX INFO: renamed from: drawImage-gbVJVH8 */
    void mo1473drawImagegbVJVH8(ImageBitmap imageBitmap, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* JADX INFO: renamed from: drawLine-1RTmtNc */
    void mo1474drawLine1RTmtNc(Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2);

    /* JADX INFO: renamed from: drawLine-NGM6Ib0 */
    void mo1475drawLineNGM6Ib0(long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2);

    /* JADX INFO: renamed from: drawPath-GBMwjPU */
    void mo1476drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* JADX INFO: renamed from: drawPath-LG529CI */
    void mo1477drawPathLG529CI(Path path, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* JADX INFO: renamed from: drawRect-AsUm42w */
    void mo1478drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* JADX INFO: renamed from: drawRect-n-J9OG0 */
    void mo1479drawRectnJ9OG0(long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ */
    void mo1480drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA */
    void mo1481drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i);

    /* JADX INFO: renamed from: getCenter-F1C5BW0 */
    long mo1482getCenterF1C5BW0();

    DrawContext getDrawContext();

    LayoutDirection getLayoutDirection();

    /* JADX INFO: renamed from: getSize-NH-jbRc */
    long mo1483getSizeNHjbRc();

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.drawscope.DrawScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: DrawScope.kt */
    public abstract /* synthetic */ class CC {
        static {
            Companion companion = DrawScope.Companion;
        }

        /* JADX INFO: renamed from: drawLine-1RTmtNc$default, reason: not valid java name */
        public static /* synthetic */ void m1502drawLine1RTmtNc$default(DrawScope drawScope, Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-1RTmtNc");
            }
            drawScope.mo1474drawLine1RTmtNc(brush, j, j2, (i3 & 8) != 0 ? 0.0f : f, (i3 & 16) != 0 ? Stroke.Companion.m1513getDefaultCapKaPHkGw() : i, (i3 & 32) != 0 ? null : pathEffect, (i3 & 64) != 0 ? 1.0f : f2, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i2);
        }

        /* JADX INFO: renamed from: drawLine-NGM6Ib0$default, reason: not valid java name */
        public static /* synthetic */ void m1503drawLineNGM6Ib0$default(DrawScope drawScope, long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-NGM6Ib0");
            }
            drawScope.mo1475drawLineNGM6Ib0(j, j2, j3, (i3 & 8) != 0 ? 0.0f : f, (i3 & 16) != 0 ? Stroke.Companion.m1513getDefaultCapKaPHkGw() : i, (i3 & 32) != 0 ? null : pathEffect, (i3 & 64) != 0 ? 1.0f : f2, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i2);
        }

        /* JADX INFO: renamed from: drawRect-AsUm42w$default, reason: not valid java name */
        public static /* synthetic */ void m1505drawRectAsUm42w$default(DrawScope drawScope, Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-AsUm42w");
            }
            long jM1171getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.Companion.m1171getZeroF1C5BW0() : j;
            drawScope.mo1478drawRectAsUm42w(brush, jM1171getZeroF1C5BW0, (i2 & 4) != 0 ? m1497$private$offsetSizePENXr5M(drawScope, drawScope.mo1483getSizeNHjbRc(), jM1171getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawRect-n-J9OG0$default, reason: not valid java name */
        public static /* synthetic */ void m1506drawRectnJ9OG0$default(DrawScope drawScope, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-n-J9OG0");
            }
            long jM1171getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.Companion.m1171getZeroF1C5BW0() : j2;
            drawScope.mo1479drawRectnJ9OG0(j, jM1171getZeroF1C5BW0, (i2 & 4) != 0 ? m1497$private$offsetSizePENXr5M(drawScope, drawScope.mo1483getSizeNHjbRc(), jM1171getZeroF1C5BW0) : j3, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawImage-gbVJVH8$default, reason: not valid java name */
        public static /* synthetic */ void m1501drawImagegbVJVH8$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-gbVJVH8");
            }
            drawScope.mo1473drawImagegbVJVH8(imageBitmap, (i2 & 2) != 0 ? Offset.Companion.m1171getZeroF1C5BW0() : j, (i2 & 4) != 0 ? 1.0f : f, (i2 & 8) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 16) != 0 ? null : colorFilter, (i2 & 32) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawImage-AZ2fEMs$default, reason: not valid java name */
        public static /* synthetic */ void m1500drawImageAZ2fEMs$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-AZ2fEMs");
            }
            long jM2464getZeronOccac = (i3 & 2) != 0 ? IntOffset.Companion.m2464getZeronOccac() : j;
            long jIntSize = (i3 & 4) != 0 ? IntSizeKt.IntSize(imageBitmap.getWidth(), imageBitmap.getHeight()) : j2;
            drawScope.mo1472drawImageAZ2fEMs(imageBitmap, jM2464getZeronOccac, jIntSize, (i3 & 8) != 0 ? IntOffset.Companion.m2464getZeronOccac() : j3, (i3 & 16) != 0 ? jIntSize : j4, (i3 & 32) != 0 ? 1.0f : f, (i3 & 64) != 0 ? Fill.INSTANCE : drawStyle, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i, (i3 & 512) != 0 ? DrawScope.Companion.m1509getDefaultFilterQualityfv9h1I() : i2);
        }

        /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ$default, reason: not valid java name */
        public static /* synthetic */ void m1507drawRoundRectZuiqVtQ$default(DrawScope drawScope, Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRoundRect-ZuiqVtQ");
            }
            long jM1171getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.Companion.m1171getZeroF1C5BW0() : j;
            drawScope.mo1480drawRoundRectZuiqVtQ(brush, jM1171getZeroF1C5BW0, (i2 & 4) != 0 ? m1497$private$offsetSizePENXr5M(drawScope, drawScope.mo1483getSizeNHjbRc(), jM1171getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? CornerRadius.Companion.m1149getZerokKHJgLs() : j3, (i2 & 16) != 0 ? 1.0f : f, (i2 & 32) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 64) != 0 ? null : colorFilter, (i2 & 128) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawCircle-VaOC9Bg$default, reason: not valid java name */
        public static /* synthetic */ void m1499drawCircleVaOC9Bg$default(DrawScope drawScope, long j, float f, long j2, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawCircle-VaOC9Bg");
            }
            drawScope.mo1471drawCircleVaOC9Bg(j, (i2 & 2) != 0 ? Size.m1195getMinDimensionimpl(drawScope.mo1483getSizeNHjbRc()) / 2.0f : f, (i2 & 4) != 0 ? drawScope.mo1482getCenterF1C5BW0() : j2, (i2 & 8) != 0 ? 1.0f : f2, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawArc-yD3GUKo$default, reason: not valid java name */
        public static /* synthetic */ void m1498drawArcyD3GUKo$default(DrawScope drawScope, long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawArc-yD3GUKo");
            }
            long jM1171getZeroF1C5BW0 = (i2 & 16) != 0 ? Offset.Companion.m1171getZeroF1C5BW0() : j2;
            drawScope.mo1470drawArcyD3GUKo(j, f, f2, z, jM1171getZeroF1C5BW0, (i2 & 32) != 0 ? m1497$private$offsetSizePENXr5M(drawScope, drawScope.mo1483getSizeNHjbRc(), jM1171getZeroF1C5BW0) : j3, (i2 & 64) != 0 ? 1.0f : f3, (i2 & 128) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 256) != 0 ? null : colorFilter, (i2 & 512) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : i);
        }

        /* JADX INFO: renamed from: drawPath-GBMwjPU$default, reason: not valid java name */
        public static /* synthetic */ void m1504drawPathGBMwjPU$default(DrawScope drawScope, Path path, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPath-GBMwjPU");
            }
            if ((i2 & 4) != 0) {
                f = 1.0f;
            }
            float f2 = f;
            if ((i2 & 8) != 0) {
                drawStyle = Fill.INSTANCE;
            }
            DrawStyle drawStyle2 = drawStyle;
            if ((i2 & 16) != 0) {
                colorFilter = null;
            }
            ColorFilter colorFilter2 = colorFilter;
            if ((i2 & 32) != 0) {
                i = DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU();
            }
            drawScope.mo1476drawPathGBMwjPU(path, brush, f2, drawStyle2, colorFilter2, i);
        }

        /* JADX INFO: renamed from: $private$offsetSize-PENXr5M, reason: not valid java name */
        public static long m1497$private$offsetSizePENXr5M(DrawScope drawScope, long j, long j2) {
            return SizeKt.Size(Size.m1196getWidthimpl(j) - Offset.m1159getXimpl(j2), Size.m1194getHeightimpl(j) - Offset.m1160getYimpl(j2));
        }
    }

    /* JADX INFO: compiled from: DrawScope.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final int DefaultBlendMode = BlendMode.Companion.m1276getSrcOver0nO6VwU();
        private static final int DefaultFilterQuality = FilterQuality.Companion.m1329getLowfv9h1I();

        private Companion() {
        }

        /* JADX INFO: renamed from: getDefaultBlendMode-0nO6VwU, reason: not valid java name */
        public final int m1508getDefaultBlendMode0nO6VwU() {
            return DefaultBlendMode;
        }

        /* JADX INFO: renamed from: getDefaultFilterQuality-f-v9h1I, reason: not valid java name */
        public final int m1509getDefaultFilterQualityfv9h1I() {
            return DefaultFilterQuality;
        }
    }
}
