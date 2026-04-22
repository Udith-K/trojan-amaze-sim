package androidx.compose.ui.text.input;

import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: EditCommand.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FinishComposingTextCommand implements EditCommand {
    @Override // androidx.compose.ui.text.input.EditCommand
    public void applyTo(EditingBuffer editingBuffer) {
        editingBuffer.commitComposition$ui_text_release();
    }

    public boolean equals(Object obj) {
        return obj instanceof FinishComposingTextCommand;
    }

    public int hashCode() {
        return Reflection.getOrCreateKotlinClass(FinishComposingTextCommand.class).hashCode();
    }

    public String toString() {
        return "FinishComposingTextCommand()";
    }
}
