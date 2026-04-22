package androidx.compose.ui.text.caches;

import ch.qos.logback.core.CoreConstants;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SimpleArrayMap.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SimpleArrayMap {
    private int _size;
    private int[] hashes;
    private Object[] keyValues;

    protected final int indexOf(Object obj, int i) {
        int i2 = this._size;
        if (i2 == 0) {
            return -1;
        }
        int iBinarySearchInternal = ContainerHelpersKt.binarySearchInternal(this.hashes, i2, i);
        if (iBinarySearchInternal < 0 || Intrinsics.areEqual(obj, this.keyValues[iBinarySearchInternal << 1])) {
            return iBinarySearchInternal;
        }
        int i3 = iBinarySearchInternal + 1;
        while (i3 < i2 && this.hashes[i3] == i) {
            if (Intrinsics.areEqual(obj, this.keyValues[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iBinarySearchInternal - 1; i4 >= 0 && this.hashes[i4] == i; i4--) {
            if (Intrinsics.areEqual(obj, this.keyValues[i4 << 1])) {
                return i4;
            }
        }
        return ~i3;
    }

    protected final int indexOfNull() {
        int i = this._size;
        if (i == 0) {
            return -1;
        }
        int iBinarySearchInternal = ContainerHelpersKt.binarySearchInternal(this.hashes, i, 0);
        if (iBinarySearchInternal < 0 || this.keyValues[iBinarySearchInternal << 1] == null) {
            return iBinarySearchInternal;
        }
        int i2 = iBinarySearchInternal + 1;
        while (i2 < i && this.hashes[i2] == 0) {
            if (this.keyValues[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iBinarySearchInternal - 1; i3 >= 0 && this.hashes[i3] == 0; i3--) {
            if (this.keyValues[i3 << 1] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public SimpleArrayMap(int i) {
        if (i == 0) {
            this.hashes = ContainerHelpersKt.EMPTY_INTS;
            this.keyValues = ContainerHelpersKt.EMPTY_OBJECTS;
        } else {
            this.hashes = new int[i];
            this.keyValues = new Object[i << 1];
        }
        this._size = 0;
    }

    public /* synthetic */ SimpleArrayMap(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    public final boolean containsKey(Object obj) {
        return indexOfKey(obj) >= 0;
    }

    public final int indexOfKey(Object obj) {
        return obj == null ? indexOfNull() : indexOf(obj, obj.hashCode());
    }

    public final Object get(Object obj) {
        int iIndexOfKey = indexOfKey(obj);
        if (iIndexOfKey >= 0) {
            return this.keyValues[(iIndexOfKey << 1) + 1];
        }
        return null;
    }

    public final Object keyAt(int i) {
        return this.keyValues[i << 1];
    }

    public final Object valueAt(int i) {
        return this.keyValues[(i << 1) + 1];
    }

    public final boolean isEmpty() {
        return this._size <= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof SimpleArrayMap) {
                SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
                int i = this._size;
                if (i != simpleArrayMap._size) {
                    return false;
                }
                for (int i2 = 0; i2 < i; i2++) {
                    Object objKeyAt = keyAt(i2);
                    Object objValueAt = valueAt(i2);
                    Object obj2 = simpleArrayMap.get(objKeyAt);
                    if (objValueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(objKeyAt)) {
                            return false;
                        }
                    } else if (!Intrinsics.areEqual(objValueAt, obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this._size != ((Map) obj).size()) {
                return false;
            }
            int i3 = this._size;
            for (int i4 = 0; i4 < i3; i4++) {
                Object objKeyAt2 = keyAt(i4);
                Object objValueAt2 = valueAt(i4);
                Object obj3 = ((Map) obj).get(objKeyAt2);
                if (objValueAt2 == null) {
                    if (obj3 != null || !((Map) obj).containsKey(objKeyAt2)) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(objValueAt2, obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public int hashCode() {
        int[] iArr = this.hashes;
        Object[] objArr = this.keyValues;
        int i = this._size;
        int i2 = 1;
        int i3 = 0;
        int iHashCode = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            iHashCode += (obj != null ? obj.hashCode() : 0) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return iHashCode;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this._size * 28);
        sb.append(CoreConstants.CURLY_LEFT);
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object objKeyAt = keyAt(i2);
            if (objKeyAt != this) {
                sb.append(objKeyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objValueAt = valueAt(i2);
            if (objValueAt != this) {
                sb.append(objValueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append(CoreConstants.CURLY_RIGHT);
        return sb.toString();
    }
}
