package kotlin.collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Collections.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CollectionsKt___CollectionsKt extends CollectionsKt___CollectionsJvmKt {
    public static boolean contains(Iterable iterable, Object obj) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(obj);
        }
        return indexOf(iterable, obj) >= 0;
    }

    public static Object first(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return CollectionsKt.first((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        return it.next();
    }

    public static Object first(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    public static Object firstOrNull(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static Object firstOrNull(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static Object getOrNull(List list, int i) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    public static final int indexOf(Iterable iterable, Object obj) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return ((List) iterable).indexOf(obj);
        }
        int i = 0;
        for (Object obj2 : iterable) {
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual(obj, obj2)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static Object last(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return CollectionsKt.last((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static Object last(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(CollectionsKt.getLastIndex(list));
    }

    public static Object lastOrNull(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return list.get(list.size() - 1);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public static Object lastOrNull(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static Object single(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            return CollectionsKt.single((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        Object next = it.next();
        if (it.hasNext()) {
            throw new IllegalArgumentException("Collection has more than one element.");
        }
        return next;
    }

    public static Object single(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int size = list.size();
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (size == 1) {
            return list.get(0);
        }
        throw new IllegalArgumentException("List has more than one element.");
    }

    public static Object singleOrNull(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.size() == 1) {
                return list.get(0);
            }
            return null;
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        if (it.hasNext()) {
            return null;
        }
        return next;
    }

    public static Object singleOrNull(List list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public static List drop(Iterable iterable, int i) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        }
        if (i == 0) {
            return CollectionsKt.toList(iterable);
        }
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size() - i;
            if (size <= 0) {
                return CollectionsKt.emptyList();
            }
            if (size == 1) {
                return CollectionsKt.listOf(CollectionsKt.last(iterable));
            }
            arrayList = new ArrayList(size);
            if (iterable instanceof List) {
                if (iterable instanceof RandomAccess) {
                    int size2 = collection.size();
                    while (i < size2) {
                        arrayList.add(((List) iterable).get(i));
                        i++;
                    }
                } else {
                    ListIterator listIterator = ((List) iterable).listIterator(i);
                    while (listIterator.hasNext()) {
                        arrayList.add(listIterator.next());
                    }
                }
                return arrayList;
            }
        } else {
            arrayList = new ArrayList();
        }
        int i2 = 0;
        for (Object obj : iterable) {
            if (i2 >= i) {
                arrayList.add(obj);
            } else {
                i2++;
            }
        }
        return CollectionsKt__CollectionsKt.optimizeReadOnlyList(arrayList);
    }

    public static List dropLast(List list, int i) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        }
        return take(list, RangesKt.coerceAtLeast(list.size() - i, 0));
    }

    public static List filter(Iterable iterable, Function1 predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            if (((Boolean) predicate.invoke(obj)).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static List slice(List list, IntRange indices) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(indices, "indices");
        return indices.isEmpty() ? CollectionsKt.emptyList() : CollectionsKt.toList(list.subList(indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1));
    }

    public static final List take(Iterable iterable, int i) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        }
        if (i == 0) {
            return CollectionsKt.emptyList();
        }
        if (iterable instanceof Collection) {
            if (i >= ((Collection) iterable).size()) {
                return CollectionsKt.toList(iterable);
            }
            if (i == 1) {
                return CollectionsKt.listOf(CollectionsKt.first(iterable));
            }
        }
        ArrayList arrayList = new ArrayList(i);
        Iterator it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return CollectionsKt__CollectionsKt.optimizeReadOnlyList(arrayList);
    }

    public static List takeLast(List list, int i) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        }
        if (i == 0) {
            return CollectionsKt.emptyList();
        }
        int size = list.size();
        if (i >= size) {
            return CollectionsKt.toList(list);
        }
        if (i == 1) {
            return CollectionsKt.listOf(CollectionsKt.last(list));
        }
        ArrayList arrayList = new ArrayList(i);
        if (list instanceof RandomAccess) {
            for (int i2 = size - i; i2 < size; i2++) {
                arrayList.add(list.get(i2));
            }
        } else {
            ListIterator listIterator = list.listIterator(size - i);
            while (listIterator.hasNext()) {
                arrayList.add(listIterator.next());
            }
        }
        return arrayList;
    }

    public static List reversed(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if ((iterable instanceof Collection) && ((Collection) iterable).size() <= 1) {
            return CollectionsKt.toList(iterable);
        }
        List mutableList = toMutableList(iterable);
        CollectionsKt.reverse(mutableList);
        return mutableList;
    }

    public static List sorted(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= 1) {
                return CollectionsKt.toList(iterable);
            }
            Object[] array = collection.toArray(new Comparable[0]);
            ArraysKt.sort((Comparable[]) array);
            return ArraysKt.asList(array);
        }
        List mutableList = toMutableList(iterable);
        CollectionsKt.sort(mutableList);
        return mutableList;
    }

    public static List sortedWith(Iterable iterable, Comparator comparator) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= 1) {
                return CollectionsKt.toList(iterable);
            }
            Object[] array = collection.toArray(new Object[0]);
            ArraysKt___ArraysJvmKt.sortWith(array, comparator);
            return ArraysKt.asList(array);
        }
        List mutableList = toMutableList(iterable);
        CollectionsKt.sortWith(mutableList, comparator);
        return mutableList;
    }

    public static boolean[] toBooleanArray(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        boolean[] zArr = new boolean[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            zArr[i] = ((Boolean) it.next()).booleanValue();
            i++;
        }
        return zArr;
    }

    public static byte[] toByteArray(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        byte[] bArr = new byte[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            bArr[i] = ((Number) it.next()).byteValue();
            i++;
        }
        return bArr;
    }

    public static int[] toIntArray(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        int[] iArr = new int[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = ((Number) it.next()).intValue();
            i++;
        }
        return iArr;
    }

    public static long[] toLongArray(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        long[] jArr = new long[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            jArr[i] = ((Number) it.next()).longValue();
            i++;
        }
        return jArr;
    }

    public static Collection toCollection(Iterable iterable, Collection destination) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            destination.add(it.next());
        }
        return destination;
    }

    public static HashSet toHashSet(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return (HashSet) CollectionsKt.toCollection(iterable, new HashSet(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 12))));
    }

    public static List toList(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                return CollectionsKt.emptyList();
            }
            if (size != 1) {
                return CollectionsKt.toMutableList(collection);
            }
            return CollectionsKt.listOf(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
        }
        return CollectionsKt__CollectionsKt.optimizeReadOnlyList(toMutableList(iterable));
    }

    public static final List toMutableList(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return CollectionsKt.toMutableList((Collection) iterable);
        }
        return (List) CollectionsKt.toCollection(iterable, new ArrayList());
    }

    public static List toMutableList(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        return new ArrayList(collection);
    }

    public static Set toSet(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size == 0) {
                return SetsKt.emptySet();
            }
            if (size != 1) {
                return (Set) CollectionsKt.toCollection(iterable, new LinkedHashSet(MapsKt.mapCapacity(collection.size())));
            }
            return SetsKt.setOf(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
        }
        return SetsKt__SetsKt.optimizeReadOnlySet((Set) CollectionsKt.toCollection(iterable, new LinkedHashSet()));
    }

    public static List map(Iterable iterable, Function1 transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(transform.invoke(it.next()));
        }
        return arrayList;
    }

    public static Iterable withIndex(final Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.CollectionsKt___CollectionsKt.withIndex.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Iterator invoke() {
                return iterable.iterator();
            }
        });
    }

    public static List distinct(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return CollectionsKt.toList(CollectionsKt.toMutableSet(iterable));
    }

    public static Set intersect(Iterable iterable, Iterable other) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set mutableSet = CollectionsKt.toMutableSet(iterable);
        CollectionsKt__MutableCollectionsKt.retainAll(mutableSet, other);
        return mutableSet;
    }

    public static Set toMutableSet(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return iterable instanceof Collection ? new LinkedHashSet((Collection) iterable) : (Set) CollectionsKt.toCollection(iterable, new LinkedHashSet());
    }

    public static Set union(Iterable iterable, Iterable other) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Set mutableSet = CollectionsKt.toMutableSet(iterable);
        CollectionsKt.addAll(mutableSet, other);
        return mutableSet;
    }

    public static boolean all(Iterable iterable, Function1 predicate) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            if (!((Boolean) predicate.invoke(it.next())).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static Comparable minOrNull(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Comparable comparable = (Comparable) it.next();
        while (it.hasNext()) {
            Comparable comparable2 = (Comparable) it.next();
            if (comparable.compareTo(comparable2) > 0) {
                comparable = comparable2;
            }
        }
        return comparable;
    }

    public static List chunked(Iterable iterable, int i) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return windowed(iterable, i, i, true);
    }

    public static List chunked(Iterable iterable, int i, Function1 transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return windowed(iterable, i, i, true, transform);
    }

    public static List minus(Iterable iterable, Object obj) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        boolean z = false;
        for (Object obj2 : iterable) {
            boolean z2 = true;
            if (!z && Intrinsics.areEqual(obj2, obj)) {
                z = true;
                z2 = false;
            }
            if (z2) {
                arrayList.add(obj2);
            }
        }
        return arrayList;
    }

    public static List plus(Iterable iterable, Object obj) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        if (iterable instanceof Collection) {
            return CollectionsKt.plus((Collection) iterable, obj);
        }
        ArrayList arrayList = new ArrayList();
        CollectionsKt.addAll(arrayList, iterable);
        arrayList.add(obj);
        return arrayList;
    }

    public static List plus(Collection collection, Object obj) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(obj);
        return arrayList;
    }

    public static List plus(Iterable iterable, Iterable elements) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (iterable instanceof Collection) {
            return CollectionsKt.plus((Collection) iterable, elements);
        }
        ArrayList arrayList = new ArrayList();
        CollectionsKt.addAll(arrayList, iterable);
        CollectionsKt.addAll(arrayList, elements);
        return arrayList;
    }

    public static List plus(Collection collection, Iterable elements) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            Collection collection2 = (Collection) elements;
            ArrayList arrayList = new ArrayList(collection.size() + collection2.size());
            arrayList.addAll(collection);
            arrayList.addAll(collection2);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(collection);
        CollectionsKt.addAll(arrayList2, elements);
        return arrayList2;
    }

    public static final List windowed(Iterable iterable, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        if ((iterable instanceof RandomAccess) && (iterable instanceof List)) {
            List list = (List) iterable;
            int size = list.size();
            ArrayList arrayList = new ArrayList((size / i2) + (size % i2 == 0 ? 0 : 1));
            int i3 = 0;
            while (i3 >= 0 && i3 < size) {
                int iCoerceAtMost = RangesKt.coerceAtMost(i, size - i3);
                if (iCoerceAtMost < i && !z) {
                    break;
                }
                ArrayList arrayList2 = new ArrayList(iCoerceAtMost);
                for (int i4 = 0; i4 < iCoerceAtMost; i4++) {
                    arrayList2.add(list.get(i4 + i3));
                }
                arrayList.add(arrayList2);
                i3 += i2;
            }
            return arrayList;
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator itWindowedIterator = SlidingWindowKt.windowedIterator(iterable.iterator(), i, i2, z, false);
        while (itWindowedIterator.hasNext()) {
            arrayList3.add((List) itWindowedIterator.next());
        }
        return arrayList3;
    }

    public static final List windowed(Iterable iterable, int i, int i2, boolean z, Function1 transform) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        SlidingWindowKt.checkWindowSizeStep(i, i2);
        if ((iterable instanceof RandomAccess) && (iterable instanceof List)) {
            List list = (List) iterable;
            int size = list.size();
            int i3 = 0;
            ArrayList arrayList = new ArrayList((size / i2) + (size % i2 == 0 ? 0 : 1));
            MovingSubList movingSubList = new MovingSubList(list);
            while (i3 >= 0 && i3 < size) {
                int iCoerceAtMost = RangesKt.coerceAtMost(i, size - i3);
                if (!z && iCoerceAtMost < i) {
                    break;
                }
                movingSubList.move(i3, iCoerceAtMost + i3);
                arrayList.add(transform.invoke(movingSubList));
                i3 += i2;
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator itWindowedIterator = SlidingWindowKt.windowedIterator(iterable.iterator(), i, i2, z, true);
        while (itWindowedIterator.hasNext()) {
            arrayList2.add(transform.invoke((List) itWindowedIterator.next()));
        }
        return arrayList2;
    }

    public static List zip(Iterable iterable, Iterable other) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Iterator it = iterable.iterator();
        Iterator it2 = other.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(iterable, 10), CollectionsKt.collectionSizeOrDefault(other, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(TuplesKt.to(it.next(), it2.next()));
        }
        return arrayList;
    }

    public static final Appendable joinTo(Iterable iterable, Appendable buffer, CharSequence separator, CharSequence prefix, CharSequence postfix, int i, CharSequence truncated, Function1 function1) throws IOException {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (Object obj : iterable) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            StringsKt.appendElement(buffer, obj, function1);
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static /* synthetic */ String joinToString$default(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i2 & 2) != 0 ? "" : charSequence2;
        CharSequence charSequence6 = (i2 & 4) == 0 ? charSequence3 : "";
        if ((i2 & 8) != 0) {
            i = -1;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        return joinToString(iterable, charSequence, charSequence5, charSequence6, i3, charSequence7, function1);
    }

    public static final String joinToString(Iterable iterable, CharSequence separator, CharSequence prefix, CharSequence postfix, int i, CharSequence truncated, Function1 function1) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String string = ((StringBuilder) joinTo(iterable, new StringBuilder(), separator, prefix, postfix, i, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public static Sequence asSequence(final Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        return new Sequence() { // from class: kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator iterator() {
                return iterable.iterator();
            }
        };
    }
}
