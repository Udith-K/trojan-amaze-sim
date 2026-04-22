package io.ktor.client.plugins.observer;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DelegatedCall.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DelegatedCall extends HttpClientCall {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelegatedCall(HttpClient client, ByteReadChannel content, HttpClientCall originCall) {
        super(client);
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(originCall, "originCall");
        setRequest(new DelegatedRequest(this, originCall.getRequest()));
        setResponse(new DelegatedResponse(this, content, originCall.getResponse()));
    }
}
