package org.fdroid.download;

import io.ktor.client.engine.ProxyConfigJvmKt;
import io.ktor.client.engine.ProxyType;
import io.ktor.http.IpParserKt;
import io.ktor.util.network.NetworkAddressJvmKt;
import java.net.Proxy;
import java.net.SocketAddress;
import kotlin.Metadata;

/* JADX INFO: compiled from: Proxy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"DEFAULT_PROXY_HOST", "", "DEFAULT_PROXY_HTTP_PORT", "", "DEFAULT_PROXY_SOCKS_PORT", "isTor", "", "Ljava/net/Proxy;", "Lio/ktor/client/engine/ProxyConfig;", "download_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ProxyKt {
    private static final String DEFAULT_PROXY_HOST = "127.0.0.1";
    private static final int DEFAULT_PROXY_HTTP_PORT = 8118;
    private static final int DEFAULT_PROXY_SOCKS_PORT = 9050;

    public static final boolean isTor(Proxy proxy) {
        if (proxy == null || !IpParserKt.hostIsIp("127.0.0.1")) {
            return false;
        }
        SocketAddress socketAddressResolveAddress = ProxyConfigJvmKt.resolveAddress(proxy);
        return (ProxyConfigJvmKt.getType(proxy) == ProxyType.HTTP && NetworkAddressJvmKt.getPort(socketAddressResolveAddress) == 8118) || (ProxyConfigJvmKt.getType(proxy) == ProxyType.SOCKS && NetworkAddressJvmKt.getPort(socketAddressResolveAddress) == DEFAULT_PROXY_SOCKS_PORT);
    }
}
