package androidx.compose.ui.graphics;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Brush.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SolidColor extends Brush {
    private final long value;

    public /* synthetic */ SolidColor(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }

    private SolidColor(long j) {
        super(null);
        this.value = j;
    }

    /* JADX INFO: renamed from: getValue-0d7_KjU, reason: not valid java name */
    public final long m1410getValue0d7_KjU() {
        return this.value;
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: applyTo-Pq9zytI */
    public void mo1280applyToPq9zytI(long j, Paint paint, float f) {
        long jM1294copywmQWz5c$default;
        paint.setAlpha(1.0f);
        if (f != 1.0f) {
            long j2 = this.value;
            jM1294copywmQWz5c$default = Color.m1294copywmQWz5c$default(j2, Color.m1297getAlphaimpl(j2) * f, 0.0f, 0.0f, 0.0f, 14, null);
        } else {
            jM1294copywmQWz5c$default = this.value;
        }
        paint.mo1228setColor8_81llA(jM1294copywmQWz5c$default);
        if (paint.getShader() != null) {
            paint.setShader(null);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SolidColor) && Color.m1296equalsimpl0(this.value, ((SolidColor) obj).value);
    }

    public int hashCode() {
        return Color.m1302hashCodeimpl(this.value);
    }

    public String toString() {
        return "SolidColor(value=" + ((Object) Color.m1303toStringimpl(this.value)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
