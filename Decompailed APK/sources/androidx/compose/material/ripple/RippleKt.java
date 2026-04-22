package androidx.compose.material.ripple;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.interaction.DragInteraction$Start;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Ripple.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RippleKt {
    private static final TweenSpec DefaultTweenSpec = new TweenSpec(15, 0, EasingKt.getLinearEasing(), 2, null);

    /* JADX INFO: renamed from: createRippleModifierNode-TDGSqEk, reason: not valid java name */
    public static final DelegatableNode m587createRippleModifierNodeTDGSqEk(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, Function0 function0) {
        return Ripple_androidKt.m593createPlatformRippleNodeTDGSqEk(interactionSource, z, f, colorProducer, function0);
    }

    /* JADX INFO: renamed from: rememberRipple-9IZ8Weo, reason: not valid java name */
    public static final Indication m588rememberRipple9IZ8Weo(boolean z, float f, long j, Composer composer, int i, int i2) {
        boolean z2 = true;
        if ((i2 & 1) != 0) {
            z = true;
        }
        if ((i2 & 2) != 0) {
            f = Dp.Companion.m2425getUnspecifiedD9Ej5fM();
        }
        if ((i2 & 4) != 0) {
            j = Color.Companion.m1309getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1635163520, i, -1, "androidx.compose.material.ripple.rememberRipple (Ripple.kt:146)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m1290boximpl(j), composer, (i >> 6) & 14);
        boolean z3 = (((i & 14) ^ 6) > 4 && composer.changed(z)) || (i & 6) == 4;
        if ((((i & 112) ^ 48) <= 32 || !composer.changed(f)) && (i & 48) != 32) {
            z2 = false;
        }
        boolean z4 = z3 | z2;
        Object objRememberedValue = composer.rememberedValue();
        if (z4 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new PlatformRipple(z, f, stateRememberUpdatedState, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        PlatformRipple platformRipple = (PlatformRipple) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return platformRipple;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationSpec incomingStateLayerAnimationSpecFor(Interaction interaction) {
        if (interaction instanceof HoverInteraction$Enter) {
            return DefaultTweenSpec;
        }
        if (!(interaction instanceof FocusInteraction$Focus) && !(interaction instanceof DragInteraction$Start)) {
            return DefaultTweenSpec;
        }
        return new TweenSpec(45, 0, EasingKt.getLinearEasing(), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationSpec outgoingStateLayerAnimationSpecFor(Interaction interaction) {
        if (!(interaction instanceof HoverInteraction$Enter) && !(interaction instanceof FocusInteraction$Focus)) {
            return interaction instanceof DragInteraction$Start ? new TweenSpec(150, 0, EasingKt.getLinearEasing(), 2, null) : DefaultTweenSpec;
        }
        return DefaultTweenSpec;
    }
}
