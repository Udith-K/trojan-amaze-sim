package androidx.compose.animation.core;

import androidx.compose.runtime.State;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;

/* JADX INFO: compiled from: AnimateAsState.kt */
/* JADX INFO: loaded from: classes.dex */
final class AnimateAsStateKt$animateValueAsState$3$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ State $animSpec$delegate;
    final /* synthetic */ Animatable $animatable;
    final /* synthetic */ Channel $channel;
    final /* synthetic */ State $listener$delegate;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnimateAsStateKt$animateValueAsState$3$1(Channel channel, Animatable animatable, State state, State state2, Continuation continuation) {
        super(2, continuation);
        this.$channel = channel;
        this.$animatable = animatable;
        this.$animSpec$delegate = state;
        this.$listener$delegate = state2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        AnimateAsStateKt$animateValueAsState$3$1 animateAsStateKt$animateValueAsState$3$1 = new AnimateAsStateKt$animateValueAsState$3$1(this.$channel, this.$animatable, this.$animSpec$delegate, this.$listener$delegate, continuation);
        animateAsStateKt$animateValueAsState$3$1.L$0 = obj;
        return animateAsStateKt$animateValueAsState$3$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((AnimateAsStateKt$animateValueAsState$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x003a -> B:12:0x003d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 1
            if (r1 == 0) goto L20
            if (r1 != r2) goto L18
            java.lang.Object r1 = r13.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r3 = r13.L$0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            kotlin.ResultKt.throwOnFailure(r14)
            r4 = r14
            goto L3d
        L18:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L20:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r1 = r13.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlinx.coroutines.channels.Channel r3 = r13.$channel
            kotlinx.coroutines.channels.ChannelIterator r3 = r3.iterator()
            r12 = r3
            r3 = r1
            r1 = r12
        L30:
            r13.L$0 = r3
            r13.L$1 = r1
            r13.label = r2
            java.lang.Object r4 = r1.hasNext(r13)
            if (r4 != r0) goto L3d
            return r0
        L3d:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L6f
            java.lang.Object r4 = r1.next()
            kotlinx.coroutines.channels.Channel r5 = r13.$channel
            java.lang.Object r5 = r5.mo2824tryReceivePtdJZtk()
            java.lang.Object r5 = kotlinx.coroutines.channels.ChannelResult.m2830getOrNullimpl(r5)
            if (r5 != 0) goto L57
            r7 = r4
            goto L58
        L57:
            r7 = r5
        L58:
            androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1$1 r4 = new androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1$1
            androidx.compose.animation.core.Animatable r8 = r13.$animatable
            androidx.compose.runtime.State r9 = r13.$animSpec$delegate
            androidx.compose.runtime.State r10 = r13.$listener$delegate
            r11 = 0
            r6 = r4
            r6.<init>(r7, r8, r9, r10, r11)
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            r5 = r3
            r8 = r4
            kotlinx.coroutines.BuildersKt.launch$default(r5, r6, r7, r8, r9, r10)
            goto L30
        L6f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: AnimateAsState.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ State $animSpec$delegate;
        final /* synthetic */ Animatable $animatable;
        final /* synthetic */ State $listener$delegate;
        final /* synthetic */ Object $newTarget;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Object obj, Animatable animatable, State state, State state2, Continuation continuation) {
            super(2, continuation);
            this.$newTarget = obj;
            this.$animatable = animatable;
            this.$animSpec$delegate = state;
            this.$listener$delegate = state2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$newTarget, this.$animatable, this.$animSpec$delegate, this.$listener$delegate, continuation);
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
                if (!Intrinsics.areEqual(this.$newTarget, this.$animatable.getTargetValue())) {
                    Animatable animatable = this.$animatable;
                    Object obj2 = this.$newTarget;
                    AnimationSpec animationSpecAnimateValueAsState$lambda$6 = AnimateAsStateKt.animateValueAsState$lambda$6(this.$animSpec$delegate);
                    this.label = 1;
                    if (Animatable.animateTo$default(animatable, obj2, animationSpecAnimateValueAsState$lambda$6, null, null, this, 12, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Function1 function1AnimateValueAsState$lambda$4 = AnimateAsStateKt.animateValueAsState$lambda$4(this.$listener$delegate);
            if (function1AnimateValueAsState$lambda$4 != null) {
                function1AnimateValueAsState$lambda$4.invoke(this.$animatable.getValue());
            }
            return Unit.INSTANCE;
        }
    }
}
