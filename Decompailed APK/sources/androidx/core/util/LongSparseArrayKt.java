package androidx.core.util;

import android.util.LongSparseArray;
import kotlin.collections.LongIterator;

/* JADX INFO: compiled from: LongSparseArray.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LongSparseArrayKt {
    public static final LongIterator keyIterator(final LongSparseArray longSparseArray) {
        return new LongIterator() { // from class: androidx.core.util.LongSparseArrayKt.keyIterator.1
            private int index;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < longSparseArray.size();
            }

            @Override // kotlin.collections.LongIterator
            public long nextLong() {
                LongSparseArray longSparseArray2 = longSparseArray;
                int i = this.index;
                this.index = i + 1;
                return longSparseArray2.keyAt(i);
            }
        };
    }
}
