package androidx.compose.ui.text.android.selection;

/* JADX INFO: compiled from: SegmentFinder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class GraphemeClusterSegmentFinder implements SegmentFinder {
    public abstract int next(int i);

    public abstract int previous(int i);

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int previousStartBoundary(int i) {
        return previous(i);
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int previousEndBoundary(int i) {
        int iPrevious = previous(i);
        if (iPrevious == -1 || previous(iPrevious) == -1) {
            return -1;
        }
        return iPrevious;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int nextStartBoundary(int i) {
        int next = next(i);
        if (next == -1 || next(next) == -1) {
            return -1;
        }
        return next;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int nextEndBoundary(int i) {
        return next(i);
    }
}
