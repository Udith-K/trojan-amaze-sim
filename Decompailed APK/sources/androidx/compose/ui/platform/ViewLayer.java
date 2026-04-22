package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: ViewLayer.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ViewLayer extends View implements OwnedLayer {
    private static boolean hasRetrievedMethod;
    private static Field recreateDisplayList;
    private static boolean shouldUseDispatchDraw;
    private static Method updateDisplayListIfDirtyMethod;
    private final CanvasHolder canvasHolder;
    private Rect clipBoundsCache;
    private boolean clipToBounds;
    private final DrawChildContainer container;
    private Function2 drawBlock;
    private boolean drawnWithZ;
    private Function0 invalidateParentLayer;
    private boolean isInvalidated;
    private final long layerId;
    private boolean mHasOverlappingRendering;
    private long mTransformOrigin;
    private final LayerMatrixCache matrixCache;
    private int mutatedFields;
    private final OutlineResolver outlineResolver;
    private final AndroidComposeView ownerView;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final Function2 getMatrix = new Function2() { // from class: androidx.compose.ui.platform.ViewLayer$Companion$getMatrix$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((View) obj, (Matrix) obj2);
            return Unit.INSTANCE;
        }

        public final void invoke(View view, Matrix matrix) {
            matrix.set(view.getMatrix());
        }
    };
    private static final ViewOutlineProvider OutlineProvider = new ViewOutlineProvider() { // from class: androidx.compose.ui.platform.ViewLayer$Companion$OutlineProvider$1
        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type androidx.compose.ui.platform.ViewLayer");
            Outline androidOutline = ((ViewLayer) view).outlineResolver.getAndroidOutline();
            Intrinsics.checkNotNull(androidOutline);
            outline.set(androidOutline);
        }
    };

    @Override // android.view.View
    public void forceLayout() {
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public final AndroidComposeView getOwnerView() {
        return this.ownerView;
    }

    public final DrawChildContainer getContainer() {
        return this.container;
    }

    public ViewLayer(AndroidComposeView androidComposeView, DrawChildContainer drawChildContainer, Function2 function2, Function0 function0) {
        super(androidComposeView.getContext());
        this.ownerView = androidComposeView;
        this.container = drawChildContainer;
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
        this.outlineResolver = new OutlineResolver();
        this.canvasHolder = new CanvasHolder();
        this.matrixCache = new LayerMatrixCache(getMatrix);
        this.mTransformOrigin = TransformOrigin.Companion.m1434getCenterSzJe1aQ();
        this.mHasOverlappingRendering = true;
        setWillNotDraw(false);
        drawChildContainer.addView(this);
        this.layerId = View.generateViewId();
    }

    private final Path getManualClipPath() {
        if (!getClipToOutline() || this.outlineResolver.getOutlineClipSupported()) {
            return null;
        }
        return this.outlineResolver.getClipPath();
    }

    public final boolean isInvalidated() {
        return this.isInvalidated;
    }

    private final void setInvalidated(boolean z) {
        if (z != this.isInvalidated) {
            this.isInvalidated = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    public long getLayerId() {
        return this.layerId;
    }

    public long getOwnerViewId() {
        if (Build.VERSION.SDK_INT >= 29) {
            return UniqueDrawingIdApi29.getUniqueDrawingId(this.ownerView);
        }
        return -1L;
    }

    /* JADX INFO: compiled from: ViewLayer.android.kt */
    private static final class UniqueDrawingIdApi29 {
        public static final UniqueDrawingIdApi29 INSTANCE = new UniqueDrawingIdApi29();

        private UniqueDrawingIdApi29() {
        }

        public static final long getUniqueDrawingId(View view) {
            return view.getUniqueDrawingId();
        }
    }

    public final float getCameraDistancePx() {
        return getCameraDistance() / getResources().getDisplayMetrics().densityDpi;
    }

    public final void setCameraDistancePx(float f) {
        setCameraDistance(f * getResources().getDisplayMetrics().densityDpi);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateLayerProperties(ReusableGraphicsLayerScope reusableGraphicsLayerScope) {
        Function0 function0;
        int mutatedFields$ui_release = reusableGraphicsLayerScope.getMutatedFields$ui_release() | this.mutatedFields;
        if ((mutatedFields$ui_release & PKIFailureInfo.certConfirmed) != 0) {
            long jMo1335getTransformOriginSzJe1aQ = reusableGraphicsLayerScope.mo1335getTransformOriginSzJe1aQ();
            this.mTransformOrigin = jMo1335getTransformOriginSzJe1aQ;
            setPivotX(TransformOrigin.m1429getPivotFractionXimpl(jMo1335getTransformOriginSzJe1aQ) * getWidth());
            setPivotY(TransformOrigin.m1430getPivotFractionYimpl(this.mTransformOrigin) * getHeight());
        }
        if ((mutatedFields$ui_release & 1) != 0) {
            setScaleX(reusableGraphicsLayerScope.getScaleX());
        }
        if ((mutatedFields$ui_release & 2) != 0) {
            setScaleY(reusableGraphicsLayerScope.getScaleY());
        }
        if ((mutatedFields$ui_release & 4) != 0) {
            setAlpha(reusableGraphicsLayerScope.getAlpha());
        }
        if ((mutatedFields$ui_release & 8) != 0) {
            setTranslationX(reusableGraphicsLayerScope.getTranslationX());
        }
        if ((mutatedFields$ui_release & 16) != 0) {
            setTranslationY(reusableGraphicsLayerScope.getTranslationY());
        }
        if ((mutatedFields$ui_release & 32) != 0) {
            setElevation(reusableGraphicsLayerScope.getShadowElevation());
        }
        if ((mutatedFields$ui_release & 1024) != 0) {
            setRotation(reusableGraphicsLayerScope.getRotationZ());
        }
        if ((mutatedFields$ui_release & 256) != 0) {
            setRotationX(reusableGraphicsLayerScope.getRotationX());
        }
        if ((mutatedFields$ui_release & 512) != 0) {
            setRotationY(reusableGraphicsLayerScope.getRotationY());
        }
        if ((mutatedFields$ui_release & 2048) != 0) {
            setCameraDistancePx(reusableGraphicsLayerScope.getCameraDistance());
        }
        boolean z = false;
        boolean z2 = getManualClipPath() != null;
        boolean z3 = reusableGraphicsLayerScope.getClip() && reusableGraphicsLayerScope.getShape() != RectangleShapeKt.getRectangleShape();
        if ((mutatedFields$ui_release & 24576) != 0) {
            this.clipToBounds = reusableGraphicsLayerScope.getClip() && reusableGraphicsLayerScope.getShape() == RectangleShapeKt.getRectangleShape();
            resetClipBounds();
            setClipToOutline(z3);
        }
        boolean zM1984updateS_szKao = this.outlineResolver.m1984updateS_szKao(reusableGraphicsLayerScope.getOutline$ui_release(), reusableGraphicsLayerScope.getAlpha(), z3, reusableGraphicsLayerScope.getShadowElevation(), reusableGraphicsLayerScope.m1397getSizeNHjbRc());
        if (this.outlineResolver.getCacheIsDirty$ui_release()) {
            updateOutlineResolver();
        }
        boolean z4 = getManualClipPath() != null;
        if (z2 != z4 || (z4 && zM1984updateS_szKao)) {
            invalidate();
        }
        if (!this.drawnWithZ && getElevation() > 0.0f && (function0 = this.invalidateParentLayer) != null) {
            function0.invoke();
        }
        if ((mutatedFields$ui_release & 7963) != 0) {
            this.matrixCache.invalidate();
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            if ((mutatedFields$ui_release & 64) != 0) {
                ViewLayerVerificationHelper28.INSTANCE.setOutlineAmbientShadowColor(this, ColorKt.m1316toArgb8_81llA(reusableGraphicsLayerScope.m1395getAmbientShadowColor0d7_KjU()));
            }
            if ((mutatedFields$ui_release & 128) != 0) {
                ViewLayerVerificationHelper28.INSTANCE.setOutlineSpotShadowColor(this, ColorKt.m1316toArgb8_81llA(reusableGraphicsLayerScope.m1398getSpotShadowColor0d7_KjU()));
            }
        }
        if (i >= 31 && (131072 & mutatedFields$ui_release) != 0) {
            ViewLayerVerificationHelper31 viewLayerVerificationHelper31 = ViewLayerVerificationHelper31.INSTANCE;
            reusableGraphicsLayerScope.getRenderEffect();
            viewLayerVerificationHelper31.setRenderEffect(this, null);
        }
        if ((mutatedFields$ui_release & 32768) != 0) {
            int iM1396getCompositingStrategyNrFUSI = reusableGraphicsLayerScope.m1396getCompositingStrategyNrFUSI();
            CompositingStrategy.Companion companion = CompositingStrategy.Companion;
            if (CompositingStrategy.m1319equalsimpl0(iM1396getCompositingStrategyNrFUSI, companion.m1324getOffscreenNrFUSI())) {
                setLayerType(2, null);
            } else if (CompositingStrategy.m1319equalsimpl0(iM1396getCompositingStrategyNrFUSI, companion.m1323getModulateAlphaNrFUSI())) {
                setLayerType(0, null);
                this.mHasOverlappingRendering = z;
            } else {
                setLayerType(0, null);
            }
            z = true;
            this.mHasOverlappingRendering = z;
        }
        this.mutatedFields = reusableGraphicsLayerScope.getMutatedFields$ui_release();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return this.mHasOverlappingRendering;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: isInLayer-k-4lQ0M */
    public boolean mo1898isInLayerk4lQ0M(long j) {
        float fM1159getXimpl = Offset.m1159getXimpl(j);
        float fM1160getYimpl = Offset.m1160getYimpl(j);
        if (this.clipToBounds) {
            return 0.0f <= fM1159getXimpl && fM1159getXimpl < ((float) getWidth()) && 0.0f <= fM1160getYimpl && fM1160getYimpl < ((float) getHeight());
        }
        if (getClipToOutline()) {
            return this.outlineResolver.m1983isInOutlinek4lQ0M(j);
        }
        return true;
    }

    private final void updateOutlineResolver() {
        setOutlineProvider(this.outlineResolver.getAndroidOutline() != null ? OutlineProvider : null);
    }

    private final void resetClipBounds() {
        Rect rect;
        if (this.clipToBounds) {
            Rect rect2 = this.clipBoundsCache;
            if (rect2 == null) {
                this.clipBoundsCache = new Rect(0, 0, getWidth(), getHeight());
            } else {
                Intrinsics.checkNotNull(rect2);
                rect2.set(0, 0, getWidth(), getHeight());
            }
            rect = this.clipBoundsCache;
        } else {
            rect = null;
        }
        setClipBounds(rect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: resize-ozmzZPI */
    public void mo1901resizeozmzZPI(long j) {
        int iM2476getWidthimpl = IntSize.m2476getWidthimpl(j);
        int iM2475getHeightimpl = IntSize.m2475getHeightimpl(j);
        if (iM2476getWidthimpl == getWidth() && iM2475getHeightimpl == getHeight()) {
            return;
        }
        setPivotX(TransformOrigin.m1429getPivotFractionXimpl(this.mTransformOrigin) * iM2476getWidthimpl);
        setPivotY(TransformOrigin.m1430getPivotFractionYimpl(this.mTransformOrigin) * iM2475getHeightimpl);
        updateOutlineResolver();
        layout(getLeft(), getTop(), getLeft() + iM2476getWidthimpl, getTop() + iM2475getHeightimpl);
        resetClipBounds();
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: move--gyyYBs */
    public void mo1900movegyyYBs(long j) {
        int iM2457getXimpl = IntOffset.m2457getXimpl(j);
        if (iM2457getXimpl != getLeft()) {
            offsetLeftAndRight(iM2457getXimpl - getLeft());
            this.matrixCache.invalidate();
        }
        int iM2458getYimpl = IntOffset.m2458getYimpl(j);
        if (iM2458getYimpl != getTop()) {
            offsetTopAndBottom(iM2458getYimpl - getTop());
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void drawLayer(Canvas canvas, GraphicsLayer graphicsLayer) {
        boolean z = getElevation() > 0.0f;
        this.drawnWithZ = z;
        if (z) {
            canvas.enableZ();
        }
        this.container.drawChild$ui_release(canvas, this, getDrawingTime());
        if (this.drawnWithZ) {
            canvas.disableZ();
        }
    }

    @Override // android.view.View
    protected void dispatchDraw(android.graphics.Canvas canvas) {
        boolean z;
        CanvasHolder canvasHolder = this.canvasHolder;
        android.graphics.Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
        canvasHolder.getAndroidCanvas().setInternalCanvas(canvas);
        AndroidCanvas androidCanvas = canvasHolder.getAndroidCanvas();
        if (getManualClipPath() == null && canvas.isHardwareAccelerated()) {
            z = false;
        } else {
            androidCanvas.save();
            this.outlineResolver.clipToOutline(androidCanvas);
            z = true;
        }
        Function2 function2 = this.drawBlock;
        if (function2 != null) {
            function2.invoke(androidCanvas, null);
        }
        if (z) {
            androidCanvas.restore();
        }
        canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
        setInvalidated(false);
    }

    @Override // android.view.View, androidx.compose.ui.node.OwnedLayer
    public void invalidate() {
        if (this.isInvalidated) {
            return;
        }
        setInvalidated(true);
        super.invalidate();
        this.ownerView.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void destroy() {
        setInvalidated(false);
        this.ownerView.requestClearInvalidObservations();
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.ownerView.recycle$ui_release(this);
        this.container.removeViewInLayout(this);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateDisplayList() {
        if (!this.isInvalidated || shouldUseDispatchDraw) {
            return;
        }
        Companion.updateDisplayList(this);
        setInvalidated(false);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: mapOffset-8S9VItk */
    public long mo1899mapOffset8S9VItk(long j, boolean z) {
        if (z) {
            float[] fArrM1973calculateInverseMatrixbWbORWo = this.matrixCache.m1973calculateInverseMatrixbWbORWo(this);
            return fArrM1973calculateInverseMatrixbWbORWo != null ? androidx.compose.ui.graphics.Matrix.m1359mapMKHz9U(fArrM1973calculateInverseMatrixbWbORWo, j) : Offset.Companion.m1169getInfiniteF1C5BW0();
        }
        return androidx.compose.ui.graphics.Matrix.m1359mapMKHz9U(this.matrixCache.m1974calculateMatrixGrdbGEg(this), j);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void mapBounds(MutableRect mutableRect, boolean z) {
        if (z) {
            float[] fArrM1973calculateInverseMatrixbWbORWo = this.matrixCache.m1973calculateInverseMatrixbWbORWo(this);
            if (fArrM1973calculateInverseMatrixbWbORWo != null) {
                androidx.compose.ui.graphics.Matrix.m1360mapimpl(fArrM1973calculateInverseMatrixbWbORWo, mutableRect);
                return;
            } else {
                mutableRect.set(0.0f, 0.0f, 0.0f, 0.0f);
                return;
            }
        }
        androidx.compose.ui.graphics.Matrix.m1360mapimpl(this.matrixCache.m1974calculateMatrixGrdbGEg(this), mutableRect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void reuseLayer(Function2 function2, Function0 function0) {
        this.container.addView(this);
        this.clipToBounds = false;
        this.drawnWithZ = false;
        this.mTransformOrigin = TransformOrigin.Companion.m1434getCenterSzJe1aQ();
        this.drawBlock = function2;
        this.invalidateParentLayer = function0;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: transform-58bKbWc */
    public void mo1902transform58bKbWc(float[] fArr) {
        androidx.compose.ui.graphics.Matrix.m1367timesAssign58bKbWc(fArr, this.matrixCache.m1974calculateMatrixGrdbGEg(this));
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: inverseTransform-58bKbWc */
    public void mo1897inverseTransform58bKbWc(float[] fArr) {
        float[] fArrM1973calculateInverseMatrixbWbORWo = this.matrixCache.m1973calculateInverseMatrixbWbORWo(this);
        if (fArrM1973calculateInverseMatrixbWbORWo != null) {
            androidx.compose.ui.graphics.Matrix.m1367timesAssign58bKbWc(fArr, fArrM1973calculateInverseMatrixbWbORWo);
        }
    }

    /* JADX INFO: compiled from: ViewLayer.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getHasRetrievedMethod() {
            return ViewLayer.hasRetrievedMethod;
        }

        public final boolean getShouldUseDispatchDraw() {
            return ViewLayer.shouldUseDispatchDraw;
        }

        public final void setShouldUseDispatchDraw$ui_release(boolean z) {
            ViewLayer.shouldUseDispatchDraw = z;
        }

        public final void updateDisplayList(View view) {
            try {
                if (!getHasRetrievedMethod()) {
                    ViewLayer.hasRetrievedMethod = true;
                    if (Build.VERSION.SDK_INT < 28) {
                        ViewLayer.updateDisplayListIfDirtyMethod = View.class.getDeclaredMethod("updateDisplayListIfDirty", null);
                        ViewLayer.recreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
                    } else {
                        ViewLayer.updateDisplayListIfDirtyMethod = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass()).invoke(View.class, "updateDisplayListIfDirty", new Class[0]);
                        ViewLayer.recreateDisplayList = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(View.class, "mRecreateDisplayList");
                    }
                    Method method = ViewLayer.updateDisplayListIfDirtyMethod;
                    if (method != null) {
                        method.setAccessible(true);
                    }
                    Field field = ViewLayer.recreateDisplayList;
                    if (field != null) {
                        field.setAccessible(true);
                    }
                }
                Field field2 = ViewLayer.recreateDisplayList;
                if (field2 != null) {
                    field2.setBoolean(view, true);
                }
                Method method2 = ViewLayer.updateDisplayListIfDirtyMethod;
                if (method2 != null) {
                    method2.invoke(view, null);
                }
            } catch (Throwable unused) {
                setShouldUseDispatchDraw$ui_release(true);
            }
        }
    }
}
