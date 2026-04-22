package kotlinx.serialization.json.internal;

import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: SchemaCache.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class DescriptorSchemaCache {
    private final Map map = CreateMapForCacheKt.createMapForCache(1);

    /* JADX INFO: compiled from: SchemaCache.kt */
    public static final class Key {
    }

    public final void set(SerialDescriptor descriptor, Key key, Object value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Map map = this.map;
        Object objCreateMapForCache = map.get(descriptor);
        if (objCreateMapForCache == null) {
            objCreateMapForCache = CreateMapForCacheKt.createMapForCache(1);
            map.put(descriptor, objCreateMapForCache);
        }
        ((Map) objCreateMapForCache).put(key, value);
    }

    public final Object getOrPut(SerialDescriptor descriptor, Key key, Function0 defaultValue) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        Object obj = get(descriptor, key);
        if (obj != null) {
            return obj;
        }
        Object objInvoke = defaultValue.invoke();
        set(descriptor, key, objInvoke);
        return objInvoke;
    }

    public final Object get(SerialDescriptor descriptor, Key key) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Map map = (Map) this.map.get(descriptor);
        Object obj = map != null ? map.get(key) : null;
        if (obj == null) {
            return null;
        }
        return obj;
    }
}
