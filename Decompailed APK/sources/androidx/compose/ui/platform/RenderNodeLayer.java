package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.os.Build;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: RenderNodeLayer.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RenderNodeLayer implements OwnedLayer {
    private Function2 drawBlock;
    private boolean drawnWithZ;
    private Function0 invalidateParentLayer;
    private boolean isDestroyed;
    private boolean isDirty;
    private int mutatedFields;
    private final AndroidComposeView ownerView;
    private final DeviceRenderNode renderNode;
    private Paint softwareLayerPaint;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final Function2 getMatrix = new Function2() { // from class: androidx.compose.ui.platform.RenderNodeLayer$Companion$getMatrix$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((DeviceRenderNode) obj, (Matrix) obj2);
            return Unit.INSTANCE;
        }

        public final void invoke(DeviceRenderNode deviceRenderNode, Matrix matrix) {
            deviceRenderNode.getMatrix(matrix);
        }
    };
    private final OutlineResolver outlineResolver = new OutlineResolver();
    private final LayerMatrixCache matrixCache = new LayerMatrixCache(getMatrix);
    private final CanvasHolder canvasHolder = new CanvasHolder();
    private long transformOrigin = TransformOrigin.Companion.m1434getCenterSzJe1aQ();

    public RenderNodeLayer(AndroidComposeView androidComposeView, Function2 function2, Function0 function0) {
        DeviceRenderNode renderNodeApi23;
        this.ownerView = androidComposeView;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
        if (Build.VERSION.SDK_INT >= 29) {
            renderNodeApi23 = new RenderNodeApi29(androidComposeView);
        } else {
            renderNodeApi23 = new RenderNodeApi23(androidComposeView);
        }
        renderNodeApi23.setHasOverlappingRendering(true);
        renderNodeApi23.setClipToBounds(false);
        this.renderNode = renderNodeApi23;
    }

    private final void setDirty(boolean z) {
        if (z != this.isDirty) {
            this.isDirty = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateLayerProperties(ReusableGraphicsLayerScope reusableGraphicsLayerScope) {
        Function0 function0;
        int mutatedFields$ui_release = reusableGraphicsLayerScope.getMutatedFields$ui_release() | this.mutatedFields;
        int i = mutatedFields$ui_release & PKIFailureInfo.certConfirmed;
        if (i != 0) {
            this.transformOrigin = reusableGraphicsLayerScope.mo1335getTransformOriginSzJe1aQ();
        }
        boolean z = false;
        boolean z2 = this.renderNode.getClipToOutline() && !this.outlineResolver.getOutlineClipSupported();
        if ((mutatedFields$ui_release & 1) != 0) {
            this.renderNode.setScaleX(reusableGraphicsLayerScope.getScaleX());
        }
        if ((mutatedFields$ui_release & 2) != 0) {
            this.renderNode.setScaleY(reusableGraphicsLayerScope.getScaleY());
        }
        if ((mutatedFields$ui_release & 4) != 0) {
            this.renderNode.setAlpha(reusableGraphicsLayerScope.getAlpha());
        }
        if ((mutatedFields$ui_release & 8) != 0) {
            this.renderNode.setTranslationX(reusableGraphicsLayerScope.getTranslationX());
        }
        if ((mutatedFields$ui_release & 16) != 0) {
            this.renderNode.setTranslationY(reusableGraphicsLayerScope.getTranslationY());
        }
        if ((mutatedFields$ui_release & 32) != 0) {
            this.renderNode.setElevation(reusableGraphicsLayerScope.getShadowElevation());
        }
        if ((mutatedFields$ui_release & 64) != 0) {
            this.renderNode.setAmbientShadowColor(ColorKt.m1316toArgb8_81llA(reusableGraphicsLayerScope.m1395getAmbientShadowColor0d7_KjU()));
        }
        if ((mutatedFields$ui_release & 128) != 0) {
            this.renderNode.setSpotShadowColor(ColorKt.m1316toArgb8_81llA(reusableGraphicsLayerScope.m1398getSpotShadowColor0d7_KjU()));
        }
        if ((mutatedFields$ui_release & 1024) != 0) {
            this.renderNode.setRotationZ(reusableGraphicsLayerScope.getRotationZ());
        }
        if ((mutatedFields$ui_release & 256) != 0) {
            this.renderNode.setRotationX(reusableGraphicsLayerScope.getRotationX());
        }
        if ((mutatedFields$ui_release & 512) != 0) {
            this.renderNode.setRotationY(reusableGraphicsLayerScope.getRotationY());
        }
        if ((mutatedFields$ui_release & 2048) != 0) {
            this.renderNode.setCameraDistance(reusableGraphicsLayerScope.getCameraDistance());
        }
        if (i != 0) {
            this.renderNode.setPivotX(TransformOrigin.m1429getPivotFractionXimpl(this.transformOrigin) * this.renderNode.getWidth());
            this.renderNode.setPivotY(TransformOrigin.m1430getPivotFractionYimpl(this.transformOrigin) * this.renderNode.getHeight());
        }
        boolean z3 = reusableGraphicsLayerScope.getClip() && reusableGraphicsLayerScope.getShape() != RectangleShapeKt.getRectangleShape();
        if ((mutatedFields$ui_release & 24576) != 0) {
            this.renderNode.setClipToOutline(z3);
            this.renderNode.setClipToBounds(reusableGraphicsLayerScope.getClip() && reusableGraphicsLayerScope.getShape() == RectangleShapeKt.getRectangleShape());
        }
        if ((131072 & mutatedFields$ui_release) != 0) {
            DeviceRenderNode deviceRenderNode = this.renderNode;
            reusableGraphicsLayerScope.getRenderEffect();
            deviceRenderNode.setRenderEffect(null);
        }
        if ((32768 & mutatedFields$ui_release) != 0) {
            this.renderNode.mo1961setCompositingStrategyaDBOjCE(reusableGraphicsLayerScope.m1396getCompositingStrategyNrFUSI());
        }
        boolean zM1984updateS_szKao = this.outlineResolver.m1984updateS_szKao(reusableGraphicsLayerScope.getOutline$ui_release(), reusableGraphicsLayerScope.getAlpha(), z3, reusableGraphicsLayerScope.getShadowElevation(), reusableGraphicsLayerScope.m1397getSizeNHjbRc());
        if (this.outlineResolver.getCacheIsDirty$ui_release()) {
            this.renderNode.setOutline(this.outlineResolver.getAndroidOutline());
        }
        if (z3 && !this.outlineResolver.getOutlineClipSupported()) {
            z = true;
        }
        if (z2 != z || (z && zM1984updateS_szKao)) {
            invalidate();
        } else {
            triggerRepaint();
        }
        if (!this.drawnWithZ && this.renderNode.getElevation() > 0.0f && (function0 = this.invalidateParentLayer) != null) {
            function0.invoke();
        }
        if ((mutatedFields$ui_release & 7963) != 0) {
            this.matrixCache.invalidate();
        }
        this.mutatedFields = reusableGraphicsLayerScope.getMutatedFields$ui_release();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: isInLayer-k-4lQ0M */
    public boolean mo1898isInLayerk4lQ0M(long j) {
        float fM1159getXimpl = Offset.m1159getXimpl(j);
        float fM1160getYimpl = Offset.m1160getYimpl(j);
        if (this.renderNode.getClipToBounds()) {
            return 0.0f <= fM1159getXimpl && fM1159getXimpl < ((float) this.renderNode.getWidth()) && 0.0f <= fM1160getYimpl && fM1160getYimpl < ((float) this.renderNode.getHeight());
        }
        if (this.renderNode.getClipToOutline()) {
            return this.outlineResolver.m1983isInOutlinek4lQ0M(j);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: resize-ozmzZPI */
    public void mo1901resizeozmzZPI(long j) {
        int iM2476getWidthimpl = IntSize.m2476getWidthimpl(j);
        int iM2475getHeightimpl = IntSize.m2475getHeightimpl(j);
        this.renderNode.setPivotX(TransformOrigin.m1429getPivotFractionXimpl(this.transformOrigin) * iM2476getWidthimpl);
        this.renderNode.setPivotY(TransformOrigin.m1430getPivotFractionYimpl(this.transformOrigin) * iM2475getHeightimpl);
        DeviceRenderNode deviceRenderNode = this.renderNode;
        if (deviceRenderNode.setPosition(deviceRenderNode.getLeft(), this.renderNode.getTop(), this.renderNode.getLeft() + iM2476getWidthimpl, this.renderNode.getTop() + iM2475getHeightimpl)) {
            this.renderNode.setOutline(this.outlineResolver.getAndroidOutline());
            invalidate();
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: move--gyyYBs */
    public void mo1900movegyyYBs(long j) {
        int left = this.renderNode.getLeft();
        int top = this.renderNode.getTop();
        int iM2457getXimpl = IntOffset.m2457getXimpl(j);
        int iM2458getYimpl = IntOffset.m2458getYimpl(j);
        if (left == iM2457getXimpl && top == iM2458getYimpl) {
            return;
        }
        if (left != iM2457getXimpl) {
            this.renderNode.offsetLeftAndRight(iM2457getXimpl - left);
        }
        if (top != iM2458getYimpl) {
            this.renderNode.offsetTopAndBottom(iM2458getYimpl - top);
        }
        triggerRepaint();
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void invalidate() {
        if (this.isDirty || this.isDestroyed) {
            return;
        }
        this.ownerView.invalidate();
        setDirty(true);
    }

    private final void triggerRepaint() {
        if (Build.VERSION.SDK_INT >= 26) {
            WrapperRenderNodeLayerHelperMethods.INSTANCE.onDescendantInvalidated(this.ownerView);
        } else {
            this.ownerView.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void drawLayer(Canvas canvas, GraphicsLayer graphicsLayer) {
        android.graphics.Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (nativeCanvas.isHardwareAccelerated()) {
            updateDisplayList();
            boolean z = this.renderNode.getElevation() > 0.0f;
            this.drawnWithZ = z;
            if (z) {
                canvas.enableZ();
            }
            this.renderNode.drawInto(nativeCanvas);
            if (this.drawnWithZ) {
                canvas.disableZ();
                return;
            }
            return;
        }
        float left = this.renderNode.getLeft();
        float top = this.renderNode.getTop();
        float right = this.renderNode.getRight();
        float bottom = this.renderNode.getBottom();
        if (this.renderNode.getAlpha() < 1.0f) {
            Paint Paint = this.softwareLayerPaint;
            if (Paint == null) {
                Paint = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = Paint;
            }
            Paint.setAlpha(this.renderNode.getAlpha());
            nativeCanvas.saveLayer(left, top, right, bottom, Paint.asFrameworkPaint());
        } else {
            canvas.save();
        }
        canvas.translate(left, top);
        canvas.mo1210concat58bKbWc(this.matrixCache.m1974calculateMatrixGrdbGEg(this.renderNode));
        clipRenderNode(canvas);
        Function2 function2 = this.drawBlock;
        if (function2 != null) {
            function2.invoke(canvas, null);
        }
        canvas.restore();
        setDirty(false);
    }

    private final void clipRenderNode(Canvas canvas) {
        if (this.renderNode.getClipToOutline() || this.renderNode.getClipToBounds()) {
            this.outlineResolver.clipToOutline(canvas);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateDisplayList() {
        if (this.isDirty || !this.renderNode.getHasDisplayList()) {
            Path clipPath = (!this.renderNode.getClipToOutline() || this.outlineResolver.getOutlineClipSupported()) ? null : this.outlineResolver.getClipPath();
            final Function2 function2 = this.drawBlock;
            if (function2 != null) {
                this.renderNode.record(this.canvasHolder, clipPath, new Function1() { // from class: androidx.compose.ui.platform.RenderNodeLayer$updateDisplayList$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Canvas) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Canvas canvas) {
                        function2.invoke(canvas, null);
                    }
                });
            }
            setDirty(false);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void destroy() {
        if (this.renderNode.getHasDisplayList()) {
            this.renderNode.discardDisplayList();
        }
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.isDestroyed = true;
        setDirty(false);
        this.ownerView.requestClearInvalidObservations();
        this.ownerView.recycle$ui_release(this);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: mapOffset-8S9VItk */
    public long mo1899mapOffset8S9VItk(long j, boolean z) {
        if (z) {
            float[] fArrM1973calculateInverseMatrixbWbORWo = this.matrixCache.m1973calculateInverseMatrixbWbORWo(this.renderNode);
            return fArrM1973calculateInverseMatrixbWbORWo != null ? androidx.compose.ui.graphics.Matrix.m1359mapMKHz9U(fArrM1973calculateInverseMatrixbWbORWo, j) : Offset.Companion.m1169getInfiniteF1C5BW0();
        }
        return androidx.compose.ui.graphics.Matrix.m1359mapMKHz9U(this.matrixCache.m1974calculateMatrixGrdbGEg(this.renderNode), j);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void mapBounds(MutableRect mutableRect, boolean z) {
        if (z) {
            float[] fArrM1973calculateInverseMatrixbWbORWo = this.matrixCache.m1973calculateInverseMatrixbWbORWo(this.renderNode);
            if (fArrM1973calculateInverseMatrixbWbORWo == null) {
                mutableRect.set(0.0f, 0.0f, 0.0f, 0.0f);
                return;
            } else {
                androidx.compose.ui.graphics.Matrix.m1360mapimpl(fArrM1973calculateInverseMatrixbWbORWo, mutableRect);
                return;
            }
        }
        androidx.compose.ui.graphics.Matrix.m1360mapimpl(this.matrixCache.m1974calculateMatrixGrdbGEg(this.renderNode), mutableRect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void reuseLayer(Function2 function2, Function0 function0) {
        setDirty(false);
        this.isDestroyed = false;
        this.drawnWithZ = false;
        this.transformOrigin = TransformOrigin.Companion.m1434getCenterSzJe1aQ();
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: transform-58bKbWc */
    public void mo1902transform58bKbWc(float[] fArr) {
        androidx.compose.ui.graphics.Matrix.m1367timesAssign58bKbWc(fArr, this.matrixCache.m1974calculateMatrixGrdbGEg(this.renderNode));
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: inverseTransform-58bKbWc */
    public void mo1897inverseTransform58bKbWc(float[] fArr) {
        float[] fArrM1973calculateInverseMatrixbWbORWo = this.matrixCache.m1973calculateInverseMatrixbWbORWo(this.renderNode);
        if (fArrM1973calculateInverseMatrixbWbORWo != null) {
            androidx.compose.ui.graphics.Matrix.m1367timesAssign58bKbWc(fArr, fArrM1973calculateInverseMatrixbWbORWo);
        }
    }

    /* JADX INFO: compiled from: RenderNodeLayer.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
