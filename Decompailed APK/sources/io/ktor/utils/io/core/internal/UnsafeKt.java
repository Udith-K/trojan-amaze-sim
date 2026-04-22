package io.ktor.utils.io.core.internal;

import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.Output;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Unsafe.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class UnsafeKt {
    public static final byte[] EmptyByteArray = new byte[0];

    public static final ChunkBuffer prepareReadFirstHead(Input input, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        return input.prepareReadHead$ktor_io(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ChunkBuffer prepareReadNextHead(Input input, ChunkBuffer current) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(current, "current");
        if (current == input) {
            if (input.canRead()) {
                return (ChunkBuffer) input;
            }
            return null;
        }
        return input.ensureNextHead$ktor_io(current);
    }

    public static final ChunkBuffer prepareWriteHead(Output output, int i, ChunkBuffer chunkBuffer) {
        Intrinsics.checkNotNullParameter(output, "<this>");
        if (chunkBuffer != null) {
            output.afterHeadWrite();
        }
        return output.prepareWriteHead(i);
    }

    public static final void completeReadHead(Input input, ChunkBuffer current) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(current, "current");
        if (current == input) {
            return;
        }
        if (current.getWritePosition() <= current.getReadPosition()) {
            input.ensureNext(current);
        } else if (current.getCapacity() - current.getLimit() < 8) {
            input.fixGapAfterRead$ktor_io(current);
        } else {
            input.setHeadPosition(current.getReadPosition());
        }
    }
}
