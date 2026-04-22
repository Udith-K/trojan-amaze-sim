package io.ktor.client.plugins;

import io.ktor.client.network.sockets.ConnectTimeoutException;
import io.ktor.client.network.sockets.SocketTimeoutException;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.Preferences;
import org.slf4j.Logger;

/* JADX INFO: compiled from: HttpTimeout.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpTimeoutKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpTimeout");

    public static final long convertLongTimeoutToLongWithInfiniteAsZero(long j) {
        if (j == Preferences.UPDATE_INTERVAL_DISABLED) {
            return 0L;
        }
        return j;
    }

    public static final void timeout(HttpRequestBuilder httpRequestBuilder, Function1 block) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        HttpTimeout.Plugin plugin = HttpTimeout.Plugin;
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = new HttpTimeout.HttpTimeoutCapabilityConfiguration(null, null, null, 7, null);
        block.invoke(httpTimeoutCapabilityConfiguration);
        httpRequestBuilder.setCapability(plugin, httpTimeoutCapabilityConfiguration);
    }

    public static final ConnectTimeoutException ConnectTimeoutException(HttpRequestData request, Throwable th) {
        Object connectTimeoutMillis;
        Intrinsics.checkNotNullParameter(request, "request");
        StringBuilder sb = new StringBuilder();
        sb.append("Connect timeout has expired [url=");
        sb.append(request.getUrl());
        sb.append(", connect_timeout=");
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) request.getCapabilityOrNull(HttpTimeout.Plugin);
        if (httpTimeoutCapabilityConfiguration == null || (connectTimeoutMillis = httpTimeoutCapabilityConfiguration.getConnectTimeoutMillis()) == null) {
            connectTimeoutMillis = "unknown";
        }
        sb.append(connectTimeoutMillis);
        sb.append(" ms]");
        return new ConnectTimeoutException(sb.toString(), th);
    }

    public static final SocketTimeoutException SocketTimeoutException(HttpRequestData request, Throwable th) {
        Object socketTimeoutMillis;
        Intrinsics.checkNotNullParameter(request, "request");
        StringBuilder sb = new StringBuilder();
        sb.append("Socket timeout has expired [url=");
        sb.append(request.getUrl());
        sb.append(", socket_timeout=");
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) request.getCapabilityOrNull(HttpTimeout.Plugin);
        if (httpTimeoutCapabilityConfiguration == null || (socketTimeoutMillis = httpTimeoutCapabilityConfiguration.getSocketTimeoutMillis()) == null) {
            socketTimeoutMillis = "unknown";
        }
        sb.append(socketTimeoutMillis);
        sb.append("] ms");
        return new SocketTimeoutException(sb.toString(), th);
    }
}
