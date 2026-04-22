package androidx.activity.compose;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.ViewTreeOnBackPressedDispatcherOwner;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: BackHandler.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LocalOnBackPressedDispatcherOwner {
    public static final LocalOnBackPressedDispatcherOwner INSTANCE = new LocalOnBackPressedDispatcherOwner();
    private static final ProvidableCompositionLocal LocalOnBackPressedDispatcherOwner = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.activity.compose.LocalOnBackPressedDispatcherOwner$LocalOnBackPressedDispatcherOwner$1
        @Override // kotlin.jvm.functions.Function0
        public final OnBackPressedDispatcherOwner invoke() {
            return null;
        }
    }, 1, null);

    private LocalOnBackPressedDispatcherOwner() {
    }

    public final OnBackPressedDispatcherOwner getCurrent(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2068013981, i, -1, "androidx.activity.compose.LocalOnBackPressedDispatcherOwner.<get-current> (BackHandler.kt:50)");
        }
        OnBackPressedDispatcherOwner onBackPressedDispatcherOwner = (OnBackPressedDispatcherOwner) composer.consume(LocalOnBackPressedDispatcherOwner);
        if (onBackPressedDispatcherOwner == null) {
            composer.startReplaceGroup(544166745);
            onBackPressedDispatcherOwner = ViewTreeOnBackPressedDispatcherOwner.get((View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView()));
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(544164296);
            composer.endReplaceGroup();
        }
        if (onBackPressedDispatcherOwner == null) {
            composer.startReplaceGroup(544168748);
            Object baseContext = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            while (true) {
                if (!(baseContext instanceof ContextWrapper)) {
                    baseContext = null;
                    break;
                }
                if (baseContext instanceof OnBackPressedDispatcherOwner) {
                    break;
                }
                baseContext = ((ContextWrapper) baseContext).getBaseContext();
            }
            onBackPressedDispatcherOwner = (OnBackPressedDispatcherOwner) baseContext;
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(544164377);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return onBackPressedDispatcherOwner;
    }
}
