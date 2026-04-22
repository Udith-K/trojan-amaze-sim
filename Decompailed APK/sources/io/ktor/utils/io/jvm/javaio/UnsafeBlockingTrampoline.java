package io.ktor.utils.io.jvm.javaio;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;

/* JADX INFO: compiled from: Blocking.kt */
/* JADX INFO: loaded from: classes.dex */
final class UnsafeBlockingTrampoline extends CoroutineDispatcher {
    public static final UnsafeBlockingTrampoline INSTANCE = new UnsafeBlockingTrampoline();

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean isDispatchNeeded(CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return true;
    }

    private UnsafeBlockingTrampoline() {
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext context, Runnable block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(block, "block");
        block.run();
    }
}
