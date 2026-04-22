package kotlin.collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Arrays.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ArraysKt___ArraysKt extends ArraysKt___ArraysJvmKt {
    public static boolean contains(Object[] objArr, Object obj) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return ArraysKt.indexOf(objArr, obj) >= 0;
    }

    public static boolean contains(byte[] bArr, byte b) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return indexOf(bArr, b) >= 0;
    }

    public static boolean contains(short[] sArr, short s) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return indexOf(sArr, s) >= 0;
    }

    public static boolean contains(int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return indexOf(iArr, i) >= 0;
    }

    public static boolean contains(long[] jArr, long j) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return indexOf(jArr, j) >= 0;
    }

    public static boolean contains(char[] cArr, char c) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return indexOf(cArr, c) >= 0;
    }

    public static Object first(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        if (objArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return objArr[0];
    }

    public static Object firstOrNull(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        if (objArr.length == 0) {
            return null;
        }
        return objArr[0];
    }

    public static Object getOrNull(Object[] objArr, int i) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        if (i < 0 || i >= objArr.length) {
            return null;
        }
        return objArr[i];
    }

    public static Integer getOrNull(int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        if (i < 0 || i >= iArr.length) {
            return null;
        }
        return Integer.valueOf(iArr[i]);
    }

    public static int indexOf(Object[] objArr, Object obj) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        int i = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i < length) {
                if (objArr[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i < length2) {
            if (Intrinsics.areEqual(obj, objArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static final int indexOf(byte[] bArr, byte b) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (b == bArr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOf(short[] sArr, short s) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            if (s == sArr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOf(int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i == iArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    public static final int indexOf(long[] jArr, long j) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            if (j == jArr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOf(char[] cArr, char c) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            if (c == cArr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static Object last(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        if (objArr.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        return objArr[ArraysKt.getLastIndex(objArr)];
    }

    public static int lastIndexOf(Object[] objArr, Object obj) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        if (obj == null) {
            int length = objArr.length - 1;
            if (length >= 0) {
                while (true) {
                    int i = length - 1;
                    if (objArr[length] == null) {
                        return length;
                    }
                    if (i < 0) {
                        break;
                    }
                    length = i;
                }
            }
        } else {
            int length2 = objArr.length - 1;
            if (length2 >= 0) {
                while (true) {
                    int i2 = length2 - 1;
                    if (Intrinsics.areEqual(obj, objArr[length2])) {
                        return length2;
                    }
                    if (i2 < 0) {
                        break;
                    }
                    length2 = i2;
                }
            }
        }
        return -1;
    }

    public static final int lastIndexOf(int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                if (i == iArr[length]) {
                    return length;
                }
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return -1;
    }

    public static Object single(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        int length = objArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return objArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static char single(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static Object singleOrNull(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        if (objArr.length == 1) {
            return objArr[0];
        }
        return null;
    }

    public static List drop(Object[] objArr, int i) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        }
        return takeLast(objArr, RangesKt.coerceAtLeast(objArr.length - i, 0));
    }

    public static List filterNotNull(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return (List) filterNotNullTo(objArr, new ArrayList());
    }

    public static final Collection filterNotNullTo(Object[] objArr, Collection destination) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (Object obj : objArr) {
            if (obj != null) {
                destination.add(obj);
            }
        }
        return destination;
    }

    public static final List takeLast(Object[] objArr, int i) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        }
        if (i == 0) {
            return CollectionsKt.emptyList();
        }
        int length = objArr.length;
        if (i >= length) {
            return ArraysKt.toList(objArr);
        }
        if (i == 1) {
            return CollectionsKt.listOf(objArr[length - 1]);
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = length - i; i2 < length; i2++) {
            arrayList.add(objArr[i2]);
        }
        return arrayList;
    }

    public static final Object[] sortedArrayWith(Object[] objArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (objArr.length == 0) {
            return objArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        ArraysKt___ArraysJvmKt.sortWith(objArrCopyOf, comparator);
        return objArrCopyOf;
    }

    public static List sortedWith(Object[] objArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return ArraysKt.asList(sortedArrayWith(objArr, comparator));
    }

    public static IntRange getIndices(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return new IntRange(0, ArraysKt.getLastIndex(objArr));
    }

    public static IntRange getIndices(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return new IntRange(0, ArraysKt.getLastIndex(iArr));
    }

    public static int getLastIndex(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return objArr.length - 1;
    }

    public static int getLastIndex(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length - 1;
    }

    public static int getLastIndex(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length - 1;
    }

    public static final Collection toCollection(Object[] objArr, Collection destination) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        for (Object obj : objArr) {
            destination.add(obj);
        }
        return destination;
    }

    public static List toList(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        int length = objArr.length;
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(objArr[0]);
        }
        return ArraysKt.toMutableList(objArr);
    }

    public static List toList(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int length = bArr.length;
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(Byte.valueOf(bArr[0]));
        }
        return toMutableList(bArr);
    }

    public static List toList(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        int length = sArr.length;
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(Short.valueOf(sArr[0]));
        }
        return toMutableList(sArr);
    }

    public static List toList(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(Integer.valueOf(iArr[0]));
        }
        return toMutableList(iArr);
    }

    public static List toList(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        int length = jArr.length;
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(Long.valueOf(jArr[0]));
        }
        return toMutableList(jArr);
    }

    public static List toList(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        int length = fArr.length;
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(Float.valueOf(fArr[0]));
        }
        return toMutableList(fArr);
    }

    public static List toList(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        int length = dArr.length;
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(Double.valueOf(dArr[0]));
        }
        return toMutableList(dArr);
    }

    public static List toList(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        int length = zArr.length;
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(Boolean.valueOf(zArr[0]));
        }
        return toMutableList(zArr);
    }

    public static List toList(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            return CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt.listOf(Character.valueOf(cArr[0]));
        }
        return toMutableList(cArr);
    }

    public static List toMutableList(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return new ArrayList(CollectionsKt__CollectionsKt.asCollection(objArr));
    }

    public static final List toMutableList(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        return arrayList;
    }

    public static final List toMutableList(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        ArrayList arrayList = new ArrayList(sArr.length);
        for (short s : sArr) {
            arrayList.add(Short.valueOf(s));
        }
        return arrayList;
    }

    public static final List toMutableList(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    public static final List toMutableList(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long j : jArr) {
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    public static final List toMutableList(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    public static final List toMutableList(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double d : dArr) {
            arrayList.add(Double.valueOf(d));
        }
        return arrayList;
    }

    public static final List toMutableList(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        ArrayList arrayList = new ArrayList(zArr.length);
        for (boolean z : zArr) {
            arrayList.add(Boolean.valueOf(z));
        }
        return arrayList;
    }

    public static final List toMutableList(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        ArrayList arrayList = new ArrayList(cArr.length);
        for (char c : cArr) {
            arrayList.add(Character.valueOf(c));
        }
        return arrayList;
    }

    public static Set toSet(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        int length = objArr.length;
        if (length == 0) {
            return SetsKt.emptySet();
        }
        if (length == 1) {
            return SetsKt.setOf(objArr[0]);
        }
        return (Set) toCollection(objArr, new LinkedHashSet(MapsKt.mapCapacity(objArr.length)));
    }

    public static List map(Object[] objArr, Function1 transform) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(transform.invoke(obj));
        }
        return arrayList;
    }

    public static Iterable withIndex(final Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return new IndexingIterable(new Function0() { // from class: kotlin.collections.ArraysKt___ArraysKt.withIndex.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Iterator invoke() {
                return ArrayIteratorKt.iterator(objArr);
            }
        });
    }

    public static List zip(Object[] objArr, Object[] other) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(objArr.length, other.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(TuplesKt.to(objArr[i], other[i]));
        }
        return arrayList;
    }

    public static List zip(Object[] objArr, Iterable other) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int length = objArr.length;
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), length));
        int i = 0;
        for (Object obj : other) {
            if (i >= length) {
                break;
            }
            arrayList.add(TuplesKt.to(objArr[i], obj));
            i++;
        }
        return arrayList;
    }

    public static final Appendable joinTo(Object[] objArr, Appendable buffer, CharSequence separator, CharSequence prefix, CharSequence postfix, int i, CharSequence truncated, Function1 function1) throws IOException {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (Object obj : objArr) {
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

    public static final Appendable joinTo(byte[] bArr, Appendable buffer, CharSequence separator, CharSequence prefix, CharSequence postfix, int i, CharSequence truncated, Function1 function1) throws IOException {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        buffer.append(prefix);
        int i2 = 0;
        for (byte b : bArr) {
            i2++;
            if (i2 > 1) {
                buffer.append(separator);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            if (function1 != null) {
                buffer.append((CharSequence) function1.invoke(Byte.valueOf(b)));
            } else {
                buffer.append(String.valueOf((int) b));
            }
        }
        if (i >= 0 && i2 > i) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static /* synthetic */ String joinToString$default(Object[] objArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        return joinToString(objArr, charSequence, charSequence5, charSequence6, i3, charSequence7, function1);
    }

    public static final String joinToString(Object[] objArr, CharSequence separator, CharSequence prefix, CharSequence postfix, int i, CharSequence truncated, Function1 function1) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String string = ((StringBuilder) joinTo(objArr, new StringBuilder(), separator, prefix, postfix, i, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public static /* synthetic */ String joinToString$default(byte[] bArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
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
        return joinToString(bArr, charSequence, charSequence5, charSequence6, i3, charSequence7, function1);
    }

    public static final String joinToString(byte[] bArr, CharSequence separator, CharSequence prefix, CharSequence postfix, int i, CharSequence truncated, Function1 function1) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(postfix, "postfix");
        Intrinsics.checkNotNullParameter(truncated, "truncated");
        String string = ((StringBuilder) joinTo(bArr, new StringBuilder(), separator, prefix, postfix, i, truncated, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public static Iterable asIterable(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return objArr.length == 0 ? CollectionsKt.emptyList() : new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$1(objArr);
    }

    public static Sequence asSequence(final Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return objArr.length == 0 ? SequencesKt.emptySequence() : new Sequence() { // from class: kotlin.collections.ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator iterator() {
                return ArrayIteratorKt.iterator(objArr);
            }
        };
    }
}
