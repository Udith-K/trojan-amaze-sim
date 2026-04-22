package io.ktor.utils.io.pool;

import java.io.Closeable;

/* JADX INFO: compiled from: Pool.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ObjectPool extends Closeable {
    Object borrow();

    void dispose();

    void recycle(Object obj);

    /* JADX INFO: compiled from: Pool.kt */
    public static final class DefaultImpls {
        public static void close(ObjectPool objectPool) {
            objectPool.dispose();
        }
    }
}
