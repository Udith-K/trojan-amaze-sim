package androidx.compose.foundation.lazy;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyListMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyListMeasureKt {
    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0411  */
    /* JADX INFO: renamed from: measureLazyList-x0Ok8Vo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.foundation.lazy.LazyListMeasureResult m314measureLazyListx0Ok8Vo(int r40, androidx.compose.foundation.lazy.LazyListMeasuredItemProvider r41, int r42, int r43, int r44, int r45, int r46, int r47, float r48, long r49, boolean r51, java.util.List r52, androidx.compose.foundation.layout.Arrangement.Vertical r53, androidx.compose.foundation.layout.Arrangement.Horizontal r54, boolean r55, androidx.compose.ui.unit.Density r56, androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator r57, int r58, java.util.List r59, boolean r60, boolean r61, androidx.compose.foundation.lazy.LazyListLayoutInfo r62, kotlinx.coroutines.CoroutineScope r63, final androidx.compose.runtime.MutableState r64, androidx.compose.ui.graphics.GraphicsContext r65, kotlin.jvm.functions.Function3 r66) {
        /*
            Method dump skipped, instruction units count: 1107
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListMeasureKt.m314measureLazyListx0Ok8Vo(int, androidx.compose.foundation.lazy.LazyListMeasuredItemProvider, int, int, int, int, int, int, float, long, boolean, java.util.List, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, boolean, androidx.compose.ui.unit.Density, androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator, int, java.util.List, boolean, boolean, androidx.compose.foundation.lazy.LazyListLayoutInfo, kotlinx.coroutines.CoroutineScope, androidx.compose.runtime.MutableState, androidx.compose.ui.graphics.GraphicsContext, kotlin.jvm.functions.Function3):androidx.compose.foundation.lazy.LazyListMeasureResult");
    }

    private static final List createItemsAfterList(List list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i, int i2, List list2, float f, boolean z, LazyListLayoutInfo lazyListLayoutInfo) {
        ArrayList arrayList;
        LazyListItemInfo lazyListItemInfo;
        LazyListMeasuredItem lazyListMeasuredItem;
        Object obj;
        int mainAxisSizeWithSpacings;
        Object obj2;
        int index;
        int iMin;
        LazyListMeasuredItem lazyListMeasuredItem2;
        Object obj3;
        int i3 = i - 1;
        int iMin2 = Math.min(((LazyListMeasuredItem) CollectionsKt.last(list)).getIndex() + i2, i3);
        int index2 = ((LazyListMeasuredItem) CollectionsKt.last(list)).getIndex() + 1;
        if (index2 <= iMin2) {
            ArrayList arrayList2 = null;
            while (true) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                arrayList = arrayList2;
                arrayList.add(LazyListMeasuredItemProvider.m319getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, index2, 0L, 2, null));
                if (index2 == iMin2) {
                    break;
                }
                index2++;
                arrayList2 = arrayList;
            }
        } else {
            arrayList = null;
        }
        if (z && lazyListLayoutInfo != null && !lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            List visibleItemsInfo = lazyListLayoutInfo.getVisibleItemsInfo();
            int size = visibleItemsInfo.size();
            while (true) {
                size--;
                if (-1 >= size) {
                    lazyListItemInfo = null;
                    break;
                }
                if (((LazyListItemInfo) visibleItemsInfo.get(size)).getIndex() > iMin2 && (size == 0 || ((LazyListItemInfo) visibleItemsInfo.get(size - 1)).getIndex() <= iMin2)) {
                    break;
                }
            }
            lazyListItemInfo = (LazyListItemInfo) visibleItemsInfo.get(size);
            LazyListItemInfo lazyListItemInfo2 = (LazyListItemInfo) CollectionsKt.last(lazyListLayoutInfo.getVisibleItemsInfo());
            if (lazyListItemInfo != null && (index = lazyListItemInfo.getIndex()) <= (iMin = Math.min(lazyListItemInfo2.getIndex(), i3))) {
                while (true) {
                    if (arrayList != null) {
                        int size2 = arrayList.size();
                        int i4 = 0;
                        while (true) {
                            if (i4 >= size2) {
                                obj3 = null;
                                break;
                            }
                            obj3 = arrayList.get(i4);
                            if (((LazyListMeasuredItem) obj3).getIndex() == index) {
                                break;
                            }
                            i4++;
                        }
                        lazyListMeasuredItem2 = (LazyListMeasuredItem) obj3;
                    } else {
                        lazyListMeasuredItem2 = null;
                    }
                    if (lazyListMeasuredItem2 == null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(LazyListMeasuredItemProvider.m319getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, index, 0L, 2, null));
                    }
                    if (index == iMin) {
                        break;
                    }
                    index++;
                }
            }
            float viewportEndOffset = ((lazyListLayoutInfo.getViewportEndOffset() - lazyListItemInfo2.getOffset()) - lazyListItemInfo2.getSize()) - f;
            if (viewportEndOffset > 0.0f) {
                int index3 = lazyListItemInfo2.getIndex() + 1;
                int i5 = 0;
                while (index3 < i && i5 < viewportEndOffset) {
                    if (index3 <= iMin2) {
                        int size3 = list.size();
                        int i6 = 0;
                        while (true) {
                            if (i6 >= size3) {
                                obj2 = null;
                                break;
                            }
                            obj2 = list.get(i6);
                            if (((LazyListMeasuredItem) obj2).getIndex() == index3) {
                                break;
                            }
                            i6++;
                        }
                        lazyListMeasuredItem = (LazyListMeasuredItem) obj2;
                    } else if (arrayList != null) {
                        int size4 = arrayList.size();
                        int i7 = 0;
                        while (true) {
                            if (i7 >= size4) {
                                obj = null;
                                break;
                            }
                            obj = arrayList.get(i7);
                            if (((LazyListMeasuredItem) obj).getIndex() == index3) {
                                break;
                            }
                            i7++;
                        }
                        lazyListMeasuredItem = (LazyListMeasuredItem) obj;
                    } else {
                        lazyListMeasuredItem = null;
                    }
                    if (lazyListMeasuredItem != null) {
                        index3++;
                        mainAxisSizeWithSpacings = lazyListMeasuredItem.getMainAxisSizeWithSpacings();
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(LazyListMeasuredItemProvider.m319getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, index3, 0L, 2, null));
                        index3++;
                        mainAxisSizeWithSpacings = ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getMainAxisSizeWithSpacings();
                    }
                    i5 += mainAxisSizeWithSpacings;
                }
            }
        }
        if (arrayList != null && ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getIndex() > iMin2) {
            iMin2 = ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getIndex();
        }
        int size5 = list2.size();
        for (int i8 = 0; i8 < size5; i8++) {
            int iIntValue = ((Number) list2.get(i8)).intValue();
            if (iIntValue > iMin2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(LazyListMeasuredItemProvider.m319getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, iIntValue, 0L, 2, null));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List createItemsBeforeList(int i, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i2, List list) {
        int iMax = Math.max(0, i - i2);
        int i3 = i - 1;
        ArrayList arrayList = null;
        if (iMax <= i3) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(LazyListMeasuredItemProvider.m319getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i3, 0L, 2, null));
                if (i3 == iMax) {
                    break;
                }
                i3--;
            }
        }
        int size = list.size() - 1;
        if (size >= 0) {
            while (true) {
                int i4 = size - 1;
                int iIntValue = ((Number) list.get(size)).intValue();
                if (iIntValue < iMax) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(LazyListMeasuredItemProvider.m319getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, iIntValue, 0L, 2, null));
                }
                if (i4 < 0) {
                    break;
                }
                size = i4;
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List calculateItemsOffsets(List list, List list2, List list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3 && i5 != 0) {
            throw new IllegalStateException("non-zero itemsScrollOffset");
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size() + list3.size());
        if (!z3) {
            int size = list2.size();
            int mainAxisSizeWithSpacings = i5;
            for (int i7 = 0; i7 < size; i7++) {
                LazyListMeasuredItem lazyListMeasuredItem = (LazyListMeasuredItem) list2.get(i7);
                mainAxisSizeWithSpacings -= lazyListMeasuredItem.getMainAxisSizeWithSpacings();
                lazyListMeasuredItem.position(mainAxisSizeWithSpacings, i, i2);
                arrayList.add(lazyListMeasuredItem);
            }
            int size2 = list.size();
            int mainAxisSizeWithSpacings2 = i5;
            for (int i8 = 0; i8 < size2; i8++) {
                LazyListMeasuredItem lazyListMeasuredItem2 = (LazyListMeasuredItem) list.get(i8);
                lazyListMeasuredItem2.position(mainAxisSizeWithSpacings2, i, i2);
                arrayList.add(lazyListMeasuredItem2);
                mainAxisSizeWithSpacings2 += lazyListMeasuredItem2.getMainAxisSizeWithSpacings();
            }
            int size3 = list3.size();
            for (int i9 = 0; i9 < size3; i9++) {
                LazyListMeasuredItem lazyListMeasuredItem3 = (LazyListMeasuredItem) list3.get(i9);
                lazyListMeasuredItem3.position(mainAxisSizeWithSpacings2, i, i2);
                arrayList.add(lazyListMeasuredItem3);
                mainAxisSizeWithSpacings2 += lazyListMeasuredItem3.getMainAxisSizeWithSpacings();
            }
        } else {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("no extra items");
            }
            int size4 = list.size();
            int[] iArr = new int[size4];
            for (int i10 = 0; i10 < size4; i10++) {
                iArr[i10] = ((LazyListMeasuredItem) list.get(calculateItemsOffsets$reverseAware(i10, z2, size4))).getSize();
            }
            int[] iArr2 = new int[size4];
            for (int i11 = 0; i11 < size4; i11++) {
                iArr2[i11] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalArrangement when isVertical == true");
                }
                vertical.arrange(density, i6, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalArrangement when isVertical == false");
                }
                horizontal.arrange(density, i6, iArr, LayoutDirection.Ltr, iArr2);
            }
            IntProgression indices = ArraysKt.getIndices(iArr2);
            if (z2) {
                indices = RangesKt.reversed(indices);
            }
            int first = indices.getFirst();
            int last = indices.getLast();
            int step = indices.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (true) {
                    int size5 = iArr2[first];
                    LazyListMeasuredItem lazyListMeasuredItem4 = (LazyListMeasuredItem) list.get(calculateItemsOffsets$reverseAware(first, z2, size4));
                    if (z2) {
                        size5 = (i6 - size5) - lazyListMeasuredItem4.getSize();
                    }
                    lazyListMeasuredItem4.position(size5, i, i2);
                    arrayList.add(lazyListMeasuredItem4);
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        }
        return arrayList;
    }
}
