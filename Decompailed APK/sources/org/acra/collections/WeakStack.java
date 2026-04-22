package org.acra.collections;

import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: WeakStack.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class WeakStack extends AbstractCollection {
    private final List contents = new ArrayList();

    @Override // java.util.AbstractCollection, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    private final void cleanup() {
        for (WeakReference weakReference : this.contents) {
            if (weakReference.get() == null) {
                this.contents.remove(weakReference);
            }
        }
    }

    public int getSize() {
        cleanup();
        return this.contents.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        Iterator it = this.contents.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(obj, ((WeakReference) it.next()).get())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new WeakIterator(this.contents.iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(Object obj) {
        return this.contents.add(new WeakReference(obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        if (obj != null) {
            int size = this.contents.size();
            for (int i = 0; i < size; i++) {
                if (Intrinsics.areEqual(obj, ((WeakReference) this.contents.get(i)).get())) {
                    this.contents.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.contents.clear();
    }

    /* JADX INFO: compiled from: WeakStack.kt */
    private static final class WeakIterator implements Iterator, KMappedMarker {
        private final Iterator iterator;
        private Object next;

        public WeakIterator(Iterator iterator) {
            Intrinsics.checkNotNullParameter(iterator, "iterator");
            this.iterator = iterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.next != null) {
                return true;
            }
            while (this.iterator.hasNext()) {
                Object obj = ((WeakReference) this.iterator.next()).get();
                if (obj != null) {
                    this.next = obj;
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            Object obj = this.next;
            this.next = null;
            while (obj == null) {
                obj = ((WeakReference) this.iterator.next()).get();
            }
            return obj;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }
    }
}
