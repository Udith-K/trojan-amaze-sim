package androidx.compose.foundation;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Scroll.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ScrollingLayoutNode extends Modifier.Node implements LayoutModifierNode {
    private boolean isReversed;
    private boolean isVertical;
    private ScrollState scrollerState;

    public final ScrollState getScrollerState() {
        return this.scrollerState;
    }

    public final void setScrollerState(ScrollState scrollState) {
        this.scrollerState = scrollState;
    }

    public final boolean isReversed() {
        return this.isReversed;
    }

    public final void setReversed(boolean z) {
        this.isReversed = z;
    }

    public final boolean isVertical() {
        return this.isVertical;
    }

    public final void setVertical(boolean z) {
        this.isVertical = z;
    }

    public ScrollingLayoutNode(ScrollState scrollState, boolean z, boolean z2) {
        this.scrollerState = scrollState;
        this.isReversed = z;
        this.isVertical = z2;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo36measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        CheckScrollableContainerConstraintsKt.m112checkScrollableContainerConstraintsK40F9xA(j, this.isVertical ? Orientation.Vertical : Orientation.Horizontal);
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(Constraints.m2381copyZbe2FdA$default(j, 0, this.isVertical ? Constraints.m2389getMaxWidthimpl(j) : Integer.MAX_VALUE, 0, this.isVertical ? Integer.MAX_VALUE : Constraints.m2388getMaxHeightimpl(j), 5, null));
        int iCoerceAtMost = RangesKt.coerceAtMost(placeableMo1743measureBRTryo0.getWidth(), Constraints.m2389getMaxWidthimpl(j));
        int iCoerceAtMost2 = RangesKt.coerceAtMost(placeableMo1743measureBRTryo0.getHeight(), Constraints.m2388getMaxHeightimpl(j));
        final int height = placeableMo1743measureBRTryo0.getHeight() - iCoerceAtMost2;
        int width = placeableMo1743measureBRTryo0.getWidth() - iCoerceAtMost;
        if (!this.isVertical) {
            height = width;
        }
        this.scrollerState.setMaxValue$foundation_release(height);
        this.scrollerState.setViewportSize$foundation_release(this.isVertical ? iCoerceAtMost2 : iCoerceAtMost);
        return MeasureScope.CC.layout$default(measureScope, iCoerceAtMost, iCoerceAtMost2, null, new Function1() { // from class: androidx.compose.foundation.ScrollingLayoutNode$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                int iCoerceIn = RangesKt.coerceIn(this.this$0.getScrollerState().getValue(), 0, height);
                int i = this.this$0.isReversed() ? iCoerceIn - height : -iCoerceIn;
                final int i2 = this.this$0.isVertical() ? 0 : i;
                final int i3 = this.this$0.isVertical() ? i : 0;
                final Placeable placeable = placeableMo1743measureBRTryo0;
                placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: androidx.compose.foundation.ScrollingLayoutNode$measure$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Placeable.PlacementScope) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Placeable.PlacementScope placementScope2) {
                        Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope2, placeable, i2, i3, 0.0f, null, 12, null);
                    }
                });
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.isVertical) {
            return intrinsicMeasurable.minIntrinsicWidth(Integer.MAX_VALUE);
        }
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.isVertical) {
            return intrinsicMeasurable.minIntrinsicHeight(i);
        }
        return intrinsicMeasurable.minIntrinsicHeight(Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.isVertical) {
            return intrinsicMeasurable.maxIntrinsicWidth(Integer.MAX_VALUE);
        }
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.isVertical) {
            return intrinsicMeasurable.maxIntrinsicHeight(i);
        }
        return intrinsicMeasurable.maxIntrinsicHeight(Integer.MAX_VALUE);
    }
}
