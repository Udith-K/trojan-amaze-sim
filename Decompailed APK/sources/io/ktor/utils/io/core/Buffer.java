package io.ktor.utils.io.core;

import ch.qos.logback.core.CoreConstants;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* JADX INFO: compiled from: Buffer.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Buffer {
    public static final Companion Companion = new Companion(null);
    private final int capacity;
    private int limit;
    private final ByteBuffer memory;
    private int readPosition;
    private int startGap;
    private int writePosition;

    public /* synthetic */ Buffer(ByteBuffer byteBuffer, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer);
    }

    private Buffer(ByteBuffer memory) {
        Intrinsics.checkNotNullParameter(memory, "memory");
        this.memory = memory;
        this.limit = memory.limit();
        this.capacity = memory.limit();
    }

    /* JADX INFO: renamed from: getMemory-SK3TCg8, reason: not valid java name */
    public final ByteBuffer m2621getMemorySK3TCg8() {
        return this.memory;
    }

    public final int getReadPosition() {
        return this.readPosition;
    }

    public final int getWritePosition() {
        return this.writePosition;
    }

    public final int getStartGap() {
        return this.startGap;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public final void discardExact(int i) {
        if (i == 0) {
            return;
        }
        int i2 = this.readPosition + i;
        if (i < 0 || i2 > this.writePosition) {
            BufferKt.discardFailed(i, getWritePosition() - getReadPosition());
            throw new KotlinNothingValueException();
        }
        this.readPosition = i2;
    }

    public final void commitWritten(int i) {
        int i2 = this.writePosition + i;
        if (i < 0 || i2 > this.limit) {
            BufferKt.commitWrittenFailed(i, getLimit() - getWritePosition());
            throw new KotlinNothingValueException();
        }
        this.writePosition = i2;
    }

    public final boolean commitWrittenUntilIndex(int i) {
        int i2 = this.limit;
        int i3 = this.writePosition;
        if (i < i3) {
            BufferKt.commitWrittenFailed(i - i3, getLimit() - getWritePosition());
            throw new KotlinNothingValueException();
        }
        if (i < i2) {
            this.writePosition = i;
            return true;
        }
        if (i == i2) {
            this.writePosition = i;
            return false;
        }
        BufferKt.commitWrittenFailed(i - i3, getLimit() - getWritePosition());
        throw new KotlinNothingValueException();
    }

    public final void discardUntilIndex$ktor_io(int i) {
        if (i < 0 || i > this.writePosition) {
            BufferKt.discardFailed(i - this.readPosition, getWritePosition() - getReadPosition());
            throw new KotlinNothingValueException();
        }
        if (this.readPosition != i) {
            this.readPosition = i;
        }
    }

    public final void reserveStartGap(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(("startGap shouldn't be negative: " + i).toString());
        }
        int i2 = this.readPosition;
        if (i2 >= i) {
            this.startGap = i;
            return;
        }
        if (i2 == this.writePosition) {
            if (i > this.limit) {
                BufferKt.startGapReservationFailedDueToLimit(this, i);
                throw new KotlinNothingValueException();
            }
            this.writePosition = i;
            this.readPosition = i;
            this.startGap = i;
            return;
        }
        BufferKt.startGapReservationFailed(this, i);
        throw new KotlinNothingValueException();
    }

    public final void reserveEndGap(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(("endGap shouldn't be negative: " + i).toString());
        }
        int i2 = this.capacity - i;
        if (i2 >= this.writePosition) {
            this.limit = i2;
            return;
        }
        if (i2 < 0) {
            BufferKt.endGapReservationFailedDueToCapacity(this, i);
        }
        if (i2 < this.startGap) {
            BufferKt.endGapReservationFailedDueToStartGap(this, i);
        }
        if (this.readPosition == this.writePosition) {
            this.limit = i2;
            this.readPosition = i2;
            this.writePosition = i2;
            return;
        }
        BufferKt.endGapReservationFailedDueToContent(this, i);
    }

    public final void resetForWrite() {
        resetForWrite(this.capacity - this.startGap);
    }

    public final void resetForWrite(int i) {
        int i2 = this.startGap;
        this.readPosition = i2;
        this.writePosition = i2;
        this.limit = i;
    }

    public final void releaseGaps$ktor_io() {
        releaseStartGap$ktor_io(0);
        releaseEndGap$ktor_io();
    }

    public final void releaseEndGap$ktor_io() {
        this.limit = this.capacity;
    }

    public final void releaseStartGap$ktor_io(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(("newReadPosition shouldn't be negative: " + i).toString());
        }
        if (i > this.readPosition) {
            throw new IllegalArgumentException(("newReadPosition shouldn't be ahead of the read position: " + i + " > " + this.readPosition).toString());
        }
        this.readPosition = i;
        if (this.startGap > i) {
            this.startGap = i;
        }
    }

    public final byte readByte() throws EOFException {
        int i = this.readPosition;
        if (i == this.writePosition) {
            throw new EOFException("No readable bytes available.");
        }
        this.readPosition = i + 1;
        return this.memory.get(i);
    }

    public void reset() {
        releaseGaps$ktor_io();
        resetForWrite();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Buffer[0x");
        String string = Integer.toString(hashCode(), CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "toString(this, checkRadix(radix))");
        sb.append(string);
        sb.append("](");
        sb.append(getWritePosition() - getReadPosition());
        sb.append(" used, ");
        sb.append(getLimit() - getWritePosition());
        sb.append(" free, ");
        sb.append(this.startGap + (getCapacity() - getLimit()));
        sb.append(" reserved of ");
        sb.append(this.capacity);
        sb.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        return sb.toString();
    }

    /* JADX INFO: compiled from: Buffer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Buffer getEmpty() {
            return ChunkBuffer.Companion.getEmpty();
        }
    }
}
