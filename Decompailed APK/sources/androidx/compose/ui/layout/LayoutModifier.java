package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;

/* JADX INFO: compiled from: LayoutModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LayoutModifier extends Modifier.Element {
    int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    /* JADX INFO: renamed from: measure-3p2s80s */
    MeasureResult mo251measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j);

    int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    /* JADX INFO: renamed from: androidx.compose.ui.layout.LayoutModifier$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LayoutModifier.kt */
    public abstract /* synthetic */ class CC {
    }
}
