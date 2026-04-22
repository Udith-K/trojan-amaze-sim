package androidx.compose.ui.input.key;

import androidx.compose.ui.node.DelegatableNode;

/* JADX INFO: compiled from: KeyInputModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public interface KeyInputModifierNode extends DelegatableNode {
    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo */
    boolean mo83onKeyEventZmokQxo(android.view.KeyEvent keyEvent);

    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo */
    boolean mo85onPreKeyEventZmokQxo(android.view.KeyEvent keyEvent);
}
