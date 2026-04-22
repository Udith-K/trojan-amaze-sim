package io.ktor.utils.io.pool;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteBufferPools.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DirectByteBufferPool extends DefaultPool {
    private final int bufferSize;

    public DirectByteBufferPool(int i, int i2) {
        super(i);
        this.bufferSize = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.ktor.utils.io.pool.DefaultPool
    public ByteBuffer produceInstance() {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(this.bufferSize);
        Intrinsics.checkNotNull(byteBufferAllocateDirect);
        return byteBufferAllocateDirect;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.ktor.utils.io.pool.DefaultPool
    public ByteBuffer clearInstance(ByteBuffer instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        instance.clear();
        instance.order(ByteOrder.BIG_ENDIAN);
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.ktor.utils.io.pool.DefaultPool
    public void validateInstance(ByteBuffer instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        if (instance.capacity() != this.bufferSize) {
            throw new IllegalStateException("Check failed.");
        }
        if (!instance.isDirect()) {
            throw new IllegalStateException("Check failed.");
        }
    }
}
