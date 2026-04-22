package org.bouncycastle.crypto;

/* JADX INFO: loaded from: classes2.dex */
public interface CharToByteConverter {
    byte[] convert(char[] cArr);

    String getType();
}
