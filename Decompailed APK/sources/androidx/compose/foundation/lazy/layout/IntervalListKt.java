package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.runtime.collection.MutableVector;

/* JADX INFO: compiled from: IntervalList.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class IntervalListKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int binarySearch(MutableVector mutableVector, int i) {
        int size = mutableVector.getSize() - 1;
        int i2 = 0;
        while (i2 < size) {
            int i3 = ((size - i2) / 2) + i2;
            int startIndex = ((IntervalList.Interval) mutableVector.getContent()[i3]).getStartIndex();
            if (startIndex == i) {
                return i3;
            }
            if (startIndex < i) {
                i2 = i3 + 1;
                if (i < ((IntervalList.Interval) mutableVector.getContent()[i2]).getStartIndex()) {
                    return i3;
                }
            } else {
                size = i3 - 1;
            }
        }
        return i2;
    }
}
