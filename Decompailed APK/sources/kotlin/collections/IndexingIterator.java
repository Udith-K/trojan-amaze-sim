package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: Iterators.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IndexingIterator implements Iterator, KMappedMarker {
    private int index;
    private final Iterator iterator;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public IndexingIterator(Iterator iterator) {
        Intrinsics.checkNotNullParameter(iterator, "iterator");
        this.iterator = iterator;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public final IndexedValue next() {
        int i = this.index;
        this.index = i + 1;
        if (i < 0) {
            CollectionsKt.throwIndexOverflow();
        }
        return new IndexedValue(i, this.iterator.next());
    }
}
