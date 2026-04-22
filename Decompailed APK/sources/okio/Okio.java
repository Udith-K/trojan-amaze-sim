package okio;

import java.io.InputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Okio {
    public static final BufferedSink buffer(Sink sink) {
        return Okio__OkioKt.buffer(sink);
    }

    public static final BufferedSource buffer(Source source) {
        return Okio__OkioKt.buffer(source);
    }

    public static final boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return Okio__JvmOkioKt.isAndroidGetsocknameError(assertionError);
    }

    public static final Sink sink(Socket socket) {
        return Okio__JvmOkioKt.sink(socket);
    }

    public static final Source source(InputStream inputStream) {
        return Okio__JvmOkioKt.source(inputStream);
    }

    public static final Source source(Socket socket) {
        return Okio__JvmOkioKt.source(socket);
    }
}
