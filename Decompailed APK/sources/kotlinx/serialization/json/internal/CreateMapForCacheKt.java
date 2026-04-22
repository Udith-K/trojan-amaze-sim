package kotlinx.serialization.json.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: createMapForCache.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class CreateMapForCacheKt {
    public static final Map createMapForCache(int i) {
        return new ConcurrentHashMap(i);
    }
}
