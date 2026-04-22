package androidx.compose.ui.layout;

import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: LayoutModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MeasuringIntrinsics {
    public static final MeasuringIntrinsics INSTANCE = new MeasuringIntrinsics();

    /* JADX INFO: compiled from: LayoutModifier.kt */
    private enum IntrinsicMinMax {
        Min,
        Max
    }

    /* JADX INFO: compiled from: LayoutModifier.kt */
    private enum IntrinsicWidthHeight {
        Width,
        Height
    }

    private MeasuringIntrinsics() {
    }

    public final int minWidth(LayoutModifier layoutModifier, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return layoutModifier.mo251measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), new DefaultIntrinsicMeasurable(intrinsicMeasurable, IntrinsicMinMax.Min, IntrinsicWidthHeight.Width), ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null)).getWidth();
    }

    public final int minHeight(LayoutModifier layoutModifier, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return layoutModifier.mo251measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), new DefaultIntrinsicMeasurable(intrinsicMeasurable, IntrinsicMinMax.Min, IntrinsicWidthHeight.Height), ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null)).getHeight();
    }

    public final int maxWidth(LayoutModifier layoutModifier, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return layoutModifier.mo251measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), new DefaultIntrinsicMeasurable(intrinsicMeasurable, IntrinsicMinMax.Max, IntrinsicWidthHeight.Width), ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null)).getWidth();
    }

    public final int maxHeight(LayoutModifier layoutModifier, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return layoutModifier.mo251measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), new DefaultIntrinsicMeasurable(intrinsicMeasurable, IntrinsicMinMax.Max, IntrinsicWidthHeight.Height), ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null)).getHeight();
    }

    /* JADX INFO: compiled from: LayoutModifier.kt */
    private static final class DefaultIntrinsicMeasurable implements Measurable {
        private final IntrinsicMeasurable measurable;
        private final IntrinsicMinMax minMax;
        private final IntrinsicWidthHeight widthHeight;

        public DefaultIntrinsicMeasurable(IntrinsicMeasurable intrinsicMeasurable, IntrinsicMinMax intrinsicMinMax, IntrinsicWidthHeight intrinsicWidthHeight) {
            this.measurable = intrinsicMeasurable;
            this.minMax = intrinsicMinMax;
            this.widthHeight = intrinsicWidthHeight;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public Object getParentData() {
            return this.measurable.getParentData();
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* JADX INFO: renamed from: measure-BRTryo0 */
        public Placeable mo1743measureBRTryo0(long j) {
            int iMinIntrinsicHeight;
            int iMinIntrinsicWidth;
            if (this.widthHeight == IntrinsicWidthHeight.Width) {
                if (this.minMax == IntrinsicMinMax.Max) {
                    iMinIntrinsicWidth = this.measurable.maxIntrinsicWidth(Constraints.m2388getMaxHeightimpl(j));
                } else {
                    iMinIntrinsicWidth = this.measurable.minIntrinsicWidth(Constraints.m2388getMaxHeightimpl(j));
                }
                return new EmptyPlaceable(iMinIntrinsicWidth, Constraints.m2384getHasBoundedHeightimpl(j) ? Constraints.m2388getMaxHeightimpl(j) : 32767);
            }
            if (this.minMax == IntrinsicMinMax.Max) {
                iMinIntrinsicHeight = this.measurable.maxIntrinsicHeight(Constraints.m2389getMaxWidthimpl(j));
            } else {
                iMinIntrinsicHeight = this.measurable.minIntrinsicHeight(Constraints.m2389getMaxWidthimpl(j));
            }
            return new EmptyPlaceable(Constraints.m2385getHasBoundedWidthimpl(j) ? Constraints.m2389getMaxWidthimpl(j) : 32767, iMinIntrinsicHeight);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicWidth(int i) {
            return this.measurable.minIntrinsicWidth(i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicWidth(int i) {
            return this.measurable.maxIntrinsicWidth(i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicHeight(int i) {
            return this.measurable.minIntrinsicHeight(i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicHeight(int i) {
            return this.measurable.maxIntrinsicHeight(i);
        }
    }

    /* JADX INFO: compiled from: LayoutModifier.kt */
    private static final class EmptyPlaceable extends Placeable {
        @Override // androidx.compose.ui.layout.Measured
        public int get(AlignmentLine alignmentLine) {
            return Integer.MIN_VALUE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.compose.ui.layout.Placeable
        /* JADX INFO: renamed from: placeAt-f8xVGno */
        public void mo1744placeAtf8xVGno(long j, float f, Function1 function1) {
        }

        public EmptyPlaceable(int i, int i2) {
            m1763setMeasuredSizeozmzZPI(IntSizeKt.IntSize(i, i2));
        }
    }
}
