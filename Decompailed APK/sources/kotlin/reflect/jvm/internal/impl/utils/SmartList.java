package kotlin.reflect.jvm.internal.impl.utils;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes2.dex */
public class SmartList extends AbstractList implements RandomAccess {
    private Object myElem;
    private int mySize;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 5 || i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 5 || i == 6 || i == 7) ? 2 : 3];
        switch (i) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
                break;
            case 4:
                objArr[0] = "a";
                break;
            default:
                objArr[0] = "elements";
                break;
        }
        if (i == 2 || i == 3) {
            objArr[1] = "iterator";
        } else if (i == 5 || i == 6 || i == 7) {
            objArr[1] = "toArray";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/SmartList";
        }
        switch (i) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
                break;
            case 4:
                objArr[2] = "toArray";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 5 && i != 6 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i) {
        int i2;
        if (i >= 0 && i < (i2 = this.mySize)) {
            if (i2 == 1) {
                return this.myElem;
            }
            return ((Object[]) this.myElem)[i];
        }
        throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + this.mySize);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        int i = this.mySize;
        if (i == 0) {
            this.myElem = obj;
        } else if (i == 1) {
            this.myElem = new Object[]{this.myElem, obj};
        } else {
            Object[] objArr = (Object[]) this.myElem;
            int length = objArr.length;
            if (i >= length) {
                int i2 = ((length * 3) / 2) + 1;
                int i3 = i + 1;
                if (i2 < i3) {
                    i2 = i3;
                }
                Object[] objArr2 = new Object[i2];
                this.myElem = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, length);
                objArr = objArr2;
            }
            objArr[this.mySize] = obj;
        }
        this.mySize++;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, Object obj) {
        int i2;
        if (i < 0 || i > (i2 = this.mySize)) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + this.mySize);
        }
        if (i2 == 0) {
            this.myElem = obj;
        } else if (i2 == 1 && i == 0) {
            this.myElem = new Object[]{obj, this.myElem};
        } else {
            Object[] objArr = new Object[i2 + 1];
            if (i2 == 1) {
                objArr[0] = this.myElem;
            } else {
                Object[] objArr2 = (Object[]) this.myElem;
                System.arraycopy(objArr2, 0, objArr, 0, i);
                System.arraycopy(objArr2, i, objArr, i + 1, this.mySize - i);
            }
            objArr[i] = obj;
            this.myElem = objArr;
        }
        this.mySize++;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.mySize;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.myElem = null;
        this.mySize = 0;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i, Object obj) {
        int i2;
        if (i < 0 || i >= (i2 = this.mySize)) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + this.mySize);
        }
        if (i2 == 1) {
            Object obj2 = this.myElem;
            this.myElem = obj;
            return obj2;
        }
        Object[] objArr = (Object[]) this.myElem;
        Object obj3 = objArr[i];
        objArr[i] = obj;
        return obj3;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object remove(int i) {
        int i2;
        Object obj;
        if (i < 0 || i >= (i2 = this.mySize)) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + this.mySize);
        }
        if (i2 == 1) {
            obj = this.myElem;
            this.myElem = null;
        } else {
            Object[] objArr = (Object[]) this.myElem;
            Object obj2 = objArr[i];
            if (i2 == 2) {
                this.myElem = objArr[1 - i];
            } else {
                int i3 = (i2 - i) - 1;
                if (i3 > 0) {
                    System.arraycopy(objArr, i + 1, objArr, i, i3);
                }
                objArr[this.mySize - 1] = null;
            }
            obj = obj2;
        }
        this.mySize--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    private static class EmptyIterator implements Iterator {
        private static final EmptyIterator INSTANCE = new EmptyIterator();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        private EmptyIterator() {
        }

        public static EmptyIterator getInstance() {
            return INSTANCE;
        }

        @Override // java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        int i = this.mySize;
        if (i == 0) {
            EmptyIterator emptyIterator = EmptyIterator.getInstance();
            if (emptyIterator == null) {
                $$$reportNull$$$0(2);
            }
            return emptyIterator;
        }
        if (i == 1) {
            return new SingletonIterator();
        }
        Iterator it = super.iterator();
        if (it == null) {
            $$$reportNull$$$0(3);
        }
        return it;
    }

    private static abstract class SingletonIteratorBase implements Iterator {
        private boolean myVisited;

        protected abstract void checkCoModification();

        protected abstract Object getElement();

        private SingletonIteratorBase() {
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return !this.myVisited;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (this.myVisited) {
                throw new NoSuchElementException();
            }
            this.myVisited = true;
            checkCoModification();
            return getElement();
        }
    }

    private class SingletonIterator extends SingletonIteratorBase {
        private final int myInitialModCount;

        public SingletonIterator() {
            super();
            this.myInitialModCount = ((AbstractList) SmartList.this).modCount;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.SmartList.SingletonIteratorBase
        protected Object getElement() {
            return SmartList.this.myElem;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.SmartList.SingletonIteratorBase
        protected void checkCoModification() {
            if (((AbstractList) SmartList.this).modCount == this.myInitialModCount) {
                return;
            }
            throw new ConcurrentModificationException("ModCount: " + ((AbstractList) SmartList.this).modCount + "; expected: " + this.myInitialModCount);
        }

        @Override // java.util.Iterator
        public void remove() {
            checkCoModification();
            SmartList.this.clear();
        }
    }

    @Override // java.util.List
    public void sort(Comparator comparator) {
        int i = this.mySize;
        if (i >= 2) {
            Arrays.sort((Object[]) this.myElem, 0, i, comparator);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] objArr) {
        if (objArr == null) {
            $$$reportNull$$$0(4);
        }
        int length = objArr.length;
        int i = this.mySize;
        if (i == 1) {
            if (length != 0) {
                objArr[0] = this.myElem;
            } else {
                Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), 1);
                objArr2[0] = this.myElem;
                return objArr2;
            }
        } else {
            if (length < i) {
                Object[] objArrCopyOf = Arrays.copyOf((Object[]) this.myElem, i, objArr.getClass());
                if (objArrCopyOf == null) {
                    $$$reportNull$$$0(6);
                }
                return objArrCopyOf;
            }
            if (i != 0) {
                System.arraycopy(this.myElem, 0, objArr, 0, i);
            }
        }
        int i2 = this.mySize;
        if (length > i2) {
            objArr[i2] = null;
        }
        return objArr;
    }
}
