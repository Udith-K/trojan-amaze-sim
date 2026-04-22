package androidx.compose.animation.core;

import androidx.collection.IntList;
import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: IntListExtension.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class IntListExtensionKt {
    public static /* synthetic */ int binarySearch$default(IntList intList, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = intList.getSize();
        }
        return binarySearch(intList, i, i2, i3);
    }

    public static final int binarySearch(IntList intList, int i, int i2, int i3) {
        if (!(i2 <= i3)) {
            PreconditionsKt.throwIllegalArgumentException("fromIndex(" + i2 + ") > toIndex(" + i3 + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Index out of range: " + i2);
        }
        if (i3 > intList.getSize()) {
            throw new IndexOutOfBoundsException("Index out of range: " + i3);
        }
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int i6 = intList.get(i5);
            if (i6 < i) {
                i2 = i5 + 1;
            } else {
                if (i6 <= i) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return -(i2 + 1);
    }
}
