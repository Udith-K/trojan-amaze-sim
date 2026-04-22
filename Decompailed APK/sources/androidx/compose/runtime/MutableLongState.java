package androidx.compose.runtime;

/* JADX INFO: compiled from: SnapshotLongState.kt */
/* JADX INFO: loaded from: classes.dex */
public interface MutableLongState extends LongState, MutableState {
    @Override // androidx.compose.runtime.LongState
    long getLongValue();

    @Override // androidx.compose.runtime.State
    Long getValue();

    void setLongValue(long j);

    void setValue(long j);

    /* JADX INFO: renamed from: androidx.compose.runtime.MutableLongState$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotLongState.kt */
    public abstract /* synthetic */ class CC {
    }
}
