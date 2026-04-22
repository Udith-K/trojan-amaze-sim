package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.FiniteAnimationSpec;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
final class LazyLayoutItemAnimation$animatePlacementDelta$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ FiniteAnimationSpec $spec;
    final /* synthetic */ long $totalDelta;
    Object L$0;
    int label;
    final /* synthetic */ LazyLayoutItemAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyLayoutItemAnimation$animatePlacementDelta$1(LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec finiteAnimationSpec, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lazyLayoutItemAnimation;
        this.$spec = finiteAnimationSpec;
        this.$totalDelta = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new LazyLayoutItemAnimation$animatePlacementDelta$1(this.this$0, this.$spec, this.$totalDelta, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((LazyLayoutItemAnimation$animatePlacementDelta$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00a9 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L23
            if (r1 == r3) goto L1b
            if (r1 != r2) goto L13
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.util.concurrent.CancellationException -> Lb5
            goto Laa
        L13:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L1b:
            java.lang.Object r1 = r11.L$0
            androidx.compose.animation.core.FiniteAnimationSpec r1 = (androidx.compose.animation.core.FiniteAnimationSpec) r1
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.util.concurrent.CancellationException -> Lb5
            goto L67
        L23:
            kotlin.ResultKt.throwOnFailure(r12)
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r12 = r11.this$0     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.animation.core.Animatable r12 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r12)     // Catch: java.util.concurrent.CancellationException -> Lb5
            boolean r12 = r12.isRunning()     // Catch: java.util.concurrent.CancellationException -> Lb5
            if (r12 == 0) goto L41
            androidx.compose.animation.core.FiniteAnimationSpec r12 = r11.$spec     // Catch: java.util.concurrent.CancellationException -> Lb5
            boolean r1 = r12 instanceof androidx.compose.animation.core.SpringSpec     // Catch: java.util.concurrent.CancellationException -> Lb5
            if (r1 == 0) goto L3b
            androidx.compose.animation.core.SpringSpec r12 = (androidx.compose.animation.core.SpringSpec) r12     // Catch: java.util.concurrent.CancellationException -> Lb5
            goto L3f
        L3b:
            androidx.compose.animation.core.SpringSpec r12 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimationKt.access$getInterruptionSpec$p()     // Catch: java.util.concurrent.CancellationException -> Lb5
        L3f:
            r1 = r12
            goto L44
        L41:
            androidx.compose.animation.core.FiniteAnimationSpec r12 = r11.$spec     // Catch: java.util.concurrent.CancellationException -> Lb5
            goto L3f
        L44:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r12 = r11.this$0     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.animation.core.Animatable r12 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r12)     // Catch: java.util.concurrent.CancellationException -> Lb5
            boolean r12 = r12.isRunning()     // Catch: java.util.concurrent.CancellationException -> Lb5
            if (r12 != 0) goto L70
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r12 = r11.this$0     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.animation.core.Animatable r12 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r12)     // Catch: java.util.concurrent.CancellationException -> Lb5
            long r4 = r11.$totalDelta     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.ui.unit.IntOffset r4 = androidx.compose.ui.unit.IntOffset.m2451boximpl(r4)     // Catch: java.util.concurrent.CancellationException -> Lb5
            r11.L$0 = r1     // Catch: java.util.concurrent.CancellationException -> Lb5
            r11.label = r3     // Catch: java.util.concurrent.CancellationException -> Lb5
            java.lang.Object r12 = r12.snapTo(r4, r11)     // Catch: java.util.concurrent.CancellationException -> Lb5
            if (r12 != r0) goto L67
            return r0
        L67:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r12 = r11.this$0     // Catch: java.util.concurrent.CancellationException -> Lb5
            kotlin.jvm.functions.Function0 r12 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getOnLayerPropertyChanged$p(r12)     // Catch: java.util.concurrent.CancellationException -> Lb5
            r12.invoke()     // Catch: java.util.concurrent.CancellationException -> Lb5
        L70:
            r5 = r1
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r12 = r11.this$0     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.animation.core.Animatable r12 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r12)     // Catch: java.util.concurrent.CancellationException -> Lb5
            java.lang.Object r12 = r12.getValue()     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.ui.unit.IntOffset r12 = (androidx.compose.ui.unit.IntOffset) r12     // Catch: java.util.concurrent.CancellationException -> Lb5
            long r3 = r12.m2463unboximpl()     // Catch: java.util.concurrent.CancellationException -> Lb5
            long r6 = r11.$totalDelta     // Catch: java.util.concurrent.CancellationException -> Lb5
            long r3 = androidx.compose.ui.unit.IntOffset.m2460minusqkQi6aY(r3, r6)     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r12 = r11.this$0     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.animation.core.Animatable r12 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r12)     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.ui.unit.IntOffset r1 = androidx.compose.ui.unit.IntOffset.m2451boximpl(r3)     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1$1 r7 = new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1$1     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r6 = r11.this$0     // Catch: java.util.concurrent.CancellationException -> Lb5
            r7.<init>()     // Catch: java.util.concurrent.CancellationException -> Lb5
            r3 = 0
            r11.L$0 = r3     // Catch: java.util.concurrent.CancellationException -> Lb5
            r11.label = r2     // Catch: java.util.concurrent.CancellationException -> Lb5
            r6 = 0
            r9 = 4
            r10 = 0
            r3 = r12
            r4 = r1
            r8 = r11
            java.lang.Object r12 = androidx.compose.animation.core.Animatable.animateTo$default(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.util.concurrent.CancellationException -> Lb5
            if (r12 != r0) goto Laa
            return r0
        Laa:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r12 = r11.this$0     // Catch: java.util.concurrent.CancellationException -> Lb5
            r0 = 0
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$setPlacementAnimationInProgress(r12, r0)     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r12 = r11.this$0     // Catch: java.util.concurrent.CancellationException -> Lb5
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$setRunningMovingAwayAnimation$p(r12, r0)     // Catch: java.util.concurrent.CancellationException -> Lb5
        Lb5:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
