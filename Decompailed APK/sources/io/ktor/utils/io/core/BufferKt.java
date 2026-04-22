package io.ktor.utils.io.core;

import java.io.EOFException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Buffer.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BufferKt {
    public static final Void discardFailed(int i, int i2) {
        throw new EOFException("Unable to discard " + i + " bytes: only " + i2 + " available for reading");
    }

    public static final Void commitWrittenFailed(int i, int i2) {
        throw new EOFException("Unable to discard " + i + " bytes: only " + i2 + " available for writing");
    }

    public static final Void startGapReservationFailedDueToLimit(Buffer buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (i > buffer.getCapacity()) {
            throw new IllegalArgumentException("Start gap " + i + " is bigger than the capacity " + buffer.getCapacity());
        }
        throw new IllegalStateException("Unable to reserve " + i + " start gap: there are already " + (buffer.getCapacity() - buffer.getLimit()) + " bytes reserved in the end");
    }

    public static final Void startGapReservationFailed(Buffer buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        throw new IllegalStateException("Unable to reserve " + i + " start gap: there are already " + (buffer.getWritePosition() - buffer.getReadPosition()) + " content bytes starting at offset " + buffer.getReadPosition());
    }

    public static final void endGapReservationFailedDueToCapacity(Buffer buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        throw new IllegalArgumentException("End gap " + i + " is too big: capacity is " + buffer.getCapacity());
    }

    public static final void endGapReservationFailedDueToStartGap(Buffer buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        throw new IllegalArgumentException("End gap " + i + " is too big: there are already " + buffer.getStartGap() + " bytes reserved in the beginning");
    }

    public static final void endGapReservationFailedDueToContent(Buffer buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        throw new IllegalArgumentException("Unable to reserve end gap " + i + ": there are already " + (buffer.getWritePosition() - buffer.getReadPosition()) + " content bytes at offset " + buffer.getReadPosition());
    }

    public static final void restoreStartGap(Buffer buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        buffer.releaseStartGap$ktor_io(buffer.getReadPosition() - i);
    }
}
