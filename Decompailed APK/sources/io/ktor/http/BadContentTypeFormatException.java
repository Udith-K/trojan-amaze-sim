package io.ktor.http;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentTypes.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BadContentTypeFormatException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BadContentTypeFormatException(String value) {
        super("Bad Content-Type format: " + value);
        Intrinsics.checkNotNullParameter(value, "value");
    }
}
