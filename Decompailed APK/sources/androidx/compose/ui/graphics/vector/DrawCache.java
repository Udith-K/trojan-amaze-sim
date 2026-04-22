package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: DrawCache.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DrawCache {
    private Canvas cachedCanvas;
    private ImageBitmap mCachedImage;
    private Density scopeDensity;
    private LayoutDirection layoutDirection = LayoutDirection.Ltr;
    private long size = IntSize.Companion.m2480getZeroYbymL2g();
    private int config = ImageBitmapConfig.Companion.m1348getArgb8888_sVssgQ();
    private final CanvasDrawScope cacheScope = new CanvasDrawScope();

    public final ImageBitmap getMCachedImage() {
        return this.mCachedImage;
    }

    /* JADX INFO: renamed from: drawCachedImage-FqjB98A, reason: not valid java name */
    public final void m1550drawCachedImageFqjB98A(int i, long j, Density density, LayoutDirection layoutDirection, Function1 function1) {
        this.scopeDensity = density;
        this.layoutDirection = layoutDirection;
        ImageBitmap imageBitmapM1353ImageBitmapx__hDU$default = this.mCachedImage;
        Canvas Canvas = this.cachedCanvas;
        if (imageBitmapM1353ImageBitmapx__hDU$default == null || Canvas == null || IntSize.m2476getWidthimpl(j) > imageBitmapM1353ImageBitmapx__hDU$default.getWidth() || IntSize.m2475getHeightimpl(j) > imageBitmapM1353ImageBitmapx__hDU$default.getHeight() || !ImageBitmapConfig.m1343equalsimpl0(this.config, i)) {
            imageBitmapM1353ImageBitmapx__hDU$default = ImageBitmapKt.m1353ImageBitmapx__hDU$default(IntSize.m2476getWidthimpl(j), IntSize.m2475getHeightimpl(j), i, false, null, 24, null);
            Canvas = CanvasKt.Canvas(imageBitmapM1353ImageBitmapx__hDU$default);
            this.mCachedImage = imageBitmapM1353ImageBitmapx__hDU$default;
            this.cachedCanvas = Canvas;
            this.config = i;
        }
        this.size = j;
        CanvasDrawScope canvasDrawScope = this.cacheScope;
        long jM2483toSizeozmzZPI = IntSizeKt.m2483toSizeozmzZPI(j);
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope.getDrawParams();
        Density densityComponent1 = drawParams.component1();
        LayoutDirection layoutDirectionComponent2 = drawParams.component2();
        Canvas canvasComponent3 = drawParams.component3();
        long jM1484component4NHjbRc = drawParams.m1484component4NHjbRc();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope.getDrawParams();
        drawParams2.setDensity(density);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(Canvas);
        drawParams2.m1486setSizeuvyYCjk(jM2483toSizeozmzZPI);
        Canvas.save();
        clear(canvasDrawScope);
        function1.invoke(canvasDrawScope);
        Canvas.restore();
        CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope.getDrawParams();
        drawParams3.setDensity(densityComponent1);
        drawParams3.setLayoutDirection(layoutDirectionComponent2);
        drawParams3.setCanvas(canvasComponent3);
        drawParams3.m1486setSizeuvyYCjk(jM1484component4NHjbRc);
        imageBitmapM1353ImageBitmapx__hDU$default.prepareToDraw();
    }

    public final void drawInto(DrawScope drawScope, float f, ColorFilter colorFilter) {
        ImageBitmap imageBitmap = this.mCachedImage;
        if (!(imageBitmap != null)) {
            InlineClassHelperKt.throwIllegalStateException("drawCachedImage must be invoked first before attempting to draw the result into another destination");
        }
        DrawScope.CC.m1500drawImageAZ2fEMs$default(drawScope, imageBitmap, 0L, this.size, 0L, 0L, f, null, colorFilter, 0, 0, 858, null);
    }

    private final void clear(DrawScope drawScope) {
        DrawScope.CC.m1506drawRectnJ9OG0$default(drawScope, Color.Companion.m1305getBlack0d7_KjU(), 0L, 0L, 0.0f, null, null, BlendMode.Companion.m1249getClear0nO6VwU(), 62, null);
    }
}
