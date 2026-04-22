package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.EndOfChain;
import java.util.Iterator;
import kotlin.collections.AbstractSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PersistentOrderedSet.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PersistentOrderedSet extends AbstractSet implements PersistentSet {
    private static final PersistentOrderedSet EMPTY;
    private final Object firstElement;
    private final PersistentHashMap hashMap;
    private final Object lastElement;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public PersistentOrderedSet(Object obj, Object obj2, PersistentHashMap persistentHashMap) {
        this.firstElement = obj;
        this.lastElement = obj2;
        this.hashMap = persistentHashMap;
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this.hashMap.size();
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return this.hashMap.containsKey(obj);
    }

    @Override // java.util.Collection, java.util.Set, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet
    public PersistentSet add(Object obj) {
        if (this.hashMap.containsKey(obj)) {
            return this;
        }
        if (isEmpty()) {
            return new PersistentOrderedSet(obj, obj, this.hashMap.put(obj, (Object) new Links()));
        }
        Object obj2 = this.lastElement;
        Object obj3 = this.hashMap.get(obj2);
        Intrinsics.checkNotNull(obj3);
        return new PersistentOrderedSet(this.firstElement, obj, this.hashMap.put(obj2, (Object) ((Links) obj3).withNext(obj)).put(obj, (Object) new Links(obj2)));
    }

    @Override // java.util.Collection, java.util.Set, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet
    public PersistentSet remove(Object obj) {
        Links links = (Links) this.hashMap.get(obj);
        if (links == null) {
            return this;
        }
        PersistentHashMap persistentHashMapRemove = this.hashMap.remove(obj);
        if (links.getHasPrevious()) {
            Object obj2 = persistentHashMapRemove.get(links.getPrevious());
            Intrinsics.checkNotNull(obj2);
            persistentHashMapRemove = persistentHashMapRemove.put(links.getPrevious(), (Object) ((Links) obj2).withNext(links.getNext()));
        }
        if (links.getHasNext()) {
            Object obj3 = persistentHashMapRemove.get(links.getNext());
            Intrinsics.checkNotNull(obj3);
            persistentHashMapRemove = persistentHashMapRemove.put(links.getNext(), (Object) ((Links) obj3).withPrevious(links.getPrevious()));
        }
        return new PersistentOrderedSet(!links.getHasPrevious() ? links.getNext() : this.firstElement, !links.getHasNext() ? links.getPrevious() : this.lastElement, persistentHashMapRemove);
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        return new PersistentOrderedSetIterator(this.firstElement, this.hashMap);
    }

    /* JADX INFO: compiled from: PersistentOrderedSet.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PersistentSet emptyOf$runtime_release() {
            return PersistentOrderedSet.EMPTY;
        }
    }

    static {
        EndOfChain endOfChain = EndOfChain.INSTANCE;
        EMPTY = new PersistentOrderedSet(endOfChain, endOfChain, PersistentHashMap.Companion.emptyOf$runtime_release());
    }
}
