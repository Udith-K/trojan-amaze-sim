package org.fdroid.download;

import ch.qos.logback.core.spi.AbstractComponentTracker;
import io.ktor.client.HttpClient;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import java.io.IOException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: HttpManager.kt */
/* JADX INFO: loaded from: classes2.dex */
final class HttpManager$head$response$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ DownloadRequest $request;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ HttpManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpManager$head$response$1(HttpManager httpManager, DownloadRequest downloadRequest, Continuation continuation) {
        super(3, continuation);
        this.this$0 = httpManager;
        this.$request = downloadRequest;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Mirror mirror, Url url, Continuation continuation) {
        HttpManager$head$response$1 httpManager$head$response$1 = new HttpManager$head$response$1(this.this$0, this.$request, continuation);
        httpManager$head$response$1.L$0 = mirror;
        httpManager$head$response$1.L$1 = url;
        return httpManager$head$response$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws IOException {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Mirror mirror = (Mirror) this.L$0;
            final Url url = (Url) this.L$1;
            this.this$0.resetProxyIfNeeded(this.$request.getProxy(), mirror);
            HttpManager.INSTANCE.getLog$download_release().debug(new Function0() { // from class: org.fdroid.download.HttpManager$head$response$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return HttpManager$head$response$1.invokeSuspend$lambda$0(url);
                }
            });
            HttpClient httpClient = this.this$0.httpClient;
            HttpManager httpManager = this.this$0;
            DownloadRequest downloadRequest = this.$request;
            HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
            URLUtilsKt.takeFrom(httpRequestBuilder.getUrl(), url);
            httpManager.addQueryParameters(httpRequestBuilder);
            httpManager.basicAuth(httpRequestBuilder, downloadRequest);
            if (mirror.isOnion()) {
                HttpTimeoutKt.timeout(httpRequestBuilder, new Function1() { // from class: org.fdroid.download.HttpManager$head$response$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return HttpManager$head$response$1.invokeSuspend$lambda$2$lambda$1((HttpTimeout.HttpTimeoutCapabilityConfiguration) obj2);
                    }
                });
            }
            httpRequestBuilder.setMethod(HttpMethod.Companion.getHead());
            HttpStatement httpStatement = new HttpStatement(httpRequestBuilder, httpClient);
            this.L$0 = null;
            this.label = 1;
            obj = httpStatement.execute(this);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object invokeSuspend$lambda$0(Url url) {
        return "HEAD " + url;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$2$lambda$1(HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration) {
        httpTimeoutCapabilityConfiguration.setConnectTimeoutMillis(Long.valueOf(AbstractComponentTracker.LINGERING_TIMEOUT));
        return Unit.INSTANCE;
    }
}
