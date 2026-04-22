package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.security.auth.Destroyable;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class HybridValueParameterSpec implements AlgorithmParameterSpec, Destroyable {
    private volatile AlgorithmParameterSpec baseSpec;
    private final AtomicBoolean hasBeenDestroyed = new AtomicBoolean(false);
    private volatile byte[] t;

    public HybridValueParameterSpec(byte[] bArr, AlgorithmParameterSpec algorithmParameterSpec) {
        this.t = bArr;
        this.baseSpec = algorithmParameterSpec;
    }

    private void checkDestroyed() {
        if (isDestroyed()) {
            throw new IllegalStateException("spec has been destroyed");
        }
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        if (this.hasBeenDestroyed.getAndSet(true)) {
            return;
        }
        Arrays.clear(this.t);
        this.t = null;
        this.baseSpec = null;
    }

    public AlgorithmParameterSpec getBaseParameterSpec() {
        checkDestroyed();
        return this.baseSpec;
    }

    public byte[] getT() {
        checkDestroyed();
        return this.t;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.hasBeenDestroyed.get();
    }
}
