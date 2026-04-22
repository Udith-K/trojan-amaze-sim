package io.ktor.client;

import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.PlatformUtils;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpClientConfig.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpClientConfig {
    private boolean expectSuccess;
    private final Map plugins = new LinkedHashMap();
    private final Map pluginConfigurations = new LinkedHashMap();
    private final Map customInterceptors = new LinkedHashMap();
    private Function1 engineConfig = new Function1() { // from class: io.ktor.client.HttpClientConfig$engineConfig$1
        public final void invoke(HttpClientEngineConfig httpClientEngineConfig) {
            Intrinsics.checkNotNullParameter(httpClientEngineConfig, "$this$null");
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpClientEngineConfig) obj);
            return Unit.INSTANCE;
        }
    };
    private boolean followRedirects = true;
    private boolean useDefaultTransformers = true;
    private boolean developmentMode = PlatformUtils.INSTANCE.getIS_DEVELOPMENT_MODE();

    public final Function1 getEngineConfig$ktor_client_core() {
        return this.engineConfig;
    }

    public final void engine(final Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        final Function1 function1 = this.engineConfig;
        this.engineConfig = new Function1() { // from class: io.ktor.client.HttpClientConfig.engine.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((HttpClientEngineConfig) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(HttpClientEngineConfig httpClientEngineConfig) {
                Intrinsics.checkNotNullParameter(httpClientEngineConfig, "$this$null");
                function1.invoke(httpClientEngineConfig);
                block.invoke(httpClientEngineConfig);
            }
        };
    }

    public final boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public final void setFollowRedirects(boolean z) {
        this.followRedirects = z;
    }

    public final boolean getUseDefaultTransformers() {
        return this.useDefaultTransformers;
    }

    public final boolean getExpectSuccess() {
        return this.expectSuccess;
    }

    public final void setExpectSuccess(boolean z) {
        this.expectSuccess = z;
    }

    public final boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public static /* synthetic */ void install$default(HttpClientConfig httpClientConfig, HttpClientPlugin httpClientPlugin, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: io.ktor.client.HttpClientConfig.install.1
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m2610invoke(Object obj2) {
                    Intrinsics.checkNotNullParameter(obj2, "$this$null");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    m2610invoke(obj2);
                    return Unit.INSTANCE;
                }
            };
        }
        httpClientConfig.install(httpClientPlugin, function1);
    }

    public final void install(final HttpClientPlugin plugin, final Function1 configure) {
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        Intrinsics.checkNotNullParameter(configure, "configure");
        final Function1 function1 = (Function1) this.pluginConfigurations.get(plugin.getKey());
        this.pluginConfigurations.put(plugin.getKey(), new Function1() { // from class: io.ktor.client.HttpClientConfig.install.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m2611invoke(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m2611invoke(Object obj) {
                Intrinsics.checkNotNullParameter(obj, "$this$null");
                Function1 function12 = function1;
                if (function12 != null) {
                    function12.invoke(obj);
                }
                configure.invoke(obj);
            }
        });
        if (this.plugins.containsKey(plugin.getKey())) {
            return;
        }
        this.plugins.put(plugin.getKey(), new Function1() { // from class: io.ktor.client.HttpClientConfig.install.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((HttpClient) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(HttpClient scope) {
                Intrinsics.checkNotNullParameter(scope, "scope");
                Attributes attributes = (Attributes) scope.getAttributes().computeIfAbsent(HttpClientPluginKt.getPLUGIN_INSTALLED_LIST(), new Function0() { // from class: io.ktor.client.HttpClientConfig$install$3$attributes$1
                    @Override // kotlin.jvm.functions.Function0
                    public final Attributes invoke() {
                        return AttributesJvmKt.Attributes(true);
                    }
                });
                Object obj = scope.getConfig$ktor_client_core().pluginConfigurations.get(plugin.getKey());
                Intrinsics.checkNotNull(obj);
                Object objPrepare = plugin.prepare((Function1) obj);
                plugin.install(objPrepare, scope);
                attributes.put(plugin.getKey(), objPrepare);
            }
        });
    }

    public final void install(String key, Function1 block) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(block, "block");
        this.customInterceptors.put(key, block);
    }

    public final void install(HttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        Iterator it = this.plugins.values().iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(client);
        }
        Iterator it2 = this.customInterceptors.values().iterator();
        while (it2.hasNext()) {
            ((Function1) it2.next()).invoke(client);
        }
    }

    public final void plusAssign(HttpClientConfig other) {
        Intrinsics.checkNotNullParameter(other, "other");
        this.followRedirects = other.followRedirects;
        this.useDefaultTransformers = other.useDefaultTransformers;
        this.expectSuccess = other.expectSuccess;
        this.plugins.putAll(other.plugins);
        this.pluginConfigurations.putAll(other.pluginConfigurations);
        this.customInterceptors.putAll(other.customInterceptors);
    }
}
