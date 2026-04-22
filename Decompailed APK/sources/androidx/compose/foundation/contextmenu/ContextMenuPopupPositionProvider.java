package androidx.compose.foundation.contextmenu;

import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ContextMenuPopupPositionProvider.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ContextMenuPopupPositionProvider implements PopupPositionProvider {
    private final long localPosition;

    public /* synthetic */ ContextMenuPopupPositionProvider(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }

    private ContextMenuPopupPositionProvider(long j) {
        this.localPosition = j;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* JADX INFO: renamed from: calculatePosition-llwVHH4, reason: not valid java name */
    public long mo151calculatePositionllwVHH4(IntRect intRect, long j, LayoutDirection layoutDirection, long j2) {
        return IntOffsetKt.IntOffset(ContextMenuPopupPositionProvider_androidKt.alignPopupAxis(intRect.getLeft() + IntOffset.m2457getXimpl(this.localPosition), IntSize.m2476getWidthimpl(j2), IntSize.m2476getWidthimpl(j), layoutDirection == LayoutDirection.Ltr), ContextMenuPopupPositionProvider_androidKt.alignPopupAxis$default(intRect.getTop() + IntOffset.m2458getYimpl(this.localPosition), IntSize.m2475getHeightimpl(j2), IntSize.m2475getHeightimpl(j), false, 8, null));
    }
}
