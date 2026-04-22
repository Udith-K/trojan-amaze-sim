package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RouteException.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class RouteException extends RuntimeException {
    private final IOException firstConnectException;
    private IOException lastConnectException;

    public final IOException getFirstConnectException() {
        return this.firstConnectException;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RouteException(IOException firstConnectException) {
        super(firstConnectException);
        Intrinsics.checkNotNullParameter(firstConnectException, "firstConnectException");
        this.firstConnectException = firstConnectException;
        this.lastConnectException = firstConnectException;
    }

    public final IOException getLastConnectException() {
        return this.lastConnectException;
    }

    public final void addConnectException(IOException e) {
        Intrinsics.checkNotNullParameter(e, "e");
        ExceptionsKt.addSuppressed(this.firstConnectException, e);
        this.lastConnectException = e;
    }
}
