package com.google.common.collect;

import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public abstract class ObjectArrays {
    public static Object[] newArray(Object[] objArr, int i) {
        return Platform.newArray(objArr, i);
    }

    static Object[] toArrayImpl(Object[] objArr, int i, int i2, Object[] objArr2) {
        Preconditions.checkPositionIndexes(i, i + i2, objArr.length);
        if (objArr2.length < i2) {
            objArr2 = newArray(objArr2, i2);
        } else if (objArr2.length > i2) {
            objArr2[i2] = null;
        }
        System.arraycopy(objArr, i, objArr2, 0, i2);
        return objArr2;
    }
}
