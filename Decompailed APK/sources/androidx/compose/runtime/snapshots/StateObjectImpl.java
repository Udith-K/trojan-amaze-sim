package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.AtomicInt;
import androidx.compose.runtime.snapshots.StateObject;

/* JADX INFO: compiled from: StateObjectImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StateObjectImpl implements StateObject {
    private final AtomicInt readerKind = new AtomicInt(0);

    @Override // androidx.compose.runtime.snapshots.StateObject
    public /* synthetic */ StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        return StateObject.CC.$default$mergeRecords(this, stateRecord, stateRecord2, stateRecord3);
    }

    /* JADX INFO: renamed from: recordReadIn-h_f27i8$runtime_release, reason: not valid java name */
    public final void m1065recordReadInh_f27i8$runtime_release(int i) {
        int iM1059constructorimpl;
        do {
            iM1059constructorimpl = ReaderKind.m1059constructorimpl(this.readerKind.get());
            if ((iM1059constructorimpl & i) != 0) {
                return;
            }
        } while (!this.readerKind.compareAndSet(iM1059constructorimpl, ReaderKind.m1059constructorimpl(iM1059constructorimpl | i)));
    }

    /* JADX INFO: renamed from: isReadIn-h_f27i8$runtime_release, reason: not valid java name */
    public final boolean m1064isReadInh_f27i8$runtime_release(int i) {
        return (i & ReaderKind.m1059constructorimpl(this.readerKind.get())) != 0;
    }
}
