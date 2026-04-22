package androidx.compose.ui.layout;

/* JADX INFO: compiled from: PinnableContainer.kt */
/* JADX INFO: loaded from: classes.dex */
public interface PinnableContainer {

    /* JADX INFO: compiled from: PinnableContainer.kt */
    public interface PinnedHandle {
        void release();
    }

    PinnedHandle pin();
}
