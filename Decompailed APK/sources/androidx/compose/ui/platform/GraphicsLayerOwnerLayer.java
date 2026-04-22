package androidx.compose.ui.platform;

import android.os.Build;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: GraphicsLayerOwnerLayer.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class GraphicsLayerOwnerLayer implements OwnedLayer {
    private final GraphicsContext context;
    private Function2 drawBlock;
    private boolean drawnWithEnabledZ;
    private GraphicsLayer graphicsLayer;
    private Function0 invalidateParentLayer;
    private float[] inverseMatrixCache;
    private boolean isDestroyed;
    private boolean isDirty;
    private int mutatedFields;
    private Outline outline;
    private final AndroidComposeView ownerView;
    private Paint softwareLayerPaint;
    private Path tmpPath;
    private long size = IntSizeKt.IntSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private final float[] matrixCache = Matrix.m1356constructorimpl$default(null, 1, null);
    private Density density = DensityKt.Density$default(1.0f, 0.0f, 2, null);
    private LayoutDirection layoutDirection = LayoutDirection.Ltr;
    private final CanvasDrawScope scope = new CanvasDrawScope();
    private long transformOrigin = TransformOrigin.Companion.m1434getCenterSzJe1aQ();
    private final Function1 recordLambda = new Function1() { // from class: androidx.compose.ui.platform.GraphicsLayerOwnerLayer$recordLambda$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DrawScope) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(DrawScope drawScope) {
            GraphicsLayerOwnerLayer graphicsLayerOwnerLayer = this.this$0;
            Canvas canvas = drawScope.getDrawContext().getCanvas();
            Function2 function2 = graphicsLayerOwnerLayer.drawBlock;
            if (function2 != null) {
                function2.invoke(canvas, drawScope.getDrawContext().getGraphicsLayer());
            }
        }
    };

    public GraphicsLayerOwnerLayer(GraphicsLayer graphicsLayer, GraphicsContext graphicsContext, AndroidComposeView androidComposeView, Function2 function2, Function0 function0) {
        this.graphicsLayer = graphicsLayer;
        this.context = graphicsContext;
        this.ownerView = androidComposeView;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
    }

    private final void setDirty(boolean z) {
        if (z != this.isDirty) {
            this.isDirty = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateLayerProperties(ReusableGraphicsLayerScope reusableGraphicsLayerScope) {
        boolean z;
        int iM1517getModulateAlphake2Ky5w;
        Function0 function0;
        int mutatedFields$ui_release = reusableGraphicsLayerScope.getMutatedFields$ui_release() | this.mutatedFields;
        this.layoutDirection = reusableGraphicsLayerScope.getLayoutDirection$ui_release();
        this.density = reusableGraphicsLayerScope.getGraphicsDensity$ui_release();
        int i = mutatedFields$ui_release & PKIFailureInfo.certConfirmed;
        if (i != 0) {
            this.transformOrigin = reusableGraphicsLayerScope.mo1335getTransformOriginSzJe1aQ();
        }
        if ((mutatedFields$ui_release & 1) != 0) {
            this.graphicsLayer.setScaleX(reusableGraphicsLayerScope.getScaleX());
        }
        if ((mutatedFields$ui_release & 2) != 0) {
            this.graphicsLayer.setScaleY(reusableGraphicsLayerScope.getScaleY());
        }
        if ((mutatedFields$ui_release & 4) != 0) {
            this.graphicsLayer.setAlpha(reusableGraphicsLayerScope.getAlpha());
        }
        if ((mutatedFields$ui_release & 8) != 0) {
            this.graphicsLayer.setTranslationX(reusableGraphicsLayerScope.getTranslationX());
        }
        if ((mutatedFields$ui_release & 16) != 0) {
            this.graphicsLayer.setTranslationY(reusableGraphicsLayerScope.getTranslationY());
        }
        if ((mutatedFields$ui_release & 32) != 0) {
            this.graphicsLayer.setShadowElevation(reusableGraphicsLayerScope.getShadowElevation());
            if (reusableGraphicsLayerScope.getShadowElevation() > 0.0f && !this.drawnWithEnabledZ && (function0 = this.invalidateParentLayer) != null) {
                function0.invoke();
            }
        }
        if ((mutatedFields$ui_release & 64) != 0) {
            this.graphicsLayer.m1527setAmbientShadowColor8_81llA(reusableGraphicsLayerScope.m1395getAmbientShadowColor0d7_KjU());
        }
        if ((mutatedFields$ui_release & 128) != 0) {
            this.graphicsLayer.m1532setSpotShadowColor8_81llA(reusableGraphicsLayerScope.m1398getSpotShadowColor0d7_KjU());
        }
        if ((mutatedFields$ui_release & 1024) != 0) {
            this.graphicsLayer.setRotationZ(reusableGraphicsLayerScope.getRotationZ());
        }
        if ((mutatedFields$ui_release & 256) != 0) {
            this.graphicsLayer.setRotationX(reusableGraphicsLayerScope.getRotationX());
        }
        if ((mutatedFields$ui_release & 512) != 0) {
            this.graphicsLayer.setRotationY(reusableGraphicsLayerScope.getRotationY());
        }
        if ((mutatedFields$ui_release & 2048) != 0) {
            this.graphicsLayer.setCameraDistance(reusableGraphicsLayerScope.getCameraDistance());
        }
        if (i != 0) {
            if (TransformOrigin.m1428equalsimpl0(this.transformOrigin, TransformOrigin.Companion.m1434getCenterSzJe1aQ())) {
                this.graphicsLayer.m1529setPivotOffsetk4lQ0M(Offset.Companion.m1170getUnspecifiedF1C5BW0());
            } else {
                this.graphicsLayer.m1529setPivotOffsetk4lQ0M(OffsetKt.Offset(TransformOrigin.m1429getPivotFractionXimpl(this.transformOrigin) * IntSize.m2476getWidthimpl(this.size), TransformOrigin.m1430getPivotFractionYimpl(this.transformOrigin) * IntSize.m2475getHeightimpl(this.size)));
            }
        }
        if ((mutatedFields$ui_release & 16384) != 0) {
            this.graphicsLayer.setClip(reusableGraphicsLayerScope.getClip());
        }
        if ((131072 & mutatedFields$ui_release) != 0) {
            GraphicsLayer graphicsLayer = this.graphicsLayer;
            reusableGraphicsLayerScope.getRenderEffect();
            graphicsLayer.setRenderEffect(null);
        }
        if ((32768 & mutatedFields$ui_release) != 0) {
            GraphicsLayer graphicsLayer2 = this.graphicsLayer;
            int iM1396getCompositingStrategyNrFUSI = reusableGraphicsLayerScope.m1396getCompositingStrategyNrFUSI();
            CompositingStrategy.Companion companion = CompositingStrategy.Companion;
            if (CompositingStrategy.m1319equalsimpl0(iM1396getCompositingStrategyNrFUSI, companion.m1322getAutoNrFUSI())) {
                iM1517getModulateAlphake2Ky5w = androidx.compose.ui.graphics.layer.CompositingStrategy.Companion.m1516getAutoke2Ky5w();
            } else if (CompositingStrategy.m1319equalsimpl0(iM1396getCompositingStrategyNrFUSI, companion.m1324getOffscreenNrFUSI())) {
                iM1517getModulateAlphake2Ky5w = androidx.compose.ui.graphics.layer.CompositingStrategy.Companion.m1518getOffscreenke2Ky5w();
            } else {
                if (!CompositingStrategy.m1319equalsimpl0(iM1396getCompositingStrategyNrFUSI, companion.m1323getModulateAlphaNrFUSI())) {
                    throw new IllegalStateException("Not supported composition strategy");
                }
                iM1517getModulateAlphake2Ky5w = androidx.compose.ui.graphics.layer.CompositingStrategy.Companion.m1517getModulateAlphake2Ky5w();
            }
            graphicsLayer2.m1528setCompositingStrategyWpw9cng(iM1517getModulateAlphake2Ky5w);
        }
        if (Intrinsics.areEqual(this.outline, reusableGraphicsLayerScope.getOutline$ui_release())) {
            z = false;
        } else {
            this.outline = reusableGraphicsLayerScope.getOutline$ui_release();
            updateOutline();
            z = true;
        }
        this.mutatedFields = reusableGraphicsLayerScope.getMutatedFields$ui_release();
        if (mutatedFields$ui_release != 0 || z) {
            triggerRepaint();
        }
    }

    private final void triggerRepaint() {
        if (Build.VERSION.SDK_INT >= 26) {
            WrapperRenderNodeLayerHelperMethods.INSTANCE.onDescendantInvalidated(this.ownerView);
        } else {
            this.ownerView.invalidate();
        }
    }

    private final void updateOutline() {
        Function0 function0;
        Outline outline = this.outline;
        if (outline == null) {
            return;
        }
        GraphicsLayerKt.setOutline(this.graphicsLayer, outline);
        if (!(outline instanceof Outline.Generic) || Build.VERSION.SDK_INT >= 33 || (function0 = this.invalidateParentLayer) == null) {
            return;
        }
        function0.invoke();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: isInLayer-k-4lQ0M */
    public boolean mo1898isInLayerk4lQ0M(long j) {
        float fM1159getXimpl = Offset.m1159getXimpl(j);
        float fM1160getYimpl = Offset.m1160getYimpl(j);
        if (this.graphicsLayer.getClip()) {
            return ShapeContainingUtilKt.isInOutline$default(this.graphicsLayer.getOutline(), fM1159getXimpl, fM1160getYimpl, null, null, 24, null);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: move--gyyYBs */
    public void mo1900movegyyYBs(long j) {
        this.graphicsLayer.m1533setTopLeftgyyYBs(j);
        triggerRepaint();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: resize-ozmzZPI */
    public void mo1901resizeozmzZPI(long j) {
        if (IntSize.m2474equalsimpl0(j, this.size)) {
            return;
        }
        this.size = j;
        invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void drawLayer(Canvas canvas, GraphicsLayer graphicsLayer) {
        android.graphics.Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (nativeCanvas.isHardwareAccelerated()) {
            updateDisplayList();
            this.drawnWithEnabledZ = this.graphicsLayer.getShadowElevation() > 0.0f;
            DrawContext drawContext = this.scope.getDrawContext();
            drawContext.setCanvas(canvas);
            drawContext.setGraphicsLayer(graphicsLayer);
            GraphicsLayerKt.drawLayer(this.scope, this.graphicsLayer);
            return;
        }
        float fM2457getXimpl = IntOffset.m2457getXimpl(this.graphicsLayer.m1525getTopLeftnOccac());
        float fM2458getYimpl = IntOffset.m2458getYimpl(this.graphicsLayer.m1525getTopLeftnOccac());
        float fM2476getWidthimpl = fM2457getXimpl + IntSize.m2476getWidthimpl(this.size);
        float fM2475getHeightimpl = fM2458getYimpl + IntSize.m2475getHeightimpl(this.size);
        if (this.graphicsLayer.getAlpha() < 1.0f) {
            Paint Paint = this.softwareLayerPaint;
            if (Paint == null) {
                Paint = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = Paint;
            }
            Paint.setAlpha(this.graphicsLayer.getAlpha());
            nativeCanvas.saveLayer(fM2457getXimpl, fM2458getYimpl, fM2476getWidthimpl, fM2475getHeightimpl, Paint.asFrameworkPaint());
        } else {
            canvas.save();
        }
        canvas.translate(fM2457getXimpl, fM2458getYimpl);
        canvas.mo1210concat58bKbWc(m1971getMatrixsQKQjiQ());
        if (this.graphicsLayer.getClip()) {
            clipManually(canvas);
        }
        Function2 function2 = this.drawBlock;
        if (function2 != null) {
            function2.invoke(canvas, null);
        }
        canvas.restore();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateDisplayList() {
        if (this.isDirty) {
            if (!TransformOrigin.m1428equalsimpl0(this.transformOrigin, TransformOrigin.Companion.m1434getCenterSzJe1aQ()) && !IntSize.m2474equalsimpl0(this.graphicsLayer.m1524getSizeYbymL2g(), this.size)) {
                this.graphicsLayer.m1529setPivotOffsetk4lQ0M(OffsetKt.Offset(TransformOrigin.m1429getPivotFractionXimpl(this.transformOrigin) * IntSize.m2476getWidthimpl(this.size), TransformOrigin.m1430getPivotFractionYimpl(this.transformOrigin) * IntSize.m2475getHeightimpl(this.size)));
            }
            this.graphicsLayer.m1526recordmLhObY(this.density, this.layoutDirection, this.size, this.recordLambda);
            setDirty(false);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void invalidate() {
        if (this.isDirty || this.isDestroyed) {
            return;
        }
        this.ownerView.invalidate();
        setDirty(true);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void destroy() {
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.isDestroyed = true;
        setDirty(false);
        GraphicsContext graphicsContext = this.context;
        if (graphicsContext != null) {
            graphicsContext.releaseGraphicsLayer(this.graphicsLayer);
            this.ownerView.recycle$ui_release(this);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: mapOffset-8S9VItk */
    public long mo1899mapOffset8S9VItk(long j, boolean z) {
        if (z) {
            float[] fArrM1970getInverseMatrix3i98HWw = m1970getInverseMatrix3i98HWw();
            return fArrM1970getInverseMatrix3i98HWw != null ? Matrix.m1359mapMKHz9U(fArrM1970getInverseMatrix3i98HWw, j) : Offset.Companion.m1169getInfiniteF1C5BW0();
        }
        return Matrix.m1359mapMKHz9U(m1971getMatrixsQKQjiQ(), j);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void mapBounds(MutableRect mutableRect, boolean z) {
        if (z) {
            float[] fArrM1970getInverseMatrix3i98HWw = m1970getInverseMatrix3i98HWw();
            if (fArrM1970getInverseMatrix3i98HWw == null) {
                mutableRect.set(0.0f, 0.0f, 0.0f, 0.0f);
                return;
            } else {
                Matrix.m1360mapimpl(fArrM1970getInverseMatrix3i98HWw, mutableRect);
                return;
            }
        }
        Matrix.m1360mapimpl(m1971getMatrixsQKQjiQ(), mutableRect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void reuseLayer(Function2 function2, Function0 function0) {
        GraphicsContext graphicsContext = this.context;
        if (graphicsContext == null) {
            throw new IllegalArgumentException("currently reuse is only supported when we manage the layer lifecycle");
        }
        if (!this.graphicsLayer.isReleased()) {
            throw new IllegalArgumentException("layer should have been released before reuse");
        }
        this.graphicsLayer = graphicsContext.createGraphicsLayer();
        this.isDestroyed = false;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
        this.transformOrigin = TransformOrigin.Companion.m1434getCenterSzJe1aQ();
        this.drawnWithEnabledZ = false;
        this.size = IntSizeKt.IntSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.outline = null;
        this.mutatedFields = 0;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: transform-58bKbWc */
    public void mo1902transform58bKbWc(float[] fArr) {
        Matrix.m1367timesAssign58bKbWc(fArr, m1971getMatrixsQKQjiQ());
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: inverseTransform-58bKbWc */
    public void mo1897inverseTransform58bKbWc(float[] fArr) {
        float[] fArrM1970getInverseMatrix3i98HWw = m1970getInverseMatrix3i98HWw();
        if (fArrM1970getInverseMatrix3i98HWw != null) {
            Matrix.m1367timesAssign58bKbWc(fArr, fArrM1970getInverseMatrix3i98HWw);
        }
    }

    /* JADX INFO: renamed from: getMatrix-sQKQjiQ, reason: not valid java name */
    private final float[] m1971getMatrixsQKQjiQ() {
        updateMatrix();
        return this.matrixCache;
    }

    /* JADX INFO: renamed from: getInverseMatrix-3i98HWw, reason: not valid java name */
    private final float[] m1970getInverseMatrix3i98HWw() {
        float[] fArrM1971getMatrixsQKQjiQ = m1971getMatrixsQKQjiQ();
        float[] fArrM1356constructorimpl$default = this.inverseMatrixCache;
        if (fArrM1356constructorimpl$default == null) {
            fArrM1356constructorimpl$default = Matrix.m1356constructorimpl$default(null, 1, null);
            this.inverseMatrixCache = fArrM1356constructorimpl$default;
        }
        if (InvertMatrixKt.m1972invertToJiSxe2E(fArrM1971getMatrixsQKQjiQ, fArrM1356constructorimpl$default)) {
            return fArrM1356constructorimpl$default;
        }
        return null;
    }

    private final void updateMatrix() {
        long jM1523getPivotOffsetF1C5BW0;
        GraphicsLayer graphicsLayer = this.graphicsLayer;
        if (OffsetKt.m1174isUnspecifiedk4lQ0M(graphicsLayer.m1523getPivotOffsetF1C5BW0())) {
            jM1523getPivotOffsetF1C5BW0 = SizeKt.m1203getCenteruvyYCjk(IntSizeKt.m2483toSizeozmzZPI(this.size));
        } else {
            jM1523getPivotOffsetF1C5BW0 = graphicsLayer.m1523getPivotOffsetF1C5BW0();
        }
        Matrix.m1361resetimpl(this.matrixCache);
        float[] fArr = this.matrixCache;
        float[] fArrM1356constructorimpl$default = Matrix.m1356constructorimpl$default(null, 1, null);
        Matrix.m1370translateimpl$default(fArrM1356constructorimpl$default, -Offset.m1159getXimpl(jM1523getPivotOffsetF1C5BW0), -Offset.m1160getYimpl(jM1523getPivotOffsetF1C5BW0), 0.0f, 4, null);
        Matrix.m1367timesAssign58bKbWc(fArr, fArrM1356constructorimpl$default);
        float[] fArr2 = this.matrixCache;
        float[] fArrM1356constructorimpl$default2 = Matrix.m1356constructorimpl$default(null, 1, null);
        Matrix.m1370translateimpl$default(fArrM1356constructorimpl$default2, graphicsLayer.getTranslationX(), graphicsLayer.getTranslationY(), 0.0f, 4, null);
        Matrix.m1362rotateXimpl(fArrM1356constructorimpl$default2, graphicsLayer.getRotationX());
        Matrix.m1363rotateYimpl(fArrM1356constructorimpl$default2, graphicsLayer.getRotationY());
        Matrix.m1364rotateZimpl(fArrM1356constructorimpl$default2, graphicsLayer.getRotationZ());
        Matrix.m1366scaleimpl$default(fArrM1356constructorimpl$default2, graphicsLayer.getScaleX(), graphicsLayer.getScaleY(), 0.0f, 4, null);
        Matrix.m1367timesAssign58bKbWc(fArr2, fArrM1356constructorimpl$default2);
        float[] fArr3 = this.matrixCache;
        float[] fArrM1356constructorimpl$default3 = Matrix.m1356constructorimpl$default(null, 1, null);
        Matrix.m1370translateimpl$default(fArrM1356constructorimpl$default3, Offset.m1159getXimpl(jM1523getPivotOffsetF1C5BW0), Offset.m1160getYimpl(jM1523getPivotOffsetF1C5BW0), 0.0f, 4, null);
        Matrix.m1367timesAssign58bKbWc(fArr3, fArrM1356constructorimpl$default3);
    }

    private final void clipManually(Canvas canvas) {
        if (this.graphicsLayer.getClip()) {
            Outline outline = this.graphicsLayer.getOutline();
            if (outline instanceof Outline.Rectangle) {
                Canvas.CC.m1285clipRectmtrdDE$default(canvas, ((Outline.Rectangle) outline).getRect(), 0, 2, null);
                return;
            }
            if (outline instanceof Outline.Rounded) {
                Path Path = this.tmpPath;
                if (Path == null) {
                    Path = AndroidPath_androidKt.Path();
                    this.tmpPath = Path;
                }
                Path.reset();
                Path.CC.addRoundRect$default(Path, ((Outline.Rounded) outline).getRoundRect(), null, 2, null);
                Canvas.CC.m1283clipPathmtrdDE$default(canvas, Path, 0, 2, null);
                return;
            }
            if (outline instanceof Outline.Generic) {
                Canvas.CC.m1283clipPathmtrdDE$default(canvas, ((Outline.Generic) outline).getPath(), 0, 2, null);
            }
        }
    }
}
