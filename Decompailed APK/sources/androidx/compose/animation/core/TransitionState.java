package androidx.compose.animation.core;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TransitionState {
    private final MutableState isRunning$delegate;

    public /* synthetic */ TransitionState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Object getCurrentState();

    public abstract Object getTargetState();

    public abstract void setCurrentState$animation_core_release(Object obj);

    public abstract void transitionConfigured$animation_core_release(Transition transition);

    public abstract void transitionRemoved$animation_core_release();

    private TransitionState() {
        this.isRunning$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
    }

    public final boolean isRunning$animation_core_release() {
        return ((Boolean) this.isRunning$delegate.getValue()).booleanValue();
    }

    public final void setRunning$animation_core_release(boolean z) {
        this.isRunning$delegate.setValue(Boolean.valueOf(z));
    }
}
