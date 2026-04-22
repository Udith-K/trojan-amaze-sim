package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TwoDimensionalFocusSearch.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TwoDimensionalFocusSearchKt {

    /* JADX INFO: compiled from: TwoDimensionalFocusSearch.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 2;
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

    /* JADX INFO: renamed from: twoDimensionalFocusSearch-sMXa3k8, reason: not valid java name */
    public static final Boolean m1142twoDimensionalFocusSearchsMXa3k8(FocusTargetNode focusTargetNode, int i, Rect rect, Function1 function1) {
        FocusStateImpl focusState = focusTargetNode.getFocusState();
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        int i2 = iArr[focusState.ordinal()];
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3) {
                return Boolean.valueOf(m1138findChildCorrespondingToFocusEnterOMvw8(focusTargetNode, i, function1));
            }
            if (i2 == 4) {
                if (focusTargetNode.fetchFocusProperties$ui_release().getCanFocus()) {
                    return (Boolean) function1.invoke(focusTargetNode);
                }
                if (rect == null) {
                    return Boolean.valueOf(m1138findChildCorrespondingToFocusEnterOMvw8(focusTargetNode, i, function1));
                }
                return Boolean.valueOf(m1141searchChildren4C6V_qg(focusTargetNode, rect, i, function1));
            }
            throw new NoWhenBranchMatchedException();
        }
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild == null) {
            throw new IllegalStateException("ActiveParent must have a focusedChild");
        }
        int i3 = iArr[activeChild.getFocusState().ordinal()];
        if (i3 == 1) {
            Boolean boolM1142twoDimensionalFocusSearchsMXa3k8 = m1142twoDimensionalFocusSearchsMXa3k8(activeChild, i, rect, function1);
            if (!Intrinsics.areEqual(boolM1142twoDimensionalFocusSearchsMXa3k8, Boolean.FALSE)) {
                return boolM1142twoDimensionalFocusSearchsMXa3k8;
            }
            if (rect == null) {
                rect = FocusTraversalKt.focusRect(activeNode(activeChild));
            }
            return Boolean.valueOf(m1139generateAndSearchChildren4C6V_qg(focusTargetNode, rect, i, function1));
        }
        if (i3 == 2 || i3 == 3) {
            if (rect == null) {
                rect = FocusTraversalKt.focusRect(activeChild);
            }
            return Boolean.valueOf(m1139generateAndSearchChildren4C6V_qg(focusTargetNode, rect, i, function1));
        }
        if (i3 != 4) {
            throw new NoWhenBranchMatchedException();
        }
        throw new IllegalStateException("ActiveParent must have a focusedChild");
    }

    private static final void collectAccessibleChildren(DelegatableNode delegatableNode, MutableVector mutableVector) {
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(1024);
        if (!delegatableNode.getNode().isAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$ui_release = delegatableNode.getNode().getChild$ui_release();
        if (child$ui_release == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, delegatableNode.getNode());
        } else {
            mutableVector2.add(child$ui_release);
        }
        while (mutableVector2.isNotEmpty()) {
            Modifier.Node nodePop = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
            if ((nodePop.getAggregateChildKindSet$ui_release() & iM1895constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, nodePop);
            } else {
                while (true) {
                    if (nodePop == null) {
                        break;
                    }
                    if ((nodePop.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        MutableVector mutableVector3 = null;
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
                                                if (mutableVector3 == null) {
                                                    mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector3.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector3.add(delegate$ui_release);
                                            }
                                        }
                                    }
                                    if (i == 1) {
                                    }
                                }
                            } else {
                                FocusTargetNode focusTargetNode = (FocusTargetNode) nodePop;
                                if (focusTargetNode.isAttached() && !DelegatableNodeKt.requireLayoutNode(focusTargetNode).isDeactivated()) {
                                    if (focusTargetNode.fetchFocusProperties$ui_release().getCanFocus()) {
                                        mutableVector.add(focusTargetNode);
                                    } else {
                                        collectAccessibleChildren(focusTargetNode, mutableVector);
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                        }
                    } else {
                        nodePop = nodePop.getChild$ui_release();
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: generateAndSearchChildren-4C6V_qg, reason: not valid java name */
    private static final boolean m1139generateAndSearchChildren4C6V_qg(final FocusTargetNode focusTargetNode, final Rect rect, final int i, final Function1 function1) {
        if (m1141searchChildren4C6V_qg(focusTargetNode, rect, i, function1)) {
            return true;
        }
        Boolean bool = (Boolean) BeyondBoundsLayoutKt.m1087searchBeyondBoundsOMvw8(focusTargetNode, i, new Function1() { // from class: androidx.compose.ui.focus.TwoDimensionalFocusSearchKt$generateAndSearchChildren$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BeyondBoundsLayout.BeyondBoundsScope beyondBoundsScope) {
                boolean zM1141searchChildren4C6V_qg = TwoDimensionalFocusSearchKt.m1141searchChildren4C6V_qg(focusTargetNode, rect, i, function1);
                Boolean boolValueOf = Boolean.valueOf(zM1141searchChildren4C6V_qg);
                if (zM1141searchChildren4C6V_qg || !beyondBoundsScope.getHasMoreContent()) {
                    return boolValueOf;
                }
                return null;
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: findBestCandidate-4WY_MpI, reason: not valid java name */
    private static final FocusTargetNode m1137findBestCandidate4WY_MpI(MutableVector mutableVector, Rect rect, int i) {
        Rect rectTranslate;
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s())) {
            rectTranslate = rect.translate(rect.getWidth() + 1, 0.0f);
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
            rectTranslate = rect.translate(-(rect.getWidth() + 1), 0.0f);
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s())) {
            rectTranslate = rect.translate(0.0f, rect.getHeight() + 1);
        } else {
            if (!FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            rectTranslate = rect.translate(0.0f, -(rect.getHeight() + 1));
        }
        int size = mutableVector.getSize();
        FocusTargetNode focusTargetNode = null;
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i2 = 0;
            do {
                FocusTargetNode focusTargetNode2 = (FocusTargetNode) content[i2];
                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode2)) {
                    Rect rectFocusRect = FocusTraversalKt.focusRect(focusTargetNode2);
                    if (m1140isBetterCandidateI7lrPNg(rectFocusRect, rectTranslate, rect, i)) {
                        focusTargetNode = focusTargetNode2;
                        rectTranslate = rectFocusRect;
                    }
                }
                i2++;
            } while (i2 < size);
        }
        return focusTargetNode;
    }

    private static final boolean isBetterCandidate_I7lrPNg$isCandidate(Rect rect, int i, Rect rect2) {
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s())) {
            if ((rect2.getRight() <= rect.getRight() && rect2.getLeft() < rect.getRight()) || rect2.getLeft() <= rect.getLeft()) {
                return false;
            }
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
            if ((rect2.getLeft() >= rect.getLeft() && rect2.getRight() > rect.getLeft()) || rect2.getRight() >= rect.getRight()) {
                return false;
            }
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s())) {
            if ((rect2.getBottom() <= rect.getBottom() && rect2.getTop() < rect.getBottom()) || rect2.getTop() <= rect.getTop()) {
                return false;
            }
        } else {
            if (!FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            if ((rect2.getTop() >= rect.getTop() && rect2.getBottom() > rect.getTop()) || rect2.getBottom() >= rect.getBottom()) {
                return false;
            }
        }
        return true;
    }

    private static final float isBetterCandidate_I7lrPNg$majorAxisDistance(Rect rect, int i, Rect rect2) {
        float top;
        float bottom;
        float top2;
        float bottom2;
        float f;
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (!FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s())) {
            if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
                top = rect.getLeft();
                bottom = rect2.getRight();
            } else if (FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s())) {
                top2 = rect2.getTop();
                bottom2 = rect.getBottom();
            } else {
                if (!FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
                    throw new IllegalStateException("This function should only be used for 2-D focus search");
                }
                top = rect.getTop();
                bottom = rect2.getBottom();
            }
            f = top - bottom;
            return Math.max(0.0f, f);
        }
        top2 = rect2.getLeft();
        bottom2 = rect.getRight();
        f = top2 - bottom2;
        return Math.max(0.0f, f);
    }

    private static final float isBetterCandidate_I7lrPNg$minorAxisDistance(Rect rect, int i, Rect rect2) {
        float f;
        float left;
        float left2;
        float width;
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
            f = 2;
            left = rect2.getTop() + (rect2.getHeight() / f);
            left2 = rect.getTop();
            width = rect.getHeight();
        } else {
            if (!(FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s()))) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            f = 2;
            left = rect2.getLeft() + (rect2.getWidth() / f);
            left2 = rect.getLeft();
            width = rect.getWidth();
        }
        return left - (left2 + (width / f));
    }

    private static final long isBetterCandidate_I7lrPNg$weightedDistance(int i, Rect rect, Rect rect2) {
        long jAbs = (long) Math.abs(isBetterCandidate_I7lrPNg$majorAxisDistance(rect2, i, rect));
        long jAbs2 = (long) Math.abs(isBetterCandidate_I7lrPNg$minorAxisDistance(rect2, i, rect));
        return (((long) 13) * jAbs * jAbs) + (jAbs2 * jAbs2);
    }

    /* JADX INFO: renamed from: isBetterCandidate-I7lrPNg, reason: not valid java name */
    private static final boolean m1140isBetterCandidateI7lrPNg(Rect rect, Rect rect2, Rect rect3, int i) {
        if (isBetterCandidate_I7lrPNg$isCandidate(rect, i, rect3)) {
            return !isBetterCandidate_I7lrPNg$isCandidate(rect2, i, rect3) || m1136beamBeatsI7lrPNg(rect3, rect, rect2, i) || (!m1136beamBeatsI7lrPNg(rect3, rect2, rect, i) && isBetterCandidate_I7lrPNg$weightedDistance(i, rect3, rect) < isBetterCandidate_I7lrPNg$weightedDistance(i, rect3, rect2));
        }
        return false;
    }

    private static final boolean beamBeats_I7lrPNg$inSourceBeam(Rect rect, int i, Rect rect2) {
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (!(FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s()))) {
            if (!(FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s()))) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            if (rect.getRight() > rect2.getLeft() && rect.getLeft() < rect2.getRight()) {
                return true;
            }
        } else if (rect.getBottom() > rect2.getTop() && rect.getTop() < rect2.getBottom()) {
            return true;
        }
        return false;
    }

    private static final boolean beamBeats_I7lrPNg$isInDirectionOfSearch(Rect rect, int i, Rect rect2) {
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s())) {
            if (rect2.getLeft() < rect.getRight()) {
                return false;
            }
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
            if (rect2.getRight() > rect.getLeft()) {
                return false;
            }
        } else if (FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s())) {
            if (rect2.getTop() < rect.getBottom()) {
                return false;
            }
        } else {
            if (!FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            if (rect2.getBottom() > rect.getTop()) {
                return false;
            }
        }
        return true;
    }

    private static final float beamBeats_I7lrPNg$majorAxisDistance$6(Rect rect, int i, Rect rect2) {
        float top;
        float bottom;
        float top2;
        float bottom2;
        float f;
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (!FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s())) {
            if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
                top = rect.getLeft();
                bottom = rect2.getRight();
            } else if (FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s())) {
                top2 = rect2.getTop();
                bottom2 = rect.getBottom();
            } else {
                if (!FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
                    throw new IllegalStateException("This function should only be used for 2-D focus search");
                }
                top = rect.getTop();
                bottom = rect2.getBottom();
            }
            f = top - bottom;
            return Math.max(0.0f, f);
        }
        top2 = rect2.getLeft();
        bottom2 = rect.getRight();
        f = top2 - bottom2;
        return Math.max(0.0f, f);
    }

    private static final float beamBeats_I7lrPNg$majorAxisDistanceToFarEdge(Rect rect, int i, Rect rect2) {
        float bottom;
        float bottom2;
        float top;
        float top2;
        float f;
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (!FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s())) {
            if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
                bottom = rect.getRight();
                bottom2 = rect2.getRight();
            } else if (FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s())) {
                top = rect2.getTop();
                top2 = rect.getTop();
            } else {
                if (!FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
                    throw new IllegalStateException("This function should only be used for 2-D focus search");
                }
                bottom = rect.getBottom();
                bottom2 = rect2.getBottom();
            }
            f = bottom - bottom2;
            return Math.max(1.0f, f);
        }
        top = rect2.getLeft();
        top2 = rect.getLeft();
        f = top - top2;
        return Math.max(1.0f, f);
    }

    /* JADX INFO: renamed from: beamBeats-I7lrPNg, reason: not valid java name */
    private static final boolean m1136beamBeatsI7lrPNg(Rect rect, Rect rect2, Rect rect3, int i) {
        if (beamBeats_I7lrPNg$inSourceBeam(rect3, i, rect) || !beamBeats_I7lrPNg$inSourceBeam(rect2, i, rect)) {
            return false;
        }
        if (beamBeats_I7lrPNg$isInDirectionOfSearch(rect3, i, rect)) {
            FocusDirection.Companion companion = FocusDirection.Companion;
            if (!FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s()) && !FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s()) && beamBeats_I7lrPNg$majorAxisDistance$6(rect2, i, rect) >= beamBeats_I7lrPNg$majorAxisDistanceToFarEdge(rect3, i, rect)) {
                return false;
            }
        }
        return true;
    }

    private static final Rect topLeft(Rect rect) {
        return new Rect(rect.getLeft(), rect.getTop(), rect.getLeft(), rect.getTop());
    }

    private static final Rect bottomRight(Rect rect) {
        return new Rect(rect.getRight(), rect.getBottom(), rect.getRight(), rect.getBottom());
    }

    private static final FocusTargetNode activeNode(FocusTargetNode focusTargetNode) {
        if (focusTargetNode.getFocusState() != FocusStateImpl.ActiveParent) {
            throw new IllegalStateException("Searching for active node in inactive hierarchy");
        }
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(focusTargetNode);
        if (focusTargetNodeFindActiveFocusNode != null) {
            return focusTargetNodeFindActiveFocusNode;
        }
        throw new IllegalStateException("ActiveParent must have a focusedChild");
    }

    /* JADX INFO: renamed from: findChildCorrespondingToFocusEnter--OM-vw8, reason: not valid java name */
    public static final boolean m1138findChildCorrespondingToFocusEnterOMvw8(FocusTargetNode focusTargetNode, int i, Function1 function1) {
        Rect rectBottomRight;
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16], 0);
        collectAccessibleChildren(focusTargetNode, mutableVector);
        if (mutableVector.getSize() <= 1) {
            FocusTargetNode focusTargetNode2 = (FocusTargetNode) (mutableVector.isEmpty() ? null : mutableVector.getContent()[0]);
            if (focusTargetNode2 != null) {
                return ((Boolean) function1.invoke(focusTargetNode2)).booleanValue();
            }
            return false;
        }
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1096getEnterdhqQ8s())) {
            i = companion.m1101getRightdhqQ8s();
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
            rectBottomRight = topLeft(FocusTraversalKt.focusRect(focusTargetNode));
        } else {
            if (!(FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s()) ? true : FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s()))) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            rectBottomRight = bottomRight(FocusTraversalKt.focusRect(focusTargetNode));
        }
        FocusTargetNode focusTargetNodeM1137findBestCandidate4WY_MpI = m1137findBestCandidate4WY_MpI(mutableVector, rectBottomRight, i);
        if (focusTargetNodeM1137findBestCandidate4WY_MpI != null) {
            return ((Boolean) function1.invoke(focusTargetNodeM1137findBestCandidate4WY_MpI)).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: searchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m1141searchChildren4C6V_qg(FocusTargetNode focusTargetNode, Rect rect, int i, Function1 function1) {
        FocusTargetNode focusTargetNodeM1137findBestCandidate4WY_MpI;
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16], 0);
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(1024);
        if (!focusTargetNode.getNode().isAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$ui_release = focusTargetNode.getNode().getChild$ui_release();
        if (child$ui_release == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusTargetNode.getNode());
        } else {
            mutableVector2.add(child$ui_release);
        }
        while (mutableVector2.isNotEmpty()) {
            Modifier.Node nodePop = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
            if ((nodePop.getAggregateChildKindSet$ui_release() & iM1895constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, nodePop);
            } else {
                while (true) {
                    if (nodePop == null) {
                        break;
                    }
                    if ((nodePop.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        MutableVector mutableVector3 = null;
                        while (nodePop != null) {
                            if (!(nodePop instanceof FocusTargetNode)) {
                                if ((nodePop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i2 = 0;
                                    for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate$ui_release(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild$ui_release()) {
                                        if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                            i2++;
                                            if (i2 == 1) {
                                                nodePop = delegate$ui_release;
                                            } else {
                                                if (mutableVector3 == null) {
                                                    mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector3.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector3.add(delegate$ui_release);
                                            }
                                        }
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                            } else {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) nodePop;
                                if (focusTargetNode2.isAttached()) {
                                    mutableVector.add(focusTargetNode2);
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                        }
                    } else {
                        nodePop = nodePop.getChild$ui_release();
                    }
                }
            }
        }
        while (mutableVector.isNotEmpty() && (focusTargetNodeM1137findBestCandidate4WY_MpI = m1137findBestCandidate4WY_MpI(mutableVector, rect, i)) != null) {
            if (focusTargetNodeM1137findBestCandidate4WY_MpI.fetchFocusProperties$ui_release().getCanFocus()) {
                return ((Boolean) function1.invoke(focusTargetNodeM1137findBestCandidate4WY_MpI)).booleanValue();
            }
            if (m1139generateAndSearchChildren4C6V_qg(focusTargetNodeM1137findBestCandidate4WY_MpI, rect, i, function1)) {
                return true;
            }
            mutableVector.remove(focusTargetNodeM1137findBestCandidate4WY_MpI);
        }
        return false;
    }
}
