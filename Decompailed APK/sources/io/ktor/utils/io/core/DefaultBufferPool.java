package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.Allocator;
import io.ktor.utils.io.bits.DefaultAllocator;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.DefaultPool;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: BufferFactory.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DefaultBufferPool extends DefaultPool {
    private final Allocator allocator;
    private final int bufferSize;

    public /* synthetic */ DefaultBufferPool(int i, int i2, Allocator allocator, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? PKIFailureInfo.certConfirmed : i, (i3 & 2) != 0 ? 1000 : i2, (i3 & 4) != 0 ? DefaultAllocator.INSTANCE : allocator);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultBufferPool(int i, int i2, Allocator allocator) {
        super(i2);
        Intrinsics.checkNotNullParameter(allocator, "allocator");
        this.bufferSize = i;
        this.allocator = allocator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.ktor.utils.io.pool.DefaultPool
    public ChunkBuffer produceInstance() {
        return new ChunkBuffer(this.allocator.mo2612allocgFvZug(this.bufferSize), null, this, 0 == true ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.ktor.utils.io.pool.DefaultPool
    public void disposeInstance(ChunkBuffer instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        this.allocator.mo2613free3GNKZMM(instance.m2621getMemorySK3TCg8());
        super.disposeInstance((Object) instance);
        instance.unlink$ktor_io();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.ktor.utils.io.pool.DefaultPool
    public void validateInstance(ChunkBuffer instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        super.validateInstance((Object) instance);
        if (instance.m2621getMemorySK3TCg8().limit() != this.bufferSize) {
            StringBuilder sb = new StringBuilder();
            sb.append("Buffer size mismatch. Expected: ");
            sb.append(this.bufferSize);
            sb.append(", actual: ");
            sb.append(instance.m2621getMemorySK3TCg8().limit());
            throw new IllegalStateException(sb.toString().toString());
        }
        if (instance == ChunkBuffer.Companion.getEmpty()) {
            throw new IllegalStateException("ChunkBuffer.Empty couldn't be recycled");
        }
        if (instance == Buffer.Companion.getEmpty()) {
            throw new IllegalStateException("Empty instance couldn't be recycled");
        }
        if (instance.getReferenceCount() != 0) {
            throw new IllegalStateException("Unable to clear buffer: it is still in use.");
        }
        if (instance.getNext() != null) {
            throw new IllegalStateException("Recycled instance shouldn't be a part of a chain.");
        }
        if (instance.getOrigin() != null) {
            throw new IllegalStateException("Recycled instance shouldn't be a view or another buffer.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.ktor.utils.io.pool.DefaultPool
    public ChunkBuffer clearInstance(ChunkBuffer instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        ChunkBuffer chunkBuffer = (ChunkBuffer) super.clearInstance((Object) instance);
        chunkBuffer.unpark$ktor_io();
        chunkBuffer.reset();
        return chunkBuffer;
    }
}
