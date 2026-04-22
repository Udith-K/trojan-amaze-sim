package androidx.compose.foundation.text.handwriting;

import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: StylusHandwriting.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StylusHandwritingNode extends DelegatingNode implements PointerInputModifierNode, FocusEventModifierNode {
    private boolean focused;
    private Function0 onHandwritingSlopExceeded;
    private final SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = (SuspendingPointerInputModifierNode) delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new StylusHandwritingNode$suspendingPointerInputModifierNode$1(this, null)));

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return PointerInputModifierNode.CC.$default$interceptOutOfBoundsChildEvents(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ void onDensityChange() {
        onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ void onViewConfigurationChange() {
        onCancelPointerInput();
    }

    public final Function0 getOnHandwritingSlopExceeded() {
        return this.onHandwritingSlopExceeded;
    }

    public final void setOnHandwritingSlopExceeded(Function0 function0) {
        this.onHandwritingSlopExceeded = function0;
    }

    public StylusHandwritingNode(Function0 function0) {
        this.onHandwritingSlopExceeded = function0;
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        this.focused = focusState.isFocused();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo84onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        this.suspendingPointerInputModifierNode.mo84onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        this.suspendingPointerInputModifierNode.onCancelPointerInput();
    }
}
