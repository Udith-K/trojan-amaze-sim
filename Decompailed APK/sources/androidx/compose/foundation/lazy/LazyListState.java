package androidx.compose.foundation.lazy;

import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.foundation.lazy.layout.NestedPrefetchScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyListState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyListState implements ScrollableState {
    public static final Companion Companion = new Companion(null);
    private static final Saver Saver = ListSaverKt.listSaver(new Function2() { // from class: androidx.compose.foundation.lazy.LazyListState$Companion$Saver$1
        @Override // kotlin.jvm.functions.Function2
        public final List invoke(SaverScope saverScope, LazyListState lazyListState) {
            return CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(lazyListState.getFirstVisibleItemIndex()), Integer.valueOf(lazyListState.getFirstVisibleItemScrollOffset())});
        }
    }, new Function1() { // from class: androidx.compose.foundation.lazy.LazyListState$Companion$Saver$2
        @Override // kotlin.jvm.functions.Function1
        public final LazyListState invoke(List list) {
            return new LazyListState(((Number) list.get(0)).intValue(), ((Number) list.get(1)).intValue());
        }
    });
    private AnimationState _scrollDeltaBetweenPasses;
    private final LazyListAnimateScrollScope animateScrollScope;
    private final AwaitFirstLayoutModifier awaitLayoutModifier;
    private final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;
    private final MutableState canScrollBackward$delegate;
    private final MutableState canScrollForward$delegate;
    private boolean hasLookaheadPassOccurred;
    private final MutableInteractionSource internalInteractionSource;
    private final LazyLayoutItemAnimator itemAnimator;
    private final MutableState layoutInfoState;
    private final MutableState measurementScopeInvalidator;
    private int numMeasurePasses;
    private final LazyLayoutPinnedItemList pinnedItems;
    private final MutableState placementScopeInvalidator;
    private LazyListMeasureResult postLookaheadLayoutInfo;
    private final LazyListPrefetchScope prefetchScope;
    private final LazyLayoutPrefetchState prefetchState;
    private final LazyListPrefetchStrategy prefetchStrategy;
    private boolean prefetchingEnabled;
    private Remeasurement remeasurement;
    private final RemeasurementModifier remeasurementModifier;
    private final LazyListScrollPosition scrollPosition;
    private float scrollToBeConsumed;
    private final ScrollableState scrollableState;

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListState$scroll$1, reason: invalid class name */
    /* JADX INFO: compiled from: LazyListState.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LazyListState.this.scroll(null, null, this);
        }
    }

    public LazyListState(final int i, int i2, LazyListPrefetchStrategy lazyListPrefetchStrategy) {
        this.prefetchStrategy = lazyListPrefetchStrategy;
        LazyListScrollPosition lazyListScrollPosition = new LazyListScrollPosition(i, i2);
        this.scrollPosition = lazyListScrollPosition;
        this.animateScrollScope = new LazyListAnimateScrollScope(this);
        this.layoutInfoState = SnapshotStateKt.mutableStateOf(LazyListStateKt.EmptyLazyListMeasureResult, SnapshotStateKt.neverEqualPolicy());
        this.internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
        this.scrollableState = ScrollableStateKt.ScrollableState(new Function1() { // from class: androidx.compose.foundation.lazy.LazyListState$scrollableState$1
            {
                super(1);
            }

            public final Float invoke(float f) {
                return Float.valueOf(-this.this$0.onScroll$foundation_release(-f));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).floatValue());
            }
        });
        this.prefetchingEnabled = true;
        this.remeasurementModifier = new RemeasurementModifier() { // from class: androidx.compose.foundation.lazy.LazyListState$remeasurementModifier$1
            @Override // androidx.compose.ui.Modifier
            public /* synthetic */ boolean all(Function1 function1) {
                return ((Boolean) function1.invoke(this)).booleanValue();
            }

            @Override // androidx.compose.ui.Modifier
            public /* synthetic */ Object foldIn(Object obj, Function2 function2) {
                return function2.invoke(obj, this);
            }

            @Override // androidx.compose.ui.Modifier
            public /* synthetic */ Modifier then(Modifier modifier) {
                return Modifier.CC.$default$then(this, modifier);
            }

            @Override // androidx.compose.ui.layout.RemeasurementModifier
            public void onRemeasurementAvailable(Remeasurement remeasurement) {
                this.this$0.remeasurement = remeasurement;
            }
        };
        this.awaitLayoutModifier = new AwaitFirstLayoutModifier();
        this.itemAnimator = new LazyLayoutItemAnimator();
        this.beyondBoundsInfo = new LazyLayoutBeyondBoundsInfo();
        this.prefetchState = new LazyLayoutPrefetchState(lazyListPrefetchStrategy.getPrefetchScheduler(), new Function1() { // from class: androidx.compose.foundation.lazy.LazyListState$prefetchState$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((NestedPrefetchScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(NestedPrefetchScope nestedPrefetchScope) {
                LazyListPrefetchStrategy lazyListPrefetchStrategy2 = this.this$0.prefetchStrategy;
                int i3 = i;
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                companion.restoreNonObservable(currentThreadSnapshot, companion.makeCurrentNonObservable(currentThreadSnapshot), currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null);
                lazyListPrefetchStrategy2.onNestedPrefetch(nestedPrefetchScope, i3);
            }
        });
        this.prefetchScope = new LazyListPrefetchScope() { // from class: androidx.compose.foundation.lazy.LazyListState$prefetchScope$1
            @Override // androidx.compose.foundation.lazy.LazyListPrefetchScope
            public LazyLayoutPrefetchState.PrefetchHandle schedulePrefetch(int i3) {
                Snapshot.Companion companion = Snapshot.Companion;
                LazyListState lazyListState = this.this$0;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    long jM315getChildConstraintsmsEJaDk = ((LazyListMeasureResult) lazyListState.layoutInfoState.getValue()).m315getChildConstraintsmsEJaDk();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    return this.this$0.getPrefetchState$foundation_release().m349schedulePrefetch0kLqBqw(i3, jM315getChildConstraintsmsEJaDk);
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
        };
        this.pinnedItems = new LazyLayoutPinnedItemList();
        lazyListScrollPosition.getNearestRangeState();
        this.measurementScopeInvalidator = ObservableScopeInvalidator.m353constructorimpl$default(null, 1, null);
        Boolean bool = Boolean.FALSE;
        this.canScrollForward$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.canScrollBackward$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.placementScopeInvalidator = ObservableScopeInvalidator.m353constructorimpl$default(null, 1, null);
        TwoWayConverter vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        Float fValueOf = Float.valueOf(0.0f);
        this._scrollDeltaBetweenPasses = AnimationStateKt.AnimationState(vectorConverter, fValueOf, fValueOf, (56 & 8) != 0 ? Long.MIN_VALUE : 0L, (56 & 16) != 0 ? Long.MIN_VALUE : 0L, (56 & 32) != 0 ? false : false);
    }

    public LazyListState(int i, int i2) {
        this(i, i2, LazyListPrefetchStrategyKt.LazyListPrefetchStrategy$default(0, 1, null));
    }

    public final boolean getHasLookaheadPassOccurred$foundation_release() {
        return this.hasLookaheadPassOccurred;
    }

    public final LazyListMeasureResult getPostLookaheadLayoutInfo$foundation_release() {
        return this.postLookaheadLayoutInfo;
    }

    public final int getFirstVisibleItemIndex() {
        return this.scrollPosition.getIndex();
    }

    public final int getFirstVisibleItemScrollOffset() {
        return this.scrollPosition.getScrollOffset();
    }

    public final LazyListLayoutInfo getLayoutInfo() {
        return (LazyListLayoutInfo) this.layoutInfoState.getValue();
    }

    public final MutableInteractionSource getInternalInteractionSource$foundation_release() {
        return this.internalInteractionSource;
    }

    public final float getScrollToBeConsumed$foundation_release() {
        return this.scrollToBeConsumed;
    }

    public final Remeasurement getRemeasurement$foundation_release() {
        return this.remeasurement;
    }

    public final RemeasurementModifier getRemeasurementModifier$foundation_release() {
        return this.remeasurementModifier;
    }

    public final AwaitFirstLayoutModifier getAwaitLayoutModifier$foundation_release() {
        return this.awaitLayoutModifier;
    }

    public final LazyLayoutItemAnimator getItemAnimator$foundation_release() {
        return this.itemAnimator;
    }

    public final LazyLayoutBeyondBoundsInfo getBeyondBoundsInfo$foundation_release() {
        return this.beyondBoundsInfo;
    }

    public final LazyLayoutPrefetchState getPrefetchState$foundation_release() {
        return this.prefetchState;
    }

    public final LazyLayoutPinnedItemList getPinnedItems$foundation_release() {
        return this.pinnedItems;
    }

    public final IntRange getNearestRange$foundation_release() {
        return (IntRange) this.scrollPosition.getNearestRangeState().getValue();
    }

    public static /* synthetic */ Object scrollToItem$default(LazyListState lazyListState, int i, int i2, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return lazyListState.scrollToItem(i, i2, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListState$scrollToItem$2, reason: invalid class name */
    /* JADX INFO: compiled from: LazyListState.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ int $index;
        final /* synthetic */ int $scrollOffset;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int i, int i2, Continuation continuation) {
            super(2, continuation);
            this.$index = i;
            this.$scrollOffset = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return LazyListState.this.new AnonymousClass2(this.$index, this.$scrollOffset, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation continuation) {
            return ((AnonymousClass2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            LazyListState.this.snapToItemIndexInternal$foundation_release(this.$index, this.$scrollOffset, true);
            return Unit.INSTANCE;
        }
    }

    public final Object scrollToItem(int i, int i2, Continuation continuation) {
        Object objScroll$default = ScrollableState.CC.scroll$default(this, null, new AnonymousClass2(i, i2, null), continuation, 1, null);
        return objScroll$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: getMeasurementScopeInvalidator-zYiylxw$foundation_release, reason: not valid java name */
    public final MutableState m323getMeasurementScopeInvalidatorzYiylxw$foundation_release() {
        return this.measurementScopeInvalidator;
    }

    public final void snapToItemIndexInternal$foundation_release(int i, int i2, boolean z) {
        if (this.scrollPosition.getIndex() != i || this.scrollPosition.getScrollOffset() != i2) {
            this.itemAnimator.reset();
        }
        this.scrollPosition.requestPositionAndForgetLastKnownKey(i, i2);
        if (z) {
            Remeasurement remeasurement = this.remeasurement;
            if (remeasurement != null) {
                remeasurement.forceRemeasure();
                return;
            }
            return;
        }
        ObservableScopeInvalidator.m354invalidateScopeimpl(this.measurementScopeInvalidator);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object scroll(androidx.compose.foundation.MutatePriority r6, kotlin.jvm.functions.Function2 r7, kotlin.coroutines.Continuation r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof androidx.compose.foundation.lazy.LazyListState.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.lazy.LazyListState$scroll$1 r0 = (androidx.compose.foundation.lazy.LazyListState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.lazy.LazyListState$scroll$1 r0 = new androidx.compose.foundation.lazy.LazyListState$scroll$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L45
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6c
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            java.lang.Object r6 = r0.L$2
            r7 = r6
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r6 = r0.L$1
            androidx.compose.foundation.MutatePriority r6 = (androidx.compose.foundation.MutatePriority) r6
            java.lang.Object r2 = r0.L$0
            androidx.compose.foundation.lazy.LazyListState r2 = (androidx.compose.foundation.lazy.LazyListState) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5a
        L45:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier r8 = r5.awaitLayoutModifier
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r4
            java.lang.Object r8 = r8.waitForFirstLayout(r0)
            if (r8 != r1) goto L59
            return r1
        L59:
            r2 = r5
        L5a:
            androidx.compose.foundation.gestures.ScrollableState r8 = r2.scrollableState
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.L$2 = r2
            r0.label = r3
            java.lang.Object r6 = r8.scroll(r6, r7, r0)
            if (r6 != r1) goto L6c
            return r1
        L6c:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListState.scroll(androidx.compose.foundation.MutatePriority, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public float dispatchRawDelta(float f) {
        return this.scrollableState.dispatchRawDelta(f);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    private void setCanScrollForward(boolean z) {
        this.canScrollForward$delegate.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getCanScrollForward() {
        return ((Boolean) this.canScrollForward$delegate.getValue()).booleanValue();
    }

    private void setCanScrollBackward(boolean z) {
        this.canScrollBackward$delegate.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getCanScrollBackward() {
        return ((Boolean) this.canScrollBackward$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: getPlacementScopeInvalidator-zYiylxw$foundation_release, reason: not valid java name */
    public final MutableState m324getPlacementScopeInvalidatorzYiylxw$foundation_release() {
        return this.placementScopeInvalidator;
    }

    public final float onScroll$foundation_release(float f) {
        if ((f < 0.0f && !getCanScrollForward()) || (f > 0.0f && !getCanScrollBackward())) {
            return 0.0f;
        }
        if (Math.abs(this.scrollToBeConsumed) > 0.5f) {
            throw new IllegalStateException(("entered drag with non-zero pending scroll: " + this.scrollToBeConsumed).toString());
        }
        float f2 = this.scrollToBeConsumed + f;
        this.scrollToBeConsumed = f2;
        if (Math.abs(f2) > 0.5f) {
            LazyListMeasureResult lazyListMeasureResult = (LazyListMeasureResult) this.layoutInfoState.getValue();
            float f3 = this.scrollToBeConsumed;
            int iRound = Math.round(f3);
            LazyListMeasureResult lazyListMeasureResult2 = this.postLookaheadLayoutInfo;
            boolean zTryToApplyScrollWithoutRemeasure = lazyListMeasureResult.tryToApplyScrollWithoutRemeasure(iRound, !this.hasLookaheadPassOccurred);
            if (zTryToApplyScrollWithoutRemeasure && lazyListMeasureResult2 != null) {
                zTryToApplyScrollWithoutRemeasure = lazyListMeasureResult2.tryToApplyScrollWithoutRemeasure(iRound, true);
            }
            if (zTryToApplyScrollWithoutRemeasure) {
                applyMeasureResult$foundation_release(lazyListMeasureResult, this.hasLookaheadPassOccurred, true);
                ObservableScopeInvalidator.m354invalidateScopeimpl(this.placementScopeInvalidator);
                notifyPrefetchOnScroll(f3 - this.scrollToBeConsumed, lazyListMeasureResult);
            } else {
                Remeasurement remeasurement = this.remeasurement;
                if (remeasurement != null) {
                    remeasurement.forceRemeasure();
                }
                notifyPrefetchOnScroll(f3 - this.scrollToBeConsumed, getLayoutInfo());
            }
        }
        if (Math.abs(this.scrollToBeConsumed) <= 0.5f) {
            return f;
        }
        float f4 = f - this.scrollToBeConsumed;
        this.scrollToBeConsumed = 0.0f;
        return f4;
    }

    private final void notifyPrefetchOnScroll(float f, LazyListLayoutInfo lazyListLayoutInfo) {
        if (this.prefetchingEnabled) {
            this.prefetchStrategy.onScroll(this.prefetchScope, f, lazyListLayoutInfo);
        }
    }

    public static /* synthetic */ void applyMeasureResult$foundation_release$default(LazyListState lazyListState, LazyListMeasureResult lazyListMeasureResult, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        lazyListState.applyMeasureResult$foundation_release(lazyListMeasureResult, z, z2);
    }

    public final void applyMeasureResult$foundation_release(LazyListMeasureResult lazyListMeasureResult, boolean z, boolean z2) {
        if (!z && this.hasLookaheadPassOccurred) {
            this.postLookaheadLayoutInfo = lazyListMeasureResult;
            return;
        }
        if (z) {
            this.hasLookaheadPassOccurred = true;
        }
        setCanScrollBackward(lazyListMeasureResult.getCanScrollBackward());
        setCanScrollForward(lazyListMeasureResult.getCanScrollForward());
        this.scrollToBeConsumed -= lazyListMeasureResult.getConsumedScroll();
        this.layoutInfoState.setValue(lazyListMeasureResult);
        if (z2) {
            this.scrollPosition.updateScrollOffset(lazyListMeasureResult.getFirstVisibleItemScrollOffset());
        } else {
            this.scrollPosition.updateFromMeasureResult(lazyListMeasureResult);
            if (this.prefetchingEnabled) {
                this.prefetchStrategy.onVisibleItemsUpdated(this.prefetchScope, lazyListMeasureResult);
            }
        }
        if (z) {
            updateScrollDeltaForPostLookahead(lazyListMeasureResult.getScrollBackAmount(), lazyListMeasureResult.getDensity(), lazyListMeasureResult.getCoroutineScope());
        }
        this.numMeasurePasses++;
    }

    public final float getScrollDeltaBetweenPasses$foundation_release() {
        return ((Number) this._scrollDeltaBetweenPasses.getValue()).floatValue();
    }

    private final void updateScrollDeltaForPostLookahead(float f, Density density, CoroutineScope coroutineScope) {
        if (f <= density.mo210toPx0680j_4(LazyListStateKt.DeltaThresholdForScrollAnimation)) {
            return;
        }
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            float fFloatValue = ((Number) this._scrollDeltaBetweenPasses.getValue()).floatValue();
            if (this._scrollDeltaBetweenPasses.isRunning()) {
                this._scrollDeltaBetweenPasses = AnimationStateKt.copy$default(this._scrollDeltaBetweenPasses, fFloatValue - f, 0.0f, 0L, 0L, false, 30, (Object) null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new LazyListState$updateScrollDeltaForPostLookahead$2$1(this, null), 3, null);
            } else {
                this._scrollDeltaBetweenPasses = new AnimationState(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), Float.valueOf(-f), null, 0L, 0L, false, 60, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new LazyListState$updateScrollDeltaForPostLookahead$2$2(this, null), 3, null);
            }
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            throw th;
        }
    }

    public final int updateScrollPositionIfTheFirstItemWasMoved$foundation_release(LazyListItemProvider lazyListItemProvider, int i) {
        return this.scrollPosition.updateScrollPositionIfTheFirstItemWasMoved(lazyListItemProvider, i);
    }

    /* JADX INFO: compiled from: LazyListState.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver getSaver() {
            return LazyListState.Saver;
        }
    }
}
