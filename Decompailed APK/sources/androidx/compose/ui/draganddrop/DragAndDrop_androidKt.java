package androidx.compose.ui.draganddrop;

import androidx.compose.ui.geometry.OffsetKt;

/* JADX INFO: compiled from: DragAndDrop.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DragAndDrop_androidKt {
    public static final long getPositionInRoot(DragAndDropEvent dragAndDropEvent) {
        return OffsetKt.Offset(dragAndDropEvent.getDragEvent$ui_release().getX(), dragAndDropEvent.getDragEvent$ui_release().getY());
    }
}
