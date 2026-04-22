package io.ktor.utils.io;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ChildHandle;
import kotlinx.coroutines.ChildJob;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes.dex */
final class ChannelJob implements Job, WriterJob {
    private final ByteChannel channel;
    private final Job delegate;

    @Override // kotlinx.coroutines.Job
    public ChildHandle attachChild(ChildJob child) {
        Intrinsics.checkNotNullParameter(child, "child");
        return this.delegate.attachChild(child);
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cancellationException) {
        this.delegate.cancel(cancellationException);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public Object fold(Object obj, Function2 operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        return this.delegate.fold(obj, operation);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element get(CoroutineContext.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.delegate.get(key);
    }

    @Override // kotlinx.coroutines.Job
    public CancellationException getCancellationException() {
        return this.delegate.getCancellationException();
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key getKey() {
        return this.delegate.getKey();
    }

    @Override // kotlinx.coroutines.Job
    public Job getParent() {
        return this.delegate.getParent();
    }

    @Override // kotlinx.coroutines.Job
    public DisposableHandle invokeOnCompletion(Function1 handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        return this.delegate.invokeOnCompletion(handler);
    }

    @Override // kotlinx.coroutines.Job
    public DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1 handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        return this.delegate.invokeOnCompletion(z, z2, handler);
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        return this.delegate.isActive();
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCancelled() {
        return this.delegate.isCancelled();
    }

    @Override // kotlinx.coroutines.Job
    public Object join(Continuation continuation) {
        return this.delegate.join(continuation);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.delegate.minusKey(key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return this.delegate.plus(context);
    }

    @Override // kotlinx.coroutines.Job
    public boolean start() {
        return this.delegate.start();
    }

    public ChannelJob(Job delegate, ByteChannel channel) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(channel, "channel");
        this.delegate = delegate;
        this.channel = channel;
    }

    @Override // io.ktor.utils.io.WriterJob
    public ByteChannel getChannel() {
        return this.channel;
    }

    public String toString() {
        return "ChannelJob[" + this.delegate + ']';
    }
}
