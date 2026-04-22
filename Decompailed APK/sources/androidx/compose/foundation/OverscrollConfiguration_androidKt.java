package androidx.compose.foundation;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: OverscrollConfiguration.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class OverscrollConfiguration_androidKt {
    private static final ProvidableCompositionLocal LocalOverscrollConfiguration = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.foundation.OverscrollConfiguration_androidKt$LocalOverscrollConfiguration$1
        @Override // kotlin.jvm.functions.Function0
        public final OverscrollConfiguration invoke() {
            return new OverscrollConfiguration(0L, null, 3, null);
        }
    }, 1, null);

    public static final ProvidableCompositionLocal getLocalOverscrollConfiguration() {
        return LocalOverscrollConfiguration;
    }
}
