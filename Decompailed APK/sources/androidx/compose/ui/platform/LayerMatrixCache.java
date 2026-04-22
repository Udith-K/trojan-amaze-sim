package androidx.compose.ui.platform;

import android.graphics.Matrix;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayerMatrixCache.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LayerMatrixCache {
    private Matrix androidMatrixCache;
    private final Function2 getMatrix;
    private float[] inverseMatrixCache;
    private boolean isDirty = true;
    private boolean isInverseDirty = true;
    private boolean isInverseValid = true;
    private float[] matrixCache;
    private Matrix previousAndroidMatrix;

    public LayerMatrixCache(Function2 function2) {
        this.getMatrix = function2;
    }

    public final void invalidate() {
        this.isDirty = true;
        this.isInverseDirty = true;
    }

    /* JADX INFO: renamed from: calculateMatrix-GrdbGEg, reason: not valid java name */
    public final float[] m1974calculateMatrixGrdbGEg(Object obj) {
        float[] fArrM1356constructorimpl$default = this.matrixCache;
        if (fArrM1356constructorimpl$default == null) {
            fArrM1356constructorimpl$default = androidx.compose.ui.graphics.Matrix.m1356constructorimpl$default(null, 1, null);
            this.matrixCache = fArrM1356constructorimpl$default;
        }
        if (!this.isDirty) {
            return fArrM1356constructorimpl$default;
        }
        Matrix matrix = this.androidMatrixCache;
        if (matrix == null) {
            matrix = new Matrix();
            this.androidMatrixCache = matrix;
        }
        this.getMatrix.invoke(obj, matrix);
        Matrix matrix2 = this.previousAndroidMatrix;
        if (matrix2 == null || !Intrinsics.areEqual(matrix, matrix2)) {
            AndroidMatrixConversions_androidKt.m1221setFromtUYjHk(fArrM1356constructorimpl$default, matrix);
            this.androidMatrixCache = matrix2;
            this.previousAndroidMatrix = matrix;
        }
        this.isDirty = false;
        return fArrM1356constructorimpl$default;
    }

    /* JADX INFO: renamed from: calculateInverseMatrix-bWbORWo, reason: not valid java name */
    public final float[] m1973calculateInverseMatrixbWbORWo(Object obj) {
        float[] fArrM1356constructorimpl$default = this.inverseMatrixCache;
        if (fArrM1356constructorimpl$default == null) {
            fArrM1356constructorimpl$default = androidx.compose.ui.graphics.Matrix.m1356constructorimpl$default(null, 1, null);
            this.inverseMatrixCache = fArrM1356constructorimpl$default;
        }
        if (this.isInverseDirty) {
            this.isInverseValid = InvertMatrixKt.m1972invertToJiSxe2E(m1974calculateMatrixGrdbGEg(obj), fArrM1356constructorimpl$default);
            this.isInverseDirty = false;
        }
        if (this.isInverseValid) {
            return fArrM1356constructorimpl$default;
        }
        return null;
    }
}
