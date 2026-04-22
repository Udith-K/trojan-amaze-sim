package androidx.compose.foundation.layout;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: WindowInsets.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidWindowInsets implements WindowInsets {
    private final MutableState insets$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Insets.NONE, null, 2, null);
    private final MutableState isVisible$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
    private final String name;
    private final int type;

    public AndroidWindowInsets(int i, String str) {
        this.type = i;
        this.name = str;
    }

    public final Insets getInsets$foundation_layout_release() {
        return (Insets) this.insets$delegate.getValue();
    }

    public final void setInsets$foundation_layout_release(Insets insets) {
        this.insets$delegate.setValue(insets);
    }

    private final void setVisible(boolean z) {
        this.isVisible$delegate.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getLeft(Density density, LayoutDirection layoutDirection) {
        return getInsets$foundation_layout_release().left;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getTop(Density density) {
        return getInsets$foundation_layout_release().top;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getRight(Density density, LayoutDirection layoutDirection) {
        return getInsets$foundation_layout_release().right;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public int getBottom(Density density) {
        return getInsets$foundation_layout_release().bottom;
    }

    public final void update$foundation_layout_release(WindowInsetsCompat windowInsetsCompat, int i) {
        if (i == 0 || (i & this.type) != 0) {
            setInsets$foundation_layout_release(windowInsetsCompat.getInsets(this.type));
            setVisible(windowInsetsCompat.isVisible(this.type));
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AndroidWindowInsets) && this.type == ((AndroidWindowInsets) obj).type;
    }

    public int hashCode() {
        return this.type;
    }

    public String toString() {
        return this.name + CoreConstants.LEFT_PARENTHESIS_CHAR + getInsets$foundation_layout_release().left + ", " + getInsets$foundation_layout_release().top + ", " + getInsets$foundation_layout_release().right + ", " + getInsets$foundation_layout_release().bottom + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
