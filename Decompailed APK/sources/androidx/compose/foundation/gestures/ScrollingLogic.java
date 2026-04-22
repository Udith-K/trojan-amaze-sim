package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ScrollingLogic {
    private FlingBehavior flingBehavior;
    private NestedScrollDispatcher nestedScrollDispatcher;
    private Orientation orientation;
    private OverscrollEffect overscrollEffect;
    private boolean reverseDirection;
    private ScrollableState scrollableState;
    private int latestScrollSource = NestedScrollSource.Companion.m1643getUserInputWNlRxjI();
    private ScrollScope outerStateScope = ScrollableKt.NoOpScrollScope;
    private final ScrollingLogic$nestedScrollScope$1 nestedScrollScope = new NestedScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollingLogic$nestedScrollScope$1
        @Override // androidx.compose.foundation.gestures.NestedScrollScope
        /* JADX INFO: renamed from: scrollBy-OzD1aCk */
        public long mo202scrollByOzD1aCk(long j, int i) {
            return this.this$0.m226performScroll3eAAhYA(this.this$0.outerStateScope, j, i);
        }

        @Override // androidx.compose.foundation.gestures.NestedScrollScope
        /* JADX INFO: renamed from: scrollByWithOverscroll-OzD1aCk */
        public long mo203scrollByWithOverscrollOzD1aCk(long j, int i) {
            this.this$0.latestScrollSource = i;
            OverscrollEffect overscrollEffect = this.this$0.overscrollEffect;
            if (overscrollEffect == null || !this.this$0.getShouldDispatchOverscroll()) {
                return this.this$0.m226performScroll3eAAhYA(this.this$0.outerStateScope, j, i);
            }
            return overscrollEffect.mo93applyToScrollRhakbz0(j, this.this$0.latestScrollSource, this.this$0.performScrollForOverscroll);
        }
    };
    private final Function1 performScrollForOverscroll = new Function1() { // from class: androidx.compose.foundation.gestures.ScrollingLogic$performScrollForOverscroll$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Offset.m1150boximpl(m238invokeMKHz9U(((Offset) obj).m1168unboximpl()));
        }

        /* JADX INFO: renamed from: invoke-MK-Hz9U, reason: not valid java name */
        public final long m238invokeMKHz9U(long j) {
            ScrollScope scrollScope = this.this$0.outerStateScope;
            ScrollingLogic scrollingLogic = this.this$0;
            return scrollingLogic.m226performScroll3eAAhYA(scrollScope, j, scrollingLogic.latestScrollSource);
        }
    };

    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.foundation.gestures.ScrollingLogic$nestedScrollScope$1] */
    public ScrollingLogic(ScrollableState scrollableState, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, Orientation orientation, boolean z, NestedScrollDispatcher nestedScrollDispatcher) {
        this.scrollableState = scrollableState;
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        this.orientation = orientation;
        this.reverseDirection = z;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
    }

    /* JADX INFO: renamed from: toOffset-tuRUvjQ, reason: not valid java name */
    public final long m236toOffsettuRUvjQ(float f) {
        if (f == 0.0f) {
            return Offset.Companion.m1171getZeroF1C5BW0();
        }
        return this.orientation == Orientation.Horizontal ? OffsetKt.Offset(f, 0.0f) : OffsetKt.Offset(0.0f, f);
    }

    /* JADX INFO: renamed from: singleAxisOffset-MK-Hz9U, reason: not valid java name */
    public final long m234singleAxisOffsetMKHz9U(long j) {
        return this.orientation == Orientation.Horizontal ? Offset.m1153copydBAh8RU$default(j, 0.0f, 0.0f, 1, null) : Offset.m1153copydBAh8RU$default(j, 0.0f, 0.0f, 2, null);
    }

    /* JADX INFO: renamed from: toFloat-k-4lQ0M, reason: not valid java name */
    public final float m235toFloatk4lQ0M(long j) {
        return this.orientation == Orientation.Horizontal ? Offset.m1159getXimpl(j) : Offset.m1160getYimpl(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toFloat-TH1AsA0, reason: not valid java name */
    public final float m228toFloatTH1AsA0(long j) {
        return this.orientation == Orientation.Horizontal ? Velocity.m2516getXimpl(j) : Velocity.m2517getYimpl(j);
    }

    /* JADX INFO: renamed from: singleAxisVelocity-AH228Gc, reason: not valid java name */
    private final long m227singleAxisVelocityAH228Gc(long j) {
        return this.orientation == Orientation.Horizontal ? Velocity.m2513copyOhffZ5M$default(j, 0.0f, 0.0f, 1, null) : Velocity.m2513copyOhffZ5M$default(j, 0.0f, 0.0f, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: update-QWom1Mo, reason: not valid java name */
    public final long m229updateQWom1Mo(long j, float f) {
        return this.orientation == Orientation.Horizontal ? Velocity.m2513copyOhffZ5M$default(j, f, 0.0f, 2, null) : Velocity.m2513copyOhffZ5M$default(j, 0.0f, f, 1, null);
    }

    public final float reverseIfNeeded(float f) {
        return this.reverseDirection ? f * (-1) : f;
    }

    /* JADX INFO: renamed from: reverseIfNeeded-MK-Hz9U, reason: not valid java name */
    public final long m233reverseIfNeededMKHz9U(long j) {
        return this.reverseDirection ? Offset.m1165timestuRUvjQ(j, -1.0f) : j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: performScroll-3eAAhYA, reason: not valid java name */
    public final long m226performScroll3eAAhYA(ScrollScope scrollScope, long j, int i) {
        long jM1639dispatchPreScrollOzD1aCk = this.nestedScrollDispatcher.m1639dispatchPreScrollOzD1aCk(j, i);
        long jM1163minusMKHz9U = Offset.m1163minusMKHz9U(j, jM1639dispatchPreScrollOzD1aCk);
        long jM233reverseIfNeededMKHz9U = m233reverseIfNeededMKHz9U(m236toOffsettuRUvjQ(scrollScope.scrollBy(m235toFloatk4lQ0M(m233reverseIfNeededMKHz9U(m234singleAxisOffsetMKHz9U(jM1163minusMKHz9U))))));
        return Offset.m1164plusMKHz9U(Offset.m1164plusMKHz9U(jM1639dispatchPreScrollOzD1aCk, jM233reverseIfNeededMKHz9U), this.nestedScrollDispatcher.m1637dispatchPostScrollDzOQY0M(jM233reverseIfNeededMKHz9U, Offset.m1163minusMKHz9U(jM1163minusMKHz9U, jM233reverseIfNeededMKHz9U), i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getShouldDispatchOverscroll() {
        return this.scrollableState.getCanScrollForward() || this.scrollableState.getCanScrollBackward();
    }

    /* JADX INFO: renamed from: performRawScroll-MK-Hz9U, reason: not valid java name */
    public final long m232performRawScrollMKHz9U(long j) {
        if (this.scrollableState.isScrollInProgress()) {
            return Offset.Companion.m1171getZeroF1C5BW0();
        }
        return m236toOffsettuRUvjQ(reverseIfNeeded(this.scrollableState.dispatchRawDelta(reverseIfNeeded(m235toFloatk4lQ0M(j)))));
    }

    /* JADX INFO: renamed from: onDragStopped-sF-c-tU, reason: not valid java name */
    public final Object m231onDragStoppedsFctU(long j, Continuation continuation) {
        long jM227singleAxisVelocityAH228Gc = m227singleAxisVelocityAH228Gc(j);
        ScrollingLogic$onDragStopped$performFling$1 scrollingLogic$onDragStopped$performFling$1 = new ScrollingLogic$onDragStopped$performFling$1(this, null);
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        if (overscrollEffect != null && getShouldDispatchOverscroll()) {
            Object objMo92applyToFlingBMRW4eQ = overscrollEffect.mo92applyToFlingBMRW4eQ(jM227singleAxisVelocityAH228Gc, scrollingLogic$onDragStopped$performFling$1, continuation);
            return objMo92applyToFlingBMRW4eQ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo92applyToFlingBMRW4eQ : Unit.INSTANCE;
        }
        Object objInvoke = scrollingLogic$onDragStopped$performFling$1.invoke(Velocity.m2510boximpl(jM227singleAxisVelocityAH228Gc), continuation);
        return objInvoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvoke : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: doFlingAnimation-QWom1Mo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m230doFlingAnimationQWom1Mo(long r12, kotlin.coroutines.Continuation r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1
            if (r0 == 0) goto L13
            r0 = r14
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1 r0 = (androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1 r0 = new androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1
            r0.<init>(r11, r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r12 = r0.L$0
            kotlin.jvm.internal.Ref$LongRef r12 = (kotlin.jvm.internal.Ref$LongRef) r12
            kotlin.ResultKt.throwOnFailure(r14)
            goto L57
        L2d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L35:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$LongRef r14 = new kotlin.jvm.internal.Ref$LongRef
            r14.<init>()
            r14.element = r12
            androidx.compose.foundation.MutatePriority r2 = androidx.compose.foundation.MutatePriority.Default
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2 r10 = new androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2
            r9 = 0
            r4 = r10
            r5 = r11
            r6 = r14
            r7 = r12
            r4.<init>(r5, r6, r7, r9)
            r0.L$0 = r14
            r0.label = r3
            java.lang.Object r12 = r11.scroll(r2, r10, r0)
            if (r12 != r1) goto L56
            return r1
        L56:
            r12 = r14
        L57:
            long r12 = r12.element
            androidx.compose.ui.unit.Velocity r12 = androidx.compose.ui.unit.Velocity.m2510boximpl(r12)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic.m230doFlingAnimationQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean shouldScrollImmediately() {
        if (!this.scrollableState.isScrollInProgress()) {
            OverscrollEffect overscrollEffect = this.overscrollEffect;
            if (!(overscrollEffect != null ? overscrollEffect.isInProgress() : false)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollingLogic$scroll$2, reason: invalid class name */
    /* JADX INFO: compiled from: Scrollable.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function2 $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Function2 function2, Continuation continuation) {
            super(2, continuation);
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = ScrollingLogic.this.new AnonymousClass2(this.$block, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation continuation) {
            return ((AnonymousClass2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ScrollingLogic.this.outerStateScope = (ScrollScope) this.L$0;
                Function2 function2 = this.$block;
                ScrollingLogic$nestedScrollScope$1 scrollingLogic$nestedScrollScope$1 = ScrollingLogic.this.nestedScrollScope;
                this.label = 1;
                if (function2.invoke(scrollingLogic$nestedScrollScope$1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object scroll(MutatePriority mutatePriority, Function2 function2, Continuation continuation) {
        Object objScroll = this.scrollableState.scroll(mutatePriority, new AnonymousClass2(function2, null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    public final boolean update(ScrollableState scrollableState, Orientation orientation, OverscrollEffect overscrollEffect, boolean z, FlingBehavior flingBehavior, NestedScrollDispatcher nestedScrollDispatcher) {
        boolean z2;
        boolean z3 = true;
        if (Intrinsics.areEqual(this.scrollableState, scrollableState)) {
            z2 = false;
        } else {
            this.scrollableState = scrollableState;
            z2 = true;
        }
        this.overscrollEffect = overscrollEffect;
        if (this.orientation != orientation) {
            this.orientation = orientation;
            z2 = true;
        }
        if (this.reverseDirection != z) {
            this.reverseDirection = z;
        } else {
            z3 = z2;
        }
        this.flingBehavior = flingBehavior;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        return z3;
    }

    public final boolean isVertical() {
        return this.orientation == Orientation.Vertical;
    }
}
