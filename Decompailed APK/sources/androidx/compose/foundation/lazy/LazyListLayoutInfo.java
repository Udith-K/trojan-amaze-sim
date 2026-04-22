package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import java.util.List;

/* JADX INFO: compiled from: LazyListLayoutInfo.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LazyListLayoutInfo {
    int getAfterContentPadding();

    int getBeforeContentPadding();

    int getMainAxisItemSpacing();

    Orientation getOrientation();

    int getTotalItemsCount();

    int getViewportEndOffset();

    /* JADX INFO: renamed from: getViewportSize-YbymL2g, reason: not valid java name */
    long mo313getViewportSizeYbymL2g();

    int getViewportStartOffset();

    List getVisibleItemsInfo();
}
