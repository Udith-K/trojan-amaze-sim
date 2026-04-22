package io.ktor.utils.io.core.internal;

import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.bouncycastle.asn1.BERTags;

/* JADX INFO: compiled from: UTF8.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class UTF8Kt {
    public static final int codePoint(char c, char c2) {
        return ((c - 55232) << 10) | (c2 - 56320);
    }

    public static final int highSurrogate(int i) {
        return (i >>> 10) + 55232;
    }

    public static final boolean isBmpCodePoint(int i) {
        return (i >>> 16) == 0;
    }

    public static final boolean isValidCodePoint(int i) {
        return i <= 1114111;
    }

    public static final int lowSurrogate(int i) {
        return (i & 1023) + 56320;
    }

    /* JADX INFO: renamed from: encodeUTF8-lBXzO7A, reason: not valid java name */
    public static final int m2631encodeUTF8lBXzO7A(ByteBuffer encodeUTF8, CharSequence text, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(encodeUTF8, "$this$encodeUTF8");
        Intrinsics.checkNotNullParameter(text, "text");
        int iMin = Math.min(i2, i + 65535);
        int iCoerceAtMost = RangesKt.coerceAtMost(i4, 65535);
        int i5 = i;
        int i6 = i3;
        while (i6 < iCoerceAtMost && i5 < iMin) {
            int i7 = i5 + 1;
            char cCharAt = text.charAt(i5);
            int i8 = cCharAt & 65535;
            if ((cCharAt & 65408) == 0) {
                encodeUTF8.put(i6, (byte) i8);
                i5 = i7;
                i6++;
            } else {
                return m2632encodeUTF8Stage1Vm9B2pQ(encodeUTF8, text, i5, iMin, i, i6, iCoerceAtMost, i3);
            }
        }
        return EncodeResult.m2628constructorimpl(UShort.m2716constructorimpl((short) (i5 - i)), UShort.m2716constructorimpl((short) (i6 - i3)));
    }

    /* JADX INFO: renamed from: encodeUTF8Stage1-Vm9B2pQ, reason: not valid java name */
    private static final int m2632encodeUTF8Stage1Vm9B2pQ(ByteBuffer byteBuffer, CharSequence charSequence, int i, int i2, int i3, int i4, int i5, int i6) {
        int iCodePoint;
        int i7;
        int i8 = i5 - 3;
        int i9 = i;
        int i10 = i4;
        while (i8 - i10 > 0 && i9 < i2) {
            int i11 = i9 + 1;
            char cCharAt = charSequence.charAt(i9);
            if (!Character.isHighSurrogate(cCharAt)) {
                i9 = i11;
                iCodePoint = cCharAt;
            } else if (i11 == i2 || !Character.isLowSurrogate(charSequence.charAt(i11))) {
                i9 = i11;
                iCodePoint = 63;
            } else {
                i9 += 2;
                iCodePoint = codePoint(cCharAt, charSequence.charAt(i11));
            }
            if (iCodePoint >= 0 && iCodePoint < 128) {
                byteBuffer.put(i10, (byte) iCodePoint);
                i7 = 1;
            } else if (128 <= iCodePoint && iCodePoint < 2048) {
                byteBuffer.put(i10, (byte) (((iCodePoint >> 6) & 31) | 192));
                byteBuffer.put(i10 + 1, (byte) (128 | (iCodePoint & 63)));
                i7 = 2;
            } else if (2048 <= iCodePoint && iCodePoint < 65536) {
                byteBuffer.put(i10, (byte) (((iCodePoint >> 12) & 15) | BERTags.FLAGS));
                byteBuffer.put(i10 + 1, (byte) ((63 & (iCodePoint >> 6)) | 128));
                byteBuffer.put(i10 + 2, (byte) (128 | (iCodePoint & 63)));
                i7 = 3;
            } else {
                if (65536 > iCodePoint || iCodePoint >= 1114112) {
                    malformedCodePoint(iCodePoint);
                    throw new KotlinNothingValueException();
                }
                byteBuffer.put(i10, (byte) (((iCodePoint >> 18) & 7) | 240));
                byteBuffer.put(i10 + 1, (byte) (((iCodePoint >> 12) & 63) | 128));
                byteBuffer.put(i10 + 2, (byte) ((63 & (iCodePoint >> 6)) | 128));
                byteBuffer.put(i10 + 3, (byte) (128 | (iCodePoint & 63)));
                i7 = 4;
            }
            i10 += i7;
        }
        if (i10 == i8) {
            return m2633encodeUTF8Stage2Vm9B2pQ(byteBuffer, charSequence, i9, i2, i3, i10, i5, i6);
        }
        return EncodeResult.m2628constructorimpl(UShort.m2716constructorimpl((short) (i9 - i3)), UShort.m2716constructorimpl((short) (i10 - i6)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e2, code lost:
    
        malformedCodePoint(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ea, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00fd, code lost:
    
        return io.ktor.utils.io.core.internal.EncodeResult.m2628constructorimpl(kotlin.UShort.m2716constructorimpl((short) (r3 - r20)), kotlin.UShort.m2716constructorimpl((short) (r4 - r23)));
     */
    /* JADX INFO: renamed from: encodeUTF8Stage2-Vm9B2pQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final int m2633encodeUTF8Stage2Vm9B2pQ(java.nio.ByteBuffer r16, java.lang.CharSequence r17, int r18, int r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.internal.UTF8Kt.m2633encodeUTF8Stage2Vm9B2pQ(java.nio.ByteBuffer, java.lang.CharSequence, int, int, int, int, int, int):int");
    }

    public static final Void malformedByteCount(int i) throws MalformedUTF8InputException {
        throw new MalformedUTF8InputException("Expected " + i + " more character bytes");
    }

    public static final Void malformedCodePoint(int i) {
        throw new IllegalArgumentException("Malformed code-point " + i + " found");
    }
}
