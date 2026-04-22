package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;

/* JADX INFO: compiled from: KeyEventHelpers.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class KeyEventHelpers_androidKt {
    public static final void showCharacterPalette() {
    }

    /* JADX INFO: renamed from: cancelsTextSelection-ZmokQxo, reason: not valid java name */
    public static final boolean m384cancelsTextSelectionZmokQxo(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 4 && KeyEventType.m1622equalsimpl0(KeyEvent_androidKt.m1627getTypeZmokQxo(keyEvent), KeyEventType.Companion.m1624getKeyUpCS__XNY());
    }
}
