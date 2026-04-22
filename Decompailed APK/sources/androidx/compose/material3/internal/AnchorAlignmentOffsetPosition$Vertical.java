package androidx.compose.material3.internal;

import androidx.compose.material3.internal.MenuPosition;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntRect;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenuPosition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AnchorAlignmentOffsetPosition$Vertical implements MenuPosition.Vertical {
    private final Alignment.Vertical anchorAlignment;
    private final Alignment.Vertical menuAlignment;
    private final int offset;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnchorAlignmentOffsetPosition$Vertical)) {
            return false;
        }
        AnchorAlignmentOffsetPosition$Vertical anchorAlignmentOffsetPosition$Vertical = (AnchorAlignmentOffsetPosition$Vertical) obj;
        return Intrinsics.areEqual(this.menuAlignment, anchorAlignmentOffsetPosition$Vertical.menuAlignment) && Intrinsics.areEqual(this.anchorAlignment, anchorAlignmentOffsetPosition$Vertical.anchorAlignment) && this.offset == anchorAlignmentOffsetPosition$Vertical.offset;
    }

    public int hashCode() {
        return (((this.menuAlignment.hashCode() * 31) + this.anchorAlignment.hashCode()) * 31) + this.offset;
    }

    public String toString() {
        return "Vertical(menuAlignment=" + this.menuAlignment + ", anchorAlignment=" + this.anchorAlignment + ", offset=" + this.offset + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public AnchorAlignmentOffsetPosition$Vertical(Alignment.Vertical vertical, Alignment.Vertical vertical2, int i) {
        this.menuAlignment = vertical;
        this.anchorAlignment = vertical2;
        this.offset = i;
    }

    @Override // androidx.compose.material3.internal.MenuPosition.Vertical
    /* JADX INFO: renamed from: position-JVtK1S4, reason: not valid java name */
    public int mo780positionJVtK1S4(IntRect intRect, long j, int i) {
        int iAlign = this.anchorAlignment.align(0, intRect.getHeight());
        return intRect.getTop() + iAlign + (-this.menuAlignment.align(0, i)) + this.offset;
    }
}
