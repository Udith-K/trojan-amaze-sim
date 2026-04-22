package io.ktor.utils.io.core;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OutputPrimitives.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class OutputPrimitivesKt {
    public static final void writeShort(Output output, short s) {
        Intrinsics.checkNotNullParameter(output, "<this>");
        int tailPosition$ktor_io = output.getTailPosition$ktor_io();
        if (output.getTailEndExclusive$ktor_io() - tailPosition$ktor_io <= 2) {
            writeShortFallback(output, s);
        } else {
            output.setTailPosition$ktor_io(tailPosition$ktor_io + 2);
            output.m2624getTailMemorySK3TCg8$ktor_io().putShort(tailPosition$ktor_io, s);
        }
    }

    private static final void writeShortFallback(Output output, short s) {
        BufferPrimitivesKt.writeShort(output.prepareWriteHead(2), s);
        output.afterHeadWrite();
    }
}
