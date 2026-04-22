package androidx.compose.material3.internal;

import androidx.compose.material3.internal.MenuPosition;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenuPosition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AnchorAlignmentOffsetPosition$Horizontal implements MenuPosition.Horizontal {
    private final Alignment.Horizontal anchorAlignment;
    private final Alignment.Horizontal menuAlignment;
    private final int offset;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnchorAlignmentOffsetPosition$Horizontal)) {
            return false;
        }
        AnchorAlignmentOffsetPosition$Horizontal anchorAlignmentOffsetPosition$Horizontal = (AnchorAlignmentOffsetPosition$Horizontal) obj;
        return Intrinsics.areEqual(this.menuAlignment, anchorAlignmentOffsetPosition$Horizontal.menuAlignment) && Intrinsics.areEqual(this.anchorAlignment, anchorAlignmentOffsetPosition$Horizontal.anchorAlignment) && this.offset == anchorAlignmentOffsetPosition$Horizontal.offset;
    }

    public int hashCode() {
        return (((this.menuAlignment.hashCode() * 31) + this.anchorAlignment.hashCode()) * 31) + this.offset;
    }

    public String toString() {
        return "Horizontal(menuAlignment=" + this.menuAlignment + ", anchorAlignment=" + this.anchorAlignment + ", offset=" + this.offset + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public AnchorAlignmentOffsetPosition$Horizontal(Alignment.Horizontal horizontal, Alignment.Horizontal horizontal2, int i) {
        this.menuAlignment = horizontal;
        this.anchorAlignment = horizontal2;
        this.offset = i;
    }

    @Override // androidx.compose.material3.internal.MenuPosition.Horizontal
    /* JADX INFO: renamed from: position-95KtPRI, reason: not valid java name */
    public int mo779position95KtPRI(IntRect intRect, long j, int i, LayoutDirection layoutDirection) {
        int iAlign = this.anchorAlignment.align(0, intRect.getWidth(), layoutDirection);
        return intRect.getLeft() + iAlign + (-this.menuAlignment.align(0, i, layoutDirection)) + (layoutDirection == LayoutDirection.Ltr ? this.offset : -this.offset);
    }
}
