package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public abstract class Lists {
    public static ArrayList newArrayList(Object... objArr) {
        Preconditions.checkNotNull(objArr);
        ArrayList arrayList = new ArrayList(computeArrayListCapacity(objArr.length));
        Collections.addAll(arrayList, objArr);
        return arrayList;
    }

    static int computeArrayListCapacity(int i) {
        CollectPreconditions.checkNonnegative(i, "arraySize");
        return Ints.saturatedCast(((long) i) + 5 + ((long) (i / 10)));
    }
}
