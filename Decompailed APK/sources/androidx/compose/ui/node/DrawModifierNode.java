package androidx.compose.ui.node;

import androidx.compose.ui.graphics.drawscope.ContentDrawScope;

/* JADX INFO: compiled from: DrawModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public interface DrawModifierNode extends DelegatableNode {
    void draw(ContentDrawScope contentDrawScope);

    void onMeasureResultChanged();

    /* JADX INFO: renamed from: androidx.compose.ui.node.DrawModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: DrawModifierNode.kt */
    public abstract /* synthetic */ class CC {
        public static void $default$onMeasureResultChanged(DrawModifierNode drawModifierNode) {
        }
    }
}
