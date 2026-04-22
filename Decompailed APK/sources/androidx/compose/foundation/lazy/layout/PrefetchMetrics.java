package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableObjectLongMap;
import androidx.collection.ObjectLongMapKt;

/* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PrefetchMetrics {
    private long averageCompositionTimeNanos;
    private long averageMeasureTimeNanos;
    private final MutableObjectLongMap averageCompositionTimeNanosByContentType = ObjectLongMapKt.mutableObjectLongMapOf();
    private final MutableObjectLongMap averageMeasureTimeNanosByContentType = ObjectLongMapKt.mutableObjectLongMapOf();

    public final MutableObjectLongMap getAverageCompositionTimeNanosByContentType() {
        return this.averageCompositionTimeNanosByContentType;
    }

    public final MutableObjectLongMap getAverageMeasureTimeNanosByContentType() {
        return this.averageMeasureTimeNanosByContentType;
    }

    public final long getAverageCompositionTimeNanos() {
        return this.averageCompositionTimeNanos;
    }

    public final long getAverageMeasureTimeNanos() {
        return this.averageMeasureTimeNanos;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long calculateAverageTime(long j, long j2) {
        if (j2 == 0) {
            return j;
        }
        long j3 = 4;
        return (j / j3) + ((j2 / j3) * ((long) 3));
    }
}
