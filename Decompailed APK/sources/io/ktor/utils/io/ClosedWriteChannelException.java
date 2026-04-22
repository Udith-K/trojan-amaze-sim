package io.ktor.utils.io;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: ByteWriteChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ClosedWriteChannelException extends CancellationException {
    public ClosedWriteChannelException(String str) {
        super(str);
    }
}
