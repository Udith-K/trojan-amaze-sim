package androidx.compose.foundation.shape;

import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundedCornerShape.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RoundedCornerShape extends CornerBasedShape {
    public RoundedCornerShape(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
        super(cornerSize, cornerSize2, cornerSize3, cornerSize4);
    }

    @Override // androidx.compose.foundation.shape.CornerBasedShape
    /* JADX INFO: renamed from: createOutline-LjSzlW0 */
    public Outline mo365createOutlineLjSzlW0(long j, float f, float f2, float f3, float f4, LayoutDirection layoutDirection) {
        if (f + f2 + f3 + f4 == 0.0f) {
            return new Outline.Rectangle(SizeKt.m1204toRectuvyYCjk(j));
        }
        Rect rectM1204toRectuvyYCjk = SizeKt.m1204toRectuvyYCjk(j);
        LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
        return new Outline.Rounded(RoundRectKt.m1188RoundRectZAM2FJo(rectM1204toRectuvyYCjk, CornerRadiusKt.CornerRadius$default(layoutDirection == layoutDirection2 ? f : f2, 0.0f, 2, null), CornerRadiusKt.CornerRadius$default(layoutDirection == layoutDirection2 ? f2 : f, 0.0f, 2, null), CornerRadiusKt.CornerRadius$default(layoutDirection == layoutDirection2 ? f3 : f4, 0.0f, 2, null), CornerRadiusKt.CornerRadius$default(layoutDirection == layoutDirection2 ? f4 : f3, 0.0f, 2, null)));
    }

    @Override // androidx.compose.foundation.shape.CornerBasedShape
    public RoundedCornerShape copy(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
        return new RoundedCornerShape(cornerSize, cornerSize2, cornerSize3, cornerSize4);
    }

    public String toString() {
        return "RoundedCornerShape(topStart = " + getTopStart() + ", topEnd = " + getTopEnd() + ", bottomEnd = " + getBottomEnd() + ", bottomStart = " + getBottomStart() + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundedCornerShape)) {
            return false;
        }
        RoundedCornerShape roundedCornerShape = (RoundedCornerShape) obj;
        return Intrinsics.areEqual(getTopStart(), roundedCornerShape.getTopStart()) && Intrinsics.areEqual(getTopEnd(), roundedCornerShape.getTopEnd()) && Intrinsics.areEqual(getBottomEnd(), roundedCornerShape.getBottomEnd()) && Intrinsics.areEqual(getBottomStart(), roundedCornerShape.getBottomStart());
    }

    public int hashCode() {
        return (((((getTopStart().hashCode() * 31) + getTopEnd().hashCode()) * 31) + getBottomEnd().hashCode()) * 31) + getBottomStart().hashCode();
    }
}
