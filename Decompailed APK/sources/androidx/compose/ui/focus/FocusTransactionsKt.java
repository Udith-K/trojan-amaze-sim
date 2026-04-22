package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusTransactions.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FocusTransactionsKt {

    /* JADX INFO: compiled from: FocusTransactions.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final boolean requestFocus(FocusTargetNode focusTargetNode) {
        Boolean boolM1126requestFocusMxy_nc0 = m1126requestFocusMxy_nc0(focusTargetNode, FocusDirection.Companion.m1096getEnterdhqQ8s());
        if (boolM1126requestFocusMxy_nc0 != null) {
            return boolM1126requestFocusMxy_nc0.booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: requestFocus-Mxy_nc0, reason: not valid java name */
    public static final Boolean m1126requestFocusMxy_nc0(final FocusTargetNode focusTargetNode, int i) {
        Boolean boolValueOf;
        FocusTransactionManager focusTransactionManagerRequireTransactionManager = FocusTargetNodeKt.requireTransactionManager(focusTargetNode);
        Function0 function0 = new Function0() { // from class: androidx.compose.ui.focus.FocusTransactionsKt.requestFocus.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m1128invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1128invoke() {
                if (focusTargetNode.getNode().isAttached()) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
                }
            }
        };
        try {
            if (focusTransactionManagerRequireTransactionManager.ongoingTransaction) {
                focusTransactionManagerRequireTransactionManager.cancelTransaction();
            }
            focusTransactionManagerRequireTransactionManager.beginTransaction();
            focusTransactionManagerRequireTransactionManager.cancellationListener.add(function0);
            int i2 = WhenMappings.$EnumSwitchMapping$0[m1125performCustomRequestFocusMxy_nc0(focusTargetNode, i).ordinal()];
            if (i2 == 1) {
                boolValueOf = Boolean.valueOf(performRequestFocus(focusTargetNode));
            } else if (i2 != 2) {
                if (i2 != 3 && i2 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                boolValueOf = null;
            } else {
                boolValueOf = Boolean.TRUE;
            }
            return boolValueOf;
        } finally {
            focusTransactionManagerRequireTransactionManager.commitTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean performRequestFocus(androidx.compose.ui.focus.FocusTargetNode r11) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTransactionsKt.performRequestFocus(androidx.compose.ui.focus.FocusTargetNode):boolean");
    }

    private static final boolean requestFocusForChild(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2) {
        Modifier.Node node;
        Modifier.Node nodePop;
        NodeChain nodes$ui_release;
        NodeChain nodes$ui_release2;
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(1024);
        if (!focusTargetNode2.getNode().isAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent$ui_release = focusTargetNode2.getNode().getParent$ui_release();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode2);
        loop0: while (true) {
            node = null;
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
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    parent$ui_release = parent$ui_release.getParent$ui_release();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent$ui_release = (layoutNodeRequireLayoutNode == null || (nodes$ui_release2 = layoutNodeRequireLayoutNode.getNodes$ui_release()) == null) ? null : nodes$ui_release2.getTail$ui_release();
        }
        if (!Intrinsics.areEqual(nodePop, focusTargetNode)) {
            throw new IllegalStateException("Non child node cannot request focus.");
        }
        int i2 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i2 == 1) {
            boolean zGrantFocus = grantFocus(focusTargetNode2);
            if (!zGrantFocus) {
                return zGrantFocus;
            }
            focusTargetNode.setFocusState(FocusStateImpl.ActiveParent);
            return zGrantFocus;
        }
        if (i2 != 2) {
            if (i2 == 3) {
                requireActiveChild(focusTargetNode);
                if (clearChildFocus$default(focusTargetNode, false, false, 3, null) && grantFocus(focusTargetNode2)) {
                    return true;
                }
            } else {
                if (i2 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                int iM1895constructorimpl2 = NodeKind.m1895constructorimpl(1024);
                if (!focusTargetNode.getNode().isAttached()) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                Modifier.Node parent$ui_release2 = focusTargetNode.getNode().getParent$ui_release();
                LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
                loop4: while (true) {
                    if (layoutNodeRequireLayoutNode2 == null) {
                        break;
                    }
                    if ((layoutNodeRequireLayoutNode2.getNodes$ui_release().getHead$ui_release().getAggregateChildKindSet$ui_release() & iM1895constructorimpl2) != 0) {
                        while (parent$ui_release2 != null) {
                            if ((parent$ui_release2.getKindSet$ui_release() & iM1895constructorimpl2) != 0) {
                                Modifier.Node nodePop2 = parent$ui_release2;
                                MutableVector mutableVector2 = null;
                                while (nodePop2 != null) {
                                    if (nodePop2 instanceof FocusTargetNode) {
                                        node = nodePop2;
                                        break loop4;
                                    }
                                    if ((nodePop2.getKindSet$ui_release() & iM1895constructorimpl2) != 0 && (nodePop2 instanceof DelegatingNode)) {
                                        int i3 = 0;
                                        for (Modifier.Node delegate$ui_release2 = ((DelegatingNode) nodePop2).getDelegate$ui_release(); delegate$ui_release2 != null; delegate$ui_release2 = delegate$ui_release2.getChild$ui_release()) {
                                            if ((delegate$ui_release2.getKindSet$ui_release() & iM1895constructorimpl2) != 0) {
                                                i3++;
                                                if (i3 == 1) {
                                                    nodePop2 = delegate$ui_release2;
                                                } else {
                                                    if (mutableVector2 == null) {
                                                        mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    if (nodePop2 != null) {
                                                        mutableVector2.add(nodePop2);
                                                        nodePop2 = null;
                                                    }
                                                    mutableVector2.add(delegate$ui_release2);
                                                }
                                            }
                                        }
                                        if (i3 == 1) {
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                }
                            }
                            parent$ui_release2 = parent$ui_release2.getParent$ui_release();
                        }
                    }
                    layoutNodeRequireLayoutNode2 = layoutNodeRequireLayoutNode2.getParent$ui_release();
                    parent$ui_release2 = (layoutNodeRequireLayoutNode2 == null || (nodes$ui_release = layoutNodeRequireLayoutNode2.getNodes$ui_release()) == null) ? null : nodes$ui_release.getTail$ui_release();
                }
                FocusTargetNode focusTargetNode3 = (FocusTargetNode) node;
                if (focusTargetNode3 == null && requestFocusForOwner(focusTargetNode)) {
                    boolean zGrantFocus2 = grantFocus(focusTargetNode2);
                    if (!zGrantFocus2) {
                        return zGrantFocus2;
                    }
                    focusTargetNode.setFocusState(FocusStateImpl.ActiveParent);
                    return zGrantFocus2;
                }
                if (focusTargetNode3 != null && requestFocusForChild(focusTargetNode3, focusTargetNode)) {
                    boolean zRequestFocusForChild = requestFocusForChild(focusTargetNode, focusTargetNode2);
                    if (focusTargetNode.getFocusState() != FocusStateImpl.ActiveParent) {
                        throw new IllegalStateException("Deactivated node is focused");
                    }
                    if (!zRequestFocusForChild) {
                        return zRequestFocusForChild;
                    }
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode3);
                    return zRequestFocusForChild;
                }
            }
        }
        return false;
    }

    public static final boolean clearFocus(FocusTargetNode focusTargetNode, boolean z, boolean z2) {
        int i = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i == 1) {
            focusTargetNode.setFocusState(FocusStateImpl.Inactive);
            if (z2) {
                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
            }
        } else {
            if (i == 2) {
                if (!z) {
                    return z;
                }
                focusTargetNode.setFocusState(FocusStateImpl.Inactive);
                if (!z2) {
                    return z;
                }
                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
                return z;
            }
            if (i != 3) {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
            } else {
                if (!clearChildFocus(focusTargetNode, z, z2)) {
                    return false;
                }
                focusTargetNode.setFocusState(FocusStateImpl.Inactive);
                if (z2) {
                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode);
                }
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: performCustomEnter-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m1123performCustomEnterMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        if (!focusTargetNode.isProcessingCustomEnter) {
            focusTargetNode.isProcessingCustomEnter = true;
            try {
                FocusRequester focusRequester = (FocusRequester) focusTargetNode.fetchFocusProperties$ui_release().getEnter().invoke(FocusDirection.m1088boximpl(i));
                FocusRequester.Companion companion = FocusRequester.Companion;
                if (focusRequester != companion.getDefault()) {
                    if (focusRequester == companion.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    return focusRequester.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
            } finally {
                focusTargetNode.isProcessingCustomEnter = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX INFO: renamed from: performCustomExit-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m1124performCustomExitMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        if (!focusTargetNode.isProcessingCustomExit) {
            focusTargetNode.isProcessingCustomExit = true;
            try {
                FocusRequester focusRequester = (FocusRequester) focusTargetNode.fetchFocusProperties$ui_release().getExit().invoke(FocusDirection.m1088boximpl(i));
                FocusRequester.Companion companion = FocusRequester.Companion;
                if (focusRequester != companion.getDefault()) {
                    if (focusRequester == companion.getCancel()) {
                        return CustomDestinationResult.Cancelled;
                    }
                    return focusRequester.focus$ui_release() ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
            } finally {
                focusTargetNode.isProcessingCustomExit = false;
            }
        }
        return CustomDestinationResult.None;
    }

    private static final boolean grantFocus(final FocusTargetNode focusTargetNode) {
        ObserverModifierNodeKt.observeReads(focusTargetNode, new Function0() { // from class: androidx.compose.ui.focus.FocusTransactionsKt.grantFocus.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m1127invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1127invoke() {
                focusTargetNode.fetchFocusProperties$ui_release();
            }
        });
        int i = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i != 3 && i != 4) {
            return true;
        }
        focusTargetNode.setFocusState(FocusStateImpl.Active);
        return true;
    }

    static /* synthetic */ boolean clearChildFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return clearChildFocus(focusTargetNode, z, z2);
    }

    private static final boolean clearChildFocus(FocusTargetNode focusTargetNode, boolean z, boolean z2) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild != null) {
            return clearFocus(activeChild, z, z2);
        }
        return true;
    }

    private static final boolean requestFocusForOwner(FocusTargetNode focusTargetNode) {
        return DelegatableNodeKt.requireOwner(focusTargetNode).getFocusOwner().mo1110requestFocusForOwner7o62pno(null, null);
    }

    private static final FocusTargetNode requireActiveChild(FocusTargetNode focusTargetNode) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild != null) {
            return activeChild;
        }
        throw new IllegalArgumentException("ActiveParent with no focused child");
    }

    /* JADX INFO: renamed from: performCustomRequestFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m1125performCustomRequestFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        Modifier.Node nodePop;
        NodeChain nodes$ui_release;
        int i2 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i2 == 1 || i2 == 2) {
            return CustomDestinationResult.None;
        }
        if (i2 == 3) {
            return m1122performCustomClearFocusMxy_nc0(requireActiveChild(focusTargetNode), i);
        }
        if (i2 == 4) {
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
                                    int i3 = 0;
                                    for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate$ui_release(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild$ui_release()) {
                                        if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                            i3++;
                                            if (i3 == 1) {
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
                                    if (i3 == 1) {
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
            if (focusTargetNode2 == null) {
                return CustomDestinationResult.None;
            }
            int i4 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode2.getFocusState().ordinal()];
            if (i4 == 1) {
                return m1123performCustomEnterMxy_nc0(focusTargetNode2, i);
            }
            if (i4 == 2) {
                return CustomDestinationResult.Cancelled;
            }
            if (i4 == 3) {
                return m1125performCustomRequestFocusMxy_nc0(focusTargetNode2, i);
            }
            if (i4 != 4) {
                throw new NoWhenBranchMatchedException();
            }
            CustomDestinationResult customDestinationResultM1125performCustomRequestFocusMxy_nc0 = m1125performCustomRequestFocusMxy_nc0(focusTargetNode2, i);
            CustomDestinationResult customDestinationResult = customDestinationResultM1125performCustomRequestFocusMxy_nc0 != CustomDestinationResult.None ? customDestinationResultM1125performCustomRequestFocusMxy_nc0 : null;
            return customDestinationResult == null ? m1123performCustomEnterMxy_nc0(focusTargetNode2, i) : customDestinationResult;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: performCustomClearFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m1122performCustomClearFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        int i2 = WhenMappings.$EnumSwitchMapping$1[focusTargetNode.getFocusState().ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return CustomDestinationResult.Cancelled;
            }
            if (i2 == 3) {
                CustomDestinationResult customDestinationResultM1122performCustomClearFocusMxy_nc0 = m1122performCustomClearFocusMxy_nc0(requireActiveChild(focusTargetNode), i);
                if (customDestinationResultM1122performCustomClearFocusMxy_nc0 == CustomDestinationResult.None) {
                    customDestinationResultM1122performCustomClearFocusMxy_nc0 = null;
                }
                return customDestinationResultM1122performCustomClearFocusMxy_nc0 == null ? m1124performCustomExitMxy_nc0(focusTargetNode, i) : customDestinationResultM1122performCustomClearFocusMxy_nc0;
            }
            if (i2 != 4) {
                throw new NoWhenBranchMatchedException();
            }
        }
        return CustomDestinationResult.None;
    }
}
