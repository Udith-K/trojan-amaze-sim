package okhttp3.internal.tls;

import java.util.List;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.Platform;

/* JADX INFO: compiled from: CertificateChainCleaner.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class CertificateChainCleaner {
    public static final Companion Companion = new Companion(null);

    public abstract List clean(List list, String str);

    /* JADX INFO: compiled from: CertificateChainCleaner.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CertificateChainCleaner get(X509TrustManager trustManager) {
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            return Platform.Companion.get().buildCertificateChainCleaner(trustManager);
        }
    }
}
