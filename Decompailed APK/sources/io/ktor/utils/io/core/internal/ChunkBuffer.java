package io.ktor.utils.io.core.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import io.ktor.utils.io.bits.DefaultAllocator;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BufferFactoryKt;
import io.ktor.utils.io.pool.NoPoolImpl;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: ChunkBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
public class ChunkBuffer extends Buffer {
    public static final Companion Companion = new Companion(null);
    private static final ChunkBuffer Empty;
    private static final ObjectPool EmptyPool;
    private static final ObjectPool NoPool;
    private static final ObjectPool NoPoolManuallyManaged;
    private static final /* synthetic */ AtomicReferenceFieldUpdater nextRef$FU;
    private static final /* synthetic */ AtomicIntegerFieldUpdater refCount$FU;
    private volatile /* synthetic */ Object nextRef;
    private ChunkBuffer origin;
    private final ObjectPool parentPool;
    private volatile /* synthetic */ int refCount;

    public /* synthetic */ ChunkBuffer(ByteBuffer byteBuffer, ChunkBuffer chunkBuffer, ObjectPool objectPool, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer, chunkBuffer, objectPool);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private ChunkBuffer(ByteBuffer memory, ChunkBuffer chunkBuffer, ObjectPool objectPool) {
        super(memory, null);
        Intrinsics.checkNotNullParameter(memory, "memory");
        this.parentPool = objectPool;
        if (chunkBuffer == this) {
            throw new IllegalArgumentException("A chunk couldn't be a view of itself.");
        }
        this.nextRef = null;
        this.refCount = 1;
        this.origin = chunkBuffer;
    }

    public final ChunkBuffer getOrigin() {
        return this.origin;
    }

    public final ChunkBuffer getNext() {
        return (ChunkBuffer) this.nextRef;
    }

    public final void setNext(ChunkBuffer chunkBuffer) {
        if (chunkBuffer == null) {
            cleanNext();
        } else {
            appendNext(chunkBuffer);
        }
    }

    public final int getReferenceCount() {
        return this.refCount;
    }

    private final void appendNext(ChunkBuffer chunkBuffer) {
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(nextRef$FU, this, null, chunkBuffer)) {
            throw new IllegalStateException("This chunk has already a next chunk.");
        }
    }

    public final ChunkBuffer cleanNext() {
        return (ChunkBuffer) nextRef$FU.getAndSet(this, null);
    }

    public void release(ObjectPool pool) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        if (release$ktor_io()) {
            ChunkBuffer chunkBuffer = this.origin;
            if (chunkBuffer != null) {
                unlink$ktor_io();
                chunkBuffer.release(pool);
            } else {
                ObjectPool objectPool = this.parentPool;
                if (objectPool != null) {
                    pool = objectPool;
                }
                pool.recycle(this);
            }
        }
    }

    public final void unlink$ktor_io() {
        if (!refCount$FU.compareAndSet(this, 0, -1)) {
            throw new IllegalStateException("Unable to unlink: buffer is in use.");
        }
        cleanNext();
        this.origin = null;
    }

    @Override // io.ktor.utils.io.core.Buffer
    public final void reset() {
        if (this.origin != null) {
            throw new IllegalArgumentException("Unable to reset buffer with origin");
        }
        super.reset();
        this.nextRef = null;
    }

    /* JADX INFO: compiled from: ChunkBuffer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ObjectPool getPool() {
            return BufferFactoryKt.getDefaultChunkedBufferPool();
        }

        public final ObjectPool getEmptyPool() {
            return ChunkBuffer.EmptyPool;
        }

        public final ChunkBuffer getEmpty() {
            return ChunkBuffer.Empty;
        }
    }

    static {
        ObjectPool objectPool = new ObjectPool() { // from class: io.ktor.utils.io.core.internal.ChunkBuffer$Companion$EmptyPool$1
            @Override // io.ktor.utils.io.pool.ObjectPool
            public void dispose() {
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                ObjectPool.DefaultImpls.close(this);
            }

            @Override // io.ktor.utils.io.pool.ObjectPool
            public ChunkBuffer borrow() {
                return ChunkBuffer.Companion.getEmpty();
            }

            @Override // io.ktor.utils.io.pool.ObjectPool
            public void recycle(ChunkBuffer instance) {
                Intrinsics.checkNotNullParameter(instance, "instance");
                if (instance != ChunkBuffer.Companion.getEmpty()) {
                    throw new IllegalArgumentException("Only ChunkBuffer.Empty instance could be recycled.");
                }
            }
        };
        EmptyPool = objectPool;
        Empty = new ChunkBuffer(Memory.Companion.m2617getEmptySK3TCg8(), 0 == true ? 1 : 0, objectPool, 0 == true ? 1 : 0);
        NoPool = new NoPoolImpl() { // from class: io.ktor.utils.io.core.internal.ChunkBuffer$Companion$NoPool$1
            @Override // io.ktor.utils.io.pool.ObjectPool
            public ChunkBuffer borrow() {
                return new ChunkBuffer(DefaultAllocator.INSTANCE.mo2612allocgFvZug(PKIFailureInfo.certConfirmed), null, this, 0 == true ? 1 : 0);
            }

            @Override // io.ktor.utils.io.pool.NoPoolImpl, io.ktor.utils.io.pool.ObjectPool
            public void recycle(ChunkBuffer instance) {
                Intrinsics.checkNotNullParameter(instance, "instance");
                DefaultAllocator.INSTANCE.mo2613free3GNKZMM(instance.m2621getMemorySK3TCg8());
            }
        };
        NoPoolManuallyManaged = new NoPoolImpl() { // from class: io.ktor.utils.io.core.internal.ChunkBuffer$Companion$NoPoolManuallyManaged$1
            @Override // io.ktor.utils.io.pool.NoPoolImpl, io.ktor.utils.io.pool.ObjectPool
            public void recycle(ChunkBuffer instance) {
                Intrinsics.checkNotNullParameter(instance, "instance");
            }

            @Override // io.ktor.utils.io.pool.ObjectPool
            public ChunkBuffer borrow() {
                throw new UnsupportedOperationException("This pool doesn't support borrow");
            }
        };
        nextRef$FU = AtomicReferenceFieldUpdater.newUpdater(ChunkBuffer.class, Object.class, "nextRef");
        refCount$FU = AtomicIntegerFieldUpdater.newUpdater(ChunkBuffer.class, "refCount");
    }

    public final void unpark$ktor_io() {
        int i;
        do {
            i = this.refCount;
            if (i < 0) {
                throw new IllegalStateException("This instance is already disposed and couldn't be borrowed.");
            }
            if (i > 0) {
                throw new IllegalStateException("This instance is already in use but somehow appeared in the pool.");
            }
        } while (!refCount$FU.compareAndSet(this, i, 1));
    }

    public final boolean release$ktor_io() {
        int i;
        int i2;
        do {
            i = this.refCount;
            if (i <= 0) {
                throw new IllegalStateException("Unable to release: it is already released.");
            }
            i2 = i - 1;
        } while (!refCount$FU.compareAndSet(this, i, i2));
        return i2 == 0;
    }
}
