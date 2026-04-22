package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyLayoutSemantics.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0017\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b&\u0010'R\u0017\u0010\f\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\f\u0010%\u001a\u0004\b(\u0010'¨\u0006)"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticsModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticsModifierNode;", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "itemProviderLambda", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "state", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "", "userScrollEnabled", "reverseScrolling", "<init>", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;Landroidx/compose/foundation/gestures/Orientation;ZZ)V", "create", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticsModifierNode;", "node", "", "update", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticsModifierNode;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lkotlin/jvm/functions/Function0;", "getItemProviderLambda", "()Lkotlin/jvm/functions/Function0;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "getState", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "Landroidx/compose/foundation/gestures/Orientation;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "Z", "getUserScrollEnabled", "()Z", "getReverseScrolling", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class LazyLayoutSemanticsModifier extends ModifierNodeElement {
    private final Function0 itemProviderLambda;
    private final Orientation orientation;
    private final boolean reverseScrolling;
    private final LazyLayoutSemanticState state;
    private final boolean userScrollEnabled;

    public LazyLayoutSemanticsModifier(Function0 function0, LazyLayoutSemanticState lazyLayoutSemanticState, Orientation orientation, boolean z, boolean z2) {
        this.itemProviderLambda = function0;
        this.state = lazyLayoutSemanticState;
        this.orientation = orientation;
        this.userScrollEnabled = z;
        this.reverseScrolling = z2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public LazyLayoutSemanticsModifierNode getNode() {
        return new LazyLayoutSemanticsModifierNode(this.itemProviderLambda, this.state, this.orientation, this.userScrollEnabled, this.reverseScrolling);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(LazyLayoutSemanticsModifierNode node) {
        node.update(this.itemProviderLambda, this.state, this.orientation, this.userScrollEnabled, this.reverseScrolling);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LazyLayoutSemanticsModifier)) {
            return false;
        }
        LazyLayoutSemanticsModifier lazyLayoutSemanticsModifier = (LazyLayoutSemanticsModifier) other;
        return this.itemProviderLambda == lazyLayoutSemanticsModifier.itemProviderLambda && Intrinsics.areEqual(this.state, lazyLayoutSemanticsModifier.state) && this.orientation == lazyLayoutSemanticsModifier.orientation && this.userScrollEnabled == lazyLayoutSemanticsModifier.userScrollEnabled && this.reverseScrolling == lazyLayoutSemanticsModifier.reverseScrolling;
    }

    public int hashCode() {
        return (((((((this.itemProviderLambda.hashCode() * 31) + this.state.hashCode()) * 31) + this.orientation.hashCode()) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.userScrollEnabled)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.reverseScrolling);
    }
}
