package androidx.compose.ui.text.input;

import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: EditCommand.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DeleteSurroundingTextCommand implements EditCommand {
    private final int lengthAfterCursor;
    private final int lengthBeforeCursor;

    public DeleteSurroundingTextCommand(int i, int i2) {
        this.lengthBeforeCursor = i;
        this.lengthAfterCursor = i2;
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException(("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + i + " and " + i2 + " respectively.").toString());
        }
    }

    @Override // androidx.compose.ui.text.input.EditCommand
    public void applyTo(EditingBuffer editingBuffer) {
        int selectionEnd$ui_text_release = editingBuffer.getSelectionEnd$ui_text_release();
        int i = this.lengthAfterCursor;
        int length$ui_text_release = selectionEnd$ui_text_release + i;
        if (((selectionEnd$ui_text_release ^ length$ui_text_release) & (i ^ length$ui_text_release)) < 0) {
            length$ui_text_release = editingBuffer.getLength$ui_text_release();
        }
        editingBuffer.delete$ui_text_release(editingBuffer.getSelectionEnd$ui_text_release(), Math.min(length$ui_text_release, editingBuffer.getLength$ui_text_release()));
        int selectionStart$ui_text_release = editingBuffer.getSelectionStart$ui_text_release();
        int i2 = this.lengthBeforeCursor;
        int i3 = selectionStart$ui_text_release - i2;
        if (((selectionStart$ui_text_release ^ i3) & (i2 ^ selectionStart$ui_text_release)) < 0) {
            i3 = 0;
        }
        editingBuffer.delete$ui_text_release(Math.max(0, i3), editingBuffer.getSelectionStart$ui_text_release());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeleteSurroundingTextCommand)) {
            return false;
        }
        DeleteSurroundingTextCommand deleteSurroundingTextCommand = (DeleteSurroundingTextCommand) obj;
        return this.lengthBeforeCursor == deleteSurroundingTextCommand.lengthBeforeCursor && this.lengthAfterCursor == deleteSurroundingTextCommand.lengthAfterCursor;
    }

    public int hashCode() {
        return (this.lengthBeforeCursor * 31) + this.lengthAfterCursor;
    }

    public String toString() {
        return "DeleteSurroundingTextCommand(lengthBeforeCursor=" + this.lengthBeforeCursor + ", lengthAfterCursor=" + this.lengthAfterCursor + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
