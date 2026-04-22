package io.ktor.util.pipeline;

import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SuspendFunctionGun.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SuspendFunctionGun extends PipelineContext {
    private final List blocks;
    private final Continuation continuation;
    private int index;
    private int lastSuspensionIndex;
    private Object subject;
    private final Continuation[] suspensions;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuspendFunctionGun(Object initial, Object context, List blocks) {
        super(context);
        Intrinsics.checkNotNullParameter(initial, "initial");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(blocks, "blocks");
        this.blocks = blocks;
        this.continuation = new SuspendFunctionGun$continuation$1(this);
        this.subject = initial;
        this.suspensions = new Continuation[blocks.size()];
        this.lastSuspensionIndex = -1;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.continuation.getContext();
    }

    public Object getSubject() {
        return this.subject;
    }

    public void setSubject(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.subject = obj;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceed(Continuation continuation) {
        Object coroutine_suspended;
        if (this.index == this.blocks.size()) {
            coroutine_suspended = getSubject();
        } else {
            addContinuation$ktor_utils(IntrinsicsKt.intercepted(continuation));
            if (loop(true)) {
                discardLastRootContinuation();
                coroutine_suspended = getSubject();
            } else {
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
        }
        if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceedWith(Object obj, Continuation continuation) {
        setSubject(obj);
        return proceed(continuation);
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object execute$ktor_utils(Object obj, Continuation continuation) {
        this.index = 0;
        if (this.blocks.size() == 0) {
            return obj;
        }
        setSubject(obj);
        if (this.lastSuspensionIndex >= 0) {
            throw new IllegalStateException("Already started");
        }
        return proceed(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean loop(boolean z) {
        int i;
        do {
            i = this.index;
            if (i == this.blocks.size()) {
                if (z) {
                    return true;
                }
                Result.Companion companion = Result.Companion;
                resumeRootWith(Result.m2639constructorimpl(getSubject()));
                return false;
            }
            this.index = i + 1;
            try {
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                resumeRootWith(Result.m2639constructorimpl(ResultKt.createFailure(th)));
                return false;
            }
        } while (((Function3) this.blocks.get(i)).invoke(this, getSubject(), this.continuation) != IntrinsicsKt.getCOROUTINE_SUSPENDED());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeRootWith(Object obj) {
        int i = this.lastSuspensionIndex;
        if (i < 0) {
            throw new IllegalStateException("No more continuations to resume");
        }
        Continuation continuation = this.suspensions[i];
        Intrinsics.checkNotNull(continuation);
        Continuation[] continuationArr = this.suspensions;
        int i2 = this.lastSuspensionIndex;
        this.lastSuspensionIndex = i2 - 1;
        continuationArr[i2] = null;
        if (!Result.m2644isFailureimpl(obj)) {
            continuation.resumeWith(obj);
            return;
        }
        Throwable thM2642exceptionOrNullimpl = Result.m2642exceptionOrNullimpl(obj);
        Intrinsics.checkNotNull(thM2642exceptionOrNullimpl);
        continuation.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(StackTraceRecoverKt.recoverStackTraceBridge(thM2642exceptionOrNullimpl, continuation))));
    }

    private final void discardLastRootContinuation() {
        int i = this.lastSuspensionIndex;
        if (i < 0) {
            throw new IllegalStateException("No more continuations to resume");
        }
        Continuation[] continuationArr = this.suspensions;
        this.lastSuspensionIndex = i - 1;
        continuationArr[i] = null;
    }

    public final void addContinuation$ktor_utils(Continuation continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        Continuation[] continuationArr = this.suspensions;
        int i = this.lastSuspensionIndex + 1;
        this.lastSuspensionIndex = i;
        continuationArr[i] = continuation;
    }
}
