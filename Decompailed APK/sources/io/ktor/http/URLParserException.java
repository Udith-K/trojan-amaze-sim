package io.ktor.http;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: URLParser.kt */
/* JADX INFO: loaded from: classes.dex */
public final class URLParserException extends IllegalStateException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public URLParserException(String urlString, Throwable cause) {
        super("Fail to parse url: " + urlString, cause);
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}
