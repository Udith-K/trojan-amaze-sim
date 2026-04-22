package io.ktor.http;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpHeaders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UnsafeHeaderException extends IllegalArgumentException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnsafeHeaderException(String header) {
        super("Header(s) " + header + " are controlled by the engine and cannot be set explicitly");
        Intrinsics.checkNotNullParameter(header, "header");
    }
}
