package io.ktor.client.request;

import ch.qos.logback.core.CoreConstants;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.util.Base64Kt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class UtilsKt {
    public static final void header(HttpMessageBuilder httpMessageBuilder, String key, Object obj) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (obj != null) {
            httpMessageBuilder.getHeaders().append(key, obj.toString());
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final void parameter(HttpRequestBuilder httpRequestBuilder, String key, Object obj) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (obj != null) {
            httpRequestBuilder.getUrl().getParameters().append(key, obj.toString());
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final void basicAuth(HttpMessageBuilder httpMessageBuilder, String username, String password) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        String authorization = HttpHeaders.INSTANCE.getAuthorization();
        StringBuilder sb = new StringBuilder();
        sb.append("Basic ");
        sb.append(Base64Kt.encodeBase64(username + CoreConstants.COLON_CHAR + password));
        header(httpMessageBuilder, authorization, sb.toString());
    }
}
