package io.ktor.utils.io.bits;

import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryFactoryJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DefaultAllocator implements Allocator {
    public static final DefaultAllocator INSTANCE = new DefaultAllocator();

    @Override // io.ktor.utils.io.bits.Allocator
    /* JADX INFO: renamed from: free-3GNKZMM */
    public void mo2613free3GNKZMM(ByteBuffer instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
    }

    private DefaultAllocator() {
    }

    @Override // io.ktor.utils.io.bits.Allocator
    /* JADX INFO: renamed from: alloc-gFv-Zug */
    public ByteBuffer mo2612allocgFvZug(int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(size)");
        return Memory.m2614constructorimpl(byteBufferAllocate);
    }
}
