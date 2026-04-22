package androidx.compose.foundation.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.SpanStyle;

/* JADX INFO: compiled from: TextLinkScope.kt */
/* JADX INFO: loaded from: classes.dex */
final class TextAnnotatorScope {
    private final AnnotatedString.Builder builder;

    public TextAnnotatorScope(AnnotatedString.Builder builder) {
        this.builder = builder;
    }

    public final void replaceStyle(SpanStyle spanStyle, int i, int i2) {
        this.builder.addStyle(spanStyle, i, i2);
    }
}
