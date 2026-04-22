package androidx.compose.ui.platform;

import android.view.ActionMode;
import android.view.View;

/* JADX INFO: compiled from: AndroidTextToolbar.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextToolbarHelperMethods {
    public static final TextToolbarHelperMethods INSTANCE = new TextToolbarHelperMethods();

    private TextToolbarHelperMethods() {
    }

    public final ActionMode startActionMode(View view, ActionMode.Callback callback, int i) {
        return view.startActionMode(callback, i);
    }

    public final void invalidateContentRect(ActionMode actionMode) {
        actionMode.invalidateContentRect();
    }
}
