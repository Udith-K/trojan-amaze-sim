package androidx.compose.foundation.text.input.internal;

import android.graphics.Matrix;
import android.os.Build;
import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LegacyCursorAnchorInfoBuilder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LegacyCursorAnchorInfoBuilder_androidKt {
    public static final CursorAnchorInfo build(CursorAnchorInfo.Builder builder, TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Matrix matrix, Rect rect, Rect rect2, boolean z, boolean z2, boolean z3, boolean z4) {
        builder.reset();
        builder.setMatrix(matrix);
        int iM2115getMinimpl = TextRange.m2115getMinimpl(textFieldValue.m2239getSelectiond9O1mEE());
        builder.setSelectionRange(iM2115getMinimpl, TextRange.m2114getMaximpl(textFieldValue.m2239getSelectiond9O1mEE()));
        if (z) {
            setInsertionMarker(builder, iM2115getMinimpl, offsetMapping, textLayoutResult, rect);
        }
        if (z2) {
            TextRange textRangeM2238getCompositionMzsxiRA = textFieldValue.m2238getCompositionMzsxiRA();
            int iM2115getMinimpl2 = textRangeM2238getCompositionMzsxiRA != null ? TextRange.m2115getMinimpl(textRangeM2238getCompositionMzsxiRA.m2121unboximpl()) : -1;
            TextRange textRangeM2238getCompositionMzsxiRA2 = textFieldValue.m2238getCompositionMzsxiRA();
            int iM2114getMaximpl = textRangeM2238getCompositionMzsxiRA2 != null ? TextRange.m2114getMaximpl(textRangeM2238getCompositionMzsxiRA2.m2121unboximpl()) : -1;
            if (iM2115getMinimpl2 >= 0 && iM2115getMinimpl2 < iM2114getMaximpl) {
                builder.setComposingText(iM2115getMinimpl2, textFieldValue.getText().subSequence(iM2115getMinimpl2, iM2114getMaximpl));
                addCharacterBounds(builder, iM2115getMinimpl2, iM2114getMaximpl, offsetMapping, textLayoutResult, rect);
            }
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 33 && z3) {
            CursorAnchorInfoApi33Helper.setEditorBoundsInfo(builder, rect2);
        }
        if (i >= 34 && z4) {
            CursorAnchorInfoApi34Helper.addVisibleLineBounds(builder, textLayoutResult, rect);
        }
        return builder.build();
    }

    private static final CursorAnchorInfo.Builder setInsertionMarker(CursorAnchorInfo.Builder builder, int i, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Rect rect) {
        if (i < 0) {
            return builder;
        }
        int iOriginalToTransformed = offsetMapping.originalToTransformed(i);
        Rect cursorRect = textLayoutResult.getCursorRect(iOriginalToTransformed);
        float fCoerceIn = RangesKt.coerceIn(cursorRect.getLeft(), 0.0f, IntSize.m2476getWidthimpl(textLayoutResult.m2103getSizeYbymL2g()));
        boolean zContainsInclusive = containsInclusive(rect, fCoerceIn, cursorRect.getTop());
        boolean zContainsInclusive2 = containsInclusive(rect, fCoerceIn, cursorRect.getBottom());
        boolean z = textLayoutResult.getBidiRunDirection(iOriginalToTransformed) == ResolvedTextDirection.Rtl;
        int i2 = (zContainsInclusive || zContainsInclusive2) ? 1 : 0;
        if (!zContainsInclusive || !zContainsInclusive2) {
            i2 |= 2;
        }
        builder.setInsertionMarkerLocation(fCoerceIn, cursorRect.getTop(), cursorRect.getBottom(), cursorRect.getBottom(), z ? i2 | 4 : i2);
        return builder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0063  */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1, types: [int] */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r17v0, types: [android.view.inputmethod.CursorAnchorInfo$Builder] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final android.view.inputmethod.CursorAnchorInfo.Builder addCharacterBounds(android.view.inputmethod.CursorAnchorInfo.Builder r17, int r18, int r19, androidx.compose.ui.text.input.OffsetMapping r20, androidx.compose.ui.text.TextLayoutResult r21, androidx.compose.ui.geometry.Rect r22) {
        /*
            r0 = r19
            r1 = r20
            r2 = r18
            r3 = r22
            int r4 = r1.originalToTransformed(r2)
            int r5 = r1.originalToTransformed(r0)
            int r6 = r5 - r4
            int r6 = r6 * 4
            float[] r6 = new float[r6]
            androidx.compose.ui.text.MultiParagraph r7 = r21.getMultiParagraph()
            long r8 = androidx.compose.ui.text.TextRangeKt.TextRange(r4, r5)
            r5 = 0
            r7.m2049fillBoundingBoxes8ffj60Q(r8, r6, r5)
        L22:
            if (r2 >= r0) goto L8e
            int r5 = r1.originalToTransformed(r2)
            int r7 = r5 - r4
            int r7 = r7 * 4
            androidx.compose.ui.geometry.Rect r8 = new androidx.compose.ui.geometry.Rect
            r9 = r6[r7]
            int r10 = r7 + 1
            r10 = r6[r10]
            int r11 = r7 + 2
            r11 = r6[r11]
            int r7 = r7 + 3
            r7 = r6[r7]
            r8.<init>(r9, r10, r11, r7)
            boolean r7 = r3.overlaps(r8)
            float r9 = r8.getLeft()
            float r10 = r8.getTop()
            boolean r9 = containsInclusive(r3, r9, r10)
            if (r9 == 0) goto L63
            float r9 = r8.getRight()
            float r10 = r8.getBottom()
            boolean r9 = containsInclusive(r3, r9, r10)
            if (r9 != 0) goto L60
            goto L63
        L60:
            r9 = r21
            goto L66
        L63:
            r7 = r7 | 2
            goto L60
        L66:
            androidx.compose.ui.text.style.ResolvedTextDirection r5 = r9.getBidiRunDirection(r5)
            androidx.compose.ui.text.style.ResolvedTextDirection r10 = androidx.compose.ui.text.style.ResolvedTextDirection.Rtl
            if (r5 != r10) goto L73
            r5 = r7 | 4
            r16 = r5
            goto L75
        L73:
            r16 = r7
        L75:
            float r12 = r8.getLeft()
            float r13 = r8.getTop()
            float r14 = r8.getRight()
            float r15 = r8.getBottom()
            r10 = r17
            r11 = r2
            r10.addCharacterBounds(r11, r12, r13, r14, r15, r16)
            int r2 = r2 + 1
            goto L22
        L8e:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.LegacyCursorAnchorInfoBuilder_androidKt.addCharacterBounds(android.view.inputmethod.CursorAnchorInfo$Builder, int, int, androidx.compose.ui.text.input.OffsetMapping, androidx.compose.ui.text.TextLayoutResult, androidx.compose.ui.geometry.Rect):android.view.inputmethod.CursorAnchorInfo$Builder");
    }

    public static final boolean containsInclusive(Rect rect, float f, float f2) {
        float left = rect.getLeft();
        if (f <= rect.getRight() && left <= f) {
            float top = rect.getTop();
            if (f2 <= rect.getBottom() && top <= f2) {
                return true;
            }
        }
        return false;
    }
}
