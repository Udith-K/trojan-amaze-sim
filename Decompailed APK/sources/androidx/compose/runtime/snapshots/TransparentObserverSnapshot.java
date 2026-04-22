package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.ActualJvm_jvmKt;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TransparentObserverSnapshot extends Snapshot {
    private final boolean mergeParentObservers;
    private final boolean ownsPreviousSnapshot;
    private final Snapshot previousSnapshot;
    private Function1 readObserver;
    private final Snapshot root;
    private final long threadId;
    private final Function1 writeObserver;

    public TransparentObserverSnapshot(Snapshot snapshot, Function1 function1, boolean z, boolean z2) {
        Function1 readObserver;
        super(0, SnapshotIdSet.Companion.getEMPTY(), null);
        this.previousSnapshot = snapshot;
        this.mergeParentObservers = z;
        this.ownsPreviousSnapshot = z2;
        this.readObserver = SnapshotKt.mergedReadObserver(function1, (snapshot == null || (readObserver = snapshot.getReadObserver()) == null) ? ((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).getReadObserver() : readObserver, z);
        this.threadId = ActualJvm_jvmKt.currentThreadId();
        this.root = this;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime_release, reason: merged with bridge method [inline-methods] */
    public Function1 getReadObserver() {
        return this.readObserver;
    }

    public void setReadObserver(Function1 function1) {
        this.readObserver = function1;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1 getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    public final long getThreadId$runtime_release() {
        return this.threadId;
    }

    private final Snapshot getCurrentSnapshot() {
        Snapshot snapshot = this.previousSnapshot;
        return snapshot == null ? (Snapshot) SnapshotKt.currentGlobalSnapshot.get() : snapshot;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        Snapshot snapshot;
        setDisposed$runtime_release(true);
        if (!this.ownsPreviousSnapshot || (snapshot = this.previousSnapshot) == null) {
            return;
        }
        snapshot.dispose();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public int getId() {
        return getCurrentSnapshot().getId();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public SnapshotIdSet getInvalid$runtime_release() {
        return getCurrentSnapshot().getInvalid$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return getCurrentSnapshot().getReadOnly();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime_release */
    public void mo1058recordModified$runtime_release(StateObject stateObject) {
        getCurrentSnapshot().mo1058recordModified$runtime_release(stateObject);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1 function1) {
        Function1 function1MergedReadObserver$default = SnapshotKt.mergedReadObserver$default(function1, getReadObserver(), false, 4, null);
        if (!this.mergeParentObservers) {
            return SnapshotKt.createTransparentSnapshotWithNoParentReadObserver(getCurrentSnapshot().takeNestedSnapshot(null), function1MergedReadObserver$default, true);
        }
        return getCurrentSnapshot().takeNestedSnapshot(function1MergedReadObserver$default);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        getCurrentSnapshot().notifyObjectsInitialized$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime_release, reason: merged with bridge method [inline-methods] */
    public Void mo1055nestedActivated$runtime_release(Snapshot snapshot) {
        SnapshotStateMapKt.unsupported();
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime_release, reason: merged with bridge method [inline-methods] */
    public Void mo1056nestedDeactivated$runtime_release(Snapshot snapshot) {
        SnapshotStateMapKt.unsupported();
        throw new KotlinNothingValueException();
    }
}
