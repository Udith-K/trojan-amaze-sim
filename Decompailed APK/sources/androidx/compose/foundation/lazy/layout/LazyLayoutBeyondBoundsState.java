package androidx.compose.foundation.lazy.layout;

/* JADX INFO: compiled from: LazyLayoutBeyondBoundsState.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LazyLayoutBeyondBoundsState {
    int getFirstPlacedIndex();

    boolean getHasVisibleItems();

    int getItemCount();

    int getLastPlacedIndex();

    void remeasure();
}
