package androidx.compose.ui.platform;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* JADX INFO: compiled from: PlatformTextInputMethodRequest.android.kt */
/* JADX INFO: loaded from: classes.dex */
public interface PlatformTextInputMethodRequest {
    InputConnection createInputConnection(EditorInfo editorInfo);
}
