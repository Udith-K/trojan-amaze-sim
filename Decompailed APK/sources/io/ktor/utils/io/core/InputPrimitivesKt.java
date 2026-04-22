package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InputPrimitives.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class InputPrimitivesKt {
    private static final short readShortFallback(Input input) throws EOFException {
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 2);
        if (chunkBufferPrepareReadFirstHead != null) {
            short s = BufferPrimitivesKt.readShort(chunkBufferPrepareReadFirstHead);
            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            return s;
        }
        StringsKt.prematureEndOfStream(2);
        throw new KotlinNothingValueException();
    }

    public static final short readShort(Input input) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        if (input.getHeadEndExclusive() - input.getHeadPosition() <= 2) {
            return readShortFallback(input);
        }
        int headPosition = input.getHeadPosition();
        input.setHeadPosition(headPosition + 2);
        return input.m2623getHeadMemorySK3TCg8().getShort(headPosition);
    }
}
