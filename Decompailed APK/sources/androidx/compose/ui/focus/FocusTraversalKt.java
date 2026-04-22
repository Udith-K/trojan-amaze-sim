package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusTraversal.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FocusTraversalKt {

    /* JADX INFO: compiled from: FocusTraversal.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: customFocusSearch--OM-vw8, reason: not valid java name */
    public static final FocusRequester m1129customFocusSearchOMvw8(FocusTargetNode focusTargetNode, int i, LayoutDirection layoutDirection) {
        FocusRequester end;
        FocusProperties focusPropertiesFetchFocusProperties$ui_release = focusTargetNode.fetchFocusProperties$ui_release();
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1099getNextdhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getNext();
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1100getPreviousdhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getPrevious();
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getUp();
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getDown();
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s())) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
            if (i2 == 1) {
                end = focusPropertiesFetchFocusProperties$ui_release.getStart();
            } else {
                if (i2 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                end = focusPropertiesFetchFocusProperties$ui_release.getEnd();
            }
            if (end == FocusRequester.Companion.getDefault()) {
                end = null;
            }
            if (end == null) {
                return focusPropertiesFetchFocusProperties$ui_release.getLeft();
            }
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
            int i3 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
            if (i3 == 1) {
                end = focusPropertiesFetchFocusProperties$ui_release.getEnd();
            } else {
                if (i3 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                end = focusPropertiesFetchFocusProperties$ui_release.getStart();
            }
            if (end == FocusRequester.Companion.getDefault()) {
                end = null;
            }
            if (end == null) {
                return focusPropertiesFetchFocusProperties$ui_release.getRight();
            }
        } else {
            if (FocusDirection.m1091equalsimpl0(i, companion.m1096getEnterdhqQ8s())) {
                return (FocusRequester) focusPropertiesFetchFocusProperties$ui_release.getEnter().invoke(FocusDirection.m1088boximpl(i));
            }
            if (FocusDirection.m1091equalsimpl0(i, companion.m1097getExitdhqQ8s())) {
                return (FocusRequester) focusPropertiesFetchFocusProperties$ui_release.getExit().invoke(FocusDirection.m1088boximpl(i));
            }
            throw new IllegalStateException("invalid FocusDirection");
        }
        return end;
    }

    private static final FocusTargetNode findNonDeactivatedParent(FocusTargetNode focusTargetNode) {
        NodeChain nodes$ui_release;
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(1024);
        if (!focusTargetNode.getNode().isAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent$ui_release = focusTargetNode.getNode().getParent$ui_release();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes$ui_release().getHead$ui_release().getAggregateChildKindSet$ui_release() & iM1895constructorimpl) != 0) {
                while (parent$ui_release != null) {
                    if ((parent$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        Modifier.Node nodePop = parent$ui_release;
                        MutableVector mutableVector = null;
                        while (nodePop != null) {
                            if (!(nodePop instanceof FocusTargetNode)) {
                                if ((nodePop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i = 0;
                                    for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate$ui_release(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild$ui_release()) {
                                        if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                            i++;
                                            if (i == 1) {
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
                                    if (i == 1) {
                                    }
                                }
                            } else {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
                                if (focusTargetNode2.fetchFocusProperties$ui_release().getCanFocus()) {
                                    return focusTargetNode2;
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
        return null;
    }

    /* JADX INFO: renamed from: focusSearch-0X8WOeE, reason: not valid java name */
    public static final Boolean m1130focusSearch0X8WOeE(FocusTargetNode focusTargetNode, int i, LayoutDirection layoutDirection, Rect rect, Function1 function1) {
        int iM1101getRightdhqQ8s;
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1099getNextdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1100getPreviousdhqQ8s())) {
            return Boolean.valueOf(OneDimensionalFocusSearchKt.m1133oneDimensionalFocusSearchOMvw8(focusTargetNode, i, function1));
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
            return TwoDimensionalFocusSearchKt.m1142twoDimensionalFocusSearchsMXa3k8(focusTargetNode, i, rect, function1);
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1096getEnterdhqQ8s())) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
            if (i2 == 1) {
                iM1101getRightdhqQ8s = companion.m1101getRightdhqQ8s();
            } else {
                if (i2 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                iM1101getRightdhqQ8s = companion.m1098getLeftdhqQ8s();
            }
            FocusTargetNode focusTargetNodeFindActiveFocusNode = findActiveFocusNode(focusTargetNode);
            if (focusTargetNodeFindActiveFocusNode != null) {
                return TwoDimensionalFocusSearchKt.m1142twoDimensionalFocusSearchsMXa3k8(focusTargetNodeFindActiveFocusNode, iM1101getRightdhqQ8s, rect, function1);
            }
            return null;
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1097getExitdhqQ8s())) {
            FocusTargetNode focusTargetNodeFindActiveFocusNode2 = findActiveFocusNode(focusTargetNode);
            FocusTargetNode focusTargetNodeFindNonDeactivatedParent = focusTargetNodeFindActiveFocusNode2 != null ? findNonDeactivatedParent(focusTargetNodeFindActiveFocusNode2) : null;
            return Boolean.valueOf((focusTargetNodeFindNonDeactivatedParent == null || Intrinsics.areEqual(focusTargetNodeFindNonDeactivatedParent, focusTargetNode)) ? false : ((Boolean) function1.invoke(focusTargetNodeFindNonDeactivatedParent)).booleanValue());
        }
        throw new IllegalStateException(("Focus search invoked with invalid FocusDirection " + ((Object) FocusDirection.m1093toStringimpl(i))).toString());
    }

    public static final Rect focusRect(FocusTargetNode focusTargetNode) {
        Rect rectLocalBoundingBoxOf;
        NodeCoordinator coordinator$ui_release = focusTargetNode.getCoordinator$ui_release();
        return (coordinator$ui_release == null || (rectLocalBoundingBoxOf = LayoutCoordinatesKt.findRootCoordinates(coordinator$ui_release).localBoundingBoxOf(coordinator$ui_release, false)) == null) ? Rect.Companion.getZero() : rectLocalBoundingBoxOf;
    }

    public static final boolean isEligibleForFocusSearch(FocusTargetNode focusTargetNode) {
        LayoutNode layoutNode;
        NodeCoordinator coordinator$ui_release;
        LayoutNode layoutNode2;
        NodeCoordinator coordinator$ui_release2 = focusTargetNode.getCoordinator$ui_release();
        return (coordinator$ui_release2 == null || (layoutNode = coordinator$ui_release2.getLayoutNode()) == null || !layoutNode.isPlaced() || (coordinator$ui_release = focusTargetNode.getCoordinator$ui_release()) == null || (layoutNode2 = coordinator$ui_release.getLayoutNode()) == null || !layoutNode2.isAttached()) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x003b, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.focus.FocusTargetNode getActiveChild(androidx.compose.ui.focus.FocusTargetNode r10) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.getActiveChild(androidx.compose.ui.focus.FocusTargetNode):androidx.compose.ui.focus.FocusTargetNode");
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x004f, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.focus.FocusTargetNode findActiveFocusNode(androidx.compose.ui.focus.FocusTargetNode r10) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.findActiveFocusNode(androidx.compose.ui.focus.FocusTargetNode):androidx.compose.ui.focus.FocusTargetNode");
    }
}
