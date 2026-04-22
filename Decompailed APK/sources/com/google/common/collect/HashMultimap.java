package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class HashMultimap extends HashMultimapGwtSerializationDependencies {
    transient int expectedValuesPerKey;

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMultimap
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    @Override // com.google.common.collect.AbstractMultimap
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static HashMultimap create() {
        return new HashMultimap();
    }

    private HashMultimap() {
        this(12, 2);
    }

    private HashMultimap(int i, int i2) {
        super(Platform.newHashMapWithExpectedSize(i));
        this.expectedValuesPerKey = 2;
        Preconditions.checkArgument(i2 >= 0);
        this.expectedValuesPerKey = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public Set createCollection() {
        return Platform.newHashSetWithExpectedSize(this.expectedValuesPerKey);
    }
}
