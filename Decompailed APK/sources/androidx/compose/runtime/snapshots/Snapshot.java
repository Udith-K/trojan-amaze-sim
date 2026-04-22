package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.snapshots.Snapshot;
import java.util.Collection;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Snapshot {
    private boolean disposed;
    private int id;
    private SnapshotIdSet invalid;
    private int pinningTrackingHandle;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ Snapshot(int i, SnapshotIdSet snapshotIdSet, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, snapshotIdSet);
    }

    public abstract Function1 getReadObserver();

    public abstract boolean getReadOnly();

    public int getWriteCount$runtime_release() {
        return 0;
    }

    public abstract Function1 getWriteObserver$runtime_release();

    /* JADX INFO: renamed from: nestedActivated$runtime_release */
    public abstract void mo1055nestedActivated$runtime_release(Snapshot snapshot);

    /* JADX INFO: renamed from: nestedDeactivated$runtime_release */
    public abstract void mo1056nestedDeactivated$runtime_release(Snapshot snapshot);

    public abstract void notifyObjectsInitialized$runtime_release();

    /* JADX INFO: renamed from: recordModified$runtime_release */
    public abstract void mo1058recordModified$runtime_release(StateObject stateObject);

    public abstract Snapshot takeNestedSnapshot(Function1 function1);

    private Snapshot(int i, SnapshotIdSet snapshotIdSet) {
        this.invalid = snapshotIdSet;
        this.id = i;
        this.pinningTrackingHandle = i != 0 ? SnapshotKt.trackPinning(i, getInvalid$runtime_release()) : -1;
    }

    public SnapshotIdSet getInvalid$runtime_release() {
        return this.invalid;
    }

    public void setInvalid$runtime_release(SnapshotIdSet snapshotIdSet) {
        this.invalid = snapshotIdSet;
    }

    public int getId() {
        return this.id;
    }

    public void setId$runtime_release(int i) {
        this.id = i;
    }

    public void setWriteCount$runtime_release(int i) {
        throw new IllegalStateException("Updating write count is not supported for this snapshot");
    }

    public void dispose() {
        this.disposed = true;
        synchronized (SnapshotKt.getLock()) {
            releasePinnedSnapshotLocked$runtime_release();
            Unit unit = Unit.INSTANCE;
        }
    }

    public Snapshot makeCurrent() {
        Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
        SnapshotKt.threadSnapshot.set(this);
        return snapshot;
    }

    public void restoreCurrent(Snapshot snapshot) {
        SnapshotKt.threadSnapshot.set(snapshot);
    }

    public final boolean getDisposed$runtime_release() {
        return this.disposed;
    }

    public final void setDisposed$runtime_release(boolean z) {
        this.disposed = z;
    }

    public void closeLocked$runtime_release() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId());
    }

    public void releasePinnedSnapshotsForCloseLocked$runtime_release() {
        releasePinnedSnapshotLocked$runtime_release();
    }

    public final void validateNotDisposed$runtime_release() {
        if (this.disposed) {
            PreconditionsKt.throwIllegalArgumentException("Cannot use a disposed snapshot");
        }
    }

    public final void releasePinnedSnapshotLocked$runtime_release() {
        int i = this.pinningTrackingHandle;
        if (i >= 0) {
            SnapshotKt.releasePinningLocked(i);
            this.pinningTrackingHandle = -1;
        }
    }

    public final int takeoverPinnedSnapshot$runtime_release() {
        int i = this.pinningTrackingHandle;
        this.pinningTrackingHandle = -1;
        return i;
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Snapshot getCurrent() {
            return SnapshotKt.currentSnapshot();
        }

        public final boolean isInSnapshot() {
            return SnapshotKt.threadSnapshot.get() != null;
        }

        public final Snapshot takeSnapshot(Function1 function1) {
            return SnapshotKt.currentSnapshot().takeNestedSnapshot(function1);
        }

        public final MutableSnapshot takeMutableSnapshot(Function1 function1, Function1 function12) {
            MutableSnapshot mutableSnapshotTakeNestedMutableSnapshot;
            Snapshot snapshotCurrentSnapshot = SnapshotKt.currentSnapshot();
            MutableSnapshot mutableSnapshot = snapshotCurrentSnapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshotCurrentSnapshot : null;
            if (mutableSnapshot == null || (mutableSnapshotTakeNestedMutableSnapshot = mutableSnapshot.takeNestedMutableSnapshot(function1, function12)) == null) {
                throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot");
            }
            return mutableSnapshotTakeNestedMutableSnapshot;
        }

        public final Object observe(Function1 function1, Function1 function12, Function0 function0) {
            Snapshot transparentObserverMutableSnapshot;
            if (function1 != null || function12 != null) {
                Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
                if (snapshot instanceof TransparentObserverMutableSnapshot) {
                    TransparentObserverMutableSnapshot transparentObserverMutableSnapshot2 = (TransparentObserverMutableSnapshot) snapshot;
                    if (transparentObserverMutableSnapshot2.getThreadId$runtime_release() == ActualJvm_jvmKt.currentThreadId()) {
                        Function1 readObserver$runtime_release = transparentObserverMutableSnapshot2.getReadObserver();
                        Function1 writeObserver$runtime_release = transparentObserverMutableSnapshot2.getWriteObserver$runtime_release();
                        try {
                            ((TransparentObserverMutableSnapshot) snapshot).setReadObserver(SnapshotKt.mergedReadObserver$default(function1, readObserver$runtime_release, false, 4, null));
                            ((TransparentObserverMutableSnapshot) snapshot).setWriteObserver(SnapshotKt.mergedWriteObserver(function12, writeObserver$runtime_release));
                            return function0.invoke();
                        } finally {
                            transparentObserverMutableSnapshot2.setReadObserver(readObserver$runtime_release);
                            transparentObserverMutableSnapshot2.setWriteObserver(writeObserver$runtime_release);
                        }
                    }
                }
                if (snapshot == null || (snapshot instanceof MutableSnapshot)) {
                    transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, function1, function12, true, false);
                } else {
                    if (function1 == null) {
                        return function0.invoke();
                    }
                    transparentObserverMutableSnapshot = snapshot.takeNestedSnapshot(function1);
                }
                try {
                    Snapshot snapshotMakeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                    try {
                        return function0.invoke();
                    } finally {
                        transparentObserverMutableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    }
                } finally {
                    transparentObserverMutableSnapshot.dispose();
                }
            }
            return function0.invoke();
        }

        public final Snapshot getCurrentThreadSnapshot() {
            return (Snapshot) SnapshotKt.threadSnapshot.get();
        }

        public final Snapshot makeCurrentNonObservable(Snapshot snapshot) {
            if (snapshot instanceof TransparentObserverMutableSnapshot) {
                TransparentObserverMutableSnapshot transparentObserverMutableSnapshot = (TransparentObserverMutableSnapshot) snapshot;
                if (transparentObserverMutableSnapshot.getThreadId$runtime_release() == ActualJvm_jvmKt.currentThreadId()) {
                    transparentObserverMutableSnapshot.setReadObserver(null);
                    return snapshot;
                }
            }
            if (snapshot instanceof TransparentObserverSnapshot) {
                TransparentObserverSnapshot transparentObserverSnapshot = (TransparentObserverSnapshot) snapshot;
                if (transparentObserverSnapshot.getThreadId$runtime_release() == ActualJvm_jvmKt.currentThreadId()) {
                    transparentObserverSnapshot.setReadObserver(null);
                    return snapshot;
                }
            }
            Snapshot snapshotCreateTransparentSnapshotWithNoParentReadObserver$default = SnapshotKt.createTransparentSnapshotWithNoParentReadObserver$default(snapshot, null, false, 6, null);
            snapshotCreateTransparentSnapshotWithNoParentReadObserver$default.makeCurrent();
            return snapshotCreateTransparentSnapshotWithNoParentReadObserver$default;
        }

        public final void restoreNonObservable(Snapshot snapshot, Snapshot snapshot2, Function1 function1) {
            if (snapshot == snapshot2) {
                if (snapshot instanceof TransparentObserverMutableSnapshot) {
                    ((TransparentObserverMutableSnapshot) snapshot).setReadObserver(function1);
                    return;
                } else {
                    if (snapshot instanceof TransparentObserverSnapshot) {
                        ((TransparentObserverSnapshot) snapshot).setReadObserver(function1);
                        return;
                    }
                    throw new IllegalStateException(("Non-transparent snapshot was reused: " + snapshot).toString());
                }
            }
            snapshot2.restoreCurrent(snapshot);
            snapshot2.dispose();
        }

        public final ObserverHandle registerApplyObserver(final Function2 function2) {
            SnapshotKt.advanceGlobalSnapshot(SnapshotKt.emptyLambda);
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.applyObservers = CollectionsKt.plus((Collection) SnapshotKt.applyObservers, (Object) function2);
                Unit unit = Unit.INSTANCE;
            }
            return new ObserverHandle() { // from class: androidx.compose.runtime.snapshots.Snapshot$Companion$$ExternalSyntheticLambda1
                @Override // androidx.compose.runtime.snapshots.ObserverHandle
                public final void dispose() {
                    Snapshot.Companion.registerApplyObserver$lambda$6(function2);
                }
            };
        }

        public final void notifyObjectsInitialized() {
            SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void registerApplyObserver$lambda$6(Function2 function2) {
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.applyObservers = CollectionsKt.minus(SnapshotKt.applyObservers, function2);
                Unit unit = Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void registerGlobalWriteObserver$lambda$9(Function1 function1) {
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.globalWriteObservers = CollectionsKt.minus(SnapshotKt.globalWriteObservers, function1);
                Unit unit = Unit.INSTANCE;
            }
            SnapshotKt.advanceGlobalSnapshot();
        }

        public final ObserverHandle registerGlobalWriteObserver(final Function1 function1) {
            synchronized (SnapshotKt.getLock()) {
                SnapshotKt.globalWriteObservers = CollectionsKt.plus((Collection) SnapshotKt.globalWriteObservers, (Object) function1);
                Unit unit = Unit.INSTANCE;
            }
            SnapshotKt.advanceGlobalSnapshot();
            return new ObserverHandle() { // from class: androidx.compose.runtime.snapshots.Snapshot$Companion$$ExternalSyntheticLambda0
                @Override // androidx.compose.runtime.snapshots.ObserverHandle
                public final void dispose() {
                    Snapshot.Companion.registerGlobalWriteObserver$lambda$9(function1);
                }
            };
        }

        public final void sendApplyNotifications() {
            boolean z;
            synchronized (SnapshotKt.getLock()) {
                MutableScatterSet modified$runtime_release = ((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).getModified$runtime_release();
                z = false;
                if (modified$runtime_release != null) {
                    if (modified$runtime_release.isNotEmpty()) {
                        z = true;
                    }
                }
            }
            if (z) {
                SnapshotKt.advanceGlobalSnapshot();
            }
        }
    }

    public final void closeAndReleasePinning$runtime_release() {
        synchronized (SnapshotKt.getLock()) {
            closeLocked$runtime_release();
            releasePinnedSnapshotsForCloseLocked$runtime_release();
            Unit unit = Unit.INSTANCE;
        }
    }
}
