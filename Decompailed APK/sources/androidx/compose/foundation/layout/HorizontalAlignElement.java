package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RowColumnImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/layout/HorizontalAlignElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/layout/HorizontalAlignNode;", "Landroidx/compose/ui/Alignment$Horizontal;", "horizontal", "<init>", "(Landroidx/compose/ui/Alignment$Horizontal;)V", "create", "()Landroidx/compose/foundation/layout/HorizontalAlignNode;", "node", "", "update", "(Landroidx/compose/foundation/layout/HorizontalAlignNode;)V", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/Alignment$Horizontal;", "getHorizontal", "()Landroidx/compose/ui/Alignment$Horizontal;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HorizontalAlignElement extends ModifierNodeElement {
    private final Alignment.Horizontal horizontal;

    public HorizontalAlignElement(Alignment.Horizontal horizontal) {
        this.horizontal = horizontal;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public HorizontalAlignNode getNode() {
        return new HorizontalAlignNode(this.horizontal);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(HorizontalAlignNode node) {
        node.setHorizontal(this.horizontal);
    }

    public int hashCode() {
        return this.horizontal.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        HorizontalAlignElement horizontalAlignElement = other instanceof HorizontalAlignElement ? (HorizontalAlignElement) other : null;
        if (horizontalAlignElement == null) {
            return false;
        }
        return Intrinsics.areEqual(this.horizontal, horizontalAlignElement.horizontal);
    }
}
