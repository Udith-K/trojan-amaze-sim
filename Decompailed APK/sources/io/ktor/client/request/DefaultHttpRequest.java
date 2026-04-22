package io.ktor.client.request;

import io.ktor.client.call.HttpClientCall;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.Attributes;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultHttpRequest.kt */
/* JADX INFO: loaded from: classes.dex */
public class DefaultHttpRequest implements HttpRequest {
    private final Attributes attributes;
    private final HttpClientCall call;
    private final OutgoingContent content;
    private final Headers headers;
    private final HttpMethod method;
    private final Url url;

    public DefaultHttpRequest(HttpClientCall call, HttpRequestData data) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(data, "data");
        this.call = call;
        this.method = data.getMethod();
        this.url = data.getUrl();
        this.content = data.getBody();
        this.headers = data.getHeaders();
        this.attributes = data.getAttributes();
    }

    @Override // io.ktor.client.request.HttpRequest
    public HttpClientCall getCall() {
        return this.call;
    }

    @Override // io.ktor.client.request.HttpRequest, kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return getCall().getCoroutineContext();
    }

    @Override // io.ktor.client.request.HttpRequest
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // io.ktor.client.request.HttpRequest
    public Url getUrl() {
        return this.url;
    }

    @Override // io.ktor.http.HttpMessage
    public Headers getHeaders() {
        return this.headers;
    }

    @Override // io.ktor.client.request.HttpRequest
    public Attributes getAttributes() {
        return this.attributes;
    }
}
