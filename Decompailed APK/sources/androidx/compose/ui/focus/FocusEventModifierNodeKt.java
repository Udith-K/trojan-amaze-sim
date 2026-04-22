package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: FocusEventModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FocusEventModifierNodeKt {

    /* JADX INFO: compiled from: FocusEventModifierNode.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void invalidateFocusEvent(FocusEventModifierNode focusEventModifierNode) {
        DelegatableNodeKt.requireOwner(focusEventModifierNode).getFocusOwner().scheduleInvalidation(focusEventModifierNode);
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x0095, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.focus.FocusState getFocusState(androidx.compose.ui.focus.FocusEventModifierNode r12) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusEventModifierNodeKt.getFocusState(androidx.compose.ui.focus.FocusEventModifierNode):androidx.compose.ui.focus.FocusState");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v5 */
    public static final void refreshFocusEventNodes(FocusTargetNode focusTargetNode) {
        NodeChain nodes$ui_release;
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(PKIFailureInfo.certConfirmed);
        int iM1895constructorimpl2 = NodeKind.m1895constructorimpl(1024);
        Modifier.Node node = focusTargetNode.getNode();
        int i = iM1895constructorimpl | iM1895constructorimpl2;
        if (!focusTargetNode.getNode().isAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = focusTargetNode.getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes$ui_release().getHead$ui_release().getAggregateChildKindSet$ui_release() & i) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet$ui_release() & i) != 0) {
                        if (node2 != node && (node2.getKindSet$ui_release() & iM1895constructorimpl2) != 0) {
                            return;
                        }
                        if ((node2.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                            ?? Pop = node2;
                            ?? mutableVector = 0;
                            while (Pop != 0) {
                                if (!(Pop instanceof FocusEventModifierNode)) {
                                    if ((Pop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (Pop instanceof DelegatingNode)) {
                                        Modifier.Node delegate$ui_release = ((DelegatingNode) Pop).getDelegate$ui_release();
                                        int i2 = 0;
                                        Pop = Pop;
                                        mutableVector = mutableVector;
                                        while (delegate$ui_release != null) {
                                            if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                                i2++;
                                                mutableVector = mutableVector;
                                                if (i2 == 1) {
                                                    Pop = delegate$ui_release;
                                                } else {
                                                    if (mutableVector == 0) {
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    if (Pop != 0) {
                                                        mutableVector.add(Pop);
                                                        Pop = 0;
                                                    }
                                                    mutableVector.add(delegate$ui_release);
                                                }
                                            }
                                            delegate$ui_release = delegate$ui_release.getChild$ui_release();
                                            Pop = Pop;
                                            mutableVector = mutableVector;
                                        }
                                        if (i2 == 1) {
                                        }
                                    }
                                } else {
                                    FocusEventModifierNode focusEventModifierNode = (FocusEventModifierNode) Pop;
                                    focusEventModifierNode.onFocusEvent(getFocusState(focusEventModifierNode));
                                }
                                Pop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                    }
                    node2 = node2.getParent$ui_release();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            node2 = (layoutNodeRequireLayoutNode == null || (nodes$ui_release = layoutNodeRequireLayoutNode.getNodes$ui_release()) == null) ? null : nodes$ui_release.getTail$ui_release();
        }
    }
}
