package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.RectF;
import android.os.Build;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawContextKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidGraphicsLayer.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class GraphicsLayer {
    public static final Companion Companion = new Companion(null);
    private static final LayerSnapshotImpl SnapshotImpl;
    private Outline androidOutline;
    private final ChildLayerDependenciesTracker childDependenciesTracker;
    private boolean clip;
    private final GraphicsLayerImpl impl;
    private androidx.compose.ui.graphics.Outline internalOutline;
    private boolean isReleased;
    private Path outlinePath;
    private int parentLayerUsages;
    private RectF pathBounds;
    private long pivotOffset;
    private Path roundRectClipPath;
    private float roundRectCornerRadius;
    private long roundRectOutlineSize;
    private long roundRectOutlineTopLeft;
    private long size;
    private Paint softwareLayerPaint;
    private long topLeft;
    private boolean usePathForClip;
    private Density density = DrawContextKt.getDefaultDensity();
    private LayoutDirection layoutDirection = LayoutDirection.Ltr;
    private Function1 drawBlock = new Function1() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$drawBlock$1
        public final void invoke(DrawScope drawScope) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DrawScope) obj);
            return Unit.INSTANCE;
        }
    };
    private final Function1 clipDrawBlock = new Function1() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$clipDrawBlock$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DrawScope) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(DrawScope drawScope) {
            Path path = this.this$0.outlinePath;
            if (!this.this$0.usePathForClip || !this.this$0.getClip() || path == null) {
                this.this$0.drawBlock.invoke(drawScope);
                return;
            }
            Function1 function1 = this.this$0.drawBlock;
            int iM1289getIntersectrtfAjoo = ClipOp.Companion.m1289getIntersectrtfAjoo();
            DrawContext drawContext = drawScope.getDrawContext();
            long jMo1487getSizeNHjbRc = drawContext.mo1487getSizeNHjbRc();
            drawContext.getCanvas().save();
            try {
                drawContext.getTransform().mo1489clipPathmtrdDE(path, iM1289getIntersectrtfAjoo);
                function1.invoke(drawScope);
            } finally {
                drawContext.getCanvas().restore();
                drawContext.mo1488setSizeuvyYCjk(jMo1487getSizeNHjbRc);
            }
        }
    };
    private boolean outlineDirty = true;

    public GraphicsLayer(GraphicsLayerImpl graphicsLayerImpl, LayerManager layerManager) {
        this.impl = graphicsLayerImpl;
        Offset.Companion companion = Offset.Companion;
        this.roundRectOutlineTopLeft = companion.m1171getZeroF1C5BW0();
        this.roundRectOutlineSize = Size.Companion.m1201getUnspecifiedNHjbRc();
        this.childDependenciesTracker = new ChildLayerDependenciesTracker();
        graphicsLayerImpl.setClip(false);
        this.topLeft = IntOffset.Companion.m2464getZeronOccac();
        this.size = IntSize.Companion.m2480getZeroYbymL2g();
        this.pivotOffset = companion.m1170getUnspecifiedF1C5BW0();
    }

    public final boolean isReleased() {
        return this.isReleased;
    }

    /* JADX INFO: renamed from: getCompositingStrategy-ke2Ky5w, reason: not valid java name */
    public final int m1522getCompositingStrategyke2Ky5w() {
        return this.impl.mo1536getCompositingStrategyke2Ky5w();
    }

    /* JADX INFO: renamed from: setCompositingStrategy-Wpw9cng, reason: not valid java name */
    public final void m1528setCompositingStrategyWpw9cng(int i) {
        if (CompositingStrategy.m1515equalsimpl0(this.impl.mo1536getCompositingStrategyke2Ky5w(), i)) {
            return;
        }
        this.impl.mo1539setCompositingStrategyWpw9cng(i);
    }

    /* JADX INFO: renamed from: getTopLeft-nOcc-ac, reason: not valid java name */
    public final long m1525getTopLeftnOccac() {
        return this.topLeft;
    }

    /* JADX INFO: renamed from: setTopLeft--gyyYBs, reason: not valid java name */
    public final void m1533setTopLeftgyyYBs(long j) {
        if (IntOffset.m2456equalsimpl0(this.topLeft, j)) {
            return;
        }
        this.topLeft = j;
        m1519setPositionVbeCjmY(j, this.size);
    }

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name */
    public final long m1524getSizeYbymL2g() {
        return this.size;
    }

    /* JADX INFO: renamed from: setSize-ozmzZPI, reason: not valid java name */
    private final void m1520setSizeozmzZPI(long j) {
        if (IntSize.m2474equalsimpl0(this.size, j)) {
            return;
        }
        this.size = j;
        m1519setPositionVbeCjmY(this.topLeft, j);
        if (this.roundRectOutlineSize == 9205357640488583168L) {
            this.outlineDirty = true;
            configureOutlineAndClip();
        }
    }

    public final float getAlpha() {
        return this.impl.getAlpha();
    }

    public final void setAlpha(float f) {
        if (this.impl.getAlpha() == f) {
            return;
        }
        this.impl.setAlpha(f);
    }

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: not valid java name */
    public final int m1521getBlendMode0nO6VwU() {
        return this.impl.mo1535getBlendMode0nO6VwU();
    }

    public final ColorFilter getColorFilter() {
        return this.impl.getColorFilter();
    }

    /* JADX INFO: renamed from: getPivotOffset-F1C5BW0, reason: not valid java name */
    public final long m1523getPivotOffsetF1C5BW0() {
        return this.pivotOffset;
    }

    /* JADX INFO: renamed from: setPivotOffset-k-4lQ0M, reason: not valid java name */
    public final void m1529setPivotOffsetk4lQ0M(long j) {
        if (Offset.m1156equalsimpl0(this.pivotOffset, j)) {
            return;
        }
        this.pivotOffset = j;
        this.impl.mo1541setPivotOffsetk4lQ0M(j);
    }

    public final float getScaleX() {
        return this.impl.getScaleX();
    }

    public final void setScaleX(float f) {
        if (this.impl.getScaleX() == f) {
            return;
        }
        this.impl.setScaleX(f);
    }

    public final float getScaleY() {
        return this.impl.getScaleY();
    }

    public final void setScaleY(float f) {
        if (this.impl.getScaleY() == f) {
            return;
        }
        this.impl.setScaleY(f);
    }

    public final float getTranslationX() {
        return this.impl.getTranslationX();
    }

    public final void setTranslationX(float f) {
        if (this.impl.getTranslationX() == f) {
            return;
        }
        this.impl.setTranslationX(f);
    }

    public final float getTranslationY() {
        return this.impl.getTranslationY();
    }

    public final void setTranslationY(float f) {
        if (this.impl.getTranslationY() == f) {
            return;
        }
        this.impl.setTranslationY(f);
    }

    public final float getShadowElevation() {
        return this.impl.getShadowElevation();
    }

    public final void setShadowElevation(float f) {
        if (this.impl.getShadowElevation() == f) {
            return;
        }
        this.impl.setShadowElevation(f);
        this.outlineDirty = true;
        configureOutlineAndClip();
    }

    public final float getRotationX() {
        return this.impl.getRotationX();
    }

    public final void setRotationX(float f) {
        if (this.impl.getRotationX() == f) {
            return;
        }
        this.impl.setRotationX(f);
    }

    public final float getRotationY() {
        return this.impl.getRotationY();
    }

    public final void setRotationY(float f) {
        if (this.impl.getRotationY() == f) {
            return;
        }
        this.impl.setRotationY(f);
    }

    public final float getRotationZ() {
        return this.impl.getRotationZ();
    }

    public final void setRotationZ(float f) {
        if (this.impl.getRotationZ() == f) {
            return;
        }
        this.impl.setRotationZ(f);
    }

    public final void setCameraDistance(float f) {
        if (this.impl.getCameraDistance() == f) {
            return;
        }
        this.impl.setCameraDistance(f);
    }

    public final boolean getClip() {
        return this.clip;
    }

    public final void setClip(boolean z) {
        if (this.clip != z) {
            this.clip = z;
            this.outlineDirty = true;
            configureOutlineAndClip();
        }
    }

    public final void setRenderEffect(RenderEffect renderEffect) {
        this.impl.getRenderEffect();
        if (Intrinsics.areEqual((Object) null, renderEffect)) {
            return;
        }
        this.impl.setRenderEffect(renderEffect);
    }

    /* JADX INFO: renamed from: setPosition-VbeCjmY, reason: not valid java name */
    private final void m1519setPositionVbeCjmY(long j, long j2) {
        this.impl.mo1542setPositionH0pRuoY(IntOffset.m2457getXimpl(j), IntOffset.m2458getYimpl(j), j2);
    }

    /* JADX INFO: renamed from: record-mL-hObY, reason: not valid java name */
    public final void m1526recordmLhObY(Density density, LayoutDirection layoutDirection, long j, Function1 function1) {
        m1520setSizeozmzZPI(j);
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.drawBlock = function1;
        this.impl.setInvalidated(true);
        recordInternal();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void recordInternal() {
        /*
            r15 = this;
            androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker r0 = r15.childDependenciesTracker
            androidx.compose.ui.graphics.layer.GraphicsLayer r1 = androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$getDependency$p(r0)
            androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$setOldDependency$p(r0, r1)
            androidx.collection.MutableScatterSet r1 = androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$getDependenciesSet$p(r0)
            if (r1 == 0) goto L28
            boolean r2 = r1.isNotEmpty()
            if (r2 == 0) goto L28
            androidx.collection.MutableScatterSet r2 = androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$getOldDependenciesSet$p(r0)
            if (r2 != 0) goto L22
            androidx.collection.MutableScatterSet r2 = androidx.collection.ScatterSetKt.mutableScatterSetOf()
            androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$setOldDependenciesSet$p(r0, r2)
        L22:
            r2.addAll(r1)
            r1.clear()
        L28:
            r1 = 1
            androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$setTrackingInProgress$p(r0, r1)
            androidx.compose.ui.graphics.layer.GraphicsLayerImpl r1 = r15.impl
            androidx.compose.ui.unit.Density r2 = r15.density
            androidx.compose.ui.unit.LayoutDirection r3 = r15.layoutDirection
            kotlin.jvm.functions.Function1 r4 = r15.clipDrawBlock
            r1.record(r2, r3, r15, r4)
            r1 = 0
            androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$setTrackingInProgress$p(r0, r1)
            androidx.compose.ui.graphics.layer.GraphicsLayer r2 = androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$getOldDependency$p(r0)
            if (r2 == 0) goto L44
            r2.onRemovedFromParentLayer()
        L44:
            androidx.collection.MutableScatterSet r0 = androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$getOldDependenciesSet$p(r0)
            if (r0 == 0) goto L97
            boolean r2 = r0.isNotEmpty()
            if (r2 == 0) goto L97
            java.lang.Object[] r2 = r0.elements
            long[] r3 = r0.metadata
            int r4 = r3.length
            int r4 = r4 + (-2)
            if (r4 < 0) goto L94
            r5 = r1
        L5a:
            r6 = r3[r5]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L8f
            int r8 = r5 - r4
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r1
        L74:
            if (r10 >= r8) goto L8d
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L89
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r2[r11]
            androidx.compose.ui.graphics.layer.GraphicsLayer r11 = (androidx.compose.ui.graphics.layer.GraphicsLayer) r11
            r11.onRemovedFromParentLayer()
        L89:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L74
        L8d:
            if (r8 != r9) goto L94
        L8f:
            if (r5 == r4) goto L94
            int r5 = r5 + 1
            goto L5a
        L94:
            r0.clear()
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.GraphicsLayer.recordInternal():void");
    }

    private final void addSubLayer(GraphicsLayer graphicsLayer) {
        if (this.childDependenciesTracker.onDependencyAdded(graphicsLayer)) {
            graphicsLayer.onAddedToParentLayer();
        }
    }

    private final void transformCanvas(Canvas canvas) {
        float fM2457getXimpl = IntOffset.m2457getXimpl(this.topLeft);
        float fM2458getYimpl = IntOffset.m2458getYimpl(this.topLeft);
        float fM2457getXimpl2 = IntOffset.m2457getXimpl(this.topLeft) + IntSize.m2476getWidthimpl(this.size);
        float fM2458getYimpl2 = IntOffset.m2458getYimpl(this.topLeft) + IntSize.m2475getHeightimpl(this.size);
        float alpha = getAlpha();
        ColorFilter colorFilter = getColorFilter();
        int iM1521getBlendMode0nO6VwU = m1521getBlendMode0nO6VwU();
        if (alpha < 1.0f || !BlendMode.m1246equalsimpl0(iM1521getBlendMode0nO6VwU, BlendMode.Companion.m1276getSrcOver0nO6VwU()) || colorFilter != null || CompositingStrategy.m1515equalsimpl0(m1522getCompositingStrategyke2Ky5w(), CompositingStrategy.Companion.m1518getOffscreenke2Ky5w())) {
            Paint Paint = this.softwareLayerPaint;
            if (Paint == null) {
                Paint = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = Paint;
            }
            Paint.setAlpha(alpha);
            Paint.mo1227setBlendModes9anfk8(iM1521getBlendMode0nO6VwU);
            Paint.setColorFilter(colorFilter);
            canvas.saveLayer(fM2457getXimpl, fM2458getYimpl, fM2457getXimpl2, fM2458getYimpl2, Paint.asFrameworkPaint());
        } else {
            canvas.save();
        }
        canvas.translate(fM2457getXimpl, fM2458getYimpl);
        canvas.concat(this.impl.calculateMatrix());
    }

    private final void recreateDisplayListIfNeeded() {
        if (this.impl.getHasDisplayList()) {
            return;
        }
        try {
            recordInternal();
        } catch (Throwable unused) {
        }
    }

    public final void draw$ui_graphics_release(androidx.compose.ui.graphics.Canvas canvas, GraphicsLayer graphicsLayer) {
        if (this.isReleased) {
            return;
        }
        configureOutlineAndClip();
        recreateDisplayListIfNeeded();
        boolean z = getShadowElevation() > 0.0f;
        if (z) {
            canvas.enableZ();
        }
        Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        boolean zIsHardwareAccelerated = nativeCanvas.isHardwareAccelerated();
        if (!zIsHardwareAccelerated) {
            nativeCanvas.save();
            transformCanvas(nativeCanvas);
        }
        boolean z2 = !zIsHardwareAccelerated && this.clip;
        if (z2) {
            canvas.save();
            androidx.compose.ui.graphics.Outline outline = getOutline();
            if (outline instanceof Outline.Rectangle) {
                Canvas.CC.m1285clipRectmtrdDE$default(canvas, outline.getBounds(), 0, 2, null);
            } else if (outline instanceof Outline.Rounded) {
                Path Path = this.roundRectClipPath;
                if (Path != null) {
                    Path.rewind();
                } else {
                    Path = AndroidPath_androidKt.Path();
                    this.roundRectClipPath = Path;
                }
                Path.CC.addRoundRect$default(Path, ((Outline.Rounded) outline).getRoundRect(), null, 2, null);
                Canvas.CC.m1283clipPathmtrdDE$default(canvas, Path, 0, 2, null);
            } else if (outline instanceof Outline.Generic) {
                Canvas.CC.m1283clipPathmtrdDE$default(canvas, ((Outline.Generic) outline).getPath(), 0, 2, null);
            }
        }
        if (graphicsLayer != null) {
            graphicsLayer.addSubLayer(this);
        }
        this.impl.draw(canvas);
        if (z2) {
            canvas.restore();
        }
        if (z) {
            canvas.disableZ();
        }
        if (zIsHardwareAccelerated) {
            return;
        }
        nativeCanvas.restore();
    }

    private final void onAddedToParentLayer() {
        this.parentLayerUsages++;
    }

    private final void onRemovedFromParentLayer() {
        this.parentLayerUsages--;
        discardContentIfReleasedAndHaveNoParentLayerUsages();
    }

    private final RectF obtainPathBounds() {
        RectF rectF = this.pathBounds;
        if (rectF != null) {
            return rectF;
        }
        RectF rectF2 = new RectF();
        this.pathBounds = rectF2;
        return rectF2;
    }

    private final void configureOutlineAndClip() {
        if (this.outlineDirty) {
            android.graphics.Outline outline = null;
            if (this.clip || getShadowElevation() > 0.0f) {
                Path path = this.outlinePath;
                if (path != null) {
                    RectF rectFObtainPathBounds = obtainPathBounds();
                    if (!(path instanceof AndroidPath)) {
                        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
                    }
                    ((AndroidPath) path).getInternalPath().computeBounds(rectFObtainPathBounds, false);
                    android.graphics.Outline outlineUpdatePathOutline = updatePathOutline(path);
                    if (outlineUpdatePathOutline != null) {
                        outlineUpdatePathOutline.setAlpha(getAlpha());
                        outline = outlineUpdatePathOutline;
                    }
                    this.impl.mo1540setOutlineO0kMr_c(outline, IntSizeKt.IntSize(Math.round(rectFObtainPathBounds.width()), Math.round(rectFObtainPathBounds.height())));
                    if (this.usePathForClip && this.clip) {
                        this.impl.setClip(false);
                        this.impl.discardDisplayList();
                    } else {
                        this.impl.setClip(this.clip);
                    }
                } else {
                    this.impl.setClip(this.clip);
                    Size.Companion.m1202getZeroNHjbRc();
                    android.graphics.Outline outlineObtainAndroidOutline = obtainAndroidOutline();
                    long jM2483toSizeozmzZPI = IntSizeKt.m2483toSizeozmzZPI(this.size);
                    long j = this.roundRectOutlineTopLeft;
                    long j2 = this.roundRectOutlineSize;
                    long j3 = j2 == 9205357640488583168L ? jM2483toSizeozmzZPI : j2;
                    outlineObtainAndroidOutline.setRoundRect(Math.round(Offset.m1159getXimpl(j)), Math.round(Offset.m1160getYimpl(j)), Math.round(Offset.m1159getXimpl(j) + Size.m1196getWidthimpl(j3)), Math.round(Offset.m1160getYimpl(j) + Size.m1194getHeightimpl(j3)), this.roundRectCornerRadius);
                    outlineObtainAndroidOutline.setAlpha(getAlpha());
                    this.impl.mo1540setOutlineO0kMr_c(outlineObtainAndroidOutline, IntSizeKt.m2482roundToIntSizeuvyYCjk(j3));
                }
            } else {
                this.impl.setClip(false);
                this.impl.mo1540setOutlineO0kMr_c(null, IntSize.Companion.m2480getZeroYbymL2g());
            }
        }
        this.outlineDirty = false;
    }

    private final android.graphics.Outline updatePathOutline(Path path) {
        android.graphics.Outline outline;
        int i = Build.VERSION.SDK_INT;
        if (i > 28 || path.isConvex()) {
            android.graphics.Outline outlineObtainAndroidOutline = obtainAndroidOutline();
            if (i < 30) {
                if (!(path instanceof AndroidPath)) {
                    throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
                }
                outlineObtainAndroidOutline.setConvexPath(((AndroidPath) path).getInternalPath());
            } else {
                OutlineVerificationHelper.INSTANCE.setPath(outlineObtainAndroidOutline, path);
            }
            this.usePathForClip = !outlineObtainAndroidOutline.canClip();
            outline = outlineObtainAndroidOutline;
        } else {
            android.graphics.Outline outline2 = this.androidOutline;
            if (outline2 != null) {
                outline2.setEmpty();
            }
            this.usePathForClip = true;
            this.impl.setInvalidated(true);
            outline = null;
        }
        this.outlinePath = path;
        return outline;
    }

    private final android.graphics.Outline obtainAndroidOutline() {
        android.graphics.Outline outline = this.androidOutline;
        if (outline != null) {
            return outline;
        }
        android.graphics.Outline outline2 = new android.graphics.Outline();
        this.androidOutline = outline2;
        return outline2;
    }

    public final void release$ui_graphics_release() {
        if (this.isReleased) {
            return;
        }
        this.isReleased = true;
        discardContentIfReleasedAndHaveNoParentLayerUsages();
    }

    private final void discardContentIfReleasedAndHaveNoParentLayerUsages() {
        if (this.isReleased && this.parentLayerUsages == 0) {
            discardDisplayList$ui_graphics_release();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void discardDisplayList$ui_graphics_release() {
        /*
            r15 = this;
            androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker r0 = r15.childDependenciesTracker
            androidx.compose.ui.graphics.layer.GraphicsLayer r1 = androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$getDependency$p(r0)
            if (r1 == 0) goto Lf
            r1.onRemovedFromParentLayer()
            r1 = 0
            androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$setDependency$p(r0, r1)
        Lf:
            androidx.collection.MutableScatterSet r0 = androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$getDependenciesSet$p(r0)
            if (r0 == 0) goto L5d
            java.lang.Object[] r1 = r0.elements
            long[] r2 = r0.metadata
            int r3 = r2.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L5a
            r4 = 0
            r5 = r4
        L20:
            r6 = r2[r5]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L55
            int r8 = r5 - r3
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r4
        L3a:
            if (r10 >= r8) goto L53
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L4f
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r1[r11]
            androidx.compose.ui.graphics.layer.GraphicsLayer r11 = (androidx.compose.ui.graphics.layer.GraphicsLayer) r11
            r11.onRemovedFromParentLayer()
        L4f:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L3a
        L53:
            if (r8 != r9) goto L5a
        L55:
            if (r5 == r3) goto L5a
            int r5 = r5 + 1
            goto L20
        L5a:
            r0.clear()
        L5d:
            androidx.compose.ui.graphics.layer.GraphicsLayerImpl r0 = r15.impl
            r0.discardDisplayList()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.GraphicsLayer.discardDisplayList$ui_graphics_release():void");
    }

    public final androidx.compose.ui.graphics.Outline getOutline() {
        androidx.compose.ui.graphics.Outline rectangle;
        androidx.compose.ui.graphics.Outline outline = this.internalOutline;
        Path path = this.outlinePath;
        if (outline != null) {
            return outline;
        }
        if (path == null) {
            long jM2483toSizeozmzZPI = IntSizeKt.m2483toSizeozmzZPI(this.size);
            long j = this.roundRectOutlineTopLeft;
            long j2 = this.roundRectOutlineSize;
            if (j2 != 9205357640488583168L) {
                jM2483toSizeozmzZPI = j2;
            }
            float fM1159getXimpl = Offset.m1159getXimpl(j);
            float fM1160getYimpl = Offset.m1160getYimpl(j);
            float fM1196getWidthimpl = fM1159getXimpl + Size.m1196getWidthimpl(jM2483toSizeozmzZPI);
            float fM1194getHeightimpl = fM1160getYimpl + Size.m1194getHeightimpl(jM2483toSizeozmzZPI);
            float f = this.roundRectCornerRadius;
            if (f > 0.0f) {
                rectangle = new Outline.Rounded(RoundRectKt.m1189RoundRectgG7oq9Y(fM1159getXimpl, fM1160getYimpl, fM1196getWidthimpl, fM1194getHeightimpl, CornerRadiusKt.CornerRadius$default(f, 0.0f, 2, null)));
            } else {
                rectangle = new Outline.Rectangle(new Rect(fM1159getXimpl, fM1160getYimpl, fM1196getWidthimpl, fM1194getHeightimpl));
            }
            this.internalOutline = rectangle;
            return rectangle;
        }
        Outline.Generic generic = new Outline.Generic(path);
        this.internalOutline = generic;
        return generic;
    }

    private final void resetOutlineParams() {
        this.internalOutline = null;
        this.outlinePath = null;
        this.roundRectOutlineSize = Size.Companion.m1201getUnspecifiedNHjbRc();
        this.roundRectOutlineTopLeft = Offset.Companion.m1171getZeroF1C5BW0();
        this.roundRectCornerRadius = 0.0f;
        this.outlineDirty = true;
        this.usePathForClip = false;
    }

    public final void setPathOutline(Path path) {
        resetOutlineParams();
        this.outlinePath = path;
        configureOutlineAndClip();
    }

    /* JADX INFO: renamed from: setRoundRectOutline-TNW_H78, reason: not valid java name */
    public final void m1531setRoundRectOutlineTNW_H78(long j, long j2, float f) {
        if (Offset.m1156equalsimpl0(this.roundRectOutlineTopLeft, j) && Size.m1193equalsimpl0(this.roundRectOutlineSize, j2) && this.roundRectCornerRadius == f && this.outlinePath == null) {
            return;
        }
        resetOutlineParams();
        this.roundRectOutlineTopLeft = j;
        this.roundRectOutlineSize = j2;
        this.roundRectCornerRadius = f;
        configureOutlineAndClip();
    }

    /* JADX INFO: renamed from: setRectOutline-tz77jQw, reason: not valid java name */
    public final void m1530setRectOutlinetz77jQw(long j, long j2) {
        m1531setRoundRectOutlineTNW_H78(j, j2, 0.0f);
    }

    /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA, reason: not valid java name */
    public final void m1527setAmbientShadowColor8_81llA(long j) {
        if (Color.m1296equalsimpl0(j, this.impl.mo1534getAmbientShadowColor0d7_KjU())) {
            return;
        }
        this.impl.mo1538setAmbientShadowColor8_81llA(j);
    }

    /* JADX INFO: renamed from: setSpotShadowColor-8_81llA, reason: not valid java name */
    public final void m1532setSpotShadowColor8_81llA(long j) {
        if (Color.m1296equalsimpl0(j, this.impl.mo1537getSpotShadowColor0d7_KjU())) {
            return;
        }
        this.impl.mo1543setSpotShadowColor8_81llA(j);
    }

    /* JADX INFO: compiled from: AndroidGraphicsLayer.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        LayerSnapshotImpl layerSnapshotImpl;
        if (LayerManager.Companion.isRobolectric()) {
            layerSnapshotImpl = LayerSnapshotV21.INSTANCE;
        } else if (Build.VERSION.SDK_INT >= 28) {
            layerSnapshotImpl = LayerSnapshotV28.INSTANCE;
        } else if (SurfaceUtils.INSTANCE.isLockHardwareCanvasAvailable()) {
            layerSnapshotImpl = LayerSnapshotV22.INSTANCE;
        } else {
            layerSnapshotImpl = LayerSnapshotV21.INSTANCE;
        }
        SnapshotImpl = layerSnapshotImpl;
    }
}
