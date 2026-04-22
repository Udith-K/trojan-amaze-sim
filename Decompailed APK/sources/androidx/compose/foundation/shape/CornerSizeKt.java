package androidx.compose.foundation.shape;

import androidx.compose.ui.unit.Density;

/* JADX INFO: compiled from: CornerSize.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CornerSizeKt {
    private static final CornerSize ZeroCornerSize = new CornerSize() { // from class: androidx.compose.foundation.shape.CornerSizeKt$ZeroCornerSize$1
        @Override // androidx.compose.foundation.shape.CornerSize
        /* JADX INFO: renamed from: toPx-TmRCtEA */
        public float mo366toPxTmRCtEA(long j, Density density) {
            return 0.0f;
        }

        public String toString() {
            return "ZeroCornerSize";
        }
    };

    /* JADX INFO: renamed from: CornerSize-0680j_4, reason: not valid java name */
    public static final CornerSize m367CornerSize0680j_4(float f) {
        return new DpCornerSize(f, null);
    }

    public static final CornerSize CornerSize(int i) {
        return new PercentCornerSize(i);
    }
}
