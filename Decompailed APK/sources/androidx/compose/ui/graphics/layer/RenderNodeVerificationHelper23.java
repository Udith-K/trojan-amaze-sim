package androidx.compose.ui.graphics.layer;

import android.view.RenderNode;

/* JADX INFO: compiled from: GraphicsLayerV23.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class RenderNodeVerificationHelper23 {
    public static final RenderNodeVerificationHelper23 INSTANCE = new RenderNodeVerificationHelper23();

    private RenderNodeVerificationHelper23() {
    }

    public final void destroyDisplayListData(RenderNode renderNode) {
        renderNode.destroyDisplayListData();
    }
}
