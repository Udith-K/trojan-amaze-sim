package io.ktor.http;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: URLProtocol.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class URLProtocolKt {
    public static final boolean isWebsocket(URLProtocol uRLProtocol) {
        Intrinsics.checkNotNullParameter(uRLProtocol, "<this>");
        return Intrinsics.areEqual(uRLProtocol.getName(), "ws") || Intrinsics.areEqual(uRLProtocol.getName(), "wss");
    }

    public static final boolean isSecure(URLProtocol uRLProtocol) {
        Intrinsics.checkNotNullParameter(uRLProtocol, "<this>");
        return Intrinsics.areEqual(uRLProtocol.getName(), "https") || Intrinsics.areEqual(uRLProtocol.getName(), "wss");
    }
}
