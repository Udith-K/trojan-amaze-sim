package androidx.compose.runtime.snapshots;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
public final class GlobalSnapshot extends MutableSnapshot {
    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(final Function1 function1) {
        return SnapshotKt.takeNewSnapshot(new Function1() { // from class: androidx.compose.runtime.snapshots.GlobalSnapshot.takeNestedSnapshot.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ReadonlySnapshot invoke(SnapshotIdSet snapshotIdSet) {
                int i;
                synchronized (SnapshotKt.getLock()) {
                    i = SnapshotKt.nextSnapshotId;
                    SnapshotKt.nextSnapshotId = i + 1;
                }
                return new ReadonlySnapshot(i, snapshotIdSet, function1);
            }
        });
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public MutableSnapshot takeNestedMutableSnapshot(final Function1 function1, final Function1 function12) {
        return (MutableSnapshot) SnapshotKt.takeNewSnapshot(new Function1() { // from class: androidx.compose.runtime.snapshots.GlobalSnapshot.takeNestedMutableSnapshot.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final MutableSnapshot invoke(SnapshotIdSet snapshotIdSet) {
                int i;
                synchronized (SnapshotKt.getLock()) {
                    i = SnapshotKt.nextSnapshotId;
                    SnapshotKt.nextSnapshotId = i + 1;
                }
                return new MutableSnapshot(i, snapshotIdSet, function1, function12);
            }
        });
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        SnapshotKt.advanceGlobalSnapshot();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime_release, reason: merged with bridge method [inline-methods] */
    public Void mo1056nestedDeactivated$runtime_release(Snapshot snapshot) {
        SnapshotStateMapKt.unsupported();
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime_release, reason: merged with bridge method [inline-methods] */
    public Void mo1055nestedActivated$runtime_release(Snapshot snapshot) {
        SnapshotStateMapKt.unsupported();
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public SnapshotApplyResult apply() {
        throw new IllegalStateException("Cannot apply the global snapshot directly. Call Snapshot.advanceGlobalSnapshot");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public GlobalSnapshot(int i, SnapshotIdSet snapshotIdSet) {
        Function1 function1;
        synchronized (SnapshotKt.getLock()) {
            final List list = SnapshotKt.globalWriteObservers;
            function1 = (Function1) CollectionsKt.singleOrNull(list);
            function1 = function1 == null ? new Function1() { // from class: androidx.compose.runtime.snapshots.GlobalSnapshot$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m1057invoke(obj);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m1057invoke(Object obj) {
                    List list2 = list;
                    int size = list2.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((Function1) list2.get(i2)).invoke(obj);
                    }
                }
            } : function1;
        }
        super(i, snapshotIdSet, null, function1);
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        synchronized (SnapshotKt.getLock()) {
            releasePinnedSnapshotLocked$runtime_release();
            Unit unit = Unit.INSTANCE;
        }
    }
}
