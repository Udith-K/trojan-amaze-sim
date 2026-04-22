package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;

/* JADX INFO: compiled from: IconButton.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IconButtonDefaults {
    public static final IconButtonDefaults INSTANCE = new IconButtonDefaults();

    private IconButtonDefaults() {
    }

    public final IconButtonColors iconButtonColors(Composer composer, int i) {
        composer.startReplaceGroup(-1519621781);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1519621781, i, -1, "androidx.compose.material3.IconButtonDefaults.iconButtonColors (IconButton.kt:592)");
        }
        long jM1304unboximpl = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m1304unboximpl();
        IconButtonColors iconButtonColorsM689defaultIconButtonColors4WTKRHQ$material3_release = m689defaultIconButtonColors4WTKRHQ$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6), jM1304unboximpl);
        if (Color.m1296equalsimpl0(iconButtonColorsM689defaultIconButtonColors4WTKRHQ$material3_release.m688getContentColor0d7_KjU(), jM1304unboximpl)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return iconButtonColorsM689defaultIconButtonColors4WTKRHQ$material3_release;
        }
        IconButtonColors iconButtonColorsM687copyjRlVdoo = iconButtonColorsM689defaultIconButtonColors4WTKRHQ$material3_release.m687copyjRlVdoo((5 & 1) != 0 ? iconButtonColorsM689defaultIconButtonColors4WTKRHQ$material3_release.containerColor : 0L, (5 & 2) != 0 ? iconButtonColorsM689defaultIconButtonColors4WTKRHQ$material3_release.contentColor : jM1304unboximpl, (5 & 4) != 0 ? iconButtonColorsM689defaultIconButtonColors4WTKRHQ$material3_release.disabledContainerColor : 0L, (5 & 8) != 0 ? iconButtonColorsM689defaultIconButtonColors4WTKRHQ$material3_release.disabledContentColor : Color.m1294copywmQWz5c$default(jM1304unboximpl, 0.38f, 0.0f, 0.0f, 0.0f, 14, null));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return iconButtonColorsM687copyjRlVdoo;
    }

    /* JADX INFO: renamed from: defaultIconButtonColors-4WTKRHQ$material3_release, reason: not valid java name */
    public final IconButtonColors m689defaultIconButtonColors4WTKRHQ$material3_release(ColorScheme colorScheme, long j) {
        IconButtonColors defaultIconButtonColorsCached$material3_release = colorScheme.getDefaultIconButtonColorsCached$material3_release();
        if (defaultIconButtonColorsCached$material3_release != null) {
            return defaultIconButtonColorsCached$material3_release;
        }
        Color.Companion companion = Color.Companion;
        IconButtonColors iconButtonColors = new IconButtonColors(companion.m1308getTransparent0d7_KjU(), j, companion.m1308getTransparent0d7_KjU(), Color.m1294copywmQWz5c$default(j, 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultIconButtonColorsCached$material3_release(iconButtonColors);
        return iconButtonColors;
    }
}
