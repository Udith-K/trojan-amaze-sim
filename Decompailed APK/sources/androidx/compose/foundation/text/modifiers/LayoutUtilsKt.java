package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LayoutUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LayoutUtilsKt {
    /* JADX INFO: renamed from: finalConstraints-tfFHcEY, reason: not valid java name */
    public static final long m514finalConstraintstfFHcEY(long j, boolean z, int i, float f) {
        return Constraints.Companion.m2397fitPrioritizingWidthZbe2FdA(0, m516finalMaxWidthtfFHcEY(j, z, i, f), 0, Constraints.m2388getMaxHeightimpl(j));
    }

    /* JADX INFO: renamed from: finalMaxWidth-tfFHcEY, reason: not valid java name */
    public static final int m516finalMaxWidthtfFHcEY(long j, boolean z, int i, float f) {
        int iM2389getMaxWidthimpl = ((z || TextOverflow.m2372equalsimpl0(i, TextOverflow.Companion.m2376getEllipsisgIe3tQ8())) && Constraints.m2385getHasBoundedWidthimpl(j)) ? Constraints.m2389getMaxWidthimpl(j) : Integer.MAX_VALUE;
        return Constraints.m2391getMinWidthimpl(j) == iM2389getMaxWidthimpl ? iM2389getMaxWidthimpl : RangesKt.coerceIn(TextDelegateKt.ceilToIntPx(f), Constraints.m2391getMinWidthimpl(j), iM2389getMaxWidthimpl);
    }

    /* JADX INFO: renamed from: finalMaxLines-xdlQI24, reason: not valid java name */
    public static final int m515finalMaxLinesxdlQI24(boolean z, int i, int i2) {
        if (z || !TextOverflow.m2372equalsimpl0(i, TextOverflow.Companion.m2376getEllipsisgIe3tQ8())) {
            return RangesKt.coerceAtLeast(i2, 1);
        }
        return 1;
    }
}
