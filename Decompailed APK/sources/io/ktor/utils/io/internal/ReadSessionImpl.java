package io.ktor.utils.io.internal;

import io.ktor.utils.io.ByteBufferChannel;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReadSessionImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ReadSessionImpl {
    private final ByteBufferChannel channel;
    private ChunkBuffer lastView;

    public ReadSessionImpl(ByteBufferChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        this.channel = channel;
        this.lastView = ChunkBuffer.Companion.getEmpty();
    }
}
