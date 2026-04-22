package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: LazyLayoutBeyondBoundsState.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyLayoutBeyondBoundsStateKt {
    public static final List calculateLazyLayoutPinnedIndices(LazyLayoutItemProvider lazyLayoutItemProvider, LazyLayoutPinnedItemList lazyLayoutPinnedItemList, LazyLayoutBeyondBoundsInfo lazyLayoutBeyondBoundsInfo) {
        IntRange empty;
        if (!lazyLayoutBeyondBoundsInfo.hasIntervals() && lazyLayoutPinnedItemList.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        if (lazyLayoutBeyondBoundsInfo.hasIntervals()) {
            empty = new IntRange(lazyLayoutBeyondBoundsInfo.getStart(), Math.min(lazyLayoutBeyondBoundsInfo.getEnd(), lazyLayoutItemProvider.getItemCount() - 1));
        } else {
            empty = IntRange.Companion.getEMPTY();
        }
        int size = lazyLayoutPinnedItemList.size();
        for (int i = 0; i < size; i++) {
            LazyLayoutPinnedItemList.PinnedItem pinnedItem = (LazyLayoutPinnedItemList.PinnedItem) lazyLayoutPinnedItemList.get(i);
            int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(lazyLayoutItemProvider, pinnedItem.getKey(), pinnedItem.getIndex());
            int first = empty.getFirst();
            if ((iFindIndexByKey > empty.getLast() || first > iFindIndexByKey) && iFindIndexByKey >= 0 && iFindIndexByKey < lazyLayoutItemProvider.getItemCount()) {
                arrayList.add(Integer.valueOf(iFindIndexByKey));
            }
        }
        int first2 = empty.getFirst();
        int last = empty.getLast();
        if (first2 <= last) {
            while (true) {
                arrayList.add(Integer.valueOf(first2));
                if (first2 == last) {
                    break;
                }
                first2++;
            }
        }
        return arrayList;
    }
}
