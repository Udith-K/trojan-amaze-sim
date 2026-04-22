package io.ktor.util;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Base64.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Base64Kt {
    private static final int[] BASE64_INVERSE_ALPHABET;

    static {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[i] = StringsKt.indexOf$default((CharSequence) "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", (char) i, 0, false, 6, (Object) null);
        }
        BASE64_INVERSE_ALPHABET = iArr;
    }

    public static final String encodeBase64(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        try {
            io.ktor.utils.io.core.StringsKt.writeText$default(bytePacketBuilder, str, 0, 0, null, 14, null);
            return encodeBase64(bytePacketBuilder.build());
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final String encodeBase64(byte[] bArr) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        int i3 = 3;
        char[] cArr = new char[((bArr.length * 8) / 6) + 3];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = i4 + 3;
            if (i6 > bArr.length) {
                break;
            }
            int i7 = (bArr[i4 + 2] & 255) | ((bArr[i4] & 255) << 16) | ((bArr[i4 + 1] & 255) << 8);
            int i8 = 3;
            while (-1 < i8) {
                cArr[i5] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((i7 >> (i8 * 6)) & 63);
                i8--;
                i5++;
            }
            i4 = i6;
        }
        int length = bArr.length - i4;
        if (length == 0) {
            return StringsKt.concatToString(cArr, 0, i5);
        }
        if (length == 1) {
            i = (bArr[i4] & 255) << 16;
        } else {
            i = ((bArr[i4 + 1] & 255) << 8) | ((bArr[i4] & 255) << 16);
        }
        int i9 = ((3 - length) * 8) / 6;
        if (i9 <= 3) {
            while (true) {
                i2 = i5 + 1;
                cArr[i5] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((i >> (i3 * 6)) & 63);
                if (i3 == i9) {
                    break;
                }
                i3--;
                i5 = i2;
            }
            i5 = i2;
        }
        int i10 = 0;
        while (i10 < i9) {
            cArr[i5] = '=';
            i10++;
            i5++;
        }
        return StringsKt.concatToString(cArr, 0, i5);
    }

    public static final String encodeBase64(ByteReadPacket byteReadPacket) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        return encodeBase64(io.ktor.utils.io.core.StringsKt.readBytes$default(byteReadPacket, 0, 1, null));
    }
}
