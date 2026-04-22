package kotlinx.coroutines.intrinsics;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;

/* JADX INFO: compiled from: Undispatched.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class UndispatchedKt {
    public static final void startCoroutineUndispatched(Function2 function2, Object obj, Continuation continuation) {
        Continuation continuationProbeCoroutineCreated = DebugProbesKt.probeCoroutineCreated(continuation);
        try {
            CoroutineContext context = continuation.getContext();
            Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(context, null);
            try {
                Object objInvoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(obj, continuationProbeCoroutineCreated);
                if (objInvoke != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    continuationProbeCoroutineCreated.resumeWith(Result.m2639constructorimpl(objInvoke));
                }
            } finally {
                ThreadContextKt.restoreThreadContext(context, objUpdateThreadContext);
            }
        } catch (Throwable th) {
            Result.Companion companion = Result.Companion;
            continuationProbeCoroutineCreated.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(th)));
        }
    }

    public static final Object startUndispatchedOrReturn(ScopeCoroutine scopeCoroutine, Object obj, Function2 function2) {
        Object completedExceptionally;
        Object objMakeCompletingOnce$kotlinx_coroutines_core;
        try {
            completedExceptionally = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(obj, scopeCoroutine);
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th, false, 2, null);
        }
        if (completedExceptionally != IntrinsicsKt.getCOROUTINE_SUSPENDED() && (objMakeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally)) != JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            if (objMakeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                throw ((CompletedExceptionally) objMakeCompletingOnce$kotlinx_coroutines_core).cause;
            }
            return JobSupportKt.unboxState(objMakeCompletingOnce$kotlinx_coroutines_core);
        }
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
}
