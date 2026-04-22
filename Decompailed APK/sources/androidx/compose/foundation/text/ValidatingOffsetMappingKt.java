package androidx.compose.foundation.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;

/* JADX INFO: compiled from: ValidatingOffsetMapping.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ValidatingOffsetMappingKt {
    private static final OffsetMapping ValidatingEmptyOffsetMappingIdentity = new ValidatingOffsetMapping(OffsetMapping.Companion.getIdentity(), 0, 0);

    public static final OffsetMapping getValidatingEmptyOffsetMappingIdentity() {
        return ValidatingEmptyOffsetMappingIdentity;
    }

    public static final TransformedText filterWithValidation(VisualTransformation visualTransformation, AnnotatedString annotatedString) {
        TransformedText transformedTextFilter = visualTransformation.filter(annotatedString);
        throwIfNotValidTransform$default(transformedTextFilter, annotatedString.length(), 0, 2, null);
        return new TransformedText(transformedTextFilter.getText(), new ValidatingOffsetMapping(transformedTextFilter.getOffsetMapping(), annotatedString.length(), transformedTextFilter.getText().length()));
    }

    public static /* synthetic */ void throwIfNotValidTransform$default(TransformedText transformedText, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 100;
        }
        throwIfNotValidTransform(transformedText, i, i2);
    }

    public static final void throwIfNotValidTransform(TransformedText transformedText, int i, int i2) {
        int length = transformedText.getText().length();
        int iMin = Math.min(i, i2);
        for (int i3 = 0; i3 < iMin; i3++) {
            validateOriginalToTransformed(transformedText.getOffsetMapping().originalToTransformed(i3), length, i3);
        }
        validateOriginalToTransformed(transformedText.getOffsetMapping().originalToTransformed(i), length, i);
        int iMin2 = Math.min(length, i2);
        for (int i4 = 0; i4 < iMin2; i4++) {
            validateTransformedToOriginal(transformedText.getOffsetMapping().transformedToOriginal(i4), i, i4);
        }
        validateTransformedToOriginal(transformedText.getOffsetMapping().transformedToOriginal(length), i, length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateTransformedToOriginal(int i, int i2, int i3) {
        if (i < 0 || i > i2) {
            throw new IllegalStateException(("OffsetMapping.transformedToOriginal returned invalid mapping: " + i3 + " -> " + i + " is not in range of original text [0, " + i2 + ']').toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateOriginalToTransformed(int i, int i2, int i3) {
        if (i < 0 || i > i2) {
            throw new IllegalStateException(("OffsetMapping.originalToTransformed returned invalid mapping: " + i3 + " -> " + i + " is not in range of transformed text [0, " + i2 + ']').toString());
        }
    }
}
