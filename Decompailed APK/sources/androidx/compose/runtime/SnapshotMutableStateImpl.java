package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.StateRecord;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnapshotState.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SnapshotMutableStateImpl extends StateObjectImpl implements SnapshotMutableState {
    private StateStateRecord next;
    private final SnapshotMutationPolicy policy;

    @Override // androidx.compose.runtime.snapshots.SnapshotMutableState
    public SnapshotMutationPolicy getPolicy() {
        return this.policy;
    }

    public SnapshotMutableStateImpl(Object obj, SnapshotMutationPolicy snapshotMutationPolicy) {
        this.policy = snapshotMutationPolicy;
        StateStateRecord stateStateRecord = new StateStateRecord(obj);
        if (Snapshot.Companion.isInSnapshot()) {
            StateStateRecord stateStateRecord2 = new StateStateRecord(obj);
            stateStateRecord2.setSnapshotId$runtime_release(1);
            stateStateRecord.setNext$runtime_release(stateStateRecord2);
        }
        this.next = stateStateRecord;
    }

    @Override // androidx.compose.runtime.MutableState, androidx.compose.runtime.State
    public Object getValue() {
        return ((StateStateRecord) SnapshotKt.readable(this.next, this)).getValue();
    }

    @Override // androidx.compose.runtime.MutableState
    public void setValue(Object obj) {
        Snapshot current;
        StateStateRecord stateStateRecord = (StateStateRecord) SnapshotKt.current(this.next);
        if (getPolicy().equivalent(stateStateRecord.getValue(), obj)) {
            return;
        }
        StateStateRecord stateStateRecord2 = this.next;
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.Companion.getCurrent();
            ((StateStateRecord) SnapshotKt.overwritableRecord(stateStateRecord2, this, current, stateStateRecord)).setValue(obj);
            Unit unit = Unit.INSTANCE;
        }
        SnapshotKt.notifyWrite(current, this);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.next;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord stateRecord) {
        Intrinsics.checkNotNull(stateRecord, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl>");
        this.next = (StateStateRecord) stateRecord;
    }

    @Override // androidx.compose.runtime.snapshots.StateObjectImpl, androidx.compose.runtime.snapshots.StateObject
    public StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        Intrinsics.checkNotNull(stateRecord, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl>");
        StateStateRecord stateStateRecord = (StateStateRecord) stateRecord;
        Intrinsics.checkNotNull(stateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl>");
        StateStateRecord stateStateRecord2 = (StateStateRecord) stateRecord2;
        Intrinsics.checkNotNull(stateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl>");
        StateStateRecord stateStateRecord3 = (StateStateRecord) stateRecord3;
        if (getPolicy().equivalent(stateStateRecord2.getValue(), stateStateRecord3.getValue())) {
            return stateRecord2;
        }
        Object objMerge = getPolicy().merge(stateStateRecord.getValue(), stateStateRecord2.getValue(), stateStateRecord3.getValue());
        if (objMerge == null) {
            return null;
        }
        StateRecord stateRecordCreate = stateStateRecord3.create();
        Intrinsics.checkNotNull(stateRecordCreate, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl.mergeRecords$lambda$4>");
        ((StateStateRecord) stateRecordCreate).setValue(objMerge);
        return stateRecordCreate;
    }

    public String toString() {
        return "MutableState(value=" + ((StateStateRecord) SnapshotKt.current(this.next)).getValue() + ")@" + hashCode();
    }

    /* JADX INFO: compiled from: SnapshotState.kt */
    private static final class StateStateRecord extends StateRecord {
        private Object value;

        public StateStateRecord(Object obj) {
            this.value = obj;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord stateRecord) {
            Intrinsics.checkNotNull(stateRecord, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord>");
            this.value = ((StateStateRecord) stateRecord).value;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new StateStateRecord(this.value);
        }

        public final Object getValue() {
            return this.value;
        }

        public final void setValue(Object obj) {
            this.value = obj;
        }
    }
}
