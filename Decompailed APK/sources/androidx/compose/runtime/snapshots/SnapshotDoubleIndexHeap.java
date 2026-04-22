package androidx.compose.runtime.snapshots;

import kotlin.collections.ArraysKt;

/* JADX INFO: compiled from: SnapshotDoubleIndexHeap.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SnapshotDoubleIndexHeap {
    private int firstFreeHandle;
    private int[] handles;
    private int size;
    private int[] values = new int[16];
    private int[] index = new int[16];

    public SnapshotDoubleIndexHeap() {
        int[] iArr = new int[16];
        int i = 0;
        while (i < 16) {
            int i2 = i + 1;
            iArr[i] = i2;
            i = i2;
        }
        this.handles = iArr;
    }

    public final int lowestOrDefault(int i) {
        return this.size > 0 ? this.values[0] : i;
    }

    public final int add(int i) {
        ensure(this.size + 1);
        int i2 = this.size;
        this.size = i2 + 1;
        int iAllocateHandle = allocateHandle();
        this.values[i2] = i;
        this.index[i2] = iAllocateHandle;
        this.handles[iAllocateHandle] = i2;
        shiftUp(i2);
        return iAllocateHandle;
    }

    public final void remove(int i) {
        int i2 = this.handles[i];
        swap(i2, this.size - 1);
        this.size--;
        shiftUp(i2);
        shiftDown(i2);
        freeHandle(i);
    }

    private final void shiftUp(int i) {
        int[] iArr = this.values;
        int i2 = iArr[i];
        while (i > 0) {
            int i3 = ((i + 1) >> 1) - 1;
            if (iArr[i3] <= i2) {
                return;
            }
            swap(i3, i);
            i = i3;
        }
    }

    private final void shiftDown(int i) {
        int i2;
        int[] iArr = this.values;
        int i3 = this.size >> 1;
        while (i < i3) {
            int i4 = (i + 1) << 1;
            int i5 = i4 - 1;
            if (i4 < this.size && (i2 = iArr[i4]) < iArr[i5]) {
                if (i2 >= iArr[i]) {
                    return;
                }
                swap(i4, i);
                i = i4;
            } else {
                if (iArr[i5] >= iArr[i]) {
                    return;
                }
                swap(i5, i);
                i = i5;
            }
        }
    }

    private final void swap(int i, int i2) {
        int[] iArr = this.values;
        int[] iArr2 = this.index;
        int[] iArr3 = this.handles;
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
        int i4 = iArr2[i];
        iArr2[i] = iArr2[i2];
        iArr2[i2] = i4;
        iArr3[iArr2[i]] = i;
        iArr3[iArr2[i2]] = i2;
    }

    private final void ensure(int i) {
        int[] iArr = this.values;
        int length = iArr.length;
        if (i <= length) {
            return;
        }
        int i2 = length * 2;
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        ArraysKt.copyInto$default(iArr, iArr2, 0, 0, 0, 14, (Object) null);
        ArraysKt.copyInto$default(this.index, iArr3, 0, 0, 0, 14, (Object) null);
        this.values = iArr2;
        this.index = iArr3;
    }

    private final int allocateHandle() {
        int length = this.handles.length;
        if (this.firstFreeHandle >= length) {
            int i = length * 2;
            int[] iArr = new int[i];
            int i2 = 0;
            while (i2 < i) {
                int i3 = i2 + 1;
                iArr[i2] = i3;
                i2 = i3;
            }
            ArraysKt.copyInto$default(this.handles, iArr, 0, 0, 0, 14, (Object) null);
            this.handles = iArr;
        }
        int i4 = this.firstFreeHandle;
        this.firstFreeHandle = this.handles[i4];
        return i4;
    }

    private final void freeHandle(int i) {
        this.handles[i] = this.firstFreeHandle;
        this.firstFreeHandle = i;
    }
}
