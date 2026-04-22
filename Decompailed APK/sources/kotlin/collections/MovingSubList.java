package kotlin.collections;

import java.util.List;
import java.util.RandomAccess;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlidingWindow.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MovingSubList extends AbstractList implements RandomAccess {
    private int _size;
    private int fromIndex;
    private final List list;

    public MovingSubList(List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
    }

    public final void move(int i, int i2) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, this.list.size());
        this.fromIndex = i;
        this._size = i2 - i;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Object get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this._size);
        return this.list.get(this.fromIndex + i);
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this._size;
    }
}
