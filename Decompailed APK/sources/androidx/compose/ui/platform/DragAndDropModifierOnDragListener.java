package androidx.compose.ui.platform;

import android.view.DragEvent;
import android.view.View;
import androidx.collection.ArraySet;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropManager;
import androidx.compose.ui.draganddrop.DragAndDropModifierNode;
import androidx.compose.ui.draganddrop.DragAndDropNode;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.node.ModifierNodeElement;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: AndroidComposeView.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class DragAndDropModifierOnDragListener implements View.OnDragListener, DragAndDropManager {
    private final Function3 startDrag;
    private final DragAndDropNode rootDragAndDropNode = new DragAndDropNode(new Function1() { // from class: androidx.compose.ui.platform.DragAndDropModifierOnDragListener$rootDragAndDropNode$1
        @Override // kotlin.jvm.functions.Function1
        public final DragAndDropTarget invoke(DragAndDropEvent dragAndDropEvent) {
            return null;
        }
    });
    private final ArraySet interestedNodes = new ArraySet(0, 1, null);
    private final Modifier modifier = new ModifierNodeElement() { // from class: androidx.compose.ui.platform.DragAndDropModifierOnDragListener$modifier$1
        public boolean equals(Object other) {
            return other == this;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(DragAndDropNode node) {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public DragAndDropNode getNode() {
            return this.this$0.rootDragAndDropNode;
        }

        public int hashCode() {
            return this.this$0.rootDragAndDropNode.hashCode();
        }
    };

    public DragAndDropModifierOnDragListener(Function3 function3) {
        this.startDrag = function3;
    }

    public Modifier getModifier() {
        return this.modifier;
    }

    @Override // android.view.View.OnDragListener
    public boolean onDrag(View view, DragEvent dragEvent) {
        DragAndDropEvent dragAndDropEvent = new DragAndDropEvent(dragEvent);
        switch (dragEvent.getAction()) {
            case 1:
                boolean zAcceptDragAndDropTransfer = this.rootDragAndDropNode.acceptDragAndDropTransfer(dragAndDropEvent);
                Iterator<E> it = this.interestedNodes.iterator();
                while (it.hasNext()) {
                    ((DragAndDropModifierNode) it.next()).onStarted(dragAndDropEvent);
                }
                break;
            case 2:
                this.rootDragAndDropNode.onMoved(dragAndDropEvent);
                break;
            case 4:
                this.rootDragAndDropNode.onEnded(dragAndDropEvent);
                break;
            case 5:
                this.rootDragAndDropNode.onEntered(dragAndDropEvent);
                break;
            case 6:
                this.rootDragAndDropNode.onExited(dragAndDropEvent);
                break;
        }
        return false;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropManager
    public void registerNodeInterest(DragAndDropModifierNode dragAndDropModifierNode) {
        this.interestedNodes.add(dragAndDropModifierNode);
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropManager
    public boolean isInterestedNode(DragAndDropModifierNode dragAndDropModifierNode) {
        return this.interestedNodes.contains(dragAndDropModifierNode);
    }
}
