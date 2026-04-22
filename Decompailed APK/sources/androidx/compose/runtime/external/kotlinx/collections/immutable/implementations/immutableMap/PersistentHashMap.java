package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableCollection;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode;
import java.util.Map;
import java.util.Set;
import kotlin.collections.AbstractMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: PersistentHashMap.kt */
/* JADX INFO: loaded from: classes.dex */
public class PersistentHashMap extends AbstractMap implements Map, KMappedMarker {
    private final TrieNode node;
    private final int size;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final PersistentHashMap EMPTY = new PersistentHashMap(TrieNode.Companion.getEMPTY$runtime_release(), 0);

    public final TrieNode getNode$runtime_release() {
        return this.node;
    }

    public PersistentHashMap(TrieNode trieNode, int i) {
        this.node = trieNode;
        this.size = i;
    }

    @Override // kotlin.collections.AbstractMap
    public int getSize() {
        return this.size;
    }

    @Override // kotlin.collections.AbstractMap
    public ImmutableSet getKeys() {
        return new PersistentHashMapKeys(this);
    }

    @Override // kotlin.collections.AbstractMap
    public ImmutableCollection getValues() {
        return new PersistentHashMapValues(this);
    }

    private final ImmutableSet createEntries() {
        return new PersistentHashMapEntries(this);
    }

    @Override // kotlin.collections.AbstractMap
    public final Set getEntries() {
        return createEntries();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.node.containsKey(obj != null ? obj.hashCode() : 0, obj, 0);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.node.get(obj != null ? obj.hashCode() : 0, obj, 0);
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public PersistentHashMap put(Object obj, Object obj2) {
        TrieNode.ModificationResult modificationResultPut = this.node.put(obj != null ? obj.hashCode() : 0, obj, obj2, 0);
        return modificationResultPut == null ? this : new PersistentHashMap(modificationResultPut.getNode(), size() + modificationResultPut.getSizeDelta());
    }

    @Override // kotlin.collections.AbstractMap, java.util.Map
    public PersistentHashMap remove(Object obj) {
        TrieNode trieNodeRemove = this.node.remove(obj != null ? obj.hashCode() : 0, obj, 0);
        if (this.node == trieNodeRemove) {
            return this;
        }
        if (trieNodeRemove == null) {
            return Companion.emptyOf$runtime_release();
        }
        return new PersistentHashMap(trieNodeRemove, size() - 1);
    }

    /* JADX INFO: compiled from: PersistentHashMap.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PersistentHashMap emptyOf$runtime_release() {
            PersistentHashMap persistentHashMap = PersistentHashMap.EMPTY;
            Intrinsics.checkNotNull(persistentHashMap, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap.Companion.emptyOf>");
            return persistentHashMap;
        }
    }
}
