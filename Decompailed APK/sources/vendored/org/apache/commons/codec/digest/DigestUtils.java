package vendored.org.apache.commons.codec.digest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import vendored.org.apache.commons.codec.binary.Hex;
import vendored.org.apache.commons.codec.binary.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DigestUtils {
    public static byte[] digest(MessageDigest messageDigest, File file) {
        return updateDigest(messageDigest, file).digest();
    }

    public static MessageDigest getDigest(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static MessageDigest getSha256Digest() {
        return getDigest("SHA-256");
    }

    public static byte[] sha256(byte[] bArr) {
        return getSha256Digest().digest(bArr);
    }

    public static byte[] sha256(String str) {
        return sha256(StringUtils.getBytesUtf8(str));
    }

    public static String sha256Hex(byte[] bArr) {
        return Hex.encodeHexString(sha256(bArr));
    }

    public static String sha256Hex(String str) {
        return Hex.encodeHexString(sha256(str));
    }

    public static MessageDigest updateDigest(MessageDigest messageDigest, File file) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            MessageDigest messageDigestUpdateDigest = updateDigest(messageDigest, bufferedInputStream);
            bufferedInputStream.close();
            return messageDigestUpdateDigest;
        } catch (Throwable th) {
            try {
                bufferedInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static MessageDigest updateDigest(MessageDigest messageDigest, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024];
        int i = inputStream.read(bArr, 0, 1024);
        while (i > -1) {
            messageDigest.update(bArr, 0, i);
            i = inputStream.read(bArr, 0, 1024);
        }
        return messageDigest;
    }
}
