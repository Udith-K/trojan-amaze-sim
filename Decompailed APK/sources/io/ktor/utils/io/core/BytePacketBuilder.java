package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BytePacketBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BytePacketBuilder extends Output {
    @Override // io.ktor.utils.io.core.Output
    protected final void closeDestination() {
    }

    @Override // io.ktor.utils.io.core.Output
    /* JADX INFO: renamed from: flush-62zg_DM, reason: not valid java name */
    protected final void mo2622flush62zg_DM(ByteBuffer source, int i, int i2) {
        Intrinsics.checkNotNullParameter(source, "source");
    }

    public /* synthetic */ BytePacketBuilder(ObjectPool objectPool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ChunkBuffer.Companion.getPool() : objectPool);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BytePacketBuilder(ObjectPool pool) {
        super(pool);
        Intrinsics.checkNotNullParameter(pool, "pool");
    }

    public final int getSize() {
        return get_size();
    }

    @Override // java.lang.Appendable
    public BytePacketBuilder append(char c) {
        Output outputAppend = super.append(c);
        Intrinsics.checkNotNull(outputAppend, "null cannot be cast to non-null type io.ktor.utils.io.core.BytePacketBuilder");
        return (BytePacketBuilder) outputAppend;
    }

    @Override // java.lang.Appendable
    public BytePacketBuilder append(CharSequence charSequence) {
        Output outputAppend = super.append(charSequence);
        Intrinsics.checkNotNull(outputAppend, "null cannot be cast to non-null type io.ktor.utils.io.core.BytePacketBuilder");
        return (BytePacketBuilder) outputAppend;
    }

    @Override // java.lang.Appendable
    public BytePacketBuilder append(CharSequence charSequence, int i, int i2) {
        Output outputAppend = super.append(charSequence, i, i2);
        Intrinsics.checkNotNull(outputAppend, "null cannot be cast to non-null type io.ktor.utils.io.core.BytePacketBuilder");
        return (BytePacketBuilder) outputAppend;
    }

    public final ByteReadPacket build() {
        int size = getSize();
        ChunkBuffer chunkBufferStealAll$ktor_io = stealAll$ktor_io();
        if (chunkBufferStealAll$ktor_io == null) {
            return ByteReadPacket.Companion.getEmpty();
        }
        return new ByteReadPacket(chunkBufferStealAll$ktor_io, size, getPool());
    }

    public String toString() {
        return "BytePacketBuilder[0x" + hashCode() + ']';
    }
}
