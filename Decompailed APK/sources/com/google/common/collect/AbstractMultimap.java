package com.google.common.collect;

import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
abstract class AbstractMultimap implements Multimap {
    private transient Map asMap;
    private transient Set keySet;

    abstract Map createAsMap();

    abstract Set createKeySet();

    AbstractMultimap() {
    }

    public Set keySet() {
        Set set = this.keySet;
        if (set != null) {
            return set;
        }
        Set setCreateKeySet = createKeySet();
        this.keySet = setCreateKeySet;
        return setCreateKeySet;
    }

    @Override // com.google.common.collect.Multimap
    public Map asMap() {
        Map map = this.asMap;
        if (map != null) {
            return map;
        }
        Map mapCreateAsMap = createAsMap();
        this.asMap = mapCreateAsMap;
        return mapCreateAsMap;
    }

    public boolean equals(Object obj) {
        return Multimaps.equalsImpl(this, obj);
    }

    public int hashCode() {
        return asMap().hashCode();
    }

    public String toString() {
        return asMap().toString();
    }
}
