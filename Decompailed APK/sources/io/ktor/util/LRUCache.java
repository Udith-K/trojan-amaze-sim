package io.ktor.util;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Cache.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LRUCache extends LinkedHashMap {
    private final Function1 close;
    private final int maxSize;
    private final Function1 supplier;

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set entrySet() {
        return getEntries();
    }

    public /* bridge */ Set getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set getKeys() {
        return super.keySet();
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection getValues() {
        return super.values();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set keySet() {
        return getKeys();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection values() {
        return getValues();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LRUCache(Function1 supplier, Function1 close, int i) {
        super(10, 0.75f, true);
        Intrinsics.checkNotNullParameter(supplier, "supplier");
        Intrinsics.checkNotNullParameter(close, "close");
        this.supplier = supplier;
        this.close = close;
        this.maxSize = i;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry eldest) {
        Intrinsics.checkNotNullParameter(eldest, "eldest");
        boolean z = size() > this.maxSize;
        if (z) {
            this.close.invoke(eldest.getValue());
        }
        return z;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        if (this.maxSize == 0) {
            return this.supplier.invoke(obj);
        }
        synchronized (this) {
            Object obj2 = super.get(obj);
            if (obj2 != null) {
                return obj2;
            }
            Object objInvoke = this.supplier.invoke(obj);
            put(obj, objInvoke);
            return objInvoke;
        }
    }
}
