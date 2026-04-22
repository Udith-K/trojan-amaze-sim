package io.ktor.utils.io.core;

import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.charsets.EncodingKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.EncodeResult;
import io.ktor.utils.io.core.internal.UTF8Kt;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: Strings.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StringsKt {
    public static /* synthetic */ byte[] readBytes$default(ByteReadPacket byteReadPacket, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            long remaining = byteReadPacket.getRemaining();
            if (remaining > 2147483647L) {
                throw new IllegalArgumentException("Unable to convert to a ByteArray: packet is too big");
            }
            i = (int) remaining;
        }
        return readBytes(byteReadPacket, i);
    }

    public static final byte[] readBytes(ByteReadPacket byteReadPacket, int i) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        if (i != 0) {
            byte[] bArr = new byte[i];
            InputArraysKt.readFully(byteReadPacket, bArr, 0, i);
            return bArr;
        }
        return UnsafeKt.EmptyByteArray;
    }

    public static /* synthetic */ String readText$default(Input input, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readText(input, charset, i);
    }

    public static final String readText(Input input, Charset charset, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        CharsetDecoder charsetDecoderNewDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(charsetDecoderNewDecoder, "charset.newDecoder()");
        return EncodingKt.decode(charsetDecoderNewDecoder, input, i);
    }

    public static /* synthetic */ String readTextExactBytes$default(Input input, int i, Charset charset, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        return readTextExactBytes(input, i, charset);
    }

    public static final String readTextExactBytes(Input input, int i, Charset charset) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        CharsetDecoder charsetDecoderNewDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(charsetDecoderNewDecoder, "charset.newDecoder()");
        return CharsetJVMKt.decodeExactBytes(charsetDecoderNewDecoder, input, i);
    }

    public static /* synthetic */ void writeText$default(Output output, CharSequence charSequence, int i, int i2, Charset charset, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        if ((i3 & 8) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(output, charSequence, i, i2, charset);
    }

    public static final void writeText(Output output, CharSequence text, int i, int i2, Charset charset) {
        Intrinsics.checkNotNullParameter(output, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (charset == Charsets.UTF_8) {
            writeTextUtf8(output, text, i, i2);
            return;
        }
        CharsetEncoder charsetEncoderNewEncoder = charset.newEncoder();
        Intrinsics.checkNotNullExpressionValue(charsetEncoderNewEncoder, "charset.newEncoder()");
        EncodingKt.encodeToImpl(charsetEncoderNewEncoder, output, text, i, i2);
    }

    public static final Void prematureEndOfStream(int i) throws EOFException {
        throw new EOFException("Premature end of stream: expected " + i + " bytes");
    }

    private static final void writeTextUtf8(Output output, CharSequence charSequence, int i, int i2) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        while (true) {
            try {
                int iM2631encodeUTF8lBXzO7A = UTF8Kt.m2631encodeUTF8lBXzO7A(chunkBufferPrepareWriteHead.m2621getMemorySK3TCg8(), charSequence, i, i2, chunkBufferPrepareWriteHead.getWritePosition(), chunkBufferPrepareWriteHead.getLimit());
                int iM2625component1Mh2AYeg = EncodeResult.m2625component1Mh2AYeg(iM2631encodeUTF8lBXzO7A) & 65535;
                i += iM2625component1Mh2AYeg;
                chunkBufferPrepareWriteHead.commitWritten(EncodeResult.m2626component2Mh2AYeg(iM2631encodeUTF8lBXzO7A) & 65535);
                int i3 = (iM2625component1Mh2AYeg != 0 || i >= i2) ? i < i2 ? 1 : 0 : 8;
                if (i3 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i3, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }
}
