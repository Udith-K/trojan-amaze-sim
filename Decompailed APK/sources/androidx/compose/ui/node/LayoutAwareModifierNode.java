package androidx.compose.ui.node;

import androidx.compose.ui.layout.LayoutCoordinates;

/* JADX INFO: compiled from: LayoutAwareModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LayoutAwareModifierNode extends DelegatableNode {
    void onPlaced(LayoutCoordinates layoutCoordinates);

    /* JADX INFO: renamed from: onRemeasured-ozmzZPI */
    void mo174onRemeasuredozmzZPI(long j);

    /* JADX INFO: renamed from: androidx.compose.ui.node.LayoutAwareModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LayoutAwareModifierNode.kt */
    public abstract /* synthetic */ class CC {
        public static void $default$onPlaced(LayoutAwareModifierNode layoutAwareModifierNode, LayoutCoordinates layoutCoordinates) {
        }

        /* JADX INFO: renamed from: $default$onRemeasured-ozmzZPI, reason: not valid java name */
        public static void m1802$default$onRemeasuredozmzZPI(LayoutAwareModifierNode layoutAwareModifierNode, long j) {
        }
    }
}
