package io.ktor.client.call;

import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseKt;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HttpClientCall.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NoTransformationFoundException extends UnsupportedOperationException {
    private final String message;

    public NoTransformationFoundException(HttpResponse response, KClass from, KClass to) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        StringBuilder sb = new StringBuilder();
        sb.append("\n        Expected response body of the type '");
        sb.append(to);
        sb.append("' but was '");
        sb.append(from);
        sb.append("'\n        In response from `");
        sb.append(HttpResponseKt.getRequest(response).getUrl());
        sb.append("`\n        Response status `");
        sb.append(response.getStatus());
        sb.append("`\n        Response header `ContentType: ");
        Headers headers = response.getHeaders();
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        sb.append(headers.get(httpHeaders.getContentType()));
        sb.append("` \n        Request header `Accept: ");
        sb.append(HttpResponseKt.getRequest(response).getHeaders().get(httpHeaders.getAccept()));
        sb.append("`\n        \n        You can read how to resolve NoTransformationFoundException at FAQ: \n        https://ktor.io/docs/faq.html#no-transformation-found-exception\n    ");
        this.message = StringsKt.trimIndent(sb.toString());
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
