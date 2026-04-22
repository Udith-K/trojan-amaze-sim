package org.bouncycastle.asn1.x9;

import org.bouncycastle.math.ec.ECCurve;

/* JADX INFO: loaded from: classes2.dex */
public abstract class X9ECParametersHolder {
    private ECCurve curve;
    private X9ECParameters params;

    protected ECCurve createCurve() {
        return createParameters().getCurve();
    }

    protected abstract X9ECParameters createParameters();

    public synchronized ECCurve getCurve() {
        try {
            if (this.curve == null) {
                this.curve = createCurve();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.curve;
    }

    public synchronized X9ECParameters getParameters() {
        try {
            if (this.params == null) {
                this.params = createParameters();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.params;
    }
}
