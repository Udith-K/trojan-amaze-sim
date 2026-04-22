package androidx.collection;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IntList.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class IntListKt {
    private static final IntList EmptyIntList = new MutableIntList(0);

    public static final IntList intListOf(int... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        MutableIntList mutableIntList = new MutableIntList(elements.length);
        mutableIntList.plusAssign(elements);
        return mutableIntList;
    }
}
