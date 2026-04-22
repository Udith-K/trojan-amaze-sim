package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.Memory;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BufferAppend.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BufferAppendKt {
    public static final int writeBufferAppend(Buffer buffer, Buffer other, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        int iMin = Math.min(other.getWritePosition() - other.getReadPosition(), i);
        if (buffer.getLimit() - buffer.getWritePosition() <= iMin) {
            writeBufferAppendUnreserve(buffer, iMin);
        }
        ByteBuffer byteBufferM2621getMemorySK3TCg8 = buffer.m2621getMemorySK3TCg8();
        int writePosition = buffer.getWritePosition();
        buffer.getLimit();
        ByteBuffer byteBufferM2621getMemorySK3TCg82 = other.m2621getMemorySK3TCg8();
        int readPosition = other.getReadPosition();
        other.getWritePosition();
        Memory.m2615copyToJT6ljtQ(byteBufferM2621getMemorySK3TCg82, byteBufferM2621getMemorySK3TCg8, readPosition, iMin, writePosition);
        other.discardExact(iMin);
        buffer.commitWritten(iMin);
        return iMin;
    }

    private static final void writeBufferAppendUnreserve(Buffer buffer, int i) {
        if ((buffer.getLimit() - buffer.getWritePosition()) + (buffer.getCapacity() - buffer.getLimit()) < i) {
            throw new IllegalArgumentException("Can't append buffer: not enough free space at the end");
        }
        if ((buffer.getWritePosition() + i) - buffer.getLimit() > 0) {
            buffer.releaseEndGap$ktor_io();
        }
    }
}
