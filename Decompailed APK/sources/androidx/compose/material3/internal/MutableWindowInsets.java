package androidx.compose.material3.internal;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: compiled from: MutableWindowInsets.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MutableWindowInsets implements WindowInsets {
    private final MutableState insets$delegate;

    public MutableWindowInsets(WindowInsets windowInsets) {
        this.insets$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(windowInsets, null, 2, null);
    }

    public final WindowInsets getInsets() {
        return (WindowInsets) this.insets$delegate.getValue();
    }

    public final void setInsets(WindowInsets windowInsets) {
        this.insets$delegate.setValue(windowInsets);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getLeft(Density density, LayoutDirection layoutDirection) {
        return getInsets().getLeft(density, layoutDirection);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getTop(Density density) {
        return getInsets().getTop(density);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getRight(Density density, LayoutDirection layoutDirection) {
        return getInsets().getRight(density, layoutDirection);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getBottom(Density density) {
        return getInsets().getBottom(density);
    }
}
