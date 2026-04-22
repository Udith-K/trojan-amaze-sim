package androidx.compose.ui.draganddrop;

/* JADX INFO: compiled from: DragAndDropManager.kt */
/* JADX INFO: loaded from: classes.dex */
public interface DragAndDropManager {
    boolean isInterestedNode(DragAndDropModifierNode dragAndDropModifierNode);

    void registerNodeInterest(DragAndDropModifierNode dragAndDropModifierNode);
}
