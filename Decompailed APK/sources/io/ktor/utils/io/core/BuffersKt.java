package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Buffers.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BuffersKt {
    public static final void releaseAll(ChunkBuffer chunkBuffer, ObjectPool pool) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        while (chunkBuffer != null) {
            ChunkBuffer chunkBufferCleanNext = chunkBuffer.cleanNext();
            chunkBuffer.release(pool);
            chunkBuffer = chunkBufferCleanNext;
        }
    }

    public static final ChunkBuffer findTail(ChunkBuffer chunkBuffer) {
        Intrinsics.checkNotNullParameter(chunkBuffer, "<this>");
        while (true) {
            ChunkBuffer next = chunkBuffer.getNext();
            if (next == null) {
                return chunkBuffer;
            }
            chunkBuffer = next;
        }
    }

    private static final long remainingAll(ChunkBuffer chunkBuffer, long j) {
        do {
            j += (long) (chunkBuffer.getWritePosition() - chunkBuffer.getReadPosition());
            chunkBuffer = chunkBuffer.getNext();
        } while (chunkBuffer != null);
        return j;
    }

    public static final long remainingAll(ChunkBuffer chunkBuffer) {
        Intrinsics.checkNotNullParameter(chunkBuffer, "<this>");
        return remainingAll(chunkBuffer, 0L);
    }
}
