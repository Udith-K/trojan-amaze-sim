package io.ktor.http;

import java.nio.charset.Charset;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpMessageProperties.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpMessagePropertiesKt {
    public static final ContentType contentType(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        String str = httpMessageBuilder.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (str != null) {
            return ContentType.Companion.parse(str);
        }
        return null;
    }

    public static final ContentType contentType(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        String str = httpMessage.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (str != null) {
            return ContentType.Companion.parse(str);
        }
        return null;
    }

    public static final Charset charset(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        ContentType contentType = contentType(httpMessage);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    public static final Long contentLength(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        String str = httpMessage.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        if (str != null) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }
}
