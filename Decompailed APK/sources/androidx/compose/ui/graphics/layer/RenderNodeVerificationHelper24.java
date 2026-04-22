package androidx.compose.ui.graphics.layer;

import android.view.RenderNode;

/* JADX INFO: compiled from: GraphicsLayerV23.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class RenderNodeVerificationHelper24 {
    public static final RenderNodeVerificationHelper24 INSTANCE = new RenderNodeVerificationHelper24();

    private RenderNodeVerificationHelper24() {
    }

    public final void discardDisplayList(RenderNode renderNode) {
        renderNode.discardDisplayList();
    }
}
