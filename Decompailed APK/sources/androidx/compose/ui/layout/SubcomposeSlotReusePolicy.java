package androidx.compose.ui.layout;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: SubcomposeLayout.kt */
/* JADX INFO: loaded from: classes.dex */
public interface SubcomposeSlotReusePolicy {
    boolean areCompatible(Object obj, Object obj2);

    void getSlotsToRetain(SlotIdsSet slotIdsSet);

    /* JADX INFO: compiled from: SubcomposeLayout.kt */
    public static final class SlotIdsSet implements Collection, KMappedMarker {
        private final Set set;

        @Override // java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return this.set.contains(obj);
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection collection) {
            return this.set.containsAll(collection);
        }

        public int getSize() {
            return this.set.size();
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return this.set.isEmpty();
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate predicate) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            return CollectionToArray.toArray(this, objArr);
        }

        public SlotIdsSet(Set set) {
            this.set = set;
        }

        @Override // java.util.Collection
        public final /* bridge */ int size() {
            return getSize();
        }

        public /* synthetic */ SlotIdsSet(Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new LinkedHashSet() : set);
        }

        @Override // java.util.Collection
        /* JADX INFO: renamed from: add$ui_release, reason: merged with bridge method [inline-methods] */
        public final boolean add(Object obj) {
            return this.set.add(obj);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return this.set.iterator();
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            return this.set.remove(obj);
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection collection) {
            return this.set.remove(collection);
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection collection) {
            return this.set.retainAll(collection);
        }

        @Override // java.util.Collection
        public final void clear() {
            this.set.clear();
        }
    }
}
