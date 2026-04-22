package androidx.compose.ui.node;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_jvmKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusEventModifierNodeKt;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusPropertiesModifierNodeKt;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTargetNodeKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.semantics.SemanticsModifier;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: NodeKind.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class NodeKindKt {
    private static final MutableObjectIntMap classToKindSetMap = ObjectIntMapKt.mutableObjectIntMapOf();

    public static final int calculateNodeKindSetFrom(Modifier.Element element) {
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(1);
        if (element instanceof LayoutModifier) {
            iM1895constructorimpl |= NodeKind.m1895constructorimpl(2);
        }
        if (element instanceof DrawModifier) {
            iM1895constructorimpl |= NodeKind.m1895constructorimpl(4);
        }
        if (element instanceof SemanticsModifier) {
            iM1895constructorimpl |= NodeKind.m1895constructorimpl(8);
        }
        if ((element instanceof ModifierLocalConsumer) || (element instanceof ModifierLocalProvider)) {
            iM1895constructorimpl |= NodeKind.m1895constructorimpl(32);
        }
        if (element instanceof OnGloballyPositionedModifier) {
            iM1895constructorimpl |= NodeKind.m1895constructorimpl(256);
        }
        return element instanceof ParentDataModifier ? iM1895constructorimpl | NodeKind.m1895constructorimpl(64) : iM1895constructorimpl;
    }

    /* JADX INFO: renamed from: getIncludeSelfInTraversal-H91voCI, reason: not valid java name */
    public static final boolean m1896getIncludeSelfInTraversalH91voCI(int i) {
        return (i & NodeKind.m1895constructorimpl(128)) != 0;
    }

    private static final void scheduleInvalidationOfAssociatedFocusTargets(FocusPropertiesModifierNode focusPropertiesModifierNode) {
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(1024);
        if (!focusPropertiesModifierNode.getNode().isAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$ui_release = focusPropertiesModifierNode.getNode().getChild$ui_release();
        if (child$ui_release == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusPropertiesModifierNode.getNode());
        } else {
            mutableVector.add(child$ui_release);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node nodePop = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((nodePop.getAggregateChildKindSet$ui_release() & iM1895constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector, nodePop);
            } else {
                while (true) {
                    if (nodePop == null) {
                        break;
                    }
                    if ((nodePop.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        MutableVector mutableVector2 = null;
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
                                                if (mutableVector2 == null) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector2.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector2.add(delegate$ui_release);
                                            }
                                        }
                                    }
                                    if (i == 1) {
                                    }
                                }
                            } else {
                                FocusTargetNodeKt.invalidateFocusTarget((FocusTargetNode) nodePop);
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                        }
                    } else {
                        nodePop = nodePop.getChild$ui_release();
                    }
                }
            }
        }
    }

    public static final int calculateNodeKindSetFrom(Modifier.Node node) {
        if (node.getKindSet$ui_release() != 0) {
            return node.getKindSet$ui_release();
        }
        MutableObjectIntMap mutableObjectIntMap = classToKindSetMap;
        Object objClassKeyForObject = Actual_jvmKt.classKeyForObject(node);
        int iFindKeyIndex = mutableObjectIntMap.findKeyIndex(objClassKeyForObject);
        if (iFindKeyIndex < 0) {
            int iM1895constructorimpl = NodeKind.m1895constructorimpl(1);
            if (node instanceof LayoutModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(2);
            }
            if (node instanceof DrawModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(4);
            }
            if (node instanceof SemanticsModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(8);
            }
            if (node instanceof PointerInputModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(16);
            }
            if (node instanceof ModifierLocalModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(32);
            }
            if (node instanceof ParentDataModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(64);
            }
            if (node instanceof LayoutAwareModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(128);
            }
            if (node instanceof GlobalPositionAwareModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(256);
            }
            if (node instanceof FocusTargetNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(1024);
            }
            if (node instanceof FocusPropertiesModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(2048);
            }
            if (node instanceof FocusEventModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(PKIFailureInfo.certConfirmed);
            }
            if (node instanceof KeyInputModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(8192);
            }
            if (node instanceof RotaryInputModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(16384);
            }
            if (node instanceof CompositionLocalConsumerModifierNode) {
                iM1895constructorimpl |= NodeKind.m1895constructorimpl(32768);
            }
            int iM1895constructorimpl2 = node instanceof TraversableNode ? NodeKind.m1895constructorimpl(PKIFailureInfo.transactionIdInUse) | iM1895constructorimpl : iM1895constructorimpl;
            mutableObjectIntMap.set(objClassKeyForObject, iM1895constructorimpl2);
            return iM1895constructorimpl2;
        }
        return mutableObjectIntMap.values[iFindKeyIndex];
    }

    public static final void autoInvalidateRemovedNode(Modifier.Node node) {
        if (!node.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("autoInvalidateRemovedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 2);
    }

    public static final void autoInvalidateInsertedNode(Modifier.Node node) {
        if (!node.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("autoInvalidateInsertedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 1);
    }

    public static final void autoInvalidateUpdatedNode(Modifier.Node node) {
        if (!node.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("autoInvalidateUpdatedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 0);
    }

    public static final void autoInvalidateNodeIncludingDelegates(Modifier.Node node, int i, int i2) {
        if (node instanceof DelegatingNode) {
            DelegatingNode delegatingNode = (DelegatingNode) node;
            autoInvalidateNodeSelf(node, delegatingNode.getSelfKindSet$ui_release() & i, i2);
            int i3 = (~delegatingNode.getSelfKindSet$ui_release()) & i;
            for (Modifier.Node delegate$ui_release = delegatingNode.getDelegate$ui_release(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild$ui_release()) {
                autoInvalidateNodeIncludingDelegates(delegate$ui_release, i3, i2);
            }
            return;
        }
        autoInvalidateNodeSelf(node, i & node.getKindSet$ui_release(), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void autoInvalidateNodeSelf(Modifier.Node node, int i, int i2) {
        if (i2 != 0 || node.getShouldAutoInvalidate()) {
            if ((NodeKind.m1895constructorimpl(2) & i) != 0 && (node instanceof LayoutModifierNode)) {
                LayoutModifierNodeKt.invalidateMeasurement((LayoutModifierNode) node);
                if (i2 == 2) {
                    DelegatableNodeKt.m1795requireCoordinator64DMado(node, NodeKind.m1895constructorimpl(2)).onRelease();
                }
            }
            if ((NodeKind.m1895constructorimpl(128) & i) != 0 && (node instanceof LayoutAwareModifierNode) && i2 != 2) {
                DelegatableNodeKt.requireLayoutNode(node).invalidateMeasurements$ui_release();
            }
            if ((NodeKind.m1895constructorimpl(256) & i) != 0 && (node instanceof GlobalPositionAwareModifierNode) && i2 != 2) {
                DelegatableNodeKt.requireLayoutNode(node).invalidateOnPositioned$ui_release();
            }
            if ((NodeKind.m1895constructorimpl(4) & i) != 0 && (node instanceof DrawModifierNode)) {
                DrawModifierNodeKt.invalidateDraw((DrawModifierNode) node);
            }
            if ((NodeKind.m1895constructorimpl(8) & i) != 0 && (node instanceof SemanticsModifierNode)) {
                SemanticsModifierNodeKt.invalidateSemantics((SemanticsModifierNode) node);
            }
            if ((NodeKind.m1895constructorimpl(64) & i) != 0 && (node instanceof ParentDataModifierNode)) {
                ParentDataModifierNodeKt.invalidateParentData((ParentDataModifierNode) node);
            }
            if ((NodeKind.m1895constructorimpl(1024) & i) != 0 && (node instanceof FocusTargetNode) && i2 != 2) {
                FocusTargetNodeKt.invalidateFocusTarget((FocusTargetNode) node);
            }
            if ((NodeKind.m1895constructorimpl(2048) & i) != 0 && (node instanceof FocusPropertiesModifierNode)) {
                FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) node;
                if (specifiesCanFocusProperty(focusPropertiesModifierNode)) {
                    if (i2 == 2) {
                        scheduleInvalidationOfAssociatedFocusTargets(focusPropertiesModifierNode);
                    } else {
                        FocusPropertiesModifierNodeKt.invalidateFocusProperties(focusPropertiesModifierNode);
                    }
                }
            }
            if ((i & NodeKind.m1895constructorimpl(PKIFailureInfo.certConfirmed)) == 0 || !(node instanceof FocusEventModifierNode)) {
                return;
            }
            FocusEventModifierNodeKt.invalidateFocusEvent((FocusEventModifierNode) node);
        }
    }

    private static final boolean specifiesCanFocusProperty(FocusPropertiesModifierNode focusPropertiesModifierNode) {
        CanFocusChecker canFocusChecker = CanFocusChecker.INSTANCE;
        canFocusChecker.reset();
        focusPropertiesModifierNode.applyFocusProperties(canFocusChecker);
        return canFocusChecker.isCanFocusSet();
    }

    public static final int calculateNodeKindSetFromIncludingDelegates(Modifier.Node node) {
        if (node instanceof DelegatingNode) {
            DelegatingNode delegatingNode = (DelegatingNode) node;
            int selfKindSet$ui_release = delegatingNode.getSelfKindSet$ui_release();
            for (Modifier.Node delegate$ui_release = delegatingNode.getDelegate$ui_release(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild$ui_release()) {
                selfKindSet$ui_release |= calculateNodeKindSetFromIncludingDelegates(delegate$ui_release);
            }
            return selfKindSet$ui_release;
        }
        return calculateNodeKindSetFrom(node);
    }
}
