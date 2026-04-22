package androidx.compose.foundation.interaction;

import androidx.compose.runtime.MutableState;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: FocusInteraction.kt */
/* JADX INFO: loaded from: classes.dex */
final class FocusInteractionKt$collectIsFocusedAsState$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MutableState $isFocused;
    final /* synthetic */ InteractionSource $this_collectIsFocusedAsState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FocusInteractionKt$collectIsFocusedAsState$1$1(InteractionSource interactionSource, MutableState mutableState, Continuation continuation) {
        super(2, continuation);
        this.$this_collectIsFocusedAsState = interactionSource;
        this.$isFocused = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FocusInteractionKt$collectIsFocusedAsState$1$1(this.$this_collectIsFocusedAsState, this.$isFocused, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((FocusInteractionKt$collectIsFocusedAsState$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ArrayList arrayList = new ArrayList();
            Flow interactions = this.$this_collectIsFocusedAsState.getInteractions();
            final MutableState mutableState = this.$isFocused;
            FlowCollector flowCollector = new FlowCollector() { // from class: androidx.compose.foundation.interaction.FocusInteractionKt$collectIsFocusedAsState$1$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Interaction interaction, Continuation continuation) {
                    if (interaction instanceof FocusInteraction$Focus) {
                        arrayList.add(interaction);
                    } else if (interaction instanceof FocusInteraction$Unfocus) {
                        arrayList.remove(((FocusInteraction$Unfocus) interaction).getFocus());
                    }
                    mutableState.setValue(Boxing.boxBoolean(!arrayList.isEmpty()));
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (interactions.collect(flowCollector, this) == coroutine_suspended) {
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
