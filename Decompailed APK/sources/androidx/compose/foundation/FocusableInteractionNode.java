package androidx.compose.foundation;

import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Focusable.kt */
/* JADX INFO: loaded from: classes.dex */
final class FocusableInteractionNode extends Modifier.Node {
    private FocusInteraction$Focus focusedInteraction;
    private MutableInteractionSource interactionSource;
    private final boolean shouldAutoInvalidate;

    public FocusableInteractionNode(MutableInteractionSource mutableInteractionSource) {
        this.interactionSource = mutableInteractionSource;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    public final void setFocus(boolean z) {
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null) {
            if (z) {
                FocusInteraction$Focus focusInteraction$Focus = this.focusedInteraction;
                if (focusInteraction$Focus != null) {
                    emitWithFallback(mutableInteractionSource, new FocusInteraction$Unfocus(focusInteraction$Focus));
                    this.focusedInteraction = null;
                }
                FocusInteraction$Focus focusInteraction$Focus2 = new FocusInteraction$Focus();
                emitWithFallback(mutableInteractionSource, focusInteraction$Focus2);
                this.focusedInteraction = focusInteraction$Focus2;
                return;
            }
            FocusInteraction$Focus focusInteraction$Focus3 = this.focusedInteraction;
            if (focusInteraction$Focus3 != null) {
                emitWithFallback(mutableInteractionSource, new FocusInteraction$Unfocus(focusInteraction$Focus3));
                this.focusedInteraction = null;
            }
        }
    }

    public final void update(MutableInteractionSource mutableInteractionSource) {
        if (Intrinsics.areEqual(this.interactionSource, mutableInteractionSource)) {
            return;
        }
        disposeInteractionSource();
        this.interactionSource = mutableInteractionSource;
    }

    private final void disposeInteractionSource() {
        FocusInteraction$Focus focusInteraction$Focus;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null && (focusInteraction$Focus = this.focusedInteraction) != null) {
            mutableInteractionSource.tryEmit(new FocusInteraction$Unfocus(focusInteraction$Focus));
        }
        this.focusedInteraction = null;
    }

    private final void emitWithFallback(final MutableInteractionSource mutableInteractionSource, final Interaction interaction) {
        if (isAttached()) {
            Job job = (Job) getCoroutineScope().getCoroutineContext().get(Job.Key);
            BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass1(mutableInteractionSource, interaction, job != null ? job.invokeOnCompletion(new Function1() { // from class: androidx.compose.foundation.FocusableInteractionNode$emitWithFallback$handler$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Throwable) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th) {
                    mutableInteractionSource.tryEmit(interaction);
                }
            }) : null, null), 3, null);
        } else {
            mutableInteractionSource.tryEmit(interaction);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.FocusableInteractionNode$emitWithFallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: Focusable.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ DisposableHandle $handler;
        final /* synthetic */ Interaction $interaction;
        final /* synthetic */ MutableInteractionSource $this_emitWithFallback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MutableInteractionSource mutableInteractionSource, Interaction interaction, DisposableHandle disposableHandle, Continuation continuation) {
            super(2, continuation);
            this.$this_emitWithFallback = mutableInteractionSource;
            this.$interaction = interaction;
            this.$handler = disposableHandle;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$this_emitWithFallback, this.$interaction, this.$handler, continuation);
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
                MutableInteractionSource mutableInteractionSource = this.$this_emitWithFallback;
                Interaction interaction = this.$interaction;
                this.label = 1;
                if (mutableInteractionSource.emit(interaction, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            DisposableHandle disposableHandle = this.$handler;
            if (disposableHandle != null) {
                disposableHandle.dispose();
            }
            return Unit.INSTANCE;
        }
    }
}
