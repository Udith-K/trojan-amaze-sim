package io.ktor.util.pipeline;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;

/* JADX INFO: compiled from: StackWalkingFailedFrame.kt */
/* JADX INFO: loaded from: classes.dex */
public final class StackWalkingFailedFrame implements CoroutineStackFrame, Continuation {
    public static final StackWalkingFailedFrame INSTANCE = new StackWalkingFailedFrame();

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        return null;
    }

    private StackWalkingFailedFrame() {
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        StackWalkingFailed.INSTANCE.failedToCaptureStackFrame();
    }
}
