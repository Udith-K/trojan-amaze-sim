package androidx.compose.animation.core;

import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.ui.MotionDurationScale;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FloatCompanionObject;

/* JADX INFO: compiled from: SuspendAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SuspendAnimationKt {

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animate$4, reason: invalid class name */
    /* JADX INFO: compiled from: SuspendAnimation.kt */
    static final class AnonymousClass4 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass4(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SuspendAnimationKt.animate(null, null, 0L, null, this);
        }
    }

    public static /* synthetic */ Object animate$default(float f, float f2, float f3, AnimationSpec animationSpec, Function2 function2, Continuation continuation, int i, Object obj) {
        float f4 = (i & 4) != 0 ? 0.0f : f3;
        if ((i & 8) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animate(f, f2, f4, animationSpec, function2, continuation);
    }

    public static final Object animate(float f, float f2, float f3, AnimationSpec animationSpec, Function2 function2, Continuation continuation) {
        Object objAnimate = animate(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), Boxing.boxFloat(f), Boxing.boxFloat(f2), Boxing.boxFloat(f3), animationSpec, function2, continuation);
        return objAnimate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate : Unit.INSTANCE;
    }

    public static final Object animate(final TwoWayConverter twoWayConverter, Object obj, Object obj2, Object obj3, AnimationSpec animationSpec, final Function2 function2, Continuation continuation) {
        AnimationVector animationVectorNewInstance;
        if (obj3 == null || (animationVectorNewInstance = (AnimationVector) twoWayConverter.getConvertToVector().invoke(obj3)) == null) {
            animationVectorNewInstance = AnimationVectorsKt.newInstance((AnimationVector) twoWayConverter.getConvertToVector().invoke(obj));
        }
        Object objAnimate$default = animate$default(new AnimationState(twoWayConverter, obj, animationVectorNewInstance, 0L, 0L, false, 56, null), new TargetBasedAnimation(animationSpec, twoWayConverter, obj, obj2, animationVectorNewInstance), 0L, new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt.animate.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj4) {
                invoke((AnimationScope) obj4);
                return Unit.INSTANCE;
            }

            public final void invoke(AnimationScope animationScope) {
                function2.invoke(animationScope.getValue(), twoWayConverter.getConvertFromVector().invoke(animationScope.getVelocityVector()));
            }
        }, continuation, 2, null);
        return objAnimate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate$default : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateTo$default(AnimationState animationState, Object obj, AnimationSpec animationSpec, boolean z, Function1 function1, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt.animateTo.2
                public final void invoke(AnimationScope animationScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj3) {
                    invoke((AnimationScope) obj3);
                    return Unit.INSTANCE;
                }
            };
        }
        return animateTo(animationState, obj, animationSpec2, z2, function1, continuation);
    }

    public static final Object animateTo(AnimationState animationState, Object obj, AnimationSpec animationSpec, boolean z, Function1 function1, Continuation continuation) {
        Object objAnimate = animate(animationState, new TargetBasedAnimation(animationSpec, animationState.getTypeConverter(), animationState.getValue(), obj, animationState.getVelocityVector()), z ? animationState.getLastFrameTimeNanos() : Long.MIN_VALUE, function1, continuation);
        return objAnimate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateDecay$default(AnimationState animationState, DecayAnimationSpec decayAnimationSpec, boolean z, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt.animateDecay.4
                public final void invoke(AnimationScope animationScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((AnimationScope) obj2);
                    return Unit.INSTANCE;
                }
            };
        }
        return animateDecay(animationState, decayAnimationSpec, z, function1, continuation);
    }

    public static final Object animateDecay(AnimationState animationState, DecayAnimationSpec decayAnimationSpec, boolean z, Function1 function1, Continuation continuation) {
        Object objAnimate = animate(animationState, new DecayAnimation(decayAnimationSpec, animationState.getTypeConverter(), animationState.getValue(), animationState.getVelocityVector()), z ? animationState.getLastFrameTimeNanos() : Long.MIN_VALUE, function1, continuation);
        return objAnimate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object animate(final androidx.compose.animation.core.AnimationState r25, final androidx.compose.animation.core.Animation r26, long r27, final kotlin.jvm.functions.Function1 r29, kotlin.coroutines.Continuation r30) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.SuspendAnimationKt.animate(androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.Animation, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object animate$default(AnimationState animationState, Animation animation, long j, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MIN_VALUE;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt.animate.5
                public final void invoke(AnimationScope animationScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((AnimationScope) obj2);
                    return Unit.INSTANCE;
                }
            };
        }
        return animate(animationState, animation, j2, function1, continuation);
    }

    private static final Object callWithFrameNanos(Animation animation, final Function1 function1, Continuation continuation) {
        if (animation.isInfinite()) {
            return InfiniteAnimationPolicyKt.withInfiniteAnimationFrameNanos(function1, continuation);
        }
        return MonotonicFrameClockKt.withFrameNanos(new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt.callWithFrameNanos.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).longValue());
            }

            public final Object invoke(long j) {
                return function1.invoke(Long.valueOf(j));
            }
        }, continuation);
    }

    public static final float getDurationScale(CoroutineContext coroutineContext) {
        MotionDurationScale motionDurationScale = (MotionDurationScale) coroutineContext.get(MotionDurationScale.Key);
        float scaleFactor = motionDurationScale != null ? motionDurationScale.getScaleFactor() : 1.0f;
        if (!(scaleFactor >= 0.0f)) {
            PreconditionsKt.throwIllegalStateException("negative scale factor");
        }
        return scaleFactor;
    }

    public static final void updateState(AnimationScope animationScope, AnimationState animationState) {
        animationState.setValue$animation_core_release(animationScope.getValue());
        AnimationVectorsKt.copyFrom(animationState.getVelocityVector(), animationScope.getVelocityVector());
        animationState.setFinishedTimeNanos$animation_core_release(animationScope.getFinishedTimeNanos());
        animationState.setLastFrameTimeNanos$animation_core_release(animationScope.getLastFrameTimeNanos());
        animationState.setRunning$animation_core_release(animationScope.isRunning());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doAnimationFrameWithScale(AnimationScope animationScope, long j, float f, Animation animation, AnimationState animationState, Function1 function1) {
        long startTimeNanos;
        if (f == 0.0f) {
            startTimeNanos = animation.getDurationNanos();
        } else {
            startTimeNanos = (long) ((j - animationScope.getStartTimeNanos()) / f);
        }
        doAnimationFrame(animationScope, j, startTimeNanos, animation, animationState, function1);
    }

    private static final void doAnimationFrame(AnimationScope animationScope, long j, long j2, Animation animation, AnimationState animationState, Function1 function1) {
        animationScope.setLastFrameTimeNanos$animation_core_release(j);
        animationScope.setValue$animation_core_release(animation.getValueFromNanos(j2));
        animationScope.setVelocityVector$animation_core_release(animation.getVelocityVectorFromNanos(j2));
        if (animation.isFinishedFromNanos(j2)) {
            animationScope.setFinishedTimeNanos$animation_core_release(animationScope.getLastFrameTimeNanos());
            animationScope.setRunning$animation_core_release(false);
        }
        updateState(animationScope, animationState);
        function1.invoke(animationScope);
    }
}
