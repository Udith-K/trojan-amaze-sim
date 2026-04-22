package org.bouncycastle.pqc.crypto.cmce;

/* JADX INFO: loaded from: classes2.dex */
class GF12 extends GF {
    public GF12(int i) {
        super(i);
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.GF
    protected short gf_frac(short s, short s2) {
        return gf_mul(gf_inv(s), s2);
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.GF
    protected short gf_inv(short s) {
        short sGf_mul = gf_mul(gf_sq(s), s);
        short sGf_mul2 = gf_mul(gf_sq(gf_sq(sGf_mul)), sGf_mul);
        return gf_sq(gf_mul(gf_sq(gf_mul(gf_sq(gf_sq(gf_mul(gf_sq(gf_sq(gf_sq(gf_sq(sGf_mul2)))), sGf_mul2))), sGf_mul)), s));
    }

    @Override // org.bouncycastle.pqc.crypto.cmce.GF
    protected short gf_mul(short s, short s2) {
        int i = (s2 & 1) * s;
        int i2 = 1;
        while (true) {
            int i3 = this.GFBITS;
            if (i2 >= i3) {
                int i4 = 8372224 & i;
                int i5 = (i4 >> 12) ^ ((i4 >> 9) ^ i);
                int i6 = i5 & 12288;
                return (short) (((i5 ^ (i6 >> 9)) ^ (i6 >> 12)) & ((1 << i3) - 1));
            }
            i ^= ((1 << i2) & s2) * s;
            i2++;
        }
    }

    protected short gf_sq(short s) {
        int[] iArr = {1431655765, 858993459, 252645135, 16711935};
        int i = (s | (s << 8)) & iArr[3];
        int i2 = (i | (i << 4)) & iArr[2];
        int i3 = (i2 | (i2 << 2)) & iArr[1];
        int i4 = (i3 | (i3 << 1)) & iArr[0];
        int i5 = 8372224 & i4;
        int i6 = (i4 ^ (i5 >> 9)) ^ (i5 >> 12);
        int i7 = i6 & 12288;
        return (short) (((i6 ^ (i7 >> 9)) ^ (i7 >> 12)) & ((1 << this.GFBITS) - 1));
    }
}
