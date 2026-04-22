package kotlin.collections;

import java.util.Arrays;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Arrays.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    public static boolean contentDeepEquals(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            Object obj2 = objArr2[i];
            if (obj != obj2) {
                if (obj == null || obj2 == null) {
                    return false;
                }
                if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
                    if (!ArraysKt.contentDeepEquals((Object[]) obj, (Object[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
                    if (!Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof short[]) && (obj2 instanceof short[])) {
                    if (!Arrays.equals((short[]) obj, (short[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof int[]) && (obj2 instanceof int[])) {
                    if (!Arrays.equals((int[]) obj, (int[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof long[]) && (obj2 instanceof long[])) {
                    if (!Arrays.equals((long[]) obj, (long[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof float[]) && (obj2 instanceof float[])) {
                    if (!Arrays.equals((float[]) obj, (float[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof double[]) && (obj2 instanceof double[])) {
                    if (!Arrays.equals((double[]) obj, (double[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof char[]) && (obj2 instanceof char[])) {
                    if (!Arrays.equals((char[]) obj, (char[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof boolean[]) && (obj2 instanceof boolean[])) {
                    if (!Arrays.equals((boolean[]) obj, (boolean[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof UByteArray) && (obj2 instanceof UByteArray)) {
                    if (!UArraysKt.m2740contentEqualskV0jMPg(((UByteArray) obj).m2668unboximpl(), ((UByteArray) obj2).m2668unboximpl())) {
                        return false;
                    }
                } else if ((obj instanceof UShortArray) && (obj2 instanceof UShortArray)) {
                    if (!UArraysKt.m2738contentEqualsFGO6Aew(((UShortArray) obj).m2735unboximpl(), ((UShortArray) obj2).m2735unboximpl())) {
                        return false;
                    }
                } else if ((obj instanceof UIntArray) && (obj2 instanceof UIntArray)) {
                    if (!UArraysKt.m2739contentEqualsKJPZfPQ(((UIntArray) obj).m2690unboximpl(), ((UIntArray) obj2).m2690unboximpl())) {
                        return false;
                    }
                } else if ((obj instanceof ULongArray) && (obj2 instanceof ULongArray)) {
                    if (!UArraysKt.m2741contentEqualslec5QzE(((ULongArray) obj).m2713unboximpl(), ((ULongArray) obj2).m2713unboximpl())) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(obj, obj2)) {
                    return false;
                }
            }
        }
        return true;
    }
}
