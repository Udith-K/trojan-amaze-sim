package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.foundation.lazy.layout.IntervalList;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: LazyLayoutKeyIndexMap.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NearestRangeKeyIndexMap implements LazyLayoutKeyIndexMap {
    private final Object[] keys;
    private final int keysStartIndex;
    private final ObjectIntMap map;

    public NearestRangeKeyIndexMap(IntRange intRange, LazyLayoutIntervalContent lazyLayoutIntervalContent) {
        IntervalList intervals = lazyLayoutIntervalContent.getIntervals();
        final int first = intRange.getFirst();
        if (first < 0) {
            throw new IllegalStateException("negative nearestRange.first");
        }
        final int iMin = Math.min(intRange.getLast(), intervals.getSize() - 1);
        if (iMin < first) {
            this.map = ObjectIntMapKt.emptyObjectIntMap();
            this.keys = new Object[0];
            this.keysStartIndex = 0;
        } else {
            int i = (iMin - first) + 1;
            this.keys = new Object[i];
            this.keysStartIndex = first;
            final MutableObjectIntMap mutableObjectIntMap = new MutableObjectIntMap(i);
            intervals.forEach(first, iMin, new Function1() { // from class: androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((IntervalList.Interval) obj);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0039  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void invoke(androidx.compose.foundation.lazy.layout.IntervalList.Interval r7) {
                    /*
                        r6 = this;
                        java.lang.Object r0 = r7.getValue()
                        androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent$Interval r0 = (androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval) r0
                        kotlin.jvm.functions.Function1 r0 = r0.getKey()
                        int r1 = r1
                        int r2 = r7.getStartIndex()
                        int r1 = java.lang.Math.max(r1, r2)
                        int r2 = r2
                        int r3 = r7.getStartIndex()
                        int r4 = r7.getSize()
                        int r3 = r3 + r4
                        int r3 = r3 + (-1)
                        int r2 = java.lang.Math.min(r2, r3)
                        if (r1 > r2) goto L57
                    L27:
                        if (r0 == 0) goto L39
                        int r3 = r7.getStartIndex()
                        int r3 = r1 - r3
                        java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                        java.lang.Object r3 = r0.invoke(r3)
                        if (r3 != 0) goto L3d
                    L39:
                        java.lang.Object r3 = androidx.compose.foundation.lazy.layout.Lazy_androidKt.getDefaultLazyLayoutKey(r1)
                    L3d:
                        androidx.collection.MutableObjectIntMap r4 = r3
                        r4.set(r3, r1)
                        androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap r4 = r4
                        java.lang.Object[] r4 = androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap.access$getKeys$p(r4)
                        androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap r5 = r4
                        int r5 = androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap.access$getKeysStartIndex$p(r5)
                        int r5 = r1 - r5
                        r4[r5] = r3
                        if (r1 == r2) goto L57
                        int r1 = r1 + 1
                        goto L27
                    L57:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap$2$1.invoke(androidx.compose.foundation.lazy.layout.IntervalList$Interval):void");
                }
            });
            this.map = mutableObjectIntMap;
        }
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
    public int getIndex(Object obj) {
        ObjectIntMap objectIntMap = this.map;
        int iFindKeyIndex = objectIntMap.findKeyIndex(obj);
        if (iFindKeyIndex >= 0) {
            return objectIntMap.values[iFindKeyIndex];
        }
        return -1;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
    public Object getKey(int i) {
        Object[] objArr = this.keys;
        int i2 = i - this.keysStartIndex;
        if (i2 < 0 || i2 > ArraysKt.getLastIndex(objArr)) {
            return null;
        }
        return objArr[i2];
    }
}
