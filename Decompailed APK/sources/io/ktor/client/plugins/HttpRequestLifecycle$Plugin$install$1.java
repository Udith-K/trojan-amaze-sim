package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: HttpRequestLifecycle.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpRequestLifecycle$Plugin$install$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpRequestLifecycle$Plugin$install$1(HttpClient httpClient, Continuation continuation) {
        super(3, continuation);
        this.$scope = httpClient;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext pipelineContext, Object obj, Continuation continuation) {
        HttpRequestLifecycle$Plugin$install$1 httpRequestLifecycle$Plugin$install$1 = new HttpRequestLifecycle$Plugin$install$1(this.$scope, continuation);
        httpRequestLifecycle$Plugin$install$1.L$0 = pipelineContext;
        return httpRequestLifecycle$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CompletableJob completableJob;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            completableJob = (CompletableJob) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                completableJob.complete();
                return Unit.INSTANCE;
            } catch (Throwable th) {
                th = th;
                try {
                    completableJob.completeExceptionally(th);
                    throw th;
                } catch (Throwable th2) {
                    completableJob.complete();
                    throw th2;
                }
            }
        }
        ResultKt.throwOnFailure(obj);
        PipelineContext pipelineContext = (PipelineContext) this.L$0;
        CompletableJob completableJobSupervisorJob = SupervisorKt.SupervisorJob(((HttpRequestBuilder) pipelineContext.getContext()).getExecutionContext());
        CoroutineContext.Element element = this.$scope.getCoroutineContext().get(Job.Key);
        Intrinsics.checkNotNull(element);
        HttpRequestLifecycleKt.attachToClientEngineJob(completableJobSupervisorJob, (Job) element);
        try {
            ((HttpRequestBuilder) pipelineContext.getContext()).setExecutionContext$ktor_client_core(completableJobSupervisorJob);
            this.L$0 = completableJobSupervisorJob;
            this.label = 1;
            if (pipelineContext.proceed(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            completableJob = completableJobSupervisorJob;
            completableJob.complete();
            return Unit.INSTANCE;
        } catch (Throwable th3) {
            th = th3;
            completableJob = completableJobSupervisorJob;
            completableJob.completeExceptionally(th);
            throw th;
        }
    }
}
