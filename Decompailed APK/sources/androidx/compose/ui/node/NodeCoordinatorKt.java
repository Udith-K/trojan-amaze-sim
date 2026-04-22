package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;

/* JADX INFO: compiled from: NodeCoordinator.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class NodeCoordinatorKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: nextUntil-hw7D004, reason: not valid java name */
    public static final Modifier.Node m1894nextUntilhw7D004(DelegatableNode delegatableNode, int i, int i2) {
        Modifier.Node child$ui_release = delegatableNode.getNode().getChild$ui_release();
        if (child$ui_release == null || (child$ui_release.getAggregateChildKindSet$ui_release() & i) == 0) {
            return null;
        }
        while (child$ui_release != null) {
            int kindSet$ui_release = child$ui_release.getKindSet$ui_release();
            if ((kindSet$ui_release & i2) != 0) {
                return null;
            }
            if ((kindSet$ui_release & i) != 0) {
                return child$ui_release;
            }
            child$ui_release = child$ui_release.getChild$ui_release();
        }
        return null;
    }
}
