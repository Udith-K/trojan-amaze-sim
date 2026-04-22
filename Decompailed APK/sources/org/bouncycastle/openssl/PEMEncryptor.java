package org.bouncycastle.openssl;

/* JADX INFO: loaded from: classes2.dex */
public interface PEMEncryptor {
    byte[] encrypt(byte[] bArr) throws PEMException;

    String getAlgorithm();

    byte[] getIV();
}
