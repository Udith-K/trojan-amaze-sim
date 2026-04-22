package androidx.compose.runtime;

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: Effects.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CompositionScopedCoroutineScopeCanceller implements RememberObserver {
    private final CoroutineScope coroutineScope;

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
    }

    public CompositionScopedCoroutineScopeCanceller(CoroutineScope coroutineScope) {
        this.coroutineScope = coroutineScope;
    }

    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        CoroutineScopeKt.cancel(this.coroutineScope, new LeftCompositionCancellationException());
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        CoroutineScopeKt.cancel(this.coroutineScope, new LeftCompositionCancellationException());
    }
}
