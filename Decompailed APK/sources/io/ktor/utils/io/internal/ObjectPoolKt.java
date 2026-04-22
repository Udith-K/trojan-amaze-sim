package io.ktor.utils.io.internal;

import io.ktor.utils.io.internal.ReadWriteBufferState;
import io.ktor.utils.io.pool.DefaultPool;
import io.ktor.utils.io.pool.DirectByteBufferPool;
import io.ktor.utils.io.pool.NoPoolImpl;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: ObjectPool.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ObjectPoolKt {
    private static final int BUFFER_OBJECT_POOL_SIZE;
    private static final int BUFFER_POOL_SIZE;
    private static final int BUFFER_SIZE;
    private static final ObjectPool BufferObjectNoPool;
    private static final ObjectPool BufferObjectPool;
    private static final ObjectPool BufferPool;

    static {
        int iOIntProperty = UtilsKt.getIOIntProperty("BufferSize", PKIFailureInfo.certConfirmed);
        BUFFER_SIZE = iOIntProperty;
        int iOIntProperty2 = UtilsKt.getIOIntProperty("BufferPoolSize", 2048);
        BUFFER_POOL_SIZE = iOIntProperty2;
        final int iOIntProperty3 = UtilsKt.getIOIntProperty("BufferObjectPoolSize", 1024);
        BUFFER_OBJECT_POOL_SIZE = iOIntProperty3;
        BufferPool = new DirectByteBufferPool(iOIntProperty2, iOIntProperty);
        BufferObjectPool = new DefaultPool(iOIntProperty3) { // from class: io.ktor.utils.io.internal.ObjectPoolKt$BufferObjectPool$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.ktor.utils.io.pool.DefaultPool
            public ReadWriteBufferState.Initial produceInstance() {
                return new ReadWriteBufferState.Initial((ByteBuffer) ObjectPoolKt.getBufferPool().borrow(), 0, 2, null);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // io.ktor.utils.io.pool.DefaultPool
            public void disposeInstance(ReadWriteBufferState.Initial instance) {
                Intrinsics.checkNotNullParameter(instance, "instance");
                ObjectPoolKt.getBufferPool().recycle(instance.backingBuffer);
            }
        };
        BufferObjectNoPool = new NoPoolImpl() { // from class: io.ktor.utils.io.internal.ObjectPoolKt$BufferObjectNoPool$1
            @Override // io.ktor.utils.io.pool.ObjectPool
            public ReadWriteBufferState.Initial borrow() {
                ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(ObjectPoolKt.getBUFFER_SIZE());
                Intrinsics.checkNotNullExpressionValue(byteBufferAllocateDirect, "allocateDirect(BUFFER_SIZE)");
                return new ReadWriteBufferState.Initial(byteBufferAllocateDirect, 0, 2, null);
            }
        };
    }

    public static final int getBUFFER_SIZE() {
        return BUFFER_SIZE;
    }

    public static final ObjectPool getBufferPool() {
        return BufferPool;
    }

    public static final ObjectPool getBufferObjectPool() {
        return BufferObjectPool;
    }

    public static final ObjectPool getBufferObjectNoPool() {
        return BufferObjectNoPool;
    }
}
