package androidx.compose.runtime;

/* JADX INFO: compiled from: SnapshotIntState.kt */
/* JADX INFO: loaded from: classes.dex */
public interface MutableIntState extends IntState, MutableState {
    @Override // androidx.compose.runtime.IntState
    int getIntValue();

    @Override // androidx.compose.runtime.State
    Integer getValue();

    void setIntValue(int i);

    void setValue(int i);

    /* JADX INFO: renamed from: androidx.compose.runtime.MutableIntState$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotIntState.kt */
    public abstract /* synthetic */ class CC {
    }
}
