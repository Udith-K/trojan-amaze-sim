package io.ktor.client.plugins.observer;

import io.ktor.client.call.HttpClientCall;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DelegatedCall.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DelegatedCallKt {
    public static final HttpClientCall wrapWithContent(HttpClientCall httpClientCall, ByteReadChannel content) {
        Intrinsics.checkNotNullParameter(httpClientCall, "<this>");
        Intrinsics.checkNotNullParameter(content, "content");
        return new DelegatedCall(httpClientCall.getClient(), content, httpClientCall);
    }
}
