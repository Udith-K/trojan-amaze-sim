package io.ktor.client.content;

import io.ktor.client.utils.ByteChannelUtilsKt;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.ByteChannelCtorKt;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ObservableContent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ObservableContent extends OutgoingContent.ReadChannelContent {
    private final CoroutineContext callContext;
    private final ByteReadChannel content;
    private final OutgoingContent delegate;
    private final Function3 listener;

    public ObservableContent(OutgoingContent delegate, CoroutineContext callContext, Function3 listener) {
        ByteReadChannel from;
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(callContext, "callContext");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.delegate = delegate;
        this.callContext = callContext;
        this.listener = listener;
        if (delegate instanceof OutgoingContent.ByteArrayContent) {
            from = ByteChannelCtorKt.ByteReadChannel(((OutgoingContent.ByteArrayContent) delegate).bytes());
        } else if (delegate instanceof OutgoingContent.NoContent) {
            from = ByteReadChannel.Companion.getEmpty();
        } else {
            if (!(delegate instanceof OutgoingContent.ReadChannelContent)) {
                throw new NoWhenBranchMatchedException();
            }
            from = ((OutgoingContent.ReadChannelContent) delegate).readFrom();
        }
        this.content = from;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.delegate.getContentType();
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return this.delegate.getContentLength();
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Headers getHeaders() {
        return this.delegate.getHeaders();
    }

    @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
    public ByteReadChannel readFrom() {
        return ByteChannelUtilsKt.observable(this.content, this.callContext, getContentLength(), this.listener);
    }
}
