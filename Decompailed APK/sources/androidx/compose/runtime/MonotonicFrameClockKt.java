package androidx.compose.runtime;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: MonotonicFrameClock.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class MonotonicFrameClockKt {
    public static final Object withFrameNanos(Function1 function1, Continuation continuation) {
        return getMonotonicFrameClock(continuation.getContext()).withFrameNanos(function1, continuation);
    }

    public static final Object withFrameMillis(final Function1 function1, Continuation continuation) {
        return getMonotonicFrameClock(continuation.getContext()).withFrameNanos(new Function1() { // from class: androidx.compose.runtime.MonotonicFrameClockKt.withFrameMillis.2
            {
                super(1);
            }

            public final Object invoke(long j) {
                return function1.invoke(Long.valueOf(j / 1000000));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).longValue());
            }
        }, continuation);
    }

    public static final MonotonicFrameClock getMonotonicFrameClock(CoroutineContext coroutineContext) {
        MonotonicFrameClock monotonicFrameClock = (MonotonicFrameClock) coroutineContext.get(MonotonicFrameClock.Key);
        if (monotonicFrameClock != null) {
            return monotonicFrameClock;
        }
        throw new IllegalStateException("A MonotonicFrameClock is not available in this CoroutineContext. Callers should supply an appropriate MonotonicFrameClock using withContext.");
    }
}
