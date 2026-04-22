package androidx.compose.foundation.text;

import android.view.InputDevice;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldFocusModifier.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldFocusModifier_androidKt {
    public static final Modifier interceptDPadAndMoveFocus(Modifier modifier, final LegacyTextFieldState legacyTextFieldState, final FocusManager focusManager) {
        return KeyInputModifierKt.onPreviewKeyEvent(modifier, new Function1() { // from class: androidx.compose.foundation.text.TextFieldFocusModifier_androidKt.interceptDPadAndMoveFocus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m444invokeZmokQxo(((KeyEvent) obj).m1620unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m444invokeZmokQxo(android.view.KeyEvent keyEvent) {
                InputDevice device = keyEvent.getDevice();
                boolean zMo1105moveFocus3ESFkO8 = false;
                if (device != null && device.supportsSource(513) && !device.isVirtual() && KeyEventType.m1622equalsimpl0(KeyEvent_androidKt.m1627getTypeZmokQxo(keyEvent), KeyEventType.Companion.m1623getKeyDownCS__XNY()) && keyEvent.getSource() != 257) {
                    if (TextFieldFocusModifier_androidKt.m443isKeyCodeYhN2O0w(keyEvent, 19)) {
                        zMo1105moveFocus3ESFkO8 = focusManager.mo1105moveFocus3ESFkO8(FocusDirection.Companion.m1102getUpdhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m443isKeyCodeYhN2O0w(keyEvent, 20)) {
                        zMo1105moveFocus3ESFkO8 = focusManager.mo1105moveFocus3ESFkO8(FocusDirection.Companion.m1095getDowndhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m443isKeyCodeYhN2O0w(keyEvent, 21)) {
                        zMo1105moveFocus3ESFkO8 = focusManager.mo1105moveFocus3ESFkO8(FocusDirection.Companion.m1098getLeftdhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m443isKeyCodeYhN2O0w(keyEvent, 22)) {
                        zMo1105moveFocus3ESFkO8 = focusManager.mo1105moveFocus3ESFkO8(FocusDirection.Companion.m1101getRightdhqQ8s());
                    } else if (TextFieldFocusModifier_androidKt.m443isKeyCodeYhN2O0w(keyEvent, 23)) {
                        SoftwareKeyboardController keyboardController = legacyTextFieldState.getKeyboardController();
                        if (keyboardController != null) {
                            keyboardController.show();
                        }
                        zMo1105moveFocus3ESFkO8 = true;
                    }
                }
                return Boolean.valueOf(zMo1105moveFocus3ESFkO8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isKeyCode-YhN2O0w, reason: not valid java name */
    public static final boolean m443isKeyCodeYhN2O0w(android.view.KeyEvent keyEvent, int i) {
        return Key_androidKt.m1632getNativeKeyCodeYVgTNJs(KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent)) == i;
    }
}
