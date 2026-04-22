package io.ktor.client.call;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpClientCall.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DoubleReceiveException extends IllegalStateException {
    private final String message;

    public DoubleReceiveException(HttpClientCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.message = "Response already received: " + call;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
