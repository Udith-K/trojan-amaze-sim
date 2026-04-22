package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;

/* JADX INFO: compiled from: KeyMapping.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class KeyMappingKt {
    private static final KeyMapping defaultKeyMapping;

    public static final KeyMapping commonKeyMapping(final Function1 function1) {
        return new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt.commonKeyMapping.1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo385mapZmokQxo(KeyEvent keyEvent) {
                if (((Boolean) function1.invoke(androidx.compose.ui.input.key.KeyEvent.m1615boximpl(keyEvent))).booleanValue() && KeyEvent_androidKt.m1631isShiftPressedZmokQxo(keyEvent)) {
                    if (Key.m1599equalsimpl0(KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent), MappedKeys.INSTANCE.m427getZEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    return null;
                }
                if (((Boolean) function1.invoke(androidx.compose.ui.input.key.KeyEvent.m1615boximpl(keyEvent))).booleanValue()) {
                    long jM1626getKeyZmokQxo = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
                    MappedKeys mappedKeys = MappedKeys.INSTANCE;
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m407getCEK5gGoQ()) ? true : Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m417getInsertEK5gGoQ())) {
                        return KeyCommand.COPY;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m424getVEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m425getXEK5gGoQ())) {
                        return KeyCommand.CUT;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m404getAEK5gGoQ())) {
                        return KeyCommand.SELECT_ALL;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m426getYEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m427getZEK5gGoQ())) {
                        return KeyCommand.UNDO;
                    }
                    return null;
                }
                if (KeyEvent_androidKt.m1630isCtrlPressedZmokQxo(keyEvent)) {
                    return null;
                }
                if (KeyEvent_androidKt.m1631isShiftPressedZmokQxo(keyEvent)) {
                    long jM1626getKeyZmokQxo2 = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
                    MappedKeys mappedKeys2 = MappedKeys.INSTANCE;
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m412getDirectionLeftEK5gGoQ())) {
                        return KeyCommand.SELECT_LEFT_CHAR;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m413getDirectionRightEK5gGoQ())) {
                        return KeyCommand.SELECT_RIGHT_CHAR;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m414getDirectionUpEK5gGoQ())) {
                        return KeyCommand.SELECT_UP;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m411getDirectionDownEK5gGoQ())) {
                        return KeyCommand.SELECT_DOWN;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m421getPageUpEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_UP;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m420getPageDownEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_DOWN;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m419getMoveHomeEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_START;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m418getMoveEndEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_END;
                    }
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m417getInsertEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    return null;
                }
                long jM1626getKeyZmokQxo3 = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
                MappedKeys mappedKeys3 = MappedKeys.INSTANCE;
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m412getDirectionLeftEK5gGoQ())) {
                    return KeyCommand.LEFT_CHAR;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m413getDirectionRightEK5gGoQ())) {
                    return KeyCommand.RIGHT_CHAR;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m414getDirectionUpEK5gGoQ())) {
                    return KeyCommand.UP;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m411getDirectionDownEK5gGoQ())) {
                    return KeyCommand.DOWN;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m421getPageUpEK5gGoQ())) {
                    return KeyCommand.PAGE_UP;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m420getPageDownEK5gGoQ())) {
                    return KeyCommand.PAGE_DOWN;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m419getMoveHomeEK5gGoQ())) {
                    return KeyCommand.LINE_START;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m418getMoveEndEK5gGoQ())) {
                    return KeyCommand.LINE_END;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m415getEnterEK5gGoQ())) {
                    return KeyCommand.NEW_LINE;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m406getBackspaceEK5gGoQ())) {
                    return KeyCommand.DELETE_PREV_CHAR;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m410getDeleteEK5gGoQ())) {
                    return KeyCommand.DELETE_NEXT_CHAR;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m422getPasteEK5gGoQ())) {
                    return KeyCommand.PASTE;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m409getCutEK5gGoQ())) {
                    return KeyCommand.CUT;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m408getCopyEK5gGoQ())) {
                    return KeyCommand.COPY;
                }
                if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m423getTabEK5gGoQ())) {
                    return KeyCommand.TAB;
                }
                return null;
            }
        };
    }

    public static final KeyMapping getDefaultKeyMapping() {
        return defaultKeyMapping;
    }

    static {
        final KeyMapping keyMappingCommonKeyMapping = commonKeyMapping(new PropertyReference1Impl() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return Boolean.valueOf(KeyEvent_androidKt.m1630isCtrlPressedZmokQxo(((androidx.compose.ui.input.key.KeyEvent) obj).m1620unboximpl()));
            }
        });
        defaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$2$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo385mapZmokQxo(KeyEvent keyEvent) {
                KeyCommand keyCommand = null;
                if (KeyEvent_androidKt.m1631isShiftPressedZmokQxo(keyEvent) && KeyEvent_androidKt.m1630isCtrlPressedZmokQxo(keyEvent)) {
                    long jM1626getKeyZmokQxo = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
                    MappedKeys mappedKeys = MappedKeys.INSTANCE;
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m412getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LEFT_WORD;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m413getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_RIGHT_WORD;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m414getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_PREV_PARAGRAPH;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo, mappedKeys.m411getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_NEXT_PARAGRAPH;
                    }
                } else if (KeyEvent_androidKt.m1630isCtrlPressedZmokQxo(keyEvent)) {
                    long jM1626getKeyZmokQxo2 = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
                    MappedKeys mappedKeys2 = MappedKeys.INSTANCE;
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m412getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.LEFT_WORD;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m413getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.RIGHT_WORD;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m414getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.PREV_PARAGRAPH;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m411getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.NEXT_PARAGRAPH;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m416getHEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_CHAR;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m410getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_NEXT_WORD;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m406getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_WORD;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo2, mappedKeys2.m405getBackslashEK5gGoQ())) {
                        keyCommand = KeyCommand.DESELECT;
                    }
                } else if (KeyEvent_androidKt.m1631isShiftPressedZmokQxo(keyEvent)) {
                    long jM1626getKeyZmokQxo3 = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
                    MappedKeys mappedKeys3 = MappedKeys.INSTANCE;
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m419getMoveHomeEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_LEFT;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo3, mappedKeys3.m418getMoveEndEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_RIGHT;
                    }
                } else if (KeyEvent_androidKt.m1629isAltPressedZmokQxo(keyEvent)) {
                    long jM1626getKeyZmokQxo4 = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
                    MappedKeys mappedKeys4 = MappedKeys.INSTANCE;
                    if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo4, mappedKeys4.m406getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_FROM_LINE_START;
                    } else if (Key.m1599equalsimpl0(jM1626getKeyZmokQxo4, mappedKeys4.m410getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_TO_LINE_END;
                    }
                }
                return keyCommand == null ? keyMappingCommonKeyMapping.mo385mapZmokQxo(keyEvent) : keyCommand;
            }
        };
    }
}
