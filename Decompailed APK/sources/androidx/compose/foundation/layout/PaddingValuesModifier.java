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
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Padding.kt */
/* JADX INFO: loaded from: classes.dex */
final class PaddingValuesModifier extends Modifier.Node implements LayoutModifierNode {
    private PaddingValues paddingValues;

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

    public final PaddingValues getPaddingValues() {
        return this.paddingValues;
    }

    public final void setPaddingValues(PaddingValues paddingValues) {
        this.paddingValues = paddingValues;
    }

    public PaddingValuesModifier(PaddingValues paddingValues) {
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo36measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        float f = 0;
        if (Dp.m2415compareTo0680j_4(this.paddingValues.mo253calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection()), Dp.m2416constructorimpl(f)) < 0 || Dp.m2415compareTo0680j_4(this.paddingValues.mo255calculateTopPaddingD9Ej5fM(), Dp.m2416constructorimpl(f)) < 0 || Dp.m2415compareTo0680j_4(this.paddingValues.mo254calculateRightPaddingu2uoSUM(measureScope.getLayoutDirection()), Dp.m2416constructorimpl(f)) < 0 || Dp.m2415compareTo0680j_4(this.paddingValues.mo252calculateBottomPaddingD9Ej5fM(), Dp.m2416constructorimpl(f)) < 0) {
            throw new IllegalArgumentException("Padding must be non-negative");
        }
        int iMo204roundToPx0680j_4 = measureScope.mo204roundToPx0680j_4(this.paddingValues.mo253calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection())) + measureScope.mo204roundToPx0680j_4(this.paddingValues.mo254calculateRightPaddingu2uoSUM(measureScope.getLayoutDirection()));
        int iMo204roundToPx0680j_42 = measureScope.mo204roundToPx0680j_4(this.paddingValues.mo255calculateTopPaddingD9Ej5fM()) + measureScope.mo204roundToPx0680j_4(this.paddingValues.mo252calculateBottomPaddingD9Ej5fM());
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(ConstraintsKt.m2404offsetNN6EwU(j, -iMo204roundToPx0680j_4, -iMo204roundToPx0680j_42));
        return MeasureScope.CC.layout$default(measureScope, ConstraintsKt.m2403constrainWidthK40F9xA(j, placeableMo1743measureBRTryo0.getWidth() + iMo204roundToPx0680j_4), ConstraintsKt.m2402constrainHeightK40F9xA(j, placeableMo1743measureBRTryo0.getHeight() + iMo204roundToPx0680j_42), null, new Function1() { // from class: androidx.compose.foundation.layout.PaddingValuesModifier$measure$2
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
                Placeable.PlacementScope.place$default(placementScope, placeableMo1743measureBRTryo0, measureScope.mo204roundToPx0680j_4(this.getPaddingValues().mo253calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection())), measureScope.mo204roundToPx0680j_4(this.getPaddingValues().mo255calculateTopPaddingD9Ej5fM()), 0.0f, 4, null);
            }
        }, 4, null);
    }
}
