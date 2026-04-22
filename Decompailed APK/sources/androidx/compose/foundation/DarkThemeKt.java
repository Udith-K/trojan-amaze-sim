package androidx.compose.foundation;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;

/* JADX INFO: compiled from: DarkTheme.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DarkThemeKt {
    public static final boolean isSystemInDarkTheme(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1100791446, i, -1, "androidx.compose.foundation.isSystemInDarkTheme (DarkTheme.kt:40)");
        }
        boolean z_isSystemInDarkTheme = DarkTheme_androidKt._isSystemInDarkTheme(composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return z_isSystemInDarkTheme;
    }
}
