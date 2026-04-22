package io.ktor.utils.io.core;

import io.ktor.utils.io.pool.ObjectPool;

/* JADX INFO: compiled from: BufferFactory.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BufferFactoryKt {
    private static final ObjectPool DefaultChunkedBufferPool = new DefaultBufferPool(0, 0, null, 7, null);

    public static final ObjectPool getDefaultChunkedBufferPool() {
        return DefaultChunkedBufferPool;
    }
}
