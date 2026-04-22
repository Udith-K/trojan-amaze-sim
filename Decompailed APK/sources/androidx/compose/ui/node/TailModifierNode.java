package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;

/* JADX INFO: compiled from: InnerNodeCoordinator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TailModifierNode extends Modifier.Node {
    private boolean attachHasBeenRun;

    public TailModifierNode() {
        setAggregateChildKindSet$ui_release(0);
    }

    public final boolean getAttachHasBeenRun() {
        return this.attachHasBeenRun;
    }

    public String toString() {
        return "<tail>";
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        this.attachHasBeenRun = true;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.attachHasBeenRun = false;
    }
}
