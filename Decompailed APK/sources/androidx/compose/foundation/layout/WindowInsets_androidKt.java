package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.core.graphics.Insets;

/* JADX INFO: compiled from: WindowInsets.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class WindowInsets_androidKt {
    public static final InsetsValues toInsetsValues(Insets insets) {
        return new InsetsValues(insets.left, insets.top, insets.right, insets.bottom);
    }

    public static final ValueInsets ValueInsets(Insets insets, String str) {
        return new ValueInsets(toInsetsValues(insets), str);
    }

    public static final WindowInsets getSystemBars(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-282936756, i, -1, "androidx.compose.foundation.layout.<get-systemBars> (WindowInsets.android.kt:200)");
        }
        AndroidWindowInsets systemBars = WindowInsetsHolder.Companion.current(composer, 6).getSystemBars();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return systemBars;
    }
}
