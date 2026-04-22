package org.bouncycastle.cert.crmf;

/* JADX INFO: loaded from: classes2.dex */
public interface EncryptedValuePadder {
    byte[] getPaddedData(byte[] bArr);

    byte[] getUnpaddedData(byte[] bArr);
}
