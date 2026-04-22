package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;

/* JADX INFO: compiled from: Canvas.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Canvas {
    /* JADX INFO: renamed from: clipPath-mtrdD-E */
    void mo1207clipPathmtrdDE(Path path, int i);

    /* JADX INFO: renamed from: clipRect-N_I0leg */
    void mo1208clipRectN_I0leg(float f, float f2, float f3, float f4, int i);

    /* JADX INFO: renamed from: clipRect-mtrdD-E */
    void mo1209clipRectmtrdDE(Rect rect, int i);

    /* JADX INFO: renamed from: concat-58bKbWc */
    void mo1210concat58bKbWc(float[] fArr);

    void disableZ();

    void drawArc(float f, float f2, float f3, float f4, float f5, float f6, boolean z, Paint paint);

    /* JADX INFO: renamed from: drawCircle-9KIMszo */
    void mo1211drawCircle9KIMszo(long j, float f, Paint paint);

    /* JADX INFO: renamed from: drawImage-d-4ec7I */
    void mo1212drawImaged4ec7I(ImageBitmap imageBitmap, long j, Paint paint);

    /* JADX INFO: renamed from: drawImageRect-HPBpro0 */
    void mo1213drawImageRectHPBpro0(ImageBitmap imageBitmap, long j, long j2, long j3, long j4, Paint paint);

    /* JADX INFO: renamed from: drawLine-Wko1d7g */
    void mo1214drawLineWko1d7g(long j, long j2, Paint paint);

    void drawPath(Path path, Paint paint);

    void drawRect(float f, float f2, float f3, float f4, Paint paint);

    void drawRect(Rect rect, Paint paint);

    void drawRoundRect(float f, float f2, float f3, float f4, float f5, float f6, Paint paint);

    void enableZ();

    void restore();

    void rotate(float f);

    void save();

    void saveLayer(Rect rect, Paint paint);

    void scale(float f, float f2);

    void translate(float f, float f2);

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.Canvas$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Canvas.kt */
    public abstract /* synthetic */ class CC {
        /* JADX INFO: renamed from: clipRect-mtrdD-E$default, reason: not valid java name */
        public static /* synthetic */ void m1285clipRectmtrdDE$default(Canvas canvas, Rect rect, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipRect-mtrdD-E");
            }
            if ((i2 & 2) != 0) {
                i = ClipOp.Companion.m1289getIntersectrtfAjoo();
            }
            canvas.mo1209clipRectmtrdDE(rect, i);
        }

        /* JADX INFO: renamed from: clipRect-N_I0leg$default, reason: not valid java name */
        public static /* synthetic */ void m1284clipRectN_I0leg$default(Canvas canvas, float f, float f2, float f3, float f4, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipRect-N_I0leg");
            }
            if ((i2 & 16) != 0) {
                i = ClipOp.Companion.m1289getIntersectrtfAjoo();
            }
            canvas.mo1208clipRectN_I0leg(f, f2, f3, f4, i);
        }

        /* JADX INFO: renamed from: clipPath-mtrdD-E$default, reason: not valid java name */
        public static /* synthetic */ void m1283clipPathmtrdDE$default(Canvas canvas, Path path, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipPath-mtrdD-E");
            }
            if ((i2 & 2) != 0) {
                i = ClipOp.Companion.m1289getIntersectrtfAjoo();
            }
            canvas.mo1207clipPathmtrdDE(path, i);
        }
    }
}
