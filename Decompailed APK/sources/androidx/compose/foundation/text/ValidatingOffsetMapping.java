package androidx.compose.foundation.text;

import androidx.compose.ui.text.input.OffsetMapping;

/* JADX INFO: compiled from: ValidatingOffsetMapping.kt */
/* JADX INFO: loaded from: classes.dex */
final class ValidatingOffsetMapping implements OffsetMapping {
    private final OffsetMapping delegate;
    private final int originalLength;
    private final int transformedLength;

    public ValidatingOffsetMapping(OffsetMapping offsetMapping, int i, int i2) {
        this.delegate = offsetMapping;
        this.originalLength = i;
        this.transformedLength = i2;
    }

    @Override // androidx.compose.ui.text.input.OffsetMapping
    public int originalToTransformed(int i) {
        int iOriginalToTransformed = this.delegate.originalToTransformed(i);
        if (i >= 0 && i <= this.originalLength) {
            ValidatingOffsetMappingKt.validateOriginalToTransformed(iOriginalToTransformed, this.transformedLength, i);
        }
        return iOriginalToTransformed;
    }

    @Override // androidx.compose.ui.text.input.OffsetMapping
    public int transformedToOriginal(int i) {
        int iTransformedToOriginal = this.delegate.transformedToOriginal(i);
        if (i >= 0 && i <= this.transformedLength) {
            ValidatingOffsetMappingKt.validateTransformedToOriginal(iTransformedToOriginal, this.originalLength, i);
        }
        return iTransformedToOriginal;
    }
}
