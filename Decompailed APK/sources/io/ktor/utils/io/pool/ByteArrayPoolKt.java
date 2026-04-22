package io.ktor.utils.io.pool;

import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: ByteArrayPool.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ByteArrayPoolKt {
    private static final ObjectPool ByteArrayPool = new DefaultPool() { // from class: io.ktor.utils.io.pool.ByteArrayPoolKt$ByteArrayPool$1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.ktor.utils.io.pool.DefaultPool
        public byte[] produceInstance() {
            return new byte[PKIFailureInfo.certConfirmed];
        }
    };

    public static final ObjectPool getByteArrayPool() {
        return ByteArrayPool;
    }
}
