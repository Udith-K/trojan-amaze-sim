package androidx.compose.material3.internal;

import androidx.compose.ui.AbsoluteAlignment;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: compiled from: MenuPosition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MenuPosition {
    public static final MenuPosition INSTANCE = new MenuPosition();

    /* JADX INFO: compiled from: MenuPosition.kt */
    public interface Horizontal {
        /* JADX INFO: renamed from: position-95KtPRI */
        int mo779position95KtPRI(IntRect intRect, long j, int i, LayoutDirection layoutDirection);
    }

    /* JADX INFO: compiled from: MenuPosition.kt */
    public interface Vertical {
        /* JADX INFO: renamed from: position-JVtK1S4 */
        int mo780positionJVtK1S4(IntRect intRect, long j, int i);
    }

    private MenuPosition() {
    }

    public final Horizontal startToAnchorStart(int i) {
        Alignment.Companion companion = Alignment.Companion;
        return new AnchorAlignmentOffsetPosition$Horizontal(companion.getStart(), companion.getStart(), i);
    }

    public final Horizontal endToAnchorEnd(int i) {
        Alignment.Companion companion = Alignment.Companion;
        return new AnchorAlignmentOffsetPosition$Horizontal(companion.getEnd(), companion.getEnd(), i);
    }

    public final Horizontal leftToWindowLeft(int i) {
        return new WindowAlignmentMarginPosition$Horizontal(AbsoluteAlignment.INSTANCE.getLeft(), i);
    }

    public final Horizontal rightToWindowRight(int i) {
        return new WindowAlignmentMarginPosition$Horizontal(AbsoluteAlignment.INSTANCE.getRight(), i);
    }

    public final Vertical topToAnchorBottom(int i) {
        Alignment.Companion companion = Alignment.Companion;
        return new AnchorAlignmentOffsetPosition$Vertical(companion.getTop(), companion.getBottom(), i);
    }

    public final Vertical bottomToAnchorTop(int i) {
        Alignment.Companion companion = Alignment.Companion;
        return new AnchorAlignmentOffsetPosition$Vertical(companion.getBottom(), companion.getTop(), i);
    }

    public final Vertical centerToAnchorTop(int i) {
        Alignment.Companion companion = Alignment.Companion;
        return new AnchorAlignmentOffsetPosition$Vertical(companion.getCenterVertically(), companion.getTop(), i);
    }

    public final Vertical topToWindowTop(int i) {
        return new WindowAlignmentMarginPosition$Vertical(Alignment.Companion.getTop(), i);
    }

    public final Vertical bottomToWindowBottom(int i) {
        return new WindowAlignmentMarginPosition$Vertical(Alignment.Companion.getBottom(), i);
    }
}
