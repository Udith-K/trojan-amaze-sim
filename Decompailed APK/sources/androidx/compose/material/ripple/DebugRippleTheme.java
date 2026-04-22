package androidx.compose.material.ripple;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;

/* JADX INFO: compiled from: RippleTheme.kt */
/* JADX INFO: loaded from: classes.dex */
final class DebugRippleTheme implements RippleTheme {
    public static final DebugRippleTheme INSTANCE = new DebugRippleTheme();

    private DebugRippleTheme() {
    }

    @Override // androidx.compose.material.ripple.RippleTheme
    /* JADX INFO: renamed from: defaultColor-WaAFU9c, reason: not valid java name */
    public long mo578defaultColorWaAFU9c(Composer composer, int i) {
        composer.startReplaceGroup(2042140174);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2042140174, i, -1, "androidx.compose.material.ripple.DebugRippleTheme.defaultColor (RippleTheme.kt:239)");
        }
        long jM592defaultRippleColor5vOe2sY = RippleTheme.Companion.m592defaultRippleColor5vOe2sY(Color.Companion.m1305getBlack0d7_KjU(), true);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return jM592defaultRippleColor5vOe2sY;
    }

    @Override // androidx.compose.material.ripple.RippleTheme
    public RippleAlpha rippleAlpha(Composer composer, int i) {
        composer.startReplaceGroup(-1629816343);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1629816343, i, -1, "androidx.compose.material.ripple.DebugRippleTheme.rippleAlpha (RippleTheme.kt:243)");
        }
        RippleAlpha rippleAlphaM591defaultRippleAlphaDxMtmZc = RippleTheme.Companion.m591defaultRippleAlphaDxMtmZc(Color.Companion.m1305getBlack0d7_KjU(), true);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return rippleAlphaM591defaultRippleAlphaDxMtmZc;
    }
}
