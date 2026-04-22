package androidx.collection;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: ScatterSet.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MutableScatterSet$MutableSetWrapper$iterator$1 implements Iterator, KMappedMarker {
    private int current = -1;
    private final Iterator iterator;
    final /* synthetic */ MutableScatterSet this$0;

    MutableScatterSet$MutableSetWrapper$iterator$1(MutableScatterSet mutableScatterSet) {
        this.this$0 = mutableScatterSet;
        this.iterator = SequencesKt.iterator(new MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1(mutableScatterSet, this, null));
    }

    public final void setCurrent(int i) {
        this.current = i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        int i = this.current;
        if (i != -1) {
            this.this$0.removeElementAt(i);
            this.current = -1;
        }
    }
}
