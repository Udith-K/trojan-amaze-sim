package androidx.compose.ui.platform;

import android.view.View;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: PlatformTextInputSession.android.kt */
/* JADX INFO: loaded from: classes.dex */
public interface PlatformTextInputSession {
    View getView();

    Object startInputMethod(PlatformTextInputMethodRequest platformTextInputMethodRequest, Continuation continuation);
}
