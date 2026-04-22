package io.ktor.utils.io.core;

import ch.qos.logback.core.CoreConstants;
import io.ktor.utils.io.bits.MemoryJvmKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BufferUtilsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BufferUtilsJvmKt {
    public static final void readFully(Buffer buffer, ByteBuffer dst, int i) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        ByteBuffer byteBufferM2621getMemorySK3TCg8 = buffer.m2621getMemorySK3TCg8();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= i) {
            int iLimit = dst.limit();
            try {
                dst.limit(dst.position() + i);
                MemoryJvmKt.m2618copyTo62zg_DM(byteBufferM2621getMemorySK3TCg8, dst, readPosition);
                dst.limit(iLimit);
                Unit unit = Unit.INSTANCE;
                buffer.discardExact(i);
                return;
            } catch (Throwable th) {
                dst.limit(iLimit);
                throw th;
            }
        }
        throw new EOFException("Not enough bytes to read a buffer content of size " + i + CoreConstants.DOT);
    }
}
