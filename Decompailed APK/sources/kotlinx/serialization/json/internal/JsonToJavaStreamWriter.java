package kotlinx.serialization.json.internal;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: JvmJsonStreams.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JsonToJavaStreamWriter implements JsonWriter {
    private final byte[] buffer;
    private char[] charArray;
    private int indexInBuffer;
    private final OutputStream stream;

    public JsonToJavaStreamWriter(OutputStream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        this.stream = stream;
        this.buffer = ByteArrayPool.INSTANCE.take();
        this.charArray = CharArrayPool.INSTANCE.take();
    }

    @Override // kotlinx.serialization.json.internal.JsonWriter
    public void writeLong(long j) throws IOException {
        write(String.valueOf(j));
    }

    @Override // kotlinx.serialization.json.internal.JsonWriter
    public void writeChar(char c) throws IOException {
        writeUtf8CodePoint(c);
    }

    @Override // kotlinx.serialization.json.internal.JsonWriter
    public void write(String text) throws IOException {
        Intrinsics.checkNotNullParameter(text, "text");
        int length = text.length();
        ensureTotalCapacity(0, length);
        text.getChars(0, length, this.charArray, 0);
        writeUtf8(this.charArray, length);
    }

    @Override // kotlinx.serialization.json.internal.JsonWriter
    public void writeQuoted(String text) throws IOException {
        Intrinsics.checkNotNullParameter(text, "text");
        ensureTotalCapacity(0, text.length() + 2);
        char[] cArr = this.charArray;
        cArr[0] = CoreConstants.DOUBLE_QUOTE_CHAR;
        int length = text.length();
        text.getChars(0, length, cArr, 1);
        int i = length + 1;
        for (int i2 = 1; i2 < i; i2++) {
            char c = cArr[i2];
            if (c < StringOpsKt.getESCAPE_MARKERS().length && StringOpsKt.getESCAPE_MARKERS()[c] != 0) {
                appendStringSlowPath(i2, text);
                return;
            }
        }
        cArr[i] = CoreConstants.DOUBLE_QUOTE_CHAR;
        writeUtf8(cArr, length + 2);
        flush();
    }

    private final void appendStringSlowPath(int i, String str) throws IOException {
        byte b;
        int length = str.length();
        for (int i2 = i - 1; i2 < length; i2++) {
            int iEnsureTotalCapacity = ensureTotalCapacity(i, 2);
            char cCharAt = str.charAt(i2);
            if (cCharAt >= StringOpsKt.getESCAPE_MARKERS().length || (b = StringOpsKt.getESCAPE_MARKERS()[cCharAt]) == 0) {
                int i3 = iEnsureTotalCapacity + 1;
                this.charArray[iEnsureTotalCapacity] = cCharAt;
                i = i3;
            } else {
                if (b == 1) {
                    String str2 = StringOpsKt.getESCAPE_STRINGS()[cCharAt];
                    Intrinsics.checkNotNull(str2);
                    int iEnsureTotalCapacity2 = ensureTotalCapacity(iEnsureTotalCapacity, str2.length());
                    str2.getChars(0, str2.length(), this.charArray, iEnsureTotalCapacity2);
                    i = iEnsureTotalCapacity2 + str2.length();
                } else {
                    char[] cArr = this.charArray;
                    cArr[iEnsureTotalCapacity] = CoreConstants.ESCAPE_CHAR;
                    cArr[iEnsureTotalCapacity + 1] = (char) b;
                    i = iEnsureTotalCapacity + 2;
                }
            }
        }
        ensureTotalCapacity(i, 1);
        char[] cArr2 = this.charArray;
        cArr2[i] = CoreConstants.DOUBLE_QUOTE_CHAR;
        writeUtf8(cArr2, i + 1);
        flush();
    }

    private final int ensureTotalCapacity(int i, int i2) {
        int i3 = i2 + i;
        char[] cArr = this.charArray;
        if (cArr.length <= i3) {
            char[] cArrCopyOf = Arrays.copyOf(cArr, RangesKt.coerceAtLeast(i3, i * 2));
            Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(this, newSize)");
            this.charArray = cArrCopyOf;
        }
        return i;
    }

    public void release() throws IOException {
        flush();
        CharArrayPool.INSTANCE.release(this.charArray);
        ByteArrayPool.INSTANCE.release(this.buffer);
    }

    private final void flush() throws IOException {
        this.stream.write(this.buffer, 0, this.indexInBuffer);
        this.indexInBuffer = 0;
    }

    private final void writeUtf8CodePoint(int i) throws IOException {
        if (i < 128) {
            if (this.buffer.length - this.indexInBuffer < 1) {
                flush();
            }
            byte[] bArr = this.buffer;
            int i2 = this.indexInBuffer;
            this.indexInBuffer = i2 + 1;
            bArr[i2] = (byte) i;
            return;
        }
        if (i < 2048) {
            if (this.buffer.length - this.indexInBuffer < 2) {
                flush();
            }
            byte[] bArr2 = this.buffer;
            int i3 = this.indexInBuffer;
            int i4 = i3 + 1;
            this.indexInBuffer = i4;
            bArr2[i3] = (byte) ((i >> 6) | 192);
            this.indexInBuffer = i3 + 2;
            bArr2[i4] = (byte) ((i & 63) | 128);
            return;
        }
        if (55296 <= i && i < 57344) {
            if (this.buffer.length - this.indexInBuffer < 1) {
                flush();
            }
            byte[] bArr3 = this.buffer;
            int i5 = this.indexInBuffer;
            this.indexInBuffer = i5 + 1;
            bArr3[i5] = (byte) 63;
            return;
        }
        if (i < 65536) {
            if (this.buffer.length - this.indexInBuffer < 3) {
                flush();
            }
            int i6 = (i >> 12) | BERTags.FLAGS;
            byte[] bArr4 = this.buffer;
            int i7 = this.indexInBuffer;
            int i8 = i7 + 1;
            this.indexInBuffer = i8;
            bArr4[i7] = (byte) i6;
            int i9 = i7 + 2;
            this.indexInBuffer = i9;
            bArr4[i8] = (byte) (((i >> 6) & 63) | 128);
            this.indexInBuffer = i7 + 3;
            bArr4[i9] = (byte) ((i & 63) | 128);
            return;
        }
        if (i > 1114111) {
            throw new JsonEncodingException("Unexpected code point: " + i);
        }
        if (this.buffer.length - this.indexInBuffer < 4) {
            flush();
        }
        byte[] bArr5 = this.buffer;
        int i10 = this.indexInBuffer;
        int i11 = i10 + 1;
        this.indexInBuffer = i11;
        bArr5[i10] = (byte) ((i >> 18) | 240);
        int i12 = i10 + 2;
        this.indexInBuffer = i12;
        bArr5[i11] = (byte) (((i >> 12) & 63) | 128);
        int i13 = i10 + 3;
        this.indexInBuffer = i13;
        bArr5[i12] = (byte) (((i >> 6) & 63) | 128);
        this.indexInBuffer = i10 + 4;
        bArr5[i13] = (byte) ((i & 63) | 128);
    }

    private final void writeUtf8(char[] cArr, int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("count < 0");
        }
        if (i > cArr.length) {
            throw new IllegalArgumentException(("count > string.length: " + i + " > " + cArr.length).toString());
        }
        int i2 = 0;
        while (i2 < i) {
            char c = cArr[i2];
            if (c < 128) {
                if (this.buffer.length - this.indexInBuffer < 1) {
                    flush();
                }
                byte[] bArr = this.buffer;
                int i3 = this.indexInBuffer;
                int i4 = i3 + 1;
                this.indexInBuffer = i4;
                bArr[i3] = (byte) c;
                i2++;
                int iMin = Math.min(i, (bArr.length - i4) + i2);
                while (i2 < iMin) {
                    char c2 = cArr[i2];
                    if (c2 < 128) {
                        byte[] bArr2 = this.buffer;
                        int i5 = this.indexInBuffer;
                        this.indexInBuffer = i5 + 1;
                        bArr2[i5] = (byte) c2;
                        i2++;
                    }
                }
            } else {
                if (c < 2048) {
                    if (this.buffer.length - this.indexInBuffer < 2) {
                        flush();
                    }
                    byte[] bArr3 = this.buffer;
                    int i6 = this.indexInBuffer;
                    int i7 = i6 + 1;
                    this.indexInBuffer = i7;
                    bArr3[i6] = (byte) ((c >> 6) | 192);
                    this.indexInBuffer = i6 + 2;
                    bArr3[i7] = (byte) ((c & '?') | 128);
                } else if (c < 55296 || c > 57343) {
                    if (this.buffer.length - this.indexInBuffer < 3) {
                        flush();
                    }
                    int i8 = (c >> '\f') | BERTags.FLAGS;
                    byte[] bArr4 = this.buffer;
                    int i9 = this.indexInBuffer;
                    int i10 = i9 + 1;
                    this.indexInBuffer = i10;
                    bArr4[i9] = (byte) i8;
                    int i11 = i9 + 2;
                    this.indexInBuffer = i11;
                    bArr4[i10] = (byte) (((c >> 6) & 63) | 128);
                    this.indexInBuffer = i9 + 3;
                    bArr4[i11] = (byte) ((c & '?') | 128);
                } else {
                    int i12 = i2 + 1;
                    char c3 = i12 < i ? cArr[i12] : (char) 0;
                    if (c > 56319 || 56320 > c3 || c3 >= 57344) {
                        if (this.buffer.length - this.indexInBuffer < 1) {
                            flush();
                        }
                        byte[] bArr5 = this.buffer;
                        int i13 = this.indexInBuffer;
                        this.indexInBuffer = i13 + 1;
                        bArr5[i13] = (byte) 63;
                        i2 = i12;
                    } else {
                        int i14 = (((c & 1023) << 10) | (c3 & 1023)) + PKIFailureInfo.notAuthorized;
                        if (this.buffer.length - this.indexInBuffer < 4) {
                            flush();
                        }
                        byte[] bArr6 = this.buffer;
                        int i15 = this.indexInBuffer;
                        int i16 = i15 + 1;
                        this.indexInBuffer = i16;
                        bArr6[i15] = (byte) ((i14 >> 18) | 240);
                        int i17 = i15 + 2;
                        this.indexInBuffer = i17;
                        bArr6[i16] = (byte) (((i14 >> 12) & 63) | 128);
                        int i18 = i15 + 3;
                        this.indexInBuffer = i18;
                        bArr6[i17] = (byte) (((i14 >> 6) & 63) | 128);
                        this.indexInBuffer = i15 + 4;
                        bArr6[i18] = (byte) ((i14 & 63) | 128);
                        i2 += 2;
                    }
                }
                i2++;
            }
        }
    }
}
