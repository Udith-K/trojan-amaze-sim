package io.ktor.client.engine.okhttp;

import io.ktor.client.plugins.HttpTimeout;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: OkHttpEngine.kt */
/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class OkHttpEngine$clientCache$1 extends FunctionReferenceImpl implements Function1 {
    OkHttpEngine$clientCache$1(Object obj) {
        super(1, obj, OkHttpEngine.class, "createOkHttpClient", "createOkHttpClient(Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;)Lokhttp3/OkHttpClient;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final OkHttpClient invoke(HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration) {
        return ((OkHttpEngine) this.receiver).createOkHttpClient(httpTimeoutCapabilityConfiguration);
    }
}
