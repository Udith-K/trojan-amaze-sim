package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.ExtractedText;

/* JADX INFO: compiled from: InputMethodManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
public interface InputMethodManager {
    boolean isActive();

    void restartInput();

    void startStylusHandwriting();

    void updateCursorAnchorInfo(CursorAnchorInfo cursorAnchorInfo);

    void updateExtractedText(int i, ExtractedText extractedText);

    void updateSelection(int i, int i2, int i3, int i4);
}
