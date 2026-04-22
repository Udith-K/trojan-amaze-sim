package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Actor.kt */
/* JADX INFO: loaded from: classes2.dex */
class ActorCoroutine extends ChannelCoroutine implements ActorScope {
    public ActorCoroutine(CoroutineContext coroutineContext, Channel channel, boolean z) {
        super(coroutineContext, channel, false, z);
        initParentJob((Job) coroutineContext.get(Job.Key));
    }

    @Override // kotlinx.coroutines.JobSupport
    protected void onCancelling(Throwable th) {
        Channel channel = get_channel();
        if (th != null) {
            CancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            if (CancellationException == null) {
                CancellationException = ExceptionsKt.CancellationException(DebugStringsKt.getClassSimpleName(this) + " was cancelled", th);
            }
        }
        channel.cancel(CancellationException);
    }

    @Override // kotlinx.coroutines.JobSupport
    protected boolean handleJobException(Throwable th) {
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), th);
        return true;
    }
}
