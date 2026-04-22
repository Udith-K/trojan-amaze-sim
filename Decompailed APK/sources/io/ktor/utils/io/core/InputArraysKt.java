package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InputArrays.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class InputArraysKt {
    public static final void readFully(Input input, byte[] dst, int i, int i2) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead != null) {
            while (true) {
                try {
                    int iMin = Math.min(i2, chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition());
                    BufferPrimitivesKt.readFully(chunkBufferPrepareReadFirstHead, dst, i, iMin);
                    i2 -= iMin;
                    i += iMin;
                    if (i2 > 0) {
                        try {
                            chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                            if (chunkBufferPrepareReadFirstHead == null) {
                                break;
                            }
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        if (i2 <= 0) {
            return;
        }
        StringsKt.prematureEndOfStream(i2);
        throw new KotlinNothingValueException();
    }
}
