package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.node.ModifierNodeElement;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0083\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/foundation/lazy/layout/TraversablePrefetchStateModifierElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/TraversablePrefetchStateNode;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "prefetchState", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;)V", "create", "()Landroidx/compose/foundation/lazy/layout/TraversablePrefetchStateNode;", "node", "", "update", "(Landroidx/compose/foundation/lazy/layout/TraversablePrefetchStateNode;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class TraversablePrefetchStateModifierElement extends ModifierNodeElement {
    private final LazyLayoutPrefetchState prefetchState;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TraversablePrefetchStateModifierElement) && Intrinsics.areEqual(this.prefetchState, ((TraversablePrefetchStateModifierElement) other).prefetchState);
    }

    public int hashCode() {
        return this.prefetchState.hashCode();
    }

    public String toString() {
        return "TraversablePrefetchStateModifierElement(prefetchState=" + this.prefetchState + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public TraversablePrefetchStateModifierElement(LazyLayoutPrefetchState lazyLayoutPrefetchState) {
        this.prefetchState = lazyLayoutPrefetchState;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public TraversablePrefetchStateNode getNode() {
        return new TraversablePrefetchStateNode(this.prefetchState);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(TraversablePrefetchStateNode node) {
        node.setPrefetchState(this.prefetchState);
    }
}
