package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.collection.ScatterSetWrapperKt;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
public class MutableSnapshot extends Snapshot {
    private boolean applied;
    private List merged;
    private MutableScatterSet modified;
    private SnapshotIdSet previousIds;
    private int[] previousPinnedSnapshots;
    private final Function1 readObserver;
    private int snapshots;
    private int writeCount;
    private final Function1 writeObserver;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final int[] EmptyIntArray = new int[0];

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime_release */
    public Function1 getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1 getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    public MutableSnapshot(int i, SnapshotIdSet snapshotIdSet, Function1 function1, Function1 function12) {
        super(i, snapshotIdSet, null);
        this.readObserver = function1;
        this.writeObserver = function12;
        this.previousIds = SnapshotIdSet.Companion.getEMPTY();
        this.previousPinnedSnapshots = EmptyIntArray;
        this.snapshots = 1;
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1 function1, Function1 function12) {
        NestedMutableSnapshot nestedMutableSnapshot;
        validateNotDisposed$runtime_release();
        validateNotAppliedOrPinned();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            SnapshotIdSet invalid$runtime_release = getInvalid$runtime_release();
            setInvalid$runtime_release(invalid$runtime_release.set(i));
            nestedMutableSnapshot = new NestedMutableSnapshot(i, SnapshotKt.addRange(invalid$runtime_release, getId() + 1, i), SnapshotKt.mergedReadObserver$default(function1, getReadObserver(), false, 4, null), SnapshotKt.mergedWriteObserver(function12, getWriteObserver$runtime_release()), this);
        }
        if (!getApplied$runtime_release() && !getDisposed$runtime_release()) {
            int id = getId();
            synchronized (SnapshotKt.getLock()) {
                int i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
                Unit unit = Unit.INSTANCE;
            }
            setInvalid$runtime_release(SnapshotKt.addRange(getInvalid$runtime_release(), id + 1, getId()));
        }
        return nestedMutableSnapshot;
    }

    public SnapshotApplyResult apply() throws Throwable {
        MutableScatterSet modified$runtime_release;
        int i;
        MutableScatterSet modified$runtime_release2 = getModified$runtime_release();
        Map mapOptimisticMerges = modified$runtime_release2 != null ? SnapshotKt.optimisticMerges((MutableSnapshot) SnapshotKt.currentGlobalSnapshot.get(), this, SnapshotKt.openSnapshots.clear(((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).getId())) : null;
        List listEmptyList = CollectionsKt.emptyList();
        synchronized (SnapshotKt.getLock()) {
            try {
                SnapshotKt.validateOpen(this);
                if (modified$runtime_release2 == null || modified$runtime_release2.getSize() == 0) {
                    closeLocked$runtime_release();
                    GlobalSnapshot globalSnapshot = (GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get();
                    SnapshotKt.takeNewGlobalSnapshot(globalSnapshot, SnapshotKt.emptyLambda);
                    modified$runtime_release = globalSnapshot.getModified$runtime_release();
                    if (modified$runtime_release == null || !modified$runtime_release.isNotEmpty()) {
                        modified$runtime_release = null;
                    } else {
                        listEmptyList = SnapshotKt.applyObservers;
                    }
                } else {
                    GlobalSnapshot globalSnapshot2 = (GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get();
                    SnapshotApplyResult snapshotApplyResultInnerApplyLocked$runtime_release = innerApplyLocked$runtime_release(SnapshotKt.nextSnapshotId, mapOptimisticMerges, SnapshotKt.openSnapshots.clear(globalSnapshot2.getId()));
                    if (!Intrinsics.areEqual(snapshotApplyResultInnerApplyLocked$runtime_release, SnapshotApplyResult.Success.INSTANCE)) {
                        return snapshotApplyResultInnerApplyLocked$runtime_release;
                    }
                    closeLocked$runtime_release();
                    SnapshotKt.takeNewGlobalSnapshot(globalSnapshot2, SnapshotKt.emptyLambda);
                    modified$runtime_release = globalSnapshot2.getModified$runtime_release();
                    setModified(null);
                    globalSnapshot2.setModified(null);
                    listEmptyList = SnapshotKt.applyObservers;
                }
                Unit unit = Unit.INSTANCE;
                this.applied = true;
                if (modified$runtime_release != null) {
                    Set setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(modified$runtime_release);
                    if (!setWrapIntoSet.isEmpty()) {
                        int size = listEmptyList.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            ((Function2) listEmptyList.get(i2)).invoke(setWrapIntoSet, this);
                        }
                    }
                }
                if (modified$runtime_release2 != null && modified$runtime_release2.isNotEmpty()) {
                    Set setWrapIntoSet2 = ScatterSetWrapperKt.wrapIntoSet(modified$runtime_release2);
                    int size2 = listEmptyList.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        ((Function2) listEmptyList.get(i3)).invoke(setWrapIntoSet2, this);
                    }
                }
                synchronized (SnapshotKt.getLock()) {
                    try {
                        releasePinnedSnapshotsForCloseLocked$runtime_release();
                        SnapshotKt.checkAndOverwriteUnusedRecordsLocked();
                        if (modified$runtime_release != null) {
                            try {
                                Object[] objArr = modified$runtime_release.elements;
                                long[] jArr = modified$runtime_release.metadata;
                                int length = jArr.length - 2;
                                if (length >= 0) {
                                    int i4 = 0;
                                    while (true) {
                                        long j = jArr[i4];
                                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i5 = 8 - ((~(i4 - length)) >>> 31);
                                            for (int i6 = 0; i6 < i5; i6++) {
                                                if ((j & 255) < 128) {
                                                    SnapshotKt.processForUnusedRecordsLocked((StateObject) objArr[(i4 << 3) + i6]);
                                                }
                                                j >>= 8;
                                            }
                                            if (i5 != 8) {
                                                break;
                                            }
                                        }
                                        if (i4 == length) {
                                            break;
                                        }
                                        i4++;
                                    }
                                }
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                        if (modified$runtime_release2 != null) {
                            Object[] objArr2 = modified$runtime_release2.elements;
                            long[] jArr2 = modified$runtime_release2.metadata;
                            int length2 = jArr2.length - 2;
                            if (length2 >= 0) {
                                int i7 = 0;
                                while (true) {
                                    long j2 = jArr2[i7];
                                    if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                                        int i8 = 8 - ((~(i7 - length2)) >>> 31);
                                        for (int i9 = 0; i9 < i8; i9++) {
                                            if ((j2 & 255) < 128) {
                                                SnapshotKt.processForUnusedRecordsLocked((StateObject) objArr2[(i7 << 3) + i9]);
                                            }
                                            j2 >>= 8;
                                        }
                                        i = 1;
                                        if (i8 != 8) {
                                            break;
                                        }
                                    } else {
                                        i = 1;
                                    }
                                    if (i7 == length2) {
                                        break;
                                    }
                                    i7 += i;
                                }
                            }
                        }
                        List list = this.merged;
                        if (list != null) {
                            int size3 = list.size();
                            for (int i10 = 0; i10 < size3; i10++) {
                                SnapshotKt.processForUnusedRecordsLocked((StateObject) list.get(i10));
                            }
                        }
                        this.merged = null;
                        Unit unit2 = Unit.INSTANCE;
                        return SnapshotApplyResult.Success.INSTANCE;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (getDisposed$runtime_release()) {
            return;
        }
        super.dispose();
        mo1056nestedDeactivated$runtime_release(this);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1 function1) {
        NestedReadonlySnapshot nestedReadonlySnapshot;
        validateNotDisposed$runtime_release();
        validateNotAppliedOrPinned();
        int id = getId();
        recordPrevious$runtime_release(getId());
        synchronized (SnapshotKt.getLock()) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            nestedReadonlySnapshot = new NestedReadonlySnapshot(i, SnapshotKt.addRange(getInvalid$runtime_release(), id + 1, i), SnapshotKt.mergedReadObserver$default(function1, getReadObserver(), false, 4, null), this);
        }
        if (!getApplied$runtime_release() && !getDisposed$runtime_release()) {
            int id2 = getId();
            synchronized (SnapshotKt.getLock()) {
                int i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
                Unit unit = Unit.INSTANCE;
            }
            setInvalid$runtime_release(SnapshotKt.addRange(getInvalid$runtime_release(), id2 + 1, getId()));
        }
        return nestedReadonlySnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime_release */
    public void mo1055nestedActivated$runtime_release(Snapshot snapshot) {
        this.snapshots++;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime_release */
    public void mo1056nestedDeactivated$runtime_release(Snapshot snapshot) {
        if (!(this.snapshots > 0)) {
            PreconditionsKt.throwIllegalArgumentException("no pending nested snapshots");
        }
        int i = this.snapshots - 1;
        this.snapshots = i;
        if (i != 0 || this.applied) {
            return;
        }
        abandon();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        if (this.applied || getDisposed$runtime_release()) {
            return;
        }
        advance$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void closeLocked$runtime_release() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void releasePinnedSnapshotsForCloseLocked$runtime_release() {
        releasePreviouslyPinnedSnapshotsLocked();
        super.releasePinnedSnapshotsForCloseLocked$runtime_release();
    }

    private final void validateNotApplied() {
        if (this.applied) {
            PreconditionsKt.throwIllegalStateException("Unsupported operation on a snapshot that has been applied");
        }
    }

    private final void validateNotAppliedOrPinned() {
        if (!this.applied || ((Snapshot) this).pinningTrackingHandle >= 0) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unsupported operation on a disposed or applied snapshot");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void abandon() {
        /*
            r15 = this;
            androidx.collection.MutableScatterSet r0 = r15.getModified$runtime_release()
            if (r0 == 0) goto L77
            r15.validateNotApplied()
            r1 = 0
            r15.setModified(r1)
            int r1 = r15.getId()
            java.lang.Object[] r2 = r0.elements
            long[] r0 = r0.metadata
            int r3 = r0.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L77
            r4 = 0
            r5 = r4
        L1c:
            r6 = r0[r5]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L72
            int r8 = r5 - r3
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r4
        L36:
            if (r10 >= r8) goto L70
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L6c
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r2[r11]
            androidx.compose.runtime.snapshots.StateObject r11 = (androidx.compose.runtime.snapshots.StateObject) r11
            androidx.compose.runtime.snapshots.StateRecord r11 = r11.getFirstStateRecord()
        L4c:
            if (r11 == 0) goto L6c
            int r12 = r11.getSnapshotId$runtime_release()
            if (r12 == r1) goto L64
            androidx.compose.runtime.snapshots.SnapshotIdSet r12 = r15.previousIds
            int r13 = r11.getSnapshotId$runtime_release()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            boolean r12 = kotlin.collections.CollectionsKt.contains(r12, r13)
            if (r12 == 0) goto L67
        L64:
            r11.setSnapshotId$runtime_release(r4)
        L67:
            androidx.compose.runtime.snapshots.StateRecord r11 = r11.getNext$runtime_release()
            goto L4c
        L6c:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L36
        L70:
            if (r8 != r9) goto L77
        L72:
            if (r5 == r3) goto L77
            int r5 = r5 + 1
            goto L1c
        L77:
            r15.closeAndReleasePinning$runtime_release()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.abandon():void");
    }

    public final SnapshotApplyResult innerApplyLocked$runtime_release(int i, Map map, SnapshotIdSet snapshotIdSet) {
        MutableScatterSet mutableScatterSet;
        List listPlus;
        SnapshotIdSet snapshotIdSet2;
        Object[] objArr;
        long[] jArr;
        int i2;
        SnapshotIdSet snapshotIdSet3;
        MutableScatterSet mutableScatterSet2;
        Object[] objArr2;
        long[] jArr2;
        int i3;
        StateRecord stateRecord;
        StateRecord stateRecordMergeRecords;
        SnapshotIdSet snapshotIdSetOr = getInvalid$runtime_release().set(getId()).or(this.previousIds);
        MutableScatterSet modified$runtime_release = getModified$runtime_release();
        Intrinsics.checkNotNull(modified$runtime_release);
        Object[] objArr3 = modified$runtime_release.elements;
        long[] jArr3 = modified$runtime_release.metadata;
        int length = jArr3.length - 2;
        ArrayList arrayList = null;
        if (length >= 0) {
            listPlus = null;
            int i4 = 0;
            while (true) {
                long j = jArr3[i4];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8;
                    int i6 = 8 - ((~(i4 - length)) >>> 31);
                    int i7 = 0;
                    while (i7 < i6) {
                        if ((j & 255) < 128) {
                            StateObject stateObject = (StateObject) objArr3[(i4 << 3) + i7];
                            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                            mutableScatterSet2 = modified$runtime_release;
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            StateRecord stateRecord2 = SnapshotKt.readable(firstStateRecord, i, snapshotIdSet);
                            if (stateRecord2 == null || (stateRecord = SnapshotKt.readable(firstStateRecord, getId(), snapshotIdSetOr)) == null) {
                                snapshotIdSet3 = snapshotIdSetOr;
                            } else {
                                snapshotIdSet3 = snapshotIdSetOr;
                                if (stateRecord.getSnapshotId$runtime_release() != 1 && !Intrinsics.areEqual(stateRecord2, stateRecord)) {
                                    StateRecord stateRecord3 = SnapshotKt.readable(firstStateRecord, getId(), getInvalid$runtime_release());
                                    if (stateRecord3 == null) {
                                        SnapshotKt.readError();
                                        throw new KotlinNothingValueException();
                                    }
                                    if (map == null || (stateRecordMergeRecords = (StateRecord) map.get(stateRecord2)) == null) {
                                        stateRecordMergeRecords = stateObject.mergeRecords(stateRecord, stateRecord2, stateRecord3);
                                    }
                                    if (stateRecordMergeRecords == null) {
                                        return new SnapshotApplyResult.Failure(this);
                                    }
                                    if (!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord3)) {
                                        if (Intrinsics.areEqual(stateRecordMergeRecords, stateRecord2)) {
                                            if (arrayList == null) {
                                                arrayList = new ArrayList();
                                            }
                                            arrayList.add(TuplesKt.to(stateObject, stateRecord2.create()));
                                            if (listPlus == null) {
                                                listPlus = new ArrayList();
                                            }
                                            listPlus.add(stateObject);
                                        } else {
                                            if (arrayList == null) {
                                                arrayList = new ArrayList();
                                            }
                                            arrayList.add(!Intrinsics.areEqual(stateRecordMergeRecords, stateRecord) ? TuplesKt.to(stateObject, stateRecordMergeRecords) : TuplesKt.to(stateObject, stateRecord.create()));
                                        }
                                    }
                                }
                            }
                            i3 = 8;
                        } else {
                            snapshotIdSet3 = snapshotIdSetOr;
                            mutableScatterSet2 = modified$runtime_release;
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            i3 = i5;
                        }
                        j >>= i3;
                        i7++;
                        i5 = i3;
                        objArr3 = objArr2;
                        jArr3 = jArr2;
                        modified$runtime_release = mutableScatterSet2;
                        snapshotIdSetOr = snapshotIdSet3;
                    }
                    snapshotIdSet2 = snapshotIdSetOr;
                    mutableScatterSet = modified$runtime_release;
                    objArr = objArr3;
                    jArr = jArr3;
                    i2 = 1;
                    if (i6 != i5) {
                        break;
                    }
                } else {
                    snapshotIdSet2 = snapshotIdSetOr;
                    mutableScatterSet = modified$runtime_release;
                    objArr = objArr3;
                    jArr = jArr3;
                    i2 = 1;
                }
                if (i4 == length) {
                    break;
                }
                i4 += i2;
                objArr3 = objArr;
                jArr3 = jArr;
                modified$runtime_release = mutableScatterSet;
                snapshotIdSetOr = snapshotIdSet2;
            }
        } else {
            mutableScatterSet = modified$runtime_release;
            listPlus = null;
        }
        if (arrayList != null) {
            advance$runtime_release();
            int size = arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                Pair pair = (Pair) arrayList.get(i8);
                StateObject stateObject2 = (StateObject) pair.component1();
                StateRecord stateRecord4 = (StateRecord) pair.component2();
                stateRecord4.setSnapshotId$runtime_release(getId());
                synchronized (SnapshotKt.getLock()) {
                    stateRecord4.setNext$runtime_release(stateObject2.getFirstStateRecord());
                    stateObject2.prependStateRecord(stateRecord4);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        if (listPlus != null) {
            int size2 = listPlus.size();
            for (int i9 = 0; i9 < size2; i9++) {
                mutableScatterSet.remove((StateObject) listPlus.get(i9));
            }
            List list = this.merged;
            if (list != null) {
                listPlus = CollectionsKt.plus((Collection) list, (Iterable) listPlus);
            }
            this.merged = listPlus;
        }
        return SnapshotApplyResult.Success.INSTANCE;
    }

    public final void advance$runtime_release() {
        recordPrevious$runtime_release(getId());
        Unit unit = Unit.INSTANCE;
        if (getApplied$runtime_release() || getDisposed$runtime_release()) {
            return;
        }
        int id = getId();
        synchronized (SnapshotKt.getLock()) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            setId$runtime_release(i);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
        }
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid$runtime_release(), id + 1, getId()));
    }

    public final void recordPreviousPinnedSnapshot$runtime_release(int i) {
        if (i >= 0) {
            this.previousPinnedSnapshots = ArraysKt.plus(this.previousPinnedSnapshots, i);
        }
    }

    public final void recordPreviousPinnedSnapshots$runtime_release(int[] iArr) {
        if (iArr.length == 0) {
            return;
        }
        int[] iArr2 = this.previousPinnedSnapshots;
        if (iArr2.length != 0) {
            iArr = ArraysKt.plus(iArr2, iArr);
        }
        this.previousPinnedSnapshots = iArr;
    }

    private final void releasePreviouslyPinnedSnapshotsLocked() {
        int length = this.previousPinnedSnapshots.length;
        for (int i = 0; i < length; i++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[i]);
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime_release */
    public void mo1058recordModified$runtime_release(StateObject stateObject) {
        MutableScatterSet modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release == null) {
            modified$runtime_release = ScatterSetKt.mutableScatterSetOf();
            setModified(modified$runtime_release);
        }
        modified$runtime_release.add(stateObject);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public int getWriteCount$runtime_release() {
        return this.writeCount;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setWriteCount$runtime_release(int i) {
        this.writeCount = i;
    }

    public MutableScatterSet getModified$runtime_release() {
        return this.modified;
    }

    public void setModified(MutableScatterSet mutableScatterSet) {
        this.modified = mutableScatterSet;
    }

    public final SnapshotIdSet getPreviousIds$runtime_release() {
        return this.previousIds;
    }

    public final int[] getPreviousPinnedSnapshots$runtime_release() {
        return this.previousPinnedSnapshots;
    }

    public final boolean getApplied$runtime_release() {
        return this.applied;
    }

    public final void setApplied$runtime_release(boolean z) {
        this.applied = z;
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void recordPrevious$runtime_release(int i) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.set(i);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void recordPreviousList$runtime_release(SnapshotIdSet snapshotIdSet) {
        synchronized (SnapshotKt.getLock()) {
            this.previousIds = this.previousIds.or(snapshotIdSet);
            Unit unit = Unit.INSTANCE;
        }
    }
}
