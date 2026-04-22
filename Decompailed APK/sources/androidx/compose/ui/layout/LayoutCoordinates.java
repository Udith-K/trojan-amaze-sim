package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;

/* JADX INFO: compiled from: LayoutCoordinates.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LayoutCoordinates {
    LayoutCoordinates getParentLayoutCoordinates();

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name */
    long mo1745getSizeYbymL2g();

    boolean isAttached();

    Rect localBoundingBoxOf(LayoutCoordinates layoutCoordinates, boolean z);

    /* JADX INFO: renamed from: localPositionOf-R5De75A, reason: not valid java name */
    long mo1746localPositionOfR5De75A(LayoutCoordinates layoutCoordinates, long j);

    /* JADX INFO: renamed from: localPositionOf-S_NoaFU, reason: not valid java name */
    long mo1747localPositionOfS_NoaFU(LayoutCoordinates layoutCoordinates, long j, boolean z);

    /* JADX INFO: renamed from: localToRoot-MK-Hz9U, reason: not valid java name */
    long mo1748localToRootMKHz9U(long j);

    /* JADX INFO: renamed from: localToWindow-MK-Hz9U, reason: not valid java name */
    long mo1749localToWindowMKHz9U(long j);

    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U, reason: not valid java name */
    long mo1750screenToLocalMKHz9U(long j);

    /* JADX INFO: renamed from: transformFrom-EL8BTi8, reason: not valid java name */
    void mo1751transformFromEL8BTi8(LayoutCoordinates layoutCoordinates, float[] fArr);

    /* JADX INFO: renamed from: transformToScreen-58bKbWc, reason: not valid java name */
    void mo1752transformToScreen58bKbWc(float[] fArr);

    /* JADX INFO: renamed from: windowToLocal-MK-Hz9U, reason: not valid java name */
    long mo1753windowToLocalMKHz9U(long j);

    /* JADX INFO: renamed from: androidx.compose.ui.layout.LayoutCoordinates$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LayoutCoordinates.kt */
    public abstract /* synthetic */ class CC {
        public static /* synthetic */ Rect localBoundingBoxOf$default(LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: localBoundingBoxOf");
            }
            if ((i & 2) != 0) {
                z = true;
            }
            return layoutCoordinates.localBoundingBoxOf(layoutCoordinates2, z);
        }
    }
}
