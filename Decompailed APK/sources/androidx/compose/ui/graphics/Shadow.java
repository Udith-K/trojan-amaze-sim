package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Offset;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Shadow.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Shadow {
    public static final Companion Companion = new Companion(null);
    private static final Shadow None = new Shadow(0, 0, 0.0f, 7, null);
    private final float blurRadius;
    private final long color;
    private final long offset;

    public /* synthetic */ Shadow(long j, long j2, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, f);
    }

    private Shadow(long j, long j2, float f) {
        this.color = j;
        this.offset = j2;
        this.blurRadius = f;
    }

    public /* synthetic */ Shadow(long j, long j2, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ColorKt.Color(4278190080L) : j, (i & 2) != 0 ? Offset.Companion.m1171getZeroF1C5BW0() : j2, (i & 4) != 0 ? 0.0f : f, null);
    }

    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name */
    public final long m1400getColor0d7_KjU() {
        return this.color;
    }

    /* JADX INFO: renamed from: getOffset-F1C5BW0, reason: not valid java name */
    public final long m1401getOffsetF1C5BW0() {
        return this.offset;
    }

    public final float getBlurRadius() {
        return this.blurRadius;
    }

    /* JADX INFO: compiled from: Shadow.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Shadow getNone() {
            return Shadow.None;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Shadow)) {
            return false;
        }
        Shadow shadow = (Shadow) obj;
        return Color.m1296equalsimpl0(this.color, shadow.color) && Offset.m1156equalsimpl0(this.offset, shadow.offset) && this.blurRadius == shadow.blurRadius;
    }

    public int hashCode() {
        return (((Color.m1302hashCodeimpl(this.color) * 31) + Offset.m1161hashCodeimpl(this.offset)) * 31) + Float.floatToIntBits(this.blurRadius);
    }

    public String toString() {
        return "Shadow(color=" + ((Object) Color.m1303toStringimpl(this.color)) + ", offset=" + ((Object) Offset.m1166toStringimpl(this.offset)) + ", blurRadius=" + this.blurRadius + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
