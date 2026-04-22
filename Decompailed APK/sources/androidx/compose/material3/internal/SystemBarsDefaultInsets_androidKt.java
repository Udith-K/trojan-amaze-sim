package androidx.compose.material3.internal;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;

/* JADX INFO: compiled from: SystemBarsDefaultInsets.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SystemBarsDefaultInsets_androidKt {
    public static final WindowInsets getSystemBarsForVisualComponents(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2011811170, i, -1, "androidx.compose.material3.internal.<get-systemBarsForVisualComponents> (SystemBarsDefaultInsets.android.kt:23)");
        }
        WindowInsets systemBars = WindowInsets_androidKt.getSystemBars(companion, composer, i & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return systemBars;
    }
}
