package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowInsets.kt */
/* JADX INFO: loaded from: classes.dex */
final class LimitInsets implements WindowInsets {
    private final WindowInsets insets;
    private final int sides;

    public /* synthetic */ LimitInsets(WindowInsets windowInsets, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(windowInsets, i);
    }

    private LimitInsets(WindowInsets windowInsets, int i) {
        this.insets = windowInsets;
        this.sides = i;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getLeft(Density density, LayoutDirection layoutDirection) {
        int iM302getAllowLeftInRtlJoeWqyM$foundation_layout_release;
        if (layoutDirection == LayoutDirection.Ltr) {
            iM302getAllowLeftInRtlJoeWqyM$foundation_layout_release = WindowInsetsSides.Companion.m301getAllowLeftInLtrJoeWqyM$foundation_layout_release();
        } else {
            iM302getAllowLeftInRtlJoeWqyM$foundation_layout_release = WindowInsetsSides.Companion.m302getAllowLeftInRtlJoeWqyM$foundation_layout_release();
        }
        if (WindowInsetsSides.m296hasAnybkgdKaI$foundation_layout_release(this.sides, iM302getAllowLeftInRtlJoeWqyM$foundation_layout_release)) {
            return this.insets.getLeft(density, layoutDirection);
        }
        return 0;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getTop(Density density) {
        if (WindowInsetsSides.m296hasAnybkgdKaI$foundation_layout_release(this.sides, WindowInsetsSides.Companion.m307getTopJoeWqyM())) {
            return this.insets.getTop(density);
        }
        return 0;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getRight(Density density, LayoutDirection layoutDirection) {
        int iM304getAllowRightInRtlJoeWqyM$foundation_layout_release;
        if (layoutDirection == LayoutDirection.Ltr) {
            iM304getAllowRightInRtlJoeWqyM$foundation_layout_release = WindowInsetsSides.Companion.m303getAllowRightInLtrJoeWqyM$foundation_layout_release();
        } else {
            iM304getAllowRightInRtlJoeWqyM$foundation_layout_release = WindowInsetsSides.Companion.m304getAllowRightInRtlJoeWqyM$foundation_layout_release();
        }
        if (WindowInsetsSides.m296hasAnybkgdKaI$foundation_layout_release(this.sides, iM304getAllowRightInRtlJoeWqyM$foundation_layout_release)) {
            return this.insets.getRight(density, layoutDirection);
        }
        return 0;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getBottom(Density density) {
        if (WindowInsetsSides.m296hasAnybkgdKaI$foundation_layout_release(this.sides, WindowInsetsSides.Companion.m305getBottomJoeWqyM())) {
            return this.insets.getBottom(density);
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LimitInsets)) {
            return false;
        }
        LimitInsets limitInsets = (LimitInsets) obj;
        return Intrinsics.areEqual(this.insets, limitInsets.insets) && WindowInsetsSides.m295equalsimpl0(this.sides, limitInsets.sides);
    }

    public int hashCode() {
        return (this.insets.hashCode() * 31) + WindowInsetsSides.m297hashCodeimpl(this.sides);
    }

    public String toString() {
        return CoreConstants.LEFT_PARENTHESIS_CHAR + this.insets + " only " + ((Object) WindowInsetsSides.m299toStringimpl(this.sides)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
