package androidx.compose.ui.platform;

import androidx.compose.ui.text.AnnotatedString;

/* JADX INFO: compiled from: ClipboardManager.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ClipboardManager {
    AnnotatedString getText();

    boolean hasText();

    void setText(AnnotatedString annotatedString);
}
