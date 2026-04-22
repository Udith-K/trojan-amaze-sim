package io.ktor.utils.io.pool;

import io.ktor.utils.io.pool.ObjectPool;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pool.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class NoPoolImpl implements ObjectPool {
    @Override // io.ktor.utils.io.pool.ObjectPool
    public void dispose() {
    }

    @Override // io.ktor.utils.io.pool.ObjectPool
    public void recycle(Object instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ObjectPool.DefaultImpls.close(this);
    }
}
