package androidx.compose.runtime;

import androidx.collection.MutableIntList;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterSetKt;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.tooling.CompositionObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CompositionImpl implements ControlledComposition, ReusableComposition, RecomposeScopeOwner {
    private final CoroutineContext _recomposeContext;
    private final Set abandonSet;
    private final Applier applier;
    private final ChangeList changes;
    private Function2 composable;
    private final ComposerImpl composer;
    private final MutableScatterSet conditionallyInvalidatedScopes;
    private final ScopeMap derivedStates;
    private boolean disposed;
    private final MutableScatterSet invalidatedScopes;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private ScopeMap invalidations;
    private final boolean isRoot;
    private final ChangeList lateChanges;
    private final Object lock;
    private final ScopeMap observations;
    private final ScopeMap observationsProcessed;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference pendingModifications;
    private final SlotTable slotTable;

    @Override // androidx.compose.runtime.ControlledComposition
    public void insertMovableContent(List list) {
        boolean z = true;
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            if (!Intrinsics.areEqual(((MovableContentStateReference) ((Pair) list.get(i)).getFirst()).getComposition$runtime_release(), this)) {
                z = false;
                break;
            }
            i++;
        }
        ComposerKt.runtimeCheck(z);
        try {
            this.composer.insertMovableContentReferences(list);
            Unit unit = Unit.INSTANCE;
        } finally {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0180 A[EDGE_INSN: B:69:0x0180->B:221:0x0111 BREAK  A[LOOP:13: B:59:0x0146->B:70:0x0182]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void addPendingInvalidationsLocked(java.util.Set r35, boolean r36) {
        /*
            Method dump skipped, instruction units count: 1132
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.addPendingInvalidationsLocked(java.util.Set, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0050, code lost:
    
        return true;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0057  */
    @Override // androidx.compose.runtime.ControlledComposition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean observesAnyOf(java.util.Set r15) {
        /*
            r14 = this;
            boolean r0 = r15 instanceof androidx.compose.runtime.collection.ScatterSetWrapper
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L5c
            androidx.compose.runtime.collection.ScatterSetWrapper r15 = (androidx.compose.runtime.collection.ScatterSetWrapper) r15
            androidx.collection.ScatterSet r15 = r15.getSet$runtime_release()
            java.lang.Object[] r0 = r15.elements
            long[] r15 = r15.metadata
            int r3 = r15.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L7d
            r4 = r1
        L16:
            r5 = r15[r4]
            long r7 = ~r5
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L57
            int r7 = r4 - r3
            int r7 = ~r7
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r1
        L30:
            if (r9 >= r7) goto L55
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.3E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L51
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r0[r10]
            androidx.compose.runtime.collection.ScopeMap r11 = r14.observations
            boolean r11 = r11.contains(r10)
            if (r11 != 0) goto L50
            androidx.compose.runtime.collection.ScopeMap r11 = r14.derivedStates
            boolean r10 = r11.contains(r10)
            if (r10 == 0) goto L51
        L50:
            return r2
        L51:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L30
        L55:
            if (r7 != r8) goto L7d
        L57:
            if (r4 == r3) goto L7d
            int r4 = r4 + 1
            goto L16
        L5c:
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.Iterator r15 = r15.iterator()
        L62:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L7d
            java.lang.Object r0 = r15.next()
            androidx.compose.runtime.collection.ScopeMap r3 = r14.observations
            boolean r3 = r3.contains(r0)
            if (r3 != 0) goto L7c
            androidx.compose.runtime.collection.ScopeMap r3 = r14.derivedStates
            boolean r0 = r3.contains(r0)
            if (r0 == 0) goto L62
        L7c:
            return r2
        L7d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.observesAnyOf(java.util.Set):boolean");
    }

    public CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext) {
        this.parent = compositionContext;
        this.applier = applier;
        this.pendingModifications = new AtomicReference(null);
        this.lock = new Object();
        Set setAsMutableSet = new MutableScatterSet(0, 1, null).asMutableSet();
        this.abandonSet = setAsMutableSet;
        SlotTable slotTable = new SlotTable();
        if (compositionContext.getCollectingCallByInformation$runtime_release()) {
            slotTable.collectCalledByInformation();
        }
        if (compositionContext.getCollectingSourceInformation$runtime_release()) {
            slotTable.collectSourceInformation();
        }
        this.slotTable = slotTable;
        this.observations = new ScopeMap();
        this.invalidatedScopes = new MutableScatterSet(0, 1, null);
        this.conditionallyInvalidatedScopes = new MutableScatterSet(0, 1, null);
        this.derivedStates = new ScopeMap();
        ChangeList changeList = new ChangeList();
        this.changes = changeList;
        ChangeList changeList2 = new ChangeList();
        this.lateChanges = changeList2;
        this.observationsProcessed = new ScopeMap();
        this.invalidations = new ScopeMap();
        this.observerHolder = new CompositionObserverHolder(null, false, 3, null);
        ComposerImpl composerImpl = new ComposerImpl(applier, compositionContext, slotTable, setAsMutableSet, changeList, changeList2, this);
        compositionContext.registerComposer$runtime_release(composerImpl);
        this.composer = composerImpl;
        this._recomposeContext = coroutineContext;
        this.isRoot = compositionContext instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.m1007getLambda1$runtime_release();
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compositionContext, applier, (i & 4) != 0 ? null : coroutineContext);
    }

    public final CompositionObserverHolder getObserverHolder$runtime_release() {
        return this.observerHolder;
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime_release();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.isComposing$runtime_release();
    }

    @Override // androidx.compose.runtime.Composition
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2 function2) {
        composeInitial(function2);
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void setContentWithReuse(Function2 function2) {
        this.composer.startReuseFromRoot();
        composeInitial(function2);
        this.composer.endReuseFromRoot();
    }

    private final void composeInitial(Function2 function2) {
        if (this.disposed) {
            PreconditionsKt.throwIllegalStateException("The composition is disposed");
        }
        this.composable = function2;
        this.parent.composeInitial$runtime_release(this, function2);
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object andSet = this.pendingModifications.getAndSet(CompositionKt.PendingApplyNoModifications);
        if (andSet != null) {
            if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                throw new KotlinNothingValueException();
            }
            if (andSet instanceof Set) {
                addPendingInvalidationsLocked((Set) andSet, true);
                return;
            }
            if (!(andSet instanceof Object[])) {
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                throw new KotlinNothingValueException();
            }
            for (Set set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, true);
            }
        }
    }

    private final void drainPendingModificationsLocked() {
        Object andSet = this.pendingModifications.getAndSet(null);
        if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set) andSet, false);
            return;
        }
        if (andSet instanceof Object[]) {
            for (Set set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, false);
            }
            return;
        }
        if (andSet == null) {
            ComposerKt.composeRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
            throw new KotlinNothingValueException();
        }
        ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2 function2) {
        try {
            synchronized (this.lock) {
                drainPendingModificationsForCompositionLocked();
                ScopeMap scopeMapTakeInvalidations = takeInvalidations();
                try {
                    observer();
                    this.composer.composeContent$runtime_release(scopeMapTakeInvalidations, function2);
                } catch (Exception e) {
                    this.invalidations = scopeMapTakeInvalidations;
                    throw e;
                }
            }
        } finally {
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        synchronized (this.lock) {
            try {
                if (this.composer.isComposing$runtime_release()) {
                    PreconditionsKt.throwIllegalStateException("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
                }
                if (!this.disposed) {
                    this.disposed = true;
                    this.composable = ComposableSingletons$CompositionKt.INSTANCE.m1008getLambda2$runtime_release();
                    ChangeList deferredChanges$runtime_release = this.composer.getDeferredChanges$runtime_release();
                    if (deferredChanges$runtime_release != null) {
                        applyChangesInLocked(deferredChanges$runtime_release);
                    }
                    boolean z = this.slotTable.getGroupsSize() > 0;
                    if (z || !this.abandonSet.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                        if (z) {
                            this.applier.onBeginChanges();
                            SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                            try {
                                ComposerKt.removeCurrentGroup(slotWriterOpenWriter, rememberEventDispatcher);
                                Unit unit = Unit.INSTANCE;
                                slotWriterOpenWriter.close(true);
                                this.applier.clear();
                                this.applier.onEndChanges();
                                rememberEventDispatcher.dispatchRememberObservers();
                            } catch (Throwable th) {
                                slotWriterOpenWriter.close(false);
                                throw th;
                            }
                        }
                        rememberEventDispatcher.dispatchAbandons();
                    }
                    this.composer.dispose$runtime_release();
                }
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th2) {
                throw th2;
            }
        }
        this.parent.unregisterComposition$runtime_release(this);
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        synchronized (this.lock) {
            z = this.invalidations.getSize() > 0;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set set) {
        Object obj;
        Object objPlus;
        do {
            obj = this.pendingModifications.get();
            if (obj == null ? true : Intrinsics.areEqual(obj, CompositionKt.PendingApplyNoModifications)) {
                objPlus = set;
            } else if (obj instanceof Set) {
                objPlus = new Set[]{obj, set};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                objPlus = ArraysKt.plus((Set[]) obj, set);
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingModifications, obj, objPlus));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0 function0) {
        this.composer.prepareCompose$runtime_release(function0);
    }

    private final void addPendingInvalidationsLocked(Object obj, boolean z) {
        Object obj2 = this.observations.getMap().get(obj);
        if (obj2 == null) {
            return;
        }
        if (obj2 instanceof MutableScatterSet) {
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
            Object[] objArr = mutableScatterSet.elements;
            long[] jArr = mutableScatterSet.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objArr[(i << 3) + i3];
                            if (!this.observationsProcessed.remove(obj, recomposeScopeImpl) && recomposeScopeImpl.invalidateForResult(obj) != InvalidationResult.IGNORED) {
                                if (recomposeScopeImpl.isConditional() && !z) {
                                    this.conditionallyInvalidatedScopes.add(recomposeScopeImpl);
                                } else {
                                    this.invalidatedScopes.add(recomposeScopeImpl);
                                }
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return;
                    }
                }
                if (i == length) {
                    return;
                } else {
                    i++;
                }
            }
        } else {
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj2;
            if (this.observationsProcessed.remove(obj, recomposeScopeImpl2) || recomposeScopeImpl2.invalidateForResult(obj) == InvalidationResult.IGNORED) {
                return;
            }
            if (recomposeScopeImpl2.isConditional() && !z) {
                this.conditionallyInvalidatedScopes.add(recomposeScopeImpl2);
            } else {
                this.invalidatedScopes.add(recomposeScopeImpl2);
            }
        }
    }

    private final void cleanUpDerivedStateObservations() {
        long[] jArr;
        int i;
        long[] jArr2;
        int i2;
        int i3;
        int i4;
        boolean zIsEmpty;
        Object[] objArr;
        long[] jArr3;
        long[] jArr4;
        MutableScatterMap map = this.derivedStates.getMap();
        long[] jArr5 = map.metadata;
        int length = jArr5.length - 2;
        char c = 7;
        long j = -9187201950435737472L;
        int i5 = 8;
        if (length >= 0) {
            int i6 = 0;
            while (true) {
                long j2 = jArr5[i6];
                if ((((~j2) << c) & j2 & j) != j) {
                    int i7 = 8 - ((~(i6 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((j2 & 255) < 128) {
                            int i9 = (i6 << 3) + i8;
                            Object obj = map.keys[i9];
                            Object obj2 = map.values[i9];
                            if (obj2 instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2>");
                                MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                                Object[] objArr2 = mutableScatterSet.elements;
                                long[] jArr6 = mutableScatterSet.metadata;
                                int length2 = jArr6.length - 2;
                                jArr2 = jArr5;
                                i2 = length;
                                if (length2 >= 0) {
                                    int i10 = 0;
                                    while (true) {
                                        long j3 = jArr6[i10];
                                        i3 = i6;
                                        Object[] objArr3 = objArr2;
                                        if ((((~j3) << c) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i11 = 8 - ((~(i10 - length2)) >>> 31);
                                            int i12 = 0;
                                            while (i12 < i11) {
                                                int i13 = i12;
                                                Object[] objArr4 = objArr3;
                                                if ((j3 & 255) < 128) {
                                                    int i14 = (i10 << 3) + i13;
                                                    jArr4 = jArr6;
                                                    if (!this.observations.contains((DerivedState) objArr4[i14])) {
                                                        mutableScatterSet.removeElementAt(i14);
                                                    }
                                                } else {
                                                    jArr4 = jArr6;
                                                }
                                                j3 >>= 8;
                                                i12 = i13 + 1;
                                                jArr6 = jArr4;
                                                objArr3 = objArr4;
                                            }
                                            objArr = objArr3;
                                            jArr3 = jArr6;
                                            if (i11 != 8) {
                                                break;
                                            }
                                        } else {
                                            objArr = objArr3;
                                            jArr3 = jArr6;
                                        }
                                        if (i10 == length2) {
                                            break;
                                        }
                                        i10++;
                                        jArr6 = jArr3;
                                        objArr2 = objArr;
                                        i6 = i3;
                                        c = 7;
                                    }
                                } else {
                                    i3 = i6;
                                }
                                zIsEmpty = mutableScatterSet.isEmpty();
                            } else {
                                jArr2 = jArr5;
                                i2 = length;
                                i3 = i6;
                                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2");
                                zIsEmpty = !this.observations.contains((DerivedState) obj2);
                            }
                            if (zIsEmpty) {
                                map.removeValueAt(i9);
                            }
                            i4 = 8;
                        } else {
                            jArr2 = jArr5;
                            i2 = length;
                            i3 = i6;
                            i4 = i5;
                        }
                        j2 >>= i4;
                        i8++;
                        i5 = i4;
                        jArr5 = jArr2;
                        length = i2;
                        i6 = i3;
                        c = 7;
                    }
                    jArr = jArr5;
                    int i15 = length;
                    int i16 = i6;
                    if (i7 != i5) {
                        break;
                    }
                    length = i15;
                    i = i16;
                } else {
                    jArr = jArr5;
                    i = i6;
                }
                if (i == length) {
                    break;
                }
                i6 = i + 1;
                jArr5 = jArr;
                c = 7;
                j = -9187201950435737472L;
                i5 = 8;
            }
        }
        if (!this.conditionallyInvalidatedScopes.isNotEmpty()) {
            return;
        }
        MutableScatterSet mutableScatterSet2 = this.conditionallyInvalidatedScopes;
        Object[] objArr5 = mutableScatterSet2.elements;
        long[] jArr7 = mutableScatterSet2.metadata;
        int length3 = jArr7.length - 2;
        if (length3 < 0) {
            return;
        }
        int i17 = 0;
        while (true) {
            long j4 = jArr7[i17];
            if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i18 = 8 - ((~(i17 - length3)) >>> 31);
                for (int i19 = 0; i19 < i18; i19++) {
                    if ((j4 & 255) < 128) {
                        int i20 = (i17 << 3) + i19;
                        if (!((RecomposeScopeImpl) objArr5[i20]).isConditional()) {
                            mutableScatterSet2.removeElementAt(i20);
                        }
                    }
                    j4 >>= 8;
                }
                if (i18 != 8) {
                    return;
                }
            }
            if (i17 == length3) {
                return;
            } else {
                i17++;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.RecomposeScopeOwner
    public void recordReadOf(Object obj) {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        long[] jArr;
        long[] jArr2;
        int i;
        if (getAreChildrenComposing() || (currentRecomposeScope$runtime_release = this.composer.getCurrentRecomposeScope$runtime_release()) == null) {
            return;
        }
        currentRecomposeScope$runtime_release.setUsed(true);
        if (currentRecomposeScope$runtime_release.recordRead(obj)) {
            return;
        }
        if (obj instanceof StateObjectImpl) {
            ((StateObjectImpl) obj).m1065recordReadInh_f27i8$runtime_release(ReaderKind.m1059constructorimpl(1));
        }
        this.observations.add(obj, currentRecomposeScope$runtime_release);
        if (obj instanceof DerivedState) {
            DerivedState derivedState = (DerivedState) obj;
            DerivedState.Record currentRecord = derivedState.getCurrentRecord();
            this.derivedStates.removeScope(obj);
            ObjectIntMap dependencies = currentRecord.getDependencies();
            Object[] objArr = dependencies.keys;
            long[] jArr3 = dependencies.metadata;
            int length = jArr3.length - 2;
            if (length >= 0) {
                int i2 = 0;
                while (true) {
                    long j = jArr3[i2];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i3 = 8;
                        int i4 = 8 - ((~(i2 - length)) >>> 31);
                        int i5 = 0;
                        while (i5 < i4) {
                            if ((j & 255) < 128) {
                                StateObject stateObject = (StateObject) objArr[(i2 << 3) + i5];
                                if (stateObject instanceof StateObjectImpl) {
                                    jArr2 = jArr3;
                                    ((StateObjectImpl) stateObject).m1065recordReadInh_f27i8$runtime_release(ReaderKind.m1059constructorimpl(1));
                                } else {
                                    jArr2 = jArr3;
                                }
                                this.derivedStates.add(stateObject, obj);
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
                            break;
                        }
                    } else {
                        jArr = jArr3;
                    }
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                    jArr3 = jArr;
                }
            }
            currentRecomposeScope$runtime_release.recordDerivedStateValue(derivedState, currentRecord.getCurrentValue());
        }
    }

    private final void invalidateScopeOfLocked(Object obj) {
        Object obj2 = this.observations.getMap().get(obj);
        if (obj2 == null) {
            return;
        }
        if (obj2 instanceof MutableScatterSet) {
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
            Object[] objArr = mutableScatterSet.elements;
            long[] jArr = mutableScatterSet.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objArr[(i << 3) + i3];
                            if (recomposeScopeImpl.invalidateForResult(obj) == InvalidationResult.IMMINENT) {
                                this.observationsProcessed.add(obj, recomposeScopeImpl);
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return;
                    }
                }
                if (i == length) {
                    return;
                } else {
                    i++;
                }
            }
        } else {
            RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj2;
            if (recomposeScopeImpl2.invalidateForResult(obj) == InvalidationResult.IMMINENT) {
                this.observationsProcessed.add(obj, recomposeScopeImpl2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    @Override // androidx.compose.runtime.ControlledComposition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void recordWriteOf(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = r14.lock
            monitor-enter(r0)
            r14.invalidateScopeOfLocked(r15)     // Catch: java.lang.Throwable -> L53
            androidx.compose.runtime.collection.ScopeMap r1 = r14.derivedStates     // Catch: java.lang.Throwable -> L53
            androidx.collection.MutableScatterMap r1 = r1.getMap()     // Catch: java.lang.Throwable -> L53
            java.lang.Object r15 = r1.get(r15)     // Catch: java.lang.Throwable -> L53
            if (r15 == 0) goto L65
            boolean r1 = r15 instanceof androidx.collection.MutableScatterSet     // Catch: java.lang.Throwable -> L53
            if (r1 == 0) goto L60
            androidx.collection.MutableScatterSet r15 = (androidx.collection.MutableScatterSet) r15     // Catch: java.lang.Throwable -> L53
            java.lang.Object[] r1 = r15.elements     // Catch: java.lang.Throwable -> L53
            long[] r15 = r15.metadata     // Catch: java.lang.Throwable -> L53
            int r2 = r15.length     // Catch: java.lang.Throwable -> L53
            int r2 = r2 + (-2)
            if (r2 < 0) goto L65
            r3 = 0
            r4 = r3
        L23:
            r5 = r15[r4]     // Catch: java.lang.Throwable -> L53
            long r7 = ~r5     // Catch: java.lang.Throwable -> L53
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L5b
            int r7 = r4 - r2
            int r7 = ~r7     // Catch: java.lang.Throwable -> L53
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r3
        L3d:
            if (r9 >= r7) goto L59
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.3E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L55
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r1[r10]     // Catch: java.lang.Throwable -> L53
            androidx.compose.runtime.DerivedState r10 = (androidx.compose.runtime.DerivedState) r10     // Catch: java.lang.Throwable -> L53
            r14.invalidateScopeOfLocked(r10)     // Catch: java.lang.Throwable -> L53
            goto L55
        L53:
            r15 = move-exception
            goto L69
        L55:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L3d
        L59:
            if (r7 != r8) goto L65
        L5b:
            if (r4 == r2) goto L65
            int r4 = r4 + 1
            goto L23
        L60:
            androidx.compose.runtime.DerivedState r15 = (androidx.compose.runtime.DerivedState) r15     // Catch: java.lang.Throwable -> L53
            r14.invalidateScopeOfLocked(r15)     // Catch: java.lang.Throwable -> L53
        L65:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L53
            monitor-exit(r0)
            return
        L69:
            monitor-exit(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.recordWriteOf(java.lang.Object):void");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean recompose() {
        boolean zRecompose$runtime_release;
        synchronized (this.lock) {
            try {
                drainPendingModificationsForCompositionLocked();
                try {
                    ScopeMap scopeMapTakeInvalidations = takeInvalidations();
                    try {
                        observer();
                        zRecompose$runtime_release = this.composer.recompose$runtime_release(scopeMapTakeInvalidations);
                        if (!zRecompose$runtime_release) {
                            drainPendingModificationsLocked();
                        }
                    } catch (Exception e) {
                        this.invalidations = scopeMapTakeInvalidations;
                        throw e;
                    }
                } finally {
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zRecompose$runtime_release;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void disposeUnusedMovableContent(MovableContentState movableContentState) {
        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
        SlotWriter slotWriterOpenWriter = movableContentState.getSlotTable$runtime_release().openWriter();
        try {
            ComposerKt.removeCurrentGroup(slotWriterOpenWriter, rememberEventDispatcher);
            Unit unit = Unit.INSTANCE;
            slotWriterOpenWriter.close(true);
            rememberEventDispatcher.dispatchRememberObservers();
        } catch (Throwable th) {
            slotWriterOpenWriter.close(false);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void applyChangesInLocked(androidx.compose.runtime.changelist.ChangeList r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 487
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.applyChangesInLocked(androidx.compose.runtime.changelist.ChangeList):void");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        synchronized (this.lock) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                        throw th;
                    } catch (Exception e) {
                        abandonChanges();
                        throw e;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyLateChanges() {
        synchronized (this.lock) {
            try {
                if (this.lateChanges.isNotEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                        throw th;
                    } catch (Exception e) {
                        abandonChanges();
                        throw e;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void changesApplied() {
        synchronized (this.lock) {
            try {
                this.composer.changesApplied$runtime_release();
                if (!this.abandonSet.isEmpty()) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                        throw th;
                    } catch (Exception e) {
                        abandonChanges();
                        throw e;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        if (this.abandonSet.isEmpty()) {
            return;
        }
        new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        synchronized (this.lock) {
            try {
                for (Object obj : this.slotTable.getSlots()) {
                    RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
                    if (recomposeScopeImpl != null) {
                        recomposeScopeImpl.invalidate();
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public Object delegateInvalidations(ControlledComposition controlledComposition, int i, Function0 function0) {
        if (controlledComposition != null && !Intrinsics.areEqual(controlledComposition, this) && i >= 0) {
            this.invalidationDelegate = (CompositionImpl) controlledComposition;
            this.invalidationDelegateGroup = i;
            try {
                return function0.invoke();
            } finally {
                this.invalidationDelegate = null;
                this.invalidationDelegateGroup = 0;
            }
        }
        return function0.invoke();
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public InvalidationResult invalidate(RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        CompositionImpl compositionImpl;
        if (recomposeScopeImpl.getDefaultsInScope()) {
            recomposeScopeImpl.setDefaultsInvalid(true);
        }
        Anchor anchor = recomposeScopeImpl.getAnchor();
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (!this.slotTable.ownsAnchor(anchor)) {
            synchronized (this.lock) {
                compositionImpl = this.invalidationDelegate;
            }
            if (compositionImpl != null && compositionImpl.tryImminentInvalidation(recomposeScopeImpl, obj)) {
                return InvalidationResult.IMMINENT;
            }
            return InvalidationResult.IGNORED;
        }
        if (!recomposeScopeImpl.getCanRecompose()) {
            return InvalidationResult.IGNORED;
        }
        return invalidateChecked(recomposeScopeImpl, anchor, obj);
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public void recomposeScopeReleased(RecomposeScopeImpl recomposeScopeImpl) {
        this.pendingInvalidScopes = true;
    }

    private final boolean tryImminentInvalidation(RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        return isComposing() && this.composer.tryImminentInvalidation$runtime_release(recomposeScopeImpl, obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ab A[Catch: all -> 0x001e, EDGE_INSN: B:65:0x00ab->B:51:0x00ab BREAK  A[LOOP:0: B:32:0x0064->B:47:0x00a3], EDGE_INSN: B:66:0x00ab->B:51:0x00ab BREAK  A[LOOP:0: B:32:0x0064->B:47:0x00a3], TRY_LEAVE, TryCatch #0 {all -> 0x001e, blocks: (B:4:0x000b, B:6:0x0010, B:14:0x0023, B:16:0x0029, B:19:0x002d, B:21:0x0032, B:22:0x003b, B:24:0x003f, B:25:0x0048, B:27:0x0054, B:29:0x0058, B:32:0x0064, B:34:0x0074, B:36:0x0080, B:38:0x008a, B:43:0x0099, B:47:0x00a3, B:48:0x00a6, B:51:0x00ab), top: B:63:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.compose.runtime.InvalidationResult invalidateChecked(androidx.compose.runtime.RecomposeScopeImpl r21, androidx.compose.runtime.Anchor r22, java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.invalidateChecked(androidx.compose.runtime.RecomposeScopeImpl, androidx.compose.runtime.Anchor, java.lang.Object):androidx.compose.runtime.InvalidationResult");
    }

    public final void removeObservation$runtime_release(Object obj, RecomposeScopeImpl recomposeScopeImpl) {
        this.observations.remove(obj, recomposeScopeImpl);
    }

    public final void removeDerivedStateObservation$runtime_release(DerivedState derivedState) {
        if (this.observations.contains(derivedState)) {
            return;
        }
        this.derivedStates.removeScope(derivedState);
    }

    private final ScopeMap takeInvalidations() {
        ScopeMap scopeMap = this.invalidations;
        this.invalidations = new ScopeMap();
        return scopeMap;
    }

    private final CompositionObserver observer() {
        CompositionObserverHolder compositionObserverHolder = this.observerHolder;
        if (compositionObserverHolder.getRoot()) {
            compositionObserverHolder.getObserver();
        } else {
            CompositionObserverHolder observerHolder$runtime_release = this.parent.getObserverHolder$runtime_release();
            if (observerHolder$runtime_release != null) {
                observerHolder$runtime_release.getObserver();
            }
            compositionObserverHolder.getObserver();
            if (!Intrinsics.areEqual((Object) null, (Object) null)) {
                compositionObserverHolder.setObserver(null);
            }
        }
        return null;
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void deactivate() {
        synchronized (this.lock) {
            try {
                boolean z = this.slotTable.getGroupsSize() > 0;
                if (z || !this.abandonSet.isEmpty()) {
                    Trace trace = Trace.INSTANCE;
                    Object objBeginSection = trace.beginSection("Compose:deactivate");
                    try {
                        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                        if (z) {
                            this.applier.onBeginChanges();
                            SlotWriter slotWriterOpenWriter = this.slotTable.openWriter();
                            try {
                                ComposerKt.deactivateCurrentGroup(slotWriterOpenWriter, rememberEventDispatcher);
                                Unit unit = Unit.INSTANCE;
                                slotWriterOpenWriter.close(true);
                                this.applier.onEndChanges();
                                rememberEventDispatcher.dispatchRememberObservers();
                            } catch (Throwable th) {
                                slotWriterOpenWriter.close(false);
                                throw th;
                            }
                        }
                        rememberEventDispatcher.dispatchAbandons();
                        Unit unit2 = Unit.INSTANCE;
                        trace.endSection(objBeginSection);
                    } catch (Throwable th2) {
                        Trace.INSTANCE.endSection(objBeginSection);
                        throw th2;
                    }
                }
                this.observations.clear();
                this.derivedStates.clear();
                this.invalidations.clear();
                this.changes.clear();
                this.lateChanges.clear();
                this.composer.deactivate$runtime_release();
                Unit unit3 = Unit.INSTANCE;
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }

    /* JADX INFO: compiled from: Composition.kt */
    private static final class RememberEventDispatcher implements RememberManager {
        private final Set abandoning;
        private MutableScatterSet releasing;
        private final List remembering = new ArrayList();
        private final List leaving = new ArrayList();
        private final List sideEffects = new ArrayList();
        private final List pending = new ArrayList();
        private final MutableIntList priorities = new MutableIntList(0, 1, null);
        private final MutableIntList afters = new MutableIntList(0, 1, null);

        public RememberEventDispatcher(Set set) {
            this.abandoning = set;
        }

        @Override // androidx.compose.runtime.RememberManager
        public void remembering(RememberObserver rememberObserver) {
            this.remembering.add(rememberObserver);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void forgetting(RememberObserver rememberObserver, int i, int i2, int i3) {
            recordLeaving(rememberObserver, i, i2, i3);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void sideEffect(Function0 function0) {
            this.sideEffects.add(function0);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void deactivating(ComposeNodeLifecycleCallback composeNodeLifecycleCallback, int i, int i2, int i3) {
            recordLeaving(composeNodeLifecycleCallback, i, i2, i3);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void releasing(ComposeNodeLifecycleCallback composeNodeLifecycleCallback, int i, int i2, int i3) {
            MutableScatterSet mutableScatterSetMutableScatterSetOf = this.releasing;
            if (mutableScatterSetMutableScatterSetOf == null) {
                mutableScatterSetMutableScatterSetOf = ScatterSetKt.mutableScatterSetOf();
                this.releasing = mutableScatterSetMutableScatterSetOf;
            }
            mutableScatterSetMutableScatterSetOf.plusAssign(composeNodeLifecycleCallback);
            recordLeaving(composeNodeLifecycleCallback, i, i2, i3);
        }

        public final void dispatchRememberObservers() {
            Object objBeginSection;
            processPendingLeaving(Integer.MIN_VALUE);
            if (!this.leaving.isEmpty()) {
                objBeginSection = Trace.INSTANCE.beginSection("Compose:onForgotten");
                try {
                    MutableScatterSet mutableScatterSet = this.releasing;
                    for (int size = this.leaving.size() - 1; -1 < size; size--) {
                        Object obj = this.leaving.get(size);
                        if (obj instanceof RememberObserver) {
                            this.abandoning.remove(obj);
                            ((RememberObserver) obj).onForgotten();
                        }
                        if (obj instanceof ComposeNodeLifecycleCallback) {
                            if (mutableScatterSet != null && mutableScatterSet.contains(obj)) {
                                ((ComposeNodeLifecycleCallback) obj).onRelease();
                            } else {
                                ((ComposeNodeLifecycleCallback) obj).onDeactivate();
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    Trace.INSTANCE.endSection(objBeginSection);
                } finally {
                }
            }
            if (this.remembering.isEmpty()) {
                return;
            }
            objBeginSection = Trace.INSTANCE.beginSection("Compose:onRemembered");
            try {
                List list = this.remembering;
                int size2 = list.size();
                for (int i = 0; i < size2; i++) {
                    RememberObserver rememberObserver = (RememberObserver) list.get(i);
                    this.abandoning.remove(rememberObserver);
                    rememberObserver.onRemembered();
                }
                Unit unit2 = Unit.INSTANCE;
                Trace.INSTANCE.endSection(objBeginSection);
            } finally {
            }
        }

        public final void dispatchSideEffects() {
            if (this.sideEffects.isEmpty()) {
                return;
            }
            Object objBeginSection = Trace.INSTANCE.beginSection("Compose:sideeffects");
            try {
                List list = this.sideEffects;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ((Function0) list.get(i)).invoke();
                }
                this.sideEffects.clear();
                Unit unit = Unit.INSTANCE;
                Trace.INSTANCE.endSection(objBeginSection);
            } catch (Throwable th) {
                Trace.INSTANCE.endSection(objBeginSection);
                throw th;
            }
        }

        public final void dispatchAbandons() {
            if (this.abandoning.isEmpty()) {
                return;
            }
            Object objBeginSection = Trace.INSTANCE.beginSection("Compose:abandons");
            try {
                Iterator it = this.abandoning.iterator();
                while (it.hasNext()) {
                    RememberObserver rememberObserver = (RememberObserver) it.next();
                    it.remove();
                    rememberObserver.onAbandoned();
                }
                Unit unit = Unit.INSTANCE;
                Trace.INSTANCE.endSection(objBeginSection);
            } catch (Throwable th) {
                Trace.INSTANCE.endSection(objBeginSection);
                throw th;
            }
        }

        private final void recordLeaving(Object obj, int i, int i2, int i3) {
            processPendingLeaving(i);
            if (i3 >= 0 && i3 < i) {
                this.pending.add(obj);
                this.priorities.add(i2);
                this.afters.add(i3);
                return;
            }
            this.leaving.add(obj);
        }

        private final void processPendingLeaving(int i) {
            int i2 = 0;
            if (this.pending.isEmpty()) {
                return;
            }
            int i3 = 0;
            List listMutableListOf = null;
            MutableIntList mutableIntList = null;
            MutableIntList mutableIntList2 = null;
            while (i3 < this.afters.getSize()) {
                if (i <= this.afters.get(i3)) {
                    Object objRemove = this.pending.remove(i3);
                    int iRemoveAt = this.afters.removeAt(i3);
                    int iRemoveAt2 = this.priorities.removeAt(i3);
                    if (listMutableListOf == null) {
                        listMutableListOf = CollectionsKt.mutableListOf(objRemove);
                        mutableIntList2 = new MutableIntList(0, 1, null);
                        mutableIntList2.add(iRemoveAt);
                        mutableIntList = new MutableIntList(0, 1, null);
                        mutableIntList.add(iRemoveAt2);
                    } else {
                        Intrinsics.checkNotNull(mutableIntList, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                        Intrinsics.checkNotNull(mutableIntList2, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                        listMutableListOf.add(objRemove);
                        mutableIntList2.add(iRemoveAt);
                        mutableIntList.add(iRemoveAt2);
                    }
                } else {
                    i3++;
                }
            }
            if (listMutableListOf != null) {
                Intrinsics.checkNotNull(mutableIntList, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                Intrinsics.checkNotNull(mutableIntList2, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                int size = listMutableListOf.size() - 1;
                while (i2 < size) {
                    int i4 = i2 + 1;
                    int size2 = listMutableListOf.size();
                    for (int i5 = i4; i5 < size2; i5++) {
                        int i6 = mutableIntList2.get(i2);
                        int i7 = mutableIntList2.get(i5);
                        if (i6 < i7 || (i7 == i6 && mutableIntList.get(i2) < mutableIntList.get(i5))) {
                            CompositionKt.swap(listMutableListOf, i2, i5);
                            CompositionKt.swap(mutableIntList, i2, i5);
                            CompositionKt.swap(mutableIntList2, i2, i5);
                        }
                    }
                    i2 = i4;
                }
                this.leaving.addAll(listMutableListOf);
            }
        }
    }
}
