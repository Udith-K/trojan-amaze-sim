package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.LazyListPrefetchStrategy;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import androidx.compose.foundation.lazy.layout.PrefetchScheduler;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: LazyListPrefetchStrategy.kt */
/* JADX INFO: loaded from: classes.dex */
final class DefaultLazyListPrefetchStrategy implements LazyListPrefetchStrategy {
    private LazyLayoutPrefetchState.PrefetchHandle currentPrefetchHandle;
    private int indexToPrefetch = -1;
    private final int nestedPrefetchItemCount;
    private boolean wasScrollingForward;

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public /* synthetic */ PrefetchScheduler getPrefetchScheduler() {
        return LazyListPrefetchStrategy.CC.$default$getPrefetchScheduler(this);
    }

    public DefaultLazyListPrefetchStrategy(int i) {
        this.nestedPrefetchItemCount = i;
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onScroll(LazyListPrefetchScope lazyListPrefetchScope, float f, LazyListLayoutInfo lazyListLayoutInfo) {
        int index;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle2;
        LazyLayoutPrefetchState.PrefetchHandle prefetchHandle3;
        if (lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            return;
        }
        boolean z = f < 0.0f;
        if (z) {
            index = ((LazyListItemInfo) CollectionsKt.last(lazyListLayoutInfo.getVisibleItemsInfo())).getIndex() + 1;
        } else {
            index = ((LazyListItemInfo) CollectionsKt.first(lazyListLayoutInfo.getVisibleItemsInfo())).getIndex() - 1;
        }
        if (index < 0 || index >= lazyListLayoutInfo.getTotalItemsCount()) {
            return;
        }
        if (index != this.indexToPrefetch) {
            if (this.wasScrollingForward != z && (prefetchHandle3 = this.currentPrefetchHandle) != null) {
                prefetchHandle3.cancel();
            }
            this.wasScrollingForward = z;
            this.indexToPrefetch = index;
            this.currentPrefetchHandle = lazyListPrefetchScope.schedulePrefetch(index);
        }
        if (!z) {
            if (lazyListLayoutInfo.getViewportStartOffset() - ((LazyListItemInfo) CollectionsKt.first(lazyListLayoutInfo.getVisibleItemsInfo())).getOffset() >= f || (prefetchHandle = this.currentPrefetchHandle) == null) {
                return;
            }
            prefetchHandle.markAsUrgent();
            return;
        }
        LazyListItemInfo lazyListItemInfo = (LazyListItemInfo) CollectionsKt.last(lazyListLayoutInfo.getVisibleItemsInfo());
        if (((lazyListItemInfo.getOffset() + lazyListItemInfo.getSize()) + lazyListLayoutInfo.getMainAxisItemSpacing()) - lazyListLayoutInfo.getViewportEndOffset() >= (-f) || (prefetchHandle2 = this.currentPrefetchHandle) == null) {
            return;
        }
        prefetchHandle2.markAsUrgent();
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onVisibleItemsUpdated(LazyListPrefetchScope lazyListPrefetchScope, LazyListLayoutInfo lazyListLayoutInfo) {
        int index;
        if (this.indexToPrefetch == -1 || lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            return;
        }
        if (this.wasScrollingForward) {
            index = ((LazyListItemInfo) CollectionsKt.last(lazyListLayoutInfo.getVisibleItemsInfo())).getIndex() + 1;
        } else {
            index = ((LazyListItemInfo) CollectionsKt.first(lazyListLayoutInfo.getVisibleItemsInfo())).getIndex() - 1;
        }
        if (this.indexToPrefetch != index) {
            this.indexToPrefetch = -1;
            LazyLayoutPrefetchState.PrefetchHandle prefetchHandle = this.currentPrefetchHandle;
            if (prefetchHandle != null) {
                prefetchHandle.cancel();
            }
            this.currentPrefetchHandle = null;
        }
    }

    @Override // androidx.compose.foundation.lazy.LazyListPrefetchStrategy
    public void onNestedPrefetch(NestedPrefetchScope nestedPrefetchScope, int i) {
        int i2 = this.nestedPrefetchItemCount;
        for (int i3 = 0; i3 < i2; i3++) {
            nestedPrefetchScope.schedulePrefetch(i + i3);
        }
    }
}
