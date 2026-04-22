package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.foundation.interaction.DragInteraction$Start;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.material3.internal.ElevationKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Dp;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes.dex */
final class CardElevation$animateElevation$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Animatable $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ float $target;
    int label;
    final /* synthetic */ CardElevation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CardElevation$animateElevation$2$1(Animatable animatable, float f, boolean z, CardElevation cardElevation, Interaction interaction, Continuation continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.$target = f;
        this.$enabled = z;
        this.this$0 = cardElevation;
        this.$interaction = interaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CardElevation$animateElevation$2$1(this.$animatable, this.$target, this.$enabled, this.this$0, this.$interaction, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((CardElevation$animateElevation$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!Dp.m2418equalsimpl0(((Dp) this.$animatable.getTargetValue()).m2422unboximpl(), this.$target)) {
                if (!this.$enabled) {
                    Animatable animatable = this.$animatable;
                    Dp dpM2414boximpl = Dp.m2414boximpl(this.$target);
                    this.label = 1;
                    if (animatable.snapTo(dpM2414boximpl, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    float fM2422unboximpl = ((Dp) this.$animatable.getTargetValue()).m2422unboximpl();
                    Interaction dragInteraction$Start = null;
                    if (Dp.m2418equalsimpl0(fM2422unboximpl, this.this$0.pressedElevation)) {
                        dragInteraction$Start = new PressInteraction.Press(Offset.Companion.m1171getZeroF1C5BW0(), null);
                    } else if (Dp.m2418equalsimpl0(fM2422unboximpl, this.this$0.hoveredElevation)) {
                        dragInteraction$Start = new HoverInteraction$Enter();
                    } else if (Dp.m2418equalsimpl0(fM2422unboximpl, this.this$0.focusedElevation)) {
                        dragInteraction$Start = new FocusInteraction$Focus();
                    } else if (Dp.m2418equalsimpl0(fM2422unboximpl, this.this$0.draggedElevation)) {
                        dragInteraction$Start = new DragInteraction$Start();
                    }
                    Animatable animatable2 = this.$animatable;
                    float f = this.$target;
                    Interaction interaction = this.$interaction;
                    this.label = 2;
                    if (ElevationKt.m781animateElevationrAjV9yQ(animatable2, f, dragInteraction$Start, interaction, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
        } else {
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
