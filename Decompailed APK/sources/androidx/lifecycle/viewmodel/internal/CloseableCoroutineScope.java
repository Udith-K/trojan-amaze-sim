package androidx.lifecycle.viewmodel.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt__JobKt;

/* JADX INFO: compiled from: CloseableCoroutineScope.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CloseableCoroutineScope implements AutoCloseable, CoroutineScope {
    private final CoroutineContext coroutineContext;

    public CloseableCoroutineScope(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        this.coroutineContext = coroutineContext;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        JobKt__JobKt.cancel$default(getCoroutineContext(), null, 1, null);
    }
}
