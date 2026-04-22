package androidx.compose.runtime;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.StateRecord;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DerivedState.kt */
/* JADX INFO: loaded from: classes.dex */
final class DerivedSnapshotState extends StateObjectImpl implements DerivedState {
    private final Function0 calculation;
    private ResultRecord first = new ResultRecord();
    private final SnapshotMutationPolicy policy;

    @Override // androidx.compose.runtime.DerivedState
    public SnapshotMutationPolicy getPolicy() {
        return this.policy;
    }

    public DerivedSnapshotState(Function0 function0, SnapshotMutationPolicy snapshotMutationPolicy) {
        this.calculation = function0;
        this.policy = snapshotMutationPolicy;
    }

    /* JADX INFO: compiled from: DerivedState.kt */
    public static final class ResultRecord extends StateRecord implements DerivedState.Record {
        private ObjectIntMap dependencies = ObjectIntMapKt.emptyObjectIntMap();
        private Object result = Unset;
        private int resultHash;
        private int validSnapshotId;
        private int validSnapshotWriteCount;
        public static final Companion Companion = new Companion(null);
        public static final int $stable = 8;
        private static final Object Unset = new Object();

        /* JADX INFO: compiled from: DerivedState.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Object getUnset() {
                return ResultRecord.Unset;
            }
        }

        public final void setValidSnapshotId(int i) {
            this.validSnapshotId = i;
        }

        public final void setValidSnapshotWriteCount(int i) {
            this.validSnapshotWriteCount = i;
        }

        @Override // androidx.compose.runtime.DerivedState.Record
        public ObjectIntMap getDependencies() {
            return this.dependencies;
        }

        public void setDependencies(ObjectIntMap objectIntMap) {
            this.dependencies = objectIntMap;
        }

        public final Object getResult() {
            return this.result;
        }

        public final void setResult(Object obj) {
            this.result = obj;
        }

        public final void setResultHash(int i) {
            this.resultHash = i;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord stateRecord) {
            Intrinsics.checkNotNull(stateRecord, "null cannot be cast to non-null type androidx.compose.runtime.DerivedSnapshotState.ResultRecord<T of androidx.compose.runtime.DerivedSnapshotState.ResultRecord>");
            ResultRecord resultRecord = (ResultRecord) stateRecord;
            setDependencies(resultRecord.getDependencies());
            this.result = resultRecord.result;
            this.resultHash = resultRecord.resultHash;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new ResultRecord();
        }

        @Override // androidx.compose.runtime.DerivedState.Record
        public Object getCurrentValue() {
            return this.result;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean isValid(androidx.compose.runtime.DerivedState r6, androidx.compose.runtime.snapshots.Snapshot r7) {
            /*
                r5 = this;
                java.lang.Object r0 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
                monitor-enter(r0)
                int r1 = r5.validSnapshotId     // Catch: java.lang.Throwable -> L1a
                int r2 = r7.getId()     // Catch: java.lang.Throwable -> L1a
                r3 = 1
                r4 = 0
                if (r1 != r2) goto L1c
                int r1 = r5.validSnapshotWriteCount     // Catch: java.lang.Throwable -> L1a
                int r2 = r7.getWriteCount$runtime_release()     // Catch: java.lang.Throwable -> L1a
                if (r1 == r2) goto L18
                goto L1c
            L18:
                r1 = r4
                goto L1d
            L1a:
                r6 = move-exception
                goto L4d
            L1c:
                r1 = r3
            L1d:
                monitor-exit(r0)
                java.lang.Object r0 = r5.result
                java.lang.Object r2 = androidx.compose.runtime.DerivedSnapshotState.ResultRecord.Unset
                if (r0 == r2) goto L2f
                if (r1 == 0) goto L30
                int r0 = r5.resultHash
                int r6 = r5.readableHash(r6, r7)
                if (r0 != r6) goto L2f
                goto L30
            L2f:
                r3 = r4
            L30:
                if (r3 == 0) goto L4c
                if (r1 == 0) goto L4c
                java.lang.Object r6 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
                monitor-enter(r6)
                int r0 = r7.getId()     // Catch: java.lang.Throwable -> L49
                r5.validSnapshotId = r0     // Catch: java.lang.Throwable -> L49
                int r7 = r7.getWriteCount$runtime_release()     // Catch: java.lang.Throwable -> L49
                r5.validSnapshotWriteCount = r7     // Catch: java.lang.Throwable -> L49
                kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L49
                monitor-exit(r6)
                goto L4c
            L49:
                r7 = move-exception
                monitor-exit(r6)
                throw r7
            L4c:
                return r3
            L4d:
                monitor-exit(r0)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.DerivedSnapshotState.ResultRecord.isValid(androidx.compose.runtime.DerivedState, androidx.compose.runtime.snapshots.Snapshot):boolean");
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x00a4 A[Catch: all -> 0x007c, LOOP:1: B:16:0x003c->B:40:0x00a4, LOOP_END, TryCatch #1 {all -> 0x007c, blocks: (B:13:0x002f, B:16:0x003c, B:18:0x004c, B:20:0x0058, B:22:0x0062, B:34:0x0098, B:25:0x0071, B:27:0x0075, B:31:0x0086, B:30:0x007e, B:42:0x00a8, B:40:0x00a4), top: B:64:0x002f }] */
        /* JADX WARN: Removed duplicated region for block: B:68:0x00a0 A[EDGE_INSN: B:68:0x00a0->B:38:0x00a0 BREAK  A[LOOP:1: B:16:0x003c->B:40:0x00a4], SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int readableHash(androidx.compose.runtime.DerivedState r22, androidx.compose.runtime.snapshots.Snapshot r23) {
            /*
                Method dump skipped, instruction units count: 221
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.DerivedSnapshotState.ResultRecord.readableHash(androidx.compose.runtime.DerivedState, androidx.compose.runtime.snapshots.Snapshot):int");
        }
    }

    public final StateRecord current(Snapshot snapshot) {
        return currentRecord((ResultRecord) SnapshotKt.current(this.first, snapshot), snapshot, false, this.calculation);
    }

    /* JADX WARN: Finally extract failed */
    private final ResultRecord currentRecord(ResultRecord resultRecord, Snapshot snapshot, boolean z, Function0 function0) {
        Snapshot.Companion companion;
        SnapshotMutationPolicy policy;
        int i;
        int i2;
        ResultRecord resultRecord2 = resultRecord;
        int i3 = 1;
        if (!resultRecord2.isValid(this, snapshot)) {
            int i4 = 0;
            final MutableObjectIntMap mutableObjectIntMap = new MutableObjectIntMap(0, 1, null);
            final IntRef intRef = (IntRef) SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
            if (intRef == null) {
                intRef = new IntRef(0);
                SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.set(intRef);
            }
            final int element = intRef.getElement();
            MutableVector mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            int size = mutableVectorDerivedStateObservers.getSize();
            if (size > 0) {
                Object[] content = mutableVectorDerivedStateObservers.getContent();
                int i5 = 0;
                while (true) {
                    ((DerivedStateObserver) content[i5]).start(this);
                    int i6 = i5 + 1;
                    if (i6 >= size) {
                        break;
                    }
                    i5 = i6;
                }
            }
            try {
                intRef.setElement(element + 1);
                Object objObserve = Snapshot.Companion.observe(new Function1() { // from class: androidx.compose.runtime.DerivedSnapshotState$currentRecord$result$1$1$result$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        m1012invoke(obj);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m1012invoke(Object obj) {
                        if (obj == this.this$0) {
                            throw new IllegalStateException("A derived state calculation cannot read itself");
                        }
                        if (obj instanceof StateObject) {
                            int element2 = intRef.getElement();
                            MutableObjectIntMap mutableObjectIntMap2 = mutableObjectIntMap;
                            mutableObjectIntMap2.set(obj, Math.min(element2 - element, mutableObjectIntMap2.getOrDefault(obj, Integer.MAX_VALUE)));
                        }
                    }
                }, null, function0);
                intRef.setElement(element);
                int size2 = mutableVectorDerivedStateObservers.getSize();
                if (size2 > 0) {
                    Object[] content2 = mutableVectorDerivedStateObservers.getContent();
                    do {
                        ((DerivedStateObserver) content2[i4]).done(this);
                        i4++;
                    } while (i4 < size2);
                }
                synchronized (SnapshotKt.getLock()) {
                    try {
                        companion = Snapshot.Companion;
                        Snapshot current = companion.getCurrent();
                        if (resultRecord.getResult() == ResultRecord.Companion.getUnset() || (policy = getPolicy()) == null || !policy.equivalent(objObserve, resultRecord.getResult())) {
                            resultRecord2 = (ResultRecord) SnapshotKt.newWritableRecord(this.first, this, current);
                            resultRecord2.setDependencies(mutableObjectIntMap);
                            resultRecord2.setResultHash(resultRecord2.readableHash(this, current));
                            resultRecord2.setResult(objObserve);
                        } else {
                            resultRecord2.setDependencies(mutableObjectIntMap);
                            resultRecord2.setResultHash(resultRecord2.readableHash(this, current));
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                IntRef intRef2 = (IntRef) SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
                if (intRef2 != null && intRef2.getElement() == 0) {
                    companion.notifyObjectsInitialized();
                    synchronized (SnapshotKt.getLock()) {
                        Snapshot current2 = companion.getCurrent();
                        resultRecord2.setValidSnapshotId(current2.getId());
                        resultRecord2.setValidSnapshotWriteCount(current2.getWriteCount$runtime_release());
                        Unit unit = Unit.INSTANCE;
                    }
                }
                return resultRecord2;
            } catch (Throwable th2) {
                int size3 = mutableVectorDerivedStateObservers.getSize();
                if (size3 > 0) {
                    Object[] content3 = mutableVectorDerivedStateObservers.getContent();
                    do {
                        ((DerivedStateObserver) content3[i4]).done(this);
                        i4++;
                    } while (i4 < size3);
                }
                throw th2;
            }
        }
        if (z) {
            MutableVector mutableVectorDerivedStateObservers2 = SnapshotStateKt.derivedStateObservers();
            int size4 = mutableVectorDerivedStateObservers2.getSize();
            if (size4 > 0) {
                Object[] content4 = mutableVectorDerivedStateObservers2.getContent();
                int i7 = 0;
                do {
                    ((DerivedStateObserver) content4[i7]).start(this);
                    i7++;
                } while (i7 < size4);
            }
            try {
                ObjectIntMap dependencies = resultRecord.getDependencies();
                IntRef intRef3 = (IntRef) SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
                if (intRef3 == null) {
                    intRef3 = new IntRef(0);
                    SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.set(intRef3);
                }
                int element2 = intRef3.getElement();
                Object[] objArr = dependencies.keys;
                int[] iArr = dependencies.values;
                long[] jArr = dependencies.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i8 = 0;
                    while (true) {
                        long j = jArr[i8];
                        long[] jArr2 = jArr;
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i9 = 8;
                            int i10 = 8 - ((~(i8 - length)) >>> 31);
                            int i11 = 0;
                            while (i11 < i10) {
                                if ((j & 255) < 128) {
                                    int i12 = (i8 << 3) + i11;
                                    StateObject stateObject = (StateObject) objArr[i12];
                                    intRef3.setElement(element2 + iArr[i12]);
                                    Function1 readObserver = snapshot.getReadObserver();
                                    if (readObserver != null) {
                                        readObserver.invoke(stateObject);
                                    }
                                    i2 = 8;
                                } else {
                                    i2 = i9;
                                }
                                j >>= i2;
                                i11++;
                                i9 = i2;
                                i3 = 1;
                            }
                            int i13 = i9;
                            i = i3;
                            if (i10 != i13) {
                                break;
                            }
                        } else {
                            i = i3;
                        }
                        if (i8 == length) {
                            break;
                        }
                        i8 += i;
                        i3 = i;
                        jArr = jArr2;
                    }
                }
                intRef3.setElement(element2);
                Unit unit2 = Unit.INSTANCE;
                int size5 = mutableVectorDerivedStateObservers2.getSize();
                if (size5 > 0) {
                    Object[] content5 = mutableVectorDerivedStateObservers2.getContent();
                    int i14 = 0;
                    do {
                        ((DerivedStateObserver) content5[i14]).done(this);
                        i14++;
                    } while (i14 < size5);
                }
            } catch (Throwable th3) {
                int size6 = mutableVectorDerivedStateObservers2.getSize();
                if (size6 > 0) {
                    Object[] content6 = mutableVectorDerivedStateObservers2.getContent();
                    int i15 = 0;
                    do {
                        ((DerivedStateObserver) content6[i15]).done(this);
                        i15++;
                    } while (i15 < size6);
                }
                throw th3;
            }
        }
        return resultRecord2;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.first;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord stateRecord) {
        Intrinsics.checkNotNull(stateRecord, "null cannot be cast to non-null type androidx.compose.runtime.DerivedSnapshotState.ResultRecord<T of androidx.compose.runtime.DerivedSnapshotState>");
        this.first = (ResultRecord) stateRecord;
    }

    @Override // androidx.compose.runtime.State
    public Object getValue() {
        Snapshot.Companion companion = Snapshot.Companion;
        Function1 readObserver = companion.getCurrent().getReadObserver();
        if (readObserver != null) {
            readObserver.invoke(this);
        }
        Snapshot current = companion.getCurrent();
        return currentRecord((ResultRecord) SnapshotKt.current(this.first, current), current, true, this.calculation).getResult();
    }

    @Override // androidx.compose.runtime.DerivedState
    public DerivedState.Record getCurrentRecord() {
        Snapshot current = Snapshot.Companion.getCurrent();
        return currentRecord((ResultRecord) SnapshotKt.current(this.first, current), current, false, this.calculation);
    }

    public String toString() {
        return "DerivedState(value=" + displayValue() + ")@" + hashCode();
    }

    private final String displayValue() {
        ResultRecord resultRecord = (ResultRecord) SnapshotKt.current(this.first);
        if (resultRecord.isValid(this, Snapshot.Companion.getCurrent())) {
            return String.valueOf(resultRecord.getResult());
        }
        return "<Not calculated>";
    }
}
