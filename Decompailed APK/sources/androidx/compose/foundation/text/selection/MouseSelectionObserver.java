package androidx.compose.foundation.text.selection;

/* JADX INFO: compiled from: SelectionGestures.kt */
/* JADX INFO: loaded from: classes.dex */
public interface MouseSelectionObserver {
    /* JADX INFO: renamed from: onDrag-3MmeM6k, reason: not valid java name */
    boolean mo535onDrag3MmeM6k(long j, SelectionAdjustment selectionAdjustment);

    void onDragDone();

    /* JADX INFO: renamed from: onExtend-k-4lQ0M, reason: not valid java name */
    boolean mo536onExtendk4lQ0M(long j);

    /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
    boolean mo537onExtendDragk4lQ0M(long j);

    /* JADX INFO: renamed from: onStart-3MmeM6k, reason: not valid java name */
    boolean mo538onStart3MmeM6k(long j, SelectionAdjustment selectionAdjustment);
}
