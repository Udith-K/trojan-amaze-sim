package androidx.compose.foundation.lazy;

import java.util.List;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: LazyListHeaders.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyListHeadersKt {
    public static final LazyListMeasuredItem findOrComposeLazyListHeader(List list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, List list2, int i, int i2, int i3) {
        int index = ((LazyListMeasuredItem) CollectionsKt.first(list)).getIndex();
        int size = list2.size();
        int iIntValue = -1;
        int iIntValue2 = -1;
        int i4 = 0;
        while (i4 < size && ((Number) list2.get(i4)).intValue() <= index) {
            iIntValue2 = ((Number) list2.get(i4)).intValue();
            i4++;
            iIntValue = ((Number) ((i4 < 0 || i4 > CollectionsKt.getLastIndex(list2)) ? -1 : list2.get(i4))).intValue();
        }
        int size2 = list.size();
        int offset = Integer.MIN_VALUE;
        int offset2 = Integer.MIN_VALUE;
        int i5 = -1;
        for (int i6 = 0; i6 < size2; i6++) {
            LazyListMeasuredItem lazyListMeasuredItem = (LazyListMeasuredItem) list.get(i6);
            if (lazyListMeasuredItem.getIndex() == iIntValue2) {
                offset = lazyListMeasuredItem.getOffset();
                i5 = i6;
            } else if (lazyListMeasuredItem.getIndex() == iIntValue) {
                offset2 = lazyListMeasuredItem.getOffset();
            }
        }
        if (iIntValue2 == -1) {
            return null;
        }
        LazyListMeasuredItem lazyListMeasuredItemM319getAndMeasure0kLqBqw$default = LazyListMeasuredItemProvider.m319getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, iIntValue2, 0L, 2, null);
        lazyListMeasuredItemM319getAndMeasure0kLqBqw$default.setNonScrollableItem(true);
        int iMax = offset != Integer.MIN_VALUE ? Math.max(-i, offset) : -i;
        if (offset2 != Integer.MIN_VALUE) {
            iMax = Math.min(iMax, offset2 - lazyListMeasuredItemM319getAndMeasure0kLqBqw$default.getSize());
        }
        lazyListMeasuredItemM319getAndMeasure0kLqBqw$default.position(iMax, i2, i3);
        if (i5 != -1) {
            list.set(i5, lazyListMeasuredItemM319getAndMeasure0kLqBqw$default);
        } else {
            list.add(0, lazyListMeasuredItemM319getAndMeasure0kLqBqw$default);
        }
        return lazyListMeasuredItemM319getAndMeasure0kLqBqw$default;
    }
}
