package androidx.compose.runtime.snapshots;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StateRecord {
    private StateRecord next;
    private int snapshotId = SnapshotKt.currentSnapshot().getId();

    public abstract void assign(StateRecord stateRecord);

    public abstract StateRecord create();

    public final int getSnapshotId$runtime_release() {
        return this.snapshotId;
    }

    public final void setSnapshotId$runtime_release(int i) {
        this.snapshotId = i;
    }

    public final StateRecord getNext$runtime_release() {
        return this.next;
    }

    public final void setNext$runtime_release(StateRecord stateRecord) {
        this.next = stateRecord;
    }
}
