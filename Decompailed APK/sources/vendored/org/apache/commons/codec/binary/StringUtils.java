package vendored.org.apache.commons.codec.binary;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes2.dex */
public abstract class StringUtils {
    private static byte[] getBytes(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return str.getBytes(charset);
    }

    public static byte[] getBytesUtf8(String str) {
        return getBytes(str, StandardCharsets.UTF_8);
    }
}
