package androidx.compose.ui.draganddrop;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNode$Companion$TraverseDescendantsAction;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.unit.IntSize;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: DragAndDropNode.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DragAndDropNodeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void dispatchEntered(DragAndDropTarget dragAndDropTarget, DragAndDropEvent dragAndDropEvent) {
        dragAndDropTarget.onEntered(dragAndDropEvent);
        dragAndDropTarget.onMoved(dragAndDropEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: contains-Uv8p0NA, reason: not valid java name */
    public static final boolean m1074containsUv8p0NA(DragAndDropModifierNode dragAndDropModifierNode, long j) {
        if (!dragAndDropModifierNode.getNode().isAttached()) {
            return false;
        }
        LayoutCoordinates coordinates = DelegatableNodeKt.requireLayoutNode(dragAndDropModifierNode).getCoordinates();
        if (!coordinates.isAttached()) {
            return false;
        }
        long jMo1745getSizeYbymL2g = coordinates.mo1745getSizeYbymL2g();
        int iM2476getWidthimpl = IntSize.m2476getWidthimpl(jMo1745getSizeYbymL2g);
        int iM2475getHeightimpl = IntSize.m2475getHeightimpl(jMo1745getSizeYbymL2g);
        long jPositionInRoot = LayoutCoordinatesKt.positionInRoot(coordinates);
        float fM1159getXimpl = Offset.m1159getXimpl(jPositionInRoot);
        float fM1160getYimpl = Offset.m1160getYimpl(jPositionInRoot);
        float f = iM2476getWidthimpl + fM1159getXimpl;
        float f2 = iM2475getHeightimpl + fM1160getYimpl;
        float fM1159getXimpl2 = Offset.m1159getXimpl(j);
        if (fM1159getXimpl > fM1159getXimpl2 || fM1159getXimpl2 > f) {
            return false;
        }
        float fM1160getYimpl2 = Offset.m1160getYimpl(j);
        return fM1160getYimpl <= fM1160getYimpl2 && fM1160getYimpl2 <= f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void traverseSelfAndDescendants(TraversableNode traversableNode, Function1 function1) {
        if (function1.invoke(traversableNode) != TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal) {
            return;
        }
        TraversableNodeKt.traverseDescendants(traversableNode, function1);
    }
}
