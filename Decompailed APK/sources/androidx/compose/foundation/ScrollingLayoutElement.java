package androidx.compose.foundation;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Scroll.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\t\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u0006\u0010\u001bR\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b\u0007\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/ScrollingLayoutElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/ScrollingLayoutNode;", "Landroidx/compose/foundation/ScrollState;", "scrollState", "", "isReversed", "isVertical", "<init>", "(Landroidx/compose/foundation/ScrollState;ZZ)V", "create", "()Landroidx/compose/foundation/ScrollingLayoutNode;", "node", "", "update", "(Landroidx/compose/foundation/ScrollingLayoutNode;)V", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/foundation/ScrollState;", "getScrollState", "()Landroidx/compose/foundation/ScrollState;", "Z", "()Z", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScrollingLayoutElement extends ModifierNodeElement {
    private final boolean isReversed;
    private final boolean isVertical;
    private final ScrollState scrollState;

    public ScrollingLayoutElement(ScrollState scrollState, boolean z, boolean z2) {
        this.scrollState = scrollState;
        this.isReversed = z;
        this.isVertical = z2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public ScrollingLayoutNode getNode() {
        return new ScrollingLayoutNode(this.scrollState, this.isReversed, this.isVertical);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(ScrollingLayoutNode node) {
        node.setScrollerState(this.scrollState);
        node.setReversed(this.isReversed);
        node.setVertical(this.isVertical);
    }

    public int hashCode() {
        return (((this.scrollState.hashCode() * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isReversed)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isVertical);
    }

    public boolean equals(Object other) {
        if (!(other instanceof ScrollingLayoutElement)) {
            return false;
        }
        ScrollingLayoutElement scrollingLayoutElement = (ScrollingLayoutElement) other;
        return Intrinsics.areEqual(this.scrollState, scrollingLayoutElement.scrollState) && this.isReversed == scrollingLayoutElement.isReversed && this.isVertical == scrollingLayoutElement.isVertical;
    }
}
