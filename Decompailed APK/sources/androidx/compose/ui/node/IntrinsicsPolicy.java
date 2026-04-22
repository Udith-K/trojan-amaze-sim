package androidx.compose.ui.node;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.layout.MeasurePolicy;

/* JADX INFO: compiled from: IntrinsicsPolicy.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IntrinsicsPolicy {
    private final LayoutNode layoutNode;
    private final MutableState measurePolicyState$delegate;

    public IntrinsicsPolicy(LayoutNode layoutNode, MeasurePolicy measurePolicy) {
        this.layoutNode = layoutNode;
        this.measurePolicyState$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(measurePolicy, null, 2, null);
    }

    private final MeasurePolicy getMeasurePolicyState() {
        return (MeasurePolicy) this.measurePolicyState$delegate.getValue();
    }

    private final void setMeasurePolicyState(MeasurePolicy measurePolicy) {
        this.measurePolicyState$delegate.setValue(measurePolicy);
    }

    public final void updateFrom(MeasurePolicy measurePolicy) {
        setMeasurePolicyState(measurePolicy);
    }

    public final int minIntrinsicWidth(int i) {
        return getMeasurePolicyState().minIntrinsicWidth(this.layoutNode.getOuterCoordinator$ui_release(), this.layoutNode.getChildMeasurables$ui_release(), i);
    }

    public final int minIntrinsicHeight(int i) {
        return getMeasurePolicyState().minIntrinsicHeight(this.layoutNode.getOuterCoordinator$ui_release(), this.layoutNode.getChildMeasurables$ui_release(), i);
    }

    public final int maxIntrinsicWidth(int i) {
        return getMeasurePolicyState().maxIntrinsicWidth(this.layoutNode.getOuterCoordinator$ui_release(), this.layoutNode.getChildMeasurables$ui_release(), i);
    }

    public final int maxIntrinsicHeight(int i) {
        return getMeasurePolicyState().maxIntrinsicHeight(this.layoutNode.getOuterCoordinator$ui_release(), this.layoutNode.getChildMeasurables$ui_release(), i);
    }

    public final int minLookaheadIntrinsicWidth(int i) {
        return getMeasurePolicyState().minIntrinsicWidth(this.layoutNode.getOuterCoordinator$ui_release(), this.layoutNode.getChildLookaheadMeasurables$ui_release(), i);
    }

    public final int minLookaheadIntrinsicHeight(int i) {
        return getMeasurePolicyState().minIntrinsicHeight(this.layoutNode.getOuterCoordinator$ui_release(), this.layoutNode.getChildLookaheadMeasurables$ui_release(), i);
    }

    public final int maxLookaheadIntrinsicWidth(int i) {
        return getMeasurePolicyState().maxIntrinsicWidth(this.layoutNode.getOuterCoordinator$ui_release(), this.layoutNode.getChildLookaheadMeasurables$ui_release(), i);
    }

    public final int maxLookaheadIntrinsicHeight(int i) {
        return getMeasurePolicyState().maxIntrinsicHeight(this.layoutNode.getOuterCoordinator$ui_release(), this.layoutNode.getChildLookaheadMeasurables$ui_release(), i);
    }
}
