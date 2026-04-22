package androidx.compose.runtime.snapshots;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.DerivedStateObserver;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: SnapshotStateObserver.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SnapshotStateObserver {
    public static final int $stable = 8;
    private ObserverHandle applyUnsubscribe;
    private ObservedScopeMap currentMap;
    private boolean isPaused;
    private final Function1 onChangedExecutor;
    private boolean sendingNotifications;
    private final AtomicReference pendingChanges = new AtomicReference(null);
    private final Function2 applyObserver = new Function2() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$applyObserver$1
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Set) obj, (Snapshot) obj2);
            return Unit.INSTANCE;
        }

        public final void invoke(Set set, Snapshot snapshot) {
            this.this$0.addChanges(set);
            if (this.this$0.drainChanges()) {
                this.this$0.sendNotifications();
            }
        }
    };
    private final Function1 readObserver = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$readObserver$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m1062invoke(obj);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1062invoke(Object obj) {
            if (this.this$0.isPaused) {
                return;
            }
            MutableVector mutableVector = this.this$0.observedScopeMaps;
            SnapshotStateObserver snapshotStateObserver = this.this$0;
            synchronized (mutableVector) {
                SnapshotStateObserver.ObservedScopeMap observedScopeMap = snapshotStateObserver.currentMap;
                Intrinsics.checkNotNull(observedScopeMap);
                observedScopeMap.recordRead(obj);
                Unit unit = Unit.INSTANCE;
            }
        }
    };
    private final MutableVector observedScopeMaps = new MutableVector(new ObservedScopeMap[16], 0);
    private long currentMapThreadId = -1;

    public SnapshotStateObserver(Function1 function1) {
        this.onChangedExecutor = function1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean drainChanges() {
        boolean z;
        synchronized (this.observedScopeMaps) {
            z = this.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean z2 = false;
        while (true) {
            Set setRemoveChanges = removeChanges();
            if (setRemoveChanges == null) {
                return z2;
            }
            synchronized (this.observedScopeMaps) {
                try {
                    MutableVector mutableVector = this.observedScopeMaps;
                    int size = mutableVector.getSize();
                    if (size > 0) {
                        Object[] content = mutableVector.getContent();
                        int i = 0;
                        do {
                            z2 = ((ObservedScopeMap) content[i]).recordInvalidation(setRemoveChanges) || z2;
                            i++;
                        } while (i < size);
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendNotifications() {
        this.onChangedExecutor.invoke(new Function0() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver.sendNotifications.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m1063invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1063invoke() {
                do {
                    MutableVector mutableVector = SnapshotStateObserver.this.observedScopeMaps;
                    SnapshotStateObserver snapshotStateObserver = SnapshotStateObserver.this;
                    synchronized (mutableVector) {
                        try {
                            if (!snapshotStateObserver.sendingNotifications) {
                                snapshotStateObserver.sendingNotifications = true;
                                try {
                                    MutableVector mutableVector2 = snapshotStateObserver.observedScopeMaps;
                                    int size = mutableVector2.getSize();
                                    if (size > 0) {
                                        Object[] content = mutableVector2.getContent();
                                        int i = 0;
                                        do {
                                            ((ObservedScopeMap) content[i]).notifyInvalidatedScopes();
                                            i++;
                                        } while (i < size);
                                    }
                                    snapshotStateObserver.sendingNotifications = false;
                                } finally {
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } while (SnapshotStateObserver.this.drainChanges());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void addChanges(Set set) {
        Object obj;
        Set setPlus;
        do {
            obj = this.pendingChanges.get();
            if (obj == null) {
                setPlus = set;
            } else if (obj instanceof Set) {
                setPlus = CollectionsKt.listOf((Object[]) new Set[]{obj, set});
            } else {
                if (!(obj instanceof List)) {
                    report();
                    throw new KotlinNothingValueException();
                }
                setPlus = CollectionsKt.plus((Collection) obj, (Iterable) CollectionsKt.listOf(set));
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, obj, setPlus));
    }

    private final Set removeChanges() {
        Object obj;
        Object objSubList;
        Set set;
        do {
            obj = this.pendingChanges.get();
            objSubList = null;
            if (obj == null) {
                return null;
            }
            if (obj instanceof Set) {
                set = (Set) obj;
            } else if (obj instanceof List) {
                List list = (List) obj;
                Set set2 = (Set) list.get(0);
                if (list.size() == 2) {
                    objSubList = list.get(1);
                } else if (list.size() > 2) {
                    objSubList = list.subList(1, list.size());
                }
                set = set2;
            } else {
                report();
                throw new KotlinNothingValueException();
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, obj, objSubList));
        return set;
    }

    private final Void report() {
        ComposerKt.composeRuntimeError("Unexpected notification");
        throw new KotlinNothingValueException();
    }

    public final void clear() {
        synchronized (this.observedScopeMaps) {
            try {
                MutableVector mutableVector = this.observedScopeMaps;
                int size = mutableVector.getSize();
                if (size > 0) {
                    Object[] content = mutableVector.getContent();
                    int i = 0;
                    do {
                        ((ObservedScopeMap) content[i]).clear();
                        i++;
                    } while (i < size);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void clearIf(Function1 function1) {
        synchronized (this.observedScopeMaps) {
            try {
                MutableVector mutableVector = this.observedScopeMaps;
                int size = mutableVector.getSize();
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    ObservedScopeMap observedScopeMap = (ObservedScopeMap) mutableVector.getContent()[i2];
                    observedScopeMap.removeScopeIf(function1);
                    if (!observedScopeMap.hasScopeObservations()) {
                        i++;
                    } else if (i > 0) {
                        mutableVector.getContent()[i2 - i] = mutableVector.getContent()[i2];
                    }
                }
                int i3 = size - i;
                ArraysKt.fill(mutableVector.getContent(), (Object) null, i3, size);
                mutableVector.setSize(i3);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void observeReads(Object obj, Function1 function1, Function0 function0) {
        ObservedScopeMap observedScopeMapEnsureMap;
        synchronized (this.observedScopeMaps) {
            observedScopeMapEnsureMap = ensureMap(function1);
        }
        boolean z = this.isPaused;
        ObservedScopeMap observedScopeMap = this.currentMap;
        long j = this.currentMapThreadId;
        if (j != -1) {
            if (!(j == ActualJvm_jvmKt.currentThreadId())) {
                PreconditionsKt.throwIllegalArgumentException("Detected multithreaded access to SnapshotStateObserver: previousThreadId=" + j + "), currentThread={id=" + ActualJvm_jvmKt.currentThreadId() + ", name=" + ActualJvm_jvmKt.currentThreadName() + "}. Note that observation on multiple threads in layout/draw is not supported. Make sure your measure/layout/draw for each Owner (AndroidComposeView) is executed on the same thread.");
            }
        }
        try {
            this.isPaused = false;
            this.currentMap = observedScopeMapEnsureMap;
            this.currentMapThreadId = ActualJvm_jvmKt.currentThreadId();
            observedScopeMapEnsureMap.observe(obj, this.readObserver, function0);
        } finally {
            this.currentMap = observedScopeMap;
            this.isPaused = z;
            this.currentMapThreadId = j;
        }
    }

    public final void start() {
        this.applyUnsubscribe = Snapshot.Companion.registerApplyObserver(this.applyObserver);
    }

    public final void stop() {
        ObserverHandle observerHandle = this.applyUnsubscribe;
        if (observerHandle != null) {
            observerHandle.dispose();
        }
    }

    private final ObservedScopeMap ensureMap(Function1 function1) {
        Object obj;
        MutableVector mutableVector = this.observedScopeMaps;
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                obj = content[i];
                if (((ObservedScopeMap) obj).getOnChanged() == function1) {
                    break;
                }
                i++;
            } while (i < size);
            obj = null;
        } else {
            obj = null;
        }
        ObservedScopeMap observedScopeMap = (ObservedScopeMap) obj;
        if (observedScopeMap != null) {
            return observedScopeMap;
        }
        Intrinsics.checkNotNull(function1, "null cannot be cast to non-null type kotlin.Function1<kotlin.Any, kotlin.Unit>");
        ObservedScopeMap observedScopeMap2 = new ObservedScopeMap((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1));
        this.observedScopeMaps.add(observedScopeMap2);
        return observedScopeMap2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SnapshotStateObserver.kt */
    static final class ObservedScopeMap {
        private Object currentScope;
        private MutableObjectIntMap currentScopeReads;
        private int deriveStateScopeCount;
        private final Function1 onChanged;
        private int currentToken = -1;
        private final ScopeMap valueToScopes = new ScopeMap();
        private final MutableScatterMap scopeToValues = new MutableScatterMap(0, 1, null);
        private final MutableScatterSet invalidated = new MutableScatterSet(0, 1, null);
        private final MutableVector statesToReread = new MutableVector(new DerivedState[16], 0);
        private final DerivedStateObserver derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState derivedState) {
                this.this$0.deriveStateScopeCount++;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState derivedState) {
                SnapshotStateObserver.ObservedScopeMap observedScopeMap = this.this$0;
                observedScopeMap.deriveStateScopeCount--;
            }
        };
        private final ScopeMap dependencyToDerivedStates = new ScopeMap();
        private final HashMap recordedDerivedStateValues = new HashMap();

        public ObservedScopeMap(Function1 function1) {
            this.onChanged = function1;
        }

        public final Function1 getOnChanged() {
            return this.onChanged;
        }

        public final void recordRead(Object obj) {
            Object obj2 = this.currentScope;
            Intrinsics.checkNotNull(obj2);
            int i = this.currentToken;
            MutableObjectIntMap mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                mutableObjectIntMap = new MutableObjectIntMap(0, 1, null);
                this.currentScopeReads = mutableObjectIntMap;
                this.scopeToValues.set(obj2, mutableObjectIntMap);
                Unit unit = Unit.INSTANCE;
            }
            recordRead(obj, i, obj2, mutableObjectIntMap);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0089  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final void recordRead(java.lang.Object r21, int r22, java.lang.Object r23, androidx.collection.MutableObjectIntMap r24) {
            /*
                r20 = this;
                r0 = r20
                r1 = r21
                r2 = r22
                int r3 = r0.deriveStateScopeCount
                if (r3 <= 0) goto Lb
                return
            Lb:
                r3 = -1
                r4 = r24
                int r4 = r4.put(r1, r2, r3)
                boolean r5 = r1 instanceof androidx.compose.runtime.DerivedState
                r6 = 2
                if (r5 == 0) goto L91
                if (r4 == r2) goto L91
                r2 = r1
                androidx.compose.runtime.DerivedState r2 = (androidx.compose.runtime.DerivedState) r2
                androidx.compose.runtime.DerivedState$Record r2 = r2.getCurrentRecord()
                java.util.HashMap r5 = r0.recordedDerivedStateValues
                java.lang.Object r7 = r2.getCurrentValue()
                r5.put(r1, r7)
                androidx.collection.ObjectIntMap r2 = r2.getDependencies()
                androidx.compose.runtime.collection.ScopeMap r5 = r0.dependencyToDerivedStates
                r5.removeScope(r1)
                java.lang.Object[] r7 = r2.keys
                long[] r2 = r2.metadata
                int r8 = r2.length
                int r8 = r8 - r6
                if (r8 < 0) goto L8f
                r10 = 0
            L3b:
                r11 = r2[r10]
                long r13 = ~r11
                r15 = 7
                long r13 = r13 << r15
                long r13 = r13 & r11
                r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
                long r13 = r13 & r15
                int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
                if (r13 == 0) goto L89
                int r13 = r10 - r8
                int r13 = ~r13
                int r13 = r13 >>> 31
                r14 = 8
                int r13 = 8 - r13
                r15 = 0
            L55:
                if (r15 >= r13) goto L86
                r16 = 255(0xff, double:1.26E-321)
                long r16 = r11 & r16
                r18 = 128(0x80, double:6.3E-322)
                int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
                if (r16 >= 0) goto L7f
                int r16 = r10 << 3
                int r16 = r16 + r15
                r16 = r7[r16]
                r9 = r16
                androidx.compose.runtime.snapshots.StateObject r9 = (androidx.compose.runtime.snapshots.StateObject) r9
                boolean r3 = r9 instanceof androidx.compose.runtime.snapshots.StateObjectImpl
                if (r3 == 0) goto L79
                r3 = r9
                androidx.compose.runtime.snapshots.StateObjectImpl r3 = (androidx.compose.runtime.snapshots.StateObjectImpl) r3
                int r14 = androidx.compose.runtime.snapshots.ReaderKind.m1059constructorimpl(r6)
                r3.m1065recordReadInh_f27i8$runtime_release(r14)
            L79:
                r5.add(r9, r1)
                r3 = 8
                goto L80
            L7f:
                r3 = r14
            L80:
                long r11 = r11 >> r3
                int r15 = r15 + 1
                r14 = r3
                r3 = -1
                goto L55
            L86:
                r3 = r14
                if (r13 != r3) goto L8f
            L89:
                if (r10 == r8) goto L8f
                int r10 = r10 + 1
                r3 = -1
                goto L3b
            L8f:
                r2 = -1
                goto L92
            L91:
                r2 = r3
            L92:
                if (r4 != r2) goto La9
                boolean r2 = r1 instanceof androidx.compose.runtime.snapshots.StateObjectImpl
                if (r2 == 0) goto La2
                r2 = r1
                androidx.compose.runtime.snapshots.StateObjectImpl r2 = (androidx.compose.runtime.snapshots.StateObjectImpl) r2
                int r3 = androidx.compose.runtime.snapshots.ReaderKind.m1059constructorimpl(r6)
                r2.m1065recordReadInh_f27i8$runtime_release(r3)
            La2:
                androidx.compose.runtime.collection.ScopeMap r2 = r0.valueToScopes
                r3 = r23
                r2.add(r1, r3)
            La9:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.recordRead(java.lang.Object, int, java.lang.Object, androidx.collection.MutableObjectIntMap):void");
        }

        public final void observe(Object obj, Function1 function1, Function0 function0) {
            Object obj2 = this.currentScope;
            MutableObjectIntMap mutableObjectIntMap = this.currentScopeReads;
            int i = this.currentToken;
            this.currentScope = obj;
            this.currentScopeReads = (MutableObjectIntMap) this.scopeToValues.get(obj);
            if (this.currentToken == -1) {
                this.currentToken = SnapshotKt.currentSnapshot().getId();
            }
            DerivedStateObserver derivedStateObserver = this.derivedStateObserver;
            MutableVector mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            try {
                mutableVectorDerivedStateObservers.add(derivedStateObserver);
                Snapshot.Companion.observe(function1, null, function0);
                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                Object obj3 = this.currentScope;
                Intrinsics.checkNotNull(obj3);
                clearObsoleteStateReads(obj3);
                this.currentScope = obj2;
                this.currentScopeReads = mutableObjectIntMap;
                this.currentToken = i;
            } catch (Throwable th) {
                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                throw th;
            }
        }

        private final void clearObsoleteStateReads(Object obj) {
            int i = this.currentToken;
            MutableObjectIntMap mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                return;
            }
            long[] jArr = mutableObjectIntMap.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i2 = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128) {
                            int i5 = (i2 << 3) + i4;
                            Object obj2 = mutableObjectIntMap.keys[i5];
                            boolean z = mutableObjectIntMap.values[i5] != i;
                            if (z) {
                                removeObservation(obj, obj2);
                            }
                            if (z) {
                                mutableObjectIntMap.removeValueAt(i5);
                            }
                        }
                        j >>= 8;
                    }
                    if (i3 != 8) {
                        return;
                    }
                }
                if (i2 == length) {
                    return;
                } else {
                    i2++;
                }
            }
        }

        public final void removeScopeIf(Function1 function1) {
            long[] jArr;
            int i;
            long[] jArr2;
            int i2;
            long j;
            int i3;
            long j2;
            int i4;
            MutableScatterMap mutableScatterMap = this.scopeToValues;
            long[] jArr3 = mutableScatterMap.metadata;
            int length = jArr3.length - 2;
            if (length < 0) {
                return;
            }
            int i5 = 0;
            while (true) {
                long j3 = jArr3[i5];
                long j4 = -9187201950435737472L;
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8;
                    int i7 = 8 - ((~(i5 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((j3 & 255) < 128) {
                            int i9 = (i5 << 3) + i8;
                            Object obj = mutableScatterMap.keys[i9];
                            MutableObjectIntMap mutableObjectIntMap = (MutableObjectIntMap) mutableScatterMap.values[i9];
                            Boolean bool = (Boolean) function1.invoke(obj);
                            if (bool.booleanValue()) {
                                Object[] objArr = mutableObjectIntMap.keys;
                                int[] iArr = mutableObjectIntMap.values;
                                long[] jArr4 = mutableObjectIntMap.metadata;
                                int length2 = jArr4.length - 2;
                                jArr2 = jArr3;
                                if (length2 >= 0) {
                                    i3 = i7;
                                    int i10 = 0;
                                    while (true) {
                                        long j5 = jArr4[i10];
                                        i2 = i5;
                                        j = j3;
                                        j2 = -9187201950435737472L;
                                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i11 = 8 - ((~(i10 - length2)) >>> 31);
                                            for (int i12 = 0; i12 < i11; i12++) {
                                                if ((j5 & 255) < 128) {
                                                    int i13 = (i10 << 3) + i12;
                                                    Object obj2 = objArr[i13];
                                                    int i14 = iArr[i13];
                                                    removeObservation(obj, obj2);
                                                }
                                                j5 >>= 8;
                                            }
                                            if (i11 != 8) {
                                                break;
                                            }
                                        }
                                        if (i10 == length2) {
                                            break;
                                        }
                                        i10++;
                                        i5 = i2;
                                        j3 = j;
                                    }
                                } else {
                                    i2 = i5;
                                    j = j3;
                                    i3 = i7;
                                    j2 = -9187201950435737472L;
                                }
                            } else {
                                jArr2 = jArr3;
                                i2 = i5;
                                j = j3;
                                i3 = i7;
                                j2 = j4;
                            }
                            if (bool.booleanValue()) {
                                mutableScatterMap.removeValueAt(i9);
                            }
                            i4 = 8;
                        } else {
                            jArr2 = jArr3;
                            i2 = i5;
                            j = j3;
                            i3 = i7;
                            j2 = j4;
                            i4 = i6;
                        }
                        j3 = j >> i4;
                        i8++;
                        i6 = i4;
                        j4 = j2;
                        jArr3 = jArr2;
                        i7 = i3;
                        i5 = i2;
                    }
                    jArr = jArr3;
                    int i15 = i5;
                    if (i7 != i6) {
                        return;
                    } else {
                        i = i15;
                    }
                } else {
                    jArr = jArr3;
                    i = i5;
                }
                if (i == length) {
                    return;
                }
                i5 = i + 1;
                jArr3 = jArr;
            }
        }

        public final boolean hasScopeObservations() {
            return this.scopeToValues.isNotEmpty();
        }

        private final void removeObservation(Object obj, Object obj2) {
            this.valueToScopes.remove(obj2, obj);
            if (!(obj2 instanceof DerivedState) || this.valueToScopes.contains(obj2)) {
                return;
            }
            this.dependencyToDerivedStates.removeScope(obj2);
            this.recordedDerivedStateValues.remove(obj2);
        }

        public final void clear() {
            this.valueToScopes.clear();
            this.scopeToValues.clear();
            this.dependencyToDerivedStates.clear();
            this.recordedDerivedStateValues.clear();
        }

        /*  JADX ERROR: Type inference failed with stack overflow
            jadx.core.utils.exceptions.JadxOverflowException
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
            */
        public final boolean recordInvalidation(java.util.Set r43) {
            /*
                Method dump skipped, instruction units count: 1699
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.recordInvalidation(java.util.Set):boolean");
        }

        public final void rereadDerivedState(DerivedState derivedState) {
            long[] jArr;
            long[] jArr2;
            int i;
            MutableObjectIntMap mutableObjectIntMap;
            MutableScatterMap mutableScatterMap = this.scopeToValues;
            int id = SnapshotKt.currentSnapshot().getId();
            Object obj = this.valueToScopes.getMap().get(derivedState);
            if (obj == null) {
                return;
            }
            if (obj instanceof MutableScatterSet) {
                MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
                Object[] objArr = mutableScatterSet.elements;
                long[] jArr3 = mutableScatterSet.metadata;
                int length = jArr3.length - 2;
                if (length < 0) {
                    return;
                }
                int i2 = 0;
                while (true) {
                    long j = jArr3[i2];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i3 = 8;
                        int i4 = 8 - ((~(i2 - length)) >>> 31);
                        int i5 = 0;
                        while (i5 < i4) {
                            if ((j & 255) < 128) {
                                Object obj2 = objArr[(i2 << 3) + i5];
                                MutableObjectIntMap mutableObjectIntMap2 = (MutableObjectIntMap) mutableScatterMap.get(obj2);
                                jArr2 = jArr3;
                                if (mutableObjectIntMap2 == null) {
                                    mutableObjectIntMap = new MutableObjectIntMap(0, 1, null);
                                    mutableScatterMap.set(obj2, mutableObjectIntMap);
                                    Unit unit = Unit.INSTANCE;
                                } else {
                                    mutableObjectIntMap = mutableObjectIntMap2;
                                }
                                recordRead(derivedState, id, obj2, mutableObjectIntMap);
                                i = 8;
                            } else {
                                jArr2 = jArr3;
                                i = i3;
                            }
                            j >>= i;
                            i5++;
                            i3 = i;
                            jArr3 = jArr2;
                        }
                        jArr = jArr3;
                        if (i4 != i3) {
                            return;
                        }
                    } else {
                        jArr = jArr3;
                    }
                    if (i2 == length) {
                        return;
                    }
                    i2++;
                    jArr3 = jArr;
                }
            } else {
                MutableObjectIntMap mutableObjectIntMap3 = (MutableObjectIntMap) mutableScatterMap.get(obj);
                if (mutableObjectIntMap3 == null) {
                    mutableObjectIntMap3 = new MutableObjectIntMap(0, 1, null);
                    mutableScatterMap.set(obj, mutableObjectIntMap3);
                    Unit unit2 = Unit.INSTANCE;
                }
                recordRead(derivedState, id, obj, mutableObjectIntMap3);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void notifyInvalidatedScopes() {
            /*
                r17 = this;
                r0 = r17
                androidx.collection.MutableScatterSet r1 = r0.invalidated
                kotlin.jvm.functions.Function1 r2 = r0.onChanged
                java.lang.Object[] r3 = r1.elements
                long[] r4 = r1.metadata
                int r5 = r4.length
                int r5 = r5 + (-2)
                if (r5 < 0) goto L49
                r6 = 0
                r7 = r6
            L11:
                r8 = r4[r7]
                long r10 = ~r8
                r12 = 7
                long r10 = r10 << r12
                long r10 = r10 & r8
                r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
                long r10 = r10 & r12
                int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                if (r10 == 0) goto L44
                int r10 = r7 - r5
                int r10 = ~r10
                int r10 = r10 >>> 31
                r11 = 8
                int r10 = 8 - r10
                r12 = r6
            L2b:
                if (r12 >= r10) goto L42
                r13 = 255(0xff, double:1.26E-321)
                long r13 = r13 & r8
                r15 = 128(0x80, double:6.3E-322)
                int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
                if (r13 >= 0) goto L3e
                int r13 = r7 << 3
                int r13 = r13 + r12
                r13 = r3[r13]
                r2.invoke(r13)
            L3e:
                long r8 = r8 >> r11
                int r12 = r12 + 1
                goto L2b
            L42:
                if (r10 != r11) goto L49
            L44:
                if (r7 == r5) goto L49
                int r7 = r7 + 1
                goto L11
            L49:
                r1.clear()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.notifyInvalidatedScopes():void");
        }
    }
}
