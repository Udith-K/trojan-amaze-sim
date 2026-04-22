package org.bouncycastle.pqc.crypto.frodo;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Exceptions;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes2.dex */
abstract class FrodoMatrixGenerator {
    int n;
    int q;

    static class Aes128MatrixGenerator extends FrodoMatrixGenerator {
        BufferedBlockCipher cipher;

        public Aes128MatrixGenerator(int i, int i2) {
            super(i, i2);
            this.cipher = new BufferedBlockCipher(new AESEngine());
        }

        void aes128(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            try {
                this.cipher.init(true, new KeyParameter(bArr2));
                this.cipher.doFinal(bArr, this.cipher.processBytes(bArr3, 0, bArr3.length, bArr, 0));
            } catch (InvalidCipherTextException e) {
                throw Exceptions.illegalStateException(e.toString(), e);
            }
        }

        @Override // org.bouncycastle.pqc.crypto.frodo.FrodoMatrixGenerator
        short[] genMatrix(byte[] bArr) {
            int i = this.n;
            short[] sArr = new short[i * i];
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[16];
            for (int i2 = 0; i2 < this.n; i2++) {
                for (int i3 = 0; i3 < this.n; i3 += 8) {
                    System.arraycopy(Pack.shortToLittleEndian((short) (i2 & 65535)), 0, bArr2, 0, 2);
                    System.arraycopy(Pack.shortToLittleEndian((short) (65535 & i3)), 0, bArr2, 2, 2);
                    aes128(bArr3, bArr, bArr2);
                    for (int i4 = 0; i4 < 8; i4++) {
                        sArr[(this.n * i2) + i3 + i4] = (short) (Pack.littleEndianToShort(bArr3, i4 * 2) % this.q);
                    }
                }
            }
            return sArr;
        }
    }

    static class Shake128MatrixGenerator extends FrodoMatrixGenerator {
        public Shake128MatrixGenerator(int i, int i2) {
            super(i, i2);
        }

        @Override // org.bouncycastle.pqc.crypto.frodo.FrodoMatrixGenerator
        short[] genMatrix(byte[] bArr) {
            int i = this.n;
            short[] sArr = new short[i * i];
            int i2 = (i * 16) / 8;
            byte[] bArr2 = new byte[i2];
            for (short s = 0; s < this.n; s = (short) (s + 1)) {
                byte[] bArrConcatenate = Arrays.concatenate(Pack.shortToLittleEndian(s), bArr);
                SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
                sHAKEDigest.update(bArrConcatenate, 0, bArrConcatenate.length);
                sHAKEDigest.doFinal(bArr2, 0, i2);
                short s2 = 0;
                while (true) {
                    int i3 = this.n;
                    if (s2 < i3) {
                        sArr[(i3 * s) + s2] = (short) (Pack.littleEndianToShort(bArr2, s2 * 2) % this.q);
                        s2 = (short) (s2 + 1);
                    }
                }
            }
            return sArr;
        }
    }

    public FrodoMatrixGenerator(int i, int i2) {
        this.n = i;
        this.q = i2;
    }

    abstract short[] genMatrix(byte[] bArr);
}
