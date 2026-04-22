package androidx.compose.ui.semantics;

import androidx.compose.ui.node.LayoutNode;

/* JADX INFO: compiled from: SemanticsOwner.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SemanticsOwner {
    private final EmptySemanticsModifier outerSemanticsNode;
    private final LayoutNode rootNode;

    public SemanticsOwner(LayoutNode layoutNode, EmptySemanticsModifier emptySemanticsModifier) {
        this.rootNode = layoutNode;
        this.outerSemanticsNode = emptySemanticsModifier;
    }

    public final SemanticsNode getUnmergedRootSemanticsNode() {
        return new SemanticsNode(this.outerSemanticsNode, false, this.rootNode, new SemanticsConfiguration());
    }
}
