package io.ktor.client.call;

import io.ktor.client.HttpClient;
import io.ktor.client.request.DefaultHttpRequest;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.request.HttpResponseData;
import io.ktor.client.statement.DefaultHttpResponse;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.utils.io.ByteReadChannel;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HttpClientCall.kt */
/* JADX INFO: loaded from: classes.dex */
public class HttpClientCall implements CoroutineScope {
    public static final Companion Companion = new Companion(null);
    private static final AttributeKey CustomResponse = new AttributeKey("CustomResponse");
    private static final /* synthetic */ AtomicIntegerFieldUpdater received$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClientCall.class, "received");
    private final boolean allowDoubleReceive;
    private final HttpClient client;
    private volatile /* synthetic */ int received;
    protected HttpRequest request;
    protected HttpResponse response;

    /* JADX INFO: renamed from: io.ktor.client.call.HttpClientCall$bodyNullable$1, reason: invalid class name */
    /* JADX INFO: compiled from: HttpClientCall.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpClientCall.this.bodyNullable(null, this);
        }
    }

    protected Object getResponseContent(Continuation continuation) {
        return getResponseContent$suspendImpl(this, continuation);
    }

    public HttpClientCall(HttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
        this.received = 0;
    }

    public final HttpClient getClient() {
        return this.client;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return getResponse().getCoroutineContext();
    }

    public final Attributes getAttributes() {
        return getRequest().getAttributes();
    }

    public final HttpRequest getRequest() {
        HttpRequest httpRequest = this.request;
        if (httpRequest != null) {
            return httpRequest;
        }
        Intrinsics.throwUninitializedPropertyAccessException("request");
        return null;
    }

    protected final void setRequest(HttpRequest httpRequest) {
        Intrinsics.checkNotNullParameter(httpRequest, "<set-?>");
        this.request = httpRequest;
    }

    public final HttpResponse getResponse() {
        HttpResponse httpResponse = this.response;
        if (httpResponse != null) {
            return httpResponse;
        }
        Intrinsics.throwUninitializedPropertyAccessException("response");
        return null;
    }

    protected final void setResponse(HttpResponse httpResponse) {
        Intrinsics.checkNotNullParameter(httpResponse, "<set-?>");
        this.response = httpResponse;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpClientCall(HttpClient client, HttpRequestData requestData, HttpResponseData responseData) {
        this(client);
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(requestData, "requestData");
        Intrinsics.checkNotNullParameter(responseData, "responseData");
        setRequest(new DefaultHttpRequest(this, requestData));
        setResponse(new DefaultHttpResponse(this, responseData));
        if (responseData.getBody() instanceof ByteReadChannel) {
            return;
        }
        getAttributes().put(CustomResponse, responseData.getBody());
    }

    protected boolean getAllowDoubleReceive() {
        return this.allowDoubleReceive;
    }

    static /* synthetic */ Object getResponseContent$suspendImpl(HttpClientCall httpClientCall, Continuation continuation) {
        return httpClientCall.getResponse().getContent();
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object bodyNullable(io.ktor.util.reflect.TypeInfo r6, kotlin.coroutines.Continuation r7) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.call.HttpClientCall.bodyNullable(io.ktor.util.reflect.TypeInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public String toString() {
        return "HttpClientCall[" + getRequest().getUrl() + ", " + getResponse().getStatus() + ']';
    }

    public final void setResponse$ktor_client_core(HttpResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        setResponse(response);
    }

    /* JADX INFO: compiled from: HttpClientCall.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
