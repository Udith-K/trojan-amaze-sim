package androidx.compose.ui.window;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/* JADX INFO: compiled from: AndroidPopup.android.kt */
/* JADX INFO: loaded from: classes.dex */
public interface PopupLayoutHelper {
    void getWindowVisibleDisplayFrame(View view, Rect rect);

    void setGestureExclusionRects(View view, int i, int i2);

    void updateViewLayout(WindowManager windowManager, View view, ViewGroup.LayoutParams layoutParams);
}
