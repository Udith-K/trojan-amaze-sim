package androidx.compose.material3;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.material3.internal.SystemBarsDefaultInsets_androidKt;
import androidx.compose.material3.tokens.TopAppBarLargeTokens;
import androidx.compose.material3.tokens.TopAppBarMediumTokens;
import androidx.compose.material3.tokens.TopAppBarSmallTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TopAppBarDefaults {
    public static final TopAppBarDefaults INSTANCE = new TopAppBarDefaults();
    private static final float LargeAppBarCollapsedHeight;
    private static final float LargeAppBarExpandedHeight;
    private static final float MediumAppBarCollapsedHeight;
    private static final float MediumAppBarExpandedHeight;
    private static final float TopAppBarExpandedHeight;

    private TopAppBarDefaults() {
    }

    public final TopAppBarColors topAppBarColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1388520854, i, -1, "androidx.compose.material3.TopAppBarDefaults.topAppBarColors (AppBar.kt:977)");
        }
        TopAppBarColors defaultTopAppBarColors$material3_release = getDefaultTopAppBarColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTopAppBarColors$material3_release;
    }

    public final TopAppBarColors getDefaultTopAppBarColors$material3_release(ColorScheme colorScheme) {
        TopAppBarColors defaultTopAppBarColorsCached$material3_release = colorScheme.getDefaultTopAppBarColorsCached$material3_release();
        if (defaultTopAppBarColorsCached$material3_release != null) {
            return defaultTopAppBarColorsCached$material3_release;
        }
        TopAppBarSmallTokens topAppBarSmallTokens = TopAppBarSmallTokens.INSTANCE;
        TopAppBarColors topAppBarColors = new TopAppBarColors(ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getOnScrollContainerColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getHeadlineColor()), ColorSchemeKt.fromToken(colorScheme, topAppBarSmallTokens.getTrailingIconColor()), null);
        colorScheme.setDefaultTopAppBarColorsCached$material3_release(topAppBarColors);
        return topAppBarColors;
    }

    public final WindowInsets getWindowInsets(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2143182847, i, -1, "androidx.compose.material3.TopAppBarDefaults.<get-windowInsets> (AppBar.kt:1025)");
        }
        WindowInsets systemBarsForVisualComponents = SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.Companion, composer, 6);
        WindowInsetsSides.Companion companion = WindowInsetsSides.Companion;
        WindowInsets windowInsetsM293onlybOOhFvg = WindowInsetsKt.m293onlybOOhFvg(systemBarsForVisualComponents, WindowInsetsSides.m298plusgK_yJZ4(companion.m306getHorizontalJoeWqyM(), companion.m307getTopJoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return windowInsetsM293onlybOOhFvg;
    }

    static {
        TopAppBarSmallTokens topAppBarSmallTokens = TopAppBarSmallTokens.INSTANCE;
        TopAppBarExpandedHeight = topAppBarSmallTokens.m961getContainerHeightD9Ej5fM();
        MediumAppBarCollapsedHeight = topAppBarSmallTokens.m961getContainerHeightD9Ej5fM();
        MediumAppBarExpandedHeight = TopAppBarMediumTokens.INSTANCE.m960getContainerHeightD9Ej5fM();
        LargeAppBarCollapsedHeight = topAppBarSmallTokens.m961getContainerHeightD9Ej5fM();
        LargeAppBarExpandedHeight = TopAppBarLargeTokens.INSTANCE.m959getContainerHeightD9Ej5fM();
    }

    /* JADX INFO: renamed from: getTopAppBarExpandedHeight-D9Ej5fM, reason: not valid java name */
    public final float m778getTopAppBarExpandedHeightD9Ej5fM() {
        return TopAppBarExpandedHeight;
    }
}
