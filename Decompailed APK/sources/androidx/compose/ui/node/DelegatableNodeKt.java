package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DelegatableNode.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DelegatableNodeKt {
    public static final boolean isDelegationRoot(DelegatableNode delegatableNode) {
        return delegatableNode.getNode() == delegatableNode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final LayoutModifierNode asLayoutModifierNode(Modifier.Node node) {
        if ((NodeKind.m1895constructorimpl(2) & node.getKindSet$ui_release()) != 0) {
            if (node instanceof LayoutModifierNode) {
                return (LayoutModifierNode) node;
            }
            if (node instanceof DelegatingNode) {
                Modifier.Node delegate$ui_release = ((DelegatingNode) node).getDelegate$ui_release();
                while (delegate$ui_release != 0) {
                    if (delegate$ui_release instanceof LayoutModifierNode) {
                        return (LayoutModifierNode) delegate$ui_release;
                    }
                    if ((delegate$ui_release instanceof DelegatingNode) && (NodeKind.m1895constructorimpl(2) & delegate$ui_release.getKindSet$ui_release()) != 0) {
                        delegate$ui_release = ((DelegatingNode) delegate$ui_release).getDelegate$ui_release();
                    } else {
                        delegate$ui_release = delegate$ui_release.getChild$ui_release();
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addLayoutNodeChildren(MutableVector mutableVector, Modifier.Node node) {
        MutableVector mutableVector2 = requireLayoutNode(node).get_children$ui_release();
        int size = mutableVector2.getSize();
        if (size > 0) {
            int i = size - 1;
            Object[] content = mutableVector2.getContent();
            do {
                mutableVector.add(((LayoutNode) content[i]).getNodes$ui_release().getHead$ui_release());
                i--;
            } while (i >= 0);
        }
    }

    /* JADX INFO: renamed from: has-64DMado, reason: not valid java name */
    public static final boolean m1794has64DMado(DelegatableNode delegatableNode, int i) {
        return (delegatableNode.getNode().getAggregateChildKindSet$ui_release() & i) != 0;
    }

    /* JADX INFO: renamed from: requireCoordinator-64DMado, reason: not valid java name */
    public static final NodeCoordinator m1795requireCoordinator64DMado(DelegatableNode delegatableNode, int i) {
        NodeCoordinator coordinator$ui_release = delegatableNode.getNode().getCoordinator$ui_release();
        Intrinsics.checkNotNull(coordinator$ui_release);
        if (coordinator$ui_release.getTail() != delegatableNode || !NodeKindKt.m1896getIncludeSelfInTraversalH91voCI(i)) {
            return coordinator$ui_release;
        }
        NodeCoordinator wrapped$ui_release = coordinator$ui_release.getWrapped$ui_release();
        Intrinsics.checkNotNull(wrapped$ui_release);
        return wrapped$ui_release;
    }

    public static final LayoutNode requireLayoutNode(DelegatableNode delegatableNode) {
        NodeCoordinator coordinator$ui_release = delegatableNode.getNode().getCoordinator$ui_release();
        if (coordinator$ui_release == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Cannot obtain node coordinator. Is the Modifier.Node attached?");
            throw new KotlinNothingValueException();
        }
        return coordinator$ui_release.getLayoutNode();
    }

    public static final Owner requireOwner(DelegatableNode delegatableNode) {
        Owner owner$ui_release = requireLayoutNode(delegatableNode).getOwner$ui_release();
        if (owner$ui_release != null) {
            return owner$ui_release;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("This node does not have an owner.");
        throw new KotlinNothingValueException();
    }

    public static final Density requireDensity(DelegatableNode delegatableNode) {
        return requireLayoutNode(delegatableNode).getDensity();
    }

    public static final GraphicsContext requireGraphicsContext(DelegatableNode delegatableNode) {
        return requireOwner(delegatableNode).getGraphicsContext();
    }

    public static final LayoutDirection requireLayoutDirection(DelegatableNode delegatableNode) {
        return requireLayoutNode(delegatableNode).getLayoutDirection();
    }

    public static final LayoutCoordinates requireLayoutCoordinates(DelegatableNode delegatableNode) {
        if (!delegatableNode.getNode().isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("Cannot get LayoutCoordinates, Modifier.Node is not attached.");
        }
        LayoutCoordinates coordinates = m1795requireCoordinator64DMado(delegatableNode, NodeKind.m1895constructorimpl(2)).getCoordinates();
        if (!coordinates.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinates is not attached.");
        }
        return coordinates;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier.Node pop(MutableVector mutableVector) {
        if (mutableVector == null || mutableVector.isEmpty()) {
            return null;
        }
        return (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
    }
}
