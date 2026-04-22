package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.contextmenu.ContextMenuState;

/* JADX INFO: compiled from: ContextMenuState.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContextMenuState_androidKt {
    public static final void close(ContextMenuState contextMenuState) {
        contextMenuState.setStatus(ContextMenuState.Status.Closed.INSTANCE);
    }
}
