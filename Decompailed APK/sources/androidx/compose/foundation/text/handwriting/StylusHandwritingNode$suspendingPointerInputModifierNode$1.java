package androidx.compose.foundation.text.handwriting;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: StylusHandwriting.kt */
/* JADX INFO: loaded from: classes.dex */
final class StylusHandwritingNode$suspendingPointerInputModifierNode$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ StylusHandwritingNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StylusHandwritingNode$suspendingPointerInputModifierNode$1(StylusHandwritingNode stylusHandwritingNode, Continuation continuation) {
        super(2, continuation);
        this.this$0 = stylusHandwritingNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        StylusHandwritingNode$suspendingPointerInputModifierNode$1 stylusHandwritingNode$suspendingPointerInputModifierNode$1 = new StylusHandwritingNode$suspendingPointerInputModifierNode$1(this.this$0, continuation);
        stylusHandwritingNode$suspendingPointerInputModifierNode$1.L$0 = obj;
        return stylusHandwritingNode$suspendingPointerInputModifierNode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
        return ((StylusHandwritingNode$suspendingPointerInputModifierNode$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.handwriting.StylusHandwritingNode$suspendingPointerInputModifierNode$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: StylusHandwriting.kt */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ StylusHandwritingNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(StylusHandwritingNode stylusHandwritingNode, Continuation continuation) {
            super(2, continuation);
            this.this$0 = stylusHandwritingNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation continuation) {
            return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Path cross not found for [B:58:0x0138, B:56:0x0123], limit reached: 91 */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00e9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00f7  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0123  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x0180 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:71:0x018e  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x01b8  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x01bb  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x01c2  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x01b3 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:94:0x011d A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00e7 -> B:42:0x00ea). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x017e -> B:69:0x0181). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instruction units count: 453
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.handwriting.StylusHandwritingNode$suspendingPointerInputModifierNode$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.label = 1;
            if (ForEachGestureKt.awaitEachGesture(pointerInputScope, anonymousClass1, this) == coroutine_suspended) {
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
