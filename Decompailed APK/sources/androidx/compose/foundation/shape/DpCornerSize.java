package androidx.compose.foundation.shape;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CornerSize.kt */
/* JADX INFO: loaded from: classes.dex */
final class DpCornerSize implements CornerSize {
    private final float size;

    public /* synthetic */ DpCornerSize(float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DpCornerSize) && Dp.m2418equalsimpl0(this.size, ((DpCornerSize) obj).size);
    }

    public int hashCode() {
        return Dp.m2419hashCodeimpl(this.size);
    }

    private DpCornerSize(float f) {
        this.size = f;
    }

    @Override // androidx.compose.foundation.shape.CornerSize
    /* JADX INFO: renamed from: toPx-TmRCtEA */
    public float mo366toPxTmRCtEA(long j, Density density) {
        return density.mo210toPx0680j_4(this.size);
    }

    public String toString() {
        return "CornerSize(size = " + this.size + ".dp)";
    }
}
