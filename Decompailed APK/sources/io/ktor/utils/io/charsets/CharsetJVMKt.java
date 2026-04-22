package io.ktor.utils.io.charsets;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CharsetJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CharsetJVMKt {
    private static final ByteBuffer EmptyByteBuffer;
    private static final CharBuffer EmptyCharBuffer = CharBuffer.allocate(0);

    public static final String getName(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "<this>");
        String strName = charset.name();
        Intrinsics.checkNotNullExpressionValue(strName, "name()");
        return strName;
    }

    public static final byte[] encodeToByteArray(CharsetEncoder charsetEncoder, CharSequence input, int i, int i2) {
        Intrinsics.checkNotNullParameter(charsetEncoder, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        if (input instanceof String) {
            if (i == 0 && i2 == input.length()) {
                byte[] bytes = ((String) input).getBytes(charsetEncoder.charset());
                Intrinsics.checkNotNullExpressionValue(bytes, "input as java.lang.String).getBytes(charset())");
                return bytes;
            }
            String strSubstring = ((String) input).substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            Intrinsics.checkNotNull(strSubstring, "null cannot be cast to non-null type java.lang.String");
            byte[] bytes2 = strSubstring.getBytes(charsetEncoder.charset());
            Intrinsics.checkNotNullExpressionValue(bytes2, "input.substring(fromInde…ring).getBytes(charset())");
            return bytes2;
        }
        return encodeToByteArraySlow(charsetEncoder, input, i, i2);
    }

    private static final byte[] encodeToByteArraySlow(CharsetEncoder charsetEncoder, CharSequence charSequence, int i, int i2) throws CharacterCodingException {
        ByteBuffer byteBufferEncode = charsetEncoder.encode(CharBuffer.wrap(charSequence, i, i2));
        byte[] bArr = null;
        if (byteBufferEncode.hasArray() && byteBufferEncode.arrayOffset() == 0) {
            byte[] bArrArray = byteBufferEncode.array();
            if (bArrArray.length == byteBufferEncode.remaining()) {
                bArr = bArrArray;
            }
        }
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[byteBufferEncode.remaining()];
        byteBufferEncode.get(bArr2);
        return bArr2;
    }

    public static final int encodeImpl(CharsetEncoder charsetEncoder, CharSequence input, int i, int i2, Buffer dst) throws CharacterCodingException {
        Intrinsics.checkNotNullParameter(charsetEncoder, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(dst, "dst");
        CharBuffer charBufferWrap = CharBuffer.wrap(input, i, i2);
        int iRemaining = charBufferWrap.remaining();
        ByteBuffer byteBufferM2621getMemorySK3TCg8 = dst.m2621getMemorySK3TCg8();
        int writePosition = dst.getWritePosition();
        int limit = dst.getLimit() - writePosition;
        ByteBuffer byteBufferM2616slice87lwejk = Memory.m2616slice87lwejk(byteBufferM2621getMemorySK3TCg8, writePosition, limit);
        CoderResult result = charsetEncoder.encode(charBufferWrap, byteBufferM2616slice87lwejk, false);
        if (result.isMalformed() || result.isUnmappable()) {
            Intrinsics.checkNotNullExpressionValue(result, "result");
            throwExceptionWrapped(result);
        }
        if (byteBufferM2616slice87lwejk.limit() != limit) {
            throw new IllegalStateException("Buffer's limit change is not allowed");
        }
        dst.commitWritten(byteBufferM2616slice87lwejk.position());
        return iRemaining - charBufferWrap.remaining();
    }

    public static final int decode(CharsetDecoder charsetDecoder, Input input, Appendable dst, int i) {
        CoderResult cr;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        Intrinsics.checkNotNullParameter(charsetDecoder, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(dst, "dst");
        CharBuffer charBufferAllocate = CharBuffer.allocate(8192);
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        int iRemaining = 0;
        if (chunkBufferPrepareReadFirstHead != null) {
            int i2 = 1;
            int i3 = 1;
            int iRemaining2 = 0;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i2) {
                        int i4 = i - iRemaining2;
                        if (i4 == 0) {
                            i2 = 0;
                        } else {
                            try {
                                ByteBuffer byteBufferM2621getMemorySK3TCg8 = chunkBufferPrepareReadFirstHead.m2621getMemorySK3TCg8();
                                int readPosition = chunkBufferPrepareReadFirstHead.getReadPosition();
                                int writePosition2 = chunkBufferPrepareReadFirstHead.getWritePosition() - readPosition;
                                ByteBuffer byteBufferM2616slice87lwejk = Memory.m2616slice87lwejk(byteBufferM2621getMemorySK3TCg8, readPosition, writePosition2);
                                charBufferAllocate.clear();
                                if (i4 < 8192) {
                                    charBufferAllocate.limit(i4);
                                }
                                CoderResult rc = charsetDecoder.decode(byteBufferM2616slice87lwejk, charBufferAllocate, false);
                                charBufferAllocate.flip();
                                iRemaining2 += charBufferAllocate.remaining();
                                dst.append(charBufferAllocate);
                                if (rc.isMalformed() || rc.isUnmappable()) {
                                    Intrinsics.checkNotNullExpressionValue(rc, "rc");
                                    throwExceptionWrapped(rc);
                                }
                                i3 = (rc.isUnderflow() && byteBufferM2616slice87lwejk.hasRemaining()) ? i3 + 1 : 1;
                                if (byteBufferM2616slice87lwejk.limit() != writePosition2) {
                                    throw new IllegalStateException("Buffer's limit change is not allowed");
                                }
                                chunkBufferPrepareReadFirstHead.discardExact(byteBufferM2616slice87lwejk.position());
                                i2 = i3;
                            } finally {
                                chunkBufferPrepareReadFirstHead.getWritePosition();
                                chunkBufferPrepareReadFirstHead.getReadPosition();
                            }
                        }
                        writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    }
                    if (writePosition != 0) {
                        if (writePosition < i2 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i2);
                        } else {
                            chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                        }
                    } else {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        break;
                    }
                    if (i2 <= 0) {
                        iRemaining = 1;
                        chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (iRemaining != 0) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
            iRemaining = iRemaining2;
        }
        do {
            charBufferAllocate.clear();
            int i5 = i - iRemaining;
            if (i5 == 0) {
                break;
            }
            if (i5 < 8192) {
                charBufferAllocate.limit(i5);
            }
            cr = charsetDecoder.decode(EmptyByteBuffer, charBufferAllocate, true);
            charBufferAllocate.flip();
            iRemaining += charBufferAllocate.remaining();
            dst.append(charBufferAllocate);
            if (cr.isUnmappable() || cr.isMalformed()) {
                Intrinsics.checkNotNullExpressionValue(cr, "cr");
                throwExceptionWrapped(cr);
            }
        } while (cr.isOverflow());
        return iRemaining;
    }

    public static final String decodeExactBytes(CharsetDecoder charsetDecoder, Input input, int i) throws EOFException {
        Intrinsics.checkNotNullParameter(charsetDecoder, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        if (i != 0) {
            if (input.getHeadEndExclusive() - input.getHeadPosition() >= i) {
                if (input.m2623getHeadMemorySK3TCg8().hasArray()) {
                    ByteBuffer byteBufferM2623getHeadMemorySK3TCg8 = input.m2623getHeadMemorySK3TCg8();
                    byte[] bArrArray = byteBufferM2623getHeadMemorySK3TCg8.array();
                    Intrinsics.checkNotNullExpressionValue(bArrArray, "bb.array()");
                    int iArrayOffset = byteBufferM2623getHeadMemorySK3TCg8.arrayOffset() + byteBufferM2623getHeadMemorySK3TCg8.position() + input.getHead().getReadPosition();
                    Charset charset = charsetDecoder.charset();
                    Intrinsics.checkNotNullExpressionValue(charset, "charset()");
                    String str = new String(bArrArray, iArrayOffset, i, charset);
                    input.discardExact(i);
                    return str;
                }
                return decodeImplByteBuffer(charsetDecoder, input, i);
            }
            return decodeImplSlow(charsetDecoder, input, i);
        }
        return "";
    }

    private static final String decodeImplByteBuffer(CharsetDecoder charsetDecoder, Input input, int i) throws CharacterCodingException, EOFException {
        CharBuffer charBufferAllocate = CharBuffer.allocate(i);
        ByteBuffer byteBufferM2616slice87lwejk = Memory.m2616slice87lwejk(input.m2623getHeadMemorySK3TCg8(), input.getHead().getReadPosition(), i);
        CoderResult rc = charsetDecoder.decode(byteBufferM2616slice87lwejk, charBufferAllocate, true);
        if (rc.isMalformed() || rc.isUnmappable()) {
            Intrinsics.checkNotNullExpressionValue(rc, "rc");
            throwExceptionWrapped(rc);
        }
        charBufferAllocate.flip();
        input.discardExact(byteBufferM2616slice87lwejk.position());
        String string = charBufferAllocate.toString();
        Intrinsics.checkNotNullExpressionValue(string, "cb.toString()");
        return string;
    }

    /* JADX WARN: Finally extract failed */
    private static final String decodeImplSlow(CharsetDecoder charsetDecoder, Input input, int i) throws Throwable {
        int iPosition;
        int writePosition;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        CharBuffer charBufferAllocate = CharBuffer.allocate(i);
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        boolean z2 = false;
        if (chunkBufferPrepareReadFirstHead == null) {
            iPosition = i;
        } else {
            iPosition = i;
            int i2 = 1;
            int i3 = 1;
            boolean z3 = false;
            while (true) {
                try {
                    writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                } catch (Throwable th) {
                    th = th;
                }
                if (writePosition >= i2) {
                    try {
                        if (charBufferAllocate.hasRemaining() && iPosition != 0) {
                            ByteBuffer byteBufferM2621getMemorySK3TCg8 = chunkBufferPrepareReadFirstHead.m2621getMemorySK3TCg8();
                            int readPosition = chunkBufferPrepareReadFirstHead.getReadPosition();
                            int writePosition2 = chunkBufferPrepareReadFirstHead.getWritePosition() - readPosition;
                            ByteBuffer byteBufferM2616slice87lwejk = Memory.m2616slice87lwejk(byteBufferM2621getMemorySK3TCg8, readPosition, writePosition2);
                            int iLimit = byteBufferM2616slice87lwejk.limit();
                            int iPosition2 = byteBufferM2616slice87lwejk.position();
                            boolean z4 = iLimit - iPosition2 >= iPosition;
                            if (z4) {
                                byteBufferM2616slice87lwejk.limit(iPosition2 + iPosition);
                            }
                            CoderResult rc = charsetDecoder.decode(byteBufferM2616slice87lwejk, charBufferAllocate, z4);
                            if (rc.isMalformed() || rc.isUnmappable()) {
                                Intrinsics.checkNotNullExpressionValue(rc, "rc");
                                throwExceptionWrapped(rc);
                            }
                            i3 = (rc.isUnderflow() && byteBufferM2616slice87lwejk.hasRemaining()) ? i3 + 1 : 1;
                            byteBufferM2616slice87lwejk.limit(iLimit);
                            iPosition -= byteBufferM2616slice87lwejk.position() - iPosition2;
                            if (byteBufferM2616slice87lwejk.limit() != writePosition2) {
                                throw new IllegalStateException("Buffer's limit change is not allowed");
                            }
                            chunkBufferPrepareReadFirstHead.discardExact(byteBufferM2616slice87lwejk.position());
                            i2 = i3;
                            z3 = z4;
                            th = th;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                        i2 = 0;
                        writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    } catch (Throwable th2) {
                        chunkBufferPrepareReadFirstHead.getWritePosition();
                        chunkBufferPrepareReadFirstHead.getReadPosition();
                        throw th2;
                    }
                }
                if (writePosition != 0) {
                    if (writePosition < i2 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i2);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                } else {
                    try {
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                    } catch (Throwable th3) {
                        th = th3;
                        z = false;
                    }
                }
                if (chunkBufferPrepareReadNextHead == null) {
                    break;
                }
                if (i2 <= 0) {
                    z2 = true;
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    break;
                }
                chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
            }
            if (z2) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
            z2 = z3;
        }
        if (charBufferAllocate.hasRemaining() && !z2) {
            CoderResult rc2 = charsetDecoder.decode(EmptyByteBuffer, charBufferAllocate, true);
            if (rc2.isMalformed() || rc2.isUnmappable()) {
                Intrinsics.checkNotNullExpressionValue(rc2, "rc");
                throwExceptionWrapped(rc2);
            }
        }
        if (iPosition <= 0) {
            if (iPosition < 0) {
                throw new AssertionError("remainingInputBytes < 0");
            }
            charBufferAllocate.flip();
            String string = charBufferAllocate.toString();
            Intrinsics.checkNotNullExpressionValue(string, "cb.toString()");
            return string;
        }
        throw new EOFException("Not enough bytes available: had only " + (i - iPosition) + " instead of " + i);
    }

    private static final void throwExceptionWrapped(CoderResult coderResult) throws CharacterCodingException {
        try {
            coderResult.throwException();
        } catch (java.nio.charset.MalformedInputException e) {
            String message = e.getMessage();
            if (message == null) {
                message = "Failed to decode bytes";
            }
            throw new MalformedInputException(message);
        }
    }

    static {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(0);
        Intrinsics.checkNotNull(byteBufferAllocate);
        EmptyByteBuffer = byteBufferAllocate;
    }

    public static final boolean encodeComplete(CharsetEncoder charsetEncoder, Buffer dst) throws CharacterCodingException {
        Intrinsics.checkNotNullParameter(charsetEncoder, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        ByteBuffer byteBufferM2621getMemorySK3TCg8 = dst.m2621getMemorySK3TCg8();
        int writePosition = dst.getWritePosition();
        int limit = dst.getLimit() - writePosition;
        ByteBuffer byteBufferM2616slice87lwejk = Memory.m2616slice87lwejk(byteBufferM2621getMemorySK3TCg8, writePosition, limit);
        CoderResult result = charsetEncoder.encode(EmptyCharBuffer, byteBufferM2616slice87lwejk, true);
        if (result.isMalformed() || result.isUnmappable()) {
            Intrinsics.checkNotNullExpressionValue(result, "result");
            throwExceptionWrapped(result);
        }
        boolean zIsUnderflow = result.isUnderflow();
        if (byteBufferM2616slice87lwejk.limit() != limit) {
            throw new IllegalStateException("Buffer's limit change is not allowed");
        }
        dst.commitWritten(byteBufferM2616slice87lwejk.position());
        return zIsUnderflow;
    }
}
