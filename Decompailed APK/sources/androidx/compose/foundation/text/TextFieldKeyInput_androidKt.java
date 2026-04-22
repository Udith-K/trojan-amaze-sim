package androidx.compose.foundation.text;

import android.view.KeyEvent;

/* JADX INFO: compiled from: TextFieldKeyInput.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldKeyInput_androidKt {
    /* JADX INFO: renamed from: isTypedEvent-ZmokQxo, reason: not valid java name */
    public static final boolean m449isTypedEventZmokQxo(KeyEvent keyEvent) {
        return keyEvent.getAction() == 0 && !Character.isISOControl(keyEvent.getUnicodeChar());
    }
}
