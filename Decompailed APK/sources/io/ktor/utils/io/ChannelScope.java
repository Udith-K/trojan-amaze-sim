package io.ktor.utils.io;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes.dex */
final class ChannelScope implements CoroutineScope, WriterScope {
    private final /* synthetic */ CoroutineScope $$delegate_0;
    private final ByteChannel channel;

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public ChannelScope(CoroutineScope delegate, ByteChannel channel) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(channel, "channel");
        this.channel = channel;
        this.$$delegate_0 = delegate;
    }

    @Override // io.ktor.utils.io.WriterScope
    public ByteChannel getChannel() {
        return this.channel;
    }
}
