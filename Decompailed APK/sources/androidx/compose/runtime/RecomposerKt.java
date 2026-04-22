package androidx.compose.runtime;

import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: Recomposer.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RecomposerKt {
    private static final Object ProduceAnotherFrame = new Object();
    private static final Object FramePending = new Object();

    public static final Object removeLastMultiValue(Map map, Object obj) {
        List list = (List) map.get(obj);
        if (list == null) {
            return null;
        }
        Object objRemoveFirst = CollectionsKt.removeFirst(list);
        if (!list.isEmpty()) {
            return objRemoveFirst;
        }
        map.remove(obj);
        return objRemoveFirst;
    }
}
