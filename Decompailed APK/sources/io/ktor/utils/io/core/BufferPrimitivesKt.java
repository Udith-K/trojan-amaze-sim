package io.ktor.utils.io.core;

import ch.qos.logback.core.CoreConstants;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.bits.MemoryJvmKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BufferPrimitives.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BufferPrimitivesKt {
    public static final void readFully(Buffer buffer, byte[] destination, int i, int i2) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ByteBuffer byteBufferM2621getMemorySK3TCg8 = buffer.m2621getMemorySK3TCg8();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= i2) {
            MemoryJvmKt.m2619copyTo9zorpBc(byteBufferM2621getMemorySK3TCg8, destination, readPosition, i2, i);
            Unit unit = Unit.INSTANCE;
            buffer.discardExact(i2);
        } else {
            throw new EOFException("Not enough bytes to read a byte array of size " + i2 + CoreConstants.DOT);
        }
    }

    public static final short readShort(Buffer buffer) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        ByteBuffer byteBufferM2621getMemorySK3TCg8 = buffer.m2621getMemorySK3TCg8();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= 2) {
            Short shValueOf = Short.valueOf(byteBufferM2621getMemorySK3TCg8.getShort(readPosition));
            buffer.discardExact(2);
            return shValueOf.shortValue();
        }
        throw new EOFException("Not enough bytes to read a short integer of size 2" + CoreConstants.DOT);
    }

    public static final void writeFully(Buffer buffer, byte[] source, int i, int i2) throws InsufficientSpaceException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer byteBufferM2621getMemorySK3TCg8 = buffer.m2621getMemorySK3TCg8();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit >= i2) {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(source, i, i2).slice().order(ByteOrder.BIG_ENDIAN);
            Intrinsics.checkNotNullExpressionValue(byteBufferOrder, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
            Memory.m2615copyToJT6ljtQ(Memory.m2614constructorimpl(byteBufferOrder), byteBufferM2621getMemorySK3TCg8, 0, i2, writePosition);
            buffer.commitWritten(i2);
            return;
        }
        throw new InsufficientSpaceException("byte array", i2, limit);
    }

    public static final void writeShort(Buffer buffer, short s) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        ByteBuffer byteBufferM2621getMemorySK3TCg8 = buffer.m2621getMemorySK3TCg8();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit >= 2) {
            byteBufferM2621getMemorySK3TCg8.putShort(writePosition, s);
            buffer.commitWritten(2);
            return;
        }
        throw new InsufficientSpaceException("short integer", 2, limit);
    }
}
