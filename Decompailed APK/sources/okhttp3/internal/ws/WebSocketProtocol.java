package okhttp3.internal.ws;

import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;

/* JADX INFO: compiled from: WebSocketProtocol.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class WebSocketProtocol {
    public static final WebSocketProtocol INSTANCE = new WebSocketProtocol();

    private WebSocketProtocol() {
    }

    public final void toggleMask(Buffer.UnsafeCursor cursor, byte[] key) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Intrinsics.checkNotNullParameter(key, "key");
        int length = key.length;
        int i = 0;
        do {
            byte[] bArr = cursor.data;
            int i2 = cursor.start;
            int i3 = cursor.end;
            if (bArr != null) {
                while (i2 < i3) {
                    int i4 = i % length;
                    bArr[i2] = (byte) (bArr[i2] ^ key[i4]);
                    i2++;
                    i = i4 + 1;
                }
            }
        } while (cursor.next() != -1);
    }

    public final String closeCodeExceptionMessage(int i) {
        if (i < 1000 || i >= 5000) {
            return "Code must be in range [1000,5000): " + i;
        }
        if ((1004 > i || i >= 1007) && (1015 > i || i >= 3000)) {
            return null;
        }
        return "Code " + i + " is reserved and may not be used.";
    }

    public final void validateCloseCode(int i) {
        String strCloseCodeExceptionMessage = closeCodeExceptionMessage(i);
        if (strCloseCodeExceptionMessage == null) {
            return;
        }
        Intrinsics.checkNotNull(strCloseCodeExceptionMessage);
        throw new IllegalArgumentException(strCloseCodeExceptionMessage.toString());
    }
}
