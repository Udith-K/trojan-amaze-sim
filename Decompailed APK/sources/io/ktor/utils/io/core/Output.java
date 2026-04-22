package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UTF8Kt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.Closeable;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.bouncycastle.asn1.BERTags;

/* JADX INFO: compiled from: Output.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Output implements Appendable, Closeable {
    private ChunkBuffer _head;
    private ChunkBuffer _tail;
    private int chainedSize;
    private final ObjectPool pool;
    private int tailEndExclusive;
    private int tailInitialPosition;
    private ByteBuffer tailMemory;
    private int tailPosition;

    protected abstract void closeDestination();

    /* JADX INFO: renamed from: flush-62zg_DM */
    protected abstract void mo2622flush62zg_DM(ByteBuffer byteBuffer, int i, int i2);

    public Output(ObjectPool pool) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.pool = pool;
        this.tailMemory = Memory.Companion.m2617getEmptySK3TCg8();
    }

    protected final ObjectPool getPool() {
        return this.pool;
    }

    protected final int get_size() {
        return this.chainedSize + (this.tailPosition - this.tailInitialPosition);
    }

    /* JADX INFO: renamed from: getTailMemory-SK3TCg8$ktor_io, reason: not valid java name */
    public final ByteBuffer m2624getTailMemorySK3TCg8$ktor_io() {
        return this.tailMemory;
    }

    public final int getTailPosition$ktor_io() {
        return this.tailPosition;
    }

    public final void setTailPosition$ktor_io(int i) {
        this.tailPosition = i;
    }

    public final int getTailEndExclusive$ktor_io() {
        return this.tailEndExclusive;
    }

    public final ChunkBuffer prepareWriteHead(int i) {
        ChunkBuffer chunkBuffer;
        if (getTailEndExclusive$ktor_io() - getTailPosition$ktor_io() >= i && (chunkBuffer = this._tail) != null) {
            chunkBuffer.commitWrittenUntilIndex(this.tailPosition);
            return chunkBuffer;
        }
        return appendNewChunk();
    }

    public final void flush() {
        flushChain();
    }

    private final void flushChain() {
        ChunkBuffer chunkBufferStealAll$ktor_io = stealAll$ktor_io();
        if (chunkBufferStealAll$ktor_io == null) {
            return;
        }
        ChunkBuffer next = chunkBufferStealAll$ktor_io;
        do {
            try {
                mo2622flush62zg_DM(next.m2621getMemorySK3TCg8(), next.getReadPosition(), next.getWritePosition() - next.getReadPosition());
                next = next.getNext();
            } finally {
                BuffersKt.releaseAll(chunkBufferStealAll$ktor_io, this.pool);
            }
        } while (next != null);
    }

    public final ChunkBuffer stealAll$ktor_io() {
        ChunkBuffer chunkBuffer = this._head;
        if (chunkBuffer == null) {
            return null;
        }
        ChunkBuffer chunkBuffer2 = this._tail;
        if (chunkBuffer2 != null) {
            chunkBuffer2.commitWrittenUntilIndex(this.tailPosition);
        }
        this._head = null;
        this._tail = null;
        this.tailPosition = 0;
        this.tailEndExclusive = 0;
        this.tailInitialPosition = 0;
        this.chainedSize = 0;
        this.tailMemory = Memory.Companion.m2617getEmptySK3TCg8();
        return chunkBuffer;
    }

    public final void appendSingleChunk$ktor_io(ChunkBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (buffer.getNext() != null) {
            throw new IllegalStateException("It should be a single buffer chunk.");
        }
        appendChainImpl(buffer, buffer, 0);
    }

    private final ChunkBuffer appendNewChunk() {
        ChunkBuffer chunkBuffer = (ChunkBuffer) this.pool.borrow();
        chunkBuffer.reserveEndGap(8);
        appendSingleChunk$ktor_io(chunkBuffer);
        return chunkBuffer;
    }

    private final void appendChainImpl(ChunkBuffer chunkBuffer, ChunkBuffer chunkBuffer2, int i) {
        ChunkBuffer chunkBuffer3 = this._tail;
        if (chunkBuffer3 == null) {
            this._head = chunkBuffer;
            this.chainedSize = 0;
        } else {
            chunkBuffer3.setNext(chunkBuffer);
            int i2 = this.tailPosition;
            chunkBuffer3.commitWrittenUntilIndex(i2);
            this.chainedSize += i2 - this.tailInitialPosition;
        }
        this._tail = chunkBuffer2;
        this.chainedSize += i;
        this.tailMemory = chunkBuffer2.m2621getMemorySK3TCg8();
        this.tailPosition = chunkBuffer2.getWritePosition();
        this.tailInitialPosition = chunkBuffer2.getReadPosition();
        this.tailEndExclusive = chunkBuffer2.getLimit();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            flush();
        } finally {
            closeDestination();
        }
    }

    @Override // java.lang.Appendable
    public Output append(char c) {
        int i = this.tailPosition;
        int i2 = 3;
        if (this.tailEndExclusive - i >= 3) {
            ByteBuffer byteBuffer = this.tailMemory;
            if (c >= 0 && c < 128) {
                byteBuffer.put(i, (byte) c);
                i2 = 1;
            } else if (128 <= c && c < 2048) {
                byteBuffer.put(i, (byte) (((c >> 6) & 31) | 192));
                byteBuffer.put(i + 1, (byte) ((c & '?') | 128));
                i2 = 2;
            } else if (2048 <= c && c < 0) {
                byteBuffer.put(i, (byte) (((c >> '\f') & 15) | BERTags.FLAGS));
                byteBuffer.put(i + 1, (byte) (((c >> 6) & 63) | 128));
                byteBuffer.put(i + 2, (byte) ((c & '?') | 128));
            } else {
                if (0 > c || c >= 0) {
                    UTF8Kt.malformedCodePoint(c);
                    throw new KotlinNothingValueException();
                }
                byteBuffer.put(i, (byte) (((c >> 18) & 7) | 240));
                byteBuffer.put(i + 1, (byte) (((c >> '\f') & 63) | 128));
                byteBuffer.put(i + 2, (byte) (((c >> 6) & 63) | 128));
                byteBuffer.put(i + 3, (byte) ((c & '?') | 128));
                i2 = 4;
            }
            this.tailPosition = i + i2;
            return this;
        }
        appendCharFallback(c);
        return this;
    }

    @Override // java.lang.Appendable
    public Output append(CharSequence charSequence) {
        if (charSequence == null) {
            append("null", 0, 4);
        } else {
            append(charSequence, 0, charSequence.length());
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Output append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            return append("null", i, i2);
        }
        StringsKt.writeText(this, charSequence, i, i2, Charsets.UTF_8);
        return this;
    }

    public final void release() {
        close();
    }

    public final void afterHeadWrite() {
        ChunkBuffer chunkBuffer = this._tail;
        if (chunkBuffer != null) {
            this.tailPosition = chunkBuffer.getWritePosition();
        }
    }

    private final void appendCharFallback(char c) {
        int i = 3;
        ChunkBuffer chunkBufferPrepareWriteHead = prepareWriteHead(3);
        try {
            ByteBuffer byteBufferM2621getMemorySK3TCg8 = chunkBufferPrepareWriteHead.m2621getMemorySK3TCg8();
            int writePosition = chunkBufferPrepareWriteHead.getWritePosition();
            if (c >= 0 && c < 128) {
                byteBufferM2621getMemorySK3TCg8.put(writePosition, (byte) c);
                i = 1;
            } else if (128 <= c && c < 2048) {
                byteBufferM2621getMemorySK3TCg8.put(writePosition, (byte) (((c >> 6) & 31) | 192));
                byteBufferM2621getMemorySK3TCg8.put(writePosition + 1, (byte) ((c & '?') | 128));
                i = 2;
            } else if (2048 <= c && c < 0) {
                byteBufferM2621getMemorySK3TCg8.put(writePosition, (byte) (((c >> '\f') & 15) | BERTags.FLAGS));
                byteBufferM2621getMemorySK3TCg8.put(writePosition + 1, (byte) (((c >> 6) & 63) | 128));
                byteBufferM2621getMemorySK3TCg8.put(writePosition + 2, (byte) ((c & '?') | 128));
            } else {
                if (0 > c || c >= 0) {
                    UTF8Kt.malformedCodePoint(c);
                    throw new KotlinNothingValueException();
                }
                byteBufferM2621getMemorySK3TCg8.put(writePosition, (byte) (((c >> 18) & 7) | 240));
                byteBufferM2621getMemorySK3TCg8.put(writePosition + 1, (byte) (((c >> '\f') & 63) | 128));
                byteBufferM2621getMemorySK3TCg8.put(writePosition + 2, (byte) (((c >> 6) & 63) | 128));
                byteBufferM2621getMemorySK3TCg8.put(writePosition + 3, (byte) ((c & '?') | 128));
                i = 4;
            }
            chunkBufferPrepareWriteHead.commitWritten(i);
            if (i < 0) {
                throw new IllegalStateException("The returned value shouldn't be negative");
            }
            afterHeadWrite();
        } catch (Throwable th) {
            afterHeadWrite();
            throw th;
        }
    }
}
