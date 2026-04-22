package org.fdroid.fdroid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;

/* JADX INFO: loaded from: classes2.dex */
public class Hasher {
    private final byte[] array;
    private MessageDigest digest;
    private String hashCache;

    public Hasher(String str, byte[] bArr) throws NoSuchAlgorithmException {
        init(str);
        this.array = bArr;
    }

    private void init(String str) throws NoSuchAlgorithmException {
        try {
            this.digest = MessageDigest.getInstance(str);
        } catch (Exception e) {
            throw new NoSuchAlgorithmException(e);
        }
    }

    public String getHash() {
        String str = this.hashCache;
        if (str != null) {
            return str;
        }
        this.digest.update(this.array);
        String strHex = hex(this.digest.digest());
        this.hashCache = strHex;
        return strHex;
    }

    public static String hex(Certificate certificate) {
        byte[] encoded;
        try {
            encoded = certificate.getEncoded();
        } catch (CertificateEncodingException unused) {
            encoded = new byte[0];
        }
        return hex(encoded);
    }

    private static String hex(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = (b >> 4) & 15;
            int i3 = i * 2;
            bArr2[i3] = (byte) (i2 >= 10 ? i2 + 87 : i2 + 48);
            int i4 = b & 15;
            bArr2[i3 + 1] = (byte) (i4 >= 10 ? i4 + 87 : i4 + 48);
        }
        return new String(bArr2);
    }

    static byte[] unhex(String str) {
        int i;
        byte[] bArr = new byte[str.length() / 2];
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if ('0' <= cCharAt && cCharAt <= '9') {
                i = cCharAt - '0';
            } else if ('a' <= cCharAt && cCharAt <= 'f') {
                i = cCharAt - 'W';
            } else {
                if ('A' > cCharAt || cCharAt > 'F') {
                    throw new IllegalArgumentException("Bad hex digit");
                }
                i = cCharAt - '7';
            }
            int i3 = i2 / 2;
            byte b = bArr[i3];
            if (i2 % 2 == 0) {
                i <<= 4;
            }
            bArr[i3] = (byte) (b + ((byte) i));
        }
        return bArr;
    }
}
