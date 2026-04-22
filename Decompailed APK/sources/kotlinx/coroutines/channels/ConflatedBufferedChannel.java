package kotlinx.coroutines.channels;

import kotlin.ExceptionsKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.UndeliveredElementException;

/* JADX INFO: compiled from: ConflatedBufferedChannel.kt */
/* JADX INFO: loaded from: classes2.dex */
public class ConflatedBufferedChannel extends BufferedChannel {
    private final int capacity;
    private final BufferOverflow onBufferOverflow;

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public Object send(Object obj, Continuation continuation) {
        return send$suspendImpl(this, obj, continuation);
    }

    public ConflatedBufferedChannel(int i, BufferOverflow bufferOverflow, Function1 function1) {
        super(i, function1);
        this.capacity = i;
        this.onBufferOverflow = bufferOverflow;
        if (bufferOverflow == BufferOverflow.SUSPEND) {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + Reflection.getOrCreateKotlinClass(BufferedChannel.class).getSimpleName() + " instead").toString());
        }
        if (i >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but " + i + " was specified").toString());
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    protected boolean isConflatedDropOldest() {
        return this.onBufferOverflow == BufferOverflow.DROP_OLDEST;
    }

    static /* synthetic */ Object send$suspendImpl(ConflatedBufferedChannel conflatedBufferedChannel, Object obj, Continuation continuation) throws Throwable {
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        Object objM2841trySendImplMj0NB7M = conflatedBufferedChannel.m2841trySendImplMj0NB7M(obj, true);
        if (objM2841trySendImplMj0NB7M instanceof ChannelResult.Closed) {
            ChannelResult.m2829exceptionOrNullimpl(objM2841trySendImplMj0NB7M);
            Function1 function1 = conflatedBufferedChannel.onUndeliveredElement;
            if (function1 != null && (undeliveredElementExceptionCallUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, obj, null, 2, null)) != null) {
                ExceptionsKt.addSuppressed(undeliveredElementExceptionCallUndeliveredElementCatchingException$default, conflatedBufferedChannel.getSendException());
                throw undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
            }
            throw conflatedBufferedChannel.getSendException();
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    public Object mo2825trySendJP2dKIU(Object obj) {
        return m2841trySendImplMj0NB7M(obj, false);
    }

    /* JADX INFO: renamed from: trySendImpl-Mj0NB7M, reason: not valid java name */
    private final Object m2841trySendImplMj0NB7M(Object obj, boolean z) {
        return this.onBufferOverflow == BufferOverflow.DROP_LATEST ? m2839trySendDropLatestMj0NB7M(obj, z) : m2840trySendDropOldestJP2dKIU(obj);
    }

    /* JADX INFO: renamed from: trySendDropLatest-Mj0NB7M, reason: not valid java name */
    private final Object m2839trySendDropLatestMj0NB7M(Object obj, boolean z) {
        Function1 function1;
        UndeliveredElementException undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        Object objMo2825trySendJP2dKIU = super.mo2825trySendJP2dKIU(obj);
        if (ChannelResult.m2833isSuccessimpl(objMo2825trySendJP2dKIU) || ChannelResult.m2832isClosedimpl(objMo2825trySendJP2dKIU)) {
            return objMo2825trySendJP2dKIU;
        }
        if (z && (function1 = this.onUndeliveredElement) != null && (undeliveredElementExceptionCallUndeliveredElementCatchingException$default = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, obj, null, 2, null)) != null) {
            throw undeliveredElementExceptionCallUndeliveredElementCatchingException$default;
        }
        return ChannelResult.Companion.m2838successJP2dKIU(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: trySendDropOldest-JP2dKIU, reason: not valid java name */
    private final Object m2840trySendDropOldestJP2dKIU(Object obj) {
        ChannelSegment channelSegment;
        Object obj2 = BufferedChannelKt.BUFFERED;
        ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.sendSegment$FU.get(this);
        while (true) {
            long andIncrement = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(this);
            long j = andIncrement & 1152921504606846975L;
            boolean zIsClosedForSend0 = isClosedForSend0(andIncrement);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j2 = j / ((long) i);
            int i2 = (int) (j % ((long) i));
            if (channelSegment2.id != j2) {
                ChannelSegment channelSegmentFindSegmentSend = findSegmentSend(j2, channelSegment2);
                if (channelSegmentFindSegmentSend != null) {
                    channelSegment = channelSegmentFindSegmentSend;
                } else if (zIsClosedForSend0) {
                    return ChannelResult.Companion.m2836closedJP2dKIU(getSendException());
                }
            } else {
                channelSegment = channelSegment2;
            }
            int iUpdateCellSend = updateCellSend(channelSegment, i2, obj, j, obj2, zIsClosedForSend0);
            if (iUpdateCellSend == 0) {
                channelSegment.cleanPrev();
                return ChannelResult.Companion.m2838successJP2dKIU(Unit.INSTANCE);
            }
            if (iUpdateCellSend == 1) {
                return ChannelResult.Companion.m2838successJP2dKIU(Unit.INSTANCE);
            }
            if (iUpdateCellSend == 2) {
                if (zIsClosedForSend0) {
                    channelSegment.onSlotCleaned();
                    return ChannelResult.Companion.m2836closedJP2dKIU(getSendException());
                }
                Waiter waiter = obj2 instanceof Waiter ? (Waiter) obj2 : null;
                if (waiter != null) {
                    prepareSenderForSuspension(waiter, channelSegment, i2);
                }
                dropFirstElementUntilTheSpecifiedCellIsInTheBuffer((channelSegment.id * ((long) i)) + ((long) i2));
                return ChannelResult.Companion.m2838successJP2dKIU(Unit.INSTANCE);
            }
            if (iUpdateCellSend == 3) {
                throw new IllegalStateException("unexpected");
            }
            if (iUpdateCellSend == 4) {
                if (j < getReceiversCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
                return ChannelResult.Companion.m2836closedJP2dKIU(getSendException());
            }
            if (iUpdateCellSend == 5) {
                channelSegment.cleanPrev();
            }
            channelSegment2 = channelSegment;
        }
    }
}
