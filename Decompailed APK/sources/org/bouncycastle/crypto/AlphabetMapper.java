package org.bouncycastle.crypto;

/* JADX INFO: loaded from: classes2.dex */
public interface AlphabetMapper {
    char[] convertToChars(byte[] bArr);

    byte[] convertToIndexes(char[] cArr);

    int getRadix();
}
