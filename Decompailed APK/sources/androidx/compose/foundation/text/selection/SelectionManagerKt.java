package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;

/* JADX INFO: compiled from: SelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SelectionManagerKt {
    private static final Rect invertedInfiniteRect = new Rect(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    public static final Rect visibleBounds(LayoutCoordinates layoutCoordinates) {
        Rect rectBoundsInWindow = LayoutCoordinatesKt.boundsInWindow(layoutCoordinates);
        return RectKt.m1182Rect0a9Yr6o(layoutCoordinates.mo1753windowToLocalMKHz9U(rectBoundsInWindow.m1180getTopLeftF1C5BW0()), layoutCoordinates.mo1753windowToLocalMKHz9U(rectBoundsInWindow.m1177getBottomRightF1C5BW0()));
    }

    /* JADX INFO: renamed from: containsInclusive-Uv8p0NA, reason: not valid java name */
    public static final boolean m547containsInclusiveUv8p0NA(Rect rect, long j) {
        float left = rect.getLeft();
        float right = rect.getRight();
        float fM1159getXimpl = Offset.m1159getXimpl(j);
        if (left <= fM1159getXimpl && fM1159getXimpl <= right) {
            float top = rect.getTop();
            float bottom = rect.getBottom();
            float fM1160getYimpl = Offset.m1160getYimpl(j);
            if (top <= fM1160getYimpl && fM1160getYimpl <= bottom) {
                return true;
            }
        }
        return false;
    }
}
