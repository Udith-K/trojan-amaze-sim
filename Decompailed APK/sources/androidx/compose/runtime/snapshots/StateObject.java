package androidx.compose.runtime.snapshots;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
public interface StateObject {
    StateRecord getFirstStateRecord();

    StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3);

    void prependStateRecord(StateRecord stateRecord);

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.StateObject$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Snapshot.kt */
    public abstract /* synthetic */ class CC {
        public static StateRecord $default$mergeRecords(StateObject stateObject, StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
            return null;
        }
    }
}
