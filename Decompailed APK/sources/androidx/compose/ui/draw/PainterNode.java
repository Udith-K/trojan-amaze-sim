package androidx.compose.ui.draw;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import ch.qos.logback.core.CoreConstants;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: PainterModifier.kt */
/* JADX INFO: loaded from: classes.dex */
final class PainterNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode {
    private Alignment alignment;
    private float alpha;
    private ColorFilter colorFilter;
    private ContentScale contentScale;
    private Painter painter;
    private boolean sizeToIntrinsics;

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public /* synthetic */ void onMeasureResultChanged() {
        DrawModifierNode.CC.$default$onMeasureResultChanged(this);
    }

    public final Painter getPainter() {
        return this.painter;
    }

    public final void setPainter(Painter painter) {
        this.painter = painter;
    }

    public final boolean getSizeToIntrinsics() {
        return this.sizeToIntrinsics;
    }

    public final void setSizeToIntrinsics(boolean z) {
        this.sizeToIntrinsics = z;
    }

    public final void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public final void setContentScale(ContentScale contentScale) {
        this.contentScale = contentScale;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
    }

    public PainterNode(Painter painter, boolean z, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter) {
        this.painter = painter;
        this.sizeToIntrinsics = z;
        this.alignment = alignment;
        this.contentScale = contentScale;
        this.alpha = f;
        this.colorFilter = colorFilter;
    }

