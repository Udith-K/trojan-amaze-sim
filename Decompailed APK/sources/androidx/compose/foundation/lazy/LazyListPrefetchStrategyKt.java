package androidx.compose.foundation.lazy;

/* JADX INFO: compiled from: LazyListPrefetchStrategy.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyListPrefetchStrategyKt {
    public static /* synthetic */ LazyListPrefetchStrategy LazyListPrefetchStrategy$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 2;
        }
        return LazyListPrefetchStrategy(i);
    }

    public static final LazyListPrefetchStrategy LazyListPrefetchStrategy(int i) {
        return new DefaultLazyListPrefetchStrategy(i);
    }
}
