package io.ktor.utils.io.internal;

import io.ktor.utils.io.ByteBufferChannel;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WriteSessionImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WriteSessionImpl {
    private ByteBuffer byteBuffer;
    private ByteBufferChannel current;
    private RingBufferCapacity ringBufferCapacity;
    private ChunkBuffer view;

    public WriteSessionImpl(ByteBufferChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        this.current = channel.resolveChannelInstance$ktor_io();
        ChunkBuffer.Companion companion = ChunkBuffer.Companion;
        this.byteBuffer = companion.getEmpty().m2621getMemorySK3TCg8();
        this.view = companion.getEmpty();
        this.ringBufferCapacity = this.current.currentState$ktor_io().capacity;
    }
}
