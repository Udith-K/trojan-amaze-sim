package androidx.compose.material.ripple;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;

/* JADX INFO: compiled from: RippleTheme.kt */
/* JADX INFO: loaded from: classes.dex */
public interface RippleTheme {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: defaultColor-WaAFU9c */
    long mo578defaultColorWaAFU9c(Composer composer, int i);

    RippleAlpha rippleAlpha(Composer composer, int i);

    /* JADX INFO: compiled from: RippleTheme.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        /* JADX INFO: renamed from: defaultRippleColor-5vOe2sY, reason: not valid java name */
        public final long m592defaultRippleColor5vOe2sY(long j, boolean z) {
            return (z || ((double) ColorKt.m1315luminance8_81llA(j)) >= 0.5d) ? j : Color.Companion.m1310getWhite0d7_KjU();
        }

        /* JADX INFO: renamed from: defaultRippleAlpha-DxMtmZc, reason: not valid java name */
        public final RippleAlpha m591defaultRippleAlphaDxMtmZc(long j, boolean z) {
            if (z) {
                return ((double) ColorKt.m1315luminance8_81llA(j)) > 0.5d ? RippleThemeKt.LightThemeHighContrastRippleAlpha : RippleThemeKt.LightThemeLowContrastRippleAlpha;
            }
            return RippleThemeKt.DarkThemeRippleAlpha;
        }
    }
}
