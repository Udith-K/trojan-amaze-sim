package androidx.compose.foundation.lazy.layout;

import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: IntervalList.kt */
/* JADX INFO: loaded from: classes.dex */
public interface IntervalList {
    void forEach(int i, int i2, Function1 function1);

    Interval get(int i);

    int getSize();

    /* JADX INFO: compiled from: IntervalList.kt */
    public static final class Interval {
        private final int size;
        private final int startIndex;
        private final Object value;

        public Interval(int i, int i2, Object obj) {
            this.startIndex = i;
            this.size = i2;
            this.value = obj;
            if (i < 0) {
                throw new IllegalArgumentException(("startIndex should be >= 0, but was " + i).toString());
            }
            if (i2 > 0) {
                return;
            }
            throw new IllegalArgumentException(("size should be >0, but was " + i2).toString());
        }

        public final int getStartIndex() {
            return this.startIndex;
        }

        public final int getSize() {
            return this.size;
        }

        public final Object getValue() {
            return this.value;
        }
    }
}
