package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import java.io.Closeable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SavedStateHandleController.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleController implements LifecycleEventObserver, Closeable {
    private final SavedStateHandle handle;
    private boolean isAttached;
    private final String key;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public SavedStateHandleController(String key, SavedStateHandle handle) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(handle, "handle");
        this.key = key;
        this.handle = handle;
    }

    public final SavedStateHandle getHandle() {
        return this.handle;
    }

    public final boolean isAttached() {
        return this.isAttached;
    }

    public final void attachToLifecycle(SavedStateRegistry registry, Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(registry, "registry");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        if (this.isAttached) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.isAttached = true;
        lifecycle.addObserver(this);
        registry.registerSavedStateProvider(this.key, this.handle.savedStateProvider());
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.isAttached = false;
            source.getLifecycle().removeObserver(this);
        }
    }
}
