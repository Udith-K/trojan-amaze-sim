package androidx.compose.ui.text.android.selection;

/* JADX INFO: compiled from: SegmentFinder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public interface SegmentFinder {
    int nextEndBoundary(int i);

    int nextStartBoundary(int i);

    int previousEndBoundary(int i);

    int previousStartBoundary(int i);
}
