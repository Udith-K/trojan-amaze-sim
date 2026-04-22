package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.pointer.PointerInputChange;

/* JADX INFO: compiled from: DragGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
final class TouchSlopDetector {
    private final Orientation orientation;
    private long totalPositionChange = Offset.Companion.m1171getZeroF1C5BW0();

    public TouchSlopDetector(Orientation orientation) {
        this.orientation = orientation;
    }

    /* JADX INFO: renamed from: mainAxis-k-4lQ0M, reason: not valid java name */
    public final float m243mainAxisk4lQ0M(long j) {
        return this.orientation == Orientation.Horizontal ? Offset.m1159getXimpl(j) : Offset.m1160getYimpl(j);
    }

    /* JADX INFO: renamed from: crossAxis-k-4lQ0M, reason: not valid java name */
    public final float m242crossAxisk4lQ0M(long j) {
        return this.orientation == Orientation.Horizontal ? Offset.m1160getYimpl(j) : Offset.m1159getXimpl(j);
    }

    /* JADX INFO: renamed from: addPointerInputChange-GcwITfU, reason: not valid java name */
    public final Offset m241addPointerInputChangeGcwITfU(PointerInputChange pointerInputChange, float f) {
        float fAbs;
        long jM1164plusMKHz9U = Offset.m1164plusMKHz9U(this.totalPositionChange, Offset.m1163minusMKHz9U(pointerInputChange.m1682getPositionF1C5BW0(), pointerInputChange.m1683getPreviousPositionF1C5BW0()));
        this.totalPositionChange = jM1164plusMKHz9U;
        if (this.orientation == null) {
            fAbs = Offset.m1157getDistanceimpl(jM1164plusMKHz9U);
        } else {
            fAbs = Math.abs(m243mainAxisk4lQ0M(jM1164plusMKHz9U));
        }
        if (fAbs >= f) {
            return Offset.m1150boximpl(m240calculatePostSlopOffsettuRUvjQ(f));
        }
        return null;
    }

    public final void reset() {
        this.totalPositionChange = Offset.Companion.m1171getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: calculatePostSlopOffset-tuRUvjQ, reason: not valid java name */
    private final long m240calculatePostSlopOffsettuRUvjQ(float f) {
        if (this.orientation == null) {
            long j = this.totalPositionChange;
            return Offset.m1163minusMKHz9U(this.totalPositionChange, Offset.m1165timestuRUvjQ(Offset.m1154divtuRUvjQ(j, Offset.m1157getDistanceimpl(j)), f));
        }
        float fM243mainAxisk4lQ0M = m243mainAxisk4lQ0M(this.totalPositionChange) - (Math.signum(m243mainAxisk4lQ0M(this.totalPositionChange)) * f);
        float fM242crossAxisk4lQ0M = m242crossAxisk4lQ0M(this.totalPositionChange);
        if (this.orientation == Orientation.Horizontal) {
            return OffsetKt.Offset(fM243mainAxisk4lQ0M, fM242crossAxisk4lQ0M);
        }
        return OffsetKt.Offset(fM242crossAxisk4lQ0M, fM243mainAxisk4lQ0M);
    }
}
