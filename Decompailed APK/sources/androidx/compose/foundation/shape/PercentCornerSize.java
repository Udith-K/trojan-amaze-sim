package androidx.compose.foundation.shape;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Density;

/* JADX INFO: compiled from: CornerSize.kt */
/* JADX INFO: loaded from: classes.dex */
final class PercentCornerSize implements CornerSize {
    private final float percent;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PercentCornerSize) && Float.compare(this.percent, ((PercentCornerSize) obj).percent) == 0;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.percent);
    }

    public PercentCornerSize(float f) {
        this.percent = f;
        if (f < 0.0f || f > 100.0f) {
            throw new IllegalArgumentException("The percent should be in the range of [0, 100]");
        }
    }

    @Override // androidx.compose.foundation.shape.CornerSize
    /* JADX INFO: renamed from: toPx-TmRCtEA */
    public float mo366toPxTmRCtEA(long j, Density density) {
        return Size.m1195getMinDimensionimpl(j) * (this.percent / 100.0f);
    }

    public String toString() {
        return "CornerSize(size = " + this.percent + "%)";
    }
}
