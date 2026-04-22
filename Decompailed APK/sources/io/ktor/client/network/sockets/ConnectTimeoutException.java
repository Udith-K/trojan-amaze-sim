package io.ktor.client.network.sockets;

import java.net.ConnectException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimeoutExceptions.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ConnectTimeoutException extends ConnectException {
    private final Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectTimeoutException(String message, Throwable th) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
        this.cause = th;
    }
}
