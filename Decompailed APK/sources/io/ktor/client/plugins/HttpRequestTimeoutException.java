package io.ktor.client.plugins;

import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.HttpRequestBuilder;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpTimeout.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpRequestTimeoutException extends IOException {
    public HttpRequestTimeoutException(String url, Long l) {
        Intrinsics.checkNotNullParameter(url, "url");
        StringBuilder sb = new StringBuilder();
        sb.append("Request timeout has expired [url=");
        sb.append(url);
        sb.append(", request_timeout=");
        sb.append(l == null ? "unknown" : l);
        sb.append(" ms]");
        super(sb.toString());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public HttpRequestTimeoutException(HttpRequestBuilder request) {
        Intrinsics.checkNotNullParameter(request, "request");
        String strBuildString = request.getUrl().buildString();
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) request.getCapabilityOrNull(HttpTimeout.Plugin);
        this(strBuildString, httpTimeoutCapabilityConfiguration != null ? httpTimeoutCapabilityConfiguration.getRequestTimeoutMillis() : null);
    }
}
