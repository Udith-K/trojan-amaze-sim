package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import kotlin.Pair;
import kotlin.collections.builders.MapBuilder;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: MapsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class MapsKt__MapsJVMKt extends MapsKt__MapWithDefaultKt {
    public static int mapCapacity(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map mapOf(Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "pair");
        Map mapSingletonMap = Collections.singletonMap(pair.getFirst(), pair.getSecond());
        Intrinsics.checkNotNullExpressionValue(mapSingletonMap, "singletonMap(...)");
        return mapSingletonMap;
    }

    public static Map createMapBuilder() {
        return new MapBuilder();
    }

    public static Map build(Map builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return ((MapBuilder) builder).build();
    }

    public static SortedMap toSortedMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return new TreeMap(map);
    }

    public static SortedMap toSortedMap(Map map, Comparator comparator) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        TreeMap treeMap = new TreeMap(comparator);
        treeMap.putAll(map);
        return treeMap;
    }

    public static final Map toSingletonMap(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        Intrinsics.checkNotNullExpressionValue(mapSingletonMap, "with(...)");
        return mapSingletonMap;
    }
}
