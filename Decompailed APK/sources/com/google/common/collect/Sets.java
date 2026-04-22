package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class Sets {

    static abstract class ImprovedAbstractSet extends AbstractSet {
        ImprovedAbstractSet() {
        }

        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection collection) {
            return Sets.removeAllImpl(this, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection collection) {
            return super.retainAll((Collection) Preconditions.checkNotNull(collection));
        }
    }

    public static HashSet newHashSet() {
        return new HashSet();
    }

    public static HashSet newHashSetWithExpectedSize(int i) {
        return new HashSet(Maps.capacity(i));
    }

    static boolean removeAllImpl(Set set, Iterator it) {
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= set.remove(it.next());
        }
        return zRemove;
    }

    static boolean removeAllImpl(Set set, Collection collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if ((collection instanceof Set) && collection.size() > set.size()) {
            return Iterators.removeAll(set.iterator(), collection);
        }
        return removeAllImpl(set, collection.iterator());
    }
}
