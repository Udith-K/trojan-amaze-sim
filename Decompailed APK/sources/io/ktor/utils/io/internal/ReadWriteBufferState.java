package io.ktor.utils.io.internal;

import java.nio.ByteBuffer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReadWriteBufferState.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ReadWriteBufferState {
    public final ByteBuffer backingBuffer;
    public final RingBufferCapacity capacity;

    public /* synthetic */ ReadWriteBufferState(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer, ringBufferCapacity);
    }

    private ReadWriteBufferState(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity) {
        this.backingBuffer = byteBuffer;
        this.capacity = ringBufferCapacity;
    }

    public ByteBuffer getReadBuffer() {
        throw new IllegalStateException(("read buffer is not available in state " + this).toString());
    }

    public ByteBuffer getWriteBuffer() {
        throw new IllegalStateException(("write buffer is not available in state " + this).toString());
    }

    public ReadWriteBufferState startReading$ktor_io() {
        throw new IllegalStateException(("ByteChannel[state: " + this + "] Concurrent reading is not supported").toString());
    }

    public ReadWriteBufferState startWriting$ktor_io() {
        throw new IllegalStateException(("ByteChannel[state: " + this + "] Concurrent writing is not supported").toString());
    }

    public ReadWriteBufferState stopReading$ktor_io() {
        throw new IllegalStateException(("Unable to stop reading in state " + this).toString());
    }

    public ReadWriteBufferState stopWriting$ktor_io() {
        throw new IllegalStateException(("Unable to stop writing in state " + this).toString());
    }

    /* JADX INFO: compiled from: ReadWriteBufferState.kt */
    public static final class IdleEmpty extends ReadWriteBufferState {
        public static final IdleEmpty INSTANCE = new IdleEmpty();

        private IdleEmpty() {
            super(ReadWriteBufferStateKt.getEmptyByteBuffer(), ReadWriteBufferStateKt.getEmptyCapacity(), null);
        }

        public String toString() {
            return "IDLE(empty)";
        }
    }

    /* JADX INFO: compiled from: ReadWriteBufferState.kt */
    public static final class Initial extends ReadWriteBufferState {
        private final IdleNonEmpty idleState;
        private final ByteBuffer readBuffer;
        private final Reading readingState;
        private final ReadingWriting readingWritingState;
        private final ByteBuffer writeBuffer;
        private final Writing writingState;

        public /* synthetic */ Initial(ByteBuffer byteBuffer, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(byteBuffer, (i2 & 2) != 0 ? 8 : i);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Initial(ByteBuffer backingBuffer, int i) {
            super(backingBuffer, new RingBufferCapacity(backingBuffer.capacity() - i), null);
            Intrinsics.checkNotNullParameter(backingBuffer, "backingBuffer");
            if (backingBuffer.position() != 0) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (backingBuffer.limit() != backingBuffer.capacity()) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            ByteBuffer byteBufferDuplicate = backingBuffer.duplicate();
            Intrinsics.checkNotNullExpressionValue(byteBufferDuplicate, "backingBuffer.duplicate()");
            this.writeBuffer = byteBufferDuplicate;
            ByteBuffer byteBufferDuplicate2 = backingBuffer.duplicate();
            Intrinsics.checkNotNullExpressionValue(byteBufferDuplicate2, "backingBuffer.duplicate()");
            this.readBuffer = byteBufferDuplicate2;
            this.idleState = new IdleNonEmpty(this);
            this.readingState = new Reading(this);
            this.writingState = new Writing(this);
            this.readingWritingState = new ReadingWriting(this);
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public ByteBuffer getWriteBuffer() {
            return this.writeBuffer;
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public ByteBuffer getReadBuffer() {
            return this.readBuffer;
        }

        public final IdleNonEmpty getIdleState$ktor_io() {
            return this.idleState;
        }

        public final Reading getReadingState$ktor_io() {
            return this.readingState;
        }

        public final Writing getWritingState$ktor_io() {
            return this.writingState;
        }

        public final ReadingWriting getReadingWritingState$ktor_io() {
            return this.readingWritingState;
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public Reading startReading$ktor_io() {
            return this.readingState;
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public Writing startWriting$ktor_io() {
            return this.writingState;
        }

        public String toString() {
            return "Initial";
        }
    }

    /* JADX INFO: compiled from: ReadWriteBufferState.kt */
    public static final class IdleNonEmpty extends ReadWriteBufferState {
        private final Initial initial;

        public final Initial getInitial() {
            return this.initial;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IdleNonEmpty(Initial initial) {
            super(initial.backingBuffer, initial.capacity, null);
            Intrinsics.checkNotNullParameter(initial, "initial");
            this.initial = initial;
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public Reading startReading$ktor_io() {
            return this.initial.getReadingState$ktor_io();
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public Writing startWriting$ktor_io() {
            return this.initial.getWritingState$ktor_io();
        }

        public String toString() {
            return "IDLE(with buffer)";
        }
    }

    /* JADX INFO: compiled from: ReadWriteBufferState.kt */
    public static final class Reading extends ReadWriteBufferState {
        private final Initial initial;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Reading(Initial initial) {
            super(initial.backingBuffer, initial.capacity, null);
            Intrinsics.checkNotNullParameter(initial, "initial");
            this.initial = initial;
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public ByteBuffer getReadBuffer() {
            return this.initial.getReadBuffer();
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public ReadingWriting startWriting$ktor_io() {
            return this.initial.getReadingWritingState$ktor_io();
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public IdleNonEmpty stopReading$ktor_io() {
            return this.initial.getIdleState$ktor_io();
        }

        public String toString() {
            return "Reading";
        }
    }

    /* JADX INFO: compiled from: ReadWriteBufferState.kt */
    public static final class Writing extends ReadWriteBufferState {
        private final Initial initial;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Writing(Initial initial) {
            super(initial.backingBuffer, initial.capacity, null);
            Intrinsics.checkNotNullParameter(initial, "initial");
            this.initial = initial;
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public ByteBuffer getWriteBuffer() {
            return this.initial.getWriteBuffer();
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public ReadingWriting startReading$ktor_io() {
            return this.initial.getReadingWritingState$ktor_io();
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public IdleNonEmpty stopWriting$ktor_io() {
            return this.initial.getIdleState$ktor_io();
        }

        public String toString() {
            return "Writing";
        }
    }

    /* JADX INFO: compiled from: ReadWriteBufferState.kt */
    public static final class ReadingWriting extends ReadWriteBufferState {
        private final Initial initial;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReadingWriting(Initial initial) {
            super(initial.backingBuffer, initial.capacity, null);
            Intrinsics.checkNotNullParameter(initial, "initial");
            this.initial = initial;
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public ByteBuffer getReadBuffer() {
            return this.initial.getReadBuffer();
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public ByteBuffer getWriteBuffer() {
            return this.initial.getWriteBuffer();
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public Writing stopReading$ktor_io() {
            return this.initial.getWritingState$ktor_io();
        }

        @Override // io.ktor.utils.io.internal.ReadWriteBufferState
        public Reading stopWriting$ktor_io() {
            return this.initial.getReadingState$ktor_io();
        }

        public String toString() {
            return "Reading+Writing";
        }
    }

    /* JADX INFO: compiled from: ReadWriteBufferState.kt */
    public static final class Terminated extends ReadWriteBufferState {
        public static final Terminated INSTANCE = new Terminated();

        private Terminated() {
            super(ReadWriteBufferStateKt.getEmptyByteBuffer(), ReadWriteBufferStateKt.getEmptyCapacity(), null);
        }

        public String toString() {
            return "Terminated";
        }
    }
}
