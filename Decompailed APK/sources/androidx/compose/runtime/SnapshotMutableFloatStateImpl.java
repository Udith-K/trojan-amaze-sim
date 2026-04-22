package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.StateRecord;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnapshotFloatState.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SnapshotMutableFloatStateImpl extends StateObjectImpl implements MutableFloatState, SnapshotMutableState {
    private FloatStateStateRecord next;

    @Override // androidx.compose.runtime.MutableFloatState, androidx.compose.runtime.State
    public /* synthetic */ Float getValue() {
        return Float.valueOf(getFloatValue());
    }

    @Override // androidx.compose.runtime.State
    public /* bridge */ /* synthetic */ Object getValue() {
        return getValue();
    }

    @Override // androidx.compose.runtime.MutableFloatState
    public /* synthetic */ void setValue(float f) {
        setFloatValue(f);
    }

    @Override // androidx.compose.runtime.MutableState
    public /* bridge */ /* synthetic */ void setValue(Object obj) {
        setValue(((Number) obj).floatValue());
    }

    public SnapshotMutableFloatStateImpl(float f) {
        FloatStateStateRecord floatStateStateRecord = new FloatStateStateRecord(f);
        if (Snapshot.Companion.isInSnapshot()) {
            FloatStateStateRecord floatStateStateRecord2 = new FloatStateStateRecord(f);
            floatStateStateRecord2.setSnapshotId$runtime_release(1);
            floatStateStateRecord.setNext$runtime_release(floatStateStateRecord2);
        }
        this.next = floatStateStateRecord;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.next;
    }

    @Override // androidx.compose.runtime.MutableFloatState, androidx.compose.runtime.FloatState
    public float getFloatValue() {
        return ((FloatStateStateRecord) SnapshotKt.readable(this.next, this)).getValue();
    }

    @Override // androidx.compose.runtime.MutableFloatState
    public void setFloatValue(float f) {
        Snapshot current;
        FloatStateStateRecord floatStateStateRecord = (FloatStateStateRecord) SnapshotKt.current(this.next);
        if (floatStateStateRecord.getValue() == f) {
            return;
        }
        FloatStateStateRecord floatStateStateRecord2 = this.next;
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.Companion.getCurrent();
            ((FloatStateStateRecord) SnapshotKt.overwritableRecord(floatStateStateRecord2, this, current, floatStateStateRecord)).setValue(f);
            Unit unit = Unit.INSTANCE;
        }
        SnapshotKt.notifyWrite(current, this);
    }

    @Override // androidx.compose.runtime.snapshots.SnapshotMutableState
    public SnapshotMutationPolicy getPolicy() {
        return SnapshotStateKt.structuralEqualityPolicy();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord stateRecord) {
        Intrinsics.checkNotNull(stateRecord, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableFloatStateImpl.FloatStateStateRecord");
        this.next = (FloatStateStateRecord) stateRecord;
    }

    @Override // androidx.compose.runtime.snapshots.StateObjectImpl, androidx.compose.runtime.snapshots.StateObject
    public StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        Intrinsics.checkNotNull(stateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableFloatStateImpl.FloatStateStateRecord");
        Intrinsics.checkNotNull(stateRecord3, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableFloatStateImpl.FloatStateStateRecord");
        if (((FloatStateStateRecord) stateRecord2).getValue() == ((FloatStateStateRecord) stateRecord3).getValue()) {
            return stateRecord2;
        }
        return null;
    }

    public String toString() {
        return "MutableFloatState(value=" + ((FloatStateStateRecord) SnapshotKt.current(this.next)).getValue() + ")@" + hashCode();
    }

    /* JADX INFO: compiled from: SnapshotFloatState.kt */
    private static final class FloatStateStateRecord extends StateRecord {
        private float value;

        public final float getValue() {
            return this.value;
        }

        public final void setValue(float f) {
            this.value = f;
        }

        public FloatStateStateRecord(float f) {
            this.value = f;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord stateRecord) {
            Intrinsics.checkNotNull(stateRecord, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableFloatStateImpl.FloatStateStateRecord");
            this.value = ((FloatStateStateRecord) stateRecord).value;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new FloatStateStateRecord(this.value);
        }
    }
}
