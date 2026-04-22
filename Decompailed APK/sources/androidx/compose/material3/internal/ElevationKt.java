package androidx.compose.material3.internal;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.Easing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* JADX INFO: compiled from: Elevation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ElevationKt {
    private static final TweenSpec DefaultIncomingSpec;
    private static final TweenSpec DefaultOutgoingSpec;
    private static final TweenSpec HoveredOutgoingSpec;
    private static final Easing OutgoingSpecEasing;

    /* JADX INFO: renamed from: animateElevation-rAjV9yQ, reason: not valid java name */
    public static final Object m781animateElevationrAjV9yQ(Animatable animatable, float f, Interaction interaction, Interaction interaction2, Continuation continuation) {
        AnimationSpec animationSpecOutgoingAnimationSpecForInteraction;
        if (interaction2 != null) {
            animationSpecOutgoingAnimationSpecForInteraction = ElevationDefaults.INSTANCE.incomingAnimationSpecForInteraction(interaction2);
        } else {
            animationSpecOutgoingAnimationSpecForInteraction = interaction != null ? ElevationDefaults.INSTANCE.outgoingAnimationSpecForInteraction(interaction) : null;
        }
        AnimationSpec animationSpec = animationSpecOutgoingAnimationSpecForInteraction;
        if (animationSpec != null) {
            Object objAnimateTo$default = Animatable.animateTo$default(animatable, Dp.m2414boximpl(f), animationSpec, null, null, continuation, 12, null);
            return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
        }
        Object objSnapTo = animatable.snapTo(Dp.m2414boximpl(f), continuation);
        return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
    }

    static {
        CubicBezierEasing cubicBezierEasing = new CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f);
        OutgoingSpecEasing = cubicBezierEasing;
        DefaultIncomingSpec = new TweenSpec(120, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
        DefaultOutgoingSpec = new TweenSpec(150, 0, cubicBezierEasing, 2, null);
        HoveredOutgoingSpec = new TweenSpec(120, 0, cubicBezierEasing, 2, null);
    }
}
