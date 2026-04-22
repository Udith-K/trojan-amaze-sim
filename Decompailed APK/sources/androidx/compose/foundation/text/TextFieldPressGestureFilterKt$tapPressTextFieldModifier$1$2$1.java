package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextFieldPressGestureFilter.kt */
/* JADX INFO: loaded from: classes.dex */
final class TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ State $onTapState;
    final /* synthetic */ MutableState $pressedInteraction;
    final /* synthetic */ CoroutineScope $scope;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1(CoroutineScope coroutineScope, MutableState mutableState, MutableInteractionSource mutableInteractionSource, State state, Continuation continuation) {
        super(2, continuation);
        this.$scope = coroutineScope;
        this.$pressedInteraction = mutableState;
        this.$interactionSource = mutableInteractionSource;
        this.$onTapState = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1 textFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1 = new TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1(this.$scope, this.$pressedInteraction, this.$interactionSource, this.$onTapState, continuation);
        textFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1.L$0 = obj;
        return textFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
        return ((TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$scope, this.$pressedInteraction, this.$interactionSource, null);
            final State state = this.$onTapState;
            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    m451invokek4lQ0M(((Offset) obj2).m1168unboximpl());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m451invokek4lQ0M(long j) {
                    ((Function1) state.getValue()).invoke(Offset.m1150boximpl(j));
                }
            };
            this.label = 1;
            if (TapGestureDetectorKt.detectTapAndPress(pointerInputScope, anonymousClass1, function1, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldPressGestureFilter.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ MutableState $pressedInteraction;
        final /* synthetic */ CoroutineScope $scope;
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(CoroutineScope coroutineScope, MutableState mutableState, MutableInteractionSource mutableInteractionSource, Continuation continuation) {
            super(3, continuation);
            this.$scope = coroutineScope;
            this.$pressedInteraction = mutableState;
            this.$interactionSource = mutableInteractionSource;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m450invoked4ec7I((PressGestureScope) obj, ((Offset) obj2).m1168unboximpl(), (Continuation) obj3);
        }

        /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
        public final Object m450invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$scope, this.$pressedInteraction, this.$interactionSource, continuation);
            anonymousClass1.L$0 = pressGestureScope;
            anonymousClass1.J$0 = j;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldPressGestureFilter.kt */
        static final class C00221 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ long $it;
            final /* synthetic */ MutableState $pressedInteraction;
            Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00221(MutableState mutableState, long j, MutableInteractionSource mutableInteractionSource, Continuation continuation) {
                super(2, continuation);
                this.$pressedInteraction = mutableState;
                this.$it = j;
                this.$interactionSource = mutableInteractionSource;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00221(this.$pressedInteraction, this.$it, this.$interactionSource, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00221) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                /*
                    r7 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r7.label
                    r2 = 0
                    r3 = 2
                    r4 = 1
                    if (r1 == 0) goto L27
                    if (r1 == r4) goto L1f
                    if (r1 != r3) goto L17
                    java.lang.Object r0 = r7.L$0
                    androidx.compose.foundation.interaction.PressInteraction$Press r0 = (androidx.compose.foundation.interaction.PressInteraction.Press) r0
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L66
                L17:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                L1f:
                    java.lang.Object r1 = r7.L$0
                    androidx.compose.runtime.MutableState r1 = (androidx.compose.runtime.MutableState) r1
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L4b
                L27:
                    kotlin.ResultKt.throwOnFailure(r8)
                    androidx.compose.runtime.MutableState r8 = r7.$pressedInteraction
                    java.lang.Object r8 = r8.getValue()
                    androidx.compose.foundation.interaction.PressInteraction$Press r8 = (androidx.compose.foundation.interaction.PressInteraction.Press) r8
                    if (r8 == 0) goto L4f
                    androidx.compose.foundation.interaction.MutableInteractionSource r1 = r7.$interactionSource
                    androidx.compose.runtime.MutableState r5 = r7.$pressedInteraction
                    androidx.compose.foundation.interaction.PressInteraction$Cancel r6 = new androidx.compose.foundation.interaction.PressInteraction$Cancel
                    r6.<init>(r8)
                    if (r1 == 0) goto L4c
                    r7.L$0 = r5
                    r7.label = r4
                    java.lang.Object r8 = r1.emit(r6, r7)
                    if (r8 != r0) goto L4a
                    return r0
                L4a:
                    r1 = r5
                L4b:
                    r5 = r1
                L4c:
                    r5.setValue(r2)
                L4f:
                    androidx.compose.foundation.interaction.PressInteraction$Press r8 = new androidx.compose.foundation.interaction.PressInteraction$Press
                    long r4 = r7.$it
                    r8.<init>(r4, r2)
                    androidx.compose.foundation.interaction.MutableInteractionSource r1 = r7.$interactionSource
                    if (r1 == 0) goto L67
                    r7.L$0 = r8
                    r7.label = r3
                    java.lang.Object r1 = r1.emit(r8, r7)
                    if (r1 != r0) goto L65
                    return r0
                L65:
                    r0 = r8
                L66:
                    r8 = r0
                L67:
                    androidx.compose.runtime.MutableState r0 = r7.$pressedInteraction
                    r0.setValue(r8)
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1.AnonymousClass1.C00221.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PressGestureScope pressGestureScope = (PressGestureScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new C00221(this.$pressedInteraction, this.J$0, this.$interactionSource, null), 3, null);
                this.label = 1;
                obj = pressGestureScope.tryAwaitRelease(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            BuildersKt__Builders_commonKt.launch$default(this.$scope, null, null, new AnonymousClass2(this.$pressedInteraction, ((Boolean) obj).booleanValue(), this.$interactionSource, null), 3, null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldPressGestureFilter.kt */
        static final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ MutableState $pressedInteraction;
            final /* synthetic */ boolean $success;
            Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(MutableState mutableState, boolean z, MutableInteractionSource mutableInteractionSource, Continuation continuation) {
                super(2, continuation);
                this.$pressedInteraction = mutableState;
                this.$success = z;
                this.$interactionSource = mutableInteractionSource;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$pressedInteraction, this.$success, this.$interactionSource, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                MutableState mutableState;
                Interaction cancel;
                MutableState mutableState2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PressInteraction.Press press = (PressInteraction.Press) this.$pressedInteraction.getValue();
                    if (press != null) {
                        boolean z = this.$success;
                        MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                        mutableState = this.$pressedInteraction;
                        if (z) {
                            cancel = new PressInteraction.Release(press);
                        } else {
                            cancel = new PressInteraction.Cancel(press);
                        }
                        if (mutableInteractionSource != null) {
                            this.L$0 = mutableState;
                            this.label = 1;
                            if (mutableInteractionSource.emit(cancel, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            mutableState2 = mutableState;
                        }
                        mutableState.setValue(null);
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableState2 = (MutableState) this.L$0;
                ResultKt.throwOnFailure(obj);
                mutableState = mutableState2;
                mutableState.setValue(null);
                return Unit.INSTANCE;
            }
        }
    }
}
