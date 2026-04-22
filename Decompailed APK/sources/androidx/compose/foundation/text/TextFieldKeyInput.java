package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.foundation.text.selection.TextFieldPreparedSelection;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextPreparedSelectionState;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextCommand;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import org.bouncycastle.asn1.eac.EACTags;

/* JADX INFO: compiled from: TextFieldKeyInput.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextFieldKeyInput {
    private final boolean editable;
    private final int imeAction;
    private final DeadKeyCombiner keyCombiner;
    private final KeyMapping keyMapping;
    private final OffsetMapping offsetMapping;
    private final Function1 onValueChange;
    private final TextPreparedSelectionState preparedSelectionState;
    private final TextFieldSelectionManager selectionManager;
    private final boolean singleLine;
    private final LegacyTextFieldState state;
    private final UndoManager undoManager;
    private final TextFieldValue value;

    public /* synthetic */ TextFieldKeyInput(LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, boolean z, boolean z2, TextPreparedSelectionState textPreparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, DeadKeyCombiner deadKeyCombiner, KeyMapping keyMapping, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(legacyTextFieldState, textFieldSelectionManager, textFieldValue, z, z2, textPreparedSelectionState, offsetMapping, undoManager, deadKeyCombiner, keyMapping, function1, i);
    }

    private TextFieldKeyInput(LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, boolean z, boolean z2, TextPreparedSelectionState textPreparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, DeadKeyCombiner deadKeyCombiner, KeyMapping keyMapping, Function1 function1, int i) {
        this.state = legacyTextFieldState;
        this.selectionManager = textFieldSelectionManager;
        this.value = textFieldValue;
        this.editable = z;
        this.singleLine = z2;
        this.preparedSelectionState = textPreparedSelectionState;
        this.offsetMapping = offsetMapping;
        this.undoManager = undoManager;
        this.keyCombiner = deadKeyCombiner;
        this.keyMapping = keyMapping;
        this.onValueChange = function1;
        this.imeAction = i;
    }

    public final LegacyTextFieldState getState() {
        return this.state;
    }

    public final TextFieldSelectionManager getSelectionManager() {
        return this.selectionManager;
    }

    public /* synthetic */ TextFieldKeyInput(LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, boolean z, boolean z2, TextPreparedSelectionState textPreparedSelectionState, OffsetMapping offsetMapping, UndoManager undoManager, DeadKeyCombiner deadKeyCombiner, KeyMapping keyMapping, Function1 function1, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(legacyTextFieldState, textFieldSelectionManager, (i2 & 4) != 0 ? new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null) : textFieldValue, (i2 & 8) != 0 ? true : z, (i2 & 16) != 0 ? false : z2, textPreparedSelectionState, (i2 & 64) != 0 ? OffsetMapping.Companion.getIdentity() : offsetMapping, (i2 & 128) != 0 ? null : undoManager, deadKeyCombiner, (i2 & 512) != 0 ? KeyMapping_androidKt.getPlatformDefaultKeyMapping() : keyMapping, (i2 & 1024) != 0 ? new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput.1
            public final void invoke(TextFieldValue textFieldValue2) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((TextFieldValue) obj);
                return Unit.INSTANCE;
            }
        } : function1, i, null);
    }

    public final boolean getSingleLine() {
        return this.singleLine;
    }

    public final UndoManager getUndoManager() {
        return this.undoManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void apply(List list) {
        EditProcessor processor = this.state.getProcessor();
        List mutableList = CollectionsKt.toMutableList((Collection) list);
        mutableList.add(0, new FinishComposingTextCommand());
        this.onValueChange.invoke(processor.apply(mutableList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void apply(EditCommand editCommand) {
        apply(CollectionsKt.listOf(editCommand));
    }

    /* JADX INFO: renamed from: typedCommand-ZmokQxo, reason: not valid java name */
    private final CommitTextCommand m445typedCommandZmokQxo(KeyEvent keyEvent) {
        Integer numM383consumeZmokQxo;
        if (TextFieldKeyInput_androidKt.m449isTypedEventZmokQxo(keyEvent) && (numM383consumeZmokQxo = this.keyCombiner.m383consumeZmokQxo(keyEvent)) != null) {
            return new CommitTextCommand(StringHelpers_jvmKt.appendCodePointX(new StringBuilder(), numM383consumeZmokQxo.intValue()).toString(), 1);
        }
        return null;
    }

    /* JADX INFO: renamed from: process-ZmokQxo, reason: not valid java name */
    public final boolean m446processZmokQxo(KeyEvent keyEvent) {
        final KeyCommand keyCommandMo385mapZmokQxo;
        CommitTextCommand commitTextCommandM445typedCommandZmokQxo = m445typedCommandZmokQxo(keyEvent);
        if (commitTextCommandM445typedCommandZmokQxo != null) {
            if (!this.editable) {
                return false;
            }
            apply(commitTextCommandM445typedCommandZmokQxo);
            this.preparedSelectionState.resetCachedX();
            return true;
        }
        if (!KeyEventType.m1622equalsimpl0(KeyEvent_androidKt.m1627getTypeZmokQxo(keyEvent), KeyEventType.Companion.m1623getKeyDownCS__XNY()) || (keyCommandMo385mapZmokQxo = this.keyMapping.mo385mapZmokQxo(keyEvent)) == null || (keyCommandMo385mapZmokQxo.getEditsText() && !this.editable)) {
            return false;
        }
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        ref$BooleanRef.element = true;
        commandExecutionContext(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2

            /* JADX INFO: compiled from: TextFieldKeyInput.kt */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[KeyCommand.values().length];
                    try {
                        iArr[KeyCommand.COPY.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[KeyCommand.PASTE.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[KeyCommand.CUT.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[KeyCommand.LEFT_CHAR.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    try {
                        iArr[KeyCommand.RIGHT_CHAR.ordinal()] = 5;
                    } catch (NoSuchFieldError unused5) {
                    }
                    try {
                        iArr[KeyCommand.LEFT_WORD.ordinal()] = 6;
                    } catch (NoSuchFieldError unused6) {
                    }
                    try {
                        iArr[KeyCommand.RIGHT_WORD.ordinal()] = 7;
                    } catch (NoSuchFieldError unused7) {
                    }
                    try {
                        iArr[KeyCommand.PREV_PARAGRAPH.ordinal()] = 8;
                    } catch (NoSuchFieldError unused8) {
                    }
                    try {
                        iArr[KeyCommand.NEXT_PARAGRAPH.ordinal()] = 9;
                    } catch (NoSuchFieldError unused9) {
                    }
                    try {
                        iArr[KeyCommand.UP.ordinal()] = 10;
                    } catch (NoSuchFieldError unused10) {
                    }
                    try {
                        iArr[KeyCommand.DOWN.ordinal()] = 11;
                    } catch (NoSuchFieldError unused11) {
                    }
                    try {
                        iArr[KeyCommand.PAGE_UP.ordinal()] = 12;
                    } catch (NoSuchFieldError unused12) {
                    }
                    try {
                        iArr[KeyCommand.PAGE_DOWN.ordinal()] = 13;
                    } catch (NoSuchFieldError unused13) {
                    }
                    try {
                        iArr[KeyCommand.LINE_START.ordinal()] = 14;
                    } catch (NoSuchFieldError unused14) {
                    }
                    try {
                        iArr[KeyCommand.LINE_END.ordinal()] = 15;
                    } catch (NoSuchFieldError unused15) {
                    }
                    try {
                        iArr[KeyCommand.LINE_LEFT.ordinal()] = 16;
                    } catch (NoSuchFieldError unused16) {
                    }
                    try {
                        iArr[KeyCommand.LINE_RIGHT.ordinal()] = 17;
                    } catch (NoSuchFieldError unused17) {
                    }
                    try {
                        iArr[KeyCommand.HOME.ordinal()] = 18;
                    } catch (NoSuchFieldError unused18) {
                    }
                    try {
                        iArr[KeyCommand.END.ordinal()] = 19;
                    } catch (NoSuchFieldError unused19) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_PREV_CHAR.ordinal()] = 20;
                    } catch (NoSuchFieldError unused20) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_NEXT_CHAR.ordinal()] = 21;
                    } catch (NoSuchFieldError unused21) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_PREV_WORD.ordinal()] = 22;
                    } catch (NoSuchFieldError unused22) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_NEXT_WORD.ordinal()] = 23;
                    } catch (NoSuchFieldError unused23) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_FROM_LINE_START.ordinal()] = 24;
                    } catch (NoSuchFieldError unused24) {
                    }
                    try {
                        iArr[KeyCommand.DELETE_TO_LINE_END.ordinal()] = 25;
                    } catch (NoSuchFieldError unused25) {
                    }
                    try {
                        iArr[KeyCommand.NEW_LINE.ordinal()] = 26;
                    } catch (NoSuchFieldError unused26) {
                    }
                    try {
                        iArr[KeyCommand.TAB.ordinal()] = 27;
                    } catch (NoSuchFieldError unused27) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_ALL.ordinal()] = 28;
                    } catch (NoSuchFieldError unused28) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LEFT_CHAR.ordinal()] = 29;
                    } catch (NoSuchFieldError unused29) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_RIGHT_CHAR.ordinal()] = 30;
                    } catch (NoSuchFieldError unused30) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LEFT_WORD.ordinal()] = 31;
                    } catch (NoSuchFieldError unused31) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_RIGHT_WORD.ordinal()] = 32;
                    } catch (NoSuchFieldError unused32) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_PREV_PARAGRAPH.ordinal()] = 33;
                    } catch (NoSuchFieldError unused33) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_NEXT_PARAGRAPH.ordinal()] = 34;
                    } catch (NoSuchFieldError unused34) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LINE_START.ordinal()] = 35;
                    } catch (NoSuchFieldError unused35) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LINE_END.ordinal()] = 36;
                    } catch (NoSuchFieldError unused36) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LINE_LEFT.ordinal()] = 37;
                    } catch (NoSuchFieldError unused37) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_LINE_RIGHT.ordinal()] = 38;
                    } catch (NoSuchFieldError unused38) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_UP.ordinal()] = 39;
                    } catch (NoSuchFieldError unused39) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_DOWN.ordinal()] = 40;
                    } catch (NoSuchFieldError unused40) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_PAGE_UP.ordinal()] = 41;
                    } catch (NoSuchFieldError unused41) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_PAGE_DOWN.ordinal()] = 42;
                    } catch (NoSuchFieldError unused42) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_HOME.ordinal()] = 43;
                    } catch (NoSuchFieldError unused43) {
                    }
                    try {
                        iArr[KeyCommand.SELECT_END.ordinal()] = 44;
                    } catch (NoSuchFieldError unused44) {
                    }
                    try {
                        iArr[KeyCommand.DESELECT.ordinal()] = 45;
                    } catch (NoSuchFieldError unused45) {
                    }
                    try {
                        iArr[KeyCommand.UNDO.ordinal()] = 46;
                    } catch (NoSuchFieldError unused46) {
                    }
                    try {
                        iArr[KeyCommand.REDO.ordinal()] = 47;
                    } catch (NoSuchFieldError unused47) {
                    }
                    try {
                        iArr[KeyCommand.CHARACTER_PALETTE.ordinal()] = 48;
                    } catch (NoSuchFieldError unused48) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((TextFieldPreparedSelection) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(TextFieldPreparedSelection textFieldPreparedSelection) {
                TextFieldValue textFieldValueUndo;
                TextFieldValue textFieldValueRedo;
                switch (WhenMappings.$EnumSwitchMapping$0[keyCommandMo385mapZmokQxo.ordinal()]) {
                    case 1:
                        this.getSelectionManager().copy$foundation_release(false);
                        break;
                    case 2:
                        this.getSelectionManager().paste$foundation_release();
                        break;
                    case 3:
                        this.getSelectionManager().cut$foundation_release();
                        break;
                    case 4:
                        textFieldPreparedSelection.collapseLeftOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((TextFieldPreparedSelection) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(TextFieldPreparedSelection textFieldPreparedSelection2) {
                                textFieldPreparedSelection2.moveCursorLeft();
                            }
                        });
                        break;
                    case 5:
                        textFieldPreparedSelection.collapseRightOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.2
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((TextFieldPreparedSelection) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(TextFieldPreparedSelection textFieldPreparedSelection2) {
                                textFieldPreparedSelection2.moveCursorRight();
                            }
                        });
                        break;
                    case 6:
                        textFieldPreparedSelection.moveCursorLeftByWord();
                        break;
                    case 7:
                        textFieldPreparedSelection.moveCursorRightByWord();
                        break;
                    case 8:
                        textFieldPreparedSelection.moveCursorPrevByParagraph();
                        break;
                    case 9:
                        textFieldPreparedSelection.moveCursorNextByParagraph();
                        break;
                    case 10:
                        textFieldPreparedSelection.moveCursorUpByLine();
                        break;
                    case 11:
                        textFieldPreparedSelection.moveCursorDownByLine();
                        break;
                    case 12:
                        textFieldPreparedSelection.moveCursorUpByPage();
                        break;
                    case 13:
                        textFieldPreparedSelection.moveCursorDownByPage();
                        break;
                    case 14:
                        textFieldPreparedSelection.moveCursorToLineStart();
                        break;
                    case 15:
                        textFieldPreparedSelection.moveCursorToLineEnd();
                        break;
                    case 16:
                        textFieldPreparedSelection.moveCursorToLineLeftSide();
                        break;
                    case 17:
                        textFieldPreparedSelection.moveCursorToLineRightSide();
                        break;
                    case 18:
                        textFieldPreparedSelection.moveCursorToHome();
                        break;
                    case 19:
                        textFieldPreparedSelection.moveCursorToEnd();
                        break;
                    case 20:
                        List listDeleteIfSelectedOr = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.3
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection textFieldPreparedSelection2) {
                                return new DeleteSurroundingTextCommand(TextRange.m2112getEndimpl(textFieldPreparedSelection2.m533getSelectiond9O1mEE()) - textFieldPreparedSelection2.getPrecedingCharacterIndex(), 0);
                            }
                        });
                        if (listDeleteIfSelectedOr != null) {
                            this.apply(listDeleteIfSelectedOr);
                        }
                        break;
                    case 21:
                        List listDeleteIfSelectedOr2 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.4
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection textFieldPreparedSelection2) {
                                int nextCharacterIndex = textFieldPreparedSelection2.getNextCharacterIndex();
                                if (nextCharacterIndex != -1) {
                                    return new DeleteSurroundingTextCommand(0, nextCharacterIndex - TextRange.m2112getEndimpl(textFieldPreparedSelection2.m533getSelectiond9O1mEE()));
                                }
                                return null;
                            }
                        });
                        if (listDeleteIfSelectedOr2 != null) {
                            this.apply(listDeleteIfSelectedOr2);
                        }
                        break;
                    case 22:
                        List listDeleteIfSelectedOr3 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.5
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection textFieldPreparedSelection2) {
                                Integer previousWordOffset = textFieldPreparedSelection2.getPreviousWordOffset();
                                if (previousWordOffset == null) {
                                    return null;
                                }
                                return new DeleteSurroundingTextCommand(TextRange.m2112getEndimpl(textFieldPreparedSelection2.m533getSelectiond9O1mEE()) - previousWordOffset.intValue(), 0);
                            }
                        });
                        if (listDeleteIfSelectedOr3 != null) {
                            this.apply(listDeleteIfSelectedOr3);
                        }
                        break;
                    case 23:
                        List listDeleteIfSelectedOr4 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.6
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection textFieldPreparedSelection2) {
                                Integer nextWordOffset = textFieldPreparedSelection2.getNextWordOffset();
                                if (nextWordOffset != null) {
                                    return new DeleteSurroundingTextCommand(0, nextWordOffset.intValue() - TextRange.m2112getEndimpl(textFieldPreparedSelection2.m533getSelectiond9O1mEE()));
                                }
                                return null;
                            }
                        });
                        if (listDeleteIfSelectedOr4 != null) {
                            this.apply(listDeleteIfSelectedOr4);
                        }
                        break;
                    case 24:
                        List listDeleteIfSelectedOr5 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.7
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection textFieldPreparedSelection2) {
                                Integer lineStartByOffset = textFieldPreparedSelection2.getLineStartByOffset();
                                if (lineStartByOffset == null) {
                                    return null;
                                }
                                return new DeleteSurroundingTextCommand(TextRange.m2112getEndimpl(textFieldPreparedSelection2.m533getSelectiond9O1mEE()) - lineStartByOffset.intValue(), 0);
                            }
                        });
                        if (listDeleteIfSelectedOr5 != null) {
                            this.apply(listDeleteIfSelectedOr5);
                        }
                        break;
                    case 25:
                        List listDeleteIfSelectedOr6 = textFieldPreparedSelection.deleteIfSelectedOr(new Function1() { // from class: androidx.compose.foundation.text.TextFieldKeyInput$process$2.8
                            @Override // kotlin.jvm.functions.Function1
                            public final EditCommand invoke(TextFieldPreparedSelection textFieldPreparedSelection2) {
                                Integer lineEndByOffset = textFieldPreparedSelection2.getLineEndByOffset();
                                if (lineEndByOffset != null) {
                                    return new DeleteSurroundingTextCommand(0, lineEndByOffset.intValue() - TextRange.m2112getEndimpl(textFieldPreparedSelection2.m533getSelectiond9O1mEE()));
                                }
                                return null;
                            }
                        });
                        if (listDeleteIfSelectedOr6 != null) {
                            this.apply(listDeleteIfSelectedOr6);
                        }
                        break;
                    case 26:
                        if (!this.getSingleLine()) {
                            this.apply(new CommitTextCommand("\n", 1));
                        } else {
                            this.getState().getOnImeActionPerformed().invoke(ImeAction.m2187boximpl(this.imeAction));
                        }
                        break;
                    case 27:
                        if (!this.getSingleLine()) {
                            this.apply(new CommitTextCommand("\t", 1));
                        } else {
                            ref$BooleanRef.element = false;
                        }
                        break;
                    case 28:
                        textFieldPreparedSelection.selectAll();
                        break;
                    case 29:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorLeft()).selectMovement();
                        break;
                    case 30:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorRight()).selectMovement();
                        break;
                    case 31:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorLeftByWord()).selectMovement();
                        break;
                    case 32:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorRightByWord()).selectMovement();
                        break;
                    case 33:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorPrevByParagraph()).selectMovement();
                        break;
                    case 34:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorNextByParagraph()).selectMovement();
                        break;
                    case 35:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorToLineStart()).selectMovement();
                        break;
                    case EACTags.APPLICATION_EXPIRATION_DATE /* 36 */:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorToLineEnd()).selectMovement();
                        break;
                    case EACTags.APPLICATION_EFFECTIVE_DATE /* 37 */:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorToLineLeftSide()).selectMovement();
                        break;
                    case EACTags.CARD_EFFECTIVE_DATE /* 38 */:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorToLineRightSide()).selectMovement();
                        break;
                    case EACTags.INTERCHANGE_CONTROL /* 39 */:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorUpByLine()).selectMovement();
                        break;
                    case 40:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorDownByLine()).selectMovement();
                        break;
                    case EACTags.INTERCHANGE_PROFILE /* 41 */:
                        textFieldPreparedSelection.moveCursorUpByPage().selectMovement();
                        break;
                    case EACTags.CURRENCY_CODE /* 42 */:
                        textFieldPreparedSelection.moveCursorDownByPage().selectMovement();
                        break;
                    case EACTags.DATE_OF_BIRTH /* 43 */:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorToHome()).selectMovement();
                        break;
                    case EACTags.CARDHOLDER_NATIONALITY /* 44 */:
                        ((TextFieldPreparedSelection) textFieldPreparedSelection.moveCursorToEnd()).selectMovement();
                        break;
                    case EACTags.LANGUAGE_PREFERENCES /* 45 */:
                        textFieldPreparedSelection.deselect();
                        break;
                    case 46:
                        UndoManager undoManager = this.getUndoManager();
                        if (undoManager != null) {
                            undoManager.makeSnapshot(textFieldPreparedSelection.getValue());
                        }
                        UndoManager undoManager2 = this.getUndoManager();
                        if (undoManager2 != null && (textFieldValueUndo = undoManager2.undo()) != null) {
                            this.onValueChange.invoke(textFieldValueUndo);
                            break;
                        }
                        break;
                    case EACTags.PIN_USAGE_POLICY /* 47 */:
                        UndoManager undoManager3 = this.getUndoManager();
                        if (undoManager3 != null && (textFieldValueRedo = undoManager3.redo()) != null) {
                            this.onValueChange.invoke(textFieldValueRedo);
                            break;
                        }
                        break;
                    case 48:
                        KeyEventHelpers_androidKt.showCharacterPalette();
                        break;
                }
            }
        });
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
        return ref$BooleanRef.element;
    }

    private final void commandExecutionContext(Function1 function1) {
        TextFieldPreparedSelection textFieldPreparedSelection = new TextFieldPreparedSelection(this.value, this.offsetMapping, this.state.getLayoutResult(), this.preparedSelectionState);
        function1.invoke(textFieldPreparedSelection);
        if (TextRange.m2110equalsimpl0(textFieldPreparedSelection.m533getSelectiond9O1mEE(), this.value.m2239getSelectiond9O1mEE()) && Intrinsics.areEqual(textFieldPreparedSelection.getAnnotatedString(), this.value.getAnnotatedString())) {
            return;
        }
        this.onValueChange.invoke(textFieldPreparedSelection.getValue());
    }
}
