package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.TraversableNodeKt;

/* JADX INFO: compiled from: BringIntoViewResponder.kt */
/* JADX INFO: loaded from: classes.dex */
abstract /* synthetic */ class BringIntoViewRequesterKt__BringIntoViewResponderKt {
    public static final BringIntoViewParent findBringIntoViewParent(DelegatableNode delegatableNode) {
        if (!delegatableNode.getNode().isAttached()) {
            return null;
        }
        BringIntoViewParent bringIntoViewParent = (BringIntoViewParent) TraversableNodeKt.findNearestAncestor(delegatableNode, BringIntoViewResponderNode.TraverseKey);
        return bringIntoViewParent == null ? BringIntoViewResponder_androidKt.defaultBringIntoViewParent(delegatableNode) : bringIntoViewParent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect localRectOf$BringIntoViewRequesterKt__BringIntoViewResponderKt(LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2, Rect rect) {
        return rect.m1181translatek4lQ0M(layoutCoordinates.localBoundingBoxOf(layoutCoordinates2, false).m1180getTopLeftF1C5BW0());
    }
}
