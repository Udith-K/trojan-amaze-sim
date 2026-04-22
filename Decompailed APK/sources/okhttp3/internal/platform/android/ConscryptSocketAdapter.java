package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.ConscryptPlatform;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import org.conscrypt.Conscrypt;

/* JADX INFO: compiled from: ConscryptSocketAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ConscryptSocketAdapter implements SocketAdapter {
    public static final Companion Companion = new Companion(null);
    private static final DeferredSocketAdapter.Factory factory = new DeferredSocketAdapter.Factory() { // from class: okhttp3.internal.platform.android.ConscryptSocketAdapter$Companion$factory$1
        @Override // okhttp3.internal.platform.android.DeferredSocketAdapter.Factory
        public boolean matchesSocket(SSLSocket sslSocket) {
            Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
            return ConscryptPlatform.Companion.isSupported() && Conscrypt.isConscrypt(sslSocket);
        }

        @Override // okhttp3.internal.platform.android.DeferredSocketAdapter.Factory
        public SocketAdapter create(SSLSocket sslSocket) {
            Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
            return new ConscryptSocketAdapter();
        }
    };

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocket(SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return Conscrypt.isConscrypt(sslSocket);
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean isSupported() {
        return ConscryptPlatform.Companion.isSupported();
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public String getSelectedProtocol(SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        if (matchesSocket(sslSocket)) {
            return Conscrypt.getApplicationProtocol(sslSocket);
        }
        return null;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public void configureTlsExtensions(SSLSocket sslSocket, String str, List protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        if (matchesSocket(sslSocket)) {
            Conscrypt.setUseSessionTickets(sslSocket, true);
            Conscrypt.setApplicationProtocols(sslSocket, (String[]) Platform.Companion.alpnProtocolNames(protocols).toArray(new String[0]));
        }
    }

    /* JADX INFO: compiled from: ConscryptSocketAdapter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DeferredSocketAdapter.Factory getFactory() {
            return ConscryptSocketAdapter.factory;
        }
    }
}
