package kotlin.collections;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: MapWithDefault.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class MapsKt__MapWithDefaultKt {
    public static final Object getOrImplicitDefaultNullable(Map map, Object obj) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        if (map instanceof MapWithDefault) {
            return ((MapWithDefault) map).getOrImplicitDefault(obj);
        }
        Object obj2 = map.get(obj);
        if (obj2 != null || map.containsKey(obj)) {
            return obj2;
        }
        throw new NoSuchElementException("Key " + obj + " is missing in the map.");
    }

    public static Map withDefaultMutable(Map map, Function1 defaultValue) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return map instanceof MutableMapWithDefault ? MapsKt.withDefaultMutable(((MutableMapWithDefault) map).getMap(), defaultValue) : new MutableMapWithDefaultImpl(map, defaultValue);
    }
}
