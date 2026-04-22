package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes2.dex */
class WotsPlus {
    private final SPHINCSPlusEngine engine;
    private final int w;

    WotsPlus(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
        this.w = sPHINCSPlusEngine.WOTS_W;
    }

    int[] base_w(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i2];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            if (i3 == 0) {
                i6 = bArr[i4];
                i4++;
                i3 += 8;
            }
            i3 -= this.engine.WOTS_LOGW;
            iArr[i5] = (i6 >>> i3) & (i - 1);
            i5++;
        }
        return iArr;
    }

    byte[] chain(byte[] bArr, int i, int i2, byte[] bArr2, ADRS adrs) {
        if (i2 == 0) {
            return Arrays.clone(bArr);
        }
        int i3 = i + i2;
        if (i3 > this.w - 1) {
            return null;
        }
        byte[] bArrChain = chain(bArr, i, i2 - 1, bArr2, adrs);
        adrs.setHashAddress(i3 - 1);
        return this.engine.F(bArr2, adrs, bArrChain);
    }

    public byte[] pkFromSig(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        SPHINCSPlusEngine sPHINCSPlusEngine;
        ADRS adrs2 = new ADRS(adrs);
        int[] iArrBase_w = base_w(bArr2, this.w, this.engine.WOTS_LEN1);
        int i = 0;
        int i2 = 0;
        while (true) {
            sPHINCSPlusEngine = this.engine;
            if (i >= sPHINCSPlusEngine.WOTS_LEN1) {
                break;
            }
            i2 += (this.w - 1) - iArrBase_w[i];
            i++;
        }
        int i3 = sPHINCSPlusEngine.WOTS_LEN2;
        int i4 = sPHINCSPlusEngine.WOTS_LOGW;
        int[] iArrConcatenate = Arrays.concatenate(iArrBase_w, base_w(Arrays.copyOfRange(Pack.intToBigEndian(i2 << (8 - ((i3 * i4) % 8))), 4 - (((i3 * i4) + 7) / 8), 4), this.w, this.engine.WOTS_LEN2));
        SPHINCSPlusEngine sPHINCSPlusEngine2 = this.engine;
        byte[] bArr4 = new byte[sPHINCSPlusEngine2.N];
        byte[][] bArr5 = new byte[sPHINCSPlusEngine2.WOTS_LEN][];
        for (int i5 = 0; i5 < this.engine.WOTS_LEN; i5++) {
            adrs.setChainAddress(i5);
            int i6 = this.engine.N;
            System.arraycopy(bArr, i5 * i6, bArr4, 0, i6);
            int i7 = iArrConcatenate[i5];
            bArr5[i5] = chain(bArr4, i7, (this.w - 1) - i7, bArr3, adrs);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr3, adrs2, Arrays.concatenate(bArr5));
    }

    byte[] pkGen(byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        byte[][] bArr3 = new byte[this.engine.WOTS_LEN][];
        for (int i = 0; i < this.engine.WOTS_LEN; i++) {
            ADRS adrs3 = new ADRS(adrs);
            adrs3.setChainAddress(i);
            adrs3.setHashAddress(0);
            bArr3[i] = chain(this.engine.PRF(bArr2, bArr, adrs3), 0, this.w - 1, bArr2, adrs3);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr3));
    }

    public byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        SPHINCSPlusEngine sPHINCSPlusEngine;
        ADRS adrs2 = new ADRS(adrs);
        int[] iArrBase_w = base_w(bArr, this.w, this.engine.WOTS_LEN1);
        int i = 0;
        int i2 = 0;
        while (true) {
            sPHINCSPlusEngine = this.engine;
            if (i >= sPHINCSPlusEngine.WOTS_LEN1) {
                break;
            }
            i2 += (this.w - 1) - iArrBase_w[i];
            i++;
        }
        int i3 = sPHINCSPlusEngine.WOTS_LOGW;
        if (i3 % 8 != 0) {
            i2 <<= 8 - ((sPHINCSPlusEngine.WOTS_LEN2 * i3) % 8);
        }
        int i4 = ((sPHINCSPlusEngine.WOTS_LEN2 * i3) + 7) / 8;
        byte[] bArrIntToBigEndian = Pack.intToBigEndian(i2);
        int[] iArrConcatenate = Arrays.concatenate(iArrBase_w, base_w(Arrays.copyOfRange(bArrIntToBigEndian, i4, bArrIntToBigEndian.length), this.w, this.engine.WOTS_LEN2));
        byte[][] bArr4 = new byte[this.engine.WOTS_LEN][];
        for (int i5 = 0; i5 < this.engine.WOTS_LEN; i5++) {
            adrs2.setChainAddress(i5);
            adrs2.setHashAddress(0);
            bArr4[i5] = chain(this.engine.PRF(bArr3, bArr2, adrs2), 0, iArrConcatenate[i5], bArr3, adrs2);
        }
        return Arrays.concatenate(bArr4);
    }
}
