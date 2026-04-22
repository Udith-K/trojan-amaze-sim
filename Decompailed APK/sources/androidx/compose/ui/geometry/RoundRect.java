package androidx.compose.ui.geometry;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RoundRect.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RoundRect {
    public static final Companion Companion = new Companion(null);
    private static final RoundRect Zero = RoundRectKt.m1189RoundRectgG7oq9Y(0.0f, 0.0f, 0.0f, 0.0f, CornerRadius.Companion.m1149getZerokKHJgLs());
    private final float bottom;
    private final long bottomLeftCornerRadius;
    private final long bottomRightCornerRadius;
    private final float left;
    private final float right;
    private final float top;
    private final long topLeftCornerRadius;
    private final long topRightCornerRadius;

    public /* synthetic */ RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, j, j2, j3, j4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundRect)) {
            return false;
        }
        RoundRect roundRect = (RoundRect) obj;
        return Float.compare(this.left, roundRect.left) == 0 && Float.compare(this.top, roundRect.top) == 0 && Float.compare(this.right, roundRect.right) == 0 && Float.compare(this.bottom, roundRect.bottom) == 0 && CornerRadius.m1144equalsimpl0(this.topLeftCornerRadius, roundRect.topLeftCornerRadius) && CornerRadius.m1144equalsimpl0(this.topRightCornerRadius, roundRect.topRightCornerRadius) && CornerRadius.m1144equalsimpl0(this.bottomRightCornerRadius, roundRect.bottomRightCornerRadius) && CornerRadius.m1144equalsimpl0(this.bottomLeftCornerRadius, roundRect.bottomLeftCornerRadius);
    }

    public int hashCode() {
        return (((((((((((((Float.floatToIntBits(this.left) * 31) + Float.floatToIntBits(this.top)) * 31) + Float.floatToIntBits(this.right)) * 31) + Float.floatToIntBits(this.bottom)) * 31) + CornerRadius.m1147hashCodeimpl(this.topLeftCornerRadius)) * 31) + CornerRadius.m1147hashCodeimpl(this.topRightCornerRadius)) * 31) + CornerRadius.m1147hashCodeimpl(this.bottomRightCornerRadius)) * 31) + CornerRadius.m1147hashCodeimpl(this.bottomLeftCornerRadius);
    }

    private RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
        this.topLeftCornerRadius = j;
        this.topRightCornerRadius = j2;
        this.bottomRightCornerRadius = j3;
        this.bottomLeftCornerRadius = j4;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getTop() {
        return this.top;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: getTopLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m1186getTopLeftCornerRadiuskKHJgLs() {
        return this.topLeftCornerRadius;
    }

    /* JADX INFO: renamed from: getTopRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m1187getTopRightCornerRadiuskKHJgLs() {
        return this.topRightCornerRadius;
    }

    /* JADX INFO: renamed from: getBottomRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m1185getBottomRightCornerRadiuskKHJgLs() {
        return this.bottomRightCornerRadius;
    }

    /* JADX INFO: renamed from: getBottomLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m1184getBottomLeftCornerRadiuskKHJgLs() {
        return this.bottomLeftCornerRadius;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    public String toString() {
        long j = this.topLeftCornerRadius;
        long j2 = this.topRightCornerRadius;
        long j3 = this.bottomRightCornerRadius;
        long j4 = this.bottomLeftCornerRadius;
        String str = GeometryUtilsKt.toStringAsFixed(this.left, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.top, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.right, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom, 1);
        if (!CornerRadius.m1144equalsimpl0(j, j2) || !CornerRadius.m1144equalsimpl0(j2, j3) || !CornerRadius.m1144equalsimpl0(j3, j4)) {
            return "RoundRect(rect=" + str + ", topLeft=" + ((Object) CornerRadius.m1148toStringimpl(j)) + ", topRight=" + ((Object) CornerRadius.m1148toStringimpl(j2)) + ", bottomRight=" + ((Object) CornerRadius.m1148toStringimpl(j3)) + ", bottomLeft=" + ((Object) CornerRadius.m1148toStringimpl(j4)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
        if (CornerRadius.m1145getXimpl(j) == CornerRadius.m1146getYimpl(j)) {
            return "RoundRect(rect=" + str + ", radius=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m1145getXimpl(j), 1) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
        return "RoundRect(rect=" + str + ", x=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m1145getXimpl(j), 1) + ", y=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m1146getYimpl(j), 1) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: RoundRect.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
