package androidx.compose.foundation.contextmenu;

/* JADX INFO: compiled from: ContextMenuPopupPositionProvider.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContextMenuPopupPositionProvider_androidKt {
    private static final int alignPopupStartEdgeToPosition(int i, int i2, boolean z) {
        return z ? i : i - i2;
    }

    private static final int alignStartEdges(int i, int i2, boolean z) {
        if (z) {
            return 0;
        }
        return i2 - i;
    }

    private static final boolean popupFitsBetweenPositionAndStartEdge(int i, int i2, int i3, boolean z) {
        if (z) {
            if (i2 > i) {
                return false;
            }
        } else if (i3 - i2 <= i) {
            return false;
        }
        return true;
    }

    public static /* synthetic */ int alignPopupAxis$default(int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            z = true;
        }
        return alignPopupAxis(i, i2, i3, z);
    }

    public static final int alignPopupAxis(int i, int i2, int i3, boolean z) {
        if (i2 >= i3) {
            return alignStartEdges(i2, i3, z);
        }
        if (popupFitsBetweenPositionAndEndEdge(i, i2, i3, z)) {
            return alignPopupStartEdgeToPosition(i, i2, z);
        }
        if (popupFitsBetweenPositionAndStartEdge(i, i2, i3, z)) {
            return alignPopupEndEdgeToPosition(i, i2, z);
        }
        return alignEndEdges(i2, i3, z);
    }

    private static final boolean popupFitsBetweenPositionAndEndEdge(int i, int i2, int i3, boolean z) {
        return popupFitsBetweenPositionAndStartEdge(i, i2, i3, !z);
    }

    private static final int alignPopupEndEdgeToPosition(int i, int i2, boolean z) {
        return alignPopupStartEdgeToPosition(i, i2, !z);
    }

    private static final int alignEndEdges(int i, int i2, boolean z) {
        return alignStartEdges(i, i2, !z);
    }
}
