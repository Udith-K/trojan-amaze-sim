package androidx.compose.ui.layout;

import androidx.compose.ui.unit.IntSizeKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Layout.kt */
/* JADX INFO: loaded from: classes.dex */
final class FixedSizeIntrinsicsPlaceable extends Placeable {
    @Override // androidx.compose.ui.layout.Measured
    public int get(AlignmentLine alignmentLine) {
        return Integer.MIN_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno, reason: not valid java name */
    public void mo1744placeAtf8xVGno(long j, float f, Function1 function1) {
    }

    public FixedSizeIntrinsicsPlaceable(int i, int i2) {
        m1763setMeasuredSizeozmzZPI(IntSizeKt.IntSize(i, i2));
    }
}
