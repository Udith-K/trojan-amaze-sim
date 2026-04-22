package androidx.compose.ui.node;

import androidx.compose.ui.semantics.SemanticsPropertyReceiver;

/* JADX INFO: compiled from: SemanticsModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public interface SemanticsModifierNode extends DelegatableNode {
    void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver);

    boolean getShouldClearDescendantSemantics();

    boolean getShouldMergeDescendantSemantics();

    /* JADX INFO: renamed from: androidx.compose.ui.node.SemanticsModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SemanticsModifierNode.kt */
    public abstract /* synthetic */ class CC {
        public static boolean $default$getShouldClearDescendantSemantics(SemanticsModifierNode semanticsModifierNode) {
            return false;
        }

        public static boolean $default$getShouldMergeDescendantSemantics(SemanticsModifierNode semanticsModifierNode) {
            return false;
        }
    }
}
