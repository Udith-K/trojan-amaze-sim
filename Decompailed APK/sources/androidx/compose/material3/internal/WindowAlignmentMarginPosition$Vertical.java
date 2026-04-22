package androidx.compose.material3.internal;

import androidx.compose.material3.internal.MenuPosition;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MenuPosition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WindowAlignmentMarginPosition$Vertical implements MenuPosition.Vertical {
    private final Alignment.Vertical alignment;
    private final int margin;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WindowAlignmentMarginPosition$Vertical)) {
            return false;
        }
        WindowAlignmentMarginPosition$Vertical windowAlignmentMarginPosition$Vertical = (WindowAlignmentMarginPosition$Vertical) obj;
        return Intrinsics.areEqual(this.alignment, windowAlignmentMarginPosition$Vertical.alignment) && this.margin == windowAlignmentMarginPosition$Vertical.margin;
    }

    public int hashCode() {
        return (this.alignment.hashCode() * 31) + this.margin;
    }

    public String toString() {
        return "Vertical(alignment=" + this.alignment + ", margin=" + this.margin + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public WindowAlignmentMarginPosition$Vertical(Alignment.Vertical vertical, int i) {
        this.alignment = vertical;
        this.margin = i;
    }

    @Override // androidx.compose.material3.internal.MenuPosition.Vertical
    /* JADX INFO: renamed from: position-JVtK1S4 */
    public int mo780positionJVtK1S4(IntRect intRect, long j, int i) {
        if (i >= IntSize.m2475getHeightimpl(j) - (this.margin * 2)) {
            return Alignment.Companion.getCenterVertically().align(i, IntSize.m2475getHeightimpl(j));
        }
        return RangesKt.coerceIn(this.alignment.align(i, IntSize.m2475getHeightimpl(j)), this.margin, (IntSize.m2475getHeightimpl(j) - this.margin) - i);
    }
}
