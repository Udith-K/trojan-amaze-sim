package androidx.compose.material3.internal;

import androidx.compose.material3.internal.MenuPosition;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MenuPosition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WindowAlignmentMarginPosition$Horizontal implements MenuPosition.Horizontal {
    private final Alignment.Horizontal alignment;
    private final int margin;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WindowAlignmentMarginPosition$Horizontal)) {
            return false;
        }
        WindowAlignmentMarginPosition$Horizontal windowAlignmentMarginPosition$Horizontal = (WindowAlignmentMarginPosition$Horizontal) obj;
        return Intrinsics.areEqual(this.alignment, windowAlignmentMarginPosition$Horizontal.alignment) && this.margin == windowAlignmentMarginPosition$Horizontal.margin;
    }

    public int hashCode() {
        return (this.alignment.hashCode() * 31) + this.margin;
    }

    public String toString() {
        return "Horizontal(alignment=" + this.alignment + ", margin=" + this.margin + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public WindowAlignmentMarginPosition$Horizontal(Alignment.Horizontal horizontal, int i) {
        this.alignment = horizontal;
        this.margin = i;
    }

    @Override // androidx.compose.material3.internal.MenuPosition.Horizontal
    /* JADX INFO: renamed from: position-95KtPRI */
    public int mo779position95KtPRI(IntRect intRect, long j, int i, LayoutDirection layoutDirection) {
        if (i >= IntSize.m2476getWidthimpl(j) - (this.margin * 2)) {
            return Alignment.Companion.getCenterHorizontally().align(i, IntSize.m2476getWidthimpl(j), layoutDirection);
        }
        return RangesKt.coerceIn(this.alignment.align(i, IntSize.m2476getWidthimpl(j), layoutDirection), this.margin, (IntSize.m2476getWidthimpl(j) - this.margin) - i);
    }
}
