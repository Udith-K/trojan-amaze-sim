package io.ktor.client.engine.okhttp;

import io.ktor.client.engine.HttpClientEngineConfig;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;

/* JADX INFO: compiled from: OkHttpConfig.kt */
/* JADX INFO: loaded from: classes.dex */
public final class OkHttpConfig extends HttpClientEngineConfig {
    private OkHttpClient preconfigured;
    private WebSocket.Factory webSocketFactory;
    private Function1 config = new Function1() { // from class: io.ktor.client.engine.okhttp.OkHttpConfig.config.1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((OkHttpClient.Builder) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(OkHttpClient.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "$this$null");
            builder.followRedirects(false);
            builder.followSslRedirects(false);
            builder.retryOnConnectionFailure(true);
        }
    };
    private int clientCacheSize = 10;

    public final Function1 getConfig$ktor_client_okhttp() {
        return this.config;
    }

    public final OkHttpClient getPreconfigured() {
        return this.preconfigured;
    }

    public final int getClientCacheSize() {
        return this.clientCacheSize;
    }

    public final WebSocket.Factory getWebSocketFactory() {
        return this.webSocketFactory;
    }

    public final void config(final Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        final Function1 function1 = this.config;
        this.config = new Function1() { // from class: io.ktor.client.engine.okhttp.OkHttpConfig.config.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((OkHttpClient.Builder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(OkHttpClient.Builder builder) {
                Intrinsics.checkNotNullParameter(builder, "$this$null");
                function1.invoke(builder);
                block.invoke(builder);
            }
        };
    }
}
