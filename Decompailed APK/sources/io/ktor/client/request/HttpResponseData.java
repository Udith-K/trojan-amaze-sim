package io.ktor.client.request;

import ch.qos.logback.core.CoreConstants;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.GMTDate;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpRequest.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpResponseData {
    private final Object body;
    private final CoroutineContext callContext;
    private final Headers headers;
    private final GMTDate requestTime;
    private final GMTDate responseTime;
    private final HttpStatusCode statusCode;
    private final HttpProtocolVersion version;

    public HttpResponseData(HttpStatusCode statusCode, GMTDate requestTime, Headers headers, HttpProtocolVersion version, Object body, CoroutineContext callContext) {
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        Intrinsics.checkNotNullParameter(requestTime, "requestTime");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(callContext, "callContext");
        this.statusCode = statusCode;
        this.requestTime = requestTime;
        this.headers = headers;
        this.version = version;
        this.body = body;
        this.callContext = callContext;
        this.responseTime = DateJvmKt.GMTDate$default(null, 1, null);
    }

    public final HttpStatusCode getStatusCode() {
        return this.statusCode;
    }

    public final GMTDate getRequestTime() {
        return this.requestTime;
    }

    public final Headers getHeaders() {
        return this.headers;
    }

    public final HttpProtocolVersion getVersion() {
        return this.version;
    }

    public final Object getBody() {
        return this.body;
    }

    public final CoroutineContext getCallContext() {
        return this.callContext;
    }

    public final GMTDate getResponseTime() {
        return this.responseTime;
    }

    public String toString() {
        return "HttpResponseData=(statusCode=" + this.statusCode + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
