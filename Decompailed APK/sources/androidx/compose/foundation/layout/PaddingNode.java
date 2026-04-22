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
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Padding.kt */
/* JADX INFO: loaded from: classes.dex */
final class PaddingNode extends Modifier.Node implements LayoutModifierNode {
    private float bottom;
    private float end;
    private boolean rtlAware;
    private float start;
    private float top;

    public /* synthetic */ PaddingNode(float f, float f2, float f3, float f4, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, z);
    }

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

    /* JADX INFO: renamed from: getStart-D9Ej5fM, reason: not valid java name */
    public final float m266getStartD9Ej5fM() {
        return this.start;
    }

    /* JADX INFO: renamed from: setStart-0680j_4, reason: not valid java name */
    public final void m270setStart0680j_4(float f) {
        this.start = f;
    }

    /* JADX INFO: renamed from: getTop-D9Ej5fM, reason: not valid java name */
    public final float m267getTopD9Ej5fM() {
        return this.top;
    }

    /* JADX INFO: renamed from: setTop-0680j_4, reason: not valid java name */
    public final void m271setTop0680j_4(float f) {
        this.top = f;
    }

    /* JADX INFO: renamed from: setEnd-0680j_4, reason: not valid java name */
    public final void m269setEnd0680j_4(float f) {
        this.end = f;
    }

    /* JADX INFO: renamed from: setBottom-0680j_4, reason: not valid java name */
    public final void m268setBottom0680j_4(float f) {
        this.bottom = f;
    }

    public final boolean getRtlAware() {
        return this.rtlAware;
    }

    public final void setRtlAware(boolean z) {
        this.rtlAware = z;
    }

    private PaddingNode(float f, float f2, float f3, float f4, boolean z) {
        this.start = f;
        this.top = f2;
        this.end = f3;
        this.bottom = f4;
        this.rtlAware = z;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo36measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        int iMo204roundToPx0680j_4 = measureScope.mo204roundToPx0680j_4(this.start) + measureScope.mo204roundToPx0680j_4(this.end);
        int iMo204roundToPx0680j_42 = measureScope.mo204roundToPx0680j_4(this.top) + measureScope.mo204roundToPx0680j_4(this.bottom);
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(ConstraintsKt.m2404offsetNN6EwU(j, -iMo204roundToPx0680j_4, -iMo204roundToPx0680j_42));
        return MeasureScope.CC.layout$default(measureScope, ConstraintsKt.m2403constrainWidthK40F9xA(j, placeableMo1743measureBRTryo0.getWidth() + iMo204roundToPx0680j_4), ConstraintsKt.m2402constrainHeightK40F9xA(j, placeableMo1743measureBRTryo0.getHeight() + iMo204roundToPx0680j_42), null, new Function1() { // from class: androidx.compose.foundation.layout.PaddingNode$measure$1
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
                if (this.this$0.getRtlAware()) {
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo1743measureBRTryo0, measureScope.mo204roundToPx0680j_4(this.this$0.m266getStartD9Ej5fM()), measureScope.mo204roundToPx0680j_4(this.this$0.m267getTopD9Ej5fM()), 0.0f, 4, null);
                } else {
                    Placeable.PlacementScope.place$default(placementScope, placeableMo1743measureBRTryo0, measureScope.mo204roundToPx0680j_4(this.this$0.m266getStartD9Ej5fM()), measureScope.mo204roundToPx0680j_4(this.this$0.m267getTopD9Ej5fM()), 0.0f, 4, null);
                }
            }
        }, 4, null);
    }
}
