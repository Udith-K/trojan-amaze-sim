package io.ktor.client.engine;

import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.request.HttpRequestData;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HttpClientEngine.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpClientEngine$executeWithinCallContext$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ HttpRequestData $requestData;
    int label;
    final /* synthetic */ HttpClientEngine this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpClientEngine$executeWithinCallContext$2(HttpClientEngine httpClientEngine, HttpRequestData httpRequestData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = httpClientEngine;
        this.$requestData = httpRequestData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HttpClientEngine$executeWithinCallContext$2(this.this$0, this.$requestData, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((HttpClientEngine$executeWithinCallContext$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (HttpClientEngine.DefaultImpls.getClosed(this.this$0)) {
                throw new ClientEngineClosedException(null, 1, null);
            }
            HttpClientEngine httpClientEngine = this.this$0;
            HttpRequestData httpRequestData = this.$requestData;
            this.label = 1;
            obj = httpClientEngine.execute(httpRequestData, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
