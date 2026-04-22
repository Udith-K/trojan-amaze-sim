package androidx.compose.ui.node;

/* JADX INFO: compiled from: DrawModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DrawModifierNodeKt {
    public static final void invalidateDraw(DrawModifierNode drawModifierNode) {
        if (drawModifierNode.getNode().isAttached()) {
            DelegatableNodeKt.m1795requireCoordinator64DMado(drawModifierNode, NodeKind.m1895constructorimpl(1)).invalidateLayer();
        }
    }
}
