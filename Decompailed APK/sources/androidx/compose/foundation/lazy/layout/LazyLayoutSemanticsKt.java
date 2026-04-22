package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: LazyLayoutSemantics.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyLayoutSemanticsKt {
    public static final float estimatedLazyScrollOffset(int i, int i2) {
        return i2 + (i * 500);
    }

    public static final Modifier lazyLayoutSemantics(Modifier modifier, Function0 function0, LazyLayoutSemanticState lazyLayoutSemanticState, Orientation orientation, boolean z, boolean z2, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1070136913, i, -1, "androidx.compose.foundation.lazy.layout.lazyLayoutSemantics (LazyLayoutSemantics.kt:46)");
        }
        Modifier modifierThen = modifier.then(new LazyLayoutSemanticsModifier(function0, lazyLayoutSemanticState, orientation, z, z2));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return modifierThen;
    }

    public static final float estimatedLazyMaxScrollOffset(int i, int i2, boolean z) {
        if (z) {
            return estimatedLazyScrollOffset(i, i2) + 100;
        }
        return estimatedLazyScrollOffset(i, i2);
    }
}
