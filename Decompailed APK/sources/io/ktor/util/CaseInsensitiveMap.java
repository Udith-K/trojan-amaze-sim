package io.ktor.util;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;

/* JADX INFO: compiled from: CaseInsensitiveMap.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CaseInsensitiveMap implements Map, KMutableMap {
    private final Map delegate = new LinkedHashMap();

    @Override // java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return containsKey((String) obj);
        }
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ Set entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public final /* bridge */ Object get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ Set keySet() {
        return getKeys();
    }

    @Override // java.util.Map
    public final /* bridge */ Object remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection values() {
        return getValues();
    }

    public int getSize() {
        return this.delegate.size();
    }

    public boolean containsKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.delegate.containsKey(new CaseInsensitiveString(key));
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.delegate.containsValue(obj);
    }

    public Object get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.delegate.get(TextKt.caseInsensitive(key));
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override // java.util.Map
    public void clear() {
        this.delegate.clear();
    }

    @Override // java.util.Map
    public Object put(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return this.delegate.put(TextKt.caseInsensitive(key), value);
    }

    public Object remove(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.delegate.remove(TextKt.caseInsensitive(key));
    }

    public Set getKeys() {
        return new DelegatingMutableSet(this.delegate.keySet(), new Function1() { // from class: io.ktor.util.CaseInsensitiveMap$keys$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(CaseInsensitiveString $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                return $receiver.getContent();
            }
        }, new Function1() { // from class: io.ktor.util.CaseInsensitiveMap$keys$2
            @Override // kotlin.jvm.functions.Function1
            public final CaseInsensitiveString invoke(String $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                return TextKt.caseInsensitive($receiver);
            }
        });
    }

    public Set getEntries() {
        return new DelegatingMutableSet(this.delegate.entrySet(), new Function1() { // from class: io.ktor.util.CaseInsensitiveMap$entries$1
            @Override // kotlin.jvm.functions.Function1
            public final Map.Entry invoke(Map.Entry $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                return new Entry(((CaseInsensitiveString) $receiver.getKey()).getContent(), $receiver.getValue());
            }
        }, new Function1() { // from class: io.ktor.util.CaseInsensitiveMap$entries$2
            @Override // kotlin.jvm.functions.Function1
            public final Map.Entry invoke(Map.Entry $receiver) {
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                return new Entry(TextKt.caseInsensitive((String) $receiver.getKey()), $receiver.getValue());
            }
        });
    }

    public Collection getValues() {
        return this.delegate.values();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CaseInsensitiveMap)) {
            return false;
        }
        return Intrinsics.areEqual(((CaseInsensitiveMap) obj).delegate, this.delegate);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.delegate.hashCode();
    }

    @Override // java.util.Map
    public void putAll(Map from) {
        Intrinsics.checkNotNullParameter(from, "from");
        for (Map.Entry entry : from.entrySet()) {
            put((String) entry.getKey(), entry.getValue());
        }
    }
}
