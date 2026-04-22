package androidx.compose.ui.input.key;

/* JADX INFO: compiled from: KeyEvent.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class KeyEvent_androidKt {
    /* JADX INFO: renamed from: getKey-ZmokQxo, reason: not valid java name */
    public static final long m1626getKeyZmokQxo(android.view.KeyEvent keyEvent) {
        return Key_androidKt.Key(keyEvent.getKeyCode());
    }

    /* JADX INFO: renamed from: getUtf16CodePoint-ZmokQxo, reason: not valid java name */
    public static final int m1628getUtf16CodePointZmokQxo(android.view.KeyEvent keyEvent) {
        return keyEvent.getUnicodeChar();
    }

    /* JADX INFO: renamed from: getType-ZmokQxo, reason: not valid java name */
    public static final int m1627getTypeZmokQxo(android.view.KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        if (action == 0) {
            return KeyEventType.Companion.m1623getKeyDownCS__XNY();
        }
        if (action == 1) {
            return KeyEventType.Companion.m1624getKeyUpCS__XNY();
        }
        return KeyEventType.Companion.m1625getUnknownCS__XNY();
    }

    /* JADX INFO: renamed from: isAltPressed-ZmokQxo, reason: not valid java name */
    public static final boolean m1629isAltPressedZmokQxo(android.view.KeyEvent keyEvent) {
        return keyEvent.isAltPressed();
    }

    /* JADX INFO: renamed from: isCtrlPressed-ZmokQxo, reason: not valid java name */
    public static final boolean m1630isCtrlPressedZmokQxo(android.view.KeyEvent keyEvent) {
        return keyEvent.isCtrlPressed();
    }

    /* JADX INFO: renamed from: isShiftPressed-ZmokQxo, reason: not valid java name */
    public static final boolean m1631isShiftPressedZmokQxo(android.view.KeyEvent keyEvent) {
        return keyEvent.isShiftPressed();
    }
}
