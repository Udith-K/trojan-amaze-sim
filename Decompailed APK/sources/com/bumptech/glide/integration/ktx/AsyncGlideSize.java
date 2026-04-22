package com.bumptech.glide.integration.ktx;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;

/* JADX INFO: compiled from: Flows.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AsyncGlideSize extends ResolvableGlideSize {
    private final CompletableDeferred size;

    public AsyncGlideSize() {
        super(null);
        this.size = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    }

    public final void setSize(Size size) {
        Intrinsics.checkNotNullParameter(size, "size");
        this.size.complete(size);
    }

    public final Object getSize(Continuation continuation) {
        return this.size.await(continuation);
    }
}
