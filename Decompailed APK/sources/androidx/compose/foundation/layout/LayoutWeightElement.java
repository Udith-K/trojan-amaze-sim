package androidx.compose.foundation.layout;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.Metadata;

/* JADX INFO: compiled from: RowColumnImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\n\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/layout/LayoutWeightElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/layout/LayoutWeightNode;", "", "weight", "", "fill", "<init>", "(FZ)V", "create", "()Landroidx/compose/foundation/layout/LayoutWeightNode;", "node", "", "update", "(Landroidx/compose/foundation/layout/LayoutWeightNode;)V", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "F", "getWeight", "()F", "Z", "getFill", "()Z", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LayoutWeightElement extends ModifierNodeElement {
    private final boolean fill;
    private final float weight;

    public LayoutWeightElement(float f, boolean z) {
        this.weight = f;
        this.fill = z;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public LayoutWeightNode getNode() {
        return new LayoutWeightNode(this.weight, this.fill);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(LayoutWeightNode node) {
        node.setWeight(this.weight);
        node.setFill(this.fill);
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.weight) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.fill);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        LayoutWeightElement layoutWeightElement = other instanceof LayoutWeightElement ? (LayoutWeightElement) other : null;
        if (layoutWeightElement == null) {
            return false;
        }
        return this.weight == layoutWeightElement.weight && this.fill == layoutWeightElement.fill;
    }
}
