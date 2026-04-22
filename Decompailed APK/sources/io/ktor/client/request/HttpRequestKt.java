package io.ktor.client.request;

import io.ktor.http.URLParserKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpRequest.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpRequestKt {
    public static final void url(HttpRequestBuilder httpRequestBuilder, String urlString) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        URLParserKt.takeFrom(httpRequestBuilder.getUrl(), urlString);
    }

    public static final boolean isUpgradeRequest(HttpRequestData httpRequestData) {
        Intrinsics.checkNotNullParameter(httpRequestData, "<this>");
        httpRequestData.getBody();
        return false;
    }
}
