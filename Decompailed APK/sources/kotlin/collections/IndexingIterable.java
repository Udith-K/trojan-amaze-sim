package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: Iterables.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IndexingIterable implements Iterable, KMappedMarker {
    private final Function0 iteratorFactory;

    public IndexingIterable(Function0 iteratorFactory) {
        Intrinsics.checkNotNullParameter(iteratorFactory, "iteratorFactory");
        this.iteratorFactory = iteratorFactory;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new IndexingIterator((Iterator) this.iteratorFactory.invoke());
    }
}
