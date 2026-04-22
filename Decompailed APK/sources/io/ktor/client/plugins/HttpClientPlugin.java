package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: HttpClientPlugin.kt */
/* JADX INFO: loaded from: classes.dex */
public interface HttpClientPlugin {
    AttributeKey getKey();

    void install(Object obj, HttpClient httpClient);

    Object prepare(Function1 function1);
}
