package androidx.compose.ui.graphics.layer;

import android.view.View;

/* JADX INFO: compiled from: GraphicsViewLayer.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class OutlineUtils {
    public static final OutlineUtils INSTANCE = new OutlineUtils();

    private OutlineUtils() {
    }

    public final boolean rebuildOutline(View view) {
        view.invalidateOutline();
        return true;
    }
}
