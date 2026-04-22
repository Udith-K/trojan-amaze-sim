package okhttp3;

import ch.qos.logback.core.CoreConstants;
import java.nio.charset.Charset;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;

/* JADX INFO: compiled from: Credentials.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class Credentials {
    public static final Credentials INSTANCE = new Credentials();

    private Credentials() {
    }

    public static final String basic(String username, String password, Charset charset) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return "Basic " + ByteString.Companion.encodeString(username + CoreConstants.COLON_CHAR + password, charset).base64();
    }
}
