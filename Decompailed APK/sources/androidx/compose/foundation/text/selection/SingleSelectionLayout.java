package androidx.compose.foundation.text.selection;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SelectionLayout.kt */
/* JADX INFO: loaded from: classes.dex */
final class SingleSelectionLayout implements SelectionLayout {
    public static final Companion Companion = new Companion(null);
    private final int endSlot;

    /* JADX INFO: renamed from: info, reason: collision with root package name */
    private final SelectableInfo f65info;
    private final boolean isStartHandle;
    private final Selection previousSelection;
    private final int startSlot;

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public void forEachMiddleInfo(Function1 function1) {
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getSize() {
        return 1;
    }

    public SingleSelectionLayout(boolean z, int i, int i2, Selection selection, SelectableInfo selectableInfo) {
        this.isStartHandle = z;
        this.startSlot = i;
        this.endSlot = i2;
        this.previousSelection = selection;
        this.f65info = selectableInfo;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public boolean isStartHandle() {
        return this.isStartHandle;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getStartSlot() {
        return this.startSlot;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public int getEndSlot() {
        return this.endSlot;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public Selection getPreviousSelection() {
        return this.previousSelection;
    }

    /* JADX INFO: compiled from: SelectionLayout.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public CrossStatus getCrossStatus() {
        return getStartSlot() < getEndSlot() ? CrossStatus.NOT_CROSSED : getStartSlot() > getEndSlot() ? CrossStatus.CROSSED : this.f65info.getRawCrossStatus();
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getStartInfo() {
        return this.f65info;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getEndInfo() {
        return this.f65info;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getCurrentInfo() {
        return this.f65info;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public SelectableInfo getFirstInfo() {
        return this.f65info;
    }

    @Override // androidx.compose.foundation.text.selection.SelectionLayout
    public boolean shouldRecomputeSelection(SelectionLayout selectionLayout) {
        if (getPreviousSelection() != null && selectionLayout != null && (selectionLayout instanceof SingleSelectionLayout)) {
            SingleSelectionLayout singleSelectionLayout = (SingleSelectionLayout) selectionLayout;
            if (getStartSlot() == singleSelectionLayout.getStartSlot() && getEndSlot() == singleSelectionLayout.getEndSlot() && isStartHandle() == singleSelectionLayout.isStartHandle() && !this.f65info.shouldRecomputeSelection(singleSelectionLayout.f65info)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "SingleSelectionLayout(isStartHandle=" + isStartHandle() + ", crossed=" + getCrossStatus() + ", info=\n\t" + this.f65info + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
