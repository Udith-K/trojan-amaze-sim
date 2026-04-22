package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

/* JADX INFO: loaded from: classes2.dex */
public abstract class JobKt {
    public static final CompletableJob Job(Job job) {
        return JobKt__JobKt.Job(job);
    }

    public static final void cancel(CoroutineContext coroutineContext, CancellationException cancellationException) {
        JobKt__JobKt.cancel(coroutineContext, cancellationException);
    }

    public static final void cancel(Job job, String str, Throwable th) {
        JobKt__JobKt.cancel(job, str, th);
    }

    public static final Object cancelAndJoin(Job job, Continuation continuation) {
        return JobKt__JobKt.cancelAndJoin(job, continuation);
    }

    public static final void cancelFutureOnCancellation(CancellableContinuation cancellableContinuation, Future future) {
        JobKt__FutureKt.cancelFutureOnCancellation(cancellableContinuation, future);
    }

    public static final void ensureActive(CoroutineContext coroutineContext) {
        JobKt__JobKt.ensureActive(coroutineContext);
    }

    public static final void ensureActive(Job job) {
        JobKt__JobKt.ensureActive(job);
    }

    public static final Job getJob(CoroutineContext coroutineContext) {
        return JobKt__JobKt.getJob(coroutineContext);
    }

    public static final boolean isActive(CoroutineContext coroutineContext) {
        return JobKt__JobKt.isActive(coroutineContext);
    }
}
