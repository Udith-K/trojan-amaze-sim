package io.ktor.client.plugins;

import io.ktor.client.request.HttpRequestBuilder;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: HttpSend.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Sender {
    Object execute(HttpRequestBuilder httpRequestBuilder, Continuation continuation);
}
