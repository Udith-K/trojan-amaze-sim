package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.URLProtocolKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: HttpTimeout.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpTimeout$Plugin$install$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ HttpTimeout $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpTimeout$Plugin$install$1(HttpTimeout httpTimeout, HttpClient httpClient, Continuation continuation) {
        super(3, continuation);
        this.$plugin = httpTimeout;
        this.$scope = httpClient;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, Continuation continuation) {
        HttpTimeout$Plugin$install$1 httpTimeout$Plugin$install$1 = new HttpTimeout$Plugin$install$1(this.$plugin, this.$scope, continuation);
        httpTimeout$Plugin$install$1.L$0 = sender;
        httpTimeout$Plugin$install$1.L$1 = httpRequestBuilder;
        return httpTimeout$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            }
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Sender sender = (Sender) this.L$0;
        HttpRequestBuilder httpRequestBuilder = (HttpRequestBuilder) this.L$1;
        if (!URLProtocolKt.isWebsocket(httpRequestBuilder.getUrl().getProtocol())) {
            httpRequestBuilder.getBody();
            HttpTimeout.Plugin plugin = HttpTimeout.Plugin;
            HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestBuilder.getCapabilityOrNull(plugin);
            if (httpTimeoutCapabilityConfiguration == null && this.$plugin.hasNotNullTimeouts()) {
                httpTimeoutCapabilityConfiguration = new HttpTimeout.HttpTimeoutCapabilityConfiguration(null, null, null, 7, null);
                httpRequestBuilder.setCapability(plugin, httpTimeoutCapabilityConfiguration);
            }
            if (httpTimeoutCapabilityConfiguration != null) {
                HttpTimeout httpTimeout = this.$plugin;
                HttpClient httpClient = this.$scope;
                Long connectTimeoutMillis = httpTimeoutCapabilityConfiguration.getConnectTimeoutMillis();
                if (connectTimeoutMillis == null) {
                    connectTimeoutMillis = httpTimeout.connectTimeoutMillis;
                }
                httpTimeoutCapabilityConfiguration.setConnectTimeoutMillis(connectTimeoutMillis);
                Long socketTimeoutMillis = httpTimeoutCapabilityConfiguration.getSocketTimeoutMillis();
                if (socketTimeoutMillis == null) {
                    socketTimeoutMillis = httpTimeout.socketTimeoutMillis;
                }
                httpTimeoutCapabilityConfiguration.setSocketTimeoutMillis(socketTimeoutMillis);
                Long requestTimeoutMillis = httpTimeoutCapabilityConfiguration.getRequestTimeoutMillis();
                if (requestTimeoutMillis == null) {
                    requestTimeoutMillis = httpTimeout.requestTimeoutMillis;
                }
                httpTimeoutCapabilityConfiguration.setRequestTimeoutMillis(requestTimeoutMillis);
                Long requestTimeoutMillis2 = httpTimeoutCapabilityConfiguration.getRequestTimeoutMillis();
                if (requestTimeoutMillis2 == null) {
                    requestTimeoutMillis2 = httpTimeout.requestTimeoutMillis;
                }
                if (requestTimeoutMillis2 != null && requestTimeoutMillis2.longValue() != Preferences.UPDATE_INTERVAL_DISABLED) {
                    final Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(httpClient, null, null, new HttpTimeout$Plugin$install$1$1$killer$1(requestTimeoutMillis2, httpRequestBuilder, httpRequestBuilder.getExecutionContext(), null), 3, null);
                    httpRequestBuilder.getExecutionContext().invokeOnCompletion(new Function1() { // from class: io.ktor.client.plugins.HttpTimeout$Plugin$install$1$1$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                            invoke((Throwable) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Throwable th) {
                            Job.DefaultImpls.cancel$default(jobLaunch$default, null, 1, null);
                        }
                    });
                }
            }
            this.L$0 = null;
            this.label = 2;
            obj = sender.execute(httpRequestBuilder, this);
            return obj == coroutine_suspended ? coroutine_suspended : obj;
        }
        this.L$0 = null;
        this.label = 1;
        obj = sender.execute(httpRequestBuilder, this);
        return obj == coroutine_suspended ? coroutine_suspended : obj;
    }
}
