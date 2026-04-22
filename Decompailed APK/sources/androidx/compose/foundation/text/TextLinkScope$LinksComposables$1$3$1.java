package androidx.compose.foundation.text;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextLinkScope.kt */
/* JADX INFO: loaded from: classes.dex */
final class TextLinkScope$LinksComposables$1$3$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ LinkStateInteractionSourceObserver $linkStateObserver;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextLinkScope$LinksComposables$1$3$1(LinkStateInteractionSourceObserver linkStateInteractionSourceObserver, MutableInteractionSource mutableInteractionSource, Continuation continuation) {
        super(2, continuation);
        this.$linkStateObserver = linkStateInteractionSourceObserver;
        this.$interactionSource = mutableInteractionSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new TextLinkScope$LinksComposables$1$3$1(this.$linkStateObserver, this.$interactionSource, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((TextLinkScope$LinksComposables$1$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LinkStateInteractionSourceObserver linkStateInteractionSourceObserver = this.$linkStateObserver;
            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            this.label = 1;
            if (linkStateInteractionSourceObserver.collectInteractionsForLinks(mutableInteractionSource, this) == coroutine_suspended) {
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
