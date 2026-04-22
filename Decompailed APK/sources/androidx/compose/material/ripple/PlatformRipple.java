package androidx.compose.material.ripple;

import android.view.View;
import android.view.ViewGroup;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Ripple.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PlatformRipple extends Ripple {
    public /* synthetic */ PlatformRipple(boolean z, float f, State state, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, f, state);
    }

    private PlatformRipple(boolean z, float f, State state) {
        super(z, f, state, null);
    }

    @Override // androidx.compose.material.ripple.Ripple
    /* JADX INFO: renamed from: rememberUpdatedRippleInstance-942rkJo, reason: not valid java name */
    public RippleIndicationInstance mo579rememberUpdatedRippleInstance942rkJo(InteractionSource interactionSource, boolean z, float f, State state, State state2, Composer composer, int i) {
        composer.startReplaceGroup(331259447);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(331259447, i, -1, "androidx.compose.material.ripple.PlatformRipple.rememberUpdatedRippleInstance (Ripple.android.kt:92)");
        }
        ViewGroup viewGroupFindNearestViewGroup = Ripple_androidKt.findNearestViewGroup((View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView()));
        boolean zChanged = ((((i & 14) ^ 6) > 4 && composer.changed(interactionSource)) || (i & 6) == 4) | ((((458752 & i) ^ 196608) > 131072 && composer.changed(this)) || (i & 196608) == 131072) | composer.changed(viewGroupFindNearestViewGroup);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new AndroidRippleIndicationInstance(z, f, state, state2, viewGroupFindNearestViewGroup, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        AndroidRippleIndicationInstance androidRippleIndicationInstance = (AndroidRippleIndicationInstance) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return androidRippleIndicationInstance;
    }
}
