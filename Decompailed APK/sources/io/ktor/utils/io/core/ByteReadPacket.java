package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteReadPacket.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ByteReadPacket extends Input {
    public static final Companion Companion = new Companion(null);
    private static final ByteReadPacket Empty;

    @Override // io.ktor.utils.io.core.Input
    protected final void closeSource() {
    }

    @Override // io.ktor.utils.io.core.Input
    protected final ChunkBuffer fill() {
        return null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteReadPacket(ChunkBuffer head, long j, ObjectPool pool) {
        super(head, j, pool);
        Intrinsics.checkNotNullParameter(head, "head");
        Intrinsics.checkNotNullParameter(pool, "pool");
        markNoMoreChunksAvailable();
    }

    public String toString() {
        return "ByteReadPacket[" + hashCode() + ']';
    }

    /* JADX INFO: compiled from: ByteReadPacket.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ByteReadPacket getEmpty() {
            return ByteReadPacket.Empty;
        }
    }

    static {
        ChunkBuffer.Companion companion = ChunkBuffer.Companion;
        Empty = new ByteReadPacket(companion.getEmpty(), 0L, companion.getEmptyPool());
    }
}
