package androidx.compose.ui.window;

import android.graphics.Rect;
import android.view.View;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: AndroidPopup.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class PopupLayoutHelperImpl29 extends PopupLayoutHelperImpl {
    @Override // androidx.compose.ui.window.PopupLayoutHelperImpl, androidx.compose.ui.window.PopupLayoutHelper
    public void setGestureExclusionRects(View view, int i, int i2) {
        view.setSystemGestureExclusionRects(CollectionsKt.mutableListOf(new Rect(0, 0, i, i2)));
    }
}
