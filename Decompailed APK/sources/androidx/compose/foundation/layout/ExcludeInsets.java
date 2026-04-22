package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: WindowInsets.kt */
/* JADX INFO: loaded from: classes.dex */
final class ExcludeInsets implements WindowInsets {
    private final WindowInsets excluded;
    private final WindowInsets included;

    public ExcludeInsets(WindowInsets windowInsets, WindowInsets windowInsets2) {
        this.included = windowInsets;
        this.excluded = windowInsets2;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getLeft(Density density, LayoutDirection layoutDirection) {
        return RangesKt.coerceAtLeast(this.included.getLeft(density, layoutDirection) - this.excluded.getLeft(density, layoutDirection), 0);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getTop(Density density) {
        return RangesKt.coerceAtLeast(this.included.getTop(density) - this.excluded.getTop(density), 0);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getRight(Density density, LayoutDirection layoutDirection) {
        return RangesKt.coerceAtLeast(this.included.getRight(density, layoutDirection) - this.excluded.getRight(density, layoutDirection), 0);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getBottom(Density density) {
        return RangesKt.coerceAtLeast(this.included.getBottom(density) - this.excluded.getBottom(density), 0);
    }

    public String toString() {
        return CoreConstants.LEFT_PARENTHESIS_CHAR + this.included + " - " + this.excluded + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExcludeInsets)) {
            return false;
        }
        ExcludeInsets excludeInsets = (ExcludeInsets) obj;
        return Intrinsics.areEqual(excludeInsets.included, this.included) && Intrinsics.areEqual(excludeInsets.excluded, this.excluded);
    }

    public int hashCode() {
        return (this.included.hashCode() * 31) + this.excluded.hashCode();
    }
}
