package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.AtomicInt;
import androidx.compose.runtime.SnapshotThreadLocal;
import androidx.compose.runtime.WeakReference;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotIdSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SnapshotKt {
    private static List applyObservers;
    private static final AtomicReference currentGlobalSnapshot;
    private static final SnapshotWeakSet extraStateObjects;
    private static List globalWriteObservers;
    private static int nextSnapshotId;
    private static SnapshotIdSet openSnapshots;
    private static AtomicInt pendingApplyObserverCount;
    private static final SnapshotDoubleIndexHeap pinningTable;
    private static final Snapshot snapshotInitializer;
    private static final Function1 emptyLambda = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1
        public final void invoke(SnapshotIdSet snapshotIdSet) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SnapshotIdSet) obj);
            return Unit.INSTANCE;
        }
    };
    private static final SnapshotThreadLocal threadSnapshot = new SnapshotThreadLocal();
    private static final Object lock = new Object();

    public static final int trackPinning(int i, SnapshotIdSet snapshotIdSet) {
        int iAdd;
        int iLowest = snapshotIdSet.lowest(i);
        synchronized (getLock()) {
            iAdd = pinningTable.add(iLowest);
        }
        return iAdd;
    }

    public static final void releasePinningLocked(int i) {
        pinningTable.remove(i);
    }

    public static final Snapshot currentSnapshot() {
        Snapshot snapshot = (Snapshot) threadSnapshot.get();
        return snapshot == null ? (Snapshot) currentGlobalSnapshot.get() : snapshot;
    }

    static {
        SnapshotIdSet.Companion companion = SnapshotIdSet.Companion;
        openSnapshots = companion.getEMPTY();
        nextSnapshotId = 2;
        pinningTable = new SnapshotDoubleIndexHeap();
        extraStateObjects = new SnapshotWeakSet();
        applyObservers = CollectionsKt.emptyList();
        globalWriteObservers = CollectionsKt.emptyList();
        int i = nextSnapshotId;
        nextSnapshotId = i + 1;
        GlobalSnapshot globalSnapshot = new GlobalSnapshot(i, companion.getEMPTY());
        openSnapshots = openSnapshots.set(globalSnapshot.getId());
        AtomicReference atomicReference = new AtomicReference(globalSnapshot);
        currentGlobalSnapshot = atomicReference;
        snapshotInitializer = (Snapshot) atomicReference.get();
        pendingApplyObserverCount = new AtomicInt(0);
    }

    static /* synthetic */ Snapshot createTransparentSnapshotWithNoParentReadObserver$default(Snapshot snapshot, Function1 function1, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return createTransparentSnapshotWithNoParentReadObserver(snapshot, function1, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Snapshot createTransparentSnapshotWithNoParentReadObserver(Snapshot snapshot, Function1 function1, boolean z) {
        boolean z2 = snapshot instanceof MutableSnapshot;
        if (z2 || snapshot == null) {
            return new TransparentObserverMutableSnapshot(z2 ? (MutableSnapshot) snapshot : null, function1, null, false, z);
        }
        return new TransparentObserverSnapshot(snapshot, function1, false, z);
    }

    static /* synthetic */ Function1 mergedReadObserver$default(Function1 function1, Function1 function12, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return mergedReadObserver(function1, function12, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1 mergedReadObserver(final Function1 function1, final Function1 function12, boolean z) {
        if (!z) {
            function12 = null;
        }
        if (function1 == null || function12 == null || function1 == function12) {
            return function1 == null ? function12 : function1;
        }
        return new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.mergedReadObserver.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m1060invoke(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1060invoke(Object obj) {
                function1.invoke(obj);
                function12.invoke(obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1 mergedWriteObserver(final Function1 function1, final Function1 function12) {
        if (function1 == null || function12 == null || function1 == function12) {
            return function1 == null ? function12 : function1;
        }
        return new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.mergedWriteObserver.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m1061invoke(obj);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1061invoke(Object obj) {
                function1.invoke(obj);
                function12.invoke(obj);
            }
        };
    }

    public static final Object getLock() {
        return lock;
    }

    public static final StateRecord newWritableRecord(StateRecord stateRecord, StateObject stateObject, Snapshot snapshot) {
        StateRecord stateRecordNewWritableRecordLocked;
        synchronized (getLock()) {
            stateRecordNewWritableRecordLocked = newWritableRecordLocked(stateRecord, stateObject, snapshot);
        }
        return stateRecordNewWritableRecordLocked;
    }

    public static final Snapshot getSnapshotInitializer() {
        return snapshotInitializer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object takeNewGlobalSnapshot(Snapshot snapshot, Function1 function1) {
        Object objInvoke = function1.invoke(openSnapshots.clear(snapshot.getId()));
        synchronized (getLock()) {
            int i = nextSnapshotId;
            nextSnapshotId = i + 1;
            openSnapshots = openSnapshots.clear(snapshot.getId());
            currentGlobalSnapshot.set(new GlobalSnapshot(i, openSnapshots));
            snapshot.dispose();
            openSnapshots = openSnapshots.set(i);
            Unit unit = Unit.INSTANCE;
        }
        return objInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object advanceGlobalSnapshot(kotlin.jvm.functions.Function1 r17) {
        /*
            r0 = 1
            androidx.compose.runtime.snapshots.Snapshot r1 = androidx.compose.runtime.snapshots.SnapshotKt.snapshotInitializer
            java.lang.String r2 = "null cannot be cast to non-null type androidx.compose.runtime.snapshots.GlobalSnapshot"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            androidx.compose.runtime.snapshots.GlobalSnapshot r1 = (androidx.compose.runtime.snapshots.GlobalSnapshot) r1
            java.lang.Object r1 = getLock()
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicReference r2 = androidx.compose.runtime.snapshots.SnapshotKt.currentGlobalSnapshot     // Catch: java.lang.Throwable -> L24
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Throwable -> L24
            r3 = r2
            androidx.compose.runtime.snapshots.GlobalSnapshot r3 = (androidx.compose.runtime.snapshots.GlobalSnapshot) r3     // Catch: java.lang.Throwable -> L24
            androidx.collection.MutableScatterSet r3 = r3.getModified$runtime_release()     // Catch: java.lang.Throwable -> L24
            if (r3 == 0) goto L27
            androidx.compose.runtime.AtomicInt r4 = androidx.compose.runtime.snapshots.SnapshotKt.pendingApplyObserverCount     // Catch: java.lang.Throwable -> L24
            r4.add(r0)     // Catch: java.lang.Throwable -> L24
            goto L27
        L24:
            r0 = move-exception
            goto Lb0
        L27:
            r4 = r2
            androidx.compose.runtime.snapshots.Snapshot r4 = (androidx.compose.runtime.snapshots.Snapshot) r4     // Catch: java.lang.Throwable -> L24
            r5 = r17
            java.lang.Object r4 = takeNewGlobalSnapshot(r4, r5)     // Catch: java.lang.Throwable -> L24
            monitor-exit(r1)
            r1 = 0
            if (r3 == 0) goto L5b
            r5 = -1
            java.util.List r6 = androidx.compose.runtime.snapshots.SnapshotKt.applyObservers     // Catch: java.lang.Throwable -> L4d
            int r7 = r6.size()     // Catch: java.lang.Throwable -> L4d
            r8 = r1
        L3c:
            if (r8 >= r7) goto L4f
            java.lang.Object r9 = r6.get(r8)     // Catch: java.lang.Throwable -> L4d
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9     // Catch: java.lang.Throwable -> L4d
            java.util.Set r10 = androidx.compose.runtime.collection.ScatterSetWrapperKt.wrapIntoSet(r3)     // Catch: java.lang.Throwable -> L4d
            r9.invoke(r10, r2)     // Catch: java.lang.Throwable -> L4d
            int r8 = r8 + r0
            goto L3c
        L4d:
            r0 = move-exception
            goto L55
        L4f:
            androidx.compose.runtime.AtomicInt r2 = androidx.compose.runtime.snapshots.SnapshotKt.pendingApplyObserverCount
            r2.add(r5)
            goto L5b
        L55:
            androidx.compose.runtime.AtomicInt r1 = androidx.compose.runtime.snapshots.SnapshotKt.pendingApplyObserverCount
            r1.add(r5)
            throw r0
        L5b:
            java.lang.Object r2 = getLock()
            monitor-enter(r2)
            checkAndOverwriteUnusedRecordsLocked()     // Catch: java.lang.Throwable -> L9f
            if (r3 == 0) goto Lac
            java.lang.Object[] r5 = r3.elements     // Catch: java.lang.Throwable -> L9f
            long[] r3 = r3.metadata     // Catch: java.lang.Throwable -> L9f
            int r6 = r3.length     // Catch: java.lang.Throwable -> L9f
            int r6 = r6 + (-2)
            if (r6 < 0) goto Laa
            r7 = r1
        L6f:
            r8 = r3[r7]     // Catch: java.lang.Throwable -> L9f
            long r10 = ~r8     // Catch: java.lang.Throwable -> L9f
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto La6
            int r10 = r7 - r6
            int r10 = ~r10     // Catch: java.lang.Throwable -> L9f
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r1
        L89:
            if (r12 >= r10) goto La4
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto La1
            int r13 = r7 << 3
            int r13 = r13 + r12
            r13 = r5[r13]     // Catch: java.lang.Throwable -> L9f
            androidx.compose.runtime.snapshots.StateObject r13 = (androidx.compose.runtime.snapshots.StateObject) r13     // Catch: java.lang.Throwable -> L9f
            processForUnusedRecordsLocked(r13)     // Catch: java.lang.Throwable -> L9f
            goto La1
        L9f:
            r0 = move-exception
            goto Lae
        La1:
            long r8 = r8 >> r11
            int r12 = r12 + r0
            goto L89
        La4:
            if (r10 != r11) goto Laa
        La6:
            if (r7 == r6) goto Laa
            int r7 = r7 + r0
            goto L6f
        Laa:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L9f
        Lac:
            monitor-exit(r2)
            return r4
        Lae:
            monitor-exit(r2)
            throw r0
        Lb0:
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotKt.advanceGlobalSnapshot(kotlin.jvm.functions.Function1):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void advanceGlobalSnapshot() {
        advanceGlobalSnapshot(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.advanceGlobalSnapshot.3
            public final void invoke(SnapshotIdSet snapshotIdSet) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SnapshotIdSet) obj);
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Snapshot takeNewSnapshot(final Function1 function1) {
        return (Snapshot) advanceGlobalSnapshot(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt.takeNewSnapshot.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Snapshot invoke(SnapshotIdSet snapshotIdSet) {
                Snapshot snapshot = (Snapshot) function1.invoke(snapshotIdSet);
                synchronized (SnapshotKt.getLock()) {
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(snapshot.getId());
                    Unit unit = Unit.INSTANCE;
                }
                return snapshot;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateOpen(Snapshot snapshot) {
        int iLowestOrDefault;
        if (openSnapshots.get(snapshot.getId())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Snapshot is not open: id=");
        sb.append(snapshot.getId());
        sb.append(", disposed=");
        sb.append(snapshot.getDisposed$runtime_release());
        sb.append(", applied=");
        MutableSnapshot mutableSnapshot = snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null;
        sb.append(mutableSnapshot != null ? Boolean.valueOf(mutableSnapshot.getApplied$runtime_release()) : "read-only");
        sb.append(", lowestPin=");
        synchronized (getLock()) {
            iLowestOrDefault = pinningTable.lowestOrDefault(-1);
        }
        sb.append(iLowestOrDefault);
        throw new IllegalStateException(sb.toString().toString());
    }

    private static final boolean valid(int i, int i2, SnapshotIdSet snapshotIdSet) {
        return (i2 == 0 || i2 > i || snapshotIdSet.get(i2)) ? false : true;
    }

    private static final boolean valid(StateRecord stateRecord, int i, SnapshotIdSet snapshotIdSet) {
        return valid(i, stateRecord.getSnapshotId$runtime_release(), snapshotIdSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StateRecord readable(StateRecord stateRecord, int i, SnapshotIdSet snapshotIdSet) {
        StateRecord stateRecord2 = null;
        while (stateRecord != null) {
            if (valid(stateRecord, i, snapshotIdSet) && (stateRecord2 == null || stateRecord2.getSnapshotId$runtime_release() < stateRecord.getSnapshotId$runtime_release())) {
                stateRecord2 = stateRecord;
            }
            stateRecord = stateRecord.getNext$runtime_release();
        }
        if (stateRecord2 != null) {
            return stateRecord2;
        }
        return null;
    }

    public static final StateRecord readable(StateRecord stateRecord, StateObject stateObject) {
        StateRecord stateRecord2;
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot current = companion.getCurrent();
        Function1 readObserver = current.getReadObserver();
        if (readObserver != null) {
            readObserver.invoke(stateObject);
        }
        StateRecord stateRecord3 = readable(stateRecord, current.getId(), current.getInvalid$runtime_release());
        if (stateRecord3 != null) {
            return stateRecord3;
        }
        synchronized (getLock()) {
            Snapshot current2 = companion.getCurrent();
            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.readable$lambda$9");
            stateRecord2 = readable(firstStateRecord, current2.getId(), current2.getInvalid$runtime_release());
            if (stateRecord2 == null) {
                readError();
                throw new KotlinNothingValueException();
            }
        }
        return stateRecord2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void readError() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied");
    }

    private static final StateRecord usedLocked(StateObject stateObject) {
        int iLowestOrDefault = pinningTable.lowestOrDefault(nextSnapshotId) - 1;
        SnapshotIdSet empty = SnapshotIdSet.Companion.getEMPTY();
        StateRecord stateRecord = null;
        for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.getNext$runtime_release()) {
            if (firstStateRecord.getSnapshotId$runtime_release() == 0) {
                return firstStateRecord;
            }
            if (valid(firstStateRecord, iLowestOrDefault, empty)) {
                if (stateRecord != null) {
                    return firstStateRecord.getSnapshotId$runtime_release() < stateRecord.getSnapshotId$runtime_release() ? firstStateRecord : stateRecord;
                }
                stateRecord = firstStateRecord;
            }
        }
        return null;
    }

    private static final boolean overwriteUnusedRecordsLocked(StateObject stateObject) {
        StateRecord stateRecord;
        int iLowestOrDefault = pinningTable.lowestOrDefault(nextSnapshotId);
        StateRecord stateRecord2 = null;
        StateRecord firstStateRecord = null;
        int i = 0;
        for (StateRecord firstStateRecord2 = stateObject.getFirstStateRecord(); firstStateRecord2 != null; firstStateRecord2 = firstStateRecord2.getNext$runtime_release()) {
            int snapshotId$runtime_release = firstStateRecord2.getSnapshotId$runtime_release();
            if (snapshotId$runtime_release != 0) {
                if (snapshotId$runtime_release >= iLowestOrDefault) {
                    i++;
                } else if (stateRecord2 == null) {
                    i++;
                    stateRecord2 = firstStateRecord2;
                } else {
                    if (firstStateRecord2.getSnapshotId$runtime_release() < stateRecord2.getSnapshotId$runtime_release()) {
                        stateRecord = stateRecord2;
                        stateRecord2 = firstStateRecord2;
                    } else {
                        stateRecord = firstStateRecord2;
                    }
                    if (firstStateRecord == null) {
                        firstStateRecord = stateObject.getFirstStateRecord();
                        StateRecord stateRecord3 = firstStateRecord;
                        while (true) {
                            if (firstStateRecord == null) {
                                firstStateRecord = stateRecord3;
                                break;
                            }
                            if (firstStateRecord.getSnapshotId$runtime_release() >= iLowestOrDefault) {
                                break;
                            }
                            if (stateRecord3.getSnapshotId$runtime_release() < firstStateRecord.getSnapshotId$runtime_release()) {
                                stateRecord3 = firstStateRecord;
                            }
                            firstStateRecord = firstStateRecord.getNext$runtime_release();
                        }
                    }
                    stateRecord2.setSnapshotId$runtime_release(0);
                    stateRecord2.assign(firstStateRecord);
                    stateRecord2 = stateRecord;
                }
            }
        }
        return i > 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkAndOverwriteUnusedRecordsLocked() {
        SnapshotWeakSet snapshotWeakSet = extraStateObjects;
        int size$runtime_release = snapshotWeakSet.getSize$runtime_release();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= size$runtime_release) {
                break;
            }
            WeakReference weakReference = snapshotWeakSet.getValues$runtime_release()[i];
            Object obj = weakReference != null ? weakReference.get() : null;
            if (obj != null && overwriteUnusedRecordsLocked((StateObject) obj)) {
                if (i2 != i) {
                    snapshotWeakSet.getValues$runtime_release()[i2] = weakReference;
                    snapshotWeakSet.getHashes$runtime_release()[i2] = snapshotWeakSet.getHashes$runtime_release()[i];
                }
                i2++;
            }
            i++;
        }
        for (int i3 = i2; i3 < size$runtime_release; i3++) {
            snapshotWeakSet.getValues$runtime_release()[i3] = null;
            snapshotWeakSet.getHashes$runtime_release()[i3] = 0;
        }
        if (i2 != size$runtime_release) {
            snapshotWeakSet.setSize$runtime_release(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void processForUnusedRecordsLocked(StateObject stateObject) {
        if (overwriteUnusedRecordsLocked(stateObject)) {
            extraStateObjects.add(stateObject);
        }
    }

    public static final StateRecord writableRecord(StateRecord stateRecord, StateObject stateObject, Snapshot snapshot) {
        StateRecord stateRecordNewWritableRecordLocked;
        if (snapshot.getReadOnly()) {
            snapshot.mo1058recordModified$runtime_release(stateObject);
        }
        int id = snapshot.getId();
        StateRecord stateRecord2 = readable(stateRecord, id, snapshot.getInvalid$runtime_release());
        if (stateRecord2 == null) {
            readError();
            throw new KotlinNothingValueException();
        }
        if (stateRecord2.getSnapshotId$runtime_release() == snapshot.getId()) {
            return stateRecord2;
        }
        synchronized (getLock()) {
            stateRecordNewWritableRecordLocked = readable(stateObject.getFirstStateRecord(), id, snapshot.getInvalid$runtime_release());
            if (stateRecordNewWritableRecordLocked == null) {
                readError();
                throw new KotlinNothingValueException();
            }
            if (stateRecordNewWritableRecordLocked.getSnapshotId$runtime_release() != id) {
                stateRecordNewWritableRecordLocked = newWritableRecordLocked(stateRecordNewWritableRecordLocked, stateObject, snapshot);
            }
        }
        Intrinsics.checkNotNull(stateRecordNewWritableRecordLocked, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.writableRecord");
        if (stateRecord2.getSnapshotId$runtime_release() != 1) {
            snapshot.mo1058recordModified$runtime_release(stateObject);
        }
        return stateRecordNewWritableRecordLocked;
    }

    public static final StateRecord overwritableRecord(StateRecord stateRecord, StateObject stateObject, Snapshot snapshot, StateRecord stateRecord2) {
        StateRecord stateRecordNewOverwritableRecordLocked;
        if (snapshot.getReadOnly()) {
            snapshot.mo1058recordModified$runtime_release(stateObject);
        }
        int id = snapshot.getId();
        if (stateRecord2.getSnapshotId$runtime_release() == id) {
            return stateRecord2;
        }
        synchronized (getLock()) {
            stateRecordNewOverwritableRecordLocked = newOverwritableRecordLocked(stateRecord, stateObject);
        }
        stateRecordNewOverwritableRecordLocked.setSnapshotId$runtime_release(id);
        if (stateRecord2.getSnapshotId$runtime_release() != 1) {
            snapshot.mo1058recordModified$runtime_release(stateObject);
        }
        return stateRecordNewOverwritableRecordLocked;
    }

    private static final StateRecord newWritableRecordLocked(StateRecord stateRecord, StateObject stateObject, Snapshot snapshot) {
        StateRecord stateRecordNewOverwritableRecordLocked = newOverwritableRecordLocked(stateRecord, stateObject);
        stateRecordNewOverwritableRecordLocked.assign(stateRecord);
        stateRecordNewOverwritableRecordLocked.setSnapshotId$runtime_release(snapshot.getId());
        return stateRecordNewOverwritableRecordLocked;
    }

    public static final StateRecord newOverwritableRecordLocked(StateRecord stateRecord, StateObject stateObject) {
        StateRecord stateRecordUsedLocked = usedLocked(stateObject);
        if (stateRecordUsedLocked != null) {
            stateRecordUsedLocked.setSnapshotId$runtime_release(Integer.MAX_VALUE);
            return stateRecordUsedLocked;
        }
        StateRecord stateRecordCreate = stateRecord.create();
        stateRecordCreate.setSnapshotId$runtime_release(Integer.MAX_VALUE);
        stateRecordCreate.setNext$runtime_release(stateObject.getFirstStateRecord());
        Intrinsics.checkNotNull(stateRecordCreate, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.newOverwritableRecordLocked$lambda$16");
        stateObject.prependStateRecord(stateRecordCreate);
        Intrinsics.checkNotNull(stateRecordCreate, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.newOverwritableRecordLocked");
        return stateRecordCreate;
    }

    public static final void notifyWrite(Snapshot snapshot, StateObject stateObject) {
        snapshot.setWriteCount$runtime_release(snapshot.getWriteCount$runtime_release() + 1);
        Function1 writeObserver$runtime_release = snapshot.getWriteObserver$runtime_release();
        if (writeObserver$runtime_release != null) {
            writeObserver$runtime_release.invoke(stateObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map optimisticMerges(MutableSnapshot mutableSnapshot, MutableSnapshot mutableSnapshot2, SnapshotIdSet snapshotIdSet) {
        long[] jArr;
        int i;
        HashMap map;
        long[] jArr2;
        int i2;
        HashMap map2;
        int i3;
        StateRecord stateRecord;
        MutableScatterSet modified$runtime_release = mutableSnapshot2.getModified$runtime_release();
        int id = mutableSnapshot.getId();
        HashMap map3 = null;
        if (modified$runtime_release == null) {
            return null;
        }
        SnapshotIdSet snapshotIdSetOr = mutableSnapshot2.getInvalid$runtime_release().set(mutableSnapshot2.getId()).or(mutableSnapshot2.getPreviousIds$runtime_release());
        Object[] objArr = modified$runtime_release.elements;
        long[] jArr3 = modified$runtime_release.metadata;
        int length = jArr3.length - 2;
        if (length >= 0) {
            HashMap map4 = null;
            int i4 = 0;
            while (true) {
                long j = jArr3[i4];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8;
                    int i6 = 8 - ((~(i4 - length)) >>> 31);
                    int i7 = 0;
                    while (i7 < i6) {
                        if ((255 & j) < 128) {
                            StateObject stateObject = (StateObject) objArr[(i4 << 3) + i7];
                            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                            StateRecord stateRecord2 = readable(firstStateRecord, id, snapshotIdSet);
                            if (stateRecord2 == null || (stateRecord = readable(firstStateRecord, id, snapshotIdSetOr)) == null || Intrinsics.areEqual(stateRecord2, stateRecord)) {
                                jArr2 = jArr3;
                                i2 = id;
                            } else {
                                jArr2 = jArr3;
                                i2 = id;
                                StateRecord stateRecord3 = readable(firstStateRecord, mutableSnapshot2.getId(), mutableSnapshot2.getInvalid$runtime_release());
                                if (stateRecord3 == null) {
                                    readError();
                                    throw new KotlinNothingValueException();
                                }
                                StateRecord stateRecordMergeRecords = stateObject.mergeRecords(stateRecord, stateRecord2, stateRecord3);
                                if (stateRecordMergeRecords == null) {
                                    return null;
                                }
                                if (map4 == null) {
                                    map4 = new HashMap();
                                }
                                map4.put(stateRecord2, stateRecordMergeRecords);
                                map4 = map4;
                            }
                            map2 = null;
                            i3 = 8;
                        } else {
                            jArr2 = jArr3;
                            i2 = id;
                            map2 = map3;
                            i3 = i5;
                        }
                        j >>= i3;
                        i7++;
                        map3 = map2;
                        i5 = i3;
                        jArr3 = jArr2;
                        id = i2;
                    }
                    jArr = jArr3;
                    i = id;
                    map = map3;
                    if (i6 != i5) {
                        return map4;
                    }
                } else {
                    jArr = jArr3;
                    i = id;
                    map = map3;
                }
                if (i4 == length) {
                    map3 = map4;
                    break;
                }
                i4++;
                map3 = map;
                jArr3 = jArr;
                id = i;
            }
        }
        return map3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void reportReadonlySnapshotWrite() {
        throw new IllegalStateException("Cannot modify a state object in a read-only snapshot");
    }

    public static final StateRecord current(StateRecord stateRecord, Snapshot snapshot) {
        StateRecord stateRecord2 = readable(stateRecord, snapshot.getId(), snapshot.getInvalid$runtime_release());
        if (stateRecord2 != null) {
            return stateRecord2;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    public static final StateRecord current(StateRecord stateRecord) {
        StateRecord stateRecord2;
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot current = companion.getCurrent();
        StateRecord stateRecord3 = readable(stateRecord, current.getId(), current.getInvalid$runtime_release());
        if (stateRecord3 != null) {
            return stateRecord3;
        }
        synchronized (getLock()) {
            Snapshot current2 = companion.getCurrent();
            stateRecord2 = readable(stateRecord, current2.getId(), current2.getInvalid$runtime_release());
        }
        if (stateRecord2 != null) {
            return stateRecord2;
        }
        readError();
        throw new KotlinNothingValueException();
    }

    public static final SnapshotIdSet addRange(SnapshotIdSet snapshotIdSet, int i, int i2) {
        while (i < i2) {
            snapshotIdSet = snapshotIdSet.set(i);
            i++;
        }
        return snapshotIdSet;
    }
}
