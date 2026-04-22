package io.ktor.client.engine.okhttp;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamRequestBody.kt */
/* JADX INFO: loaded from: classes.dex */
public final class StreamAdapterIOException extends IOException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamAdapterIOException(Throwable cause) {
        super(cause);
        Intrinsics.checkNotNullParameter(cause, "cause");
    }
}
