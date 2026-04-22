package androidx.compose.material.ripple;

import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.node.DrawModifierNodeKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CommonRipple.kt */
/* JADX INFO: loaded from: classes.dex */
final class CommonRippleNode$addRipple$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ PressInteraction.Press $interaction;
    final /* synthetic */ RippleAnimation $rippleAnimation;
    int label;
    final /* synthetic */ CommonRippleNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CommonRippleNode$addRipple$2(RippleAnimation rippleAnimation, CommonRippleNode commonRippleNode, PressInteraction.Press press, Continuation continuation) {
        super(2, continuation);
        this.$rippleAnimation = rippleAnimation;
        this.this$0 = commonRippleNode;
        this.$interaction = press;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommonRippleNode$addRipple$2(this.$rippleAnimation, this.this$0, this.$interaction, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((CommonRippleNode$addRipple$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                RippleAnimation rippleAnimation = this.$rippleAnimation;
                this.label = 1;
                if (rippleAnimation.animate(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.this$0.ripples.remove(this.$interaction);
            DrawModifierNodeKt.invalidateDraw(this.this$0);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            this.this$0.ripples.remove(this.$interaction);
            DrawModifierNodeKt.invalidateDraw(this.this$0);
            throw th;
        }
    }
}
