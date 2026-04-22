package com.google.common.collect;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
abstract class AbstractSetMultimap extends AbstractMapBasedMultimap implements Multimap {
    protected AbstractSetMultimap(Map map) {
        super(map);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap
    Collection wrapCollection(Object obj, Collection collection) {
        return new AbstractMapBasedMultimap.WrappedSet(obj, (Set) collection);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Map asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
