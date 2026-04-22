package io.ktor.client.plugins;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.client.utils.ExceptionUtilsJvmKt;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: HttpCallValidator.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpCallValidator$Companion$install$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ HttpCallValidator $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpCallValidator$Companion$install$2(HttpCallValidator httpCallValidator, Continuation continuation) {
        super(3, continuation);
        this.$plugin = httpCallValidator;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext pipelineContext, HttpResponseContainer httpResponseContainer, Continuation continuation) {
        HttpCallValidator$Companion$install$2 httpCallValidator$Companion$install$2 = new HttpCallValidator$Companion$install$2(this.$plugin, continuation);
        httpCallValidator$Companion$install$2.L$0 = pipelineContext;
        httpCallValidator$Companion$install$2.L$1 = httpResponseContainer;
        return httpCallValidator$Companion$install$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [io.ktor.util.pipeline.PipelineContext] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v8 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                HttpResponseContainer httpResponseContainer = (HttpResponseContainer) this.L$1;
                this.L$0 = pipelineContext;
                this.label = 1;
                Object objProceedWith = pipelineContext.proceedWith(httpResponseContainer, this);
                r1 = pipelineContext;
                if (objProceedWith == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (r1 != 1) {
                    if (r1 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Throwable th = (Throwable) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    throw th;
                }
                PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
                ResultKt.throwOnFailure(obj);
                r1 = pipelineContext2;
            }
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            Throwable thUnwrapCancellationException = ExceptionUtilsJvmKt.unwrapCancellationException(th2);
            HttpCallValidator httpCallValidator = this.$plugin;
            HttpRequest request = ((HttpClientCall) r1.getContext()).getRequest();
            this.L$0 = thUnwrapCancellationException;
            this.label = 2;
            if (httpCallValidator.processException(thUnwrapCancellationException, request, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            throw thUnwrapCancellationException;
        }
    }
}
