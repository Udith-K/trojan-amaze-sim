package okio;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ForwardingSource.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ForwardingSource implements Source {
    private final Source delegate;

    public ForwardingSource(Source delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    public final Source delegate() {
        return this.delegate;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    public String toString() {
        return getClass().getSimpleName() + CoreConstants.LEFT_PARENTHESIS_CHAR + this.delegate + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
