package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: HitTestResult.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HitTestResult implements List, KMappedMarker {
    private int size;
    private Object[] values = new Object[16];
    private long[] distanceFromEdgeAndInLayer = new long[16];
    private int hitDepth = -1;
    private boolean shouldSharePointerInputWithSibling = true;

    @Override // java.util.List
    public /* bridge */ /* synthetic */ void add(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ Object remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public void replaceAll(UnaryOperator unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public void sort(Comparator comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        return CollectionToArray.toArray(this, objArr);
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Modifier.Node) {
            return contains((Modifier.Node) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Modifier.Node) {
            return indexOf((Modifier.Node) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Modifier.Node) {
            return lastIndexOf((Modifier.Node) obj);
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    public int getSize() {
        return this.size;
    }

    public final boolean getShouldSharePointerInputWithSibling() {
        return this.shouldSharePointerInputWithSibling;
    }

    public final boolean hasHit() {
        long jM1800findBestHitDistanceptXAw2c = m1800findBestHitDistanceptXAw2c();
        return DistanceAndInLayer.m1798getDistanceimpl(jM1800findBestHitDistanceptXAw2c) < 0.0f && DistanceAndInLayer.m1799isInLayerimpl(jM1800findBestHitDistanceptXAw2c);
    }

    public final void acceptHits() {
        this.hitDepth = size() - 1;
    }

    private final void resizeToHitDepth() {
        int i = this.hitDepth + 1;
        int lastIndex = CollectionsKt.getLastIndex(this);
        if (i <= lastIndex) {
            while (true) {
                this.values[i] = null;
                if (i == lastIndex) {
                    break;
                } else {
                    i++;
                }
            }
        }
        this.size = this.hitDepth + 1;
    }

    public final boolean isHitInMinimumTouchTargetBetter(float f, boolean z) {
        if (this.hitDepth == CollectionsKt.getLastIndex(this)) {
            return true;
        }
        return DistanceAndInLayer.m1796compareToS_HNhKs(m1800findBestHitDistanceptXAw2c(), HitTestResultKt.DistanceAndInLayer(f, z)) > 0;
    }

    /* JADX INFO: renamed from: findBestHitDistance-ptXAw2c, reason: not valid java name */
    private final long m1800findBestHitDistanceptXAw2c() {
        long jDistanceAndInLayer = HitTestResultKt.DistanceAndInLayer(Float.POSITIVE_INFINITY, false);
        int i = this.hitDepth + 1;
        int lastIndex = CollectionsKt.getLastIndex(this);
        if (i <= lastIndex) {
            while (true) {
                long jM1797constructorimpl = DistanceAndInLayer.m1797constructorimpl(this.distanceFromEdgeAndInLayer[i]);
                if (DistanceAndInLayer.m1796compareToS_HNhKs(jM1797constructorimpl, jDistanceAndInLayer) < 0) {
                    jDistanceAndInLayer = jM1797constructorimpl;
                }
                if (DistanceAndInLayer.m1798getDistanceimpl(jDistanceAndInLayer) < 0.0f && DistanceAndInLayer.m1799isInLayerimpl(jDistanceAndInLayer)) {
                    return jDistanceAndInLayer;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return jDistanceAndInLayer;
    }

    public final void hit(Modifier.Node node, boolean z, Function0 function0) {
        hitInMinimumTouchTarget(node, -1.0f, z, function0);
        NodeCoordinator coordinator$ui_release = node.getCoordinator$ui_release();
        if (coordinator$ui_release == null || coordinator$ui_release.shouldSharePointerInputWithSiblings()) {
            return;
        }
        this.shouldSharePointerInputWithSibling = false;
    }

    public final void hitInMinimumTouchTarget(Modifier.Node node, float f, boolean z, Function0 function0) {
        int i = this.hitDepth;
        this.hitDepth = i + 1;
        ensureContainerSize();
        Object[] objArr = this.values;
        int i2 = this.hitDepth;
        objArr[i2] = node;
        this.distanceFromEdgeAndInLayer[i2] = HitTestResultKt.DistanceAndInLayer(f, z);
        resizeToHitDepth();
        function0.invoke();
        this.hitDepth = i;
    }

    public final void speculativeHit(Modifier.Node node, float f, boolean z, Function0 function0) {
        if (this.hitDepth == CollectionsKt.getLastIndex(this)) {
            hitInMinimumTouchTarget(node, f, z, function0);
            if (this.hitDepth + 1 == CollectionsKt.getLastIndex(this)) {
                resizeToHitDepth();
                return;
            }
            return;
        }
        long jM1800findBestHitDistanceptXAw2c = m1800findBestHitDistanceptXAw2c();
        int i = this.hitDepth;
        this.hitDepth = CollectionsKt.getLastIndex(this);
        hitInMinimumTouchTarget(node, f, z, function0);
        if (this.hitDepth + 1 < CollectionsKt.getLastIndex(this) && DistanceAndInLayer.m1796compareToS_HNhKs(jM1800findBestHitDistanceptXAw2c, m1800findBestHitDistanceptXAw2c()) > 0) {
            int i2 = this.hitDepth + 1;
            int i3 = i + 1;
            Object[] objArr = this.values;
            ArraysKt.copyInto(objArr, objArr, i3, i2, size());
            long[] jArr = this.distanceFromEdgeAndInLayer;
            ArraysKt.copyInto(jArr, jArr, i3, i2, size());
            this.hitDepth = ((size() + i) - this.hitDepth) - 1;
        }
        resizeToHitDepth();
        this.hitDepth = i;
    }

    private final void ensureContainerSize() {
        int i = this.hitDepth;
        Object[] objArr = this.values;
        if (i >= objArr.length) {
            int length = objArr.length + 16;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, length);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.values = objArrCopyOf;
            long[] jArrCopyOf = Arrays.copyOf(this.distanceFromEdgeAndInLayer, length);
            Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(this, newSize)");
            this.distanceFromEdgeAndInLayer = jArrCopyOf;
        }
    }

    public boolean contains(Modifier.Node node) {
        return indexOf((Object) node) != -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public Modifier.Node get(int i) {
        Object obj = this.values[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.Modifier.Node");
        return (Modifier.Node) obj;
    }

    public int indexOf(Modifier.Node node) {
        int lastIndex = CollectionsKt.getLastIndex(this);
        if (lastIndex < 0) {
            return -1;
        }
        int i = 0;
        while (!Intrinsics.areEqual(this.values[i], node)) {
            if (i == lastIndex) {
                return -1;
            }
            i++;
        }
        return i;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new HitTestResultIterator(this, 0, 0, 0, 7, null);
    }

    public int lastIndexOf(Modifier.Node node) {
        for (int lastIndex = CollectionsKt.getLastIndex(this); -1 < lastIndex; lastIndex--) {
            if (Intrinsics.areEqual(this.values[lastIndex], node)) {
                return lastIndex;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return new HitTestResultIterator(this, 0, 0, 0, 7, null);
    }

    @Override // java.util.List
    public ListIterator listIterator(int i) {
        return new HitTestResultIterator(this, i, 0, 0, 6, null);
    }

    @Override // java.util.List
    public List subList(int i, int i2) {
        return new SubList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        this.hitDepth = -1;
        resizeToHitDepth();
        this.shouldSharePointerInputWithSibling = true;
    }

    /* JADX INFO: compiled from: HitTestResult.kt */
    private final class HitTestResultIterator implements ListIterator, KMappedMarker {
        private int index;
        private final int maxIndex;
        private final int minIndex;

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void add(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void set(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public HitTestResultIterator(int i, int i2, int i3) {
            this.index = i;
            this.minIndex = i2;
            this.maxIndex = i3;
        }

        public /* synthetic */ HitTestResultIterator(HitTestResult hitTestResult, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? hitTestResult.size() : i3);
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.index < this.maxIndex;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.index > this.minIndex;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public Modifier.Node next() {
            Object[] objArr = HitTestResult.this.values;
            int i = this.index;
            this.index = i + 1;
            Object obj = objArr[i];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.Modifier.Node");
            return (Modifier.Node) obj;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.index - this.minIndex;
        }

        @Override // java.util.ListIterator
        public Modifier.Node previous() {
            Object[] objArr = HitTestResult.this.values;
            int i = this.index - 1;
            this.index = i;
            Object obj = objArr[i];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.Modifier.Node");
            return (Modifier.Node) obj;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return (this.index - this.minIndex) - 1;
        }
    }

    /* JADX INFO: compiled from: HitTestResult.kt */
    private final class SubList implements List, KMappedMarker {
        private final int maxIndex;
        private final int minIndex;

        @Override // java.util.List
        public /* bridge */ /* synthetic */ void add(int i, Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public void clear() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List
        public /* bridge */ /* synthetic */ Object remove(int i) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public boolean removeAll(Collection collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List
        public void replaceAll(UnaryOperator unaryOperator) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public boolean retainAll(Collection collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List
        public /* bridge */ /* synthetic */ Object set(int i, Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List
        public void sort(Comparator comparator) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray(Object[] objArr) {
            return CollectionToArray.toArray(this, objArr);
        }

        public SubList(int i, int i2) {
            this.minIndex = i;
            this.maxIndex = i2;
        }

        @Override // java.util.List, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Modifier.Node) {
                return contains((Modifier.Node) obj);
            }
            return false;
        }

        @Override // java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Modifier.Node) {
                return indexOf((Modifier.Node) obj);
            }
            return -1;
        }

        @Override // java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Modifier.Node) {
                return lastIndexOf((Modifier.Node) obj);
            }
            return -1;
        }

        @Override // java.util.List, java.util.Collection
        public final /* bridge */ int size() {
            return getSize();
        }

        public int getSize() {
            return this.maxIndex - this.minIndex;
        }

        public boolean contains(Modifier.Node node) {
            return indexOf((Object) node) != -1;
        }

        @Override // java.util.List, java.util.Collection
        public boolean containsAll(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.List
        public Modifier.Node get(int i) {
            Object obj = HitTestResult.this.values[i + this.minIndex];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.Modifier.Node");
            return (Modifier.Node) obj;
        }

        public int indexOf(Modifier.Node node) {
            int i = this.minIndex;
            int i2 = this.maxIndex;
            if (i > i2) {
                return -1;
            }
            while (!Intrinsics.areEqual(HitTestResult.this.values[i], node)) {
                if (i == i2) {
                    return -1;
                }
                i++;
            }
            return i - this.minIndex;
        }

        @Override // java.util.List, java.util.Collection
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            HitTestResult hitTestResult = HitTestResult.this;
            int i = this.minIndex;
            return hitTestResult.new HitTestResultIterator(i, i, this.maxIndex);
        }

        public int lastIndexOf(Modifier.Node node) {
            int i = this.maxIndex;
            int i2 = this.minIndex;
            if (i2 > i) {
                return -1;
            }
            while (!Intrinsics.areEqual(HitTestResult.this.values[i], node)) {
                if (i == i2) {
                    return -1;
                }
                i--;
            }
            return i - this.minIndex;
        }

        @Override // java.util.List
        public ListIterator listIterator() {
            HitTestResult hitTestResult = HitTestResult.this;
            int i = this.minIndex;
            return hitTestResult.new HitTestResultIterator(i, i, this.maxIndex);
        }

        @Override // java.util.List
        public ListIterator listIterator(int i) {
            HitTestResult hitTestResult = HitTestResult.this;
            int i2 = this.minIndex;
            return hitTestResult.new HitTestResultIterator(i + i2, i2, this.maxIndex);
        }

        @Override // java.util.List
        public List subList(int i, int i2) {
            HitTestResult hitTestResult = HitTestResult.this;
            int i3 = this.minIndex;
            return hitTestResult.new SubList(i + i3, i3 + i2);
        }
    }
}
