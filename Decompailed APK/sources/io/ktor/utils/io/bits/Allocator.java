package io.ktor.utils.io.bits;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: MemoryFactory.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Allocator {
    /* JADX INFO: renamed from: alloc-gFv-Zug, reason: not valid java name */
    ByteBuffer mo2612allocgFvZug(int i);

    /* JADX INFO: renamed from: free-3GNKZMM, reason: not valid java name */
    void mo2613free3GNKZMM(ByteBuffer byteBuffer);
}
