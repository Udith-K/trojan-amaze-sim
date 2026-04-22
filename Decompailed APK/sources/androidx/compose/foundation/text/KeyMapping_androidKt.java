package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;

/* JADX INFO: compiled from: KeyMapping.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class KeyMapping_androidKt {
    private static final KeyMapping platformDefaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMapping_androidKt$platformDefaultKeyMapping$1
        @Override // androidx.compose.foundation.text.KeyMapping
        /* JADX INFO: renamed from: map-ZmokQxo */
        public KeyCommand mo385mapZmokQxo(KeyEvent keyEvent) {
            KeyCommand keyCommand = null;
            if (KeyEvent_androidKt.m1631isShiftPressedZmokQxo(keyEvent) && KeyEvent_androidKt.m1629isAltPressedZmokQxo(keyEvent)) {
                long jM1626getKeyZmokQxo = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
                MappedKeys mappedKeys = MappedKeys.INSTANCE;
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m412getDirectionLeftEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_LINE_LEFT;
                } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m413getDirectionRightEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_LINE_RIGHT;
                } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m414getDirectionUpEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_HOME;
                } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m411getDirectionDownEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_END;
                }
            } else if (KeyEvent_androidKt.m1629isAltPressedZmokQxo(keyEvent)) {
                long jM1626getKeyZmokQxo2 = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
                MappedKeys mappedKeys2 = MappedKeys.INSTANCE;
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m412getDirectionLeftEK5gGoQ())) {
                    keyCommand = KeyCommand.LINE_LEFT;
                } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m413getDirectionRightEK5gGoQ())) {
                    keyCommand = KeyCommand.LINE_RIGHT;
                } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m414getDirectionUpEK5gGoQ())) {
                    keyCommand = KeyCommand.HOME;
                } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m411getDirectionDownEK5gGoQ())) {
                    keyCommand = KeyCommand.END;
                }
            }
            return keyCommand == null ? KeyMappingKt.getDefaultKeyMapping().mo385mapZmokQxo(keyEvent) : keyCommand;
        }
    };

    public static final KeyMapping getPlatformDefaultKeyMapping() {
        return platformDefaultKeyMapping;
    }
}
