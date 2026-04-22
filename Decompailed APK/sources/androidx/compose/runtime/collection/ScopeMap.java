package androidx.compose.runtime.collection;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMapKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScopeMap.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ScopeMap {
    private final MutableScatterMap map = ScatterMapKt.mutableScatterMapOf();

    public final MutableScatterMap getMap() {
        return this.map;
    }

    public final int getSize() {
        return this.map.getSize();
    }

    public final void add(Object obj, Object obj2) {
        MutableScatterMap mutableScatterMap = this.map;
        int iFindInsertIndex = mutableScatterMap.findInsertIndex(obj);
        boolean z = iFindInsertIndex < 0;
        Object obj3 = z ? null : mutableScatterMap.values[iFindInsertIndex];
        if (obj3 != null) {
            if (obj3 instanceof MutableScatterSet) {
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap.add$lambda$0>");
                ((MutableScatterSet) obj3).add(obj2);
            } else if (obj3 != obj2) {
                MutableScatterSet mutableScatterSet = new MutableScatterSet(0, 1, null);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap.add$lambda$0");
                mutableScatterSet.add(obj3);
                mutableScatterSet.add(obj2);
                obj2 = mutableScatterSet;
            }
            obj2 = obj3;
        }
        if (z) {
            int i = ~iFindInsertIndex;
            mutableScatterMap.keys[i] = obj;
            mutableScatterMap.values[i] = obj2;
            return;
        }
        mutableScatterMap.values[iFindInsertIndex] = obj2;
    }

    public final void set(Object obj, Object obj2) {
        this.map.set(obj, obj2);
    }

    public final boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    public final void clear() {
        this.map.clear();
    }

    public final boolean remove(Object obj, Object obj2) {
        Object obj3 = this.map.get(obj);
        if (obj3 == null) {
            return false;
        }
        if (obj3 instanceof MutableScatterSet) {
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj3;
            boolean zRemove = mutableScatterSet.remove(obj2);
            if (zRemove && mutableScatterSet.isEmpty()) {
                this.map.remove(obj);
            }
            return zRemove;
        }
        if (!Intrinsics.areEqual(obj3, obj2)) {
            return false;
        }
        this.map.remove(obj);
        return true;
    }

    public final void removeScope(Object obj) {
        boolean zIsEmpty;
        MutableScatterMap mutableScatterMap = this.map;
        long[] jArr = mutableScatterMap.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        Object obj2 = mutableScatterMap.keys[i4];
                        Object obj3 = mutableScatterMap.values[i4];
                        if (obj3 instanceof MutableScatterSet) {
                            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap.removeScope$lambda$3>");
                            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj3;
                            mutableScatterSet.remove(obj);
                            zIsEmpty = mutableScatterSet.isEmpty();
                        } else {
                            zIsEmpty = obj3 == obj;
                        }
                        if (zIsEmpty) {
                            mutableScatterMap.removeValueAt(i4);
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }
}
