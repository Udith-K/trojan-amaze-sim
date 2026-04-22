package androidx.compose.ui.graphics.painter;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ColorPainter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ColorPainter extends Painter {
    private float alpha;
    private final long color;
    private ColorFilter colorFilter;
    private final long intrinsicSize;

    public /* synthetic */ ColorPainter(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }

    private ColorPainter(long j) {
        this.color = j;
        this.alpha = 1.0f;
        this.intrinsicSize = Size.Companion.m1201getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected void onDraw(DrawScope drawScope) {
        DrawScope.CC.m1506drawRectnJ9OG0$default(drawScope, this.color, 0L, 0L, this.alpha, null, this.colorFilter, 0, 86, null);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyAlpha(float f) {
        this.alpha = f;
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ColorPainter) && Color.m1296equalsimpl0(this.color, ((ColorPainter) obj).color);
    }

    public int hashCode() {
        return Color.m1302hashCodeimpl(this.color);
    }

    public String toString() {
        return "ColorPainter(color=" + ((Object) Color.m1303toStringimpl(this.color)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long mo1548getIntrinsicSizeNHjbRc() {
        return this.intrinsicSize;
    }
}
