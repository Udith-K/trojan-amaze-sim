package androidx.compose.ui.layout;

import androidx.compose.ui.unit.Constraints;

/* JADX INFO: compiled from: Layout.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DefaultIntrinsicMeasurable implements Measurable {
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
    /* JADX INFO: renamed from: measure-BRTryo0, reason: not valid java name */
    public Placeable mo1743measureBRTryo0(long j) {
        int iMinIntrinsicHeight;
        int iMinIntrinsicWidth;
        if (this.widthHeight == IntrinsicWidthHeight.Width) {
            if (this.minMax == IntrinsicMinMax.Max) {
                iMinIntrinsicWidth = this.measurable.maxIntrinsicWidth(Constraints.m2388getMaxHeightimpl(j));
            } else {
                iMinIntrinsicWidth = this.measurable.minIntrinsicWidth(Constraints.m2388getMaxHeightimpl(j));
            }
            return new FixedSizeIntrinsicsPlaceable(iMinIntrinsicWidth, Constraints.m2384getHasBoundedHeightimpl(j) ? Constraints.m2388getMaxHeightimpl(j) : 32767);
        }
        if (this.minMax == IntrinsicMinMax.Max) {
            iMinIntrinsicHeight = this.measurable.maxIntrinsicHeight(Constraints.m2389getMaxWidthimpl(j));
        } else {
            iMinIntrinsicHeight = this.measurable.minIntrinsicHeight(Constraints.m2389getMaxWidthimpl(j));
        }
        return new FixedSizeIntrinsicsPlaceable(Constraints.m2385getHasBoundedWidthimpl(j) ? Constraints.m2389getMaxWidthimpl(j) : 32767, iMinIntrinsicHeight);
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
