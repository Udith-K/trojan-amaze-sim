package androidx.compose.ui.layout;

import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.LayoutModifierNodeCoordinator;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApproachMeasureScope.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ApproachMeasureScopeImpl implements IntrinsicMeasureScope, MeasureScope {
    private final LayoutModifierNodeCoordinator coordinator;

    public final ApproachLayoutModifierNode getApproachNode() {
        return null;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.coordinator.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.coordinator.getFontScale();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return this.coordinator.getLayoutDirection();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return false;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(int i, int i2, Map map, Function1 function1) {
        return this.coordinator.layout(i, i2, map, function1);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public int mo204roundToPx0680j_4(float f) {
        return this.coordinator.mo204roundToPx0680j_4(f);
    }

    public final void setApproachNode(ApproachLayoutModifierNode approachLayoutModifierNode) {
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public float mo205toDpGaN1DYA(long j) {
        return this.coordinator.mo205toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo206toDpu2uoSUM(float f) {
        return this.coordinator.mo206toDpu2uoSUM(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo207toDpu2uoSUM(int i) {
        return this.coordinator.mo207toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public long mo208toDpSizekrfVVM(long j) {
        return this.coordinator.mo208toDpSizekrfVVM(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public float mo209toPxR2X_6o(long j) {
        return this.coordinator.mo209toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public float mo210toPx0680j_4(float f) {
        return this.coordinator.mo210toPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public long mo211toSizeXkaWNTQ(long j) {
        return this.coordinator.mo211toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public long mo212toSp0xMU5do(float f) {
        return this.coordinator.mo212toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public long mo213toSpkPz2Gy4(float f) {
        return this.coordinator.mo213toSpkPz2Gy4(f);
    }

    public ApproachMeasureScopeImpl(LayoutModifierNodeCoordinator layoutModifierNodeCoordinator, ApproachLayoutModifierNode approachLayoutModifierNode) {
        this.coordinator = layoutModifierNodeCoordinator;
    }

    public final LayoutModifierNodeCoordinator getCoordinator() {
        return this.coordinator;
    }

    /* JADX INFO: renamed from: getLookaheadSize-YbymL2g, reason: not valid java name */
    public long m1724getLookaheadSizeYbymL2g() {
        LookaheadDelegate lookaheadDelegate = this.coordinator.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        MeasureResult measureResult$ui_release = lookaheadDelegate.getMeasureResult$ui_release();
        return IntSizeKt.IntSize(measureResult$ui_release.getWidth(), measureResult$ui_release.getHeight());
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(int i, int i2, Map map, Function1 function1, Function1 function12) {
        if (!((i & (-16777216)) == 0 && ((-16777216) & i2) == 0)) {
            InlineClassHelperKt.throwIllegalStateException("Size(" + i + " x " + i2 + ") is out of range. Each dimension must be between 0 and 16777215.");
        }
        return new MeasureResult(i, i2, map, function1, function12, this) { // from class: androidx.compose.ui.layout.ApproachMeasureScopeImpl.layout.1
            final /* synthetic */ Function1 $placementBlock;
            private final Map alignmentLines;
            private final int height;
            private final Function1 rulers;
            final /* synthetic */ ApproachMeasureScopeImpl this$0;
            private final int width;

            {
                this.$placementBlock = function12;
                this.this$0 = this;
                this.width = i;
                this.height = i2;
                this.alignmentLines = map;
                this.rulers = function1;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getWidth() {
                return this.width;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getHeight() {
                return this.height;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map getAlignmentLines() {
                return this.alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Function1 getRulers() {
                return this.rulers;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                this.$placementBlock.invoke(this.this$0.getCoordinator().getPlacementScope());
            }
        };
    }
}
