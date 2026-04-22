package androidx.compose.ui.platform;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.runtime.MonotonicFrameClockKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: InfiniteAnimationPolicy.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class InfiniteAnimationPolicyKt {
    public static final Object withInfiniteAnimationFrameNanos(Function1 function1, Continuation continuation) {
        ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(continuation.getContext().get(InfiniteAnimationPolicy.Key));
        return MonotonicFrameClockKt.withFrameNanos(function1, continuation);
    }
}
