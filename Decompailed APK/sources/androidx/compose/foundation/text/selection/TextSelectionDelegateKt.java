package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.unit.IntSize;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextSelectionDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextSelectionDelegateKt {
    public static final long getSelectionHandleCoordinates(TextLayoutResult textLayoutResult, int i, boolean z, boolean z2) {
        int lineForOffset = textLayoutResult.getLineForOffset(i);
        return lineForOffset >= textLayoutResult.getLineCount() ? Offset.Companion.m1170getUnspecifiedF1C5BW0() : OffsetKt.Offset(RangesKt.coerceIn(getHorizontalPosition(textLayoutResult, i, z, z2), 0.0f, IntSize.m2476getWidthimpl(textLayoutResult.m2103getSizeYbymL2g())), RangesKt.coerceIn(textLayoutResult.getLineBottom(lineForOffset), 0.0f, IntSize.m2475getHeightimpl(textLayoutResult.m2103getSizeYbymL2g())));
    }

    public static final float getHorizontalPosition(TextLayoutResult textLayoutResult, int i, boolean z, boolean z2) {
        return textLayoutResult.getHorizontalPosition(i, textLayoutResult.getBidiRunDirection(((!z || z2) && (z || !z2)) ? Math.max(i + (-1), 0) : i) == textLayoutResult.getParagraphDirection(i));
    }
}
