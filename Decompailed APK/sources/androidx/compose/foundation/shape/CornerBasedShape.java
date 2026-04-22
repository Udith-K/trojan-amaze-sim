package androidx.compose.foundation.shape;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: compiled from: CornerBasedShape.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CornerBasedShape implements Shape {
    private final CornerSize bottomEnd;
    private final CornerSize bottomStart;
    private final CornerSize topEnd;
    private final CornerSize topStart;

    public abstract CornerBasedShape copy(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4);

    /* JADX INFO: renamed from: createOutline-LjSzlW0, reason: not valid java name */
    public abstract Outline mo365createOutlineLjSzlW0(long j, float f, float f2, float f3, float f4, LayoutDirection layoutDirection);

    public CornerBasedShape(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
        this.topStart = cornerSize;
        this.topEnd = cornerSize2;
        this.bottomEnd = cornerSize3;
        this.bottomStart = cornerSize4;
    }

    public final CornerSize getTopStart() {
        return this.topStart;
    }

    public final CornerSize getTopEnd() {
        return this.topEnd;
    }

    public final CornerSize getBottomEnd() {
        return this.bottomEnd;
    }

    public final CornerSize getBottomStart() {
        return this.bottomStart;
    }

    @Override // androidx.compose.ui.graphics.Shape
    /* JADX INFO: renamed from: createOutline-Pq9zytI */
    public final Outline mo125createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
        float fMo366toPxTmRCtEA = this.topStart.mo366toPxTmRCtEA(j, density);
        float fMo366toPxTmRCtEA2 = this.topEnd.mo366toPxTmRCtEA(j, density);
        float fMo366toPxTmRCtEA3 = this.bottomEnd.mo366toPxTmRCtEA(j, density);
        float fMo366toPxTmRCtEA4 = this.bottomStart.mo366toPxTmRCtEA(j, density);
        float fM1195getMinDimensionimpl = Size.m1195getMinDimensionimpl(j);
        float f = fMo366toPxTmRCtEA + fMo366toPxTmRCtEA4;
        if (f > fM1195getMinDimensionimpl) {
            float f2 = fM1195getMinDimensionimpl / f;
            fMo366toPxTmRCtEA *= f2;
            fMo366toPxTmRCtEA4 *= f2;
        }
        float f3 = fMo366toPxTmRCtEA4;
        float f4 = fMo366toPxTmRCtEA2 + fMo366toPxTmRCtEA3;
        if (f4 > fM1195getMinDimensionimpl) {
            float f5 = fM1195getMinDimensionimpl / f4;
            fMo366toPxTmRCtEA2 *= f5;
            fMo366toPxTmRCtEA3 *= f5;
        }
        if (fMo366toPxTmRCtEA < 0.0f || fMo366toPxTmRCtEA2 < 0.0f || fMo366toPxTmRCtEA3 < 0.0f || f3 < 0.0f) {
            throw new IllegalArgumentException(("Corner size in Px can't be negative(topStart = " + fMo366toPxTmRCtEA + ", topEnd = " + fMo366toPxTmRCtEA2 + ", bottomEnd = " + fMo366toPxTmRCtEA3 + ", bottomStart = " + f3 + ")!").toString());
        }
        return mo365createOutlineLjSzlW0(j, fMo366toPxTmRCtEA, fMo366toPxTmRCtEA2, fMo366toPxTmRCtEA3, f3, layoutDirection);
    }

    public static /* synthetic */ CornerBasedShape copy$default(CornerBasedShape cornerBasedShape, CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copy");
        }
        if ((i & 1) != 0) {
            cornerSize = cornerBasedShape.topStart;
        }
        if ((i & 2) != 0) {
            cornerSize2 = cornerBasedShape.topEnd;
        }
        if ((i & 4) != 0) {
            cornerSize3 = cornerBasedShape.bottomEnd;
        }
        if ((i & 8) != 0) {
            cornerSize4 = cornerBasedShape.bottomStart;
        }
        return cornerBasedShape.copy(cornerSize, cornerSize2, cornerSize3, cornerSize4);
    }
}
