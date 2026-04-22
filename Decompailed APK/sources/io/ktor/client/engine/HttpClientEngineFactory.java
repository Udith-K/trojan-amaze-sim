package io.ktor.client.engine;

import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: HttpClientEngine.kt */
/* JADX INFO: loaded from: classes.dex */
public interface HttpClientEngineFactory {
    HttpClientEngine create(Function1 function1);
}
