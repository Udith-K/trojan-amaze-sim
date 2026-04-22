package androidx.collection;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IntObjectMap.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class IntObjectMapKt {
    private static final MutableIntObjectMap EmptyIntObjectMap = new MutableIntObjectMap(0);

    public static final IntObjectMap intObjectMapOf() {
        MutableIntObjectMap mutableIntObjectMap = EmptyIntObjectMap;
        Intrinsics.checkNotNull(mutableIntObjectMap, "null cannot be cast to non-null type androidx.collection.IntObjectMap<V of androidx.collection.IntObjectMapKt.intObjectMapOf>");
        return mutableIntObjectMap;
    }

    public static final MutableIntObjectMap mutableIntObjectMapOf() {
        return new MutableIntObjectMap(0, 1, null);
    }

    public static final MutableIntObjectMap mutableIntObjectMapOf(int i, Object obj, int i2, Object obj2, int i3, Object obj3) {
        MutableIntObjectMap mutableIntObjectMap = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap.set(i, obj);
        mutableIntObjectMap.set(i2, obj2);
        mutableIntObjectMap.set(i3, obj3);
        return mutableIntObjectMap;
    }
}
