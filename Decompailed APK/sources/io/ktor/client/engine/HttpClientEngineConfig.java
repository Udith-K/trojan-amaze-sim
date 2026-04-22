package io.ktor.client.engine;

import java.net.Proxy;

/* JADX INFO: compiled from: HttpClientEngineConfig.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpClientEngineConfig {
    private boolean pipelining;
    private Proxy proxy;
    private int threadsCount = 4;

    public final void setPipelining(boolean z) {
        this.pipelining = z;
    }

    public final Proxy getProxy() {
        return this.proxy;
    }

    public final void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
}
