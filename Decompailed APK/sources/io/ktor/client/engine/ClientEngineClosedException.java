package io.ktor.client.engine;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: HttpClientEngineBase.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ClientEngineClosedException extends IllegalStateException {
    private final Throwable cause;

    public /* synthetic */ ClientEngineClosedException(Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : th);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public ClientEngineClosedException(Throwable th) {
        super("Client already closed");
        this.cause = th;
    }
}
