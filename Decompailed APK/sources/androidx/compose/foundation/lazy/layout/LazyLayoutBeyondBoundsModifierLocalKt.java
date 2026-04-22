package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: compiled from: LazyLayoutBeyondBoundsModifierLocal.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyLayoutBeyondBoundsModifierLocalKt {
    public static final Modifier lazyLayoutBeyondBoundsModifier(Modifier modifier, LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsState, LazyLayoutBeyondBoundsInfo lazyLayoutBeyondBoundsInfo, boolean z, LayoutDirection layoutDirection, Orientation orientation, boolean z2, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1331498025, i, -1, "androidx.compose.foundation.lazy.layout.lazyLayoutBeyondBoundsModifier (LazyLayoutBeyondBoundsModifierLocal.kt:51)");
        }
        if (z2) {
            composer.startReplaceGroup(-1890632411);
            boolean z3 = ((((i & 112) ^ 48) > 32 && composer.changed(lazyLayoutBeyondBoundsState)) || (i & 48) == 32) | ((((i & 896) ^ 384) > 256 && composer.changed(lazyLayoutBeyondBoundsInfo)) || (i & 384) == 256) | ((((i & 7168) ^ 3072) > 2048 && composer.changed(z)) || (i & 3072) == 2048) | ((((57344 & i) ^ 24576) > 16384 && composer.changed(layoutDirection)) || (i & 24576) == 16384) | ((((458752 & i) ^ 196608) > 131072 && composer.changed(orientation)) || (i & 196608) == 131072);
            Object objRememberedValue = composer.rememberedValue();
            if (z3 || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new LazyLayoutBeyondBoundsModifierLocal(lazyLayoutBeyondBoundsState, lazyLayoutBeyondBoundsInfo, z, layoutDirection, orientation);
                composer.updateRememberedValue(objRememberedValue);
            }
            modifier = modifier.then((LazyLayoutBeyondBoundsModifierLocal) objRememberedValue);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1890658823);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return modifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void unsupportedDirection() {
        throw new IllegalStateException("Lazy list does not support beyond bounds layout for the specified direction");
    }
}
