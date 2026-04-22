package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusChangedModifier.kt */
/* JADX INFO: loaded from: classes.dex */
final class FocusChangedNode extends Modifier.Node implements FocusEventModifierNode {
    private FocusState focusState;
    private Function1 onFocusChanged;

    public final void setOnFocusChanged(Function1 function1) {
        this.onFocusChanged = function1;
    }

    public FocusChangedNode(Function1 function1) {
        this.onFocusChanged = function1;
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        if (Intrinsics.areEqual(this.focusState, focusState)) {
            return;
        }
        this.focusState = focusState;
        this.onFocusChanged.invoke(focusState);
    }
}
