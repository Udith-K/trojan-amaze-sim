package io.ktor.utils.io;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import ch.qos.logback.core.CoreConstants;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BufferPrimitivesJvmKt;
import io.ktor.utils.io.core.BufferUtilsJvmKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.internal.CancellableReusableContinuation;
import io.ktor.utils.io.internal.ClosedElement;
import io.ktor.utils.io.internal.JoiningState;
import io.ktor.utils.io.internal.ObjectPoolKt;
import io.ktor.utils.io.internal.ReadSessionImpl;
import io.ktor.utils.io.internal.ReadWriteBufferState;
import io.ktor.utils.io.internal.RingBufferCapacity;
import io.ktor.utils.io.internal.WriteSessionImpl;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: ByteBufferChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public class ByteBufferChannel implements ByteChannel, ByteReadChannel, ByteWriteChannel {
    private volatile /* synthetic */ Object _closed;
    private volatile /* synthetic */ Object _readOp;
    private volatile /* synthetic */ Object _state;
    volatile /* synthetic */ Object _writeOp;
    private volatile Job attachedJob;
    private final boolean autoFlush;
    private volatile JoiningState joining;
    private final ObjectPool pool;
    private int readPosition;
    private final ReadSessionImpl readSession;
    private final CancellableReusableContinuation readSuspendContinuationCache;
    private final int reservedSize;
    private volatile long totalBytesRead;
    private volatile long totalBytesWritten;
    private int writePosition;
    private final WriteSessionImpl writeSession;
    private final CancellableReusableContinuation writeSuspendContinuationCache;
    private final Function1 writeSuspension;
    private volatile int writeSuspensionSize;
    public static final Companion Companion = new Companion(null);
    private static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_state");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closed$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_closed");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _readOp$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_readOp");
    static final /* synthetic */ AtomicReferenceFieldUpdater _writeOp$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_writeOp");

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class C01851 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01851(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.awaitFreeSpaceOrDelegate(0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class C01861 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01861(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readAvailableSuspend(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3, reason: invalid class name */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readAvailableSuspend(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readRemainingSuspend$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class C01871 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C01871(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readRemainingSuspend(0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class C01881 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01881(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readSuspendImpl(0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class C01891 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01891(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readSuspendLoop(0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$write$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class C01901 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01901(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.write$suspendImpl(ByteBufferChannel.this, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class C01913 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01913(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeFullySuspend(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5, reason: invalid class name */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class AnonymousClass5 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass5(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeFullySuspend(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeSuspend$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class C01921 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01921(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeSuspend(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeSuspend$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    static final class C01933 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C01933(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeSuspend(0, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldResumeReadOp() {
        return false;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(ChunkBuffer chunkBuffer, Continuation continuation) {
        return readAvailable$suspendImpl(this, chunkBuffer, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(byte[] bArr, int i, int i2, Continuation continuation) {
        return readAvailable$suspendImpl(this, bArr, i, i2, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readRemaining(long j, Continuation continuation) {
        return readRemaining$suspendImpl(this, j, continuation);
    }

    public final ByteBufferChannel resolveChannelInstance$ktor_io() {
        return this;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object write(int i, Function1 function1, Continuation continuation) {
        return write$suspendImpl(this, i, function1, continuation);
    }

    public Object writeAvailable(byte[] bArr, int i, int i2, Continuation continuation) {
        return writeAvailable$suspendImpl(this, bArr, i, i2, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(Buffer buffer, Continuation continuation) {
        return writeFully$suspendImpl(this, buffer, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(byte[] bArr, int i, int i2, Continuation continuation) {
        return writeFully$suspendImpl(this, bArr, i, i2, continuation);
    }

    private final ByteReadPacket remainingPacket(long j) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(bytePacketBuilder, 1, null);
            while (true) {
                try {
                    if (chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition() > j) {
                        chunkBufferPrepareWriteHead.resetForWrite((int) j);
                    }
                    j -= (long) readAsMuchAsPossible$default(this, chunkBufferPrepareWriteHead, 0, 0, 6, null);
                    if (j <= 0 || isClosedForRead()) {
                        break;
                    }
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(bytePacketBuilder, 1, chunkBufferPrepareWriteHead);
                } catch (Throwable th) {
                    bytePacketBuilder.afterHeadWrite();
                    throw th;
                }
            }
            bytePacketBuilder.afterHeadWrite();
            return bytePacketBuilder.build();
        } catch (Throwable th2) {
            bytePacketBuilder.release();
            throw th2;
        }
    }

    public ByteBufferChannel(boolean z, ObjectPool pool, int i) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.autoFlush = z;
        this.pool = pool;
        this.reservedSize = i;
        this._state = ReadWriteBufferState.IdleEmpty.INSTANCE;
        this._closed = null;
        this._readOp = null;
        this._writeOp = null;
        this.readSession = new ReadSessionImpl(this);
        this.writeSession = new WriteSessionImpl(this);
        this.readSuspendContinuationCache = new CancellableReusableContinuation();
        this.writeSuspendContinuationCache = new CancellableReusableContinuation();
        this.writeSuspension = new Function1() { // from class: io.ktor.utils.io.ByteBufferChannel$writeSuspension$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation ucont) throws Throwable {
                Throwable sendException;
                Intrinsics.checkNotNullParameter(ucont, "ucont");
                int i2 = this.this$0.writeSuspensionSize;
                while (true) {
                    ClosedElement closed = this.this$0.getClosed();
                    if (closed != null && (sendException = closed.getSendException()) != null) {
                        ByteBufferChannelKt.rethrowClosed(sendException);
                        throw new KotlinNothingValueException();
                    }
                    if (!this.this$0.writeSuspendPredicate(i2)) {
                        Result.Companion companion = Result.Companion;
                        ucont.resumeWith(Result.m2639constructorimpl(Unit.INSTANCE));
                        break;
                    }
                    ByteBufferChannel byteBufferChannel = this.this$0;
                    Continuation continuationIntercepted = IntrinsicsKt.intercepted(ucont);
                    ByteBufferChannel byteBufferChannel2 = this.this$0;
                    while (byteBufferChannel.getWriteOp() == null) {
                        if (!byteBufferChannel2.writeSuspendPredicate(i2)) {
                            break;
                        }
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = ByteBufferChannel._writeOp$FU;
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, byteBufferChannel, null, continuationIntercepted)) {
                            if (byteBufferChannel2.writeSuspendPredicate(i2) || !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, byteBufferChannel, continuationIntercepted, null)) {
                                break;
                            }
                        }
                    }
                    throw new IllegalStateException("Operation is already in progress");
                }
                this.this$0.flushImpl(i2);
                if (this.this$0.shouldResumeReadOp()) {
                    this.this$0.resumeReadOp();
                }
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
        };
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    public /* synthetic */ ByteBufferChannel(boolean z, ObjectPool objectPool, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i2 & 2) != 0 ? ObjectPoolKt.getBufferObjectPool() : objectPool, (i2 & 4) != 0 ? 8 : i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel(ByteBuffer content) {
        this(false, ObjectPoolKt.getBufferObjectNoPool(), 0);
        Intrinsics.checkNotNullParameter(content, "content");
        ByteBuffer byteBufferSlice = content.slice();
        Intrinsics.checkNotNullExpressionValue(byteBufferSlice, "content.slice()");
        ReadWriteBufferState.Initial initial = new ReadWriteBufferState.Initial(byteBufferSlice, 0);
        initial.capacity.resetForRead();
        this._state = initial.startWriting$ktor_io();
        restoreStateAfterWrite$ktor_io();
        ByteWriteChannelKt.close(this);
        tryTerminate$ktor_io();
    }

    private final ReadWriteBufferState getState() {
        return (ReadWriteBufferState) this._state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClosedElement getClosed() {
        return (ClosedElement) this._closed;
    }

    private final Continuation getReadOp() {
        return (Continuation) this._readOp;
    }

    private final void setReadOp(Continuation continuation) {
        this._readOp = continuation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Continuation getWriteOp() {
        return (Continuation) this._writeOp;
    }

    static /* synthetic */ int readAsMuchAsPossible$default(ByteBufferChannel byteBufferChannel, Buffer buffer, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readAsMuchAsPossible");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = buffer.getLimit() - buffer.getWritePosition();
        }
        return byteBufferChannel.readAsMuchAsPossible(buffer, i, i2);
    }

    public final ReadWriteBufferState currentState$ktor_io() {
        return getState();
    }

    @Override // io.ktor.utils.io.ByteChannel
    public void attachJob(Job job) {
        Intrinsics.checkNotNullParameter(job, "job");
        Job job2 = this.attachedJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, null, 1, null);
        }
        this.attachedJob = job;
        Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new Function1() { // from class: io.ktor.utils.io.ByteBufferChannel.attachJob.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                ByteBufferChannel.this.attachedJob = null;
                if (th == null) {
                    return;
                }
                ByteBufferChannel.this.cancel(ExceptionUtilsKt.unwrapCancellationException(th));
            }
        }, 2, null);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public int getAvailableForRead() {
        return getState().capacity._availableForRead$internal;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean isClosedForRead() {
        return getState() == ReadWriteBufferState.Terminated.INSTANCE && getClosed() != null;
    }

    public boolean isClosedForWrite() {
        return getClosed() != null;
    }

    public long getTotalBytesRead() {
        return this.totalBytesRead;
    }

    public void setTotalBytesRead$ktor_io(long j) {
        this.totalBytesRead = j;
    }

    public long getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    public void setTotalBytesWritten$ktor_io(long j) {
        this.totalBytesWritten = j;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Throwable getClosedCause() {
        ClosedElement closed = getClosed();
        if (closed != null) {
            return closed.getCause();
        }
        return null;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean close(Throwable th) {
        ClosedElement closedElement;
        if (getClosed() != null) {
            return false;
        }
        if (th == null) {
            closedElement = ClosedElement.Companion.getEmptyCause();
        } else {
            closedElement = new ClosedElement(th);
        }
        getState().capacity.flush();
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_closed$FU, this, null, closedElement)) {
            return false;
        }
        getState().capacity.flush();
        if (getState().capacity.isEmpty() || th != null) {
            tryTerminate$ktor_io();
        }
        resumeClosed(th);
        getState();
        ReadWriteBufferState.Terminated terminated = ReadWriteBufferState.Terminated.INSTANCE;
        if (th != null) {
            Job job = this.attachedJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, null, 1, null);
            }
            this.readSuspendContinuationCache.close(th);
            this.writeSuspendContinuationCache.close(th);
            return true;
        }
        this.writeSuspendContinuationCache.close((Throwable) new ClosedWriteChannelException("Byte channel was closed"));
        this.readSuspendContinuationCache.close(Boolean.valueOf(getState().capacity.flush()));
        return true;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean cancel(Throwable th) {
        if (th == null) {
            th = new CancellationException("Channel has been cancelled");
        }
        return close(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flushImpl(int i) {
        ReadWriteBufferState state;
        do {
            state = getState();
            if (state == ReadWriteBufferState.Terminated.INSTANCE) {
                return;
            } else {
                state.capacity.flush();
            }
        } while (state != getState());
        int i2 = state.capacity._availableForWrite$internal;
        if (state.capacity._availableForRead$internal >= 1) {
            resumeReadOp();
        }
        if (i2 >= i) {
            resumeWriteOp();
        }
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public void flush() {
        flushImpl(1);
    }

    private final void prepareBuffer(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        byteBuffer.limit(RangesKt.coerceAtMost(i2 + i, byteBuffer.capacity() - this.reservedSize));
        byteBuffer.position(i);
    }

    public final ByteBuffer setupStateForWrite$ktor_io() throws Throwable {
        Object obj;
        ReadWriteBufferState readWriteBufferState;
        ReadWriteBufferState.IdleEmpty idleEmpty;
        ReadWriteBufferState readWriteBufferStateStartWriting$ktor_io;
        Continuation writeOp = getWriteOp();
        if (writeOp != null) {
            throw new IllegalStateException("Write operation is already in progress: " + writeOp);
        }
        ReadWriteBufferState readWriteBufferState2 = null;
        ReadWriteBufferState.Initial initialNewBuffer = null;
        do {
            obj = this._state;
            readWriteBufferState = (ReadWriteBufferState) obj;
            if (getClosed() != null) {
                if (initialNewBuffer != null) {
                    releaseBuffer(initialNewBuffer);
                }
                ClosedElement closed = getClosed();
                Intrinsics.checkNotNull(closed);
                ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                throw new KotlinNothingValueException();
            }
            idleEmpty = ReadWriteBufferState.IdleEmpty.INSTANCE;
            if (readWriteBufferState == idleEmpty) {
                if (initialNewBuffer == null) {
                    initialNewBuffer = newBuffer();
                }
                readWriteBufferStateStartWriting$ktor_io = initialNewBuffer.startWriting$ktor_io();
            } else {
                if (readWriteBufferState == ReadWriteBufferState.Terminated.INSTANCE) {
                    if (initialNewBuffer != null) {
                        releaseBuffer(initialNewBuffer);
                    }
                    ClosedElement closed2 = getClosed();
                    Intrinsics.checkNotNull(closed2);
                    ByteBufferChannelKt.rethrowClosed(closed2.getSendException());
                    throw new KotlinNothingValueException();
                }
                readWriteBufferStateStartWriting$ktor_io = readWriteBufferState.startWriting$ktor_io();
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, readWriteBufferStateStartWriting$ktor_io));
        if (getClosed() != null) {
            restoreStateAfterWrite$ktor_io();
            tryTerminate$ktor_io();
            ClosedElement closed3 = getClosed();
            Intrinsics.checkNotNull(closed3);
            ByteBufferChannelKt.rethrowClosed(closed3.getSendException());
            throw new KotlinNothingValueException();
        }
        ByteBuffer writeBuffer = readWriteBufferStateStartWriting$ktor_io.getWriteBuffer();
        if (initialNewBuffer != null) {
            if (readWriteBufferState == null) {
                Intrinsics.throwUninitializedPropertyAccessException("old");
            } else {
                readWriteBufferState2 = readWriteBufferState;
            }
            if (readWriteBufferState2 != idleEmpty) {
                releaseBuffer(initialNewBuffer);
            }
        }
        prepareBuffer(writeBuffer, this.writePosition, readWriteBufferStateStartWriting$ktor_io.capacity._availableForWrite$internal);
        return writeBuffer;
    }

    public final boolean tryTerminate$ktor_io() {
        if (getClosed() == null || !tryReleaseBuffer(false)) {
            return false;
        }
        resumeReadOp();
        resumeWriteOp();
        return true;
    }

    private final int carryIndex(ByteBuffer byteBuffer, int i) {
        return i >= byteBuffer.capacity() - this.reservedSize ? i - (byteBuffer.capacity() - this.reservedSize) : i;
    }

    private final int writeAsMuchAsPossible(Buffer buffer) throws Throwable {
        ByteBuffer byteBuffer = setupStateForWrite$ktor_io();
        int i = 0;
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = getState().capacity;
        getTotalBytesWritten();
        try {
            ClosedElement closed = getClosed();
            if (closed != null) {
                ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                throw new KotlinNothingValueException();
            }
            while (true) {
                int iTryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(buffer.getWritePosition() - buffer.getReadPosition(), byteBuffer.remaining()));
                if (iTryWriteAtMost == 0) {
                    break;
                }
                BufferUtilsJvmKt.readFully(buffer, byteBuffer, iTryWriteAtMost);
                i += iTryWriteAtMost;
                prepareBuffer(byteBuffer, carryIndex(byteBuffer, this.writePosition + i), ringBufferCapacity._availableForWrite$internal);
            }
            bytesWritten(byteBuffer, ringBufferCapacity, i);
            if (ringBufferCapacity.isFull() || getAutoFlush()) {
                flush();
            }
            restoreStateAfterWrite$ktor_io();
            tryTerminate$ktor_io();
            return i;
        } catch (Throwable th) {
            if (ringBufferCapacity.isFull() || getAutoFlush()) {
                flush();
            }
            restoreStateAfterWrite$ktor_io();
            tryTerminate$ktor_io();
            throw th;
        }
    }

    private final int writeAsMuchAsPossible(byte[] bArr, int i, int i2) throws Throwable {
        ByteBuffer byteBuffer = setupStateForWrite$ktor_io();
        int i3 = 0;
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = getState().capacity;
        getTotalBytesWritten();
        try {
            ClosedElement closed = getClosed();
            if (closed != null) {
                ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                throw new KotlinNothingValueException();
            }
            while (true) {
                int iTryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(i2 - i3, byteBuffer.remaining()));
                if (iTryWriteAtMost == 0) {
                    bytesWritten(byteBuffer, ringBufferCapacity, i3);
                    if (ringBufferCapacity.isFull() || getAutoFlush()) {
                        flush();
                    }
                    restoreStateAfterWrite$ktor_io();
                    tryTerminate$ktor_io();
                    return i3;
                }
                if (iTryWriteAtMost <= 0) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                byteBuffer.put(bArr, i + i3, iTryWriteAtMost);
                i3 += iTryWriteAtMost;
                prepareBuffer(byteBuffer, carryIndex(byteBuffer, this.writePosition + i3), ringBufferCapacity._availableForWrite$internal);
            }
        } catch (Throwable th) {
            if (ringBufferCapacity.isFull() || getAutoFlush()) {
                flush();
            }
            restoreStateAfterWrite$ktor_io();
            tryTerminate$ktor_io();
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    public int writeAvailable(int i, Function1 block) throws Throwable {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(block, "block");
        if (i <= 0) {
            throw new IllegalArgumentException("min should be positive");
        }
        if (i <= 4088) {
            ByteBuffer byteBuffer = setupStateForWrite$ktor_io();
            int iPosition = 0;
            if (byteBuffer == null) {
                i3 = 0;
            } else {
                RingBufferCapacity ringBufferCapacity = getState().capacity;
                getTotalBytesWritten();
                try {
                    ClosedElement closed = getClosed();
                    if (closed != null) {
                        ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                        throw new KotlinNothingValueException();
                    }
                    int iTryWriteAtLeast = ringBufferCapacity.tryWriteAtLeast(i);
                    if (iTryWriteAtLeast <= 0) {
                        i2 = 0;
                    } else {
                        prepareBuffer(byteBuffer, this.writePosition, iTryWriteAtLeast);
                        int iPosition2 = byteBuffer.position();
                        int iLimit = byteBuffer.limit();
                        block.invoke(byteBuffer);
                        if (iLimit != byteBuffer.limit()) {
                            throw new IllegalStateException("Buffer limit modified");
                        }
                        iPosition = byteBuffer.position() - iPosition2;
                        if (iPosition < 0) {
                            throw new IllegalStateException("Position has been moved backward: pushback is not supported");
                        }
                        if (iPosition < 0) {
                            throw new IllegalStateException();
                        }
                        bytesWritten(byteBuffer, ringBufferCapacity, iPosition);
                        if (iPosition < iTryWriteAtLeast) {
                            ringBufferCapacity.completeRead(iTryWriteAtLeast - iPosition);
                        }
                        i2 = 1;
                    }
                    if (ringBufferCapacity.isFull() || getAutoFlush()) {
                        flush();
                    }
                    restoreStateAfterWrite$ktor_io();
                    tryTerminate$ktor_io();
                    int i4 = iPosition;
                    iPosition = i2;
                    i3 = i4;
                } catch (Throwable th) {
                    if (ringBufferCapacity.isFull() || getAutoFlush()) {
                        flush();
                    }
                    restoreStateAfterWrite$ktor_io();
                    tryTerminate$ktor_io();
                    throw th;
                }
            }
            if (iPosition == 0) {
                return -1;
            }
            return i3;
        }
        throw new IllegalArgumentException(("Min(" + i + ") shouldn't be greater than 4088").toString());
    }

    private final int readAsMuchAsPossible(Buffer buffer, int i, int i2) throws Throwable {
        int iTryReadAtMost;
        do {
            ByteBuffer byteBuffer = setupStateForRead();
            boolean z = false;
            if (byteBuffer == null) {
                iTryReadAtMost = 0;
            } else {
                RingBufferCapacity ringBufferCapacity = getState().capacity;
                try {
                    if (ringBufferCapacity._availableForRead$internal != 0) {
                        int limit = buffer.getLimit() - buffer.getWritePosition();
                        iTryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(byteBuffer.remaining(), Math.min(limit, i2)));
                        if (iTryReadAtMost > 0) {
                            if (limit < byteBuffer.remaining()) {
                                byteBuffer.limit(byteBuffer.position() + limit);
                            }
                            BufferPrimitivesJvmKt.writeFully(buffer, byteBuffer);
                            bytesRead(byteBuffer, ringBufferCapacity, iTryReadAtMost);
                            z = true;
                        }
                    } else {
                        restoreStateAfterRead();
                        tryTerminate$ktor_io();
                        iTryReadAtMost = 0;
                    }
                } finally {
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                }
            }
            i += iTryReadAtMost;
            i2 -= iTryReadAtMost;
            if (!z || buffer.getLimit() <= buffer.getWritePosition()) {
                break;
            }
        } while (getState().capacity._availableForRead$internal > 0);
        return i;
    }

    private final int readAsMuchAsPossible(byte[] bArr, int i, int i2) throws Throwable {
        ByteBuffer byteBuffer = setupStateForRead();
        int i3 = 0;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = getState().capacity;
            try {
                if (ringBufferCapacity._availableForRead$internal != 0) {
                    int iCapacity = byteBuffer.capacity() - this.reservedSize;
                    while (true) {
                        int i4 = i2 - i3;
                        if (i4 == 0) {
                            break;
                        }
                        int i5 = this.readPosition;
                        int iTryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(iCapacity - i5, i4));
                        if (iTryReadAtMost == 0) {
                            break;
                        }
                        byteBuffer.limit(i5 + iTryReadAtMost);
                        byteBuffer.position(i5);
                        byteBuffer.get(bArr, i + i3, iTryReadAtMost);
                        bytesRead(byteBuffer, ringBufferCapacity, iTryReadAtMost);
                        i3 += iTryReadAtMost;
                    }
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate$ktor_io();
            }
        }
        return i3;
    }

    static /* synthetic */ Object readAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, byte[] bArr, int i, int i2, Continuation continuation) throws Throwable {
        int asMuchAsPossible = byteBufferChannel.readAsMuchAsPossible(bArr, i, i2);
        if (asMuchAsPossible == 0 && byteBufferChannel.getClosed() != null) {
            asMuchAsPossible = byteBufferChannel.getState().capacity.flush() ? byteBufferChannel.readAsMuchAsPossible(bArr, i, i2) : -1;
        } else if (asMuchAsPossible <= 0 && i2 != 0) {
            return byteBufferChannel.readAvailableSuspend(bArr, i, i2, continuation);
        }
        return Boxing.boxInt(asMuchAsPossible);
    }

    static /* synthetic */ Object readAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, ChunkBuffer chunkBuffer, Continuation continuation) {
        int asMuchAsPossible$default = readAsMuchAsPossible$default(byteBufferChannel, chunkBuffer, 0, 0, 6, null);
        if (asMuchAsPossible$default != 0 || byteBufferChannel.getClosed() == null) {
            if (asMuchAsPossible$default <= 0 && chunkBuffer.getLimit() > chunkBuffer.getWritePosition()) {
                return byteBufferChannel.readAvailableSuspend(chunkBuffer, continuation);
            }
        } else {
            asMuchAsPossible$default = byteBufferChannel.getState().capacity.flush() ? readAsMuchAsPossible$default(byteBufferChannel, chunkBuffer, 0, 0, 6, null) : -1;
        }
        return Boxing.boxInt(asMuchAsPossible$default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object readAvailableSuspend(byte[] r6, int r7, int r8, kotlin.coroutines.Continuation r9) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel.C01861
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C01861) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L44
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r9)
            goto L75
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            int r8 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L59
        L44:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r4
            java.lang.Object r9 = r5.readSuspend(r4, r0)
            if (r9 != r1) goto L58
            return r1
        L58:
            r2 = r5
        L59:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L67
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L67:
            r9 = 0
            r0.L$0 = r9
            r0.L$1 = r9
            r0.label = r3
            java.lang.Object r9 = r2.readAvailable(r6, r7, r8, r0)
            if (r9 != r1) goto L75
            return r1
        L75:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object readAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel.AnonymousClass3
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6d
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r6 = (io.ktor.utils.io.core.internal.ChunkBuffer) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L51
        L40:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.readSuspend(r4, r0)
            if (r7 != r1) goto L50
            return r1
        L50:
            r2 = r5
        L51:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L5f
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L5f:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r7 = r2.readAvailable(r6, r0)
            if (r7 != r1) goto L6d
            return r1
        L6d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void bytesWritten(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this.writePosition = carryIndex(byteBuffer, this.writePosition + i);
        ringBufferCapacity.completeWrite(i);
        setTotalBytesWritten$ktor_io(getTotalBytesWritten() + ((long) i));
    }

    private final void bytesRead(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this.readPosition = carryIndex(byteBuffer, this.readPosition + i);
        ringBufferCapacity.completeRead(i);
        setTotalBytesRead$ktor_io(getTotalBytesRead() + ((long) i));
        resumeWriteOp();
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel byteBufferChannel, Buffer buffer, Continuation continuation) throws Throwable {
        byteBufferChannel.writeAsMuchAsPossible(buffer);
        if (buffer.getWritePosition() <= buffer.getReadPosition()) {
            return Unit.INSTANCE;
        }
        Object objWriteFullySuspend = byteBufferChannel.writeFullySuspend(buffer, continuation);
        return objWriteFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWriteFullySuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x005a -> B:24:0x005d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object writeFullySuspend(io.ktor.utils.io.core.Buffer r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel.C01913
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel.C01913) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L42
            if (r2 == r3) goto L36
            r6 = 2
            if (r2 != r6) goto L2e
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.core.Buffer r6 = (io.ktor.utils.io.core.Buffer) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5d
        L42:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
        L46:
            int r7 = r6.getWritePosition()
            int r4 = r6.getReadPosition()
            if (r7 <= r4) goto L64
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.tryWriteSuspend$ktor_io(r3, r0)
            if (r7 != r1) goto L5d
            return r1
        L5d:
            r2.getClass()
            r2.writeAsMuchAsPossible(r6)
            goto L46
        L64:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(io.ktor.utils.io.core.Buffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:195:0x011e, code lost:
    
        r2 = r27;
        r4 = r29;
        r5 = r30;
        r1 = r3;
        r10 = r6;
        r6 = r7;
        r0 = r19;
        r7 = r20;
        r3 = r28;
        r27 = r16;
        r16 = r18;
     */
    /* JADX WARN: Path cross not found for [B:122:0x029f, B:127:0x02aa], limit reached: 194 */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02af A[Catch: all -> 0x02a6, TryCatch #4 {all -> 0x02a6, blocks: (B:120:0x0299, B:122:0x029f, B:129:0x02af, B:130:0x02be, B:127:0x02aa), top: B:174:0x0299 }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02ce A[Catch: all -> 0x004a, TryCatch #7 {all -> 0x004a, blocks: (B:15:0x0044, B:39:0x00ee, B:41:0x00f4, B:132:0x02c8, B:134:0x02ce, B:136:0x02d7, B:139:0x02fd, B:143:0x030b, B:44:0x0101, B:149:0x0324, B:151:0x032a, B:155:0x0335, B:156:0x0342, B:157:0x0348, B:153:0x0330, B:159:0x034b, B:160:0x034e, B:22:0x006a), top: B:180:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x032a A[Catch: all -> 0x004a, TryCatch #7 {all -> 0x004a, blocks: (B:15:0x0044, B:39:0x00ee, B:41:0x00f4, B:132:0x02c8, B:134:0x02ce, B:136:0x02d7, B:139:0x02fd, B:143:0x030b, B:44:0x0101, B:149:0x0324, B:151:0x032a, B:155:0x0335, B:156:0x0342, B:157:0x0348, B:153:0x0330, B:159:0x034b, B:160:0x034e, B:22:0x006a), top: B:180:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0330 A[Catch: all -> 0x004a, TryCatch #7 {all -> 0x004a, blocks: (B:15:0x0044, B:39:0x00ee, B:41:0x00f4, B:132:0x02c8, B:134:0x02ce, B:136:0x02d7, B:139:0x02fd, B:143:0x030b, B:44:0x0101, B:149:0x0324, B:151:0x032a, B:155:0x0335, B:156:0x0342, B:157:0x0348, B:153:0x0330, B:159:0x034b, B:160:0x034e, B:22:0x006a), top: B:180:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0335 A[Catch: all -> 0x004a, TryCatch #7 {all -> 0x004a, blocks: (B:15:0x0044, B:39:0x00ee, B:41:0x00f4, B:132:0x02c8, B:134:0x02ce, B:136:0x02d7, B:139:0x02fd, B:143:0x030b, B:44:0x0101, B:149:0x0324, B:151:0x032a, B:155:0x0335, B:156:0x0342, B:157:0x0348, B:153:0x0330, B:159:0x034b, B:160:0x034e, B:22:0x006a), top: B:180:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x034b A[Catch: all -> 0x004a, TryCatch #7 {all -> 0x004a, blocks: (B:15:0x0044, B:39:0x00ee, B:41:0x00f4, B:132:0x02c8, B:134:0x02ce, B:136:0x02d7, B:139:0x02fd, B:143:0x030b, B:44:0x0101, B:149:0x0324, B:151:0x032a, B:155:0x0335, B:156:0x0342, B:157:0x0348, B:153:0x0330, B:159:0x034b, B:160:0x034e, B:22:0x006a), top: B:180:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x023d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0124 A[Catch: all -> 0x0288, TRY_LEAVE, TryCatch #6 {all -> 0x0288, blocks: (B:48:0x011e, B:50:0x0124), top: B:178:0x011e }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x022c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:144:0x030e -> B:39:0x00ee). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object copyDirect$ktor_io(io.ktor.utils.io.ByteBufferChannel r27, long r28, io.ktor.utils.io.internal.JoiningState r30, kotlin.coroutines.Continuation r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 860
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.copyDirect$ktor_io(io.ktor.utils.io.ByteBufferChannel, long, io.ktor.utils.io.internal.JoiningState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel byteBufferChannel, byte[] bArr, int i, int i2, Continuation continuation) throws Throwable {
        byteBufferChannel.getClass();
        while (i2 > 0) {
            int iWriteAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible(bArr, i, i2);
            if (iWriteAsMuchAsPossible == 0) {
                break;
            }
            i += iWriteAsMuchAsPossible;
            i2 -= iWriteAsMuchAsPossible;
        }
        if (i2 == 0) {
            return Unit.INSTANCE;
        }
        Object objWriteFullySuspend = byteBufferChannel.writeFullySuspend(bArr, i, i2, continuation);
        return objWriteFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWriteFullySuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0054 -> B:20:0x0057). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object writeFullySuspend(byte[] r6, int r7, int r8, kotlin.coroutines.Continuation r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel.AnonymousClass5
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5 r0 = (io.ktor.utils.io.ByteBufferChannel.AnonymousClass5) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5 r0 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            int r6 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            byte[] r8 = (byte[]) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L57
        L35:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3d:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r5
        L41:
            if (r8 <= 0) goto L63
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r9 = r2.writeAvailable(r6, r7, r8, r0)
            if (r9 != r1) goto L54
            return r1
        L54:
            r4 = r8
            r8 = r6
            r6 = r4
        L57:
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            int r7 = r7 + r9
            int r6 = r6 - r9
            r4 = r8
            r8 = r6
            r6 = r4
            goto L41
        L63:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, byte[] bArr, int i, int i2, Continuation continuation) throws Throwable {
        byteBufferChannel.getClass();
        int iWriteAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible(bArr, i, i2);
        if (iWriteAsMuchAsPossible > 0) {
            return Boxing.boxInt(iWriteAsMuchAsPossible);
        }
        return byteBufferChannel.writeSuspend(bArr, i, i2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0059 -> B:22:0x005c). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object writeSuspend(byte[] r6, int r7, int r8, kotlin.coroutines.Continuation r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel.C01921
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C01921) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspend$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L47
            if (r2 == r3) goto L34
            r6 = 2
            if (r2 != r6) goto L2c
            kotlin.ResultKt.throwOnFailure(r9)
            return r9
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            int r6 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            byte[] r8 = (byte[]) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r4 = r8
            r8 = r6
            r6 = r4
            goto L5c
        L47:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r5
        L4b:
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r9 = r2.tryWriteSuspend$ktor_io(r3, r0)
            if (r9 != r1) goto L5c
            return r1
        L5c:
            r2.getClass()
            int r9 = r2.writeAsMuchAsPossible(r6, r7, r8)
            if (r9 <= 0) goto L4b
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object write$suspendImpl(io.ktor.utils.io.ByteBufferChannel r5, int r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel.C01901
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$write$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C01901) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$write$1 r0 = new io.ktor.utils.io.ByteBufferChannel$write$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            int r5 = r0.I$0
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r7 = (io.ktor.utils.io.ByteBufferChannel) r7
            kotlin.ResultKt.throwOnFailure(r8)
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
            goto L48
        L37:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3f:
            kotlin.ResultKt.throwOnFailure(r8)
            if (r6 <= 0) goto L80
            r8 = 4088(0xff8, float:5.729E-42)
            if (r6 > r8) goto L60
        L48:
            int r8 = r5.writeAvailable(r6, r7)
            if (r8 < 0) goto L51
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L51:
            r0.L$0 = r5
            r0.L$1 = r7
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r8 = r5.awaitFreeSpaceOrDelegate(r6, r7, r0)
            if (r8 != r1) goto L48
            return r1
        L60:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "Min("
            r5.append(r7)
            r5.append(r6)
            java.lang.String r6 = ") should'nt be greater than (4088)"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        L80:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "min should be positive"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.write$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object awaitFreeSpaceOrDelegate(int r5, kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel.C01851
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C01851) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1 r0 = new io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L42
            if (r2 == r3) goto L36
            r5 = 2
            if (r2 != r5) goto L2e
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L55
        L42:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r4
            r0.L$1 = r6
            r0.I$0 = r5
            r0.label = r3
            java.lang.Object r5 = r4.writeSuspend(r5, r0)
            if (r5 != r1) goto L54
            return r1
        L54:
            r5 = r4
        L55:
            r5.getClass()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.awaitFreeSpaceOrDelegate(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readRemaining$suspendImpl(ByteBufferChannel byteBufferChannel, long j, Continuation continuation) throws Throwable {
        if (byteBufferChannel.isClosedForWrite()) {
            Throwable closedCause = byteBufferChannel.getClosedCause();
            if (closedCause != null) {
                ByteBufferChannelKt.rethrowClosed(closedCause);
                throw new KotlinNothingValueException();
            }
            return byteBufferChannel.remainingPacket(j);
        }
        return byteBufferChannel.readRemainingSuspend(j, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0071 A[Catch: all -> 0x003e, TryCatch #2 {all -> 0x003e, blocks: (B:12:0x0039, B:32:0x00a7, B:38:0x00b6, B:21:0x0061, B:23:0x0071, B:24:0x0075, B:26:0x008b, B:28:0x0091), top: B:55:0x0039, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008b A[Catch: all -> 0x003e, TryCatch #2 {all -> 0x003e, blocks: (B:12:0x0039, B:32:0x00a7, B:38:0x00b6, B:21:0x0061, B:23:0x0071, B:24:0x0075, B:26:0x008b, B:28:0x0091), top: B:55:0x0039, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b6 A[Catch: all -> 0x003e, TRY_LEAVE, TryCatch #2 {all -> 0x003e, blocks: (B:12:0x0039, B:32:0x00a7, B:38:0x00b6, B:21:0x0061, B:23:0x0071, B:24:0x0075, B:26:0x008b, B:28:0x0091), top: B:55:0x0039, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bb A[Catch: all -> 0x00c9, TRY_ENTER, TryCatch #1 {all -> 0x00c9, blocks: (B:40:0x00bb, B:42:0x00c4, B:46:0x00cc, B:47:0x00cd, B:48:0x00d0, B:12:0x0039, B:32:0x00a7, B:38:0x00b6, B:21:0x0061, B:23:0x0071, B:24:0x0075, B:26:0x008b, B:28:0x0091), top: B:54:0x0021, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00a4 -> B:32:0x00a7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00b3 -> B:37:0x00b4). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object readRemainingSuspend(long r13, kotlin.coroutines.Continuation r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readRemainingSuspend(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeReadOp() {
        Continuation continuation = (Continuation) _readOp$FU.getAndSet(this, null);
        if (continuation != null) {
            ClosedElement closed = getClosed();
            Throwable cause = closed != null ? closed.getCause() : null;
            if (cause != null) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(cause)));
            } else {
                Result.Companion companion2 = Result.Companion;
                continuation.resumeWith(Result.m2639constructorimpl(Boolean.TRUE));
            }
        }
    }

    private final void resumeWriteOp() {
        Continuation writeOp;
        ClosedElement closed;
        Object objCreateFailure;
        do {
            writeOp = getWriteOp();
            if (writeOp == null) {
                return;
            } else {
                closed = getClosed();
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_writeOp$FU, this, writeOp, null));
        if (closed == null) {
            Result.Companion companion = Result.Companion;
            objCreateFailure = Unit.INSTANCE;
        } else {
            Result.Companion companion2 = Result.Companion;
            objCreateFailure = ResultKt.createFailure(closed.getSendException());
        }
        writeOp.resumeWith(Result.m2639constructorimpl(objCreateFailure));
    }

    private final void resumeClosed(Throwable th) {
        Continuation continuation = (Continuation) _readOp$FU.getAndSet(this, null);
        if (continuation != null) {
            if (th != null) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(th)));
            } else {
                continuation.resumeWith(Result.m2639constructorimpl(Boolean.valueOf(getState().capacity._availableForRead$internal > 0)));
            }
        }
        Continuation continuation2 = (Continuation) _writeOp$FU.getAndSet(this, null);
        if (continuation2 != null) {
            Result.Companion companion2 = Result.Companion;
            if (th == null) {
                th = new ClosedWriteChannelException("Byte channel was closed");
            }
            continuation2.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(th)));
        }
    }

    private final Object readSuspend(int i, Continuation continuation) throws Throwable {
        if (getState().capacity._availableForRead$internal >= i) {
            return Boxing.boxBoolean(true);
        }
        ClosedElement closed = getClosed();
        if (closed == null) {
            if (i == 1) {
                return readSuspendImpl(1, continuation);
            }
            return readSuspendLoop(i, continuation);
        }
        Throwable cause = closed.getCause();
        if (cause != null) {
            ByteBufferChannelKt.rethrowClosed(cause);
            throw new KotlinNothingValueException();
        }
        RingBufferCapacity ringBufferCapacity = getState().capacity;
        boolean z = ringBufferCapacity.flush() && ringBufferCapacity._availableForRead$internal >= i;
        if (getReadOp() != null) {
            throw new IllegalStateException("Read operation is already in progress");
        }
        return Boxing.boxBoolean(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0092 -> B:39:0x0095). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object readSuspendLoop(int r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel.C01891
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C01891) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L38
            if (r2 != r4) goto L30
            int r6 = r0.I$0
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L95
        L30:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L38:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
        L3c:
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r2.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            int r7 = r7._availableForRead$internal
            if (r7 < r6) goto L4b
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r6
        L4b:
            io.ktor.utils.io.internal.ClosedElement r7 = r2.getClosed()
            if (r7 == 0) goto L88
            java.lang.Throwable r0 = r7.getCause()
            if (r0 != 0) goto L7b
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r2.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            boolean r0 = r7.flush()
            if (r0 == 0) goto L68
            int r7 = r7._availableForRead$internal
            if (r7 < r6) goto L68
            r3 = r4
        L68:
            kotlin.coroutines.Continuation r6 = r2.getReadOp()
            if (r6 != 0) goto L73
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        L73:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Read operation is already in progress"
            r6.<init>(r7)
            throw r6
        L7b:
            java.lang.Throwable r6 = r7.getCause()
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r6)
            kotlin.KotlinNothingValueException r6 = new kotlin.KotlinNothingValueException
            r6.<init>()
            throw r6
        L88:
            r0.L$0 = r2
            r0.I$0 = r6
            r0.label = r4
            java.lang.Object r7 = r2.readSuspendImpl(r6, r0)
            if (r7 != r1) goto L95
            return r1
        L95:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L3c
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readSuspendLoop(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Object suspensionForSize(int i, Continuation continuation) {
        while (true) {
            if (getState().capacity._availableForRead$internal >= i) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m2639constructorimpl(Boolean.TRUE));
                break;
            }
            ClosedElement closed = getClosed();
            if (closed == null) {
                while (getReadOp() == null) {
                    if (getClosed() != null || getState().capacity._availableForRead$internal >= i) {
                        break;
                    }
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _readOp$FU;
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, null, continuation)) {
                        if ((getClosed() == null && getState().capacity._availableForRead$internal < i) || !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, continuation, null)) {
                            break;
                        }
                    }
                }
                throw new IllegalStateException("Operation is already in progress");
            }
            if (closed.getCause() != null) {
                Result.Companion companion2 = Result.Companion;
                continuation.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(closed.getCause())));
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            boolean zFlush = getState().capacity.flush();
            boolean z = false;
            boolean z2 = getState().capacity._availableForRead$internal >= i;
            Result.Companion companion3 = Result.Companion;
            if (zFlush && z2) {
                z = true;
            }
            continuation.resumeWith(Result.m2639constructorimpl(Boolean.valueOf(z)));
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object readSuspendImpl(int r5, kotlin.coroutines.Continuation r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel.C01881
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C01881) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L2d
            goto L67
        L2d:
            r6 = move-exception
            goto L68
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            kotlin.ResultKt.throwOnFailure(r6)
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            int r6 = r6._availableForRead$internal
            if (r6 >= r5) goto L6d
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L61
            r0.I$0 = r5     // Catch: java.lang.Throwable -> L61
            r0.label = r3     // Catch: java.lang.Throwable -> L61
            io.ktor.utils.io.internal.CancellableReusableContinuation r6 = r4.readSuspendContinuationCache     // Catch: java.lang.Throwable -> L61
            r4.suspensionForSize(r5, r6)     // Catch: java.lang.Throwable -> L61
            kotlin.coroutines.Continuation r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)     // Catch: java.lang.Throwable -> L61
            java.lang.Object r6 = r6.completeSuspendBlock(r5)     // Catch: java.lang.Throwable -> L61
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch: java.lang.Throwable -> L61
            if (r6 != r5) goto L64
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)     // Catch: java.lang.Throwable -> L61
            goto L64
        L61:
            r6 = move-exception
            r5 = r4
            goto L68
        L64:
            if (r6 != r1) goto L67
            return r1
        L67:
            return r6
        L68:
            r0 = 0
            r5.setReadOp(r0)
            throw r6
        L6d:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readSuspendImpl(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean writeSuspendPredicate(int i) {
        ReadWriteBufferState state = getState();
        return getClosed() == null && state.capacity._availableForWrite$internal < i && state != ReadWriteBufferState.IdleEmpty.INSTANCE;
    }

    public final Object tryWriteSuspend$ktor_io(int i, Continuation continuation) throws Throwable {
        Throwable sendException;
        if (!writeSuspendPredicate(i)) {
            ClosedElement closed = getClosed();
            if (closed != null && (sendException = closed.getSendException()) != null) {
                ByteBufferChannelKt.rethrowClosed(sendException);
                throw new KotlinNothingValueException();
            }
            return Unit.INSTANCE;
        }
        this.writeSuspensionSize = i;
        if (this.attachedJob != null) {
            Object objInvoke = this.writeSuspension.invoke(continuation);
            if (objInvoke == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return objInvoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvoke : Unit.INSTANCE;
        }
        CancellableReusableContinuation cancellableReusableContinuation = this.writeSuspendContinuationCache;
        this.writeSuspension.invoke(cancellableReusableContinuation);
        Object objCompleteSuspendBlock = cancellableReusableContinuation.completeSuspendBlock(IntrinsicsKt.intercepted(continuation));
        if (objCompleteSuspendBlock == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return objCompleteSuspendBlock == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCompleteSuspendBlock : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object writeSuspend(int r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel.C01933
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel.C01933) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspend$3
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            int r6 = r0.I$0
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L3b
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
        L3b:
            boolean r7 = r2.writeSuspendPredicate(r6)
            if (r7 == 0) goto L66
            r0.L$0 = r2
            r0.I$0 = r6
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r7 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r7.<init>(r4, r3)
            r7.initCancellability()
            access$writeSuspendBlock(r2, r6, r7)
            java.lang.Object r7 = r7.getResult()
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r7 != r4) goto L63
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L63:
            if (r7 != r1) goto L3b
            return r1
        L66:
            io.ktor.utils.io.internal.ClosedElement r6 = r2.getClosed()
            if (r6 == 0) goto L7c
            java.lang.Throwable r6 = r6.getSendException()
            if (r6 != 0) goto L73
            goto L7c
        L73:
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r6)
            kotlin.KotlinNothingValueException r6 = new kotlin.KotlinNothingValueException
            r6.<init>()
            throw r6
        L7c:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeSuspendBlock(int i, CancellableContinuation cancellableContinuation) throws Throwable {
        Throwable sendException;
        while (true) {
            ClosedElement closed = getClosed();
            if (closed != null && (sendException = closed.getSendException()) != null) {
                ByteBufferChannelKt.rethrowClosed(sendException);
                throw new KotlinNothingValueException();
            }
            if (!writeSuspendPredicate(i)) {
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m2639constructorimpl(Unit.INSTANCE));
                break;
            }
            while (getWriteOp() == null) {
                if (!writeSuspendPredicate(i)) {
                    break;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _writeOp$FU;
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, null, cancellableContinuation)) {
                    if (writeSuspendPredicate(i) || !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, cancellableContinuation, null)) {
                        break;
                    }
                }
            }
            throw new IllegalStateException("Operation is already in progress");
        }
        flushImpl(i);
        if (shouldResumeReadOp()) {
            resumeReadOp();
        }
    }

    private final ReadWriteBufferState.Initial newBuffer() {
        ReadWriteBufferState.Initial initial = (ReadWriteBufferState.Initial) this.pool.borrow();
        initial.capacity.resetForWrite();
        return initial;
    }

    private final void releaseBuffer(ReadWriteBufferState.Initial initial) {
        this.pool.recycle(initial);
    }

    public String toString() {
        return "ByteBufferChannel(" + hashCode() + ", " + getState() + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: ByteBufferChannel.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void restoreStateAfterWrite$ktor_io() {
        Object obj;
        ReadWriteBufferState readWriteBufferStateStopWriting$ktor_io;
        ReadWriteBufferState.IdleNonEmpty idleNonEmpty;
        ReadWriteBufferState readWriteBufferState = null;
        do {
            obj = this._state;
            readWriteBufferStateStopWriting$ktor_io = ((ReadWriteBufferState) obj).stopWriting$ktor_io();
            if ((readWriteBufferStateStopWriting$ktor_io instanceof ReadWriteBufferState.IdleNonEmpty) && readWriteBufferStateStopWriting$ktor_io.capacity.isEmpty()) {
                readWriteBufferStateStopWriting$ktor_io = ReadWriteBufferState.IdleEmpty.INSTANCE;
                readWriteBufferState = readWriteBufferStateStopWriting$ktor_io;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, readWriteBufferStateStopWriting$ktor_io));
        if (readWriteBufferStateStopWriting$ktor_io != ReadWriteBufferState.IdleEmpty.INSTANCE || (idleNonEmpty = (ReadWriteBufferState.IdleNonEmpty) readWriteBufferState) == null) {
            return;
        }
        releaseBuffer(idleNonEmpty.getInitial());
    }

    private final ByteBuffer setupStateForRead() throws Throwable {
        Object obj;
        Throwable cause;
        ReadWriteBufferState readWriteBufferStateStartReading$ktor_io;
        Throwable cause2;
        do {
            obj = this._state;
            ReadWriteBufferState readWriteBufferState = (ReadWriteBufferState) obj;
            if (Intrinsics.areEqual(readWriteBufferState, ReadWriteBufferState.Terminated.INSTANCE) ? true : Intrinsics.areEqual(readWriteBufferState, ReadWriteBufferState.IdleEmpty.INSTANCE)) {
                ClosedElement closed = getClosed();
                if (closed == null || (cause = closed.getCause()) == null) {
                    return null;
                }
                ByteBufferChannelKt.rethrowClosed(cause);
                throw new KotlinNothingValueException();
            }
            ClosedElement closed2 = getClosed();
            if (closed2 != null && (cause2 = closed2.getCause()) != null) {
                ByteBufferChannelKt.rethrowClosed(cause2);
                throw new KotlinNothingValueException();
            }
            if (readWriteBufferState.capacity._availableForRead$internal == 0) {
                return null;
            }
            readWriteBufferStateStartReading$ktor_io = readWriteBufferState.startReading$ktor_io();
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, readWriteBufferStateStartReading$ktor_io));
        ByteBuffer readBuffer = readWriteBufferStateStartReading$ktor_io.getReadBuffer();
        prepareBuffer(readBuffer, this.readPosition, readWriteBufferStateStartReading$ktor_io.capacity._availableForRead$internal);
        return readBuffer;
    }

    private final void restoreStateAfterRead() {
        Object obj;
        ReadWriteBufferState readWriteBufferStateStopReading$ktor_io;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        ReadWriteBufferState readWriteBufferState = null;
        do {
            obj = this._state;
            ReadWriteBufferState readWriteBufferState2 = (ReadWriteBufferState) obj;
            ReadWriteBufferState.IdleNonEmpty idleNonEmpty = (ReadWriteBufferState.IdleNonEmpty) readWriteBufferState;
            if (idleNonEmpty != null) {
                idleNonEmpty.capacity.resetForWrite();
                resumeWriteOp();
                readWriteBufferState = null;
            }
            readWriteBufferStateStopReading$ktor_io = readWriteBufferState2.stopReading$ktor_io();
            if ((readWriteBufferStateStopReading$ktor_io instanceof ReadWriteBufferState.IdleNonEmpty) && getState() == readWriteBufferState2 && readWriteBufferStateStopReading$ktor_io.capacity.tryLockForRelease()) {
                readWriteBufferStateStopReading$ktor_io = ReadWriteBufferState.IdleEmpty.INSTANCE;
                readWriteBufferState = readWriteBufferStateStopReading$ktor_io;
            }
            atomicReferenceFieldUpdater = _state$FU;
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, obj, readWriteBufferStateStopReading$ktor_io));
        ReadWriteBufferState.IdleEmpty idleEmpty = ReadWriteBufferState.IdleEmpty.INSTANCE;
        if (readWriteBufferStateStopReading$ktor_io == idleEmpty) {
            ReadWriteBufferState.IdleNonEmpty idleNonEmpty2 = (ReadWriteBufferState.IdleNonEmpty) readWriteBufferState;
            if (idleNonEmpty2 != null) {
                releaseBuffer(idleNonEmpty2.getInitial());
            }
            resumeWriteOp();
            return;
        }
        if ((readWriteBufferStateStopReading$ktor_io instanceof ReadWriteBufferState.IdleNonEmpty) && readWriteBufferStateStopReading$ktor_io.capacity.isEmpty() && readWriteBufferStateStopReading$ktor_io.capacity.tryLockForRelease() && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, readWriteBufferStateStopReading$ktor_io, idleEmpty)) {
            readWriteBufferStateStopReading$ktor_io.capacity.resetForWrite();
            releaseBuffer(((ReadWriteBufferState.IdleNonEmpty) readWriteBufferStateStopReading$ktor_io).getInitial());
            resumeWriteOp();
        }
    }

    private final boolean tryReleaseBuffer(boolean z) {
        Object obj;
        ReadWriteBufferState.Terminated terminated;
        ReadWriteBufferState.Initial initial = null;
        do {
            obj = this._state;
            ReadWriteBufferState readWriteBufferState = (ReadWriteBufferState) obj;
            ClosedElement closed = getClosed();
            if (initial != null) {
                if ((closed != null ? closed.getCause() : null) == null) {
                    initial.capacity.resetForWrite();
                }
                resumeWriteOp();
                initial = null;
            }
            terminated = ReadWriteBufferState.Terminated.INSTANCE;
            if (readWriteBufferState == terminated) {
                return true;
            }
            if (readWriteBufferState != ReadWriteBufferState.IdleEmpty.INSTANCE) {
                if (closed != null && (readWriteBufferState instanceof ReadWriteBufferState.IdleNonEmpty) && (readWriteBufferState.capacity.tryLockForRelease() || closed.getCause() != null)) {
                    if (closed.getCause() != null) {
                        readWriteBufferState.capacity.forceLockForRelease();
                    }
                    initial = ((ReadWriteBufferState.IdleNonEmpty) readWriteBufferState).getInitial();
                } else {
                    if (!z || !(readWriteBufferState instanceof ReadWriteBufferState.IdleNonEmpty) || !readWriteBufferState.capacity.tryLockForRelease()) {
                        return false;
                    }
                    initial = ((ReadWriteBufferState.IdleNonEmpty) readWriteBufferState).getInitial();
                }
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, terminated));
        if (initial != null && getState() == terminated) {
            releaseBuffer(initial);
        }
        return true;
    }
}
