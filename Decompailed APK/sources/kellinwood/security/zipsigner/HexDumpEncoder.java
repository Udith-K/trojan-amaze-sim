package kellinwood.security.zipsigner;

import ch.qos.logback.core.CoreConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.util.encoders.HexEncoder;

/* JADX INFO: loaded from: classes.dex */
public class HexDumpEncoder {
    static HexEncoder encoder = new HexEncoder();

    public static String encode(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            encoder.encode(bArr, 0, bArr.length, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < byteArray.length) {
                int i2 = i + 32;
                int iMin = Math.min(i2, byteArray.length);
                StringBuilder sb2 = new StringBuilder();
                StringBuilder sb3 = new StringBuilder();
                sb2.append(String.format("%08x: ", Integer.valueOf(i / 2)));
                while (i < iMin) {
                    sb2.append(Character.valueOf((char) byteArray[i]));
                    sb2.append(Character.valueOf((char) byteArray[i + 1]));
                    int i3 = i + 2;
                    if (i3 % 4 == 0) {
                        sb2.append(' ');
                    }
                    byte b = bArr[i / 2];
                    if (b >= 32 && b < 127) {
                        sb3.append(Character.valueOf((char) b));
                    } else {
                        sb3.append(CoreConstants.DOT);
                    }
                    i = i3;
                }
                sb.append(sb2.toString());
                for (int length = sb2.length(); length < 50; length++) {
                    sb.append(' ');
                }
                sb.append("  ");
                sb.append((CharSequence) sb3);
                sb.append("\n");
                i = i2;
            }
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
