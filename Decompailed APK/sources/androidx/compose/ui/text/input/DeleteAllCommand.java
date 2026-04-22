package androidx.compose.ui.text.input;

import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: EditCommand.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DeleteAllCommand implements EditCommand {
    @Override // androidx.compose.ui.text.input.EditCommand
    public void applyTo(EditingBuffer editingBuffer) {
        editingBuffer.replace$ui_text_release(0, editingBuffer.getLength$ui_text_release(), "");
    }

    public boolean equals(Object obj) {
        return obj instanceof DeleteAllCommand;
    }

    public int hashCode() {
        return Reflection.getOrCreateKotlinClass(DeleteAllCommand.class).hashCode();
    }

    public String toString() {
        return "DeleteAllCommand()";
    }
}
