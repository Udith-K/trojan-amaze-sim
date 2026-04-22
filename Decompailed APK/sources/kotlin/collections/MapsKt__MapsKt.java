package kotlin.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Maps.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class MapsKt__MapsKt extends MapsKt__MapsJVMKt {
    public static Map emptyMap() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        Intrinsics.checkNotNull(emptyMap, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return emptyMap;
    }

    public static Map mapOf(Pair... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return pairs.length > 0 ? toMap(pairs, new LinkedHashMap(MapsKt.mapCapacity(pairs.length))) : MapsKt.emptyMap();
    }

    public static HashMap hashMapOf(Pair... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        HashMap map = new HashMap(MapsKt.mapCapacity(pairs.length));
        putAll(map, pairs);
        return map;
    }

    public static Object getValue(Map map, Object obj) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(map, obj);
    }

    public static final void putAll(Map map, Pair[] pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        for (Pair pair : pairs) {
            map.put(pair.component1(), pair.component2());
        }
    }

    public static final void putAll(Map map, Iterable pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        Iterator it = pairs.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            map.put(pair.component1(), pair.component2());
        }
    }

    public static final void putAll(Map map, Sequence pairs) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        Iterator it = pairs.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            map.put(pair.component1(), pair.component2());
        }
    }

    public static Map toMap(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                return MapsKt.emptyMap();
            }
            if (size != 1) {
                return MapsKt.toMap(iterable, new LinkedHashMap(MapsKt.mapCapacity(collection.size())));
            }
            return MapsKt.mapOf((Pair) (iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next()));
        }
        return optimizeReadOnlyMap(MapsKt.toMap(iterable, new LinkedHashMap()));
    }

    public static Map toMap(Iterable iterable, Map destination) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        putAll(destination, iterable);
        return destination;
    }

    public static final Map toMap(Pair[] pairArr, Map destination) {
        Intrinsics.checkNotNullParameter(pairArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        putAll(destination, pairArr);
        return destination;
    }

    public static Map toMap(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return optimizeReadOnlyMap(toMap(sequence, new LinkedHashMap()));
    }

    public static final Map toMap(Sequence sequence, Map destination) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        putAll(destination, sequence);
        return destination;
    }

    public static Map toMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        int size = map.size();
        if (size == 0) {
            return MapsKt.emptyMap();
        }
        if (size == 1) {
            return MapsKt__MapsJVMKt.toSingletonMap(map);
        }
        return MapsKt.toMutableMap(map);
    }

    public static Map toMutableMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return new LinkedHashMap(map);
    }

    public static Map plus(Map map, Pair pair) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(pair, "pair");
        if (map.isEmpty()) {
            return MapsKt.mapOf(pair);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put(pair.getFirst(), pair.getSecond());
        return linkedHashMap;
    }

    public static Map plus(Map map, Map map2) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(map2, "map");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.putAll(map2);
        return linkedHashMap;
    }

    public static final Map optimizeReadOnlyMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        int size = map.size();
        if (size != 0) {
            return size != 1 ? map : MapsKt__MapsJVMKt.toSingletonMap(map);
        }
        return MapsKt.emptyMap();
    }
}
