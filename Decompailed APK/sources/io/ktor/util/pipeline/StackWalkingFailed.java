package io.ktor.util.pipeline;

/* JADX INFO: compiled from: StackWalkingFailed.kt */
/* JADX INFO: loaded from: classes.dex */
public final class StackWalkingFailed {
    public static final StackWalkingFailed INSTANCE = new StackWalkingFailed();

    private StackWalkingFailed() {
    }

    public final void failedToCaptureStackFrame() {
        throw new IllegalStateException("Failed to capture stack frame. This is usually happens when a coroutine is running so the frame stack is changing quickly and the coroutine debug agent is unable to capture it concurrently. You may retry running your test to see this particular trace.");
    }
}
