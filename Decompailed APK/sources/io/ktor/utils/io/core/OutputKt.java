package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Output.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class OutputKt {
    public static /* synthetic */ void writeFully$default(Output output, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        writeFully(output, bArr, i, i2);
    }

    public static final void writeFully(Output output, byte[] src, int i, int i2) {
        Intrinsics.checkNotNullParameter(output, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        while (true) {
            try {
                int iMin = Math.min(i2, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                BufferPrimitivesKt.writeFully(chunkBufferPrepareWriteHead, src, i, iMin);
                i += iMin;
                i2 -= iMin;
                if (i2 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }
}
