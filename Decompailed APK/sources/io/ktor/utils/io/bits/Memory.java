package io.ktor.utils.io.bits;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Memory {
    public static final Companion Companion = new Companion(null);
    private static final ByteBuffer Empty;

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static ByteBuffer m2614constructorimpl(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        return buffer;
    }

    /* JADX INFO: renamed from: slice-87lwejk, reason: not valid java name */
    public static final ByteBuffer m2616slice87lwejk(ByteBuffer byteBuffer, int i, int i2) {
        return m2614constructorimpl(MemoryJvmKt.sliceSafe(byteBuffer, i, i2));
    }

    /* JADX INFO: renamed from: copyTo-JT6ljtQ, reason: not valid java name */
    public static final void m2615copyToJT6ljtQ(ByteBuffer byteBuffer, ByteBuffer destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (byteBuffer.hasArray() && destination.hasArray() && !byteBuffer.isReadOnly() && !destination.isReadOnly()) {
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + i, destination.array(), destination.arrayOffset() + i3, i2);
            return;
        }
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i);
        byteBufferDuplicate.limit(i + i2);
        ByteBuffer byteBufferDuplicate2 = destination.duplicate();
        byteBufferDuplicate2.position(i3);
        byteBufferDuplicate2.put(byteBufferDuplicate);
    }

    /* JADX INFO: compiled from: MemoryJvm.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getEmpty-SK3TCg8, reason: not valid java name */
        public final ByteBuffer m2617getEmptySK3TCg8() {
            return Memory.Empty;
        }
    }

    static {
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(0).order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(byteBufferOrder, "allocate(0).order(ByteOrder.BIG_ENDIAN)");
        Empty = m2614constructorimpl(byteBufferOrder);
    }
}
