package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes2.dex */
public class SipHash128 extends SipHash {
    public SipHash128() {
    }

    public SipHash128(int i, int i2) {
        super(i, i2);
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, DataLengthException {
        long j = this.m;
        int i2 = this.wordPos;
        this.m = ((j >>> ((7 - i2) << 3)) >>> 8) | ((((long) ((this.wordCount << 3) + i2)) & 255) << 56);
        processMessageWord();
        this.v2 ^= 238;
        applySipRounds(this.d);
        long j2 = this.v0;
        long j3 = this.v1;
        long j4 = ((j2 ^ j3) ^ this.v2) ^ this.v3;
        this.v1 = j3 ^ 221;
        applySipRounds(this.d);
        long j5 = ((this.v0 ^ this.v1) ^ this.v2) ^ this.v3;
        reset();
        Pack.longToLittleEndian(j4, bArr, i);
        Pack.longToLittleEndian(j5, bArr, i + 8);
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash
    public long doFinal() throws IllegalStateException, DataLengthException {
        throw new UnsupportedOperationException("doFinal() is not supported");
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash128-" + this.c + "-" + this.d;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public void reset() {
        super.reset();
        this.v1 ^= 238;
    }
}
