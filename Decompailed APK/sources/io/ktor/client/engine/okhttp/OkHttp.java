package io.ktor.client.engine.okhttp;

import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineFactory;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OkHttp.kt */
/* JADX INFO: loaded from: classes.dex */
public final class OkHttp implements HttpClientEngineFactory {
    public static final OkHttp INSTANCE = new OkHttp();

    private OkHttp() {
    }

    @Override // io.ktor.client.engine.HttpClientEngineFactory
    public HttpClientEngine create(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        OkHttpConfig okHttpConfig = new OkHttpConfig();
        block.invoke(okHttpConfig);
        return new OkHttpEngine(okHttpConfig);
    }
}
