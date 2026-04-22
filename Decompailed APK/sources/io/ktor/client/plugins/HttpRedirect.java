package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.events.EventDefinition;
import io.ktor.util.AttributeKey;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpRedirect.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpRedirect {
    private final boolean allowHttpsDowngrade;
    private final boolean checkHttpMethod;
    public static final Plugin Plugin = new Plugin(null);
    private static final AttributeKey key = new AttributeKey("HttpRedirect");
    private static final EventDefinition HttpResponseRedirect = new EventDefinition();

    public /* synthetic */ HttpRedirect(boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2);
    }

    private HttpRedirect(boolean z, boolean z2) {
        this.checkHttpMethod = z;
        this.allowHttpsDowngrade = z2;
    }

    /* JADX INFO: compiled from: HttpRedirect.kt */
    public static final class Config {
        private boolean allowHttpsDowngrade;
        private boolean checkHttpMethod = true;

        public final boolean getCheckHttpMethod() {
            return this.checkHttpMethod;
        }

        public final boolean getAllowHttpsDowngrade() {
            return this.allowHttpsDowngrade;
        }
    }

    /* JADX INFO: compiled from: HttpRedirect.kt */
    public static final class Plugin implements HttpClientPlugin {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey getKey() {
            return HttpRedirect.key;
        }

        public final EventDefinition getHttpResponseRedirect() {
            return HttpRedirect.HttpResponseRedirect;
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpRedirect prepare(Function1 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Config config = new Config();
            block.invoke(config);
            return new HttpRedirect(config.getCheckHttpMethod(), config.getAllowHttpsDowngrade(), null);
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpRedirect plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            ((HttpSend) HttpClientPluginKt.plugin(scope, HttpSend.Plugin)).intercept(new HttpRedirect$Plugin$install$1(plugin, scope, null));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:20:0x011a  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0169  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x01af A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x01b0  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x01ca  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x01cd  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x01b0 -> B:35:0x01b6). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object handleCall(io.ktor.client.plugins.Sender r19, io.ktor.client.request.HttpRequestBuilder r20, io.ktor.client.call.HttpClientCall r21, boolean r22, io.ktor.client.HttpClient r23, kotlin.coroutines.Continuation r24) {
            /*
                Method dump skipped, instruction units count: 469
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpRedirect.Plugin.handleCall(io.ktor.client.plugins.Sender, io.ktor.client.request.HttpRequestBuilder, io.ktor.client.call.HttpClientCall, boolean, io.ktor.client.HttpClient, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
