package kotlinx.coroutines.intrinsics;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

/* JADX INFO: compiled from: Cancellable.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class CancellableKt {
    public static /* synthetic */ void startCoroutineCancellable$default(Function2 function2, Object obj, Continuation continuation, Function1 function1, int i, Object obj2) throws Throwable {
        if ((i & 4) != 0) {
            function1 = null;
        }
        startCoroutineCancellable(function2, obj, continuation, function1);
    }

    public static final void startCoroutineCancellable(Function2 function2, Object obj, Continuation continuation, Function1 function1) throws Throwable {
        try {
            Continuation continuationIntercepted = IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(function2, obj, continuation));
            Result.Companion companion = Result.Companion;
            DispatchedContinuationKt.resumeCancellableWith(continuationIntercepted, Result.m2639constructorimpl(Unit.INSTANCE), function1);
        } catch (Throwable th) {
            dispatcherFailure(continuation, th);
        }
    }

    public static final void startCoroutineCancellable(Continuation continuation, Continuation continuation2) throws Throwable {
        try {
            Continuation continuationIntercepted = IntrinsicsKt.intercepted(continuation);
            Result.Companion companion = Result.Companion;
            DispatchedContinuationKt.resumeCancellableWith$default(continuationIntercepted, Result.m2639constructorimpl(Unit.INSTANCE), null, 2, null);
        } catch (Throwable th) {
            dispatcherFailure(continuation2, th);
        }
    }

    private static final void dispatcherFailure(Continuation continuation, Throwable th) throws Throwable {
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(th)));
        throw th;
    }
}
