package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.DragInteraction$Cancel;
import androidx.compose.foundation.interaction.DragInteraction$Start;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.input.pointer.util.VelocityTrackerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* JADX INFO: compiled from: Draggable.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DragGestureNode extends DelegatingNode implements PointerInputModifierNode, CompositionLocalConsumerModifierNode {
    private final Function1 _canDrag = new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureNode$_canDrag$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(PointerInputChange pointerInputChange) {
            return (Boolean) this.this$0.getCanDrag().invoke(pointerInputChange);
        }
    };
    private Function1 canDrag;
    private Channel channel;
    private DragInteraction$Start dragInteraction;
    private boolean enabled;
    private MutableInteractionSource interactionSource;
    private boolean isListeningForEvents;
    private Orientation orientationLock;
    private SuspendingPointerInputModifierNode pointerInputNode;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Draggable.kt */
    static final class C00961 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00961(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragCancel(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Draggable.kt */
    static final class C00971 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00971(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragStart(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Draggable.kt */
    static final class C00981 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00981(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragStop(null, this);
        }
    }

    public abstract Object drag(Function2 function2, Continuation continuation);

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return PointerInputModifierNode.CC.$default$interceptOutOfBoundsChildEvents(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ void onDensityChange() {
        onCancelPointerInput();
    }

    /* JADX INFO: renamed from: onDragStarted-k-4lQ0M, reason: not valid java name */
    public abstract void mo186onDragStartedk4lQ0M(long j);

    /* JADX INFO: renamed from: onDragStopped-TH1AsA0, reason: not valid java name */
    public abstract void mo187onDragStoppedTH1AsA0(long j);

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ void onViewConfigurationChange() {
        onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ boolean sharePointerInputWithSiblings() {
        return PointerInputModifierNode.CC.$default$sharePointerInputWithSiblings(this);
    }

    public abstract boolean startDragImmediately();

    public DragGestureNode(Function1 function1, boolean z, MutableInteractionSource mutableInteractionSource, Orientation orientation) {
        this.orientationLock = orientation;
        this.canDrag = function1;
        this.enabled = z;
        this.interactionSource = mutableInteractionSource;
    }

    protected final Function1 getCanDrag() {
        return this.canDrag;
    }

    protected final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startListeningForEvents() {
        this.isListeningForEvents = true;
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C00991(null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Draggable.kt */
    static final class C00991 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        C00991(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            C00991 c00991 = DragGestureNode.this.new C00991(continuation);
            c00991.L$0 = obj;
            return c00991;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C00991) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(7:34|35|(1:37)|14|57|38|(2:44|(2:46|(1:48)))(2:40|(1:42))) */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00d7, code lost:
        
            r1 = r4;
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x008f  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00b9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00c0 A[Catch: CancellationException -> 0x00d7, TryCatch #1 {CancellationException -> 0x00d7, blocks: (B:38:0x00ba, B:40:0x00c0, B:44:0x00d9, B:46:0x00dd), top: B:57:0x00ba }] */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00d9 A[Catch: CancellationException -> 0x00d7, TryCatch #1 {CancellationException -> 0x00d7, blocks: (B:38:0x00ba, B:40:0x00c0, B:44:0x00d9, B:46:0x00dd), top: B:57:0x00ba }] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x00fc A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00fd  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x008d -> B:19:0x005e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00d4 -> B:19:0x005e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00db -> B:19:0x005e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00ea -> B:19:0x005e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x00fa -> B:11:0x0027). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                Method dump skipped, instruction units count: 274
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.C00991.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Draggable.kt */
        static final class C00071 extends SuspendLambda implements Function2 {
            final /* synthetic */ Ref$ObjectRef $event;
            /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DragGestureNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00071(Ref$ObjectRef ref$ObjectRef, DragGestureNode dragGestureNode, Continuation continuation) {
                super(2, continuation);
                this.$event = ref$ObjectRef;
                this.this$0 = dragGestureNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C00071 c00071 = new C00071(this.$event, this.this$0, continuation);
                c00071.L$0 = obj;
                return c00071;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Function1 function1, Continuation continuation) {
                return ((C00071) create(function1, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0049 -> B:25:0x005b). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0055 -> B:24:0x0058). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r5.label
                    r2 = 1
                    if (r1 == 0) goto L1f
                    if (r1 != r2) goto L17
                    java.lang.Object r1 = r5.L$1
                    kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref$ObjectRef) r1
                    java.lang.Object r3 = r5.L$0
                    kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L58
                L17:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r0)
                    throw r6
                L1f:
                    kotlin.ResultKt.throwOnFailure(r6)
                    java.lang.Object r6 = r5.L$0
                    kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
                    r3 = r6
                L27:
                    kotlin.jvm.internal.Ref$ObjectRef r6 = r5.$event
                    java.lang.Object r6 = r6.element
                    boolean r1 = r6 instanceof androidx.compose.foundation.gestures.DragEvent.DragStopped
                    if (r1 != 0) goto L5e
                    boolean r1 = r6 instanceof androidx.compose.foundation.gestures.DragEvent.DragCancelled
                    if (r1 != 0) goto L5e
                    boolean r1 = r6 instanceof androidx.compose.foundation.gestures.DragEvent.DragDelta
                    r4 = 0
                    if (r1 == 0) goto L3b
                    androidx.compose.foundation.gestures.DragEvent$DragDelta r6 = (androidx.compose.foundation.gestures.DragEvent.DragDelta) r6
                    goto L3c
                L3b:
                    r6 = r4
                L3c:
                    if (r6 == 0) goto L41
                    r3.invoke(r6)
                L41:
                    kotlin.jvm.internal.Ref$ObjectRef r1 = r5.$event
                    androidx.compose.foundation.gestures.DragGestureNode r6 = r5.this$0
                    kotlinx.coroutines.channels.Channel r6 = androidx.compose.foundation.gestures.DragGestureNode.access$getChannel$p(r6)
                    if (r6 == 0) goto L5b
                    r5.L$0 = r3
                    r5.L$1 = r1
                    r5.label = r2
                    java.lang.Object r6 = r6.receive(r5)
                    if (r6 != r0) goto L58
                    return r0
                L58:
                    r4 = r6
                    androidx.compose.foundation.gestures.DragEvent r4 = (androidx.compose.foundation.gestures.DragEvent) r4
                L5b:
                    r1.element = r4
                    goto L27
                L5e:
                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.C00991.C00071.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.isListeningForEvents = false;
        disposeInteractionSource();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo84onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        if (this.enabled && this.pointerInputNode == null) {
            this.pointerInputNode = (SuspendingPointerInputModifierNode) delegate(initializePointerInputNode());
        }
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.mo84onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1, reason: invalid class name */
    /* JADX INFO: compiled from: Draggable.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = DragGestureNode.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                final VelocityTracker velocityTracker = new VelocityTracker();
                final DragGestureNode dragGestureNode = DragGestureNode.this;
                Function3 function3 = new Function3() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragStart$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3, Object obj4) {
                        m190invoke0AR0LA0((PointerInputChange) obj2, (PointerInputChange) obj3, ((Offset) obj4).m1168unboximpl());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-0AR0LA0, reason: not valid java name */
                    public final void m190invoke0AR0LA0(PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2, long j) {
                        if (((Boolean) dragGestureNode.getCanDrag().invoke(pointerInputChange)).booleanValue()) {
                            if (!dragGestureNode.isListeningForEvents) {
                                if (dragGestureNode.channel == null) {
                                    dragGestureNode.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
                                }
                                dragGestureNode.startListeningForEvents();
                            }
                            VelocityTrackerKt.addPointerInputChange(velocityTracker, pointerInputChange);
                            long jM1163minusMKHz9U = Offset.m1163minusMKHz9U(pointerInputChange2.m1682getPositionF1C5BW0(), j);
                            Channel channel = dragGestureNode.channel;
                            if (channel != null) {
                                ChannelResult.m2826boximpl(channel.mo2825trySendJP2dKIU(new DragEvent.DragStarted(jM1163minusMKHz9U, null)));
                            }
                        }
                    }
                };
                final DragGestureNode dragGestureNode2 = DragGestureNode.this;
                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragEnd$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        invoke((PointerInputChange) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(PointerInputChange pointerInputChange) {
                        VelocityTrackerKt.addPointerInputChange(velocityTracker, pointerInputChange);
                        float maximumFlingVelocity = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(dragGestureNode2, CompositionLocalsKt.getLocalViewConfiguration())).getMaximumFlingVelocity();
                        long jM1721calculateVelocityAH228Gc = velocityTracker.m1721calculateVelocityAH228Gc(VelocityKt.Velocity(maximumFlingVelocity, maximumFlingVelocity));
                        velocityTracker.resetTracking();
                        Channel channel = dragGestureNode2.channel;
                        if (channel != null) {
                            ChannelResult.m2826boximpl(channel.mo2825trySendJP2dKIU(new DragEvent.DragStopped(DraggableKt.m196toValidVelocityTH1AsA0(jM1721calculateVelocityAH228Gc), null)));
                        }
                    }
                };
                final DragGestureNode dragGestureNode3 = DragGestureNode.this;
                Function0 function0 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDragCancel$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m189invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m189invoke() {
                        Channel channel = dragGestureNode3.channel;
                        if (channel != null) {
                            ChannelResult.m2826boximpl(channel.mo2825trySendJP2dKIU(DragEvent.DragCancelled.INSTANCE));
                        }
                    }
                };
                final DragGestureNode dragGestureNode4 = DragGestureNode.this;
                Function0 function02 = new Function0() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$shouldAwaitTouchSlop$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(!dragGestureNode4.startDragImmediately());
                    }
                };
                final DragGestureNode dragGestureNode5 = DragGestureNode.this;
                C00061 c00061 = new C00061(DragGestureNode.this, pointerInputScope, function3, function1, function0, function02, new Function2() { // from class: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$onDrag$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                        m188invokeUv8p0NA((PointerInputChange) obj2, ((Offset) obj3).m1168unboximpl());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
                    public final void m188invokeUv8p0NA(PointerInputChange pointerInputChange, long j) {
                        VelocityTrackerKt.addPointerInputChange(velocityTracker, pointerInputChange);
                        Channel channel = dragGestureNode5.channel;
                        if (channel != null) {
                            ChannelResult.m2826boximpl(channel.mo2825trySendJP2dKIU(new DragEvent.DragDelta(j, null)));
                        }
                    }
                }, null);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(c00061, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Draggable.kt */
        static final class C00061 extends SuspendLambda implements Function2 {
            final /* synthetic */ PointerInputScope $$this$SuspendingPointerInputModifierNode;
            final /* synthetic */ Function2 $onDrag;
            final /* synthetic */ Function0 $onDragCancel;
            final /* synthetic */ Function1 $onDragEnd;
            final /* synthetic */ Function3 $onDragStart;
            final /* synthetic */ Function0 $shouldAwaitTouchSlop;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ DragGestureNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00061(DragGestureNode dragGestureNode, PointerInputScope pointerInputScope, Function3 function3, Function1 function1, Function0 function0, Function0 function02, Function2 function2, Continuation continuation) {
                super(2, continuation);
                this.this$0 = dragGestureNode;
                this.$$this$SuspendingPointerInputModifierNode = pointerInputScope;
                this.$onDragStart = function3;
                this.$onDragEnd = function1;
                this.$onDragCancel = function0;
                this.$shouldAwaitTouchSlop = function02;
                this.$onDrag = function2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C00061 c00061 = new C00061(this.this$0, this.$$this$SuspendingPointerInputModifierNode, this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$shouldAwaitTouchSlop, this.$onDrag, continuation);
                c00061.L$0 = obj;
                return c00061;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00061) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x004e  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                /*
                    r12 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r12.label
                    r2 = 1
                    if (r1 == 0) goto L1d
                    if (r1 != r2) goto L15
                    java.lang.Object r0 = r12.L$0
                    kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                    kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.util.concurrent.CancellationException -> L13
                    goto L5d
                L13:
                    r13 = move-exception
                    goto L46
                L15:
                    java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r13.<init>(r0)
                    throw r13
                L1d:
                    kotlin.ResultKt.throwOnFailure(r13)
                    java.lang.Object r13 = r12.L$0
                    kotlinx.coroutines.CoroutineScope r13 = (kotlinx.coroutines.CoroutineScope) r13
                    androidx.compose.foundation.gestures.DragGestureNode r1 = r12.this$0     // Catch: java.util.concurrent.CancellationException -> L42
                    androidx.compose.foundation.gestures.Orientation r8 = androidx.compose.foundation.gestures.DragGestureNode.access$getOrientationLock$p(r1)     // Catch: java.util.concurrent.CancellationException -> L42
                    androidx.compose.ui.input.pointer.PointerInputScope r3 = r12.$$this$SuspendingPointerInputModifierNode     // Catch: java.util.concurrent.CancellationException -> L42
                    kotlin.jvm.functions.Function3 r4 = r12.$onDragStart     // Catch: java.util.concurrent.CancellationException -> L42
                    kotlin.jvm.functions.Function1 r5 = r12.$onDragEnd     // Catch: java.util.concurrent.CancellationException -> L42
                    kotlin.jvm.functions.Function0 r6 = r12.$onDragCancel     // Catch: java.util.concurrent.CancellationException -> L42
                    kotlin.jvm.functions.Function0 r7 = r12.$shouldAwaitTouchSlop     // Catch: java.util.concurrent.CancellationException -> L42
                    kotlin.jvm.functions.Function2 r9 = r12.$onDrag     // Catch: java.util.concurrent.CancellationException -> L42
                    r12.L$0 = r13     // Catch: java.util.concurrent.CancellationException -> L42
                    r12.label = r2     // Catch: java.util.concurrent.CancellationException -> L42
                    r10 = r12
                    java.lang.Object r13 = androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.util.concurrent.CancellationException -> L42
                    if (r13 != r0) goto L5d
                    return r0
                L42:
                    r0 = move-exception
                    r11 = r0
                    r0 = r13
                    r13 = r11
                L46:
                    androidx.compose.foundation.gestures.DragGestureNode r1 = r12.this$0
                    kotlinx.coroutines.channels.Channel r1 = androidx.compose.foundation.gestures.DragGestureNode.access$getChannel$p(r1)
                    if (r1 == 0) goto L57
                    androidx.compose.foundation.gestures.DragEvent$DragCancelled r2 = androidx.compose.foundation.gestures.DragEvent.DragCancelled.INSTANCE
                    java.lang.Object r1 = r1.mo2825trySendJP2dKIU(r2)
                    kotlinx.coroutines.channels.ChannelResult.m2826boximpl(r1)
                L57:
                    boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)
                    if (r0 == 0) goto L60
                L5d:
                    kotlin.Unit r13 = kotlin.Unit.INSTANCE
                    return r13
                L60:
                    throw r13
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.AnonymousClass1.C00061.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    private final SuspendingPointerInputModifierNode initializePointerInputNode() {
        return SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AnonymousClass1(null));
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.onCancelPointerInput();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processDragStart(androidx.compose.foundation.gestures.DragEvent.DragStarted r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.DragGestureNode.C00971
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1 r0 = (androidx.compose.foundation.gestures.DragGestureNode.C00971) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1 r0 = new androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L40
            if (r2 != r3) goto L38
            java.lang.Object r7 = r0.L$2
            androidx.compose.foundation.interaction.DragInteraction$Start r7 = (androidx.compose.foundation.interaction.DragInteraction$Start) r7
            java.lang.Object r1 = r0.L$1
            androidx.compose.foundation.gestures.DragEvent$DragStarted r1 = (androidx.compose.foundation.gestures.DragEvent.DragStarted) r1
            java.lang.Object r0 = r0.L$0
            androidx.compose.foundation.gestures.DragGestureNode r0 = (androidx.compose.foundation.gestures.DragGestureNode) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L85
        L38:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L40:
            java.lang.Object r7 = r0.L$1
            androidx.compose.foundation.gestures.DragEvent$DragStarted r7 = (androidx.compose.foundation.gestures.DragEvent.DragStarted) r7
            java.lang.Object r2 = r0.L$0
            androidx.compose.foundation.gestures.DragGestureNode r2 = (androidx.compose.foundation.gestures.DragGestureNode) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6a
        L4c:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.compose.foundation.interaction.DragInteraction$Start r8 = r6.dragInteraction
            if (r8 == 0) goto L69
            androidx.compose.foundation.interaction.MutableInteractionSource r2 = r6.interactionSource
            if (r2 == 0) goto L69
            androidx.compose.foundation.interaction.DragInteraction$Cancel r5 = new androidx.compose.foundation.interaction.DragInteraction$Cancel
            r5.<init>(r8)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r2.emit(r5, r0)
            if (r8 != r1) goto L69
            return r1
        L69:
            r2 = r6
        L6a:
            androidx.compose.foundation.interaction.DragInteraction$Start r8 = new androidx.compose.foundation.interaction.DragInteraction$Start
            r8.<init>()
            androidx.compose.foundation.interaction.MutableInteractionSource r4 = r2.interactionSource
            if (r4 == 0) goto L88
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r0 = r4.emit(r8, r0)
            if (r0 != r1) goto L82
            return r1
        L82:
            r1 = r7
            r7 = r8
            r0 = r2
        L85:
            r8 = r7
            r2 = r0
            r7 = r1
        L88:
            r2.dragInteraction = r8
            long r7 = r7.m177getStartPointF1C5BW0()
            r2.mo186onDragStartedk4lQ0M(r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.processDragStart(androidx.compose.foundation.gestures.DragEvent$DragStarted, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processDragStop(androidx.compose.foundation.gestures.DragEvent.DragStopped r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.compose.foundation.gestures.DragGestureNode.C00981
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1 r0 = (androidx.compose.foundation.gestures.DragGestureNode.C00981) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1 r0 = new androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r6 = r0.L$1
            androidx.compose.foundation.gestures.DragEvent$DragStopped r6 = (androidx.compose.foundation.gestures.DragEvent.DragStopped) r6
            java.lang.Object r0 = r0.L$0
            androidx.compose.foundation.gestures.DragGestureNode r0 = (androidx.compose.foundation.gestures.DragGestureNode) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L57
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.compose.foundation.interaction.DragInteraction$Start r7 = r5.dragInteraction
            if (r7 == 0) goto L5b
            androidx.compose.foundation.interaction.MutableInteractionSource r2 = r5.interactionSource
            if (r2 == 0) goto L56
            androidx.compose.foundation.interaction.DragInteraction$Stop r4 = new androidx.compose.foundation.interaction.DragInteraction$Stop
            r4.<init>(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r2.emit(r4, r0)
            if (r7 != r1) goto L56
            return r1
        L56:
            r0 = r5
        L57:
            r7 = 0
            r0.dragInteraction = r7
            goto L5c
        L5b:
            r0 = r5
        L5c:
            long r6 = r6.m178getVelocity9UxMQ8M()
            r0.mo187onDragStoppedTH1AsA0(r6)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.processDragStop(androidx.compose.foundation.gestures.DragEvent$DragStopped, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processDragCancel(kotlin.coroutines.Continuation r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.compose.foundation.gestures.DragGestureNode.C00961
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1 r0 = (androidx.compose.foundation.gestures.DragGestureNode.C00961) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1 r0 = new androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            androidx.compose.foundation.gestures.DragGestureNode r0 = (androidx.compose.foundation.gestures.DragGestureNode) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L51
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.compose.foundation.interaction.DragInteraction$Start r6 = r5.dragInteraction
            if (r6 == 0) goto L55
            androidx.compose.foundation.interaction.MutableInteractionSource r2 = r5.interactionSource
            if (r2 == 0) goto L50
            androidx.compose.foundation.interaction.DragInteraction$Cancel r4 = new androidx.compose.foundation.interaction.DragInteraction$Cancel
            r4.<init>(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r2.emit(r4, r0)
            if (r6 != r1) goto L50
            return r1
        L50:
            r0 = r5
        L51:
            r6 = 0
            r0.dragInteraction = r6
            goto L56
        L55:
            r0 = r5
        L56:
            androidx.compose.ui.unit.Velocity$Companion r6 = androidx.compose.ui.unit.Velocity.Companion
            long r1 = r6.m2524getZero9UxMQ8M()
            r0.mo187onDragStoppedTH1AsA0(r1)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.processDragCancel(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void disposeInteractionSource() {
        DragInteraction$Start dragInteraction$Start = this.dragInteraction;
        if (dragInteraction$Start != null) {
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                mutableInteractionSource.tryEmit(new DragInteraction$Cancel(dragInteraction$Start));
            }
            this.dragInteraction = null;
        }
    }

    public final void update(Function1 function1, boolean z, MutableInteractionSource mutableInteractionSource, Orientation orientation, boolean z2) {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode;
        this.canDrag = function1;
        boolean z3 = true;
        if (this.enabled != z) {
            this.enabled = z;
            if (!z) {
                disposeInteractionSource();
                SuspendingPointerInputModifierNode suspendingPointerInputModifierNode2 = this.pointerInputNode;
                if (suspendingPointerInputModifierNode2 != null) {
                    undelegate(suspendingPointerInputModifierNode2);
                }
                this.pointerInputNode = null;
            }
            z2 = true;
        }
        if (!Intrinsics.areEqual(this.interactionSource, mutableInteractionSource)) {
            disposeInteractionSource();
            this.interactionSource = mutableInteractionSource;
        }
        if (this.orientationLock != orientation) {
            this.orientationLock = orientation;
        } else {
            z3 = z2;
        }
        if (!z3 || (suspendingPointerInputModifierNode = this.pointerInputNode) == null) {
            return;
        }
        suspendingPointerInputModifierNode.resetPointerInputHandler();
    }
}
