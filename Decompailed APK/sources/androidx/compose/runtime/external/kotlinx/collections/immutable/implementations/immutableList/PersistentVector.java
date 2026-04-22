package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation;
import java.util.Arrays;
import java.util.ListIterator;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PersistentVector.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PersistentVector extends AbstractPersistentList implements PersistentList {
    private final Object[] root;
    private final int rootShift;
    private final int size;
    private final Object[] tail;

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this.size;
    }

    public PersistentVector(Object[] objArr, Object[] objArr2, int i, int i2) {
        this.root = objArr;
        this.tail = objArr2;
        this.size = i;
        this.rootShift = i2;
        if (!(size() > 32)) {
            PreconditionsKt.throwIllegalArgumentException("Trie-based persistent vector should have at least 33 elements, got " + size());
        }
        CommonFunctionsKt.m1053assert(size() - UtilsKt.rootSize(size()) <= RangesKt.coerceAtMost(objArr2.length, 32));
    }

    private final int rootSize() {
        return UtilsKt.rootSize(size());
    }

    @Override // java.util.Collection, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentList add(Object obj) {
        int size = size() - rootSize();
        if (size < 32) {
            Object[] objArrCopyOf = Arrays.copyOf(this.tail, 32);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            objArrCopyOf[size] = obj;
            return new PersistentVector(this.root, objArrCopyOf, size() + 1, this.rootShift);
        }
        return pushFilledTail(this.root, this.tail, UtilsKt.presizedBufferWith(obj));
    }

    private final PersistentVector pushFilledTail(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int size = size() >> 5;
        int i = this.rootShift;
        if (size > (1 << i)) {
            Object[] objArrPresizedBufferWith = UtilsKt.presizedBufferWith(objArr);
            int i2 = this.rootShift + 5;
            return new PersistentVector(pushTail(objArrPresizedBufferWith, i2, objArr2), objArr3, size() + 1, i2);
        }
        return new PersistentVector(pushTail(objArr, i, objArr2), objArr3, size() + 1, this.rootShift);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Object[] pushTail(java.lang.Object[] r4, int r5, java.lang.Object[] r6) {
        /*
            r3 = this;
            int r0 = r3.size()
            int r0 = r0 + (-1)
            int r0 = androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.UtilsKt.indexSegment(r0, r5)
            r1 = 32
            if (r4 == 0) goto L19
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r4, r1)
            java.lang.String r2 = "copyOf(this, newSize)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
            if (r4 != 0) goto L1b
        L19:
            java.lang.Object[] r4 = new java.lang.Object[r1]
        L1b:
            r1 = 5
            if (r5 != r1) goto L21
            r4[r0] = r6
            goto L2c
        L21:
            r2 = r4[r0]
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            int r5 = r5 - r1
            java.lang.Object[] r5 = r3.pushTail(r2, r5, r6)
            r4[r0] = r5
        L2c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVector.pushTail(java.lang.Object[], int, java.lang.Object[]):java.lang.Object[]");
    }

    @Override // java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentList add(int i, Object obj) {
        ListImplementation.checkPositionIndex$runtime_release(i, size());
        if (i == size()) {
            return add(obj);
        }
        int iRootSize = rootSize();
        if (i >= iRootSize) {
            return insertIntoTail(this.root, i - iRootSize, obj);
        }
        ObjectRef objectRef = new ObjectRef(null);
        return insertIntoTail(insertIntoRoot(this.root, this.rootShift, i, obj, objectRef), 0, objectRef.getValue());
    }

    private final PersistentVector insertIntoTail(Object[] objArr, int i, Object obj) {
        int size = size() - rootSize();
        Object[] objArrCopyOf = Arrays.copyOf(this.tail, 32);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        if (size < 32) {
            ArraysKt.copyInto(this.tail, objArrCopyOf, i + 1, i, size);
            objArrCopyOf[i] = obj;
            return new PersistentVector(objArr, objArrCopyOf, size() + 1, this.rootShift);
        }
        Object[] objArr2 = this.tail;
        Object obj2 = objArr2[31];
        ArraysKt.copyInto(objArr2, objArrCopyOf, i + 1, i, size - 1);
        objArrCopyOf[i] = obj;
        return pushFilledTail(objArr, objArrCopyOf, UtilsKt.presizedBufferWith(obj2));
    }

    private final Object[] insertIntoRoot(Object[] objArr, int i, int i2, Object obj, ObjectRef objectRef) {
        Object[] objArrCopyOf;
        int iIndexSegment = UtilsKt.indexSegment(i2, i);
        if (i == 0) {
            if (iIndexSegment != 0) {
                objArrCopyOf = Arrays.copyOf(objArr, 32);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            } else {
                objArrCopyOf = new Object[32];
            }
            ArraysKt.copyInto(objArr, objArrCopyOf, iIndexSegment + 1, iIndexSegment, 31);
            objectRef.setValue(objArr[31]);
            objArrCopyOf[iIndexSegment] = obj;
            return objArrCopyOf;
        }
        Object[] objArrCopyOf2 = Arrays.copyOf(objArr, 32);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf2, "copyOf(this, newSize)");
        int i3 = i - 5;
        Object obj2 = objArr[iIndexSegment];
        String str = "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>";
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        objArrCopyOf2[iIndexSegment] = insertIntoRoot((Object[]) obj2, i3, i2, obj, objectRef);
        int i4 = iIndexSegment + 1;
        while (i4 < 32 && objArrCopyOf2[i4] != null) {
            Object obj3 = objArr[i4];
            Intrinsics.checkNotNull(obj3, str);
            Object[] objArr2 = objArrCopyOf2;
            objArr2[i4] = insertIntoRoot((Object[]) obj3, i3, 0, objectRef.getValue(), objectRef);
            i4++;
            objArrCopyOf2 = objArr2;
            str = str;
        }
        return objArrCopyOf2;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentList removeAt(int i) {
        ListImplementation.checkElementIndex$runtime_release(i, size());
        int iRootSize = rootSize();
        if (i >= iRootSize) {
            return removeFromTailAt(this.root, iRootSize, this.rootShift, i - iRootSize);
        }
        return removeFromTailAt(removeFromRootAt(this.root, this.rootShift, i, new ObjectRef(this.tail[0])), iRootSize, this.rootShift, 0);
    }

    private final PersistentList removeFromTailAt(Object[] objArr, int i, int i2, int i3) {
        int size = size() - i;
        CommonFunctionsKt.m1053assert(i3 < size);
        if (size == 1) {
            return pullLastBufferFromRoot(objArr, i, i2);
        }
        Object[] objArrCopyOf = Arrays.copyOf(this.tail, 32);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        int i4 = size - 1;
        if (i3 < i4) {
            ArraysKt.copyInto(this.tail, objArrCopyOf, i3, i3 + 1, size);
        }
        objArrCopyOf[i4] = null;
        return new PersistentVector(objArr, objArrCopyOf, (i + size) - 1, i2);
    }

    private final PersistentList pullLastBufferFromRoot(Object[] objArr, int i, int i2) {
        if (i2 == 0) {
            if (objArr.length == 33) {
                objArr = Arrays.copyOf(objArr, 32);
                Intrinsics.checkNotNullExpressionValue(objArr, "copyOf(this, newSize)");
            }
            return new SmallPersistentVector(objArr);
        }
        ObjectRef objectRef = new ObjectRef(null);
        Object[] objArrPullLastBuffer = pullLastBuffer(objArr, i2, i - 1, objectRef);
        Intrinsics.checkNotNull(objArrPullLastBuffer);
        Object value = objectRef.getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr2 = (Object[]) value;
        if (objArrPullLastBuffer[1] == null) {
            Object obj = objArrPullLastBuffer[0];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            return new PersistentVector((Object[]) obj, objArr2, i, i2 - 5);
        }
        return new PersistentVector(objArrPullLastBuffer, objArr2, i, i2);
    }

    private final Object[] pullLastBuffer(Object[] objArr, int i, int i2, ObjectRef objectRef) {
        Object[] objArrPullLastBuffer;
        int iIndexSegment = UtilsKt.indexSegment(i2, i);
        if (i == 5) {
            objectRef.setValue(objArr[iIndexSegment]);
            objArrPullLastBuffer = null;
        } else {
            Object obj = objArr[iIndexSegment];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrPullLastBuffer = pullLastBuffer((Object[]) obj, i - 5, i2, objectRef);
        }
        if (objArrPullLastBuffer == null && iIndexSegment == 0) {
            return null;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, 32);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        objArrCopyOf[iIndexSegment] = objArrPullLastBuffer;
        return objArrCopyOf;
    }

    private final Object[] removeFromRootAt(Object[] objArr, int i, int i2, ObjectRef objectRef) {
        Object[] objArrCopyOf;
        int iIndexSegment = UtilsKt.indexSegment(i2, i);
        if (i == 0) {
            if (iIndexSegment != 0) {
                objArrCopyOf = Arrays.copyOf(objArr, 32);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            } else {
                objArrCopyOf = new Object[32];
            }
            ArraysKt.copyInto(objArr, objArrCopyOf, iIndexSegment, iIndexSegment + 1, 32);
            objArrCopyOf[31] = objectRef.getValue();
            objectRef.setValue(objArr[iIndexSegment]);
            return objArrCopyOf;
        }
        int iIndexSegment2 = objArr[31] == null ? UtilsKt.indexSegment(rootSize() - 1, i) : 31;
        Object[] objArrCopyOf2 = Arrays.copyOf(objArr, 32);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf2, "copyOf(this, newSize)");
        int i3 = i - 5;
        int i4 = iIndexSegment + 1;
        if (i4 <= iIndexSegment2) {
            while (true) {
                Object obj = objArrCopyOf2[iIndexSegment2];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                objArrCopyOf2[iIndexSegment2] = removeFromRootAt((Object[]) obj, i3, 0, objectRef);
                if (iIndexSegment2 == i4) {
                    break;
                }
                iIndexSegment2--;
            }
        }
        Object obj2 = objArrCopyOf2[iIndexSegment];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        objArrCopyOf2[iIndexSegment] = removeFromRootAt((Object[]) obj2, i3, i2, objectRef);
        return objArrCopyOf2;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentList removeAll(Function1 function1) {
        PersistentVectorBuilder persistentVectorBuilderBuilder = builder();
        persistentVectorBuilderBuilder.removeAllWithPredicate(function1);
        return persistentVectorBuilderBuilder.build();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentVectorBuilder builder() {
        return new PersistentVectorBuilder(this, this.root, this.tail, this.rootShift);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public ListIterator listIterator(int i) {
        ListImplementation.checkPositionIndex$runtime_release(i, size());
        return new PersistentVectorIterator(this.root, this.tail, i, size(), (this.rootShift / 5) + 1);
    }

    private final Object[] bufferFor(int i) {
        if (rootSize() <= i) {
            return this.tail;
        }
        Object[] objArr = this.root;
        for (int i2 = this.rootShift; i2 > 0; i2 -= 5) {
            Object[] objArr2 = objArr[UtilsKt.indexSegment(i, i2)];
            Intrinsics.checkNotNull(objArr2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArr = objArr2;
        }
        return objArr;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Object get(int i) {
        ListImplementation.checkElementIndex$runtime_release(i, size());
        return bufferFor(i)[i & 31];
    }

    @Override // kotlin.collections.AbstractList, java.util.List, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList
    public PersistentList set(int i, Object obj) {
        ListImplementation.checkElementIndex$runtime_release(i, size());
        if (rootSize() <= i) {
            Object[] objArrCopyOf = Arrays.copyOf(this.tail, 32);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            objArrCopyOf[i & 31] = obj;
            return new PersistentVector(this.root, objArrCopyOf, size(), this.rootShift);
        }
        return new PersistentVector(setInRoot(this.root, this.rootShift, i, obj), this.tail, size(), this.rootShift);
    }

    private final Object[] setInRoot(Object[] objArr, int i, int i2, Object obj) {
        int iIndexSegment = UtilsKt.indexSegment(i2, i);
        Object[] objArrCopyOf = Arrays.copyOf(objArr, 32);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        if (i == 0) {
            objArrCopyOf[iIndexSegment] = obj;
        } else {
            Object obj2 = objArrCopyOf[iIndexSegment];
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrCopyOf[iIndexSegment] = setInRoot((Object[]) obj2, i - 5, i2, obj);
        }
        return objArrCopyOf;
    }
}
