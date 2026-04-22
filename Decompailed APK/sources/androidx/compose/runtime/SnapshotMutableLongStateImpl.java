package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.StateRecord;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnapshotLongState.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SnapshotMutableLongStateImpl extends StateObjectImpl implements MutableLongState, SnapshotMutableState {
    private LongStateStateRecord next;

    @Override // androidx.compose.runtime.MutableLongState, androidx.compose.runtime.State
    public /* synthetic */ Long getValue() {
        return Long.valueOf(getLongValue());
    }

    @Override // androidx.compose.runtime.State
    public /* bridge */ /* synthetic */ Object getValue() {
        return getValue();
    }

    @Override // androidx.compose.runtime.MutableLongState
    public /* synthetic */ void setValue(long j) {
        setLongValue(j);
    }

    @Override // androidx.compose.runtime.MutableState
    public /* bridge */ /* synthetic */ void setValue(Object obj) {
        setValue(((Number) obj).longValue());
    }

    public SnapshotMutableLongStateImpl(long j) {
        LongStateStateRecord longStateStateRecord = new LongStateStateRecord(j);
        if (Snapshot.Companion.isInSnapshot()) {
            LongStateStateRecord longStateStateRecord2 = new LongStateStateRecord(j);
            longStateStateRecord2.setSnapshotId$runtime_release(1);
            longStateStateRecord.setNext$runtime_release(longStateStateRecord2);
        }
        this.next = longStateStateRecord;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.next;
    }

    @Override // androidx.compose.runtime.MutableLongState, androidx.compose.runtime.LongState
    public long getLongValue() {
        return ((LongStateStateRecord) SnapshotKt.readable(this.next, this)).getValue();
    }

    @Override // androidx.compose.runtime.MutableLongState
    public void setLongValue(long j) {
        Snapshot current;
        LongStateStateRecord longStateStateRecord = (LongStateStateRecord) SnapshotKt.current(this.next);
        if (longStateStateRecord.getValue() != j) {
            LongStateStateRecord longStateStateRecord2 = this.next;
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.Companion.getCurrent();
                ((LongStateStateRecord) SnapshotKt.overwritableRecord(longStateStateRecord2, this, current, longStateStateRecord)).setValue(j);
                Unit unit = Unit.INSTANCE;
            }
            SnapshotKt.notifyWrite(current, this);
        }
    }

    @Override // androidx.compose.runtime.snapshots.SnapshotMutableState
    public SnapshotMutationPolicy getPolicy() {
        return SnapshotStateKt.structuralEqualityPolicy();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord stateRecord) {
        Intrinsics.checkNotNull(stateRecord, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableLongStateImpl.LongStateStateRecord");
        this.next = (LongStateStateRecord) stateRecord;
    }

    @Override // androidx.compose.runtime.snapshots.StateObjectImpl, androidx.compose.runtime.snapshots.StateObject
    public StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        Intrinsics.checkNotNull(stateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableLongStateImpl.LongStateStateRecord");
        Intrinsics.checkNotNull(stateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableLongStateImpl.LongStateStateRecord");
        if (((LongStateStateRecord) stateRecord2).getValue() == ((LongStateStateRecord) stateRecord3).getValue()) {
            return stateRecord2;
        }
        return null;
    }

    public String toString() {
        return "MutableLongState(value=" + ((LongStateStateRecord) SnapshotKt.current(this.next)).getValue() + ")@" + hashCode();
    }

    /* JADX INFO: compiled from: SnapshotLongState.kt */
    private static final class LongStateStateRecord extends StateRecord {
        private long value;

        public final long getValue() {
            return this.value;
        }

        public final void setValue(long j) {
            this.value = j;
        }

        public LongStateStateRecord(long j) {
            this.value = j;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord stateRecord) {
            Intrinsics.checkNotNull(stateRecord, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableLongStateImpl.LongStateStateRecord");
            this.value = ((LongStateStateRecord) stateRecord).value;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new LongStateStateRecord(this.value);
        }
    }
}
