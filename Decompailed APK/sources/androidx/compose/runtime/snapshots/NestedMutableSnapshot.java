package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NestedMutableSnapshot extends MutableSnapshot {
    private boolean deactivated;
    private final MutableSnapshot parent;

    public NestedMutableSnapshot(int i, SnapshotIdSet snapshotIdSet, Function1 function1, Function1 function12, MutableSnapshot mutableSnapshot) {
        super(i, snapshotIdSet, function1, function12);
        this.parent = mutableSnapshot;
        mutableSnapshot.mo1055nestedActivated$runtime_release(this);
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed$runtime_release()) {
            return;
        }
        super.dispose();
        deactivate();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public SnapshotApplyResult apply() {
        Map mapOptimisticMerges;
        if (this.parent.getApplied$runtime_release() || this.parent.getDisposed$runtime_release()) {
            return new SnapshotApplyResult.Failure(this);
        }
        MutableScatterSet modified$runtime_release = getModified$runtime_release();
        int id = getId();
        if (modified$runtime_release != null) {
            MutableSnapshot mutableSnapshot = this.parent;
            mapOptimisticMerges = SnapshotKt.optimisticMerges(mutableSnapshot, this, mutableSnapshot.getInvalid$runtime_release());
        } else {
            mapOptimisticMerges = null;
        }
        synchronized (SnapshotKt.getLock()) {
            try {
                SnapshotKt.validateOpen(this);
                if (modified$runtime_release == null || modified$runtime_release.getSize() == 0) {
                    closeAndReleasePinning$runtime_release();
                } else {
                    SnapshotApplyResult snapshotApplyResultInnerApplyLocked$runtime_release = innerApplyLocked$runtime_release(this.parent.getId(), mapOptimisticMerges, this.parent.getInvalid$runtime_release());
                    if (!Intrinsics.areEqual(snapshotApplyResultInnerApplyLocked$runtime_release, SnapshotApplyResult.Success.INSTANCE)) {
                        return snapshotApplyResultInnerApplyLocked$runtime_release;
                    }
                    MutableScatterSet modified$runtime_release2 = this.parent.getModified$runtime_release();
                    if (modified$runtime_release2 != null) {
                        modified$runtime_release2.addAll(modified$runtime_release);
                    } else {
                        this.parent.setModified(modified$runtime_release);
                        setModified(null);
                    }
                }
                if (this.parent.getId() < id) {
                    this.parent.advance$runtime_release();
                }
                MutableSnapshot mutableSnapshot2 = this.parent;
                mutableSnapshot2.setInvalid$runtime_release(mutableSnapshot2.getInvalid$runtime_release().clear(id).andNot(getPreviousIds$runtime_release()));
                this.parent.recordPrevious$runtime_release(id);
                this.parent.recordPreviousPinnedSnapshot$runtime_release(takeoverPinnedSnapshot$runtime_release());
                this.parent.recordPreviousList$runtime_release(getPreviousIds$runtime_release());
                this.parent.recordPreviousPinnedSnapshots$runtime_release(getPreviousPinnedSnapshots$runtime_release());
                Unit unit = Unit.INSTANCE;
                setApplied$runtime_release(true);
                deactivate();
                return SnapshotApplyResult.Success.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void deactivate() {
        if (this.deactivated) {
            return;
        }
        this.deactivated = true;
        this.parent.mo1056nestedDeactivated$runtime_release(this);
    }
}
