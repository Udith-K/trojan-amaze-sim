package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.NodeMeasuringIntrinsics;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes.dex */
final class FillNode extends Modifier.Node implements LayoutModifierNode {
    private Direction direction;
    private float fraction;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.maxHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.maxIntrinsicHeight.1
            AnonymousClass1() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo1803measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo36measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.maxWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.maxIntrinsicWidth.1
            C01511() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo1803measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo36measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.minHeight$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.minIntrinsicHeight.1
            C01521() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo1803measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo36measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return NodeMeasuringIntrinsics.INSTANCE.minWidth$ui_release(new NodeMeasuringIntrinsics.MeasureBlock() { // from class: androidx.compose.ui.node.LayoutModifierNode.minIntrinsicWidth.1
            C01531() {
            }

            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.MeasureBlock
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo1803measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                return LayoutModifierNode.this.mo36measure3p2s80s(measureScope, measurable, j);
            }
        }, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    public final void setDirection(Direction direction) {
        this.direction = direction;
    }

    public final void setFraction(float f) {
        this.fraction = f;
    }

    public FillNode(Direction direction, float f) {
        this.direction = direction;
        this.fraction = f;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo36measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int iM2391getMinWidthimpl;
        int iM2389getMaxWidthimpl;
        int iM2388getMaxHeightimpl;
        int iCoerceIn;
        if (Constraints.m2385getHasBoundedWidthimpl(j) && this.direction != Direction.Vertical) {
            iM2391getMinWidthimpl = RangesKt.coerceIn(Math.round(Constraints.m2389getMaxWidthimpl(j) * this.fraction), Constraints.m2391getMinWidthimpl(j), Constraints.m2389getMaxWidthimpl(j));
            iM2389getMaxWidthimpl = iM2391getMinWidthimpl;
        } else {
            iM2391getMinWidthimpl = Constraints.m2391getMinWidthimpl(j);
            iM2389getMaxWidthimpl = Constraints.m2389getMaxWidthimpl(j);
        }
        if (Constraints.m2384getHasBoundedHeightimpl(j) && this.direction != Direction.Horizontal) {
            iCoerceIn = RangesKt.coerceIn(Math.round(Constraints.m2388getMaxHeightimpl(j) * this.fraction), Constraints.m2390getMinHeightimpl(j), Constraints.m2388getMaxHeightimpl(j));
            iM2388getMaxHeightimpl = iCoerceIn;
        } else {
            int iM2390getMinHeightimpl = Constraints.m2390getMinHeightimpl(j);
            iM2388getMaxHeightimpl = Constraints.m2388getMaxHeightimpl(j);
            iCoerceIn = iM2390getMinHeightimpl;
        }
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(ConstraintsKt.Constraints(iM2391getMinWidthimpl, iM2389getMaxWidthimpl, iCoerceIn, iM2388getMaxHeightimpl));
        return MeasureScope.CC.layout$default(measureScope, placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.layout.FillNode$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo1743measureBRTryo0, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }
}
