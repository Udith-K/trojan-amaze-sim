package androidx.compose.runtime;

/* JADX INFO: compiled from: SnapshotFloatState.kt */
/* JADX INFO: loaded from: classes.dex */
public interface MutableFloatState extends FloatState, MutableState {
    @Override // androidx.compose.runtime.FloatState
    float getFloatValue();

    @Override // androidx.compose.runtime.State
    Float getValue();

    void setFloatValue(float f);

    void setValue(float f);

    /* JADX INFO: renamed from: androidx.compose.runtime.MutableFloatState$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotFloatState.kt */
    public abstract /* synthetic */ class CC {
    }
}
