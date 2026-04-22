package io.ktor.client.engine.okhttp;

import io.ktor.websocket.Frame;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

/* JADX INFO: compiled from: OkHttpWebsocketSession.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UnsupportedFrameTypeException extends IllegalArgumentException implements CopyableThrowable {
    private final Frame frame;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnsupportedFrameTypeException(Frame frame) {
        super("Unsupported frame type: " + frame);
        Intrinsics.checkNotNullParameter(frame, "frame");
        this.frame = frame;
    }

    @Override // kotlinx.coroutines.CopyableThrowable
    public UnsupportedFrameTypeException createCopy() {
        UnsupportedFrameTypeException unsupportedFrameTypeException = new UnsupportedFrameTypeException(this.frame);
        unsupportedFrameTypeException.initCause(this);
        return unsupportedFrameTypeException;
    }
}
