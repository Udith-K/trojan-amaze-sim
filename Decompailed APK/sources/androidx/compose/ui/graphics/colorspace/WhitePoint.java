package androidx.compose.ui.graphics.colorspace;

import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: WhitePoint.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WhitePoint {
    private final float x;
    private final float y;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WhitePoint)) {
            return false;
        }
        WhitePoint whitePoint = (WhitePoint) obj;
        return Float.compare(this.x, whitePoint.x) == 0 && Float.compare(this.y, whitePoint.y) == 0;
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.x) * 31) + Float.floatToIntBits(this.y);
    }

    public String toString() {
        return "WhitePoint(x=" + this.x + ", y=" + this.y + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public WhitePoint(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public WhitePoint(float f, float f2, float f3) {
        this(f, f2, f3, f + f2 + f3);
    }

    private WhitePoint(float f, float f2, float f3, float f4) {
        this(f / f4, f2 / f4);
    }

    public final float[] toXyz$ui_graphics_release() {
        float f = this.x;
        float f2 = this.y;
        return new float[]{f / f2, 1.0f, ((1.0f - f) - f2) / f2};
    }
}
