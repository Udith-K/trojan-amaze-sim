package io.ktor.client.plugins;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpSend.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SendCountExceedException extends IllegalStateException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SendCountExceedException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
