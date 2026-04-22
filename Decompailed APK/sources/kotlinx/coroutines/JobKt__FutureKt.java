package kotlinx.coroutines;

import java.util.concurrent.Future;

/* JADX INFO: compiled from: Future.kt */
/* JADX INFO: loaded from: classes2.dex */
abstract /* synthetic */ class JobKt__FutureKt {
    public static final void cancelFutureOnCancellation(CancellableContinuation cancellableContinuation, Future future) {
        cancellableContinuation.invokeOnCancellation(new CancelFutureOnCancel(future));
    }
}
