package androidx.compose.material3.internal;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;

/* JADX INFO: compiled from: Strings.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Strings_androidKt {
    /* JADX INFO: renamed from: getString-2EP1pXo, reason: not valid java name */
    public static final String m784getString2EP1pXo(int i, Composer composer, int i2) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-907677715, i2, -1, "androidx.compose.material3.internal.getString (Strings.android.kt:30)");
        }
        composer.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration());
        String string = ((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext())).getResources().getString(i);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return string;
    }
}
