package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.util.AttributeKey;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserAgent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UserAgent {
    public static final Plugin Plugin = new Plugin(null);
    private static final AttributeKey key = new AttributeKey("UserAgent");
    private final String agent;

    public /* synthetic */ UserAgent(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private UserAgent(String str) {
        this.agent = str;
    }

    public final String getAgent() {
        return this.agent;
    }

    /* JADX INFO: compiled from: UserAgent.kt */
    public static final class Config {
        private String agent;

        public Config(String agent) {
            Intrinsics.checkNotNullParameter(agent, "agent");
            this.agent = agent;
        }

        public /* synthetic */ Config(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "Ktor http-client" : str);
        }

        public final String getAgent() {
            return this.agent;
        }

        public final void setAgent(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.agent = str;
        }
    }

    /* JADX INFO: compiled from: UserAgent.kt */
    public static final class Plugin implements HttpClientPlugin {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey getKey() {
            return UserAgent.key;
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public UserAgent prepare(Function1 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Config config = new Config(null, 1, 0 == true ? 1 : 0);
            block.invoke(config);
            return new UserAgent(config.getAgent(), 0 == true ? 1 : 0);
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(UserAgent plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            scope.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getState(), new UserAgent$Plugin$install$1(plugin, null));
        }
    }
}
