package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes.dex */
final class SizeNode extends Modifier.Node implements LayoutModifierNode {
    private boolean enforceIncoming;
    private float maxHeight;
    private float maxWidth;
    private float minHeight;
    private float minWidth;

    public /* synthetic */ SizeNode(float f, float f2, float f3, float f4, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, z);
    }

    /* JADX INFO: renamed from: setMinWidth-0680j_4, reason: not valid java name */
    public final void m290setMinWidth0680j_4(float f) {
        this.minWidth = f;
    }

    /* JADX INFO: renamed from: setMinHeight-0680j_4, reason: not valid java name */
    public final void m289setMinHeight0680j_4(float f) {
        this.minHeight = f;
    }

    /* JADX INFO: renamed from: setMaxWidth-0680j_4, reason: not valid java name */
    public final void m288setMaxWidth0680j_4(float f) {
        this.maxWidth = f;
    }

    /* JADX INFO: renamed from: setMaxHeight-0680j_4, reason: not valid java name */
    public final void m287setMaxHeight0680j_4(float f) {
        this.maxHeight = f;
    }

    public final void setEnforceIncoming(boolean z) {
        this.enforceIncoming = z;
    }

    private SizeNode(float f, float f2, float f3, float f4, boolean z) {
        this.minWidth = f;
        this.minHeight = f2;
        this.maxWidth = f3;
        this.maxHeight = f4;
        this.enforceIncoming = z;
    }

    /* JADX INFO: renamed from: getTargetConstraints-OenEA2s, reason: not valid java name */
    private final long m286getTargetConstraintsOenEA2s(Density density) {
        int iCoerceAtLeast;
        int iCoerceAtLeast2;
        float f = this.maxWidth;
        Dp.Companion companion = Dp.Companion;
        int i = 0;
        int iCoerceAtLeast3 = !Dp.m2418equalsimpl0(f, companion.m2425getUnspecifiedD9Ej5fM()) ? RangesKt.coerceAtLeast(density.mo204roundToPx0680j_4(this.maxWidth), 0) : Integer.MAX_VALUE;
        int iCoerceAtLeast4 = !Dp.m2418equalsimpl0(this.maxHeight, companion.m2425getUnspecifiedD9Ej5fM()) ? RangesKt.coerceAtLeast(density.mo204roundToPx0680j_4(this.maxHeight), 0) : Integer.MAX_VALUE;
        if (Dp.m2418equalsimpl0(this.minWidth, companion.m2425getUnspecifiedD9Ej5fM()) || (iCoerceAtLeast = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(density.mo204roundToPx0680j_4(this.minWidth), iCoerceAtLeast3), 0)) == Integer.MAX_VALUE) {
            iCoerceAtLeast = 0;
        }
        if (!Dp.m2418equalsimpl0(this.minHeight, companion.m2425getUnspecifiedD9Ej5fM()) && (iCoerceAtLeast2 = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(density.mo204roundToPx0680j_4(this.minHeight), iCoerceAtLeast4), 0)) != Integer.MAX_VALUE) {
            i = iCoerceAtLeast2;
        }
        return ConstraintsKt.Constraints(iCoerceAtLeast, iCoerceAtLeast3, i, iCoerceAtLeast4);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo36measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int iCoerceAtMost;
        int iCoerceAtLeast;
        int iCoerceAtMost2;
        int iCoerceAtLeast2;
        long jConstraints;
        long jM286getTargetConstraintsOenEA2s = m286getTargetConstraintsOenEA2s(measureScope);
        if (this.enforceIncoming) {
            jConstraints = ConstraintsKt.m2401constrainN9IONVI(j, jM286getTargetConstraintsOenEA2s);
        } else {
            float f = this.minWidth;
            Dp.Companion companion = Dp.Companion;
            if (!Dp.m2418equalsimpl0(f, companion.m2425getUnspecifiedD9Ej5fM())) {
                iCoerceAtMost = Constraints.m2391getMinWidthimpl(jM286getTargetConstraintsOenEA2s);
            } else {
                iCoerceAtMost = RangesKt.coerceAtMost(Constraints.m2391getMinWidthimpl(j), Constraints.m2389getMaxWidthimpl(jM286getTargetConstraintsOenEA2s));
            }
            if (!Dp.m2418equalsimpl0(this.maxWidth, companion.m2425getUnspecifiedD9Ej5fM())) {
                iCoerceAtLeast = Constraints.m2389getMaxWidthimpl(jM286getTargetConstraintsOenEA2s);
            } else {
                iCoerceAtLeast = RangesKt.coerceAtLeast(Constraints.m2389getMaxWidthimpl(j), Constraints.m2391getMinWidthimpl(jM286getTargetConstraintsOenEA2s));
            }
            if (!Dp.m2418equalsimpl0(this.minHeight, companion.m2425getUnspecifiedD9Ej5fM())) {
                iCoerceAtMost2 = Constraints.m2390getMinHeightimpl(jM286getTargetConstraintsOenEA2s);
            } else {
                iCoerceAtMost2 = RangesKt.coerceAtMost(Constraints.m2390getMinHeightimpl(j), Constraints.m2388getMaxHeightimpl(jM286getTargetConstraintsOenEA2s));
            }
            if (!Dp.m2418equalsimpl0(this.maxHeight, companion.m2425getUnspecifiedD9Ej5fM())) {
                iCoerceAtLeast2 = Constraints.m2388getMaxHeightimpl(jM286getTargetConstraintsOenEA2s);
            } else {
                iCoerceAtLeast2 = RangesKt.coerceAtLeast(Constraints.m2388getMaxHeightimpl(j), Constraints.m2390getMinHeightimpl(jM286getTargetConstraintsOenEA2s));
            }
            jConstraints = ConstraintsKt.Constraints(iCoerceAtMost, iCoerceAtLeast, iCoerceAtMost2, iCoerceAtLeast2);
        }
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(jConstraints);
        return MeasureScope.CC.layout$default(measureScope, placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.layout.SizeNode$measure$1
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

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long jM286getTargetConstraintsOenEA2s = m286getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m2387getHasFixedWidthimpl(jM286getTargetConstraintsOenEA2s)) {
            return Constraints.m2389getMaxWidthimpl(jM286getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m2403constrainWidthK40F9xA(jM286getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long jM286getTargetConstraintsOenEA2s = m286getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m2386getHasFixedHeightimpl(jM286getTargetConstraintsOenEA2s)) {
            return Constraints.m2388getMaxHeightimpl(jM286getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m2402constrainHeightK40F9xA(jM286getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long jM286getTargetConstraintsOenEA2s = m286getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m2387getHasFixedWidthimpl(jM286getTargetConstraintsOenEA2s)) {
            return Constraints.m2389getMaxWidthimpl(jM286getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m2403constrainWidthK40F9xA(jM286getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long jM286getTargetConstraintsOenEA2s = m286getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m2386getHasFixedHeightimpl(jM286getTargetConstraintsOenEA2s)) {
            return Constraints.m2388getMaxHeightimpl(jM286getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m2402constrainHeightK40F9xA(jM286getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicHeight(i));
    }
}
