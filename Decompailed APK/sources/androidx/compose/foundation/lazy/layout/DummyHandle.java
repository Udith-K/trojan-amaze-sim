package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;

/* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
/* JADX INFO: loaded from: classes.dex */
final class DummyHandle implements LazyLayoutPrefetchState.PrefetchHandle {
    public static final DummyHandle INSTANCE = new DummyHandle();

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
    public void cancel() {
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
    public void markAsUrgent() {
    }

    private DummyHandle() {
    }
}
