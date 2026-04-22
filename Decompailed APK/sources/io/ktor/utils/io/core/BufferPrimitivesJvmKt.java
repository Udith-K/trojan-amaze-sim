package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.MemoryJvmKt;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BufferPrimitivesJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BufferPrimitivesJvmKt {
    public static final void writeFully(Buffer buffer, ByteBuffer source) throws InsufficientSpaceException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int iRemaining = source.remaining();
        ByteBuffer byteBufferM2621getMemorySK3TCg8 = buffer.m2621getMemorySK3TCg8();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit >= iRemaining) {
            MemoryJvmKt.m2620copyToSG11BkQ(source, byteBufferM2621getMemorySK3TCg8, writePosition);
            buffer.commitWritten(iRemaining);
            return;
        }
        throw new InsufficientSpaceException("buffer content", iRemaining, limit);
    }
}
