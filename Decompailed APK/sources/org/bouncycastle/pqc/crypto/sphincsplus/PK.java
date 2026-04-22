package org.bouncycastle.pqc.crypto.sphincsplus;

/* JADX INFO: loaded from: classes2.dex */
class PK {
    final byte[] root;
    final byte[] seed;

    PK(byte[] bArr, byte[] bArr2) {
        this.seed = bArr;
        this.root = bArr2;
    }
}
