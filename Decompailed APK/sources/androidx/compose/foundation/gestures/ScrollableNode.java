package androidx.compose.foundation.gestures;

import android.view.KeyEvent;
import androidx.compose.animation.SplineBasedDecayKt;
import androidx.compose.foundation.FocusedBoundsObserverNode;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.relocation.BringIntoViewResponderNode;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusTargetModifierNodeKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollNodeKt;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
final class ScrollableNode extends DragGestureNode implements ObserverModifierNode, CompositionLocalConsumerModifierNode, FocusPropertiesModifierNode, KeyInputModifierNode, SemanticsModifierNode {
    private final ContentInViewNode contentInViewNode;
    private final DefaultFlingBehavior defaultFlingBehavior;
    private FlingBehavior flingBehavior;
    private final ScrollableNestedScrollConnection nestedScrollConnection;
    private final NestedScrollDispatcher nestedScrollDispatcher;
    private OverscrollEffect overscrollEffect;
    private Function2 scrollByAction;
    private Function2 scrollByOffsetAction;
    private ScrollConfig scrollConfig;
    private final ScrollableContainerNode scrollableContainerNode;
    private final ScrollingLogic scrollingLogic;
    private final boolean shouldAutoInvalidate;

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldClearDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldClearDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldMergeDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldMergeDescendantSemantics(this);
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: onDragStarted-k-4lQ0M */
    public void mo186onDragStartedk4lQ0M(long j) {
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo */
    public boolean mo85onPreKeyEventZmokQxo(KeyEvent keyEvent) {
        return false;
    }

    public ScrollableNode(ScrollableState scrollableState, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, Orientation orientation, boolean z, boolean z2, MutableInteractionSource mutableInteractionSource, BringIntoViewSpec bringIntoViewSpec) {
        super(ScrollableKt.CanDragCalculation, z, mutableInteractionSource, orientation);
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        NestedScrollDispatcher nestedScrollDispatcher = new NestedScrollDispatcher();
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        this.scrollableContainerNode = (ScrollableContainerNode) delegate(new ScrollableContainerNode(z));
        DefaultFlingBehavior defaultFlingBehavior = new DefaultFlingBehavior(SplineBasedDecayKt.splineBasedDecay(ScrollableKt.UnityDensity), null, 2, null);
        this.defaultFlingBehavior = defaultFlingBehavior;
        OverscrollEffect overscrollEffect2 = this.overscrollEffect;
        FlingBehavior flingBehavior2 = this.flingBehavior;
        ScrollingLogic scrollingLogic = new ScrollingLogic(scrollableState, overscrollEffect2, flingBehavior2 == null ? defaultFlingBehavior : flingBehavior2, orientation, z2, nestedScrollDispatcher);
        this.scrollingLogic = scrollingLogic;
        ScrollableNestedScrollConnection scrollableNestedScrollConnection = new ScrollableNestedScrollConnection(scrollingLogic, z);
        this.nestedScrollConnection = scrollableNestedScrollConnection;
        ContentInViewNode contentInViewNode = (ContentInViewNode) delegate(new ContentInViewNode(orientation, scrollingLogic, z2, bringIntoViewSpec));
        this.contentInViewNode = contentInViewNode;
        delegate(NestedScrollNodeKt.nestedScrollModifierNode(scrollableNestedScrollConnection, nestedScrollDispatcher));
        delegate(FocusTargetModifierNodeKt.FocusTargetModifierNode());
        delegate(new BringIntoViewResponderNode(contentInViewNode));
        delegate(new FocusedBoundsObserverNode(new Function1() { // from class: androidx.compose.foundation.gestures.ScrollableNode.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((LayoutCoordinates) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(LayoutCoordinates layoutCoordinates) {
                ScrollableNode.this.contentInViewNode.onFocusBoundsChanged(layoutCoordinates);
            }
        }));
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public Object drag(Function2 function2, Continuation continuation) {
        ScrollingLogic scrollingLogic = this.scrollingLogic;
        Object objScroll = scrollingLogic.scroll(MutatePriority.UserInput, new ScrollableNode$drag$2$1(function2, scrollingLogic, null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: onDragStopped-TH1AsA0 */
    public void mo187onDragStoppedTH1AsA0(long j) {
        BuildersKt__Builders_commonKt.launch$default(this.nestedScrollDispatcher.getCoroutineScope(), null, null, new ScrollableNode$onDragStopped$1(this, j, null), 3, null);
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public boolean startDragImmediately() {
        return this.scrollingLogic.shouldScrollImmediately();
    }

    public final void update(ScrollableState scrollableState, Orientation orientation, OverscrollEffect overscrollEffect, boolean z, boolean z2, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, BringIntoViewSpec bringIntoViewSpec) {
        boolean z3;
        if (getEnabled() != z) {
            this.nestedScrollConnection.setEnabled(z);
            this.scrollableContainerNode.update(z);
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z4 = z3;
        boolean zUpdate = this.scrollingLogic.update(scrollableState, orientation, overscrollEffect, z2, flingBehavior == null ? this.defaultFlingBehavior : flingBehavior, this.nestedScrollDispatcher);
        this.contentInViewNode.update(orientation, z2, bringIntoViewSpec);
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        update(ScrollableKt.CanDragCalculation, z, mutableInteractionSource, this.scrollingLogic.isVertical() ? Orientation.Vertical : Orientation.Horizontal, zUpdate);
        if (z4) {
            clearScrollSemanticsActions();
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateDefaultFlingBehavior();
        this.scrollConfig = AndroidScrollable_androidKt.platformScrollConfig(this);
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        updateDefaultFlingBehavior();
    }

    private final void updateDefaultFlingBehavior() {
        ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.foundation.gestures.ScrollableNode.updateDefaultFlingBehavior.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m222invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m222invoke() {
                ScrollableNode.this.defaultFlingBehavior.setFlingDecay(SplineBasedDecayKt.splineBasedDecay((Density) CompositionLocalConsumerModifierNodeKt.currentValueOf(ScrollableNode.this, CompositionLocalsKt.getLocalDensity())));
            }
        });
    }

    @Override // androidx.compose.ui.focus.FocusPropertiesModifierNode
    public void applyFocusProperties(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo */
    public boolean mo83onKeyEventZmokQxo(KeyEvent keyEvent) {
        long jOffset;
        if (getEnabled()) {
            long jM1626getKeyZmokQxo = KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent);
            Key.Companion companion = Key.Companion;
            if ((Key.m1599equalsimpl0(jM1626getKeyZmokQxo, companion.m1612getPageDownEK5gGoQ()) || Key.m1599equalsimpl0(KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent), companion.m1613getPageUpEK5gGoQ())) && KeyEventType.m1622equalsimpl0(KeyEvent_androidKt.m1627getTypeZmokQxo(keyEvent), KeyEventType.Companion.m1623getKeyDownCS__XNY()) && !KeyEvent_androidKt.m1630isCtrlPressedZmokQxo(keyEvent)) {
                if (this.scrollingLogic.isVertical()) {
                    int iM2475getHeightimpl = IntSize.m2475getHeightimpl(this.contentInViewNode.m173getViewportSizeYbymL2g$foundation_release());
                    jOffset = OffsetKt.Offset(0.0f, Key.m1599equalsimpl0(KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent), companion.m1613getPageUpEK5gGoQ()) ? iM2475getHeightimpl : -iM2475getHeightimpl);
                } else {
                    int iM2476getWidthimpl = IntSize.m2476getWidthimpl(this.contentInViewNode.m173getViewportSizeYbymL2g$foundation_release());
                    jOffset = OffsetKt.Offset(Key.m1599equalsimpl0(KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent), companion.m1613getPageUpEK5gGoQ()) ? iM2476getWidthimpl : -iM2476getWidthimpl, 0.0f);
                }
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new ScrollableNode$onKeyEvent$1(this, jOffset, null), 3, null);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode, androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo84onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            if (((Boolean) getCanDrag().invoke((PointerInputChange) changes.get(i))).booleanValue()) {
                super.mo84onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
                break;
            }
            i++;
        }
        if (pointerEventPass == PointerEventPass.Main && PointerEventType.m1660equalsimpl0(pointerEvent.m1655getType7fucELk(), PointerEventType.Companion.m1666getScroll7fucELk())) {
            m220processMouseWheelEventO0kMr_c(pointerEvent, j);
        }
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (getEnabled() && (this.scrollByAction == null || this.scrollByOffsetAction == null)) {
            setScrollSemanticsActions();
        }
        Function2 function2 = this.scrollByAction;
        if (function2 != null) {
            SemanticsPropertiesKt.scrollBy$default(semanticsPropertyReceiver, null, function2, 1, null);
        }
        Function2 function22 = this.scrollByOffsetAction;
        if (function22 != null) {
            SemanticsPropertiesKt.scrollByOffset(semanticsPropertyReceiver, function22);
        }
    }

    private final void setScrollSemanticsActions() {
        this.scrollByAction = new Function2() { // from class: androidx.compose.foundation.gestures.ScrollableNode.setScrollSemanticsActions.1
            {
                super(2);
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$setScrollSemanticsActions$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: Scrollable.kt */
            static final class C00081 extends SuspendLambda implements Function2 {
                final /* synthetic */ float $x;
                final /* synthetic */ float $y;
                int label;
                final /* synthetic */ ScrollableNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00081(ScrollableNode scrollableNode, float f, float f2, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = scrollableNode;
                    this.$x = f;
                    this.$y = f2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00081(this.this$0, this.$x, this.$y, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                    return ((C00081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        ScrollingLogic scrollingLogic = this.this$0.scrollingLogic;
                        long jOffset = OffsetKt.Offset(this.$x, this.$y);
                        this.label = 1;
                        if (ScrollableKt.m215semanticsScrollByd4ec7I(scrollingLogic, jOffset, this) == coroutine_suspended) {
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

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke(((Number) obj).floatValue(), ((Number) obj2).floatValue());
            }

            public final Boolean invoke(float f, float f2) {
                BuildersKt__Builders_commonKt.launch$default(ScrollableNode.this.getCoroutineScope(), null, null, new C00081(ScrollableNode.this, f, f2, null), 3, null);
                return Boolean.TRUE;
            }
        };
        this.scrollByOffsetAction = new AnonymousClass2(null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$setScrollSemanticsActions$2, reason: invalid class name */
    /* JADX INFO: compiled from: Scrollable.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        /* synthetic */ long J$0;
        int label;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = ScrollableNode.this.new AnonymousClass2(continuation);
            anonymousClass2.J$0 = ((Offset) obj).m1168unboximpl();
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m221invoke3MmeM6k(((Offset) obj).m1168unboximpl(), (Continuation) obj2);
        }

        /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
        public final Object m221invoke3MmeM6k(long j, Continuation continuation) {
            return ((AnonymousClass2) create(Offset.m1150boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                long j = this.J$0;
                ScrollingLogic scrollingLogic = ScrollableNode.this.scrollingLogic;
                this.label = 1;
                obj = ScrollableKt.m215semanticsScrollByd4ec7I(scrollingLogic, j, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    private final void clearScrollSemanticsActions() {
        this.scrollByAction = null;
        this.scrollByOffsetAction = null;
    }

    /* JADX INFO: renamed from: processMouseWheelEvent-O0kMr_c, reason: not valid java name */
    private final void m220processMouseWheelEventO0kMr_c(PointerEvent pointerEvent, long j) {
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            if (((PointerInputChange) changes.get(i)).isConsumed()) {
                return;
            }
        }
        ScrollConfig scrollConfig = this.scrollConfig;
        Intrinsics.checkNotNull(scrollConfig);
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new ScrollableNode$processMouseWheelEvent$2$1(this, scrollConfig.mo166calculateMouseWheelScroll8xgXZGE(DelegatableNodeKt.requireDensity(this), pointerEvent, j), null), 3, null);
        List changes2 = pointerEvent.getChanges();
        int size2 = changes2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((PointerInputChange) changes2.get(i2)).consume();
        }
    }
}
