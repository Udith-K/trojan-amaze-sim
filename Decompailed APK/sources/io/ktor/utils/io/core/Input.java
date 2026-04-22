package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.MalformedUTF8InputException;
import io.ktor.utils.io.core.internal.UTF8Kt;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.Closeable;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Input.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Input implements Closeable {
    public static final Companion Companion = new Companion(null);
    private ChunkBuffer _head;
    private int headEndExclusive;
    private ByteBuffer headMemory;
    private int headPosition;
    private boolean noMoreChunksAvailable;
    private final ObjectPool pool;
    private long tailRemaining;

    protected abstract void closeSource();

    protected abstract ChunkBuffer fill();

    public Input(ChunkBuffer head, long j, ObjectPool pool) {
        Intrinsics.checkNotNullParameter(head, "head");
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.pool = pool;
        this._head = head;
        this.headMemory = head.m2621getMemorySK3TCg8();
        this.headPosition = head.getReadPosition();
        int writePosition = head.getWritePosition();
        this.headEndExclusive = writePosition;
        this.tailRemaining = j - ((long) (writePosition - this.headPosition));
    }

    private final void set_head(ChunkBuffer chunkBuffer) {
        this._head = chunkBuffer;
        this.headMemory = chunkBuffer.m2621getMemorySK3TCg8();
        this.headPosition = chunkBuffer.getReadPosition();
        this.headEndExclusive = chunkBuffer.getWritePosition();
    }

    public final ChunkBuffer getHead() {
        ChunkBuffer chunkBuffer = this._head;
        chunkBuffer.discardUntilIndex$ktor_io(this.headPosition);
        return chunkBuffer;
    }

    /* JADX INFO: renamed from: getHeadMemory-SK3TCg8, reason: not valid java name */
    public final ByteBuffer m2623getHeadMemorySK3TCg8() {
        return this.headMemory;
    }

    public final int getHeadPosition() {
        return this.headPosition;
    }

    public final void setHeadPosition(int i) {
        this.headPosition = i;
    }

    public final int getHeadEndExclusive() {
        return this.headEndExclusive;
    }

    private final void afterRead(ChunkBuffer chunkBuffer) {
        if (chunkBuffer.getWritePosition() - chunkBuffer.getReadPosition() == 0) {
            releaseHead$ktor_io(chunkBuffer);
        }
    }

    public final void setTailRemaining(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(("tailRemaining shouldn't be negative: " + j).toString());
        }
        this.tailRemaining = j;
    }

    private final ChunkBuffer prepareReadLoop(int i, ChunkBuffer chunkBuffer) {
        while (true) {
            int headEndExclusive = getHeadEndExclusive() - getHeadPosition();
            if (headEndExclusive >= i) {
                return chunkBuffer;
            }
            ChunkBuffer next = chunkBuffer.getNext();
            if (next == null && (next = doFill()) == null) {
                return null;
            }
            if (headEndExclusive == 0) {
                if (chunkBuffer != ChunkBuffer.Companion.getEmpty()) {
                    releaseHead$ktor_io(chunkBuffer);
                }
                chunkBuffer = next;
            } else {
                int iWriteBufferAppend = BufferAppendKt.writeBufferAppend(chunkBuffer, next, i - headEndExclusive);
                this.headEndExclusive = chunkBuffer.getWritePosition();
                setTailRemaining(this.tailRemaining - ((long) iWriteBufferAppend));
                if (next.getWritePosition() <= next.getReadPosition()) {
                    chunkBuffer.setNext(null);
                    chunkBuffer.setNext(next.cleanNext());
                    next.release(this.pool);
                } else {
                    next.reserveStartGap(iWriteBufferAppend);
                }
                if (chunkBuffer.getWritePosition() - chunkBuffer.getReadPosition() >= i) {
                    return chunkBuffer;
                }
                if (i > 8) {
                    minSizeIsTooBig(i);
                    throw new KotlinNothingValueException();
                }
            }
        }
    }

    public final boolean getEndOfInput() {
        return getHeadEndExclusive() - getHeadPosition() == 0 && this.tailRemaining == 0 && (this.noMoreChunksAvailable || doFill() == null);
    }

    public final long getRemaining() {
        return ((long) (getHeadEndExclusive() - getHeadPosition())) + this.tailRemaining;
    }

    public final boolean canRead() {
        return (this.headPosition == this.headEndExclusive && this.tailRemaining == 0) ? false : true;
    }

    public final void release() {
        ChunkBuffer head = getHead();
        ChunkBuffer empty = ChunkBuffer.Companion.getEmpty();
        if (head != empty) {
            set_head(empty);
            setTailRemaining(0L);
            BuffersKt.releaseAll(head, this.pool);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        release();
        if (!this.noMoreChunksAvailable) {
            this.noMoreChunksAvailable = true;
        }
        closeSource();
    }

    public final int discard(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(("Negative discard is not allowed: " + i).toString());
        }
        return discardAsMuchAsPossible(i, 0);
    }

    public final void discardExact(int i) throws EOFException {
        if (discard(i) == i) {
            return;
        }
        throw new EOFException("Unable to discard " + i + " bytes due to end of packet");
    }

    public static /* synthetic */ String readText$default(Input input, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readText");
        }
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return input.readText(i, i2);
    }

    public final String readText(int i, int i2) throws Throwable {
        if (i == 0 && (i2 == 0 || getEndOfInput())) {
            return "";
        }
        long remaining = getRemaining();
        if (remaining > 0 && i2 >= remaining) {
            return StringsKt.readTextExactBytes$default(this, (int) remaining, null, 2, null);
        }
        StringBuilder sb = new StringBuilder(RangesKt.coerceAtMost(RangesKt.coerceAtLeast(i, 16), i2));
        readASCII(sb, i, i2);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    private final int readASCII(Appendable appendable, int i, int i2) throws Throwable {
        int i3;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        if (i2 == 0 && i == 0) {
            return 0;
        }
        if (getEndOfInput()) {
            if (i == 0) {
                return 0;
            }
            atLeastMinCharactersRequire(i);
            throw new KotlinNothingValueException();
        }
        if (i2 < i) {
            minShouldBeLess(i, i2);
            throw new KotlinNothingValueException();
        }
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(this, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i3 = 0;
        } else {
            i3 = 0;
            boolean z5 = false;
            while (true) {
                try {
                    ByteBuffer byteBufferM2621getMemorySK3TCg8 = chunkBufferPrepareReadFirstHead.m2621getMemorySK3TCg8();
                    int readPosition = chunkBufferPrepareReadFirstHead.getReadPosition();
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition();
                    for (int i4 = readPosition; i4 < writePosition; i4++) {
                        byte b = byteBufferM2621getMemorySK3TCg8.get(i4);
                        int i5 = b & 255;
                        if ((b & 128) != 128) {
                            char c = (char) i5;
                            if (i3 == i2) {
                                z3 = false;
                            } else {
                                appendable.append(c);
                                i3++;
                                z3 = true;
                            }
                            if (z3) {
                            }
                        }
                        chunkBufferPrepareReadFirstHead.discardExact(i4 - readPosition);
                        z = false;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead.discardExact(writePosition - readPosition);
                    z = true;
                    if (z) {
                        z2 = true;
                    } else if (i3 == i2) {
                        z2 = false;
                    } else {
                        z2 = false;
                        z5 = true;
                    }
                    if (z2) {
                        try {
                            chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(this, chunkBufferPrepareReadFirstHead);
                            if (chunkBufferPrepareReadFirstHead == null) {
                                break;
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (z4) {
                                UnsafeKt.completeReadHead(this, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else {
                        UnsafeKt.completeReadHead(this, chunkBufferPrepareReadFirstHead);
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z4 = true;
                }
            }
            z4 = z5;
        }
        if (z4) {
            return i3 + readUtf8(appendable, i - i3, i2 - i3);
        }
        if (i3 >= i) {
            return i3;
        }
        prematureEndOfStreamChars(i, i3);
        throw new KotlinNothingValueException();
    }

    private final Void atLeastMinCharactersRequire(int i) throws EOFException {
        throw new EOFException("at least " + i + " characters required but no bytes available");
    }

    private final Void minShouldBeLess(int i, int i2) {
        throw new IllegalArgumentException("min should be less or equal to max but min = " + i + ", max = " + i2);
    }

    private final Void prematureEndOfStreamChars(int i, int i2) throws MalformedUTF8InputException {
        throw new MalformedUTF8InputException("Premature end of stream: expected at least " + i + " chars but had only " + i2);
    }

    private final int discardAsMuchAsPossible(int i, int i2) {
        while (i != 0) {
            ChunkBuffer chunkBufferPrepareRead = prepareRead(1);
            if (chunkBufferPrepareRead == null) {
                return i2;
            }
            int iMin = Math.min(chunkBufferPrepareRead.getWritePosition() - chunkBufferPrepareRead.getReadPosition(), i);
            chunkBufferPrepareRead.discardExact(iMin);
            this.headPosition += iMin;
            afterRead(chunkBufferPrepareRead);
            i -= iMin;
            i2 += iMin;
        }
        return i2;
    }

    public final ChunkBuffer prepareReadHead$ktor_io(int i) {
        return prepareReadLoop(i, getHead());
    }

    public final ChunkBuffer ensureNextHead$ktor_io(ChunkBuffer current) {
        Intrinsics.checkNotNullParameter(current, "current");
        return ensureNext(current);
    }

    public final ChunkBuffer ensureNext(ChunkBuffer current) {
        Intrinsics.checkNotNullParameter(current, "current");
        return ensureNext(current, ChunkBuffer.Companion.getEmpty());
    }

    public final void fixGapAfterRead$ktor_io(ChunkBuffer current) {
        Intrinsics.checkNotNullParameter(current, "current");
        ChunkBuffer next = current.getNext();
        if (next == null) {
            fixGapAfterReadFallback(current);
            return;
        }
        int writePosition = current.getWritePosition() - current.getReadPosition();
        int iMin = Math.min(writePosition, 8 - (current.getCapacity() - current.getLimit()));
        if (next.getStartGap() < iMin) {
            fixGapAfterReadFallback(current);
            return;
        }
        BufferKt.restoreStartGap(next, iMin);
        if (writePosition > iMin) {
            current.releaseEndGap$ktor_io();
            this.headEndExclusive = current.getWritePosition();
            setTailRemaining(this.tailRemaining + ((long) iMin));
        } else {
            set_head(next);
            setTailRemaining(this.tailRemaining - ((long) ((next.getWritePosition() - next.getReadPosition()) - iMin)));
            current.cleanNext();
            current.release(this.pool);
        }
    }

    private final void fixGapAfterReadFallback(ChunkBuffer chunkBuffer) {
        if (!this.noMoreChunksAvailable || chunkBuffer.getNext() != null) {
            int writePosition = chunkBuffer.getWritePosition() - chunkBuffer.getReadPosition();
            int iMin = Math.min(writePosition, 8 - (chunkBuffer.getCapacity() - chunkBuffer.getLimit()));
            if (writePosition > iMin) {
                fixGapAfterReadFallbackUnreserved(chunkBuffer, writePosition, iMin);
            } else {
                ChunkBuffer chunkBuffer2 = (ChunkBuffer) this.pool.borrow();
                chunkBuffer2.reserveEndGap(8);
                chunkBuffer2.setNext(chunkBuffer.cleanNext());
                BufferAppendKt.writeBufferAppend(chunkBuffer2, chunkBuffer, writePosition);
                set_head(chunkBuffer2);
            }
            chunkBuffer.release(this.pool);
            return;
        }
        this.headPosition = chunkBuffer.getReadPosition();
        this.headEndExclusive = chunkBuffer.getWritePosition();
        setTailRemaining(0L);
    }

    private final void fixGapAfterReadFallbackUnreserved(ChunkBuffer chunkBuffer, int i, int i2) {
        ChunkBuffer chunkBuffer2 = (ChunkBuffer) this.pool.borrow();
        ChunkBuffer chunkBuffer3 = (ChunkBuffer) this.pool.borrow();
        chunkBuffer2.reserveEndGap(8);
        chunkBuffer3.reserveEndGap(8);
        chunkBuffer2.setNext(chunkBuffer3);
        chunkBuffer3.setNext(chunkBuffer.cleanNext());
        BufferAppendKt.writeBufferAppend(chunkBuffer2, chunkBuffer, i - i2);
        BufferAppendKt.writeBufferAppend(chunkBuffer3, chunkBuffer, i2);
        set_head(chunkBuffer2);
        setTailRemaining(BuffersKt.remainingAll(chunkBuffer3));
    }

    private final ChunkBuffer ensureNext(ChunkBuffer chunkBuffer, ChunkBuffer chunkBuffer2) {
        while (chunkBuffer != chunkBuffer2) {
            ChunkBuffer chunkBufferCleanNext = chunkBuffer.cleanNext();
            chunkBuffer.release(this.pool);
            if (chunkBufferCleanNext != null) {
                if (chunkBufferCleanNext.getWritePosition() > chunkBufferCleanNext.getReadPosition()) {
                    set_head(chunkBufferCleanNext);
                    setTailRemaining(this.tailRemaining - ((long) (chunkBufferCleanNext.getWritePosition() - chunkBufferCleanNext.getReadPosition())));
                    return chunkBufferCleanNext;
                }
                chunkBuffer = chunkBufferCleanNext;
            } else {
                set_head(chunkBuffer2);
                setTailRemaining(0L);
                chunkBuffer = chunkBuffer2;
            }
        }
        return doFill();
    }

    protected final void markNoMoreChunksAvailable() {
        if (this.noMoreChunksAvailable) {
            return;
        }
        this.noMoreChunksAvailable = true;
    }

    private final ChunkBuffer doFill() {
        if (this.noMoreChunksAvailable) {
            return null;
        }
        ChunkBuffer chunkBufferFill = fill();
        if (chunkBufferFill == null) {
            this.noMoreChunksAvailable = true;
            return null;
        }
        appendView(chunkBufferFill);
        return chunkBufferFill;
    }

    private final void appendView(ChunkBuffer chunkBuffer) {
        ChunkBuffer chunkBufferFindTail = BuffersKt.findTail(this._head);
        if (chunkBufferFindTail == ChunkBuffer.Companion.getEmpty()) {
            set_head(chunkBuffer);
            if (this.tailRemaining != 0) {
                throw new IllegalStateException("It should be no tail remaining bytes if current tail is EmptyBuffer");
            }
            ChunkBuffer next = chunkBuffer.getNext();
            setTailRemaining(next != null ? BuffersKt.remainingAll(next) : 0L);
            return;
        }
        chunkBufferFindTail.setNext(chunkBuffer);
        setTailRemaining(this.tailRemaining + BuffersKt.remainingAll(chunkBuffer));
    }

    public final ChunkBuffer prepareRead(int i) {
        ChunkBuffer head = getHead();
        return this.headEndExclusive - this.headPosition >= i ? head : prepareReadLoop(i, head);
    }

    private final Void minSizeIsTooBig(int i) {
        throw new IllegalStateException("minSize of " + i + " is too big (should be less than 8)");
    }

    public final ChunkBuffer releaseHead$ktor_io(ChunkBuffer head) {
        Intrinsics.checkNotNullParameter(head, "head");
        ChunkBuffer chunkBufferCleanNext = head.cleanNext();
        if (chunkBufferCleanNext == null) {
            chunkBufferCleanNext = ChunkBuffer.Companion.getEmpty();
        }
        set_head(chunkBufferCleanNext);
        setTailRemaining(this.tailRemaining - ((long) (chunkBufferCleanNext.getWritePosition() - chunkBufferCleanNext.getReadPosition())));
        head.release(this.pool);
        return chunkBufferCleanNext;
    }

    /* JADX INFO: compiled from: Input.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final int readUtf8(Appendable appendable, int i, int i2) throws Throwable {
        ByteBuffer byteBufferM2621getMemorySK3TCg8;
        int readPosition;
        int writePosition;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        int i8;
        int i9 = 1;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(this, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i8 = 0;
        } else {
            int i10 = 1;
            int i11 = 0;
            while (true) {
                try {
                    int writePosition2 = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition2 >= i10) {
                        try {
                            byteBufferM2621getMemorySK3TCg8 = chunkBufferPrepareReadFirstHead.m2621getMemorySK3TCg8();
                            readPosition = chunkBufferPrepareReadFirstHead.getReadPosition();
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition();
                            i4 = 0;
                            i5 = 0;
                            i6 = 0;
                        } catch (Throwable th) {
                            th = th;
                        }
                        for (i3 = readPosition; i3 < writePosition; i3++) {
                            byte b = byteBufferM2621getMemorySK3TCg8.get(i3);
                            int i12 = b & 255;
                            i7 = -1;
                            if ((b & 128) == 0) {
                                if (i4 != 0) {
                                    UTF8Kt.malformedByteCount(i4);
                                    throw new KotlinNothingValueException();
                                }
                                char c = (char) i12;
                                if (i11 == i2) {
                                    z4 = false;
                                } else {
                                    appendable.append(c);
                                    i11++;
                                    z4 = true;
                                }
                                if (!z4) {
                                    try {
                                        chunkBufferPrepareReadFirstHead.discardExact(i3 - readPosition);
                                        i9 = 1;
                                        break;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        i9 = 1;
                                        chunkBufferPrepareReadFirstHead.getWritePosition();
                                        chunkBufferPrepareReadFirstHead.getReadPosition();
                                        throw th;
                                    }
                                }
                                i9 = 1;
                                th = th2;
                                i9 = 1;
                                chunkBufferPrepareReadFirstHead.getWritePosition();
                                chunkBufferPrepareReadFirstHead.getReadPosition();
                                throw th;
                            }
                            if (i4 == 0) {
                                int i13 = 128;
                                i5 = i12;
                                for (int i14 = 1; i14 < 7 && (i5 & i13) != 0; i14++) {
                                    i5 &= ~i13;
                                    i13 >>= 1;
                                    i4++;
                                }
                                int i15 = i4 - 1;
                                if (i4 > writePosition - i3) {
                                    chunkBufferPrepareReadFirstHead.discardExact(i3 - readPosition);
                                    i7 = i4;
                                    i9 = 1;
                                    break;
                                }
                                i6 = i4;
                                i4 = i15;
                                i9 = 1;
                            } else {
                                i5 = (i5 << 6) | (b & 127);
                                i4--;
                                if (i4 == 0) {
                                    if (!UTF8Kt.isBmpCodePoint(i5)) {
                                        if (!UTF8Kt.isValidCodePoint(i5)) {
                                            i9 = 1;
                                            UTF8Kt.malformedCodePoint(i5);
                                            throw new KotlinNothingValueException();
                                        }
                                        char cHighSurrogate = (char) UTF8Kt.highSurrogate(i5);
                                        if (i11 == i2) {
                                            z = false;
                                        } else {
                                            appendable.append(cHighSurrogate);
                                            i11++;
                                            z = true;
                                        }
                                        if (z) {
                                            char cLowSurrogate = (char) UTF8Kt.lowSurrogate(i5);
                                            if (i11 == i2) {
                                                z2 = false;
                                            } else {
                                                appendable.append(cLowSurrogate);
                                                i11++;
                                                z2 = true;
                                            }
                                            if (!z2) {
                                            }
                                        }
                                        i9 = 1;
                                        chunkBufferPrepareReadFirstHead.discardExact(((i3 - readPosition) - i6) + 1);
                                        break;
                                        chunkBufferPrepareReadFirstHead.getWritePosition();
                                        chunkBufferPrepareReadFirstHead.getReadPosition();
                                        throw th;
                                    }
                                    char c2 = (char) i5;
                                    if (i11 == i2) {
                                        z3 = false;
                                    } else {
                                        appendable.append(c2);
                                        i11++;
                                        z3 = true;
                                    }
                                    if (!z3) {
                                        chunkBufferPrepareReadFirstHead.discardExact(((i3 - readPosition) - i6) + 1);
                                        i9 = 1;
                                        break;
                                    }
                                    i9 = 1;
                                    i5 = 0;
                                } else {
                                    i9 = 1;
                                }
                            }
                        }
                        chunkBufferPrepareReadFirstHead.discardExact(writePosition - readPosition);
                        i7 = 0;
                        i10 = i7 == 0 ? i9 : i7 > 0 ? i7 : 0;
                        writePosition2 = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    }
                    if (writePosition2 == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(this, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th3) {
                            th = th3;
                            i9 = 0;
                            if (i9 != 0) {
                                UnsafeKt.completeReadHead(this, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition2 < i10 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(this, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(this, i10);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        i9 = 0;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    if (i10 <= 0) {
                        break;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (i9 != 0) {
                UnsafeKt.completeReadHead(this, chunkBufferPrepareReadFirstHead);
            }
            i8 = i11;
        }
        if (i8 >= i) {
            return i8;
        }
        prematureEndOfStreamChars(i, i8);
        throw new KotlinNothingValueException();
    }
}
