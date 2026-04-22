package io.ktor.util.pipeline;

import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DebugPipelineContext.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DebugPipelineContext extends PipelineContext {
    private final CoroutineContext coroutineContext;
    private int index;
    private final List interceptors;
    private Object subject;

    /* JADX INFO: renamed from: io.ktor.util.pipeline.DebugPipelineContext$proceedLoop$1, reason: invalid class name */
    /* JADX INFO: compiled from: DebugPipelineContext.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DebugPipelineContext.this.proceedLoop(this);
        }
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DebugPipelineContext(Object context, List interceptors, Object subject, CoroutineContext coroutineContext) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        Intrinsics.checkNotNullParameter(subject, "subject");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        this.interceptors = interceptors;
        this.coroutineContext = coroutineContext;
        this.subject = subject;
    }

    public Object getSubject() {
        return this.subject;
    }

    public void setSubject(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.subject = obj;
    }

    public void finish() {
        this.index = -1;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceedWith(Object obj, Continuation continuation) {
        setSubject(obj);
        return proceed(continuation);
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceed(Continuation continuation) {
        int i = this.index;
        if (i < 0) {
            return getSubject();
        }
        if (i >= this.interceptors.size()) {
            finish();
            return getSubject();
        }
        return proceedLoop(continuation);
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object execute$ktor_utils(Object obj, Continuation continuation) {
        this.index = 0;
        setSubject(obj);
        return proceed(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object proceedLoop(kotlin.coroutines.Continuation r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof io.ktor.util.pipeline.DebugPipelineContext.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.util.pipeline.DebugPipelineContext$proceedLoop$1 r0 = (io.ktor.util.pipeline.DebugPipelineContext.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.pipeline.DebugPipelineContext$proceedLoop$1 r0 = new io.ktor.util.pipeline.DebugPipelineContext$proceedLoop$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.DebugPipelineContext r2 = (io.ktor.util.pipeline.DebugPipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L39
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r6
        L39:
            int r7 = r2.index
            r4 = -1
            if (r7 != r4) goto L3f
            goto L4a
        L3f:
            java.util.List r4 = r2.interceptors
            int r5 = r4.size()
            if (r7 < r5) goto L4f
            r2.finish()
        L4a:
            java.lang.Object r7 = r2.getSubject()
            return r7
        L4f:
            java.lang.Object r4 = r4.get(r7)
            kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
            int r7 = r7 + 1
            r2.index = r7
            java.lang.String r7 = "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.coroutines.SuspendFunction2<io.ktor.util.pipeline.PipelineContext<TSubject of io.ktor.util.pipeline.DebugPipelineContext, TContext of io.ktor.util.pipeline.DebugPipelineContext>, TSubject of io.ktor.util.pipeline.DebugPipelineContext, kotlin.Unit>{ io.ktor.util.pipeline.PipelineKt.PipelineInterceptor<TSubject of io.ktor.util.pipeline.DebugPipelineContext, TContext of io.ktor.util.pipeline.DebugPipelineContext> }"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r7)
            java.lang.Object r7 = r2.getSubject()
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r7 = r4.invoke(r2, r7, r0)
            if (r7 != r1) goto L39
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.pipeline.DebugPipelineContext.proceedLoop(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
