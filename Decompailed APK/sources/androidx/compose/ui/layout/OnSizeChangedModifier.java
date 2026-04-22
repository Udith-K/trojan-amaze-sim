package androidx.compose.ui.layout;

import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: OnRemeasuredModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0096\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/layout/OnSizeChangedModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/layout/OnSizeChangedNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/IntSize;", "", "onSizeChanged", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "create", "()Landroidx/compose/ui/layout/OnSizeChangedNode;", "node", "update", "(Landroidx/compose/ui/layout/OnSizeChangedNode;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lkotlin/jvm/functions/Function1;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class OnSizeChangedModifier extends ModifierNodeElement {
    private final Function1 onSizeChanged;

    public OnSizeChangedModifier(Function1 function1) {
        this.onSizeChanged = function1;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public OnSizeChangedNode create() {
        return new OnSizeChangedNode(this.onSizeChanged);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(OnSizeChangedNode node) {
        node.update(this.onSizeChanged);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof OnSizeChangedModifier) && this.onSizeChanged == ((OnSizeChangedModifier) other).onSizeChanged;
    }

    public int hashCode() {
        return this.onSizeChanged.hashCode();
    }
}
