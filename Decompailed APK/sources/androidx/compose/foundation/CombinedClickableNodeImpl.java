package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Clickable.kt */
/* JADX INFO: loaded from: classes.dex */
final class CombinedClickableNodeImpl extends AbstractClickableNode implements PointerInputModifierNode {
    private Function0 onDoubleClick;
    private Function0 onLongClick;
    private String onLongClickLabel;

    public /* synthetic */ CombinedClickableNodeImpl(Function0 function0, String str, Function0 function02, Function0 function03, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, String str2, Role role, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, str, function02, function03, mutableInteractionSource, indicationNodeFactory, z, str2, role);
    }

    private CombinedClickableNodeImpl(Function0 function0, String str, Function0 function02, Function0 function03, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, String str2, Role role) {
        super(mutableInteractionSource, indicationNodeFactory, z, str2, role, function0, null);
        this.onLongClickLabel = str;
        this.onLongClick = function02;
        this.onDoubleClick = function03;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public Object clickPointerInput(PointerInputScope pointerInputScope, Continuation continuation) {
        Object objDetectTapGestures = TapGestureDetectorKt.detectTapGestures(pointerInputScope, (!getEnabled() || this.onDoubleClick == null) ? null : new Function1() { // from class: androidx.compose.foundation.CombinedClickableNodeImpl.clickPointerInput.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m127invokek4lQ0M(((Offset) obj).m1168unboximpl());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m127invokek4lQ0M(long j) {
                Function0 function0 = CombinedClickableNodeImpl.this.onDoubleClick;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        }, (!getEnabled() || this.onLongClick == null) ? null : new Function1() { // from class: androidx.compose.foundation.CombinedClickableNodeImpl.clickPointerInput.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m128invokek4lQ0M(((Offset) obj).m1168unboximpl());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m128invokek4lQ0M(long j) {
                Function0 function0 = CombinedClickableNodeImpl.this.onLongClick;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        }, new AnonymousClass4(null), new Function1() { // from class: androidx.compose.foundation.CombinedClickableNodeImpl.clickPointerInput.5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m130invokek4lQ0M(((Offset) obj).m1168unboximpl());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m130invokek4lQ0M(long j) {
                if (CombinedClickableNodeImpl.this.getEnabled()) {
                    CombinedClickableNodeImpl.this.getOnClick().invoke();
                }
            }
        }, continuation);
        return objDetectTapGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickableNodeImpl$clickPointerInput$4, reason: invalid class name */
    /* JADX INFO: compiled from: Clickable.kt */
    static final class AnonymousClass4 extends SuspendLambda implements Function3 {
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m129invoked4ec7I((PressGestureScope) obj, ((Offset) obj2).m1168unboximpl(), (Continuation) obj3);
        }

        /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m129invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation continuation) {
            AnonymousClass4 anonymousClass4 = CombinedClickableNodeImpl.this.new AnonymousClass4(continuation);
            anonymousClass4.L$0 = pressGestureScope;
            anonymousClass4.J$0 = j;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PressGestureScope pressGestureScope = (PressGestureScope) this.L$0;
                long j = this.J$0;
                if (CombinedClickableNodeImpl.this.getEnabled()) {
                    CombinedClickableNodeImpl combinedClickableNodeImpl = CombinedClickableNodeImpl.this;
                    this.label = 1;
                    if (combinedClickableNodeImpl.m82handlePressInteractiond4ec7I(pressGestureScope, j, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: update-nSzSaCc, reason: not valid java name */
    public void m126updatenSzSaCc(Function0 function0, String str, Function0 function02, Function0 function03, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, String str2, Role role) {
        boolean z2;
        if (!Intrinsics.areEqual(this.onLongClickLabel, str)) {
            this.onLongClickLabel = str;
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if ((this.onLongClick == null) != (function02 == null)) {
            disposeInteractions();
            SemanticsModifierNodeKt.invalidateSemantics(this);
            z2 = true;
        } else {
            z2 = false;
        }
        this.onLongClick = function02;
        if ((this.onDoubleClick == null) != (function03 == null)) {
            z2 = true;
        }
        this.onDoubleClick = function03;
        boolean z3 = getEnabled() != z ? true : z2;
        m86updateCommonQzZPfjk(mutableInteractionSource, indicationNodeFactory, z, str2, role, function0);
        if (z3) {
            resetPointerInputHandler();
        }
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public void applyAdditionalSemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (this.onLongClick != null) {
            SemanticsPropertiesKt.onLongClick(semanticsPropertyReceiver, this.onLongClickLabel, new Function0() { // from class: androidx.compose.foundation.CombinedClickableNodeImpl.applyAdditionalSemantics.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    Function0 function0 = CombinedClickableNodeImpl.this.onLongClick;
                    if (function0 != null) {
                        function0.invoke();
                    }
                    return Boolean.TRUE;
                }
            });
        }
    }
}
