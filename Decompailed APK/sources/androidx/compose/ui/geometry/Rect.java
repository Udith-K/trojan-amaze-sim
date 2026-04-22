package androidx.compose.ui.geometry;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Rect.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Rect {
    public static final Companion Companion = new Companion(null);
    private static final Rect Zero = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
    private final float bottom;
    private final float left;
    private final float right;
    private final float top;

    public static /* synthetic */ Rect copy$default(Rect rect, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = rect.left;
        }
        if ((i & 2) != 0) {
            f2 = rect.top;
        }
        if ((i & 4) != 0) {
            f3 = rect.right;
        }
        if ((i & 8) != 0) {
            f4 = rect.bottom;
        }
        return rect.copy(f, f2, f3, f4);
    }

    public final Rect copy(float f, float f2, float f3, float f4) {
        return new Rect(f, f2, f3, f4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rect)) {
            return false;
        }
        Rect rect = (Rect) obj;
        return Float.compare(this.left, rect.left) == 0 && Float.compare(this.top, rect.top) == 0 && Float.compare(this.right, rect.right) == 0 && Float.compare(this.bottom, rect.bottom) == 0;
    }

    public int hashCode() {
        return (((((Float.floatToIntBits(this.left) * 31) + Float.floatToIntBits(this.top)) * 31) + Float.floatToIntBits(this.right)) * 31) + Float.floatToIntBits(this.bottom);
    }

    public Rect(float f, float f2, float f3, float f4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
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

    /* JADX INFO: compiled from: Rect.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Rect getZero() {
            return Rect.Zero;
        }
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    public final long m1179getSizeNHjbRc() {
        return SizeKt.Size(getWidth(), getHeight());
    }

    public final boolean isEmpty() {
        return this.left >= this.right || this.top >= this.bottom;
    }

    /* JADX INFO: renamed from: translate-k-4lQ0M, reason: not valid java name */
    public final Rect m1181translatek4lQ0M(long j) {
        return new Rect(this.left + Offset.m1159getXimpl(j), this.top + Offset.m1160getYimpl(j), this.right + Offset.m1159getXimpl(j), this.bottom + Offset.m1160getYimpl(j));
    }

    public final Rect translate(float f, float f2) {
        return new Rect(this.left + f, this.top + f2, this.right + f, this.bottom + f2);
    }

    public final Rect intersect(Rect rect) {
        return new Rect(Math.max(this.left, rect.left), Math.max(this.top, rect.top), Math.min(this.right, rect.right), Math.min(this.bottom, rect.bottom));
    }

    public final Rect intersect(float f, float f2, float f3, float f4) {
        return new Rect(Math.max(this.left, f), Math.max(this.top, f2), Math.min(this.right, f3), Math.min(this.bottom, f4));
    }

    public final boolean overlaps(Rect rect) {
        return this.right > rect.left && rect.right > this.left && this.bottom > rect.top && rect.bottom > this.top;
    }

    /* JADX INFO: renamed from: getTopLeft-F1C5BW0, reason: not valid java name */
    public final long m1180getTopLeftF1C5BW0() {
        return OffsetKt.Offset(this.left, this.top);
    }

    /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
    public final long m1178getCenterF1C5BW0() {
        return OffsetKt.Offset(this.left + (getWidth() / 2.0f), this.top + (getHeight() / 2.0f));
    }

    /* JADX INFO: renamed from: getBottomRight-F1C5BW0, reason: not valid java name */
    public final long m1177getBottomRightF1C5BW0() {
        return OffsetKt.Offset(this.right, this.bottom);
    }

    /* JADX INFO: renamed from: contains-k-4lQ0M, reason: not valid java name */
    public final boolean m1176containsk4lQ0M(long j) {
        return Offset.m1159getXimpl(j) >= this.left && Offset.m1159getXimpl(j) < this.right && Offset.m1160getYimpl(j) >= this.top && Offset.m1160getYimpl(j) < this.bottom;
    }

    public String toString() {
        return "Rect.fromLTRB(" + GeometryUtilsKt.toStringAsFixed(this.left, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.top, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.right, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom, 1) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
