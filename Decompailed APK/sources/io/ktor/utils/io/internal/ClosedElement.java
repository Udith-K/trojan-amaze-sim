package io.ktor.utils.io.internal;

import io.ktor.utils.io.ClosedWriteChannelException;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ByteBufferChannelInternals.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ClosedElement {
    public static final Companion Companion = new Companion(null);
    private static final ClosedElement EmptyCause = new ClosedElement(null);
    private final Throwable cause;

    public ClosedElement(Throwable th) {
        this.cause = th;
    }

    public final Throwable getCause() {
        return this.cause;
    }

    public final Throwable getSendException() {
        Throwable th = this.cause;
        return th == null ? new ClosedWriteChannelException("The channel was closed") : th;
    }

    public String toString() {
        return "Closed[" + getSendException() + ']';
    }

    /* JADX INFO: compiled from: ByteBufferChannelInternals.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ClosedElement getEmptyCause() {
            return ClosedElement.EmptyCause;
        }
    }
}
