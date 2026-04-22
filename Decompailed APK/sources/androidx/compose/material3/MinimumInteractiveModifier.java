package androidx.compose.material3;

import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.Metadata;

/* JADX INFO: compiled from: InteractiveComponentSize.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/MinimumInteractiveModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/material3/MinimumInteractiveModifierNode;", "<init>", "()V", "create", "()Landroidx/compose/material3/MinimumInteractiveModifierNode;", "node", "", "update", "(Landroidx/compose/material3/MinimumInteractiveModifierNode;)V", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MinimumInteractiveModifier extends ModifierNodeElement {
    public static final MinimumInteractiveModifier INSTANCE = new MinimumInteractiveModifier();

    public boolean equals(Object other) {
        return other == this;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(MinimumInteractiveModifierNode node) {
    }

    private MinimumInteractiveModifier() {
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public MinimumInteractiveModifierNode getNode() {
        return new MinimumInteractiveModifierNode();
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }
}
