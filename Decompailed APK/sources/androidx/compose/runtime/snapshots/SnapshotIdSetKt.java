package androidx.compose.runtime.snapshots;

/* JADX INFO: compiled from: SnapshotIdSet.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SnapshotIdSetKt {
    public static final int binarySearch(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = (i2 + length) >>> 1;
            int i4 = iArr[i3];
            if (i > i4) {
                i2 = i3 + 1;
            } else {
                if (i >= i4) {
                    return i3;
                }
                length = i3 - 1;
            }
        }
        return -(i2 + 1);
    }
}
