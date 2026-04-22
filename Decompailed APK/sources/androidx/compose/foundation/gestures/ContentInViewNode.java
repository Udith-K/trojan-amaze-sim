package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.relocation.BringIntoViewResponder;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import ch.qos.logback.core.CoreConstants;
import java.util.concurrent.CancellationException;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobKt__JobKt;

/* JADX INFO: compiled from: ContentInViewNode.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ContentInViewNode extends Modifier.Node implements BringIntoViewResponder, LayoutAwareModifierNode, CompositionLocalConsumerModifierNode {
    private BringIntoViewSpec bringIntoViewSpec;
    private LayoutCoordinates focusedChild;
    private Rect focusedChildBoundsFromPreviousRemeasure;
    private boolean isAnimationRunning;
    private Orientation orientation;
    private boolean reverseDirection;
    private final ScrollingLogic scrollingLogic;
    private final boolean shouldAutoInvalidate;
    private boolean trackingFocusedChild;
    private final BringIntoViewRequestPriorityQueue bringIntoViewRequests = new BringIntoViewRequestPriorityQueue();
    private long viewportSize = IntSize.Companion.m2480getZeroYbymL2g();

    /* JADX INFO: compiled from: ContentInViewNode.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public /* synthetic */ void onPlaced(LayoutCoordinates layoutCoordinates) {
        LayoutAwareModifierNode.CC.$default$onPlaced(this, layoutCoordinates);
    }

    public ContentInViewNode(Orientation orientation, ScrollingLogic scrollingLogic, boolean z, BringIntoViewSpec bringIntoViewSpec) {
        this.orientation = orientation;
        this.scrollingLogic = scrollingLogic;
        this.reverseDirection = z;
        this.bringIntoViewSpec = bringIntoViewSpec;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    /* JADX INFO: renamed from: getViewportSize-YbymL2g$foundation_release, reason: not valid java name */
    public final long m173getViewportSizeYbymL2g$foundation_release() {
        return this.viewportSize;
    }

    @Override // androidx.compose.foundation.relocation.BringIntoViewResponder
    public Rect calculateRectForParent(Rect rect) {
        if (IntSize.m2474equalsimpl0(this.viewportSize, IntSize.Companion.m2480getZeroYbymL2g())) {
            throw new IllegalStateException("Expected BringIntoViewRequester to not be used before parents are placed.");
        }
        return m169computeDestinationO0kMr_c(rect, this.viewportSize);
    }

    private final BringIntoViewSpec requireBringIntoViewSpec() {
        BringIntoViewSpec bringIntoViewSpec = this.bringIntoViewSpec;
        return bringIntoViewSpec == null ? (BringIntoViewSpec) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, BringIntoViewSpec_androidKt.getLocalBringIntoViewSpec()) : bringIntoViewSpec;
    }

    @Override // androidx.compose.foundation.relocation.BringIntoViewResponder
    public Object bringChildIntoView(Function0 function0, Continuation continuation) {
        Rect rect = (Rect) function0.invoke();
        if (rect == null || m171isMaxVisibleO0kMr_c$default(this, rect, 0L, 1, null)) {
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        if (this.bringIntoViewRequests.enqueue(new Request(function0, cancellableContinuationImpl)) && !this.isAnimationRunning) {
            launchAnimation();
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    public final void onFocusBoundsChanged(LayoutCoordinates layoutCoordinates) {
        this.focusedChild = layoutCoordinates;
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    /* JADX INFO: renamed from: onRemeasured-ozmzZPI, reason: not valid java name */
    public void mo174onRemeasuredozmzZPI(long j) {
        Rect focusedChildBounds;
        long j2 = this.viewportSize;
        this.viewportSize = j;
        if (m167compareToTemP2vQ(j, j2) < 0 && (focusedChildBounds = getFocusedChildBounds()) != null) {
            Rect rect = this.focusedChildBoundsFromPreviousRemeasure;
            if (rect == null) {
                rect = focusedChildBounds;
            }
            if (!this.isAnimationRunning && !this.trackingFocusedChild && m170isMaxVisibleO0kMr_c(rect, j2) && !m170isMaxVisibleO0kMr_c(focusedChildBounds, j)) {
                this.trackingFocusedChild = true;
                launchAnimation();
            }
            this.focusedChildBoundsFromPreviousRemeasure = focusedChildBounds;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getFocusedChildBounds() {
        if (!isAttached()) {
            return null;
        }
        LayoutCoordinates layoutCoordinatesRequireLayoutCoordinates = DelegatableNodeKt.requireLayoutCoordinates(this);
        LayoutCoordinates layoutCoordinates = this.focusedChild;
        if (layoutCoordinates != null) {
            if (!layoutCoordinates.isAttached()) {
                layoutCoordinates = null;
            }
            if (layoutCoordinates != null) {
                return layoutCoordinatesRequireLayoutCoordinates.localBoundingBoxOf(layoutCoordinates, false);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchAnimation() {
        BringIntoViewSpec bringIntoViewSpecRequireBringIntoViewSpec = requireBringIntoViewSpec();
        if (this.isAnimationRunning) {
            throw new IllegalStateException("launchAnimation called when previous animation was running");
        }
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, CoroutineStart.UNDISPATCHED, new AnonymousClass2(new UpdatableAnimationState(bringIntoViewSpecRequireBringIntoViewSpec.getScrollAnimationSpec()), bringIntoViewSpecRequireBringIntoViewSpec, null), 1, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2, reason: invalid class name */
    /* JADX INFO: compiled from: ContentInViewNode.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ UpdatableAnimationState $animationState;
        final /* synthetic */ BringIntoViewSpec $bringIntoViewSpec;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(UpdatableAnimationState updatableAnimationState, BringIntoViewSpec bringIntoViewSpec, Continuation continuation) {
            super(2, continuation);
            this.$animationState = updatableAnimationState;
            this.$bringIntoViewSpec = bringIntoViewSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = ContentInViewNode.this.new AnonymousClass2(this.$animationState, this.$bringIntoViewSpec, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Job job = JobKt.getJob(((CoroutineScope) this.L$0).getCoroutineContext());
                        ContentInViewNode.this.isAnimationRunning = true;
                        ScrollingLogic scrollingLogic = ContentInViewNode.this.scrollingLogic;
                        MutatePriority mutatePriority = MutatePriority.Default;
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$animationState, ContentInViewNode.this, this.$bringIntoViewSpec, job, null);
                        this.label = 1;
                        if (scrollingLogic.scroll(mutatePriority, anonymousClass1, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    ContentInViewNode.this.bringIntoViewRequests.resumeAndRemoveAll();
                    ContentInViewNode.this.isAnimationRunning = false;
                    ContentInViewNode.this.bringIntoViewRequests.cancelAndRemoveAll(null);
                    ContentInViewNode.this.trackingFocusedChild = false;
                    return Unit.INSTANCE;
                } catch (CancellationException e) {
                    throw e;
                }
            } catch (Throwable th) {
                ContentInViewNode.this.isAnimationRunning = false;
                ContentInViewNode.this.bringIntoViewRequests.cancelAndRemoveAll(null);
                ContentInViewNode.this.trackingFocusedChild = false;
                throw th;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: ContentInViewNode.kt */
        static final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ Job $animationJob;
            final /* synthetic */ UpdatableAnimationState $animationState;
            final /* synthetic */ BringIntoViewSpec $bringIntoViewSpec;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ ContentInViewNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(UpdatableAnimationState updatableAnimationState, ContentInViewNode contentInViewNode, BringIntoViewSpec bringIntoViewSpec, Job job, Continuation continuation) {
                super(2, continuation);
                this.$animationState = updatableAnimationState;
                this.this$0 = contentInViewNode;
                this.$bringIntoViewSpec = bringIntoViewSpec;
                this.$animationJob = job;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$animationState, this.this$0, this.$bringIntoViewSpec, this.$animationJob, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(NestedScrollScope nestedScrollScope, Continuation continuation) {
                return ((AnonymousClass1) create(nestedScrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final NestedScrollScope nestedScrollScope = (NestedScrollScope) this.L$0;
                    this.$animationState.setValue(this.this$0.calculateScrollDelta(this.$bringIntoViewSpec));
                    final UpdatableAnimationState updatableAnimationState = this.$animationState;
                    final ContentInViewNode contentInViewNode = this.this$0;
                    final Job job = this.$animationJob;
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.ContentInViewNode.launchAnimation.2.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                            invoke(((Number) obj2).floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(float f) {
                            float f2 = contentInViewNode.reverseDirection ? 1.0f : -1.0f;
                            ScrollingLogic scrollingLogic = contentInViewNode.scrollingLogic;
                            float fM235toFloatk4lQ0M = f2 * scrollingLogic.m235toFloatk4lQ0M(scrollingLogic.m233reverseIfNeededMKHz9U(nestedScrollScope.mo202scrollByOzD1aCk(scrollingLogic.m233reverseIfNeededMKHz9U(scrollingLogic.m236toOffsettuRUvjQ(f2 * f)), NestedScrollSource.Companion.m1643getUserInputWNlRxjI())));
                            if (Math.abs(fM235toFloatk4lQ0M) < Math.abs(f)) {
                                JobKt__JobKt.cancel$default(job, "Scroll animation cancelled because scroll was not consumed (" + fM235toFloatk4lQ0M + " < " + f + CoreConstants.RIGHT_PARENTHESIS_CHAR, null, 2, null);
                            }
                        }
                    };
                    final ContentInViewNode contentInViewNode2 = this.this$0;
                    final UpdatableAnimationState updatableAnimationState2 = this.$animationState;
                    final BringIntoViewSpec bringIntoViewSpec = this.$bringIntoViewSpec;
                    Function0 function0 = new Function0() { // from class: androidx.compose.foundation.gestures.ContentInViewNode.launchAnimation.2.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Object invoke() {
                            m175invoke();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                        public final void m175invoke() {
                            BringIntoViewRequestPriorityQueue bringIntoViewRequestPriorityQueue = contentInViewNode2.bringIntoViewRequests;
                            ContentInViewNode contentInViewNode3 = contentInViewNode2;
                            while (true) {
                                if (!bringIntoViewRequestPriorityQueue.requests.isNotEmpty()) {
                                    break;
                                }
                                Rect rect = (Rect) ((Request) bringIntoViewRequestPriorityQueue.requests.last()).getCurrentBounds().invoke();
                                if (!(rect == null ? true : ContentInViewNode.m171isMaxVisibleO0kMr_c$default(contentInViewNode3, rect, 0L, 1, null))) {
                                    break;
                                } else {
                                    ((Request) bringIntoViewRequestPriorityQueue.requests.removeAt(bringIntoViewRequestPriorityQueue.requests.getSize() - 1)).getContinuation().resumeWith(Result.m2639constructorimpl(Unit.INSTANCE));
                                }
                            }
                            if (contentInViewNode2.trackingFocusedChild) {
                                Rect focusedChildBounds = contentInViewNode2.getFocusedChildBounds();
                                if (focusedChildBounds != null && ContentInViewNode.m171isMaxVisibleO0kMr_c$default(contentInViewNode2, focusedChildBounds, 0L, 1, null)) {
                                    contentInViewNode2.trackingFocusedChild = false;
                                }
                            }
                            updatableAnimationState2.setValue(contentInViewNode2.calculateScrollDelta(bringIntoViewSpec));
                        }
                    };
                    this.label = 1;
                    if (updatableAnimationState.animateToZero(function1, function0, this) == coroutine_suspended) {
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float calculateScrollDelta(BringIntoViewSpec bringIntoViewSpec) {
        if (IntSize.m2474equalsimpl0(this.viewportSize, IntSize.Companion.m2480getZeroYbymL2g())) {
            return 0.0f;
        }
        Rect rectFindBringIntoViewRequest = findBringIntoViewRequest();
        if (rectFindBringIntoViewRequest == null) {
            rectFindBringIntoViewRequest = this.trackingFocusedChild ? getFocusedChildBounds() : null;
            if (rectFindBringIntoViewRequest == null) {
                return 0.0f;
            }
        }
        long jM2483toSizeozmzZPI = IntSizeKt.m2483toSizeozmzZPI(this.viewportSize);
        int i = WhenMappings.$EnumSwitchMapping$0[this.orientation.ordinal()];
        if (i == 1) {
            return bringIntoViewSpec.calculateScrollDistance(rectFindBringIntoViewRequest.getTop(), rectFindBringIntoViewRequest.getBottom() - rectFindBringIntoViewRequest.getTop(), Size.m1194getHeightimpl(jM2483toSizeozmzZPI));
        }
        if (i == 2) {
            return bringIntoViewSpec.calculateScrollDistance(rectFindBringIntoViewRequest.getLeft(), rectFindBringIntoViewRequest.getRight() - rectFindBringIntoViewRequest.getLeft(), Size.m1196getWidthimpl(jM2483toSizeozmzZPI));
        }
        throw new NoWhenBranchMatchedException();
    }

    private final Rect findBringIntoViewRequest() {
        MutableVector mutableVector = this.bringIntoViewRequests.requests;
        int size = mutableVector.getSize();
        Rect rect = null;
        if (size > 0) {
            int i = size - 1;
            Object[] content = mutableVector.getContent();
            do {
                Rect rect2 = (Rect) ((Request) content[i]).getCurrentBounds().invoke();
                if (rect2 != null) {
                    if (m168compareToiLBOSCw(rect2.m1179getSizeNHjbRc(), IntSizeKt.m2483toSizeozmzZPI(this.viewportSize)) > 0) {
                        return rect == null ? rect2 : rect;
                    }
                    rect = rect2;
                }
                i--;
            } while (i >= 0);
        }
        return rect;
    }

    /* JADX INFO: renamed from: computeDestination-O0kMr_c, reason: not valid java name */
    private final Rect m169computeDestinationO0kMr_c(Rect rect, long j) {
        return rect.m1181translatek4lQ0M(Offset.m1167unaryMinusF1C5BW0(m172relocationOffsetBMxPBkI(rect, j)));
    }

    /* JADX INFO: renamed from: isMaxVisible-O0kMr_c$default, reason: not valid java name */
    static /* synthetic */ boolean m171isMaxVisibleO0kMr_c$default(ContentInViewNode contentInViewNode, Rect rect, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = contentInViewNode.viewportSize;
        }
        return contentInViewNode.m170isMaxVisibleO0kMr_c(rect, j);
    }

    /* JADX INFO: renamed from: isMaxVisible-O0kMr_c, reason: not valid java name */
    private final boolean m170isMaxVisibleO0kMr_c(Rect rect, long j) {
        long jM172relocationOffsetBMxPBkI = m172relocationOffsetBMxPBkI(rect, j);
        return Math.abs(Offset.m1159getXimpl(jM172relocationOffsetBMxPBkI)) <= 0.5f && Math.abs(Offset.m1160getYimpl(jM172relocationOffsetBMxPBkI)) <= 0.5f;
    }

    /* JADX INFO: renamed from: relocationOffset-BMxPBkI, reason: not valid java name */
    private final long m172relocationOffsetBMxPBkI(Rect rect, long j) {
        long jM2483toSizeozmzZPI = IntSizeKt.m2483toSizeozmzZPI(j);
        int i = WhenMappings.$EnumSwitchMapping$0[this.orientation.ordinal()];
        if (i == 1) {
            return OffsetKt.Offset(0.0f, requireBringIntoViewSpec().calculateScrollDistance(rect.getTop(), rect.getBottom() - rect.getTop(), Size.m1194getHeightimpl(jM2483toSizeozmzZPI)));
        }
        if (i == 2) {
            return OffsetKt.Offset(requireBringIntoViewSpec().calculateScrollDistance(rect.getLeft(), rect.getRight() - rect.getLeft(), Size.m1196getWidthimpl(jM2483toSizeozmzZPI)), 0.0f);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: compareTo-TemP2vQ, reason: not valid java name */
    private final int m167compareToTemP2vQ(long j, long j2) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.orientation.ordinal()];
        if (i == 1) {
            return Intrinsics.compare(IntSize.m2475getHeightimpl(j), IntSize.m2475getHeightimpl(j2));
        }
        if (i == 2) {
            return Intrinsics.compare(IntSize.m2476getWidthimpl(j), IntSize.m2476getWidthimpl(j2));
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: compareTo-iLBOSCw, reason: not valid java name */
    private final int m168compareToiLBOSCw(long j, long j2) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.orientation.ordinal()];
        if (i == 1) {
            return Float.compare(Size.m1194getHeightimpl(j), Size.m1194getHeightimpl(j2));
        }
        if (i == 2) {
            return Float.compare(Size.m1196getWidthimpl(j), Size.m1196getWidthimpl(j2));
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void update(Orientation orientation, boolean z, BringIntoViewSpec bringIntoViewSpec) {
        this.orientation = orientation;
        this.reverseDirection = z;
        this.bringIntoViewSpec = bringIntoViewSpec;
    }

    /* JADX INFO: compiled from: ContentInViewNode.kt */
    public static final class Request {
        private final CancellableContinuation continuation;
        private final Function0 currentBounds;

        public Request(Function0 function0, CancellableContinuation cancellableContinuation) {
            this.currentBounds = function0;
            this.continuation = cancellableContinuation;
        }

        public final Function0 getCurrentBounds() {
            return this.currentBounds;
        }

        public final CancellableContinuation getContinuation() {
            return this.continuation;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0050  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String toString() {
            /*
                r4 = this;
                kotlinx.coroutines.CancellableContinuation r0 = r4.continuation
                kotlin.coroutines.CoroutineContext r0 = r0.getContext()
                kotlinx.coroutines.CoroutineName$Key r1 = kotlinx.coroutines.CoroutineName.Key
                kotlin.coroutines.CoroutineContext$Element r0 = r0.get(r1)
                kotlinx.coroutines.CoroutineName r0 = (kotlinx.coroutines.CoroutineName) r0
                if (r0 == 0) goto L15
                java.lang.String r0 = r0.getName()
                goto L16
            L15:
                r0 = 0
            L16:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Request@"
                r1.append(r2)
                int r2 = r4.hashCode()
                r3 = 16
                int r3 = kotlin.text.CharsKt.checkRadix(r3)
                java.lang.String r2 = java.lang.Integer.toString(r2, r3)
                java.lang.String r3 = "toString(this, checkRadix(radix))"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                r1.append(r2)
                if (r0 == 0) goto L50
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                r3 = 91
                r2.append(r3)
                r2.append(r0)
                java.lang.String r0 = "]("
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                if (r0 != 0) goto L52
            L50:
                java.lang.String r0 = "("
            L52:
                r1.append(r0)
                java.lang.String r0 = "currentBounds()="
                r1.append(r0)
                kotlin.jvm.functions.Function0 r0 = r4.currentBounds
                java.lang.Object r0 = r0.invoke()
                r1.append(r0)
                java.lang.String r0 = ", continuation="
                r1.append(r0)
                kotlinx.coroutines.CancellableContinuation r0 = r4.continuation
                r1.append(r0)
                r0 = 41
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ContentInViewNode.Request.toString():java.lang.String");
        }
    }
}
