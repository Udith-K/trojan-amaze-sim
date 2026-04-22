package kotlin.reflect.jvm.internal.impl.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: collections.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class CollectionsKt {
    public static final Map mapToIndex(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = iterable.iterator();
        int i = 0;
        while (it.hasNext()) {
            linkedHashMap.put(it.next(), Integer.valueOf(i));
            i++;
        }
        return linkedHashMap;
    }

    public static final void addIfNotNull(Collection collection, Object obj) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        if (obj != null) {
            collection.add(obj);
        }
    }

    public static final HashMap newHashMapWithExpectedSize(int i) {
        return new HashMap(capacity(i));
    }

    public static final HashSet newHashSetWithExpectedSize(int i) {
        return new HashSet(capacity(i));
    }

    public static final LinkedHashSet newLinkedHashSetWithExpectedSize(int i) {
        return new LinkedHashSet(capacity(i));
    }

    private static final int capacity(int i) {
        if (i < 3) {
            return 3;
        }
        return i + (i / 3) + 1;
    }

    public static final List compact(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        int size = arrayList.size();
        if (size == 0) {
            return kotlin.collections.CollectionsKt.emptyList();
        }
        if (size == 1) {
            return kotlin.collections.CollectionsKt.listOf(kotlin.collections.CollectionsKt.first((List) arrayList));
        }
        arrayList.trimToSize();
        return arrayList;
    }
}
