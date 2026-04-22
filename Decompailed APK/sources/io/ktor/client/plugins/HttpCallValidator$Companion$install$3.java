package io.ktor.client.plugins;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: HttpCallValidator.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpCallValidator$Companion$install$3 extends SuspendLambda implements Function3 {
    final /* synthetic */ HttpCallValidator $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpCallValidator$Companion$install$3(HttpCallValidator httpCallValidator, Continuation continuation) {
        super(3, continuation);
        this.$plugin = httpCallValidator;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, Continuation continuation) {
        HttpCallValidator$Companion$install$3 httpCallValidator$Companion$install$3 = new HttpCallValidator$Companion$install$3(this.$plugin, continuation);
        httpCallValidator$Companion$install$3.L$0 = sender;
        httpCallValidator$Companion$install$3.L$1 = httpRequestBuilder;
        return httpCallValidator$Companion$install$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Sender sender = (Sender) this.L$0;
            HttpRequestBuilder httpRequestBuilder = (HttpRequestBuilder) this.L$1;
            this.L$0 = null;
            this.label = 1;
            obj = sender.execute(httpRequestBuilder, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                HttpClientCall httpClientCall = (HttpClientCall) this.L$0;
                ResultKt.throwOnFailure(obj);
                return httpClientCall;
            }
            ResultKt.throwOnFailure(obj);
        }
        HttpClientCall httpClientCall2 = (HttpClientCall) obj;
        HttpCallValidator httpCallValidator = this.$plugin;
        HttpResponse response = httpClientCall2.getResponse();
        this.L$0 = httpClientCall2;
        this.label = 2;
        return httpCallValidator.validateResponse(response, this) == coroutine_suspended ? coroutine_suspended : httpClientCall2;
    }
}
