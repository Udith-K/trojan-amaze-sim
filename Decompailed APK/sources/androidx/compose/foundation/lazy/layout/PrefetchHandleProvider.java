package androidx.compose.foundation.lazy.layout;

import android.os.Trace;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNode$Companion$TraverseDescendantsAction;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PrefetchHandleProvider {
    private final PrefetchScheduler executor;
    private final LazyLayoutItemContentFactory itemContentFactory;
    private final SubcomposeLayoutState subcomposeLayoutState;

    public PrefetchHandleProvider(LazyLayoutItemContentFactory lazyLayoutItemContentFactory, SubcomposeLayoutState subcomposeLayoutState, PrefetchScheduler prefetchScheduler) {
        this.itemContentFactory = lazyLayoutItemContentFactory;
        this.subcomposeLayoutState = subcomposeLayoutState;
        this.executor = prefetchScheduler;
    }

    /* JADX INFO: renamed from: schedulePrefetch-VKLhPVY, reason: not valid java name */
    public final LazyLayoutPrefetchState.PrefetchHandle m356schedulePrefetchVKLhPVY(int i, long j, PrefetchMetrics prefetchMetrics) {
        HandleAndRequestImpl handleAndRequestImpl = new HandleAndRequestImpl(this, i, j, prefetchMetrics, null);
        this.executor.schedulePrefetch(handleAndRequestImpl);
        return handleAndRequestImpl;
    }

    /* JADX INFO: renamed from: createNestedPrefetchRequest-VKLhPVY, reason: not valid java name */
    public final PrefetchRequest m355createNestedPrefetchRequestVKLhPVY(int i, long j, PrefetchMetrics prefetchMetrics) {
        return new HandleAndRequestImpl(this, i, j, prefetchMetrics, null);
    }

    /* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
    private final class HandleAndRequestImpl implements LazyLayoutPrefetchState.PrefetchHandle, PrefetchRequest {
        private final long constraints;
        private boolean hasResolvedNestedPrefetches;
        private final int index;
        private boolean isCanceled;
        private boolean isMeasured;
        private boolean isUrgent;
        private NestedPrefetchController nestedPrefetchController;
        private SubcomposeLayoutState.PrecomposedSlotHandle precomposeHandle;
        private final PrefetchMetrics prefetchMetrics;

        public /* synthetic */ HandleAndRequestImpl(PrefetchHandleProvider prefetchHandleProvider, int i, long j, PrefetchMetrics prefetchMetrics, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, j, prefetchMetrics);
        }

        private HandleAndRequestImpl(int i, long j, PrefetchMetrics prefetchMetrics) {
            this.index = i;
            this.constraints = j;
            this.prefetchMetrics = prefetchMetrics;
        }

        private final boolean isComposed() {
            return this.precomposeHandle != null;
        }

        private final boolean isValid() {
            if (!this.isCanceled) {
                int itemCount = ((LazyLayoutItemProvider) PrefetchHandleProvider.this.itemContentFactory.getItemProvider().invoke()).getItemCount();
                int i = this.index;
                if (i >= 0 && i < itemCount) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public void cancel() {
            if (this.isCanceled) {
                return;
            }
            this.isCanceled = true;
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
            if (precomposedSlotHandle != null) {
                precomposedSlotHandle.dispose();
            }
            this.precomposeHandle = null;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public void markAsUrgent() {
            this.isUrgent = true;
        }

        private final boolean shouldExecute(PrefetchRequestScope prefetchRequestScope, long j) {
            long jAvailableTimeNanos = prefetchRequestScope.availableTimeNanos();
            return (this.isUrgent && jAvailableTimeNanos > 0) || j < jAvailableTimeNanos;
        }

        @Override // androidx.compose.foundation.lazy.layout.PrefetchRequest
        public boolean execute(PrefetchRequestScope prefetchRequestScope) {
            if (!isValid()) {
                return false;
            }
            Object contentType = ((LazyLayoutItemProvider) PrefetchHandleProvider.this.itemContentFactory.getItemProvider().invoke()).getContentType(this.index);
            if (!isComposed()) {
                if (!shouldExecute(prefetchRequestScope, (contentType == null || !this.prefetchMetrics.getAverageCompositionTimeNanosByContentType().contains(contentType)) ? this.prefetchMetrics.getAverageCompositionTimeNanos() : this.prefetchMetrics.getAverageCompositionTimeNanosByContentType().get(contentType))) {
                    return true;
                }
                PrefetchMetrics prefetchMetrics = this.prefetchMetrics;
                long jNanoTime = System.nanoTime();
                Trace.beginSection("compose:lazy:prefetch:compose");
                try {
                    performComposition();
                    Unit unit = Unit.INSTANCE;
                    Trace.endSection();
                    long jNanoTime2 = System.nanoTime() - jNanoTime;
                    if (contentType != null) {
                        prefetchMetrics.getAverageCompositionTimeNanosByContentType().set(contentType, prefetchMetrics.calculateAverageTime(jNanoTime2, prefetchMetrics.getAverageCompositionTimeNanosByContentType().getOrDefault(contentType, 0L)));
                    }
                    prefetchMetrics.averageCompositionTimeNanos = prefetchMetrics.calculateAverageTime(jNanoTime2, prefetchMetrics.getAverageCompositionTimeNanos());
                } finally {
                }
            }
            if (!this.isUrgent) {
                if (!this.hasResolvedNestedPrefetches) {
                    if (prefetchRequestScope.availableTimeNanos() <= 0) {
                        return true;
                    }
                    Trace.beginSection("compose:lazy:prefetch:resolve-nested");
                    try {
                        this.nestedPrefetchController = resolveNestedPrefetchStates();
                        this.hasResolvedNestedPrefetches = true;
                        Unit unit2 = Unit.INSTANCE;
                    } finally {
                    }
                }
                NestedPrefetchController nestedPrefetchController = this.nestedPrefetchController;
                if (nestedPrefetchController != null ? nestedPrefetchController.executeNestedPrefetches(prefetchRequestScope) : false) {
                    return true;
                }
            }
            if (!this.isMeasured && !Constraints.m2393isZeroimpl(this.constraints)) {
                if (!shouldExecute(prefetchRequestScope, (contentType == null || !this.prefetchMetrics.getAverageMeasureTimeNanosByContentType().contains(contentType)) ? this.prefetchMetrics.getAverageMeasureTimeNanos() : this.prefetchMetrics.getAverageMeasureTimeNanosByContentType().get(contentType))) {
                    return true;
                }
                PrefetchMetrics prefetchMetrics2 = this.prefetchMetrics;
                long jNanoTime3 = System.nanoTime();
                Trace.beginSection("compose:lazy:prefetch:measure");
                try {
                    m357performMeasureBRTryo0(this.constraints);
                    Unit unit3 = Unit.INSTANCE;
                    Trace.endSection();
                    long jNanoTime4 = System.nanoTime() - jNanoTime3;
                    if (contentType != null) {
                        prefetchMetrics2.getAverageMeasureTimeNanosByContentType().set(contentType, prefetchMetrics2.calculateAverageTime(jNanoTime4, prefetchMetrics2.getAverageMeasureTimeNanosByContentType().getOrDefault(contentType, 0L)));
                    }
                    prefetchMetrics2.averageMeasureTimeNanos = prefetchMetrics2.calculateAverageTime(jNanoTime4, prefetchMetrics2.getAverageMeasureTimeNanos());
                } finally {
                }
            }
            return false;
        }

        private final void performComposition() {
            if (!isValid()) {
                throw new IllegalArgumentException("Callers should check whether the request is still valid before calling performComposition()");
            }
            if (this.precomposeHandle == null) {
                LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) PrefetchHandleProvider.this.itemContentFactory.getItemProvider().invoke();
                Object key = lazyLayoutItemProvider.getKey(this.index);
                this.precomposeHandle = PrefetchHandleProvider.this.subcomposeLayoutState.precompose(key, PrefetchHandleProvider.this.itemContentFactory.getContent(this.index, key, lazyLayoutItemProvider.getContentType(this.index)));
                return;
            }
            throw new IllegalArgumentException("Request was already composed!");
        }

        /* JADX INFO: renamed from: performMeasure-BRTryo0, reason: not valid java name */
        private final void m357performMeasureBRTryo0(long j) {
            if (this.isCanceled) {
                throw new IllegalArgumentException("Callers should check whether the request is still valid before calling performMeasure()");
            }
            if (this.isMeasured) {
                throw new IllegalArgumentException("Request was already measured!");
            }
            this.isMeasured = true;
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
            if (precomposedSlotHandle == null) {
                throw new IllegalArgumentException("performComposition() must be called before performMeasure()");
            }
            int placeablesCount = precomposedSlotHandle.getPlaceablesCount();
            for (int i = 0; i < placeablesCount; i++) {
                precomposedSlotHandle.mo1754premeasure0kLqBqw(i, j);
            }
        }

        private final NestedPrefetchController resolveNestedPrefetchStates() {
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
            if (precomposedSlotHandle == null) {
                throw new IllegalArgumentException("Should precompose before resolving nested prefetch states");
            }
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            precomposedSlotHandle.traverseDescendants("androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode", new Function1() { // from class: androidx.compose.foundation.lazy.layout.PrefetchHandleProvider$HandleAndRequestImpl$resolveNestedPrefetchStates$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final TraversableNode$Companion$TraverseDescendantsAction invoke(TraversableNode traversableNode) {
                    Intrinsics.checkNotNull(traversableNode, "null cannot be cast to non-null type androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode");
                    LazyLayoutPrefetchState prefetchState = ((TraversablePrefetchStateNode) traversableNode).getPrefetchState();
                    Ref$ObjectRef ref$ObjectRef2 = ref$ObjectRef;
                    List listMutableListOf = (List) ref$ObjectRef2.element;
                    if (listMutableListOf != null) {
                        listMutableListOf.add(prefetchState);
                    } else {
                        listMutableListOf = CollectionsKt.mutableListOf(prefetchState);
                    }
                    ref$ObjectRef2.element = listMutableListOf;
                    return TraversableNode$Companion$TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
                }
            });
            List list = (List) ref$ObjectRef.element;
            if (list != null) {
                return new NestedPrefetchController(list);
            }
            return null;
        }

        public String toString() {
            return "HandleAndRequestImpl { index = " + this.index + ", constraints = " + ((Object) Constraints.m2394toStringimpl(this.constraints)) + ", isComposed = " + isComposed() + ", isMeasured = " + this.isMeasured + ", isCanceled = " + this.isCanceled + " }";
        }

        /* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
        private final class NestedPrefetchController {
            private int requestIndex;
            private final List[] requestsByState;
            private int stateIndex;
            private final List states;

            public NestedPrefetchController(List list) {
                this.states = list;
                this.requestsByState = new List[list.size()];
                if (list.isEmpty()) {
                    throw new IllegalArgumentException("NestedPrefetchController shouldn't be created with no states");
                }
            }

            public final boolean executeNestedPrefetches(PrefetchRequestScope prefetchRequestScope) {
                if (this.stateIndex >= this.states.size()) {
                    return false;
                }
                if (!HandleAndRequestImpl.this.isCanceled) {
                    Trace.beginSection("compose:lazy:prefetch:nested");
                    while (this.stateIndex < this.states.size()) {
                        try {
                            if (this.requestsByState[this.stateIndex] == null) {
                                if (prefetchRequestScope.availableTimeNanos() <= 0) {
                                    Trace.endSection();
                                    return true;
                                }
                                List[] listArr = this.requestsByState;
                                int i = this.stateIndex;
                                listArr[i] = ((LazyLayoutPrefetchState) this.states.get(i)).collectNestedPrefetchRequests$foundation_release();
                            }
                            List list = this.requestsByState[this.stateIndex];
                            Intrinsics.checkNotNull(list);
                            while (this.requestIndex < list.size()) {
                                if (((PrefetchRequest) list.get(this.requestIndex)).execute(prefetchRequestScope)) {
                                    Trace.endSection();
                                    return true;
                                }
                                this.requestIndex++;
                            }
                            this.requestIndex = 0;
                            this.stateIndex++;
                        } catch (Throwable th) {
                            Trace.endSection();
                            throw th;
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    Trace.endSection();
                    return false;
                }
                throw new IllegalStateException("Should not execute nested prefetch on canceled request");
            }
        }
    }
}
