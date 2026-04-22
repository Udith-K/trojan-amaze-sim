package androidx.collection;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ObjectIntMap.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ObjectIntMapKt {
    private static final MutableObjectIntMap EmptyObjectIntMap = new MutableObjectIntMap(0);

    public static final ObjectIntMap emptyObjectIntMap() {
        MutableObjectIntMap mutableObjectIntMap = EmptyObjectIntMap;
        Intrinsics.checkNotNull(mutableObjectIntMap, "null cannot be cast to non-null type androidx.collection.ObjectIntMap<K of androidx.collection.ObjectIntMapKt.emptyObjectIntMap>");
        return mutableObjectIntMap;
    }

    public static final MutableObjectIntMap mutableObjectIntMapOf() {
        return new MutableObjectIntMap(0, 1, null);
    }
}
