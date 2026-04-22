package androidx.compose.ui.platform;

import androidx.compose.ui.node.OwnerScope;
import androidx.compose.ui.semantics.ScrollAxisRange;
import java.util.List;

/* JADX INFO: compiled from: SemanticsUtils.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ScrollObservationScope implements OwnerScope {
    private final List allScopes;
    private ScrollAxisRange horizontalScrollAxisRange;
    private Float oldXValue;
    private Float oldYValue;
    private final int semanticsNodeId;
    private ScrollAxisRange verticalScrollAxisRange;

    public ScrollObservationScope(int i, List list, Float f, Float f2, ScrollAxisRange scrollAxisRange, ScrollAxisRange scrollAxisRange2) {
        this.semanticsNodeId = i;
        this.allScopes = list;
        this.oldXValue = f;
        this.oldYValue = f2;
        this.horizontalScrollAxisRange = scrollAxisRange;
        this.verticalScrollAxisRange = scrollAxisRange2;
    }

    public final int getSemanticsNodeId() {
        return this.semanticsNodeId;
    }

    public final Float getOldXValue() {
        return this.oldXValue;
    }

    public final void setOldXValue(Float f) {
        this.oldXValue = f;
    }

    public final Float getOldYValue() {
        return this.oldYValue;
    }

    public final void setOldYValue(Float f) {
        this.oldYValue = f;
    }

    public final ScrollAxisRange getHorizontalScrollAxisRange() {
        return this.horizontalScrollAxisRange;
    }

    public final void setHorizontalScrollAxisRange(ScrollAxisRange scrollAxisRange) {
        this.horizontalScrollAxisRange = scrollAxisRange;
    }

    public final ScrollAxisRange getVerticalScrollAxisRange() {
        return this.verticalScrollAxisRange;
    }

    public final void setVerticalScrollAxisRange(ScrollAxisRange scrollAxisRange) {
        this.verticalScrollAxisRange = scrollAxisRange;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return this.allScopes.contains(this);
    }
}
