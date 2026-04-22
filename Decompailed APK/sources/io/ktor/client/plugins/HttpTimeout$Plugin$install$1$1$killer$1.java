package io.ktor.client.plugins;

import io.ktor.client.request.HttpRequestBuilder;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: HttpTimeout.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpTimeout$Plugin$install$1$1$killer$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Job $executionContext;
    final /* synthetic */ HttpRequestBuilder $request;
    final /* synthetic */ Long $requestTimeout;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpTimeout$Plugin$install$1$1$killer$1(Long l, HttpRequestBuilder httpRequestBuilder, Job job, Continuation continuation) {
        super(2, continuation);
        this.$requestTimeout = l;
        this.$request = httpRequestBuilder;
        this.$executionContext = job;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HttpTimeout$Plugin$install$1$1$killer$1(this.$requestTimeout, this.$request, this.$executionContext, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((HttpTimeout$Plugin$install$1$1$killer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            long jLongValue = this.$requestTimeout.longValue();
            this.label = 1;
            if (DelayKt.delay(jLongValue, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        HttpRequestTimeoutException httpRequestTimeoutException = new HttpRequestTimeoutException(this.$request);
        HttpTimeoutKt.LOGGER.trace("Request timeout: " + this.$request.getUrl());
        Job job = this.$executionContext;
        String message = httpRequestTimeoutException.getMessage();
        Intrinsics.checkNotNull(message);
        JobKt.cancel(job, message, httpRequestTimeoutException);
        return Unit.INSTANCE;
    }
}
