package info.guardianproject.netcipher;

import android.os.Build;
import android.util.Log;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: loaded from: classes.dex */
public abstract class NetCipher {
    public static final Proxy ORBOT_HTTP_PROXY = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Preferences.DEFAULT_PROXY_HOST, Preferences.DEFAULT_PROXY_PORT));
    public static final Proxy ORBOT_SOCKS_PROXY = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(Preferences.DEFAULT_PROXY_HOST, 9050));
    private static Proxy proxy;

    public static void setProxy(Proxy proxy2) {
        if (proxy2 != null && proxy == ORBOT_HTTP_PROXY) {
            Log.w("NetCipher", "useTor is enabled, ignoring new proxy settings!");
        } else {
            proxy = proxy2;
        }
    }

    public static Proxy getProxy() {
        return proxy;
    }

    public static void clearProxy() {
        setProxy(null);
    }

    public static void useTor() {
        if (Build.VERSION.SDK_INT < 24) {
            setProxy(ORBOT_HTTP_PROXY);
        } else {
            setProxy(ORBOT_SOCKS_PROXY);
        }
    }
}
