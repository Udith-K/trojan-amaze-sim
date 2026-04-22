package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: EditProcessor.kt */
/* JADX INFO: loaded from: classes.dex */
public final class EditProcessor {
    private TextFieldValue mBufferState = new TextFieldValue(AnnotatedStringKt.emptyAnnotatedString(), TextRange.Companion.m2122getZerod9O1mEE(), (TextRange) null, (DefaultConstructorMarker) null);
    private EditingBuffer mBuffer = new EditingBuffer(this.mBufferState.getAnnotatedString(), this.mBufferState.m2239getSelectiond9O1mEE(), null);

    public final TextFieldValue apply(List list) {
        EditCommand editCommand;
        Exception e;
        try {
            int size = list.size();
            int i = 0;
            editCommand = null;
            while (i < size) {
                try {
                    EditCommand editCommand2 = (EditCommand) list.get(i);
                    try {
                        editCommand2.applyTo(this.mBuffer);
                        i++;
                        editCommand = editCommand2;
                    } catch (Exception e2) {
                        e = e2;
                        editCommand = editCommand2;
                        throw new RuntimeException(generateBatchErrorMessage(list, editCommand), e);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
            AnnotatedString annotatedString$ui_text_release = this.mBuffer.toAnnotatedString$ui_text_release();
            long jM2185getSelectiond9O1mEE$ui_text_release = this.mBuffer.m2185getSelectiond9O1mEE$ui_text_release();
            TextRange textRangeM2105boximpl = TextRange.m2105boximpl(jM2185getSelectiond9O1mEE$ui_text_release);
            textRangeM2105boximpl.m2121unboximpl();
            TextRange textRange = TextRange.m2116getReversedimpl(this.mBufferState.m2239getSelectiond9O1mEE()) ? null : textRangeM2105boximpl;
            TextFieldValue textFieldValue = new TextFieldValue(annotatedString$ui_text_release, textRange != null ? textRange.m2121unboximpl() : TextRangeKt.TextRange(TextRange.m2114getMaximpl(jM2185getSelectiond9O1mEE$ui_text_release), TextRange.m2115getMinimpl(jM2185getSelectiond9O1mEE$ui_text_release)), this.mBuffer.m2184getCompositionMzsxiRA$ui_text_release(), (DefaultConstructorMarker) null);
            this.mBufferState = textFieldValue;
            return textFieldValue;
        } catch (Exception e4) {
            editCommand = null;
            e = e4;
        }
    }

    public final void reset(TextFieldValue textFieldValue, TextInputSession textInputSession) {
        boolean zAreEqual = Intrinsics.areEqual(textFieldValue.m2238getCompositionMzsxiRA(), this.mBuffer.m2184getCompositionMzsxiRA$ui_text_release());
        boolean z = true;
        boolean z2 = false;
        if (!Intrinsics.areEqual(this.mBufferState.getAnnotatedString(), textFieldValue.getAnnotatedString())) {
            this.mBuffer = new EditingBuffer(textFieldValue.getAnnotatedString(), textFieldValue.m2239getSelectiond9O1mEE(), null);
        } else if (TextRange.m2110equalsimpl0(this.mBufferState.m2239getSelectiond9O1mEE(), textFieldValue.m2239getSelectiond9O1mEE())) {
            z = false;
        } else {
            this.mBuffer.setSelection$ui_text_release(TextRange.m2115getMinimpl(textFieldValue.m2239getSelectiond9O1mEE()), TextRange.m2114getMaximpl(textFieldValue.m2239getSelectiond9O1mEE()));
            z2 = true;
            z = false;
        }
        if (textFieldValue.m2238getCompositionMzsxiRA() == null) {
            this.mBuffer.commitComposition$ui_text_release();
        } else if (!TextRange.m2111getCollapsedimpl(textFieldValue.m2238getCompositionMzsxiRA().m2121unboximpl())) {
            this.mBuffer.setComposition$ui_text_release(TextRange.m2115getMinimpl(textFieldValue.m2238getCompositionMzsxiRA().m2121unboximpl()), TextRange.m2114getMaximpl(textFieldValue.m2238getCompositionMzsxiRA().m2121unboximpl()));
        }
        if (z || (!z2 && !zAreEqual)) {
            this.mBuffer.commitComposition$ui_text_release();
            textFieldValue = TextFieldValue.m2236copy3r_uNRQ$default(textFieldValue, null, 0L, null, 3, null);
        }
        TextFieldValue textFieldValue2 = this.mBufferState;
        this.mBufferState = textFieldValue;
        if (textInputSession != null) {
            textInputSession.updateState(textFieldValue2, textFieldValue);
        }
    }

    public final TextFieldValue toTextFieldValue() {
        return this.mBufferState;
    }

    private final String generateBatchErrorMessage(List list, final EditCommand editCommand) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Error while applying EditCommand batch to buffer (length=" + this.mBuffer.getLength$ui_text_release() + ", composition=" + this.mBuffer.m2184getCompositionMzsxiRA$ui_text_release() + ", selection=" + ((Object) TextRange.m2120toStringimpl(this.mBuffer.m2185getSelectiond9O1mEE$ui_text_release())) + "):");
        Intrinsics.checkNotNullExpressionValue(sb, "append(value)");
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append('\\n')");
        CollectionsKt___CollectionsKt.joinTo(list, sb, (124 & 2) != 0 ? ", " : "\n", (124 & 4) != 0 ? "" : null, (124 & 8) == 0 ? null : "", (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : new Function1() { // from class: androidx.compose.ui.text.input.EditProcessor$generateBatchErrorMessage$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(EditCommand editCommand2) {
                return (editCommand == editCommand2 ? " > " : "   ") + this.toStringForLog(editCommand2);
            }
        });
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String toStringForLog(EditCommand editCommand) {
        if (editCommand instanceof CommitTextCommand) {
            StringBuilder sb = new StringBuilder();
            sb.append("CommitTextCommand(text.length=");
            CommitTextCommand commitTextCommand = (CommitTextCommand) editCommand;
            sb.append(commitTextCommand.getText().length());
            sb.append(", newCursorPosition=");
            sb.append(commitTextCommand.getNewCursorPosition());
            sb.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
            return sb.toString();
        }
        if (editCommand instanceof SetComposingTextCommand) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("SetComposingTextCommand(text.length=");
            SetComposingTextCommand setComposingTextCommand = (SetComposingTextCommand) editCommand;
            sb2.append(setComposingTextCommand.getText().length());
            sb2.append(", newCursorPosition=");
            sb2.append(setComposingTextCommand.getNewCursorPosition());
            sb2.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
            return sb2.toString();
        }
        if (!(editCommand instanceof SetComposingRegionCommand) && !(editCommand instanceof DeleteSurroundingTextCommand) && !(editCommand instanceof DeleteSurroundingTextInCodePointsCommand) && !(editCommand instanceof SetSelectionCommand) && !(editCommand instanceof FinishComposingTextCommand) && !(editCommand instanceof DeleteAllCommand)) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unknown EditCommand: ");
            String simpleName = Reflection.getOrCreateKotlinClass(editCommand.getClass()).getSimpleName();
            if (simpleName == null) {
                simpleName = "{anonymous EditCommand}";
            }
            sb3.append(simpleName);
            return sb3.toString();
        }
        return editCommand.toString();
    }
}
