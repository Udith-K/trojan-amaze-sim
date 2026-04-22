package kotlin.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Maps.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class MapsKt___MapsKt extends MapsKt___MapsJvmKt {
    public static List toList(Map map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        if (map.size() == 0) {
            return CollectionsKt.emptyList();
        }
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return CollectionsKt.emptyList();
        }
        Map.Entry entry = (Map.Entry) it.next();
        if (!it.hasNext()) {
            return CollectionsKt.listOf(new Pair(entry.getKey(), entry.getValue()));
        }
        ArrayList arrayList = new ArrayList(map.size());
        arrayList.add(new Pair(entry.getKey(), entry.getValue()));
        do {
            Map.Entry entry2 = (Map.Entry) it.next();
            arrayList.add(new Pair(entry2.getKey(), entry2.getValue()));
        } while (it.hasNext());
        return arrayList;
    }
}
