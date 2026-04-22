package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisualTransformation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TransformedText {
    private final OffsetMapping offsetMapping;
    private final AnnotatedString text;

    public TransformedText(AnnotatedString annotatedString, OffsetMapping offsetMapping) {
        this.text = annotatedString;
        this.offsetMapping = offsetMapping;
    }

    public final AnnotatedString getText() {
        return this.text;
    }

    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransformedText)) {
            return false;
        }
        TransformedText transformedText = (TransformedText) obj;
        return Intrinsics.areEqual(this.text, transformedText.text) && Intrinsics.areEqual(this.offsetMapping, transformedText.offsetMapping);
    }

    public int hashCode() {
        return (this.text.hashCode() * 31) + this.offsetMapping.hashCode();
    }

    public String toString() {
        return "TransformedText(text=" + ((Object) this.text) + ", offsetMapping=" + this.offsetMapping + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
