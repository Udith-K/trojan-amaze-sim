package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowInsets.kt */
/* JADX INFO: loaded from: classes.dex */
final class UnionInsets implements WindowInsets {
    private final WindowInsets first;
    private final WindowInsets second;

    public UnionInsets(WindowInsets windowInsets, WindowInsets windowInsets2) {
        this.first = windowInsets;
        this.second = windowInsets2;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getLeft(Density density, LayoutDirection layoutDirection) {
        return Math.max(this.first.getLeft(density, layoutDirection), this.second.getLeft(density, layoutDirection));
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getTop(Density density) {
        return Math.max(this.first.getTop(density), this.second.getTop(density));
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getRight(Density density, LayoutDirection layoutDirection) {
        return Math.max(this.first.getRight(density, layoutDirection), this.second.getRight(density, layoutDirection));
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getBottom(Density density) {
        return Math.max(this.first.getBottom(density), this.second.getBottom(density));
    }

    public int hashCode() {
        return this.first.hashCode() + (this.second.hashCode() * 31);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UnionInsets)) {
            return false;
        }
        UnionInsets unionInsets = (UnionInsets) obj;
        return Intrinsics.areEqual(unionInsets.first, this.first) && Intrinsics.areEqual(unionInsets.second, this.second);
    }

    public String toString() {
        return CoreConstants.LEFT_PARENTHESIS_CHAR + this.first + " ∪ " + this.second + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
