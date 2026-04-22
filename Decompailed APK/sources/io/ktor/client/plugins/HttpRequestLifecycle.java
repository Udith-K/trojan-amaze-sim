package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.util.AttributeKey;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpRequestLifecycle.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpRequestLifecycle {
    public static final Plugin Plugin = new Plugin(null);
    private static final AttributeKey key = new AttributeKey("RequestLifecycle");

    public /* synthetic */ HttpRequestLifecycle(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private HttpRequestLifecycle() {
    }

    /* JADX INFO: compiled from: HttpRequestLifecycle.kt */
    public static final class Plugin implements HttpClientPlugin {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey getKey() {
            return HttpRequestLifecycle.key;
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpRequestLifecycle prepare(Function1 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return new HttpRequestLifecycle(null);
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpRequestLifecycle plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            scope.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getBefore(), new HttpRequestLifecycle$Plugin$install$1(scope, null));
        }
    }
}
