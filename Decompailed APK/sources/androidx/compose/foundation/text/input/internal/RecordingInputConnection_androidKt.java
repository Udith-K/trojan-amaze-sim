package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.ExtractedText;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RecordingInputConnection.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RecordingInputConnection_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final ExtractedText toExtractedText(TextFieldValue textFieldValue) {
        ExtractedText extractedText = new ExtractedText();
        extractedText.text = textFieldValue.getText();
        extractedText.startOffset = 0;
        extractedText.partialEndOffset = textFieldValue.getText().length();
        extractedText.partialStartOffset = -1;
        extractedText.selectionStart = TextRange.m2115getMinimpl(textFieldValue.m2239getSelectiond9O1mEE());
        extractedText.selectionEnd = TextRange.m2114getMaximpl(textFieldValue.m2239getSelectiond9O1mEE());
        extractedText.flags = !StringsKt.contains$default((CharSequence) textFieldValue.getText(), '\n', false, 2, (Object) null) ? 1 : 0;
        return extractedText;
    }
}
