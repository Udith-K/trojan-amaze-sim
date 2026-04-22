package androidx.compose.animation.core;

import androidx.compose.animation.core.KeyframesSpec;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AnimationSpecKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationVector convert(TwoWayConverter twoWayConverter, Object obj) {
        if (obj == null) {
            return null;
        }
        return (AnimationVector) twoWayConverter.getConvertToVector().invoke(obj);
    }

    public static /* synthetic */ TweenSpec tween$default(int i, int i2, Easing easing, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 300;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            easing = EasingKt.getFastOutSlowInEasing();
        }
        return tween(i, i2, easing);
    }

    public static final TweenSpec tween(int i, int i2, Easing easing) {
        return new TweenSpec(i, i2, easing);
    }

    public static /* synthetic */ SpringSpec spring$default(float f, float f2, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        if ((i & 2) != 0) {
            f2 = 1500.0f;
        }
        if ((i & 4) != 0) {
            obj = null;
        }
        return spring(f, f2, obj);
    }

    public static final SpringSpec spring(float f, float f2, Object obj) {
        return new SpringSpec(f, f2, obj);
    }

    public static final KeyframesSpec keyframes(Function1 function1) {
        KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig = new KeyframesSpec.KeyframesSpecConfig();
        function1.invoke(keyframesSpecConfig);
        return new KeyframesSpec(keyframesSpecConfig);
    }

    /* JADX INFO: renamed from: infiniteRepeatable-9IiC70o$default, reason: not valid java name */
    public static /* synthetic */ InfiniteRepeatableSpec m47infiniteRepeatable9IiC70o$default(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            repeatMode = RepeatMode.Restart;
        }
        if ((i & 4) != 0) {
            j = StartOffset.m62constructorimpl$default(0, 0, 2, null);
        }
        return m46infiniteRepeatable9IiC70o(durationBasedAnimationSpec, repeatMode, j);
    }

    /* JADX INFO: renamed from: infiniteRepeatable-9IiC70o, reason: not valid java name */
    public static final InfiniteRepeatableSpec m46infiniteRepeatable9IiC70o(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j) {
        return new InfiniteRepeatableSpec(durationBasedAnimationSpec, repeatMode, j, null);
    }

    public static final AnimationSpec delayed(AnimationSpec animationSpec, long j) {
        return new StartDelayAnimationSpec(animationSpec, j);
    }
}
