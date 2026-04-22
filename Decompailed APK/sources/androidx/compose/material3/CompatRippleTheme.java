package androidx.compose.material3;

import androidx.compose.material.ripple.RippleAlpha;
import androidx.compose.material.ripple.RippleTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;

/* JADX INFO: compiled from: Ripple.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CompatRippleTheme implements RippleTheme {
    public static final CompatRippleTheme INSTANCE = new CompatRippleTheme();

    private CompatRippleTheme() {
    }

    @Override // androidx.compose.material.ripple.RippleTheme
    /* JADX INFO: renamed from: defaultColor-WaAFU9c */
    public long mo578defaultColorWaAFU9c(Composer composer, int i) {
        composer.startReplaceGroup(-1844533201);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1844533201, i, -1, "androidx.compose.material3.CompatRippleTheme.defaultColor (Ripple.kt:244)");
        }
        long jM1304unboximpl = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m1304unboximpl();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return jM1304unboximpl;
    }

    @Override // androidx.compose.material.ripple.RippleTheme
    public RippleAlpha rippleAlpha(Composer composer, int i) {
        composer.startReplaceGroup(-290975286);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-290975286, i, -1, "androidx.compose.material3.CompatRippleTheme.rippleAlpha (Ripple.kt:248)");
        }
        RippleAlpha rippleAlpha = RippleDefaults.INSTANCE.getRippleAlpha();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return rippleAlpha;
    }
}
