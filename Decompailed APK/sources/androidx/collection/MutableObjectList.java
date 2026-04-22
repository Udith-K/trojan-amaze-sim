package androidx.collection;

import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ObjectList.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MutableObjectList extends ObjectList {
    public /* synthetic */ MutableObjectList(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 16 : i);
    }

    public MutableObjectList(int i) {
        super(i, null);
    }

    public final boolean add(Object obj) {
        ensureCapacity(this._size + 1);
        Object[] objArr = this.content;
        int i = this._size;
        objArr[i] = obj;
        this._size = i + 1;
        return true;
    }

    public final void clear() {
        ArraysKt.fill(this.content, (Object) null, 0, this._size);
        this._size = 0;
    }

    public final void ensureCapacity(int i) {
        Object[] objArr = this.content;
        if (objArr.length < i) {
            Object[] objArrCopyOf = Arrays.copyOf(objArr, Math.max(i, (objArr.length * 3) / 2));
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.content = objArrCopyOf;
        }
    }

    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        removeAt(iIndexOf);
        return true;
    }

    public final Object removeAt(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this._size)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Index ");
            sb.append(i);
            sb.append(" must be in 0..");
            sb.append(this._size - 1);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        Object[] objArr = this.content;
        Object obj = objArr[i];
        if (i != i2 - 1) {
            ArraysKt.copyInto(objArr, objArr, i, i + 1, i2);
        }
        int i3 = this._size - 1;
        this._size = i3;
        objArr[i3] = null;
        return obj;
    }
}
