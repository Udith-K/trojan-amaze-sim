package androidx.compose.material.ripple;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: RippleTheme.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RippleThemeKt {
    private static final ProvidableCompositionLocal LocalRippleTheme = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.material.ripple.RippleThemeKt$LocalRippleTheme$1
        @Override // kotlin.jvm.functions.Function0
        public final RippleTheme invoke() {
            return DebugRippleTheme.INSTANCE;
        }
    });
    private static final RippleAlpha LightThemeHighContrastRippleAlpha = new RippleAlpha(0.16f, 0.24f, 0.08f, 0.24f);
    private static final RippleAlpha LightThemeLowContrastRippleAlpha = new RippleAlpha(0.08f, 0.12f, 0.04f, 0.12f);
    private static final RippleAlpha DarkThemeRippleAlpha = new RippleAlpha(0.08f, 0.12f, 0.04f, 0.1f);

    public static final ProvidableCompositionLocal getLocalRippleTheme() {
        return LocalRippleTheme;
    }
}
