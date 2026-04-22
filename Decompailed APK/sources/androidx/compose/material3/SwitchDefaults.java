package androidx.compose.material3;

import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SwitchDefaults {
    public static final SwitchDefaults INSTANCE = new SwitchDefaults();
    private static final float IconSize = Dp.m2416constructorimpl(16);

    private SwitchDefaults() {
    }

    public final SwitchColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(435552781, i, -1, "androidx.compose.material3.SwitchDefaults.colors (Switch.kt:299)");
        }
        SwitchColors defaultSwitchColors$material3_release = getDefaultSwitchColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultSwitchColors$material3_release;
    }

    public final SwitchColors getDefaultSwitchColors$material3_release(ColorScheme colorScheme) {
        SwitchColors defaultSwitchColorsCached$material3_release = colorScheme.getDefaultSwitchColorsCached$material3_release();
        if (defaultSwitchColorsCached$material3_release != null) {
            return defaultSwitchColorsCached$material3_release;
        }
        SwitchTokens switchTokens = SwitchTokens.INSTANCE;
        long jFromToken = ColorSchemeKt.fromToken(colorScheme, switchTokens.getSelectedHandleColor());
        long jFromToken2 = ColorSchemeKt.fromToken(colorScheme, switchTokens.getSelectedTrackColor());
        Color.Companion companion = Color.Companion;
        SwitchColors switchColors = new SwitchColors(jFromToken, jFromToken2, companion.m1308getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, switchTokens.getSelectedIconColor()), ColorSchemeKt.fromToken(colorScheme, switchTokens.getUnselectedHandleColor()), ColorSchemeKt.fromToken(colorScheme, switchTokens.getUnselectedTrackColor()), ColorSchemeKt.fromToken(colorScheme, switchTokens.getUnselectedFocusTrackOutlineColor()), ColorSchemeKt.fromToken(colorScheme, switchTokens.getUnselectedIconColor()), ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledSelectedHandleColor()), switchTokens.getDisabledSelectedHandleOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.m643getSurface0d7_KjU()), ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledSelectedTrackColor()), switchTokens.getDisabledTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.m643getSurface0d7_KjU()), companion.m1308getTransparent0d7_KjU(), ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledSelectedIconColor()), switchTokens.getDisabledSelectedIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.m643getSurface0d7_KjU()), ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledUnselectedHandleColor()), switchTokens.getDisabledUnselectedHandleOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.m643getSurface0d7_KjU()), ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledUnselectedTrackColor()), switchTokens.getDisabledTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.m643getSurface0d7_KjU()), ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledUnselectedTrackOutlineColor()), switchTokens.getDisabledTrackOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.m643getSurface0d7_KjU()), ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, switchTokens.getDisabledUnselectedIconColor()), switchTokens.getDisabledUnselectedIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.m643getSurface0d7_KjU()), null);
        colorScheme.setDefaultSwitchColorsCached$material3_release(switchColors);
        return switchColors;
    }
}
