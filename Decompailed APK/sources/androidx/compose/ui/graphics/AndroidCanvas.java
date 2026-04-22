package androidx.compose.ui.graphics;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Region;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidCanvas.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidCanvas implements Canvas {
    private Rect dstRect;
    private android.graphics.Canvas internalCanvas = AndroidCanvas_androidKt.EmptyCanvas;
    private Rect srcRect;

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: clipRect-mtrdD-E, reason: not valid java name */
    public /* synthetic */ void mo1209clipRectmtrdDE(androidx.compose.ui.geometry.Rect rect, int i) {
        mo1208clipRectN_I0leg(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), i);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public /* synthetic */ void drawRect(androidx.compose.ui.geometry.Rect rect, Paint paint) {
        drawRect(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint);
    }

    public final android.graphics.Canvas getInternalCanvas() {
        return this.internalCanvas;
    }

    public final void setInternalCanvas(android.graphics.Canvas canvas) {
        this.internalCanvas = canvas;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void save() {
        this.internalCanvas.save();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void restore() {
        this.internalCanvas.restore();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void saveLayer(androidx.compose.ui.geometry.Rect rect, Paint paint) {
        this.internalCanvas.saveLayer(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint.asFrameworkPaint(), 31);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void translate(float f, float f2) {
        this.internalCanvas.translate(f, f2);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void scale(float f, float f2) {
        this.internalCanvas.scale(f, f2);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void rotate(float f) {
        this.internalCanvas.rotate(f);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: concat-58bKbWc, reason: not valid java name */
    public void mo1210concat58bKbWc(float[] fArr) {
        if (MatrixKt.m1374isIdentity58bKbWc(fArr)) {
            return;
        }
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        AndroidMatrixConversions_androidKt.m1220setFromEL8BTi8(matrix, fArr);
        this.internalCanvas.concat(matrix);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: clipRect-N_I0leg, reason: not valid java name */
    public void mo1208clipRectN_I0leg(float f, float f2, float f3, float f4, int i) {
        this.internalCanvas.clipRect(f, f2, f3, f4, m1215toRegionOp7u2Bmg(i));
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: clipPath-mtrdD-E, reason: not valid java name */
    public void mo1207clipPathmtrdDE(Path path, int i) {
        android.graphics.Canvas canvas = this.internalCanvas;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.clipPath(((AndroidPath) path).getInternalPath(), m1215toRegionOp7u2Bmg(i));
    }

    /* JADX INFO: renamed from: toRegionOp--7u2Bmg, reason: not valid java name */
    public final Region.Op m1215toRegionOp7u2Bmg(int i) {
        return ClipOp.m1287equalsimpl0(i, ClipOp.Companion.m1288getDifferencertfAjoo()) ? Region.Op.DIFFERENCE : Region.Op.INTERSECT;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawLine-Wko1d7g, reason: not valid java name */
    public void mo1214drawLineWko1d7g(long j, long j2, Paint paint) {
        this.internalCanvas.drawLine(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j), Offset.m1159getXimpl(j2), Offset.m1160getYimpl(j2), paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawRect(float f, float f2, float f3, float f4, Paint paint) {
        this.internalCanvas.drawRect(f, f2, f3, f4, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawRoundRect(float f, float f2, float f3, float f4, float f5, float f6, Paint paint) {
        this.internalCanvas.drawRoundRect(f, f2, f3, f4, f5, f6, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawCircle-9KIMszo, reason: not valid java name */
    public void mo1211drawCircle9KIMszo(long j, float f, Paint paint) {
        this.internalCanvas.drawCircle(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j), f, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawArc(float f, float f2, float f3, float f4, float f5, float f6, boolean z, Paint paint) {
        this.internalCanvas.drawArc(f, f2, f3, f4, f5, f6, z, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawPath(Path path, Paint paint) {
        android.graphics.Canvas canvas = this.internalCanvas;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.drawPath(((AndroidPath) path).getInternalPath(), paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawImage-d-4ec7I, reason: not valid java name */
    public void mo1212drawImaged4ec7I(ImageBitmap imageBitmap, long j, Paint paint) {
        this.internalCanvas.drawBitmap(AndroidImageBitmap_androidKt.asAndroidBitmap(imageBitmap), Offset.m1159getXimpl(j), Offset.m1160getYimpl(j), paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawImageRect-HPBpro0, reason: not valid java name */
    public void mo1213drawImageRectHPBpro0(ImageBitmap imageBitmap, long j, long j2, long j3, long j4, Paint paint) {
        if (this.srcRect == null) {
            this.srcRect = new Rect();
            this.dstRect = new Rect();
        }
        android.graphics.Canvas canvas = this.internalCanvas;
        Bitmap bitmapAsAndroidBitmap = AndroidImageBitmap_androidKt.asAndroidBitmap(imageBitmap);
        Rect rect = this.srcRect;
        Intrinsics.checkNotNull(rect);
        rect.left = IntOffset.m2457getXimpl(j);
        rect.top = IntOffset.m2458getYimpl(j);
        rect.right = IntOffset.m2457getXimpl(j) + IntSize.m2476getWidthimpl(j2);
        rect.bottom = IntOffset.m2458getYimpl(j) + IntSize.m2475getHeightimpl(j2);
        Unit unit = Unit.INSTANCE;
        Rect rect2 = this.dstRect;
        Intrinsics.checkNotNull(rect2);
        rect2.left = IntOffset.m2457getXimpl(j3);
        rect2.top = IntOffset.m2458getYimpl(j3);
        rect2.right = IntOffset.m2457getXimpl(j3) + IntSize.m2476getWidthimpl(j4);
        rect2.bottom = IntOffset.m2458getYimpl(j3) + IntSize.m2475getHeightimpl(j4);
        canvas.drawBitmap(bitmapAsAndroidBitmap, rect, rect2, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void enableZ() {
        CanvasUtils.INSTANCE.enableZ(this.internalCanvas, true);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void disableZ() {
        CanvasUtils.INSTANCE.enableZ(this.internalCanvas, false);
    }
}
