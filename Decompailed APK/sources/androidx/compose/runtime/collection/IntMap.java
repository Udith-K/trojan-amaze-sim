package androidx.compose.runtime.collection;

import android.util.SparseArray;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ActualIntMap.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IntMap {
    private final SparseArray sparseArray;

    private IntMap(SparseArray sparseArray) {
        this.sparseArray = sparseArray;
    }

    public IntMap(int i) {
        this(new SparseArray(i));
    }

    public /* synthetic */ IntMap(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }

    public final Object get(int i) {
        return this.sparseArray.get(i);
    }

    public final void set(int i, Object obj) {
        this.sparseArray.put(i, obj);
    }
}
