package androidx.compose.foundation.relocation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.TraversableNode;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BringIntoViewResponder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BringIntoViewResponderNode extends Modifier.Node implements BringIntoViewParent, LayoutAwareModifierNode, TraversableNode {
    private boolean hasBeenPlaced;
    private BringIntoViewResponder responder;
    private final boolean shouldAutoInvalidate;
    public static final TraverseKey TraverseKey = new TraverseKey(null);
    public static final int $stable = 8;

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    /* JADX INFO: renamed from: onRemeasured-ozmzZPI */
    public /* synthetic */ void mo174onRemeasuredozmzZPI(long j) {
        LayoutAwareModifierNode.CC.m1802$default$onRemeasuredozmzZPI(this, j);
    }

    public final BringIntoViewResponder getResponder() {
        return this.responder;
    }

    public BringIntoViewResponderNode(BringIntoViewResponder bringIntoViewResponder) {
        this.responder = bringIntoViewResponder;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return TraverseKey;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public void onPlaced(LayoutCoordinates layoutCoordinates) {
        this.hasBeenPlaced = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect bringChildIntoView$localRect(BringIntoViewResponderNode bringIntoViewResponderNode, LayoutCoordinates layoutCoordinates, Function0 function0) {
        Rect rect;
        if (!bringIntoViewResponderNode.isAttached() || !bringIntoViewResponderNode.hasBeenPlaced) {
            return null;
        }
        LayoutCoordinates layoutCoordinatesRequireLayoutCoordinates = DelegatableNodeKt.requireLayoutCoordinates(bringIntoViewResponderNode);
        if (!layoutCoordinates.isAttached()) {
            layoutCoordinates = null;
        }
        if (layoutCoordinates == null || (rect = (Rect) function0.invoke()) == null) {
            return null;
        }
        return BringIntoViewRequesterKt__BringIntoViewResponderKt.localRectOf$BringIntoViewRequesterKt__BringIntoViewResponderKt(layoutCoordinatesRequireLayoutCoordinates, layoutCoordinates, rect);
    }

    @Override // androidx.compose.foundation.relocation.BringIntoViewParent
    public Object bringChildIntoView(final LayoutCoordinates layoutCoordinates, final Function0 function0, Continuation continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(layoutCoordinates, function0, new Function0() { // from class: androidx.compose.foundation.relocation.BringIntoViewResponderNode$bringChildIntoView$parentRect$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Rect invoke() {
                Rect rectBringChildIntoView$localRect = BringIntoViewResponderNode.bringChildIntoView$localRect(this.this$0, layoutCoordinates, function0);
                if (rectBringChildIntoView$localRect != null) {
                    return this.this$0.getResponder().calculateRectForParent(rectBringChildIntoView$localRect);
                }
                return null;
            }
        }, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.relocation.BringIntoViewResponderNode$bringChildIntoView$2, reason: invalid class name */
    /* JADX INFO: compiled from: BringIntoViewResponder.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function0 $boundsProvider;
        final /* synthetic */ LayoutCoordinates $childCoordinates;
        final /* synthetic */ Function0 $parentRect;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(LayoutCoordinates layoutCoordinates, Function0 function0, Function0 function02, Continuation continuation) {
            super(2, continuation);
            this.$childCoordinates = layoutCoordinates;
            this.$boundsProvider = function0;
            this.$parentRect = function02;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = BringIntoViewResponderNode.this.new AnonymousClass2(this.$childCoordinates, this.$boundsProvider, this.$parentRect, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(BringIntoViewResponderNode.this, this.$childCoordinates, this.$boundsProvider, null), 3, null);
                return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C00172(BringIntoViewResponderNode.this, this.$parentRect, null), 3, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.relocation.BringIntoViewResponderNode$bringChildIntoView$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: BringIntoViewResponder.kt */
        static final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ Function0 $boundsProvider;
            final /* synthetic */ LayoutCoordinates $childCoordinates;
            int label;
            final /* synthetic */ BringIntoViewResponderNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(BringIntoViewResponderNode bringIntoViewResponderNode, LayoutCoordinates layoutCoordinates, Function0 function0, Continuation continuation) {
                super(2, continuation);
                this.this$0 = bringIntoViewResponderNode;
                this.$childCoordinates = layoutCoordinates;
                this.$boundsProvider = function0;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, this.$childCoordinates, this.$boundsProvider, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    BringIntoViewResponder responder = this.this$0.getResponder();
                    C00161 c00161 = new C00161(this.this$0, this.$childCoordinates, this.$boundsProvider);
                    this.label = 1;
                    if (responder.bringChildIntoView(c00161, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: androidx.compose.foundation.relocation.BringIntoViewResponderNode$bringChildIntoView$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: BringIntoViewResponder.kt */
            /* synthetic */ class C00161 extends FunctionReferenceImpl implements Function0 {
                final /* synthetic */ Function0 $boundsProvider;
                final /* synthetic */ LayoutCoordinates $childCoordinates;
                final /* synthetic */ BringIntoViewResponderNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00161(BringIntoViewResponderNode bringIntoViewResponderNode, LayoutCoordinates layoutCoordinates, Function0 function0) {
                    super(0, Intrinsics.Kotlin.class, "localRect", "bringChildIntoView$localRect(Landroidx/compose/foundation/relocation/BringIntoViewResponderNode;Landroidx/compose/ui/layout/LayoutCoordinates;Lkotlin/jvm/functions/Function0;)Landroidx/compose/ui/geometry/Rect;", 0);
                    this.this$0 = bringIntoViewResponderNode;
                    this.$childCoordinates = layoutCoordinates;
                    this.$boundsProvider = function0;
                }

                @Override // kotlin.jvm.functions.Function0
                public final Rect invoke() {
                    return BringIntoViewResponderNode.bringChildIntoView$localRect(this.this$0, this.$childCoordinates, this.$boundsProvider);
                }
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.relocation.BringIntoViewResponderNode$bringChildIntoView$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BringIntoViewResponder.kt */
        static final class C00172 extends SuspendLambda implements Function2 {
            final /* synthetic */ Function0 $parentRect;
            int label;
            final /* synthetic */ BringIntoViewResponderNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00172(BringIntoViewResponderNode bringIntoViewResponderNode, Function0 function0, Continuation continuation) {
                super(2, continuation);
                this.this$0 = bringIntoViewResponderNode;
                this.$parentRect = function0;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00172(this.this$0, this.$parentRect, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00172) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                BringIntoViewParent bringIntoViewParentFindBringIntoViewParent;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.this$0.isAttached() && (bringIntoViewParentFindBringIntoViewParent = BringIntoViewRequesterKt.findBringIntoViewParent(this.this$0)) != null) {
                        LayoutCoordinates layoutCoordinatesRequireLayoutCoordinates = DelegatableNodeKt.requireLayoutCoordinates(this.this$0);
                        Function0 function0 = this.$parentRect;
                        this.label = 1;
                        if (bringIntoViewParentFindBringIntoViewParent.bringChildIntoView(layoutCoordinatesRequireLayoutCoordinates, function0, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
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

    /* JADX INFO: compiled from: BringIntoViewResponder.kt */
    public static final class TraverseKey {
        public /* synthetic */ TraverseKey(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private TraverseKey() {
        }
    }
}
