package androidx.compose.ui.graphics;

import android.graphics.Path;
import android.graphics.RectF;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidPath.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidPath implements Path {
    private final android.graphics.Path internalPath;
    private android.graphics.Matrix mMatrix;
    private float[] radii;
    private RectF rectF;

    public AndroidPath(android.graphics.Path path) {
        this.internalPath = path;
    }

    public /* synthetic */ AndroidPath(android.graphics.Path path, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new android.graphics.Path() : path);
    }

    public final android.graphics.Path getInternalPath() {
        return this.internalPath;
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: getFillType-Rg-k1Os, reason: not valid java name */
    public int mo1240getFillTypeRgk1Os() {
        if (this.internalPath.getFillType() == Path.FillType.EVEN_ODD) {
            return PathFillType.Companion.m1387getEvenOddRgk1Os();
        }
        return PathFillType.Companion.m1388getNonZeroRgk1Os();
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: setFillType-oQ8Xj4U, reason: not valid java name */
    public void mo1242setFillTypeoQ8Xj4U(int i) {
        Path.FillType fillType;
        android.graphics.Path path = this.internalPath;
        if (PathFillType.m1385equalsimpl0(i, PathFillType.Companion.m1387getEvenOddRgk1Os())) {
            fillType = Path.FillType.EVEN_ODD;
        } else {
            fillType = Path.FillType.WINDING;
        }
        path.setFillType(fillType);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void moveTo(float f, float f2) {
        this.internalPath.moveTo(f, f2);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void lineTo(float f, float f2) {
        this.internalPath.lineTo(f, f2);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void relativeLineTo(float f, float f2) {
        this.internalPath.rLineTo(f, f2);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void cubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        this.internalPath.cubicTo(f, f2, f3, f4, f5, f6);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void relativeCubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        this.internalPath.rCubicTo(f, f2, f3, f4, f5, f6);
    }

    @Override // androidx.compose.ui.graphics.Path
    public void addRect(Rect rect, Path.Direction direction) {
        validateRectangle(rect);
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        RectF rectF = this.rectF;
        Intrinsics.checkNotNull(rectF);
        rectF.set(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom());
        android.graphics.Path path = this.internalPath;
        RectF rectF2 = this.rectF;
        Intrinsics.checkNotNull(rectF2);
        path.addRect(rectF2, AndroidPath_androidKt.toPlatformPathDirection(direction));
    }

    @Override // androidx.compose.ui.graphics.Path
    public void addRoundRect(RoundRect roundRect, Path.Direction direction) {
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        RectF rectF = this.rectF;
        Intrinsics.checkNotNull(rectF);
        rectF.set(roundRect.getLeft(), roundRect.getTop(), roundRect.getRight(), roundRect.getBottom());
        if (this.radii == null) {
            this.radii = new float[8];
        }
        float[] fArr = this.radii;
        Intrinsics.checkNotNull(fArr);
        fArr[0] = CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs());
        fArr[1] = CornerRadius.m1146getYimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs());
        fArr[2] = CornerRadius.m1145getXimpl(roundRect.m1187getTopRightCornerRadiuskKHJgLs());
        fArr[3] = CornerRadius.m1146getYimpl(roundRect.m1187getTopRightCornerRadiuskKHJgLs());
        fArr[4] = CornerRadius.m1145getXimpl(roundRect.m1185getBottomRightCornerRadiuskKHJgLs());
        fArr[5] = CornerRadius.m1146getYimpl(roundRect.m1185getBottomRightCornerRadiuskKHJgLs());
        fArr[6] = CornerRadius.m1145getXimpl(roundRect.m1184getBottomLeftCornerRadiuskKHJgLs());
        fArr[7] = CornerRadius.m1146getYimpl(roundRect.m1184getBottomLeftCornerRadiuskKHJgLs());
        android.graphics.Path path = this.internalPath;
        RectF rectF2 = this.rectF;
        Intrinsics.checkNotNull(rectF2);
        float[] fArr2 = this.radii;
        Intrinsics.checkNotNull(fArr2);
        path.addRoundRect(rectF2, fArr2, AndroidPath_androidKt.toPlatformPathDirection(direction));
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: addPath-Uv8p0NA, reason: not valid java name */
    public void mo1239addPathUv8p0NA(Path path, long j) {
        android.graphics.Path path2 = this.internalPath;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        path2.addPath(((AndroidPath) path).getInternalPath(), Offset.m1159getXimpl(j), Offset.m1160getYimpl(j));
    }

    @Override // androidx.compose.ui.graphics.Path
    public void close() {
        this.internalPath.close();
    }

    @Override // androidx.compose.ui.graphics.Path
    public void reset() {
        this.internalPath.reset();
    }

    @Override // androidx.compose.ui.graphics.Path
    public void rewind() {
        this.internalPath.rewind();
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: translate-k-4lQ0M, reason: not valid java name */
    public void mo1243translatek4lQ0M(long j) {
        android.graphics.Matrix matrix = this.mMatrix;
        if (matrix == null) {
            this.mMatrix = new android.graphics.Matrix();
        } else {
            Intrinsics.checkNotNull(matrix);
            matrix.reset();
        }
        android.graphics.Matrix matrix2 = this.mMatrix;
        Intrinsics.checkNotNull(matrix2);
        matrix2.setTranslate(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j));
        android.graphics.Path path = this.internalPath;
        android.graphics.Matrix matrix3 = this.mMatrix;
        Intrinsics.checkNotNull(matrix3);
        path.transform(matrix3);
    }

    @Override // androidx.compose.ui.graphics.Path
    public Rect getBounds() {
        if (this.rectF == null) {
            this.rectF = new RectF();
        }
        RectF rectF = this.rectF;
        Intrinsics.checkNotNull(rectF);
        this.internalPath.computeBounds(rectF, true);
        return new Rect(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    @Override // androidx.compose.ui.graphics.Path
    /* JADX INFO: renamed from: op-N5in7k0, reason: not valid java name */
    public boolean mo1241opN5in7k0(Path path, Path path2, int i) {
        Path.Op op;
        PathOperation.Companion companion = PathOperation.Companion;
        if (PathOperation.m1390equalsimpl0(i, companion.m1391getDifferenceb3I0S0c())) {
            op = Path.Op.DIFFERENCE;
        } else if (PathOperation.m1390equalsimpl0(i, companion.m1392getIntersectb3I0S0c())) {
            op = Path.Op.INTERSECT;
        } else if (PathOperation.m1390equalsimpl0(i, companion.m1393getReverseDifferenceb3I0S0c())) {
            op = Path.Op.REVERSE_DIFFERENCE;
        } else {
            op = PathOperation.m1390equalsimpl0(i, companion.m1394getUnionb3I0S0c()) ? Path.Op.UNION : Path.Op.XOR;
        }
        android.graphics.Path path3 = this.internalPath;
        if (!(path instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        android.graphics.Path internalPath = ((AndroidPath) path).getInternalPath();
        if (!(path2 instanceof AndroidPath)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        return path3.op(internalPath, ((AndroidPath) path2).getInternalPath(), op);
    }

    @Override // androidx.compose.ui.graphics.Path
    public boolean isConvex() {
        return this.internalPath.isConvex();
    }

    @Override // androidx.compose.ui.graphics.Path
    public boolean isEmpty() {
        return this.internalPath.isEmpty();
    }

    private final void validateRectangle(Rect rect) {
        if (Float.isNaN(rect.getLeft()) || Float.isNaN(rect.getTop()) || Float.isNaN(rect.getRight()) || Float.isNaN(rect.getBottom())) {
            AndroidPath_androidKt.throwIllegalStateException("Invalid rectangle, make sure no value is NaN");
        }
    }
}
