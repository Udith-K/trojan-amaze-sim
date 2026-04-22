package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: EditCommand.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SetComposingTextCommand implements EditCommand {
    private final AnnotatedString annotatedString;
    private final int newCursorPosition;

    public SetComposingTextCommand(AnnotatedString annotatedString, int i) {
        this.annotatedString = annotatedString;
        this.newCursorPosition = i;
    }

    public final int getNewCursorPosition() {
        return this.newCursorPosition;
    }

    public SetComposingTextCommand(String str, int i) {
        this(new AnnotatedString(str, null, null, 6, null), i);
    }

    public final String getText() {
        return this.annotatedString.getText();
    }

    @Override // androidx.compose.ui.text.input.EditCommand
    public void applyTo(EditingBuffer editingBuffer) {
        if (editingBuffer.hasComposition$ui_text_release()) {
            int compositionStart$ui_text_release = editingBuffer.getCompositionStart$ui_text_release();
            editingBuffer.replace$ui_text_release(editingBuffer.getCompositionStart$ui_text_release(), editingBuffer.getCompositionEnd$ui_text_release(), getText());
            if (getText().length() > 0) {
                editingBuffer.setComposition$ui_text_release(compositionStart$ui_text_release, getText().length() + compositionStart$ui_text_release);
            }
        } else {
            int selectionStart$ui_text_release = editingBuffer.getSelectionStart$ui_text_release();
            editingBuffer.replace$ui_text_release(editingBuffer.getSelectionStart$ui_text_release(), editingBuffer.getSelectionEnd$ui_text_release(), getText());
            if (getText().length() > 0) {
                editingBuffer.setComposition$ui_text_release(selectionStart$ui_text_release, getText().length() + selectionStart$ui_text_release);
            }
        }
        int cursor$ui_text_release = editingBuffer.getCursor$ui_text_release();
        int i = this.newCursorPosition;
        editingBuffer.setCursor$ui_text_release(RangesKt.coerceIn(i > 0 ? (cursor$ui_text_release + i) - 1 : (cursor$ui_text_release + i) - getText().length(), 0, editingBuffer.getLength$ui_text_release()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SetComposingTextCommand)) {
            return false;
        }
        SetComposingTextCommand setComposingTextCommand = (SetComposingTextCommand) obj;
        return Intrinsics.areEqual(getText(), setComposingTextCommand.getText()) && this.newCursorPosition == setComposingTextCommand.newCursorPosition;
    }

    public int hashCode() {
        return (getText().hashCode() * 31) + this.newCursorPosition;
    }

    public String toString() {
        return "SetComposingTextCommand(text='" + getText() + "', newCursorPosition=" + this.newCursorPosition + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
