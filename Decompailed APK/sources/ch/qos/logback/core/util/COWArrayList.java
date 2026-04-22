package ch.qos.logback.core.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class COWArrayList implements List {
    final Object[] modelArray;
    Object[] ourCopy;
    AtomicBoolean fresh = new AtomicBoolean(false);
    CopyOnWriteArrayList<Object> underlyingList = new CopyOnWriteArrayList<>();

    public COWArrayList(Object[] objArr) {
        this.modelArray = objArr;
    }

    private boolean isFresh() {
        return this.fresh.get();
    }

    private void markAsStale() {
        this.fresh.set(false);
    }

    private void refreshCopy() {
        this.ourCopy = this.underlyingList.toArray(this.modelArray);
        this.fresh.set(true);
    }

    private void refreshCopyIfNecessary() {
        if (isFresh()) {
            return;
        }
        refreshCopy();
    }

    @Override // java.util.List
    public void add(int i, Object obj) {
        this.underlyingList.add(i, obj);
        markAsStale();
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        boolean zAdd = this.underlyingList.add(obj);
        markAsStale();
        return zAdd;
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<Object> collection) {
        boolean zAddAll = this.underlyingList.addAll(i, collection);
        markAsStale();
        return zAddAll;
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<Object> collection) {
        boolean zAddAll = this.underlyingList.addAll(collection);
        markAsStale();
        return zAddAll;
    }

    public void addIfAbsent(Object obj) {
        this.underlyingList.addIfAbsent(obj);
        markAsStale();
    }

    public Object[] asTypedArray() {
        refreshCopyIfNecessary();
        return this.ourCopy;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.underlyingList.clear();
        markAsStale();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.underlyingList.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.underlyingList.containsAll(collection);
    }

    @Override // java.util.List
    public Object get(int i) {
        refreshCopyIfNecessary();
        return this.ourCopy[i];
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.underlyingList.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.underlyingList.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.underlyingList.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.underlyingList.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator() {
        return this.underlyingList.listIterator();
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator(int i) {
        return this.underlyingList.listIterator(i);
    }

    @Override // java.util.List
    public Object remove(int i) {
        Object objRemove = this.underlyingList.remove(i);
        markAsStale();
        return objRemove;
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        boolean zRemove = this.underlyingList.remove(obj);
        markAsStale();
        return zRemove;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        boolean zRemoveAll = this.underlyingList.removeAll(collection);
        markAsStale();
        return zRemoveAll;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        boolean zRetainAll = this.underlyingList.retainAll(collection);
        markAsStale();
        return zRetainAll;
    }

    @Override // java.util.List
    public Object set(int i, Object obj) {
        Object obj2 = this.underlyingList.set(i, obj);
        markAsStale();
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.underlyingList.size();
    }

    @Override // java.util.List
    public List<Object> subList(int i, int i2) {
        return this.underlyingList.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        refreshCopyIfNecessary();
        return this.ourCopy;
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        refreshCopyIfNecessary();
        return (T[]) this.ourCopy;
    }
}
