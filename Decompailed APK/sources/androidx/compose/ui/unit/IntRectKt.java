package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;

/* JADX INFO: compiled from: IntRect.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class IntRectKt {
    /* JADX INFO: renamed from: IntRect-VbeCjmY, reason: not valid java name */
    public static final IntRect m2470IntRectVbeCjmY(long j, long j2) {
        return new IntRect(IntOffset.m2457getXimpl(j), IntOffset.m2458getYimpl(j), IntOffset.m2457getXimpl(j) + IntSize.m2476getWidthimpl(j2), IntOffset.m2458getYimpl(j) + IntSize.m2475getHeightimpl(j2));
    }

    public static final IntRect roundToIntRect(Rect rect) {
        return new IntRect(Math.round(rect.getLeft()), Math.round(rect.getTop()), Math.round(rect.getRight()), Math.round(rect.getBottom()));
    }
}
