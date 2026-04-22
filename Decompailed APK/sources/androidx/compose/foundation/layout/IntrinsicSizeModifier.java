package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Intrinsic.kt */
/* JADX INFO: loaded from: classes.dex */
abstract class IntrinsicSizeModifier extends Modifier.Node implements LayoutModifierNode {
    /* JADX INFO: renamed from: calculateContentConstraints-l58MMJ0, reason: not valid java name */
    public abstract long mo256calculateContentConstraintsl58MMJ0(MeasureScope measureScope, Measurable measurable, long j);

    public abstract boolean getEnforceIncoming();

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public final MeasureResult mo36measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        long jMo256calculateContentConstraintsl58MMJ0 = mo256calculateContentConstraintsl58MMJ0(measureScope, measurable, j);
        if (getEnforceIncoming()) {
            jMo256calculateContentConstraintsl58MMJ0 = ConstraintsKt.m2401constrainN9IONVI(j, jMo256calculateContentConstraintsl58MMJ0);
        }
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(jMo256calculateContentConstraintsl58MMJ0);
        return MeasureScope.CC.layout$default(measureScope, placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.layout.IntrinsicSizeModifier$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                Placeable.PlacementScope.m1766placeRelative70tqf50$default(placementScope, placeableMo1743measureBRTryo0, IntOffset.Companion.m2464getZeronOccac(), 0.0f, 2, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }
}
