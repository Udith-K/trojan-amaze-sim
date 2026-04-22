package io.ktor.client.plugins.observer;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.date.GMTDate;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DelegatedCall.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DelegatedResponse extends HttpResponse {
    private final HttpClientCall call;
    private final ByteReadChannel content;
    private final CoroutineContext coroutineContext;
    private final HttpResponse origin;

    @Override // io.ktor.client.statement.HttpResponse
    public HttpClientCall getCall() {
        return this.call;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public ByteReadChannel getContent() {
        return this.content;
    }

    public DelegatedResponse(HttpClientCall call, ByteReadChannel content, HttpResponse origin) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(origin, "origin");
        this.call = call;
        this.content = content;
        this.origin = origin;
        this.coroutineContext = origin.getCoroutineContext();
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public HttpStatusCode getStatus() {
        return this.origin.getStatus();
    }

    @Override // io.ktor.client.statement.HttpResponse
    public HttpProtocolVersion getVersion() {
        return this.origin.getVersion();
    }

    @Override // io.ktor.client.statement.HttpResponse
    public GMTDate getRequestTime() {
        return this.origin.getRequestTime();
    }

    @Override // io.ktor.client.statement.HttpResponse
    public GMTDate getResponseTime() {
        return this.origin.getResponseTime();
    }

    @Override // io.ktor.http.HttpMessage
    public Headers getHeaders() {
        return this.origin.getHeaders();
    }
}
