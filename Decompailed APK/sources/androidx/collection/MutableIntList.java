package androidx.collection;

import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IntList.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MutableIntList extends IntList {
    public /* synthetic */ MutableIntList(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 16 : i);
    }

    public MutableIntList(int i) {
        super(i, null);
    }

    public final boolean add(int i) {
        ensureCapacity(this._size + 1);
        int[] iArr = this.content;
        int i2 = this._size;
        iArr[i2] = i;
        this._size = i2 + 1;
        return true;
    }

    public final void add(int i, int i2) {
        int i3;
        if (i < 0 || i > (i3 = this._size)) {
            throw new IndexOutOfBoundsException("Index " + i + " must be in 0.." + this._size);
        }
        ensureCapacity(i3 + 1);
        int[] iArr = this.content;
        int i4 = this._size;
        if (i != i4) {
            ArraysKt.copyInto(iArr, iArr, i + 1, i, i4);
        }
        iArr[i] = i2;
        this._size++;
    }

    public final boolean addAll(int i, int[] elements) {
        int i2;
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (i < 0 || i > (i2 = this._size)) {
            throw new IndexOutOfBoundsException("Index " + i + " must be in 0.." + this._size);
        }
        if (elements.length == 0) {
            return false;
        }
        ensureCapacity(i2 + elements.length);
        int[] iArr = this.content;
        int i3 = this._size;
        if (i != i3) {
            ArraysKt.copyInto(iArr, iArr, elements.length + i, i, i3);
        }
        ArraysKt.copyInto$default(elements, iArr, i, 0, 0, 12, (Object) null);
        this._size += elements.length;
        return true;
    }

    public final void plusAssign(int[] elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        addAll(this._size, elements);
    }

    public final void ensureCapacity(int i) {
        int[] iArr = this.content;
        if (iArr.length < i) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, Math.max(i, (iArr.length * 3) / 2));
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            this.content = iArrCopyOf;
        }
    }

    public final int removeAt(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this._size)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Index ");
            sb.append(i);
            sb.append(" must be in 0..");
            sb.append(this._size - 1);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        int[] iArr = this.content;
        int i3 = iArr[i];
        if (i != i2 - 1) {
            ArraysKt.copyInto(iArr, iArr, i, i + 1, i2);
        }
        this._size--;
        return i3;
    }

    public final int set(int i, int i2) {
        if (i < 0 || i >= this._size) {
            StringBuilder sb = new StringBuilder();
            sb.append("set index ");
            sb.append(i);
            sb.append(" must be between 0 .. ");
            sb.append(this._size - 1);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        int[] iArr = this.content;
        int i3 = iArr[i];
        iArr[i] = i2;
        return i3;
    }

    public final void sort() {
        int i = this._size;
        if (i == 0) {
            return;
        }
        ArraysKt.sort(this.content, 0, i);
    }
}
