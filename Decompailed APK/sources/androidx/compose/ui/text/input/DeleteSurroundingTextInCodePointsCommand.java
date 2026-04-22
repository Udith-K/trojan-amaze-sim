package androidx.compose.ui.text.input;

import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: EditCommand.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DeleteSurroundingTextInCodePointsCommand implements EditCommand {
    private final int lengthAfterCursor;
    private final int lengthBeforeCursor;

    public DeleteSurroundingTextInCodePointsCommand(int i, int i2) {
        this.lengthBeforeCursor = i;
        this.lengthAfterCursor = i2;
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException(("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + i + " and " + i2 + " respectively.").toString());
        }
    }

    @Override // androidx.compose.ui.text.input.EditCommand
    public void applyTo(EditingBuffer editingBuffer) {
        int i = this.lengthBeforeCursor;
        int i2 = 0;
        int i3 = 0;
        int selectionStart$ui_text_release = 0;
        while (true) {
            if (i3 < i) {
                int i4 = selectionStart$ui_text_release + 1;
                if (editingBuffer.getSelectionStart$ui_text_release() > i4) {
                    selectionStart$ui_text_release = EditCommandKt.isSurrogatePair(editingBuffer.get$ui_text_release((editingBuffer.getSelectionStart$ui_text_release() - i4) + (-1)), editingBuffer.get$ui_text_release(editingBuffer.getSelectionStart$ui_text_release() - i4)) ? selectionStart$ui_text_release + 2 : i4;
                    i3++;
                } else {
                    selectionStart$ui_text_release = editingBuffer.getSelectionStart$ui_text_release();
                    break;
                }
            } else {
                break;
            }
        }
        int i5 = this.lengthAfterCursor;
        int length$ui_text_release = 0;
        while (true) {
            if (i2 >= i5) {
                break;
            }
            int i6 = length$ui_text_release + 1;
            if (editingBuffer.getSelectionEnd$ui_text_release() + i6 < editingBuffer.getLength$ui_text_release()) {
                length$ui_text_release = EditCommandKt.isSurrogatePair(editingBuffer.get$ui_text_release((editingBuffer.getSelectionEnd$ui_text_release() + i6) + (-1)), editingBuffer.get$ui_text_release(editingBuffer.getSelectionEnd$ui_text_release() + i6)) ? length$ui_text_release + 2 : i6;
                i2++;
            } else {
                length$ui_text_release = editingBuffer.getLength$ui_text_release() - editingBuffer.getSelectionEnd$ui_text_release();
                break;
            }
        }
        editingBuffer.delete$ui_text_release(editingBuffer.getSelectionEnd$ui_text_release(), editingBuffer.getSelectionEnd$ui_text_release() + length$ui_text_release);
        editingBuffer.delete$ui_text_release(editingBuffer.getSelectionStart$ui_text_release() - selectionStart$ui_text_release, editingBuffer.getSelectionStart$ui_text_release());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeleteSurroundingTextInCodePointsCommand)) {
            return false;
        }
        DeleteSurroundingTextInCodePointsCommand deleteSurroundingTextInCodePointsCommand = (DeleteSurroundingTextInCodePointsCommand) obj;
        return this.lengthBeforeCursor == deleteSurroundingTextInCodePointsCommand.lengthBeforeCursor && this.lengthAfterCursor == deleteSurroundingTextInCodePointsCommand.lengthAfterCursor;
    }

    public int hashCode() {
        return (this.lengthBeforeCursor * 31) + this.lengthAfterCursor;
    }

    public String toString() {
        return "DeleteSurroundingTextInCodePointsCommand(lengthBeforeCursor=" + this.lengthBeforeCursor + ", lengthAfterCursor=" + this.lengthAfterCursor + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
