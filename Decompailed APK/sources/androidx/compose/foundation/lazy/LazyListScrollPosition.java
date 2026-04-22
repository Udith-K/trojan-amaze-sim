package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemProviderKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutNearestRangeState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: LazyListScrollPosition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyListScrollPosition {
    private boolean hadFirstNotEmptyLayout;
    private final MutableIntState index$delegate;
    private Object lastKnownFirstItemKey;
    private final LazyLayoutNearestRangeState nearestRangeState;
    private final MutableIntState scrollOffset$delegate;

    public LazyListScrollPosition(int i, int i2) {
        this.index$delegate = SnapshotIntStateKt.mutableIntStateOf(i);
        this.scrollOffset$delegate = SnapshotIntStateKt.mutableIntStateOf(i2);
        this.nearestRangeState = new LazyLayoutNearestRangeState(i, 30, 100);
    }

    public final int getIndex() {
        return this.index$delegate.getIntValue();
    }

    public final void setIndex(int i) {
        this.index$delegate.setIntValue(i);
    }

    private final void setScrollOffset(int i) {
        this.scrollOffset$delegate.setIntValue(i);
    }

    public final int getScrollOffset() {
        return this.scrollOffset$delegate.getIntValue();
    }

    public final LazyLayoutNearestRangeState getNearestRangeState() {
        return this.nearestRangeState;
    }

    public final void updateFromMeasureResult(LazyListMeasureResult lazyListMeasureResult) {
        LazyListMeasuredItem firstVisibleItem = lazyListMeasureResult.getFirstVisibleItem();
        this.lastKnownFirstItemKey = firstVisibleItem != null ? firstVisibleItem.getKey() : null;
        if (this.hadFirstNotEmptyLayout || lazyListMeasureResult.getTotalItemsCount() > 0) {
            this.hadFirstNotEmptyLayout = true;
            int firstVisibleItemScrollOffset = lazyListMeasureResult.getFirstVisibleItemScrollOffset();
            if (firstVisibleItemScrollOffset < 0.0f) {
                throw new IllegalStateException(("scrollOffset should be non-negative (" + firstVisibleItemScrollOffset + CoreConstants.RIGHT_PARENTHESIS_CHAR).toString());
            }
            LazyListMeasuredItem firstVisibleItem2 = lazyListMeasureResult.getFirstVisibleItem();
            update(firstVisibleItem2 != null ? firstVisibleItem2.getIndex() : 0, firstVisibleItemScrollOffset);
        }
    }

    public final void updateScrollOffset(int i) {
        if (i < 0.0f) {
            throw new IllegalStateException(("scrollOffset should be non-negative (" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR).toString());
        }
        setScrollOffset(i);
    }

    public final void requestPositionAndForgetLastKnownKey(int i, int i2) {
        update(i, i2);
        this.lastKnownFirstItemKey = null;
    }

    public final int updateScrollPositionIfTheFirstItemWasMoved(LazyListItemProvider lazyListItemProvider, int i) {
        int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(lazyListItemProvider, this.lastKnownFirstItemKey, i);
        if (i != iFindIndexByKey) {
            setIndex(iFindIndexByKey);
            this.nearestRangeState.update(i);
        }
        return iFindIndexByKey;
    }

    private final void update(int i, int i2) {
        if (i < 0.0f) {
            throw new IllegalArgumentException(("Index should be non-negative (" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR).toString());
        }
        setIndex(i);
        this.nearestRangeState.update(i);
        setScrollOffset(i2);
    }
}
