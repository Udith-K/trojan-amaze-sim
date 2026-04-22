package io.ktor.utils.io.internal;

import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReadWriteBufferState.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ReadWriteBufferStateKt {
    private static final ByteBuffer EmptyByteBuffer;
    private static final RingBufferCapacity EmptyCapacity;

    static {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(0);
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(0)");
        EmptyByteBuffer = byteBufferAllocate;
        EmptyCapacity = new RingBufferCapacity(0);
    }

    public static final ByteBuffer getEmptyByteBuffer() {
        return EmptyByteBuffer;
    }

    public static final RingBufferCapacity getEmptyCapacity() {
        return EmptyCapacity;
    }
}
