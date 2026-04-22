package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: SocketAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface SocketAdapter {
    void configureTlsExtensions(SSLSocket sSLSocket, String str, List list);

    String getSelectedProtocol(SSLSocket sSLSocket);

    boolean isSupported();

    boolean matchesSocket(SSLSocket sSLSocket);
}
