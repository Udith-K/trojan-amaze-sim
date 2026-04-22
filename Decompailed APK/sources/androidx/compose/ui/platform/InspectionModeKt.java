package androidx.compose.ui.platform;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: InspectionMode.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class InspectionModeKt {
    private static final ProvidableCompositionLocal LocalInspectionMode = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.InspectionModeKt$LocalInspectionMode$1
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.FALSE;
        }
    });

    public static final ProvidableCompositionLocal getLocalInspectionMode() {
        return LocalInspectionMode;
    }
}
