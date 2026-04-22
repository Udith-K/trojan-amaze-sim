package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BeyondBoundsLayout.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BeyondBoundsLayoutKt {
    /* JADX INFO: renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final Object m1087searchBeyondBoundsOMvw8(FocusTargetNode focusTargetNode, int i, Function1 function1) {
        Modifier.Node nodePop;
        BeyondBoundsLayout beyondBoundsLayoutParent;
        int iM1729getBeforehoxUOeE;
        NodeChain nodes$ui_release;
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(1024);
        if (!focusTargetNode.getNode().isAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent$ui_release = focusTargetNode.getNode().getParent$ui_release();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        loop0: while (true) {
            if (layoutNodeRequireLayoutNode == null) {
                nodePop = null;
                break;
            }
            if ((layoutNodeRequireLayoutNode.getNodes$ui_release().getHead$ui_release().getAggregateChildKindSet$ui_release() & iM1895constructorimpl) != 0) {
                while (parent$ui_release != null) {
                    if ((parent$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        nodePop = parent$ui_release;
                        MutableVector mutableVector = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                break loop0;
                            }
                            if ((nodePop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i2 = 0;
                                for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate$ui_release(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild$ui_release()) {
                                    if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                        i2++;
                                        if (i2 == 1) {
                                            nodePop = delegate$ui_release;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector.add(delegate$ui_release);
                                        }
                                    }
                                }
                                if (i2 == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent$ui_release = parent$ui_release.getParent$ui_release();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent$ui_release = (layoutNodeRequireLayoutNode == null || (nodes$ui_release = layoutNodeRequireLayoutNode.getNodes$ui_release()) == null) ? null : nodes$ui_release.getTail$ui_release();
        }
        FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
        if ((focusTargetNode2 != null && Intrinsics.areEqual(focusTargetNode2.getBeyondBoundsLayoutParent(), focusTargetNode.getBeyondBoundsLayoutParent())) || (beyondBoundsLayoutParent = focusTargetNode.getBeyondBoundsLayoutParent()) == null) {
            return null;
        }
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s())) {
            iM1729getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.Companion.m1727getAbovehoxUOeE();
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
            iM1729getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.Companion.m1730getBelowhoxUOeE();
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s())) {
            iM1729getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.Companion.m1731getLefthoxUOeE();
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
            iM1729getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.Companion.m1732getRighthoxUOeE();
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1099getNextdhqQ8s())) {
            iM1729getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.Companion.m1728getAfterhoxUOeE();
        } else {
            if (!FocusDirection.m1091equalsimpl0(i, companion.m1100getPreviousdhqQ8s())) {
                throw new IllegalStateException("Unsupported direction for beyond bounds layout");
            }
            iM1729getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.Companion.m1729getBeforehoxUOeE();
        }
        return beyondBoundsLayoutParent.mo330layouto7g1Pn8(iM1729getBeforehoxUOeE, function1);
    }
}
