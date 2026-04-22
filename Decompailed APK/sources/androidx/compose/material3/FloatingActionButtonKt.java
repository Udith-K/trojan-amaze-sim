package androidx.compose.material3;

import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FloatingActionButtonKt {
    private static final ExitTransition ExtendedFabCollapseAnimation;
    private static final EnterTransition ExtendedFabExpandAnimation;
    private static final float ExtendedFabStartIconPadding = Dp.m2416constructorimpl(16);
    private static final float ExtendedFabEndIconPadding = Dp.m2416constructorimpl(12);
    private static final float ExtendedFabTextPadding = Dp.m2416constructorimpl(20);
    private static final float ExtendedFabMinimumWidth = Dp.m2416constructorimpl(80);

    /* JADX WARN: Removed duplicated region for block: B:112:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0106  */
    /* JADX INFO: renamed from: FloatingActionButton-X-z6DiA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m683FloatingActionButtonXz6DiA(final kotlin.jvm.functions.Function0 r31, androidx.compose.ui.Modifier r32, androidx.compose.ui.graphics.Shape r33, long r34, long r36, androidx.compose.material3.FloatingActionButtonElevation r38, androidx.compose.foundation.interaction.MutableInteractionSource r39, final kotlin.jvm.functions.Function2 r40, androidx.compose.runtime.Composer r41, final int r42, final int r43) {
        /*
            Method dump skipped, instruction units count: 613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.FloatingActionButtonKt.m683FloatingActionButtonXz6DiA(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, long, androidx.compose.material3.FloatingActionButtonElevation, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    static {
        MotionTokens motionTokens = MotionTokens.INSTANCE;
        ExitTransition exitTransitionFadeOut$default = EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(100, 0, motionTokens.getEasingLinearCubicBezier(), 2, null), 0.0f, 2, null);
        TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(500, 0, motionTokens.getEasingEmphasizedCubicBezier(), 2, null);
        Alignment.Companion companion = Alignment.Companion;
        ExtendedFabCollapseAnimation = exitTransitionFadeOut$default.plus(EnterExitTransitionKt.shrinkHorizontally$default(tweenSpecTween$default, companion.getStart(), false, null, 12, null));
        ExtendedFabExpandAnimation = EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween(200, 100, motionTokens.getEasingLinearCubicBezier()), 0.0f, 2, null).plus(EnterExitTransitionKt.expandHorizontally$default(AnimationSpecKt.tween$default(500, 0, motionTokens.getEasingEmphasizedCubicBezier(), 2, null), companion.getStart(), false, null, 12, null));
    }
}
