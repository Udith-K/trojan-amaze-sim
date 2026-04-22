package androidx.compose.foundation;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.unit.Dp;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BorderStroke.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BorderStroke {
    private final Brush brush;
    private final float width;

    public /* synthetic */ BorderStroke(float f, Brush brush, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, brush);
    }

    private BorderStroke(float f, Brush brush) {
        this.width = f;
        this.brush = brush;
    }

    public final Brush getBrush() {
        return this.brush;
    }

    /* JADX INFO: renamed from: getWidth-D9Ej5fM, reason: not valid java name */
    public final float m110getWidthD9Ej5fM() {
        return this.width;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BorderStroke)) {
            return false;
        }
        BorderStroke borderStroke = (BorderStroke) obj;
        return Dp.m2418equalsimpl0(this.width, borderStroke.width) && Intrinsics.areEqual(this.brush, borderStroke.brush);
    }

    public int hashCode() {
        return (Dp.m2419hashCodeimpl(this.width) * 31) + this.brush.hashCode();
    }

    public String toString() {
        return "BorderStroke(width=" + ((Object) Dp.m2420toStringimpl(this.width)) + ", brush=" + this.brush + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
