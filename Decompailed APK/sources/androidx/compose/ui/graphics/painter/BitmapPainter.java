package androidx.compose.ui.graphics.painter;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BitmapPainter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BitmapPainter extends Painter {
    private float alpha;
    private ColorFilter colorFilter;
    private int filterQuality;
    private final ImageBitmap image;
    private final long size;
    private final long srcOffset;
    private final long srcSize;

    public /* synthetic */ BitmapPainter(ImageBitmap imageBitmap, long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(imageBitmap, j, j2);
    }

    public /* synthetic */ BitmapPainter(ImageBitmap imageBitmap, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(imageBitmap, (i & 2) != 0 ? IntOffset.Companion.m2464getZeronOccac() : j, (i & 4) != 0 ? IntSizeKt.IntSize(imageBitmap.getWidth(), imageBitmap.getHeight()) : j2, null);
    }

    private BitmapPainter(ImageBitmap imageBitmap, long j, long j2) {
        this.image = imageBitmap;
        this.srcOffset = j;
        this.srcSize = j2;
        this.filterQuality = FilterQuality.Companion.m1329getLowfv9h1I();
        this.size = m1547validateSizeN5eqBDc(j, j2);
        this.alpha = 1.0f;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected void onDraw(DrawScope drawScope) {
        DrawScope.CC.m1500drawImageAZ2fEMs$default(drawScope, this.image, this.srcOffset, this.srcSize, 0L, IntSizeKt.IntSize(Math.round(Size.m1196getWidthimpl(drawScope.mo1483getSizeNHjbRc())), Math.round(Size.m1194getHeightimpl(drawScope.mo1483getSizeNHjbRc()))), this.alpha, null, this.colorFilter, 0, this.filterQuality, 328, null);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc, reason: not valid java name */
    public long mo1548getIntrinsicSizeNHjbRc() {
        return IntSizeKt.m2483toSizeozmzZPI(this.size);
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

    /* JADX INFO: renamed from: validateSize-N5eqBDc, reason: not valid java name */
    private final long m1547validateSizeN5eqBDc(long j, long j2) {
        if (IntOffset.m2457getXimpl(j) < 0 || IntOffset.m2458getYimpl(j) < 0 || IntSize.m2476getWidthimpl(j2) < 0 || IntSize.m2475getHeightimpl(j2) < 0 || IntSize.m2476getWidthimpl(j2) > this.image.getWidth() || IntSize.m2475getHeightimpl(j2) > this.image.getHeight()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        return j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BitmapPainter)) {
            return false;
        }
        BitmapPainter bitmapPainter = (BitmapPainter) obj;
        return Intrinsics.areEqual(this.image, bitmapPainter.image) && IntOffset.m2456equalsimpl0(this.srcOffset, bitmapPainter.srcOffset) && IntSize.m2474equalsimpl0(this.srcSize, bitmapPainter.srcSize) && FilterQuality.m1326equalsimpl0(this.filterQuality, bitmapPainter.filterQuality);
    }

    public int hashCode() {
        return (((((this.image.hashCode() * 31) + IntOffset.m2459hashCodeimpl(this.srcOffset)) * 31) + IntSize.m2477hashCodeimpl(this.srcSize)) * 31) + FilterQuality.m1327hashCodeimpl(this.filterQuality);
    }

    public String toString() {
        return "BitmapPainter(image=" + this.image + ", srcOffset=" + ((Object) IntOffset.m2462toStringimpl(this.srcOffset)) + ", srcSize=" + ((Object) IntSize.m2478toStringimpl(this.srcSize)) + ", filterQuality=" + ((Object) FilterQuality.m1328toStringimpl(this.filterQuality)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
