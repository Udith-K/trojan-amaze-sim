package io.ktor.http;

import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentTypes.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContentTypesKt {
    public static final ContentType withCharset(ContentType contentType, Charset charset) {
        Intrinsics.checkNotNullParameter(contentType, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return contentType.withParameter("charset", CharsetJVMKt.getName(charset));
    }

    public static final Charset charset(HeaderValueWithParameters headerValueWithParameters) {
        Intrinsics.checkNotNullParameter(headerValueWithParameters, "<this>");
        String strParameter = headerValueWithParameters.parameter("charset");
        if (strParameter == null) {
            return null;
        }
        try {
            return Charset.forName(strParameter);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
