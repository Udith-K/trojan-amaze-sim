package io.ktor.util.pipeline;

import java.util.List;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PipelineContext.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PipelineContextKt {
    public static final PipelineContext pipelineContextFor(Object context, List interceptors, Object subject, CoroutineContext coroutineContext, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        Intrinsics.checkNotNullParameter(subject, "subject");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        if (PipelineContext_jvmKt.getDISABLE_SFG() || z) {
            return new DebugPipelineContext(context, interceptors, subject, coroutineContext);
        }
        return new SuspendFunctionGun(subject, context, interceptors);
    }
}
