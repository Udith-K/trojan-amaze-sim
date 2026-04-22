package androidx.compose.ui.layout;

import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
public interface MeasurePolicy {
    int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i);

    int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i);

    /* JADX INFO: renamed from: measure-3p2s80s */
    MeasureResult mo21measure3p2s80s(MeasureScope measureScope, List list, long j);

    int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i);

    int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i);

    /* JADX INFO: renamed from: androidx.compose.ui.layout.MeasurePolicy$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: MeasurePolicy.kt */
    public abstract /* synthetic */ class CC {
        public static int $default$minIntrinsicWidth(MeasurePolicy measurePolicy, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list.get(i2), IntrinsicMinMax.Min, IntrinsicWidthHeight.Width));
            }
            return measurePolicy.mo21measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null)).getWidth();
        }

        public static int $default$minIntrinsicHeight(MeasurePolicy measurePolicy, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list.get(i2), IntrinsicMinMax.Min, IntrinsicWidthHeight.Height));
            }
            return measurePolicy.mo21measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null)).getHeight();
        }

        public static int $default$maxIntrinsicWidth(MeasurePolicy measurePolicy, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list.get(i2), IntrinsicMinMax.Max, IntrinsicWidthHeight.Width));
            }
            return measurePolicy.mo21measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null)).getWidth();
        }

        public static int $default$maxIntrinsicHeight(MeasurePolicy measurePolicy, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list.get(i2), IntrinsicMinMax.Max, IntrinsicWidthHeight.Height));
            }
            return measurePolicy.mo21measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null)).getHeight();
        }
    }
}
