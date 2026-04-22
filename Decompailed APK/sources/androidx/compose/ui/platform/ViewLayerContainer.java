package androidx.compose.ui.platform;

import android.content.Context;
import android.graphics.Canvas;

/* JADX INFO: compiled from: ViewLayerContainer.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ViewLayerContainer extends DrawChildContainer {
    @Override // androidx.compose.ui.platform.DrawChildContainer, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
    }

    protected final void dispatchGetDisplayList() {
    }

    public ViewLayerContainer(Context context) {
        super(context);
    }
}
