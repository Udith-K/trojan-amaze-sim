package io.ktor.util.pipeline;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SuspendFunctionGun.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SuspendFunctionGun$continuation$1 implements Continuation, CoroutineStackFrame {
    private int currentIndex = Integer.MIN_VALUE;
    final /* synthetic */ SuspendFunctionGun this$0;

    SuspendFunctionGun$continuation$1(SuspendFunctionGun suspendFunctionGun) {
        this.this$0 = suspendFunctionGun;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation continuationPeekContinuation = peekContinuation();
        if (continuationPeekContinuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuationPeekContinuation;
        }
        return null;
    }

    private final Continuation peekContinuation() {
        if (this.currentIndex == Integer.MIN_VALUE) {
            this.currentIndex = this.this$0.lastSuspensionIndex;
        }
        if (this.currentIndex >= 0) {
            try {
                Continuation[] continuationArr = this.this$0.suspensions;
                int i = this.currentIndex;
                Continuation continuation = continuationArr[i];
                if (continuation == null) {
                    return StackWalkingFailedFrame.INSTANCE;
                }
                this.currentIndex = i - 1;
                return continuation;
            } catch (Throwable unused) {
                return StackWalkingFailedFrame.INSTANCE;
            }
        }
        this.currentIndex = Integer.MIN_VALUE;
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        Continuation continuation = this.this$0.suspensions[this.this$0.lastSuspensionIndex];
        if (continuation == this || continuation == null) {
            int i = this.this$0.lastSuspensionIndex - 1;
            while (i >= 0) {
                int i2 = i - 1;
                Continuation continuation2 = this.this$0.suspensions[i];
                if (continuation2 != this && continuation2 != null) {
                    return continuation2.getContext();
                }
                i = i2;
            }
            throw new IllegalStateException("Not started");
        }
        return continuation.getContext();
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        if (!Result.m2644isFailureimpl(obj)) {
            this.this$0.loop(false);
            return;
        }
        SuspendFunctionGun suspendFunctionGun = this.this$0;
        Throwable thM2642exceptionOrNullimpl = Result.m2642exceptionOrNullimpl(obj);
        Intrinsics.checkNotNull(thM2642exceptionOrNullimpl);
        suspendFunctionGun.resumeRootWith(Result.m2639constructorimpl(ResultKt.createFailure(thM2642exceptionOrNullimpl)));
    }
}