    private final boolean getUseIntrinsicSize() {
        return this.sizeToIntrinsics && this.painter.mo1548getIntrinsicSizeNHjbRc() != 9205357640488583168L;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo36measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(m1081modifyConstraintsZezNO4M(j));
        return MeasureScope.CC.layout$default(measureScope, placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight(), null, new Function1() { // from class: androidx.compose.ui.draw.PainterNode$measure$1
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
        if (getUseIntrinsicSize()) {
            long jM1081modifyConstraintsZezNO4M = m1081modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null));
            return Math.max(Constraints.m2391getMinWidthimpl(jM1081modifyConstraintsZezNO4M), intrinsicMeasurable.minIntrinsicWidth(i));
        }
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (getUseIntrinsicSize()) {
            long jM1081modifyConstraintsZezNO4M = m1081modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null));
            return Math.max(Constraints.m2391getMinWidthimpl(jM1081modifyConstraintsZezNO4M), intrinsicMeasurable.maxIntrinsicWidth(i));
        }
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (getUseIntrinsicSize()) {
            long jM1081modifyConstraintsZezNO4M = m1081modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null));
            return Math.max(Constraints.m2390getMinHeightimpl(jM1081modifyConstraintsZezNO4M), intrinsicMeasurable.minIntrinsicHeight(i));
        }
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (getUseIntrinsicSize()) {
            long jM1081modifyConstraintsZezNO4M = m1081modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null));
            return Math.max(Constraints.m2390getMinHeightimpl(jM1081modifyConstraintsZezNO4M), intrinsicMeasurable.maxIntrinsicHeight(i));
        }
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    /* JADX INFO: renamed from: calculateScaledSize-E7KxVPU, reason: not valid java name */
    private final long m1078calculateScaledSizeE7KxVPU(long j) {
        float fM1196getWidthimpl;
        float fM1194getHeightimpl;
        if (!getUseIntrinsicSize()) {
            return j;
        }
        if (!m1080hasSpecifiedAndFiniteWidthuvyYCjk(this.painter.mo1548getIntrinsicSizeNHjbRc())) {
            fM1196getWidthimpl = Size.m1196getWidthimpl(j);
        } else {
            fM1196getWidthimpl = Size.m1196getWidthimpl(this.painter.mo1548getIntrinsicSizeNHjbRc());
        }
        if (!m1079hasSpecifiedAndFiniteHeightuvyYCjk(this.painter.mo1548getIntrinsicSizeNHjbRc())) {
            fM1194getHeightimpl = Size.m1194getHeightimpl(j);
        } else {
            fM1194getHeightimpl = Size.m1194getHeightimpl(this.painter.mo1548getIntrinsicSizeNHjbRc());
        }
        long jSize = SizeKt.Size(fM1196getWidthimpl, fM1194getHeightimpl);
        if (Size.m1196getWidthimpl(j) != 0.0f && Size.m1194getHeightimpl(j) != 0.0f) {
            return ScaleFactorKt.m1782timesUQTWf7w(jSize, this.contentScale.mo1734computeScaleFactorH7hwNQA(jSize, j));
        }
        return Size.Companion.m1202getZeroNHjbRc();
    }

    /* JADX INFO: renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m1081modifyConstraintsZezNO4M(long j) {
        boolean z = false;
        boolean z2 = Constraints.m2385getHasBoundedWidthimpl(j) && Constraints.m2384getHasBoundedHeightimpl(j);
        if (Constraints.m2387getHasFixedWidthimpl(j) && Constraints.m2386getHasFixedHeightimpl(j)) {
            z = true;
        }
        if ((!getUseIntrinsicSize() && z2) || z) {
            return Constraints.m2381copyZbe2FdA$default(j, Constraints.m2389getMaxWidthimpl(j), 0, Constraints.m2388getMaxHeightimpl(j), 0, 10, null);
        }
        long jMo1548getIntrinsicSizeNHjbRc = this.painter.mo1548getIntrinsicSizeNHjbRc();
        long jM1078calculateScaledSizeE7KxVPU = m1078calculateScaledSizeE7KxVPU(SizeKt.Size(ConstraintsKt.m2403constrainWidthK40F9xA(j, m1080hasSpecifiedAndFiniteWidthuvyYCjk(jMo1548getIntrinsicSizeNHjbRc) ? Math.round(Size.m1196getWidthimpl(jMo1548getIntrinsicSizeNHjbRc)) : Constraints.m2391getMinWidthimpl(j)), ConstraintsKt.m2402constrainHeightK40F9xA(j, m1079hasSpecifiedAndFiniteHeightuvyYCjk(jMo1548getIntrinsicSizeNHjbRc) ? Math.round(Size.m1194getHeightimpl(jMo1548getIntrinsicSizeNHjbRc)) : Constraints.m2390getMinHeightimpl(j))));
        return Constraints.m2381copyZbe2FdA$default(j, ConstraintsKt.m2403constrainWidthK40F9xA(j, Math.round(Size.m1196getWidthimpl(jM1078calculateScaledSizeE7KxVPU))), 0, ConstraintsKt.m2402constrainHeightK40F9xA(j, Math.round(Size.m1194getHeightimpl(jM1078calculateScaledSizeE7KxVPU))), 0, 10, null);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        long jMo1548getIntrinsicSizeNHjbRc = this.painter.mo1548getIntrinsicSizeNHjbRc();
        long jSize = SizeKt.Size(m1080hasSpecifiedAndFiniteWidthuvyYCjk(jMo1548getIntrinsicSizeNHjbRc) ? Size.m1196getWidthimpl(jMo1548getIntrinsicSizeNHjbRc) : Size.m1196getWidthimpl(contentDrawScope.mo1483getSizeNHjbRc()), m1079hasSpecifiedAndFiniteHeightuvyYCjk(jMo1548getIntrinsicSizeNHjbRc) ? Size.m1194getHeightimpl(jMo1548getIntrinsicSizeNHjbRc) : Size.m1194getHeightimpl(contentDrawScope.mo1483getSizeNHjbRc()));
        long jM1202getZeroNHjbRc = (Size.m1196getWidthimpl(contentDrawScope.mo1483getSizeNHjbRc()) == 0.0f || Size.m1194getHeightimpl(contentDrawScope.mo1483getSizeNHjbRc()) == 0.0f) ? Size.Companion.m1202getZeroNHjbRc() : ScaleFactorKt.m1782timesUQTWf7w(jSize, this.contentScale.mo1734computeScaleFactorH7hwNQA(jSize, contentDrawScope.mo1483getSizeNHjbRc()));
        long jMo1066alignKFBX0sM = this.alignment.mo1066alignKFBX0sM(IntSizeKt.IntSize(Math.round(Size.m1196getWidthimpl(jM1202getZeroNHjbRc)), Math.round(Size.m1194getHeightimpl(jM1202getZeroNHjbRc))), IntSizeKt.IntSize(Math.round(Size.m1196getWidthimpl(contentDrawScope.mo1483getSizeNHjbRc())), Math.round(Size.m1194getHeightimpl(contentDrawScope.mo1483getSizeNHjbRc()))), contentDrawScope.getLayoutDirection());
        float fM2457getXimpl = IntOffset.m2457getXimpl(jMo1066alignKFBX0sM);
        float fM2458getYimpl = IntOffset.m2458getYimpl(jMo1066alignKFBX0sM);
        contentDrawScope.getDrawContext().getTransform().translate(fM2457getXimpl, fM2458getYimpl);
        try {
            this.painter.m1549drawx_KDEd0(contentDrawScope, jM1202getZeroNHjbRc, this.alpha, this.colorFilter);
            contentDrawScope.getDrawContext().getTransform().translate(-fM2457getXimpl, -fM2458getYimpl);
            contentDrawScope.drawContent();
        } catch (Throwable th) {
            contentDrawScope.getDrawContext().getTransform().translate(-fM2457getXimpl, -fM2458getYimpl);
            throw th;
        }
    }

    /* JADX INFO: renamed from: hasSpecifiedAndFiniteWidth-uvyYCjk, reason: not valid java name */
    private final boolean m1080hasSpecifiedAndFiniteWidthuvyYCjk(long j) {
        if (!Size.m1193equalsimpl0(j, Size.Companion.m1201getUnspecifiedNHjbRc())) {
            float fM1196getWidthimpl = Size.m1196getWidthimpl(j);
            if (!Float.isInfinite(fM1196getWidthimpl) && !Float.isNaN(fM1196getWidthimpl)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: hasSpecifiedAndFiniteHeight-uvyYCjk, reason: not valid java name */
    private final boolean m1079hasSpecifiedAndFiniteHeightuvyYCjk(long j) {
        if (!Size.m1193equalsimpl0(j, Size.Companion.m1201getUnspecifiedNHjbRc())) {
            float fM1194getHeightimpl = Size.m1194getHeightimpl(j);
            if (!Float.isInfinite(fM1194getHeightimpl) && !Float.isNaN(fM1194getHeightimpl)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "PainterModifier(painter=" + this.painter + ", sizeToIntrinsics=" + this.sizeToIntrinsics + ", alignment=" + this.alignment + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
