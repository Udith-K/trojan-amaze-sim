package io.ktor.http;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Codecs.kt */
/* JADX INFO: loaded from: classes.dex */
public final class URLDecodeException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public URLDecodeException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
