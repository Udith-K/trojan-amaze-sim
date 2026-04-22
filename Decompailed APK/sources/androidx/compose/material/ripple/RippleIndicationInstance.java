package androidx.compose.material.ripple;

import androidx.compose.foundation.IndicationInstance;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Ripple.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RippleIndicationInstance implements IndicationInstance {
    private final boolean bounded;
    private final StateLayer stateLayer;

    public abstract void addRipple(PressInteraction.Press press, CoroutineScope coroutineScope);

    public abstract void removeRipple(PressInteraction.Press press);

    public RippleIndicationInstance(boolean z, final State state) {
        this.bounded = z;
        this.stateLayer = new StateLayer(z, new Function0() { // from class: androidx.compose.material.ripple.RippleIndicationInstance$stateLayer$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final RippleAlpha invoke() {
                return (RippleAlpha) state.getValue();
            }
        });
    }

    public final void updateStateLayer$material_ripple_release(Interaction interaction, CoroutineScope coroutineScope) {
        this.stateLayer.handleInteraction$material_ripple_release(interaction, coroutineScope);
    }

    /* JADX INFO: renamed from: drawStateLayer-H2RKhps, reason: not valid java name */
    public final void m586drawStateLayerH2RKhps(DrawScope drawScope, float f, long j) throws Throwable {
        float fMo210toPx0680j_4;
        StateLayer stateLayer = this.stateLayer;
        if (Float.isNaN(f)) {
            fMo210toPx0680j_4 = RippleAnimationKt.m581getRippleEndRadiuscSwnlzA(drawScope, this.bounded, drawScope.mo1483getSizeNHjbRc());
        } else {
            fMo210toPx0680j_4 = drawScope.mo210toPx0680j_4(f);
        }
        stateLayer.m594drawStateLayermxwnekA(drawScope, fMo210toPx0680j_4, j);
    }
}
