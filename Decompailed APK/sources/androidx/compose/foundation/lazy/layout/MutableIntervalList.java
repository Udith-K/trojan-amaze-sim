package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.runtime.collection.MutableVector;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: IntervalList.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MutableIntervalList implements IntervalList {
    private final MutableVector intervals = new MutableVector(new IntervalList.Interval[16], 0);
    private IntervalList.Interval lastInterval;
    private int size;

    @Override // androidx.compose.foundation.lazy.layout.IntervalList
    public int getSize() {
        return this.size;
    }

    public final void addInterval(int i, Object obj) {
        if (i < 0) {
            throw new IllegalArgumentException(("size should be >=0, but was " + i).toString());
        }
        if (i == 0) {
            return;
        }
        IntervalList.Interval interval = new IntervalList.Interval(getSize(), i, obj);
        this.size = getSize() + i;
        this.intervals.add(interval);
    }

    @Override // androidx.compose.foundation.lazy.layout.IntervalList
    public void forEach(int i, int i2, Function1 function1) {
        checkIndexBounds(i);
        checkIndexBounds(i2);
        if (i2 >= i) {
            int iBinarySearch = IntervalListKt.binarySearch(this.intervals, i);
            int startIndex = ((IntervalList.Interval) this.intervals.getContent()[iBinarySearch]).getStartIndex();
            while (startIndex <= i2) {
                IntervalList.Interval interval = (IntervalList.Interval) this.intervals.getContent()[iBinarySearch];
                function1.invoke(interval);
                startIndex += interval.getSize();
                iBinarySearch++;
            }
            return;
        }
        throw new IllegalArgumentException(("toIndex (" + i2 + ") should be not smaller than fromIndex (" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR).toString());
    }

    @Override // androidx.compose.foundation.lazy.layout.IntervalList
    public IntervalList.Interval get(int i) {
        checkIndexBounds(i);
        return getIntervalForIndex(i);
    }

    private final IntervalList.Interval getIntervalForIndex(int i) {
        IntervalList.Interval interval = this.lastInterval;
        if (interval != null && contains(interval, i)) {
            return interval;
        }
        MutableVector mutableVector = this.intervals;
        IntervalList.Interval interval2 = (IntervalList.Interval) mutableVector.getContent()[IntervalListKt.binarySearch(mutableVector, i)];
        this.lastInterval = interval2;
        return interval2;
    }

    private final void checkIndexBounds(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException("Index " + i + ", size " + getSize());
        }
    }

    private final boolean contains(IntervalList.Interval interval, int i) {
        return i < interval.getStartIndex() + interval.getSize() && interval.getStartIndex() <= i;
    }
}
