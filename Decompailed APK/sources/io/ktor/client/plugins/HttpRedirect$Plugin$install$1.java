package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpRedirect;
import io.ktor.client.request.HttpRequestBuilder;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: HttpRedirect.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpRedirect$Plugin$install$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ HttpRedirect $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpRedirect$Plugin$install$1(HttpRedirect httpRedirect, HttpClient httpClient, Continuation continuation) {
        super(3, continuation);
        this.$plugin = httpRedirect;
        this.$scope = httpClient;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, Continuation continuation) {
        HttpRedirect$Plugin$install$1 httpRedirect$Plugin$install$1 = new HttpRedirect$Plugin$install$1(this.$plugin, this.$scope, continuation);
        httpRedirect$Plugin$install$1.L$0 = sender;
        httpRedirect$Plugin$install$1.L$1 = httpRequestBuilder;
        return httpRedirect$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Sender sender;
        HttpRequestBuilder httpRequestBuilder;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Sender sender2 = (Sender) this.L$0;
            HttpRequestBuilder httpRequestBuilder2 = (HttpRequestBuilder) this.L$1;
            this.L$0 = sender2;
            this.L$1 = httpRequestBuilder2;
            this.label = 1;
            Object objExecute = sender2.execute(httpRequestBuilder2, this);
            if (objExecute == coroutine_suspended) {
                return coroutine_suspended;
            }
            sender = sender2;
            httpRequestBuilder = httpRequestBuilder2;
            obj = objExecute;
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            HttpRequestBuilder httpRequestBuilder3 = (HttpRequestBuilder) this.L$1;
            Sender sender3 = (Sender) this.L$0;
            ResultKt.throwOnFailure(obj);
            httpRequestBuilder = httpRequestBuilder3;
            sender = sender3;
        }
        HttpClientCall httpClientCall = (HttpClientCall) obj;
        if (this.$plugin.checkHttpMethod && !HttpRedirectKt.ALLOWED_FOR_REDIRECT.contains(httpClientCall.getRequest().getMethod())) {
            return httpClientCall;
        }
        HttpRedirect.Plugin plugin = HttpRedirect.Plugin;
        boolean z = this.$plugin.allowHttpsDowngrade;
        HttpClient httpClient = this.$scope;
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        obj = plugin.handleCall(sender, httpRequestBuilder, httpClientCall, z, httpClient, this);
        return obj == coroutine_suspended ? coroutine_suspended : obj;
    }
}
