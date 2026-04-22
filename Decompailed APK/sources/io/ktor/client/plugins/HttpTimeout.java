package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.util.AttributeKey;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpTimeout.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpTimeout {
    public static final Plugin Plugin = new Plugin(null);
    private static final AttributeKey key = new AttributeKey("TimeoutPlugin");
    private final Long connectTimeoutMillis;
    private final Long requestTimeoutMillis;
    private final Long socketTimeoutMillis;

    public /* synthetic */ HttpTimeout(Long l, Long l2, Long l3, DefaultConstructorMarker defaultConstructorMarker) {
        this(l, l2, l3);
    }

    private HttpTimeout(Long l, Long l2, Long l3) {
        this.requestTimeoutMillis = l;
        this.connectTimeoutMillis = l2;
        this.socketTimeoutMillis = l3;
    }

    /* JADX INFO: compiled from: HttpTimeout.kt */
    public static final class HttpTimeoutCapabilityConfiguration {
        public static final Companion Companion = new Companion(null);
        private static final AttributeKey key = new AttributeKey("TimeoutConfiguration");
        private Long _connectTimeoutMillis;
        private Long _requestTimeoutMillis;
        private Long _socketTimeoutMillis;

        public /* synthetic */ HttpTimeoutCapabilityConfiguration(Long l, Long l2, Long l3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : l2, (i & 4) != 0 ? null : l3);
        }

        public HttpTimeoutCapabilityConfiguration(Long l, Long l2, Long l3) {
            this._requestTimeoutMillis = 0L;
            this._connectTimeoutMillis = 0L;
            this._socketTimeoutMillis = 0L;
            setRequestTimeoutMillis(l);
            setConnectTimeoutMillis(l2);
            setSocketTimeoutMillis(l3);
        }

        public final Long getRequestTimeoutMillis() {
            return this._requestTimeoutMillis;
        }

        public final void setRequestTimeoutMillis(Long l) {
            this._requestTimeoutMillis = checkTimeoutValue(l);
        }

        public final Long getConnectTimeoutMillis() {
            return this._connectTimeoutMillis;
        }

        public final void setConnectTimeoutMillis(Long l) {
            this._connectTimeoutMillis = checkTimeoutValue(l);
        }

        public final Long getSocketTimeoutMillis() {
            return this._socketTimeoutMillis;
        }

        public final void setSocketTimeoutMillis(Long l) {
            this._socketTimeoutMillis = checkTimeoutValue(l);
        }

        public final HttpTimeout build$ktor_client_core() {
            return new HttpTimeout(getRequestTimeoutMillis(), getConnectTimeoutMillis(), getSocketTimeoutMillis(), null);
        }

        private final Long checkTimeoutValue(Long l) {
            if (l == null || l.longValue() > 0) {
                return l;
            }
            throw new IllegalArgumentException("Only positive timeout values are allowed, for infinite timeout use HttpTimeout.INFINITE_TIMEOUT_MS");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || HttpTimeoutCapabilityConfiguration.class != obj.getClass()) {
                return false;
            }
            HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeoutCapabilityConfiguration) obj;
            return Intrinsics.areEqual(this._requestTimeoutMillis, httpTimeoutCapabilityConfiguration._requestTimeoutMillis) && Intrinsics.areEqual(this._connectTimeoutMillis, httpTimeoutCapabilityConfiguration._connectTimeoutMillis) && Intrinsics.areEqual(this._socketTimeoutMillis, httpTimeoutCapabilityConfiguration._socketTimeoutMillis);
        }

        public int hashCode() {
            Long l = this._requestTimeoutMillis;
            int iHashCode = (l != null ? l.hashCode() : 0) * 31;
            Long l2 = this._connectTimeoutMillis;
            int iHashCode2 = (iHashCode + (l2 != null ? l2.hashCode() : 0)) * 31;
            Long l3 = this._socketTimeoutMillis;
            return iHashCode2 + (l3 != null ? l3.hashCode() : 0);
        }

        /* JADX INFO: compiled from: HttpTimeout.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasNotNullTimeouts() {
        return (this.requestTimeoutMillis == null && this.connectTimeoutMillis == null && this.socketTimeoutMillis == null) ? false : true;
    }

    /* JADX INFO: compiled from: HttpTimeout.kt */
    public static final class Plugin implements HttpClientPlugin, HttpClientEngineCapability {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey getKey() {
            return HttpTimeout.key;
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpTimeout prepare(Function1 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = new HttpTimeoutCapabilityConfiguration(null, null, null, 7, null);
            block.invoke(httpTimeoutCapabilityConfiguration);
            return httpTimeoutCapabilityConfiguration.build$ktor_client_core();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpTimeout plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            ((HttpSend) HttpClientPluginKt.plugin(scope, HttpSend.Plugin)).intercept(new HttpTimeout$Plugin$install$1(plugin, scope, null));
        }
    }
}
