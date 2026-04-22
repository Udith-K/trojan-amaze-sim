package org.bouncycastle.its.operator;

/* JADX INFO: loaded from: classes2.dex */
public interface ETSIDataEncryptor {
    byte[] encrypt(byte[] bArr);

    byte[] getKey();

    byte[] getNonce();
}
