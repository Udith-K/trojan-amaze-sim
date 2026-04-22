package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.TraversableNode;

/* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
/* JADX INFO: loaded from: classes.dex */
final class TraversablePrefetchStateNode extends Modifier.Node implements TraversableNode {
    private LazyLayoutPrefetchState prefetchState;
    private final String traverseKey = "androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode";

    public final LazyLayoutPrefetchState getPrefetchState() {
        return this.prefetchState;
    }

    public final void setPrefetchState(LazyLayoutPrefetchState lazyLayoutPrefetchState) {
        this.prefetchState = lazyLayoutPrefetchState;
    }

    public TraversablePrefetchStateNode(LazyLayoutPrefetchState lazyLayoutPrefetchState) {
        this.prefetchState = lazyLayoutPrefetchState;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public String getTraverseKey() {
        return this.traverseKey;
    }
}
