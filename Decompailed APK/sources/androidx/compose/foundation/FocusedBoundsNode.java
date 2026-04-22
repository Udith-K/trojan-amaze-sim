package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusedBounds.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FocusedBoundsNode extends Modifier.Node implements TraversableNode, GlobalPositionAwareModifierNode {
    private boolean isFocused;
    private LayoutCoordinates layoutCoordinates;
    private final boolean shouldAutoInvalidate;
    public static final TraverseKey TraverseKey = new TraverseKey(null);
    public static final int $stable = 8;

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return TraverseKey;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    private final FocusedBoundsObserverNode getObserver() {
        if (!isAttached()) {
            return null;
        }
        TraversableNode traversableNodeFindNearestAncestor = TraversableNodeKt.findNearestAncestor(this, FocusedBoundsObserverNode.TraverseKey);
        if (traversableNodeFindNearestAncestor instanceof FocusedBoundsObserverNode) {
            return (FocusedBoundsObserverNode) traversableNodeFindNearestAncestor;
        }
        return null;
    }

    public final void setFocus(boolean z) {
        if (z == this.isFocused) {
            return;
        }
        if (!z) {
            FocusedBoundsObserverNode observer = getObserver();
            if (observer != null) {
                observer.onFocusBoundsChanged(null);
            }
        } else {
            notifyObserverWhenAttached();
        }
        this.isFocused = z;
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates layoutCoordinates) {
        this.layoutCoordinates = layoutCoordinates;
        if (this.isFocused) {
            if (layoutCoordinates.isAttached()) {
                notifyObserverWhenAttached();
                return;
            }
            FocusedBoundsObserverNode observer = getObserver();
            if (observer != null) {
                observer.onFocusBoundsChanged(null);
            }
        }
    }

    private final void notifyObserverWhenAttached() {
        FocusedBoundsObserverNode observer;
        LayoutCoordinates layoutCoordinates = this.layoutCoordinates;
        if (layoutCoordinates != null) {
            Intrinsics.checkNotNull(layoutCoordinates);
            if (!layoutCoordinates.isAttached() || (observer = getObserver()) == null) {
                return;
            }
            observer.onFocusBoundsChanged(this.layoutCoordinates);
        }
    }

    /* JADX INFO: compiled from: FocusedBounds.kt */
    public static final class TraverseKey {
        public /* synthetic */ TraverseKey(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private TraverseKey() {
        }
    }
}
