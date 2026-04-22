package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.node.DelegatableNode;

/* JADX INFO: compiled from: NestedScrollNode.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class NestedScrollNodeKt {
    public static final DelegatableNode nestedScrollModifierNode(NestedScrollConnection nestedScrollConnection, NestedScrollDispatcher nestedScrollDispatcher) {
        return new NestedScrollNode(nestedScrollConnection, nestedScrollDispatcher);
    }
}
