package kotlinx.coroutines.internal;

/* JADX INFO: compiled from: LimitedDispatcher.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class LimitedDispatcherKt {
    public static final void checkParallelism(int i) {
        if (i >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Expected positive parallelism level, but got " + i).toString());
    }
}
